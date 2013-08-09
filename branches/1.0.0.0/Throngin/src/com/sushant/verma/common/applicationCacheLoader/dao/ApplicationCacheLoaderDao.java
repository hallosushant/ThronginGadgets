package com.sushant.verma.common.applicationCacheLoader.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class ApplicationCacheLoaderDao extends JdbcDaoSupport{

	protected static Logger log=LogManager.getLogger(ApplicationCacheLoaderDao.class);
	
	@SuppressWarnings("unchecked")
	public Map<String, String> getContextParameter(String pquery){
		log.debug("LoadApplicationAttributeDao entered.");
		
		return (Map<String, String>)getJdbcTemplate().query(pquery, new ResultSetExtractor(){
			public Object extractData(ResultSet rs) throws SQLException,DataAccessException {
				log.debug("result set extractor entered");
				LinkedHashMap<String, String> map=new LinkedHashMap<String, String>();
				while (rs.next()) map.put(rs.getString(1), rs.getString(2));
				return map;
			}
			}
		);
	}
}
