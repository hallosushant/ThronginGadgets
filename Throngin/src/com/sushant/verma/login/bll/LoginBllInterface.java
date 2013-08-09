package com.sushant.verma.login.bll;

import java.util.Map;

import com.sushant.verma.common.dto.UserDto;


public interface LoginBllInterface {
	
	public UserDto authenticateUser(String userEmail,String password) throws Exception ;

	public Integer registerUser(UserDto userDto) throws Exception;

	@SuppressWarnings("unchecked")
	public Map confirmUserRegistration(String authId) throws Exception;

	public boolean isUserNameAvailable(String userName);

	public UserDto authOpenUser(UserDto userDto);
}
