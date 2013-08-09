package com.sushant.verma.login.dao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.sushant.verma.common.constants.DbConstants;
import com.sushant.verma.common.exception.CatchedException;

public class LoginDao extends JdbcDaoSupport {
	
	private static final long serialVersionUID = 1464567574634L;

	private static Logger log = LogManager.getLogger(LoginDao.class);

	public String authenticateUser(String userName) throws Exception{
		log.info("@Start of authenticateUser");
		log.debug("|___userName=="+userName);
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select LIKES from people where name= ? ");
		
		Object args[] = new Object[1];
		args[0] = userName;
		
		String userPassword=null;
		try{
			userPassword=(String) getJdbcTemplate().queryForObject(sql.toString().toLowerCase(), args, String.class);			
		}
		catch (EmptyResultDataAccessException e) {
			log.error("Error: EmptyResultDataAccessException");
			String exceptionMessage = "User Does not exist";
			String exceptionCode = "CatExc-001";
			throw new CatchedException(exceptionMessage, exceptionCode);
		}
		return userPassword;
	}
	
	
	public void logLoginHistory(String userName,String ipAddress){
		log.info("@Start of logLoginHistory");
		log.debug("|__userName="+userName+
				"\t|__ipAddress="+ipAddress);
		
		StringBuilder sql=new StringBuilder();
		sql.append(" INSERT INTO loginhistory (userId,loginTime, ipAddress ) ");
		sql.append(" VALUES (?,now(),?) ");
		
		Object args[] = new Object[2];
		args[0] =userName;
		args[1] =ipAddress;
		getJdbcTemplate().update(sql.toString().toLowerCase(), args);
		log.debug("LoginHistory updated for userName="+userName);
 
	}
	
	public void changeLoginPassword(String userName,String newPassword){
		log.info("@Start of changeLoginPassword");
		log.debug("|__userName="+userName+
				"\t|__newPassword="+newPassword);
		
		StringBuilder sql=new StringBuilder();
		sql.append(" UPDATE people SET likes = ? WHERE name=? ");
		
		Object args[] = new Object[2];
		args[0] =newPassword;
		args[1] =userName;
		getJdbcTemplate().update(sql.toString().toLowerCase(), args);
		log.debug("loginPassword updated for userName="+userName);
	}


	public Integer confirmRegistration(Long userId) {
		log.debug("|__userId="+userId);
		StringBuilder sql=new StringBuilder();
		sql.append(" update USER set STATUS = ? where USER_ID= ? ");
		
		Object args[] = new Object[2];
		args[0] =DbConstants.Y_STATUS;
		args[1] =userId;
		return getJdbcTemplate().update(sql.toString().toLowerCase(), args);
	}


	public int getUserNameCount(String userName) {
		log.debug("|__userName="+userName);
		StringBuilder sql=new StringBuilder();
		sql.append(" select count(1) from USER where USER_NAME= ? ");
		
		Object args[] = new Object[1];
		args[0] =userName;
		return getJdbcTemplate().queryForInt(sql.toString().toLowerCase(), args);
	}

}
