package org.janus.system.model;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import lombok.Data;

@Data
@TableName("sys_user")
public class User {

	@TableId(value="user_id", type=IdType.INPUT)
	private String userId;
	
	private String userCode;
	
	private String userName;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	
	private String companyId;
	
	private String companyName;
	
	private String deptId;
	
	private String deptName; 
	
	private String unitId;
	
	private String unitName;
	
	private int status;
	
	private int sort;
	
	private String mobile;
	
	private String phone;
	
	private String email;
	
	private String remark;
}
