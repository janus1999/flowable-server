package org.janus.system.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import lombok.Data;

@Data
@TableName("sys_org")
public class Org {
	
	@TableId(value="org_id", type=IdType.INPUT)
	private String orgId;
	
	private String orgName;
	
	private String orgCode;
	
	private String orgParentId;
	
	private String orgParentName;
	
	private String companyId;
	
	private String companyName;
	
	private int sort;
}
