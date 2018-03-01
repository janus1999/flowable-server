package org.janus.shiro;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.janus.system.model.Role;
import org.janus.system.model.User;
import org.janus.system.service.UserService;
import org.janus.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Sets;


public class JwtRealm extends AuthorizingRealm{

    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private UserService userService;
    
    @Override
    public boolean supports(AuthenticationToken token) {
        //表示此Realm只支持JwtToken类型
        return token instanceof JwtToken;
    }
    
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userCode = (String)principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		
        // 根据username查询角色
		List<Role> roles = userService.selectUserRoles(userCode);
		for(Role role : roles)
			authorizationInfo.addRole(role.getRoleId());

        // 根据username查询权限
        authorizationInfo.setStringPermissions(Sets.newHashSet("system:*"));

        return authorizationInfo;	
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		JwtToken jwtToken = (JwtToken)authenticationToken;
		String token = jwtToken.getToken();

		try{
			String userCode = tokenUtil.getUsercodeFromToken(token);
			User user = userService.getUserInfo(userCode);
			if(user == null)
				 throw new UnknownAccountException();
			
			if(user.getStatus() == 0)
				throw new LockedAccountException();
			
			return new SimpleAuthenticationInfo(userCode,token,getName());
		}catch(Exception e){
			throw new AuthenticationException(e);
		}
	}

}
