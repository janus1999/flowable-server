package org.janus.system.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import lombok.Data;

@Data
@TableName("syss_role")
public class Role {
	
	@TableId(value="role_id", type=IdType.INPUT)
	private String roleId;
	
	private String roleName;
	
	private int sort;
	
	private String remark;
}
