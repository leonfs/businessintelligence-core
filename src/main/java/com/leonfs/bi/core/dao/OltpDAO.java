package com.leonfs.bi.core.dao;

import org.springframework.jdbc.core.ResultSetExtractor;

public class OltpDAO<T> extends AbstractDAO {
	
	public T getExtractionResult(String query, ResultSetExtractor<T> extractor) {		
		T result = (T)jdbcTemplate.query(query, extractor);
		return result;
	}
	

}
