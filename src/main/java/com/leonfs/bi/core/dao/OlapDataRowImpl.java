package com.leonfs.bi.core.dao;

import java.util.HashMap;
import java.util.Map;

public class OlapDataRowImpl implements IOlapDataRow{

	private String metricValue;
	private Map<String, String> propertiesValues = new HashMap<String, String>();
	
	public OlapDataRowImpl(){
	}
	
	public OlapDataRowImpl(String metricValue){
		this.setMetricValue(metricValue);
	}
	
	public void setMetricValue(String metricValue){
		this.metricValue = metricValue;
	}
	
	public String getData(){
		return this.metricValue;
	}
	
	public void addPropertyValue(String propertyName, String value){
		this.propertiesValues.put(propertyName, value);		
	}
	
	public String getValue(String propertyName){
		return this.propertiesValues.get(propertyName);
	}

	

}
