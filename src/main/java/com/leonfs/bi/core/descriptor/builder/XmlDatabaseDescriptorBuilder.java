package com.leonfs.bi.core.descriptor.builder;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.leonfs.bi.core.descriptor.Dimension;
import com.leonfs.bi.core.descriptor.IDescriptor;
import com.leonfs.bi.core.descriptor.JoinCondition;
import com.leonfs.bi.core.descriptor.JoinTable;
import com.leonfs.bi.core.descriptor.Property;
import com.leonfs.bi.core.descriptor.XmlDatabaseDescriptor;
import com.leonfs.bi.core.descriptor.types.MetricType;
import com.leonfs.bi.utils.BiUtil;
import com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl;
import com.sun.org.apache.xpath.internal.jaxp.XPathFactoryImpl;

public class XmlDatabaseDescriptorBuilder implements IDescriptorBuilder {
	
	Document document = null;
	XmlDatabaseDescriptor descriptor;
	
	private static String DESCRIPTOR_XPATH = "/bi";
	private static String FACTTABLE_XPATH = "/bi/facttable";
	private static String JOINS_XPATH = "/bi/facttable/joins";
	private static String DIMENSIONS_XPATH = "/bi/facttable/dimensions";
	
	public XmlDatabaseDescriptorBuilder(String filePath) {
		load(new File(filePath));
	}
	
	public XmlDatabaseDescriptorBuilder(File file) {
		load(file);
	}

	public IDescriptor getDescriptor() {
		return descriptor;
	}
	
	private void load(File file) {
		DocumentBuilderFactory dFactory = new DocumentBuilderFactoryImpl();
		
		try {
			document = dFactory.newDocumentBuilder().parse(file);
			descriptor = new XmlDatabaseDescriptor();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	private Node getNode(String xpath) {
		try {
			return (Node)XPathFactoryImpl.newInstance().newXPath().evaluate(xpath, document, XPathConstants.NODE);	
		} catch (XPathExpressionException e) {
			return null;
		}
	}
	
	public IDescriptorBuilder buildJoinTables() {
		Node joinsNode = this.getNode(JOINS_XPATH);
		NodeList joinsList = joinsNode.getChildNodes();
		for (int i = 0; i < joinsList.getLength(); i++) {
			Node joinNode = joinsList.item(i);
			if(joinNode.getNodeName().equals("join")) {
				String joinName = joinNode.getAttributes().getNamedItem("name").getNodeValue();
				JoinTable joinTable = new JoinTable(joinName, descriptor);
				loadJoinTable(joinTable, joinNode);
			}
		}
		return this;
	}
	
	public IDescriptorBuilder buildFactTable() {
		Node biNode = this.getNode(DESCRIPTOR_XPATH);
		descriptor.setDescriptorName(biNode.getAttributes().getNamedItem("name").getNodeValue());
		descriptor.setLabel(biNode.getAttributes().getNamedItem("label").getNodeValue());
		descriptor.setPrivilege(biNode.getAttributes().getNamedItem("privilege").getNodeValue());
		
		Node facttableNode = this.getNode(FACTTABLE_XPATH);
		NamedNodeMap facttableAttributes = facttableNode.getAttributes();
		descriptor.setFacttable(facttableAttributes.getNamedItem("table").getNodeValue());
		descriptor.setMetric(facttableAttributes.getNamedItem("metric").getNodeValue());
		String typeName = facttableAttributes.getNamedItem("type").getNodeValue();
		descriptor.setMetricType(MetricType.valueOf(typeName));
		
		if(facttableAttributes.getNamedItem("formatter")!=null){
			descriptor.setFormatterClassName(facttableAttributes.getNamedItem("formatter").getNodeValue());
			descriptor.setCurrencyType((facttableAttributes.getNamedItem("currency") != null) ? 
					new Integer(facttableAttributes.getNamedItem("currency").getNodeValue()) : null);
			
		}
		
		return this;
	}

	
	public IDescriptorBuilder buildDimensions() {
		Node dimensionsNode= this.getNode(DIMENSIONS_XPATH);
		NodeList dimensionsList = dimensionsNode.getChildNodes();
		for (int i = 0; i < dimensionsList.getLength(); i++) {
			Node dimensionNode = dimensionsList.item(i);
			if(dimensionNode.getNodeName().equals("dimension")){
				String dimensionName = dimensionNode.getAttributes().getNamedItem("name").getNodeValue();
				Dimension dimension = new Dimension(dimensionName);
				descriptor.addDimension(dimension);
			}
		}
		return this;
	}
	
	public IDescriptorBuilder buildProperties() {
		for(Dimension dimension: descriptor.getDimensions()) {
			loadPropertiesForDimension(dimension);
		}
		return  this;
	}
	
	public void loadPropertiesForDimension(Dimension dimension) {
		Node dimensionNode = this.getNode("//dimension[@name='" + dimension.getName() + "']"); 
		NodeList propertyNodes = dimensionNode.getChildNodes();
		for (int i = 0; i < propertyNodes.getLength(); i++) {
			Node propertyNode = propertyNodes.item(i);
			if (propertyNode.getNodeName().equals("property")) {
				String propertyName = propertyNode.getAttributes().getNamedItem("name").getNodeValue();
				Property property = new Property(propertyName);
				loadProperty(property, propertyNode);
				dimension.addProperty(property);
			}
		}
	}
	
	private void loadProperty(Property property, Node propertyNode) {
		NamedNodeMap attributes = propertyNode.getAttributes();
		property.setColumn(attributes.getNamedItem("column").getNodeValue());
		property.setTable(attributes.getNamedItem("table").getNodeValue());
		property.setFunction(attributes.getNamedItem("function") != null  ? 
				attributes.getNamedItem("function").getNodeValue() : null);
		String type = attributes.getNamedItem("type") != null ? 
				attributes.getNamedItem("type").getNodeValue() : "string";
				property.setPropertyType(type);	
				property.setLabel(attributes.getNamedItem("label") != null ? attributes.getNamedItem("label").getNodeValue() : "noText");
				property.setDefaultValue(attributes.getNamedItem("default") != null ? attributes.getNamedItem("default").getNodeValue() : null);
	}
	
	private void loadJoinTable(JoinTable joinTable, Node joinNode) {
		NamedNodeMap attributes = joinNode.getAttributes();
		joinTable.setTable(attributes.getNamedItem("table").getNodeValue());
		
		NodeList joinConditionNodes = joinNode.getChildNodes();
		for (int i = 0; i < joinConditionNodes.getLength(); i++) {
			Node joinConditionNode = joinConditionNodes.item(i);
			if (joinConditionNode.getNodeName().endsWith("condition")) {
				String column = joinConditionNode.getAttributes().getNamedItem("column").getNodeValue();
				String factColumn = joinConditionNode.getAttributes().getNamedItem("factColumn").getNodeValue();
				JoinCondition joinCondition = new JoinCondition(column, factColumn);
				joinTable.addJoinCondition(joinCondition);
			}
		}
		((XmlDatabaseDescriptor)joinTable.getDescriptor()).addJoin(joinTable);
	}
	
	public void loadDimension(Dimension dimension) throws XPathExpressionException {
		dimension.getDescriptor().addDimension(dimension);
	}



}
