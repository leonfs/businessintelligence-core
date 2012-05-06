package com.leonfs.bi.core.exceptions;

public class TypeNotSupportedException extends RuntimeException {

	private static final long serialVersionUID = -3784041944656313039L;
	
	public TypeNotSupportedException(String notSupportedTypeName) {
		super("PropertyType with name:" + notSupportedTypeName + " is not supported.");
	}
	

}
