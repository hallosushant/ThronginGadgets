package com.sushant.verma.login.action;

import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.sushant.verma.common.action.BaseAction;
import com.sushant.verma.common.constants.ZConstants;
import com.sushant.verma.common.dto.UserDto;
import com.sushant.verma.common.utility.StringUtility;
import com.sushant.verma.login.bll.LoginBllInterface;
@SuppressWarnings("unchecked")
public class AjaxLoginAction extends BaseAction implements SessionAware{
	
	private static final long serialVersionUID = 5279577385446237934L;
	private static Logger log=LogManager.getLogger(AjaxLoginAction.class);
	private LoginBllInterface loginBllInterface;
	private String userName;
	private String isUserNameAvailable;
	private Map session;
	private UserDto userDto;
	
	public String execute() throws Exception{
		return SUCCESS;
	}
	public String checkUserNameAvailability(){
		log.debug("|__userName="+userName);
		if(StringUtility.isNullBlank(userName))
			return NONE;
		if(loginBllInterface.isUserNameAvailable(userName))
			isUserNameAvailable="OK";			
		else
			isUserNameAvailable="\""+userName+"\" has been taken! please try again.";

		return SUCCESS;
	}
		
	public String fetchLoggedInUser(){
		log.info("Inside getLoggedInUser()");
		userDto=(UserDto) session.get(ZConstants.LOGGEDIN_USER);
		//CommonUtility.getDTOasXML(userDto);
		return SUCCESS;
	}
	/*
	 * Getters & Setters
	 * 
	 */

	public LoginBllInterface getLoginBllInterface() {
		return loginBllInterface;
	}
	public void setLoginBllInterface(LoginBllInterface loginBllInterface) {
		this.loginBllInterface = loginBllInterface;
	}


	public String getIsUserNameAvailable() {
		return isUserNameAvailable;
	}
	public void setIsUserNameAvailable(String isUserNameAvailable) {
		this.isUserNameAvailable = isUserNameAvailable;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setSession(Map session) {
		this.session = session;
	}
	public UserDto getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
	
}
