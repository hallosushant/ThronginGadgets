package com.sushant.verma.common.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sushant.verma.common.dto.UserDto;
import com.sushant.verma.common.enums.TableEnums;

public class UserMapper implements RowMapper {
	  public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	      UserDto user = new UserDto();
	      user.setUserId(rs.getLong(TableEnums.UserEnum.USER_ID.toString()));
	      user.setUserName(rs.getString(TableEnums.UserEnum.USER_NAME.toString()));
	      user.setUserEmail(rs.getString(TableEnums.UserEnum.USER_EMAIL.toString()));
	      user.setHintQuestionId(rs.getLong(TableEnums.UserEnum.HINT_QUESTION_ID.toString()));
	      user.setHintAnswer(rs.getString(TableEnums.UserEnum.HINT_ANSWER.toString()));
	      user.setStatusId(rs.getLong(TableEnums.UserEnum.STATUS_ID.toString()));
	      user.setUserRole(rs.getString(TableEnums.UserRoleMstEnum.USER_ROLE.toString()));
	      user.setSalt(rs.getBytes(TableEnums.UserEnum.SALT.toString()));
	      user.setEncPwd(rs.getBytes(TableEnums.UserEnum.USER_PWD.toString()));
	      return user;
	  }
	}
