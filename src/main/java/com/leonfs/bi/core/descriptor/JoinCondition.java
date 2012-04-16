package com.leonfs.bi.core.descriptor;


public class JoinCondition {
	
	private String factColumn;
	private String column;
	
	public JoinCondition(String column, String factColumn){
		this.column = column;
		this.factColumn = factColumn;
	}
	
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getFactColumn() {
		return factColumn;
	}
	public void setFactColumn(String factColumn) {
		this.factColumn = factColumn;
	}
	
	public String toString(){
		return this.column +" | "+ this.factColumn;
	}
	

}
