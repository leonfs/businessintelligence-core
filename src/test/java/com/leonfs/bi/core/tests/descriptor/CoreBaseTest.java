package com.leonfs.bi.core.tests.descriptor;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leonfs.bi.core.descriptor.IDescriptor;

@ContextConfiguration(locations = {"classpath:/com/leonfs/bi/core/tests/CoreBaseTest-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class) 
public class CoreBaseTest {

	
	
	protected IDescriptor descriptor;
	
	public void setDescriptor(IDescriptor descriptor) {
		this.descriptor = descriptor;
	}
	
	@Test
	public void testNotNullDescriptor() throws Exception {
		assertNotNull(descriptor);
	}
	
	
	
}
