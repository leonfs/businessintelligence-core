package com.leonfs.bi.core.descriptor;

import java.util.Collection;


public interface IDescriptor {
	
	public String getName();
	
	public String getLabel();
	
	public String getFacttable();
	
	public void addDimension(Dimension dimension);

	public Collection<Dimension> getDimensions();
	
	public Dimension getDimensionByName(String dimensionName);
	
	public Collection<Property> getProperties();
	
	public Property getPropertyByName(String propertyName);
	
	public String getMetric();
	
	public Class<? extends Number> getMetricType();
	
	public Collection<Property> getHorizontalDefaultProperties();
	
	public Collection<Property> getVerticalDefaultProperties();
	
	public Boolean hasPermission(String userPrivilege);
	
	public String getFormatterClassName();
	
	public Integer getCurrencyType();
}
