package com.sushant.verma.login.dao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.sushant.verma.common.ZProc.ZProcConstants;
import com.sushant.verma.common.constants.DbConstants;
import com.sushant.verma.common.dto.UserDto;
import com.sushant.verma.common.mapper.UserMapper;
import com.sushant.verma.login.LoginConstants;
 

public class LoginSPDao extends JdbcDaoSupport{
 
	private static final Logger log=LogManager.getLogger(LoginSPDao.class);
  
	@SuppressWarnings("unchecked")
	public UserDto authOpenUser(UserDto userDto) {
		getJdbcTemplate().execute("SET session TRANSACTION ISOLATION LEVEL READ UNCOMMITTED");
		List<UserDto> users;
		int userEmailCount=getJdbcTemplate().queryForInt("SELECT count(USER_ID) FROM user where USER_EMAIL=? ",new Object[]{userDto.getUserEmail()});
		log.info("userEmailCount="+userEmailCount);
		if(userEmailCount==LoginConstants.ONE){
			log.info("user exists");
			StringBuilder sql=new StringBuilder();
			sql.append(" SELECT  user.USER_ID,user.USER_NAME, user.USER_EMAIL, user.HINT_QUESTION_ID, user.HINT_ANSWER, user.STATUS_ID,USER_ROLE, ");
			sql.append("  user.SALT ,user.USER_PWD ");
			sql.append(" FROM ");
			sql.append(" user_role_mst, user , user_role_mapping  ");
			sql.append(" WHERE  ");
			sql.append(" user.USER_ID = user_role_mapping.USER_ID and ");
			sql.append(" user_role_mst.USER_ROLE_ID = user_role_mapping.USER_ROLE_ID and ");
			sql.append(" user.USER_EMAIL=? and user.STATUS_ID=? ");
			ArrayList<Object> args=new ArrayList<Object>();
			args.add(userDto.getUserEmail());
			args.add(DbConstants.ACTIVE_STATUS_ID);
			log.debug("|__authOpenUser query="+sql);
			users=getJdbcTemplate().query(sql.toString(), args.toArray(),new UserMapper());
			log.debug("users="+users);
	    	if (users!=null && !users.isEmpty()) {
	    		return (UserDto)users.get(0);
	    	}
	    	return null;

		}else if(userEmailCount==LoginConstants.ZERO){
			log.info("user DOES NOT exists, creating new user");
			StringBuilder sql=new StringBuilder();
    		sql.append(" INSERT INTO user (user_name,user_email,created_by,status_id) ");
    		sql.append(" VALUES (?,?,?,?) ");
    		ArrayList<Object> args=new ArrayList<Object>();
    		args.add(userDto.getUserName());
    		args.add(userDto.getUserEmail());
    		args.add(userDto.getUserEmail());
    		args.add(DbConstants.ACTIVE_STATUS_ID);
    		int update=getJdbcTemplate().update(sql.toString(), args.toArray());
    		log.info("INSERT INTO user count ="+update);
    		
    		Long userId=getJdbcTemplate().queryForLong("select USER_ID from user where USER_EMAIL=? ",new Object[]{userDto.getUserEmail()});
    		log.info("userId generated="+userId);
    		
    		sql=new StringBuilder();
    		sql.append(" INSERT	INTO user_role_mapping(user_role_id,user_id,status_id) ");
    		sql.append(" VALUES (?,?,?) ");
    		args=new ArrayList<Object>();
    		args.add(LoginConstants.OPEN_USER_ROLE_ID);
    		args.add(userId);
    		args.add(DbConstants.ACTIVE_STATUS_ID);
    		update=getJdbcTemplate().update(sql.toString(), args.toArray());
    		log.info("INSERT INTO user_role_mapping count ="+update);
    		
    		sql=new StringBuilder();
			sql.append(" SELECT  user.USER_ID,user.USER_NAME, user.USER_EMAIL, user.HINT_QUESTION_ID, user.HINT_ANSWER, user.STATUS_ID,USER_ROLE, ");
			sql.append("  user.SALT ,user.USER_PWD ");
			sql.append(" FROM ");
			sql.append(" user_role_mst, user , user_role_mapping  ");
			sql.append(" WHERE  ");
			sql.append(" user.USER_ID = user_role_mapping.USER_ID and ");
			sql.append(" user_role_mst.USER_ROLE_ID = user_role_mapping.USER_ROLE_ID and ");
			sql.append(" user.USER_EMAIL=? and user.STATUS_ID=? ");
			args=new ArrayList<Object>();
			args.add(userDto.getUserEmail());
			args.add(DbConstants.ACTIVE_STATUS_ID);
			log.debug("|__authOpenUser query="+sql);
			users=getJdbcTemplate().query(sql.toString(), args.toArray(),new UserMapper());
			log.debug("users="+users);
	    	if (users!=null && !users.isEmpty()) {
	    		return (UserDto)users.get(0);
	    	}
	    	return null;

		}else{
			log.debug("users CAN NOT be created returning null");
			return null;
		}
		
    	/*try {
        	AuthOpenUserStoredProcedure proc = new AuthOpenUserStoredProcedure(getJdbcTemplate().getDataSource(), ZProcConstants.SP_AUTHENTICATE_OPEN_USER);
            Map results = proc.execute(userDto);
            List users = (List) results.get(ZProcConstants.RESULT_SET);
            if (users!=null && !users.isEmpty()) {
                return (UserDto)users.get(0);
            }
            return null;
            
        } catch (DataAccessException ex) {
            throw new BaseRuntimeException(ex);
        }*/
	}

