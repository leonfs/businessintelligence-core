package com.leonfs.bi.core.exceptions;

public class NoSuchDirectoryException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoSuchDirectoryException() {
		
	}
	
	@Override
	public String getMessage() {
		return super.getMessage() + " --- Not a valid directory ---";
	}
	
}
