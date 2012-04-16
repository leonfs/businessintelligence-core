package com.leonfs.bi.core.descriptor;

import com.leonfs.bi.utils.BiUtil;

public class Property {
	
	private String name;
	private String table;
	private String column;
	private String function;
	private String label;
	private String defaultValue;
	private Class<?> type;
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

	public Class<?> getType() {
		return type;
	}
	
	
	public void setType(Class<?> type) {
		this.type = type;
	}
	
	public void setType(String typeName){
		this.setType(BiUtil.getPropertyType(typeName));
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
