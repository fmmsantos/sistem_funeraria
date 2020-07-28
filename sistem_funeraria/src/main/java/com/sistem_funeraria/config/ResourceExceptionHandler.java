package com.sistem_funeraria.config;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler{ /*extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<Object> EsocialApiSecurityExceptionsHandle(RuntimeException exception, WebRequest request) {
		ExceptionResponse error =  responseError(exception, request);
		return handleExceptionInternal(exception, error, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
	}
	
	private ExceptionResponse responseError(RuntimeException exception, WebRequest request) {
		SimpleDateFormat sf  =  new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		ExceptionResponse exceptionResponse = new ExceptionResponse();
	   exceptionResponse.setCodeError(401);
		exceptionResponse.setDataHora(sf.format(new Date()));
		exceptionResponse.setMessage(exception.getMessage());
		exceptionResponse.setRequestedURI(req.getRequestURI());
		return exceptionResponse ;
	}
	*/
}
