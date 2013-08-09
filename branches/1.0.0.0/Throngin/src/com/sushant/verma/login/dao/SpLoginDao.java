package com.sushant.verma.login.dao;

import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.sushant.verma.common.dbConnect.JdbcCallDataSource;
import com.sushant.verma.common.dto.UserDto;

public class SpLoginDao extends JdbcCallDataSource{

	private static final long serialVersionUID = 1464567574634L;
	private static Logger log = LogManager.getLogger(SpLoginDao.class);
	
	public UserDto readUser(String userEmail) {
		simpleJdbcCallDataSource.withProcedureName("sp_authenticateUserPwd");
		SqlParameterSource in = new MapSqlParameterSource().addValue("userName", userEmail);
		Map<String,Object> out = simpleJdbcCallDataSource.execute(in);
		log.debug("|__out=="+out);
		UserDto userDto = new UserDto();
		userDto.setUserEmail(userEmail);
		userDto.setUserPwd((String) out.get("password"));
//		userDto.setUserExists((String) out.get("userexists"));
		return userDto;
	}

/*	public UserDto readUser(String userEmail,String password) {
		simpleJdbcCallDataSource.withProcedureName("sp_authenticateUser");
		
		Map<String,Object> args=new HashMap<String,Object>();
		args.put("in_userName", userEmail);
		args.put("in_password", password);
		Map<String,Object> out = simpleJdbcCallDataSource.execute(args);
		log.debug("|__out=="+out);
		
		UserDto userDto = new UserDto();
		userDto.setUserExists((String) out.get("out_userexists"));
		String userValid=(String) out.get("out_isuservalid");
		if(userValid.equals("1"))
			userDto.setUserValid(true);
		else
			userDto.setUserValid(false);

		return userDto;
	}*/

/*	public UserDto authenticateUser(String userEmail,String password) {
		simpleJdbcCallDataSource.withProcedureName("sp_authenticateUser");
		
		Map<String,Object> args=new HashMap<String,Object>();
		args.put("in_userEmail", userEmail);
		args.put("in_password", password);
		Map<String,Object> out = simpleJdbcCallDataSource.execute(args);
		log.debug("|__out=="+out);
		
		UserDto userDto = new UserDto();
		userDto.setUserExists((String) out.get("out_userexists"));

		String isUserActive=(String) out.get("out_useractive");
		if(StringUtility.isNotNullBlank(isUserActive) && isUserActive.equals("1")){
			userDto.setUserActive(true);

			String userValid=(String) out.get("out_isuservalid");
			if(userValid.equals("1"))
				userDto.setUserValid(true);
			else
				userDto.setUserValid(false);
		}
		else
			userDto.setUserActive(false);


		return userDto;
	}*/

}
