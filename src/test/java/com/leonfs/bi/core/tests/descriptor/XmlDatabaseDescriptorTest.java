package com.leonfs.bi.core.tests.descriptor;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import mockit.Expectations;
import mockit.NonStrict;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DuplicateKeyException;

import com.leonfs.bi.core.descriptor.Dimension;
import com.leonfs.bi.core.descriptor.XmlDatabaseDescriptor;


public class XmlDatabaseDescriptorTest {

	private XmlDatabaseDescriptor descriptor;
	
	@NonStrict
	Dimension productDimension;
	@NonStrict
	Dimension timeDimension;
	
	@Before
	public void prepareTestData() {
		
		
		new Expectations() {
			{
				productDimension.getName(); result = "productDimension";
				timeDimension.getName(); result = "timeDimension";
			}
		};
		
		descriptor = new XmlDatabaseDescriptor();	
	}
	
	
	@Test
	public void notNullDescriptor() {
		assertNotNull(descriptor);
	}
	
	@Test
	public void addDimension_Product_One() {
		descriptor.addDimension(productDimension);
		
		Integer expectedCount = 1;
		assertEquals(expectedCount, descriptor.getDimensionsCount());
	}
	
	@Test(expected = DuplicateKeyException.class)
	public void addDimension_SameDimensionTwice_ExceptionThrown() {
		descriptor.addDimension(productDimension);
		descriptor.addDimension(productDimension);
	}
	
	@Test
	public void addDimension_ProductAndTime_Two() {
		descriptor.addDimension(productDimension);
		descriptor.addDimension(timeDimension);
		
		Integer expectedCount = 2;
		assertEquals(expectedCount, descriptor.getDimensionsCount());
	}
	
	@Test
	public void getDimensionByName_NonExistentDimensionForGivenName_Null() {
		descriptor.addDimension(productDimension);
		
		Dimension actualDimension = descriptor.getDimensionByName("NonExistenDimensionName");
		assertNull(actualDimension);
	}
	
	@Test
	public void getDimensionByName_DescriptorWithoutDimensions_Null() {
		Dimension actualDimension = descriptor.getDimensionByName("NonExistenDimensionName");
		assertNull(actualDimension);
	}
	
	@Test
	public void getDimensionByName_ExistenProductDimension_ProductDimension() {
		descriptor.addDimension(productDimension);
		
		String productDimensionName = "productDimension";
		Dimension actualDimension = descriptor.getDimensionByName(productDimensionName);
		
		assertNotNull(actualDimension);
		assertEquals(productDimension, actualDimension);
	}
	
	
	
}
