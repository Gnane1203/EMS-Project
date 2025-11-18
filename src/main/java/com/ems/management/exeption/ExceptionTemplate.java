package com.ems.management.exeption;

import org.springframework.http.HttpStatus;

public class ExceptionTemplate extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpStatus statusCode;
	

	public ExceptionTemplate(String message,HttpStatus statusCode) {
		super(message);
		this.statusCode = statusCode;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}
	

}
