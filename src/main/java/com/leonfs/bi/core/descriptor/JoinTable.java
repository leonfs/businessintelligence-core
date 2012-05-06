package com.leonfs.bi.core.descriptor;

import java.util.ArrayList;
import java.util.Collection;

public class JoinTable {
	
	private IDescriptor descriptor;
	private String name;
	private String table;
	private Collection<JoinCondition> joinConditions = new ArrayList<JoinCondition>();
	
	public JoinTable(String name, IDescriptor descriptor) {
		this.name = name;
		this.descriptor = descriptor;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTable() {
		return table;
	}
	
	public void setTable(String table) {
		this.table = table;
	}

	public IDescriptor getDescriptor() {
		return descriptor;
	}

	public void setDescriptor(XmlDatabaseDescriptor descriptor) {
		this.descriptor = descriptor;
	}
	
	public void removeJoinCondition(JoinCondition joinCondition){
		this.joinConditions.remove(joinCondition);
	}
	
	public Collection<JoinCondition> getJoinConditions(){
		return this.joinConditions;
	}
	
	public void addJoinCondition(JoinCondition joinCondition){
		this.joinConditions.add(joinCondition);
	}
	
	/*
	public String getJoin(){
		StringBuffer joinBuffer = new StringBuffer("INNER JOIN " + this.table + " AS " + this.name + " ON " );
		for (JoinCondition joinCondition : joinConditions) {
			joinBuffer.append(this.name + "." + joinCondition.getColumn() + " = " + joinCondition.getFactColumn());
		}
		return joinBuffer.toString();
	}*/
}
