package com.leonfs.bi.core.dao;

import org.springframework.jdbc.core.JdbcTemplate;


public abstract class AbstractDAO {
	
	protected JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
}
