package com.leonfs.bi.core.tests.descriptor;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.leonfs.bi.core.descriptor.types.PropertyType;
import com.leonfs.bi.core.exceptions.TypeNotSupportedException;

public class PropertyTypeTest {

	@Test(expected = TypeNotSupportedException.class)
	public void getPropertyType_InvalidName_ExceptionThrown() {
		Class<?> propertyType = PropertyType.GetPropertyTypeClass("InexistentType");
	}

	@Test
	public void getPropertyType_IntegerName_IntegerClass() {
		String integerName = "INTEGER";
		Class<?> propertyType = PropertyType.GetPropertyTypeClass(integerName);
		assertEquals(Integer.class, propertyType);
	}
	
	@Test
	public void equals_NotEqual_False() {
		PropertyType integerType = PropertyType.INTEGER;
		PropertyType stringType = PropertyType.STRING;
		
		assertFalse(integerType.equals((stringType)));
	}
	
	@Test
	public void equals_Equal_True() {
		PropertyType anotherStringType = PropertyType.STRING;
		PropertyType stringType = PropertyType.STRING;
		
		assertEquals(anotherStringType, stringType);
	}
	
	
	


}
