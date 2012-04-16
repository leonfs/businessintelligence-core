package com.leonfs.bi.core.tests.descriptor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Collection;

import mockit.Expectations;
import mockit.Mocked;
import mockit.NonStrict;

import org.junit.Before;
import org.junit.Test;

import com.leonfs.bi.core.descriptor.Dimension;
import com.leonfs.bi.core.descriptor.IDescriptor;
import com.leonfs.bi.core.descriptor.Property;

public class DimensionTest {

	private Dimension product;

	@NonStrict
	Property name;
	@NonStrict
	Property category;
	@Mocked
	IDescriptor descriptor;

	private static final String TEST_DIMENSION_NAME = "Products";

	@Before
	public void prepareTestData() {
		product = new Dimension(TEST_DIMENSION_NAME);
		
		
		new Expectations() {
			{
				name.getName(); result = "productname"; 
				name.getTable(); result = "product"; 
				category.getName(); result = "category"; 
				category.getTable(); result = "productcategory"; 
			}
		};

		product.setDescriptor(descriptor);
		product.addProperty(name);
		product.addProperty(category);
	}

	@Test
	public void getPropertiesCount_AddedProductNameAndCategory_TwoProperties() {
		assertEquals(2, product.getPropertiesCount());
	}

	@Test
	public void getPropertyByName_NameProperty_NotNull() {
		assertNotNull(product.getPropertyByName("productname"));
	}
	
	@Test
	public void getPropertyByName_InexistentProperty_Null() {
		assertNull(product.getPropertyByName("notexistentpropertyname"));
	}
	
	@Test
	public void getPropertyNames_AddedProductNameAndCategory_TwoPropertyNames() {
		Collection<String> propertiesNames = product.getPropertiesNames();
		assertEquals(2, propertiesNames.size());
		assertEquals(true, propertiesNames.contains("productname"));
		assertEquals(true, propertiesNames.contains("category"));
	}

	@Test
	public void getPropertyType_NameProperty_StringClass() {
		new Expectations() {
			{
				name.getType(); result = String.class;
			}
		};
		assertEquals(String.class, product.getPropertyType("productname"));
	}
	
	@Test
	public void getReferencedTables_ProductAndProductCategoryTables_TwoTables() {
		Collection<String> referencedTables = product.getReferencedTables();
		assertEquals(2, referencedTables.size());
	}
	
	@Test
	public void getOlapAliasTable() {
		assertEquals("prod", product.getOlapAliasTable());
	}
	
	@Test
	public void getOlapAbsolutePath() {
		new Expectations() {
			{
				descriptor.getName(); result = "DescriptorName"; notStrict();
			}
		};
		
		assertEquals("descriptornameproductsdimensionprod", product.getOlapAbsolutePath());
	}
	
	
	

}
