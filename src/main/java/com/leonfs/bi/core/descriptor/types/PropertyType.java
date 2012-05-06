package com.leonfs.bi.core.descriptor.types;

import com.leonfs.bi.core.exceptions.TypeNotSupportedException;

public enum PropertyType {
	INTEGER(Integer.class),
	LONG(Long.class),
	STRING(String.class),
	DOUBLE(Double.class);
	
	private Class<?> clazz;
	
	private PropertyType(Class<?> clazz) {
		this.clazz = clazz;
	}
	
	public static Class<?> GetPropertyTypeClass(String name) {
		for (PropertyType propertyType : PropertyType.values()) {
			if (propertyType.name().equals(name)) {
				return propertyType.clazz;
			}
		}
		throw new TypeNotSupportedException(name);
	}

	public Class<?> getType() {
		return this.clazz;
	}

}
