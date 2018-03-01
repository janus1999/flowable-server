package org.janus.api;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum  RestResultEnum {
	OK(0,"操作成功"),
	UNKNOW_ERROR(1000,"未知错误"),
	ACCOUNT_UNKNOW_ERROR(2000,"未知帐号错误"),
	ACCOUNT_NOT_EXISTS(2001,"帐号不存在"),
	ACCOUNT_TIMEOUT(2002,"登录超时"),
	ACCOUNT_NO_POWER(2100,"帐号权限不足");
	
    private String msg;  
    private int code;  
      
    private RestResultEnum(int code,String msg) {  
        this.code=code;  
        this.msg=msg;  
    }  
    
    public String getMsg() {  
        return this.msg;  
    } 
    
    public int getCode() {  
      return this.code;  
    }  
}
