package com.leonfs.bi.core.descriptor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Dimension {

	private String name;
	private Map<String, Property> properties = new LinkedHashMap<String, Property>();
	private IDescriptor descriptor;

	public Dimension(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	protected void setName(String dimensionName) {
		this.name = dimensionName;
	}	

	public IDescriptor getDescriptor() {
		return descriptor;
	}
	
	public void setDescriptor(IDescriptor descriptor) {
		this.descriptor = descriptor;
	}

	public Collection<Property> getProperties() {
		return this.properties.values();
	}

	public Property getPropertyByName(String propertyName) {
		return this.properties.get(propertyName);
	}

	public List<String> getPropertiesNames() {
		List<String> propertyNames = new ArrayList<String>();
		for (Property property : this.getProperties()) {
			propertyNames.add(property.getName());
		}
		return propertyNames;
	}
	
	public Collection<String> getReferencedTables() {
		Set<String> tables = new TreeSet<String>();
		for (Property property : this.getProperties()) {
			tables.add(property.getTable());
		}
		return tables;
	}

	public Class<?> getPropertyType(String propertyName) {
		return getPropertyByName(propertyName).getType();
	}

	public void addProperty(Property property) {
		this.properties.put(property.getName(), property);
		property.setDimension(this);
	}

	public String getOlapAliasTable() {
		return this.getName().toLowerCase().substring(0, 4);
	}

	public String getOlapAbsolutePath() {
		return getDescriptor().getName().toLowerCase() + 
				this.getName().toLowerCase() + "dimension" + 
				getOlapAliasTable();
	}

	public void removeProperty(String propertyName) {
		this.properties.remove(propertyName);
	}
	
	public int getPropertiesCount() {
		return this.properties.size();
	}

}
