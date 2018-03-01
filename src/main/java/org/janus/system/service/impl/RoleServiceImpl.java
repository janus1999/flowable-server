package org.janus.system.service.impl;

import java.util.List;

import org.janus.system.dao.RoleMapper;
import org.janus.system.model.Role;
import org.janus.system.model.User;
import org.janus.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	protected RoleMapper roleMapper;
	
	public int insertRole(Role role) {
		return roleMapper.insert(role);
	}


	public Role selectById(String id) {
		return roleMapper.selectById(id);
	}
	
	public List<User> selectRoleUsers(String roleId){
		return roleMapper.selectRoleUsers(roleId);
	}

}
