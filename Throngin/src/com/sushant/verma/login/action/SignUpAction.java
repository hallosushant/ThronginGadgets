package com.sushant.verma.login.action;

import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.sushant.verma.common.action.BaseAction;
import com.sushant.verma.common.dto.UserDto;
import com.sushant.verma.common.email.EmailConstants;
import com.sushant.verma.login.LoginConstants;
import com.sushant.verma.login.bll.LoginBllInterface;

public class SignUpAction extends BaseAction implements  StrutsStatics,SessionAware{
	
	private static final long serialVersionUID = 5279577385446237934L;
	private static Logger log=LogManager.getLogger(SignUpAction.class);
	private Long userId;
	private String userName;
	private String userEmail;
	private String password;
	private String confirmPassword;
	private Integer selectedHintQuestion;
	private String hintAnswer;
	private String captchaResponse;
	private LoginBllInterface loginBllInterface;
	private Map<String, Object> session;
	private String authId;
	
	public String execute() throws Exception{
		if(isCaptchaResponseValid(captchaResponse)){
			log.debug("Valid Captcha Response");
			UserDto userDto = new UserDto();
			userDto.setUserName(userName);
			userDto.setUserEmail(userEmail);
			userDto.setUserPwd(password);
			userDto.setHintQuestionId(selectedHintQuestion.longValue());
			userDto.setHintAnswer(hintAnswer);
			userDto.setStatusId(LoginConstants.INACTIVE_STATUS_ID);
			Integer userExists = loginBllInterface.registerUser(userDto);
			if(userExists==0){
				setMsg("Registration successful for  "+userEmail + ", a confirmation email has been sent to the email address you have provided. Follow the instructions provided in the email to activate your account. <br/> " +
						"If you have not received the email within a few hours, and you've made sure it's not in your Junk, Spam or trash folders, please contact "+EmailConstants.CONTACT_THRONGIN_EMAIL_ID);
				log.info("Registration Successfull for "+userEmail);
				return SUCCESS;
			}
			else{
				log.debug("Registration Error, '"+userEmail+"' already Exists, If you have forgot your login details please Click on 'Forgot Password'!!!");
				addActionError("Registration Error, '"+userEmail+"' already Exists, If you have forgot your login details please Click on 'Forgot Password'!!!");
				return INPUT;
			}
		}
        else {
        	setMsg("wrongVerificationCode");
        	return INPUT;
        }
	}

	
	@SuppressWarnings("unchecked")
	@SkipValidation
	public String confirmRegistration() throws Exception{
		log.debug("|__authId=="+authId);
		Map registrationConfirmationMap=loginBllInterface.confirmUserRegistration(authId);
		log.debug("|__ registrationConfirmationMap=="+registrationConfirmationMap);
		msg=(String) registrationConfirmationMap.get(LoginConstants.MESSAGE);
		msgType=(String) registrationConfirmationMap.get(LoginConstants.MESSAGE_TYPE);
		String returnValue=(String) registrationConfirmationMap.get(LoginConstants.STRUTS_RESULT_NAME);
/*		if(Action.SUCCESS.equalsIgnoreCase(returnValue))
			msgType=MsgType.SUCCESS.getMsgType();
		else if(Action.INPUT.equalsIgnoreCase(returnValue))
			msgType=MsgType.ERROR.getMsgType();
*/		return returnValue;

	}
	
	
	
	
	
	
	/*
	 * Getters and Setters
	 */
	
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
	
	@RequiredStringValidator(fieldName="confirmPassword",trim=true,key="key.confirmPassword.mandatory",message="Confirm Password is mandatory",shortCircuit=true)
	@FieldExpressionValidator(fieldName="confirmPassword", expression = "confirmPassword.equals(password)",key="key.confirmPassword.matchPassword", message ="Passwords do not match",shortCircuit=true) 
	@StringLengthFieldValidator(fieldName="password",trim=true,minLength="8",maxLength="32",key="key.password.lengthValidation",message="Password must be 8 to 32 characters long",shortCircuit=true)
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	@IntRangeFieldValidator(fieldName="selectedHintQuestion",min="1",max="13",key="key.selectedHintQuestion.mandatory",message="Security Question is mandatory",shortCircuit=true)
	public int getSelectedHintQuestion() {
		return selectedHintQuestion;
	}
	public void setSelectedHintQuestion(int selectedHintQuestion) {
		this.selectedHintQuestion = selectedHintQuestion;
	}
	@RequiredStringValidator(fieldName="hintAnswer",trim=true,key="key.hintAnswer.mandatory",message="Hint Answer is mandatory",shortCircuit=true)
	@StringLengthFieldValidator(fieldName="hintAnswer",trim=true,minLength="1",maxLength="100",key="key.hintAnswer.lengthValidation",message="Hint Answer must be 1 to 100 characters long",shortCircuit=true)
	public String getHintAnswer() {
		return hintAnswer;
	}
	public void setHintAnswer(String hintAnswer) {
		this.hintAnswer = hintAnswer;
	}

	@RequiredStringValidator(fieldName="captchaResponse",trim=true,key="key.captchaResponse.mandatory",message="Verification Code is mandatory",shortCircuit=true)
	public String getCaptchaResponse() {
		log.debug("captchaResponse="+captchaResponse);
		return captchaResponse;
	}

	public void setCaptchaResponse(String captchaResponse) {
		this.captchaResponse = captchaResponse;
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

	public void setSelectedHintQuestion(Integer selectedHintQuestion) {
		this.selectedHintQuestion = selectedHintQuestion;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	
}
