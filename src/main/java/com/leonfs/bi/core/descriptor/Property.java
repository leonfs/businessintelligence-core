package com.leonfs.bi.core.descriptor;

import com.leonfs.bi.core.descriptor.types.PropertyType;

public class Property {
	
	private String name;
	private String table;
	private String column;
	private String function;
	private String label;
	private String defaultValue;
	private PropertyType type;
	private Dimension dimension;
		
	public Property(String name){
		this.name = name;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getName() {
		return name;
	}

	public void setName(String propertyName) {
		this.name = propertyName;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}
	
	public String getTable(){
		return table;
	}
	
	public String getColumn(){
		return column;
	}
	
	public String getAlias(){
		return "AS " + this.getName();
	}


	public Dimension getDimension() {
		return dimension;
	}
	
	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

	public PropertyType getPropertyType() {
		return type;
	}
	
	
	public void setPropertyType(PropertyType type) {
		this.type = type;
	}
	
	public void setPropertyType(String typeName){
		this.setPropertyType(PropertyType.valueOf(typeName));
	}
	
	public void setLabel(String label){
		this.label = label;
	}
	
	public String getLabel(){
		return this.label;
	}

	public String getDefaultValue() {
		return defaultValue;
	}


	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	/*
	public String getAbsolutePath(){
		String alias = this.getDimension().getDescriptor().getJoinName(this.getTable());
		return alias + "." + this.getColumn();
	}
	
	public String getOlapAbsolutePath(){
		return this.dimension.getOlapAliasTable() + "." + this.getName();
	}*/
}
