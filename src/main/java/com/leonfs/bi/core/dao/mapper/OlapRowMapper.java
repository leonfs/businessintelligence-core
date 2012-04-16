package com.leonfs.bi.core.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.leonfs.bi.core.dao.IOlapDataRow;
import com.leonfs.bi.core.dao.OlapDataRowImpl;
import com.leonfs.bi.core.descriptor.IDescriptor;
import com.leonfs.bi.core.descriptor.Property;

public class OlapRowMapper implements RowMapper<IOlapDataRow> {
	
	private IDescriptor descriptor;
	

	public OlapRowMapper(IDescriptor descriptor) {
		this.descriptor = descriptor;
	}
	

	public IOlapDataRow mapRow(ResultSet resultSet, int index) throws SQLException {
		long metric = resultSet.getLong(1);
		String metricString = new Long(metric).toString();
		OlapDataRowImpl dataRow = new OlapDataRowImpl(metricString); 
		Integer columnCount = resultSet.getMetaData().getColumnCount();
		
		for (int i = 2; i <= columnCount; i++) {
			String propertyName = resultSet.getMetaData().getColumnLabel(i);
			Property property = descriptor.getPropertyByName(propertyName);
			Object obj = resultSet.getObject(i);
			String resultValue = property.getType().cast(obj).toString();
			dataRow.addPropertyValue(propertyName, resultValue);
		}
		
		return dataRow;
	}
	
	
}
