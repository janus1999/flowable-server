package org.janus.system.service;

import java.util.List;

import org.janus.system.model.Role;
import org.janus.system.model.User;

public interface UserService {
	
	/**
	 * 验证用户密码
	 * @param userCode
	 * @param password
	 * @return
	 */
	public boolean validUser(String userCode,String password);

	/**
	 * 插入新用户
	 * @param user
	 * @return
	 */
	public int insert(User user);
	
	/**
	 * 根据用户ID获取用户信息
	 * @param userId
	 * @return
	 */
	public User selectById(String userId);
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public int updateById(User user);
	
	/**
	 * 根据用户编码获取用户主要身份信息
	 * @param userCode
	 * @return
	 */
	public User getUserInfo(String userCode);
	
	/**
	 * 获取用户所有的角色
	 * @param userCode
	 * @return
	 */
	public List<Role> selectUserRoles(String userCode);
	
}
