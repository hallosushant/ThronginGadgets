package com.sushant.verma.common.dbConnect;

import javax.sql.DataSource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class JdbcCallDataSource{

	private static final long serialVersionUID = 1464567574634L;
	private static Logger log = LogManager.getLogger(JdbcCallDataSource.class);
	protected static SimpleJdbcCall simpleJdbcCallDataSource;
	
	public void setDataSource(DataSource dataSource) {
		log.info("@ JdbcCallDataSource setDataSource()");
		simpleJdbcCallDataSource =new SimpleJdbcCall(dataSource);
	}

}
