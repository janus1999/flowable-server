package org.janus.system.dao;

import java.util.List;

import org.janus.system.model.Role;
import org.janus.system.model.User;

import com.baomidou.mybatisplus.mapper.BaseMapper;

public interface RoleMapper extends BaseMapper<Role>{

	public List<User> selectRoleUsers(String roleId);
}
