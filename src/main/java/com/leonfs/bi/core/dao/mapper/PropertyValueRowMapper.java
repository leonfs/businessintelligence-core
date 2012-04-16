package com.leonfs.bi.core.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PropertyValueRowMapper implements RowMapper<String> {
	
	public PropertyValueRowMapper(){
	}

	public String mapRow(ResultSet resultSet, int index) throws SQLException {
		return resultSet.getString(1);
	}
}