    @SuppressWarnings("unchecked")
    public UserDto getUser(String userEmail,String userPwd) {
    	log.debug("|___userEmail=="+userEmail+
    			"\t|___userPwd=="+userPwd);

    	StringBuilder sql=new StringBuilder();
    	sql.append(" SELECT  user.USER_ID,user.USER_NAME, user.USER_EMAIL, user.HINT_QUESTION_ID, user.HINT_ANSWER, user.STATUS_ID,USER_ROLE, ");
    	sql.append("  user.SALT ,user.USER_PWD ");
    	sql.append(" FROM ");
    	sql.append(" user_role_mst, user , user_role_mapping  ");
    	sql.append(" WHERE  ");
    	sql.append(" user.USER_ID = user_role_mapping.USER_ID and ");
    	sql.append(" user_role_mst.USER_ROLE_ID = user_role_mapping.USER_ROLE_ID and ");
    	sql.append(" user.USER_EMAIL=? and user.STATUS_ID=? ");
    	ArrayList<Object> args=new ArrayList<Object>();
    	args.add(userEmail);
    	args.add(DbConstants.ACTIVE_STATUS_ID);
    	log.debug("|__getUser query="+sql);
    	List<UserDto> users=getJdbcTemplate().query(sql.toString(), args.toArray(),new UserMapper());
    	if (users!=null && !users.isEmpty()) {
    		return (UserDto)users.get(0);
    	}
    	return null;

    	/*		try {
        	LoginStoredProcedure proc = new LoginStoredProcedure(getJdbcTemplate().getDataSource(), ZProcConstants.SP_AUTHENTICATE_USER);
            Map results = proc.execute(userEmail,userPwd);
            List users = (List) results.get(ZProcConstants.RESULT_SET);
            if (users!=null && !users.isEmpty()) {
                return (UserDto)users.get(0);
            }
            return null;
        } catch (DataAccessException ex) {
            throw new BaseRuntimeException(ex);
        }*/
    }

