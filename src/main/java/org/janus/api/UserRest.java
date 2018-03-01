package org.janus.api;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.janus.system.model.Role;
import org.janus.system.model.User;
import org.janus.system.service.UserService;
import org.janus.shiro.JwtToken;
import org.janus.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserRest {

    @Autowired
    private TokenUtil tokenUtil;
	@Resource
	protected UserService userService;
	@Value("${jwt.token.header}")
	protected String header;
	
	@RequestMapping(value="/login", method=RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public RestResultEnum login(@RequestBody Map<String,String> user, HttpServletResponse response){
		String userCode = user.get("userCode");
		String password = user.get("password");
		
		// 验证用户名密码成功后生成token
		String token = tokenUtil.generateToken(userCode);
        // 构建JwtToken
        JwtToken jwtToken = JwtToken.builder().token(token).principal(userCode).build();
        
        Subject subject = SecurityUtils.getSubject();
        
        // 该方法会调用Realm中的doGetAuthenticationInfo方法
        subject.login(jwtToken);
        
        if(subject.isAuthenticated()){
        	response.addHeader(header, token);
        	return RestResultEnum.OK;
        }
        
        throw new UnknownAccountException();
	}
	
	@RequestMapping(value="/{userCode}", method=RequestMethod.GET, produces="application/json; charset=UTF-8")
	public User getUserInfo(@PathVariable String userCode){
		User user = userService.getUserInfo(userCode);
		return user;
	}
	
	@RequiresRoles("admin")
	@ResponseStatus(HttpStatus.CREATED)	
	@RequestMapping(value="/{userCode}", method=RequestMethod.POST, produces="application/json; charset=UTF-8")
	public RestResultEnum insertUser(@PathVariable String userCode, @RequestBody User user){
		int count = userService.insert(user);
		return count==1 ? RestResultEnum.OK : RestResultEnum.UNKNOW_ERROR;
	}
	
	@RequestMapping(value="/{userCode}/roles", method=RequestMethod.GET, produces="application/json; charset=UTF-8")
	public List<Role> getUserRoles(@PathVariable String userCode){
		return userService.selectUserRoles(userCode);
	}
}
