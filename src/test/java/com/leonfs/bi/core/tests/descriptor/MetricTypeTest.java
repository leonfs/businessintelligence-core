package com.leonfs.bi.core.tests.descriptor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.leonfs.bi.core.descriptor.types.MetricType;
import com.leonfs.bi.core.exceptions.TypeNotSupportedException;

public class MetricTypeTest {
	
	@Test(expected = TypeNotSupportedException.class)
	public void getMetricType_InvalidName_ExceptionThrown() {
		Class<?> metricType = MetricType.GetMetricTypeClass("InexistentType");
	}

	@Test
	public void getPropertyType_IntegerName_IntegerClass() {
		String integerName = "INTEGER";
		Class<?> metricType = MetricType.GetMetricTypeClass(integerName);
		assertEquals(Integer.class, metricType);
	}
	
	@Test
	public void equals_NotEqual_False() {
		MetricType integerType = MetricType.INTEGER;
		MetricType longType = MetricType.LONG; 
		
		assertFalse(integerType.equals((longType)));
	}
	
	@Test
	public void equals_Equal_True() {
		MetricType anotherIntegerType = MetricType.INTEGER;
		MetricType integerType = MetricType.INTEGER;
		
		assertEquals(anotherIntegerType, integerType);
	}

}
