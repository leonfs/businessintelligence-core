package com.leonfs.bi.core.descriptor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DuplicateKeyException;
import org.w3c.dom.Document;

import com.leonfs.bi.core.descriptor.types.MetricType;

public class XmlDatabaseDescriptor implements IDescriptor {
	
	private String descriptorName;
	private String metric;
	private String facttable;
	
	private Map<String, JoinTable> joins = new LinkedHashMap<String, JoinTable>();
	private Map<String, Dimension> dimensions = new LinkedHashMap<String, Dimension>();
	
	private String label;
	private MetricType metricType;
	private String privilege;
	private String formatterClassName = null;
	private Integer currencyType = null;
	
	private Document xmlDescriptorDocument;
	
	public XmlDatabaseDescriptor() {
	
	}
	
	public String getName() {
		return descriptorName;
	}

	public void setDescriptorName(String descriptorName) {
		this.descriptorName = descriptorName;
	}

	public Collection<Dimension> getDimensions() {
		return this.dimensions.values();
	}
	
	public Integer getDimensionsCount() {
		return this.dimensions.size();
	}

	public String getFacttable() {
		return facttable;
	}
	
	public void setFacttable(String facttable) {
		this.facttable = facttable;
	}
	
	public Collection<Property> getProperties() {
		List<Property> properties = new ArrayList<Property>();
		
		for (Dimension dimension : this.getDimensions()) {
			properties.addAll(dimension.getProperties());
		}
		return properties;
	}
	
	private Collection<Property> getDefaultProperties(String value){
		Collection<Property> properties = this.getProperties();
		List<Property> defaultProperties = new ArrayList<Property>();
		for (Property property : properties) {
			if (property.getDefaultValue() != null && property.getDefaultValue().equals(value)) {
				defaultProperties.add(property);
			}
		}
		return defaultProperties;
	}
	
	public Collection<Property> getHorizontalDefaultProperties(){
		return this.getDefaultProperties("row");
	}
	
	public Collection<Property> getVerticalDefaultProperties(){
		return this.getDefaultProperties("col");
	}
	
	public Collection<JoinTable> getJoins(){
		return this.joins.values();
	}
	
	public String getMetric() {
		return metric;
	}

	public void setMetric(String metric) {
		this.metric = metric;
	}
	
	public MetricType getMetricType(){
		return metricType;
	}
	
	public void setMetricType(MetricType metricType){
		this.metricType = metricType;
	}

	public Document getDescriptorDocument() {
		return xmlDescriptorDocument;
	}

	public void setDescriptorDocument(Document xmlDescriptorDocument) {
		this.xmlDescriptorDocument = xmlDescriptorDocument;
	}
		

	public String getJoinName(String table) {
		if(table.equals("facttable")){
			return "facttable";
		}
		
		JoinTable joinTable = this.joins.get(table);
		return joinTable.getName();
	}

	public void addDimension(Dimension dimension) {
		if (this.dimensions.containsKey(dimension.getName())) {
			throw new DuplicateKeyException("Dimension with name: " + dimension.getName() + " has been already added.");
		}
		this.dimensions.put(dimension.getName(), dimension);
		dimension.setDescriptor(this);
	}

	public void addJoin(JoinTable join) {
		this.joins.put(join.getTable(), join);	
	}
	
	public String getLabel(){
		return this.label;
	}
	
	public Property getPropertyByName(String propertyName) {
		for (String dimensionName : this.dimensions.keySet()) {
			Dimension dimension = this.dimensions.get(dimensionName);
			Property property = dimension.getPropertyByName(propertyName);
			if (property != null) {
				return dimension.getPropertyByName(propertyName);
			}	
		}
		return null;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	
	public Boolean hasPermission(String userPrivilege) {
		if(userPrivilege.equals("admin")){
			return true;
		} else if(userPrivilege.equals("general") && (getPrivilege().equals("general") || getPrivilege().equals("restricted")) ) {
			return true;
		} else if(userPrivilege.equals("restricted") && getPrivilege().equals("restricted")) {
			return true;
		}
		
		return false;
	}
	
	public void setLabel(String label){
		this.label = label;
	}
	
	public void removeDimension(String dimensionName){
		this.dimensions.remove(dimensionName);
	}
	
	public void removeJoin(String joinName){
		this.joins.remove(joinName);
	}

	public String getFormatterClassName() {
		return formatterClassName;
	}

	public void setFormatterClassName(String formatterClassName) {
		this.formatterClassName = formatterClassName;
	}

	public Integer getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(Integer currencyType) {
		this.currencyType = currencyType;
	}

	public Dimension getDimensionByName(String dimensionName) {
		return dimensions.get(dimensionName);
	}
	
}