	@SuppressWarnings("unchecked")
	public Map registerUser(UserDto userDto) {
		log.info("@registerUser ");
    	getJdbcTemplate().execute("SET session TRANSACTION ISOLATION LEVEL READ UNCOMMITTED");
    	Map results=new HashMap();
    	int userEmailCount=getJdbcTemplate().queryForInt("SELECT count(USER_ID) FROM user where USER_EMAIL=? ",new Object[]{userDto.getUserEmail()});
    	int userNameCount=getJdbcTemplate().queryForInt("SELECT count(USER_ID) FROM user where USER_NAME=? ",new Object[]{userDto.getUserName()});
    	log.info("userEmailCount="+userEmailCount+" | userNameCount="+userNameCount);
    	if(userEmailCount==0 && userNameCount==0){
    		log.info("User DOES NOT exists creating new user");
    		results.put(ZProcConstants.OUT_USER_EXISTS, Integer.valueOf(LoginConstants.ZERO));
    		
    		StringBuilder sql=new StringBuilder();
    		sql.append(" INSERT INTO user (user_name,user_email,salt,user_pwd,hint_question_id,hint_answer,created_by) ");
    		sql.append(" VALUES (?,?,?,?,?,?,?) ");
    		ArrayList<Object> args=new ArrayList<Object>();
    		args.add(userDto.getUserName());
    		args.add(userDto.getUserEmail());
    		args.add(userDto.getSalt());
    		args.add(userDto.getEncPwd());
    		args.add(userDto.getHintQuestionId());
    		args.add(userDto.getHintAnswer());
    		args.add(userDto.getUserEmail());
    		int update=getJdbcTemplate().update(sql.toString(), args.toArray());
    		log.info("INSERT INTO user count ="+update);
    		
    		Long userId=getJdbcTemplate().queryForLong("select USER_ID from user where USER_EMAIL=? ",new Object[]{userDto.getUserEmail()});
    		log.info("userId generated="+userId);
    		results.put(ZProcConstants.OUT_USER_ID, userId);
    		
    		sql=new StringBuilder();
    		sql.append(" INSERT	INTO user_role_mapping(user_role_id,user_id,status_id) ");
    		sql.append(" VALUES (?,?,?) ");
    		args=new ArrayList<Object>();
    		args.add(LoginConstants.REGISTERED_USER_ROLE_ID);
    		args.add(userId);
    		args.add(DbConstants.ACTIVE_STATUS_ID);
    		update=getJdbcTemplate().update(sql.toString(), args.toArray());
    		log.info("INSERT INTO user_role_mapping count ="+update);
    		
    	}else{
    		log.info("User EXISTS, NOT creating user");
    		results.put(ZProcConstants.OUT_USER_EXISTS,Long.valueOf(LoginConstants.ONE) );
    		results.put(ZProcConstants.OUT_USER_ID, Long.valueOf(LoginConstants.ZERO));
    	}
    	
    	return results;
    	 
		/*try {
			RegisterUserZProc proc = new RegisterUserZProc(getJdbcTemplate().getDataSource(), ZProcConstants.SP_REGISTER_USER);
            Map results = proc.execute(userDto);
            log.debug("results=="+results);
            return results;
        } catch (DataAccessException ex) {
            throw new BaseRuntimeException(ex);
        }*/
	}

	@SuppressWarnings("unchecked")
	public Map confirmUserRegistration(Long userId) {
		
		log.info("@confirmUserRegistration | userId="+userId);
    	getJdbcTemplate().execute("SET session TRANSACTION ISOLATION LEVEL READ UNCOMMITTED");
    	Map results=new HashMap();
    	List<ListOrderedMap> confirmUserRegistrationList=getJdbcTemplate().queryForList("SELECT USER_NAME,USER_EMAIL,STATUS_ID FROM user where USER_ID=? ",new Object[]{userId});
    	log.info("confirmUserRegistrationList="+confirmUserRegistrationList);    	
    	
    	if(Long.valueOf(confirmUserRegistrationList.get(0).get(DbConstants.STATUS_ID).toString())==DbConstants.INACTIVE_STATUS_ID.longValue()){
    		log.info("User exists and is INACTIVE, Activiting userId="+userId);
    		String sql=" UPDATE	user SET STATUS_ID=? where USER_ID=? ";
    		ArrayList<Object> args=new ArrayList<Object>();
    		args.add(DbConstants.ACTIVE_STATUS_ID);
    		args.add(userId);
    		int update=getJdbcTemplate().update(sql.toString(), args.toArray());
    		log.info("UPDATE user count ="+update);
    		results.put(ZProcConstants.OUT_IS_REGISTRATION_SUCCESSFUL, DbConstants.Y_STATUS);
    	}
    	confirmUserRegistrationList=getJdbcTemplate().queryForList("SELECT USER_NAME,USER_EMAIL,STATUS_ID FROM user where USER_ID=? ",new Object[]{userId});
    	log.info("confirmUserRegistrationList="+confirmUserRegistrationList);
    	results.put(ZProcConstants.OUT_USER_NAME, confirmUserRegistrationList.get(0).get(DbConstants.USER_NAME));
    	results.put(ZProcConstants.OUT_USER_EMAIL, confirmUserRegistrationList.get(0).get(DbConstants.USER_EMAIL));
    	results.put(ZProcConstants.OUT_STATUS_ID, confirmUserRegistrationList.get(0).get(DbConstants.STATUS_ID));
    	
    	return results;
    	
		/*try {
			ConfirmUserRegistrationZProc proc = new ConfirmUserRegistrationZProc(getJdbcTemplate().getDataSource(), ZProcConstants.SP_CONFIRM_USER_REGISTRATION);
            Map results = proc.execute(userId);
            log.debug("results=="+results);
            return results;
        } catch (DataAccessException ex) {
            throw new BaseRuntimeException(ex);
        }*/
	}
}