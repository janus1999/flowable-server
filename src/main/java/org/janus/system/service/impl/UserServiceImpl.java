package org.janus.system.service.impl;

import java.util.List;

import org.janus.system.dao.UserMapper;
import org.janus.system.model.Role;
import org.janus.system.model.User;
import org.janus.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;

	@Override
	public boolean validUser(String userCode, String password) {
		return true;
	}
	
	@Override
	public int insert(User user) {
		return userMapper.insert(user);
	}
	
	public int updateById(User user){
		return userMapper.updateById(user);
	}

	@Override
	public User selectById(String userId) {
		return userMapper.selectById(userId);
	}

	@Override
	public User getUserInfo(String userCode) {
		return userMapper.getUserInfo(userCode);
	}
	
	@Override
	public List<Role> selectUserRoles(String userCode){
		return userMapper.selectUserRoles(userCode);
	}

}
