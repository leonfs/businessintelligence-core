package com.leonfs.bi.core.exceptions;

public class UserLoginException extends RuntimeException{

	private String message;
	private static final long serialVersionUID = 1L;

	public UserLoginException(String m) {
		this.message = m;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
