package com.leonfs.bi.core.descriptor.builder;

import com.leonfs.bi.core.descriptor.IDescriptor;

public interface IDescriptorBuilder {

	public IDescriptorBuilder buildProperties();
	public IDescriptorBuilder buildJoinTables();
	public IDescriptorBuilder buildFactTable();
	public IDescriptorBuilder buildDimensions();
	public IDescriptor getDescriptor();
	
}
