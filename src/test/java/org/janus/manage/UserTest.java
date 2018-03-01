package org.janus.manage;

import javax.annotation.Resource;

import org.janus.ServerApplication;
import org.janus.system.dao.UserMapper;
import org.janus.system.model.User;
import org.janus.system.service.UserService;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=ServerApplication.class)
public class UserTest {
	
	@Resource
	private UserMapper userMapper;
	@Resource
	private UserService userService;

	public UserTest() {
		
	}
	
	@Test
	@Transactional
	public void addUser(){
		User user = new User();
		user.setUserId("test");
		user.setUserCode("test");
		user.setUserName("测试帐号");
		user.setBirthday(new DateTime(2010,10,10,0,0,0).toDate());
		user.setCompanyId("1000");
		user.setCompanyName("公司");
		user.setDeptId("1000100");
		user.setDeptName("部门");
		user.setUnitId("1000100");
		user.setUnitName("部门");
		user.setStatus(1);
		//userService.insert(user);
		
		User u = userMapper.getUserInfo("admin");
		u = userService.getUserInfo("admin");
		
	}
}
