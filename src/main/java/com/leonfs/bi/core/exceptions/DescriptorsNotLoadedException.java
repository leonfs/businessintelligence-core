package com.leonfs.bi.core.exceptions;

public class DescriptorsNotLoadedException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7927827358195044957L;

	@Override
	public String getMessage() {	
		return super.getMessage() + "--- No se han cargado o no se han encontrado descriptores ---";
	}

}
