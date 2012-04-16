package com.leonfs.bi.core.dao;

import java.util.List;

import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import com.leonfs.bi.core.dao.mapper.OlapRowMapper;
import com.leonfs.bi.core.dao.mapper.PropertyValueRowMapper;
import com.leonfs.bi.core.descriptor.IDescriptor;

public class OlapDao extends AbstractDAO {
	
	public List<IOlapDataRow> getData(String query, List<String> properties, IDescriptor descriptor) {
		RowMapperResultSetExtractor<IOlapDataRow> extractor = new RowMapperResultSetExtractor<IOlapDataRow>(new OlapRowMapper(descriptor));
		List<IOlapDataRow> olapRows = jdbcTemplate.query(query, extractor);
		
		return olapRows;
	}
	
	public List<String> getValuesForProperty(String query) {
		RowMapperResultSetExtractor<String> extractor = new RowMapperResultSetExtractor<String>(new PropertyValueRowMapper());
		List<String> valuesForProperty = jdbcTemplate.query(query, extractor);
		
		return valuesForProperty;
		
	}

	public void executeDDLStatement(String statement) {
		jdbcTemplate.execute(statement);
	}
	
	public void executeBatch(String[] sql){
		jdbcTemplate.batchUpdate(sql);
	}
	
}
