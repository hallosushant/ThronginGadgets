package com.sushant.verma.login.action;


import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.sushant.verma.common.action.BaseAction;
import com.sushant.verma.common.constants.ZConstants;
import com.sushant.verma.common.dto.UserDto;
import com.sushant.verma.common.utility.UserDataPasser;
import com.sushant.verma.login.LoginConstants;
import com.sushant.verma.login.bll.LoginBllInterface;
@Validations
public class LoginAction extends BaseAction implements SessionAware{
	
	
	private static Logger log=LogManager.getLogger(LoginAction.class);
	private static final long serialVersionUID = -5473342218650521038L;
	private String userEmail;
	private String password;
	private String msg;
	private LoginBllInterface loginBllInterface;
	private Map<String, Object> session;
	private UserDto userDto;
	
	public String execute() throws Exception{
		log.info("Start LoginAction execute");
		boolean checkUserCredentials=false;
		Boolean showCaptcha=(Boolean) session.get(LoginConstants.SHOW_CAPTCHA);
		log.debug("|___UserName=="+getUserEmail()+" | showCaptcha="+showCaptcha);
		if(showCaptcha!=null){
			if(showCaptcha)
				checkUserCredentials=super.isCaptchaResponseValid(captchaResponse);
		}else{
			checkUserCredentials=true;
		}
		if(checkUserCredentials){
			log.debug("authenticateUser");
			userDto = loginBllInterface.authenticateUser(getUserEmail(), getPassword());

			if(userDto!=null)
			{
				//CommonUtility.getDTOasXML(userDto);
				Long userActive=userDto.getStatusId();

				if(userActive.longValue()==1){
					ServletActionContext.getRequest().getSession().setAttribute("loggedInUser", userEmail);
					session.put(ZConstants.LOGGEDIN_USER,userDto );
					UserDataPasser.setUserID(userDto.getUserId());
					UserDataPasser.setUserEmailID(userDto.getUserEmail());
					UserDataPasser.setUserRole(userDto.getUserRole());
					UserDataPasser.setUserName(userDto.getUserName());
					log.debug("loggedIn UserDto :"+session.get(ZConstants.LOGGEDIN_USER));
					addActionMessage("Welcome  "+getUserEmail());
					log.info("Login Successfull for "+getUserEmail());
					session.put(LoginConstants.SHOW_CAPTCHA, null);
					return SUCCESS;
				}
				else{
					log.debug("Login Error, '"+getUserEmail()+"' is Inactive!!!");
					addActionError("Login Error, '"+getUserEmail()+"' is Inactive!!!");
					return INPUT;
				}
			}
			else
			{
				log.debug("Login Error, Password or Email is wrong.");
				addActionError("Login Error, Password or Email is wrong.");
				session.put(LoginConstants.SHOW_CAPTCHA, true);
				return INPUT;
			}
		}
        else {
        	log.debug("Wrong Captcha Response");
        	addFieldError("captchaResponse", "Wrong Verification Code, Please try again.");
        	return INPUT;
        }
	}
	
	@RequiredStringValidator(fieldName="userEmail",trim=true,key="key.userEmail.mandatory",message="Email Id is mandatory",shortCircuit=true)
	@EmailValidator(fieldName="userEmail",key="key.userEmail.emailValidation",message="Please enter valid email",shortCircuit=true)
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@RequiredStringValidator(fieldName="password",trim=true,key="key.password.mandatory",message="Password is mandatory",shortCircuit=true)
	@StringLengthFieldValidator(fieldName="password",trim=true,minLength="8",maxLength="32",key="key.password.lengthValidation",message="Password must be 8 to 32 characters long",shortCircuit=true)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public LoginBllInterface getLoginBllInterface() {
		return loginBllInterface;
	}
	public void setLoginBllInterface(LoginBllInterface loginBllInterface) {
		this.loginBllInterface = loginBllInterface;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
