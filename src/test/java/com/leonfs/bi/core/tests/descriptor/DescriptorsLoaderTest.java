package com.leonfs.bi.core.tests.descriptor;

import org.junit.Test;

import com.leonfs.bi.core.descriptor.DescriptorsLoader;

public class DescriptorsLoaderTest {
	
	@Test
	public void testDescriptorLoading(){
		DescriptorsLoader.loadDescriptors();
		for (String name : DescriptorsLoader.getAllDescriptorsNames()) {
			System.out.println(name);
		}
		
	}

}
