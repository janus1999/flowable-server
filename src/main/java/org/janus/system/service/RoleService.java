package org.janus.system.service;

import java.util.List;

import org.janus.system.model.Role;
import org.janus.system.model.User;

public interface RoleService {

	public int insertRole(Role role);
	
	public Role selectById(String id);
	
	public List<User> selectRoleUsers(String roleId);
}
