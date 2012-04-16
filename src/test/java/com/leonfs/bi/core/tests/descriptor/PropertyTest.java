package com.leonfs.bi.core.tests.descriptor;

import static org.junit.Assert.*;
import mockit.Expectations;
import mockit.Mocked;

import org.junit.Before;
import org.junit.Test;

import com.leonfs.bi.core.descriptor.Dimension;
import com.leonfs.bi.core.descriptor.Property;

public class PropertyTest {
	
	private Property productName;
	
	@Mocked
	Dimension product;
	
	private static final String TEST_PROPERTY_NAME = "productname";

	@Before
	public void prepareTestData() {
		productName = new Property(TEST_PROPERTY_NAME);
		
		
		new Expectations() {
			{
			}
		};

		productName.setDimension(product);
		
	}
	
	@Test
	public void getAlias_ProductName_StringWithAsAndThePropertyName() {
		assertEquals("AS productname", productName.getAlias());
	}
}
