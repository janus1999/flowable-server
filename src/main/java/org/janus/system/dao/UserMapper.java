package org.janus.system.dao;

import java.util.List;

import org.janus.system.model.Role;
import org.janus.system.model.User;

import com.baomidou.mybatisplus.mapper.BaseMapper;

public interface UserMapper extends BaseMapper<User>{

	public User getUserInfo(String userCode);
	
	public List<Role> selectUserRoles(String userCode);
}
