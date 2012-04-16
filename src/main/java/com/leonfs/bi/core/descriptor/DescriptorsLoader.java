package com.leonfs.bi.core.descriptor;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.leonfs.bi.core.descriptor.builder.XMLDatabaseDescriptorBuilder;
import com.leonfs.bi.core.exceptions.NoSuchDirectoryException;
import com.leonfs.bi.utils.BiUtil;

public class DescriptorsLoader {

	private static Map<String, IDescriptor> descriptors;
	
	private DescriptorsLoader() {
		
	}
	
	public static synchronized void loadDescriptors(){
		File directory = new File(BiUtil.getDescriptorsDirectory());
		
		if(!directory.isDirectory()){
			throw new NoSuchDirectoryException();
		}
		
		File[] fileDescriptors = directory.listFiles(new FilenameFilter(){
			public boolean accept(File dir, String name) {
				if(name.endsWith("_descriptor.xml")){
					return true;
				}
				return false;
			}
		});
		
		descriptors = new HashMap<String, IDescriptor>();
		
		for(int i=0; i < fileDescriptors.length; i++){
			XMLDatabaseDescriptorBuilder descriptorBuilder = new XMLDatabaseDescriptorBuilder(fileDescriptors[i]);
			descriptorBuilder.buildFactTable()
						.buildJoinTables()
						.buildDimensions()
						.buildProperties();
			IDescriptor descriptor = descriptorBuilder.getDescriptor();
			descriptors.put(descriptor.getName(), descriptor);
		}
	}

	public static Collection<IDescriptor> getAllDescriptors(){
		if(descriptors == null){
			loadDescriptors();
		}
		return descriptors.values();
	}
	
	public static synchronized IDescriptor getDescriptorByName(String descName){
		if(descriptors == null){
			loadDescriptors();
		}
		if(descriptors.containsKey(descName)){
			return descriptors.get(descName);
		}
		
		return null;
	}
	
	public static Collection<String> getAllDescriptorsNames(){
		if(descriptors == null){
			loadDescriptors();
		}
		Collection<String> names = new ArrayList<String>();
		for(IDescriptor descriptor: descriptors.values()){
			names.add(descriptor.getName());
		}
		return names;
	}

	public static void reload() {
		synchronized (descriptors) {
			descriptors.clear();
			loadDescriptors();			
		}
	}	
	
}
