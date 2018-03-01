package org.janus.exception;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.janus.api.RestResultEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ExceptionHandle {

	@ResponseBody
	@ExceptionHandler
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public  RestResultEnum handleAuthenticationException(AuthenticationException e) {
		log.error("UnknownAccountException:",e);
		return RestResultEnum.ACCOUNT_UNKNOW_ERROR;
	}
	
	@ResponseBody
	@ExceptionHandler
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public RestResultEnum handleExpiredJwtException(ExpiredJwtException e){
		log.error("ExpiredJwtException:", e);
		return RestResultEnum.ACCOUNT_TIMEOUT;
	}
	
	@ResponseBody
	@ExceptionHandler
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public RestResultEnum handleUnauthorizedException(UnauthorizedException e){
		log.error("UnauthorizedException:", e);
		return RestResultEnum.ACCOUNT_NO_POWER;
	}
	
	@ResponseBody
	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public RestResultEnum handleException(Exception e){
		log.error("Exception:", e);
		return RestResultEnum.UNKNOW_ERROR;
	}

}
