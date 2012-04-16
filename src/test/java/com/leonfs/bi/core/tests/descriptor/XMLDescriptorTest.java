package com.leonfs.bi.core.tests.descriptor;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

public class XMLDescriptorTest extends CoreBaseTest {
	
	private static final int dimensionsAmount = 3;
	
	@Test
	public void testDimensionsAmount(){
		assertEquals(dimensionsAmount, this.descriptor.getDimensions().size());
	}
	
	@Test
	public void testMetric(){
		assertEquals("count(*)", this.descriptor.getMetric());
	}

}
