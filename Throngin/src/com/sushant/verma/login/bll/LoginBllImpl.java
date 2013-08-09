package com.sushant.verma.login.bll;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.validator.UrlValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Action;
import com.sushant.verma.common.ZProc.ZProcConstants;
import com.sushant.verma.common.constants.ZConstants.MsgType;
import com.sushant.verma.common.dto.UserDto;
import com.sushant.verma.common.email.EmailConstants;
import com.sushant.verma.common.email.EmailServiceInterface;
import com.sushant.verma.common.exception.BaseRuntimeException;
import com.sushant.verma.common.utility.CommonUtility;
import com.sushant.verma.common.utility.PasswordEncryptionService;
import com.sushant.verma.common.utility.StringUtility;
import com.sushant.verma.common.utility.ZEncrypt;
import com.sushant.verma.login.LoginConstants;
import com.sushant.verma.login.dao.LoginDao;
import com.sushant.verma.login.dao.LoginSPDao;

public class LoginBllImpl implements LoginBllInterface {
	
	private static Logger log=LogManager.getLogger(LoginBllImpl.class);

	private LoginDao loginDao;
	private LoginSPDao loginSPDao;
	private UserDto userDto;
	private EmailServiceInterface emailService;
	public UserDto authenticateUser(String userEmail, String password) throws Exception{
		log.debug("|___userEmail=="+userEmail+
				"\t|___password=="+password);

		if(StringUtility.areNotNullBlank(userEmail,password)){
			userDto=loginSPDao.getUser(userEmail, password);
			if(userDto!=null && LoginConstants.PWD_USER_ROLES.contains(userDto.getUserRole())){
				boolean isUserAuthentic=PasswordEncryptionService.authenticate(password, userDto.getEncPwd(), userDto.getSalt());
				log.debug("userDto=="+userDto+" | isUserAuthentic="+isUserAuthentic);
				if(isUserAuthentic)
					return userDto;
				else
					return null;
			}
		}
		return null;
	}
	
	public UserDto authOpenUser(UserDto userDto) {
		
		return loginSPDao.authOpenUser(userDto);
	}


	
	@SuppressWarnings("unchecked")
	public Integer registerUser(UserDto userDto) throws Exception {
		byte[] salt=PasswordEncryptionService.generateSalt();
		byte[] encPwd=PasswordEncryptionService.getEncryptedPassword(userDto.getUserPwd(),salt);
		userDto.setSalt(salt);
		userDto.setEncPwd(encPwd);
		Map registrationMap=loginSPDao.registerUser(userDto);
		Integer userExists=(Integer) registrationMap.get(ZProcConstants.OUT_USER_EXISTS);
		Long userId=(Long) registrationMap.get(ZProcConstants.OUT_USER_ID);
		
		if(userExists==0 && userId!=0){
			log.debug("user does not exists, creating registration confirmation email");
			UrlValidator urlValidator=new UrlValidator();
			String url;
			
			do{
				url=getRegistrationUrl(userId);
			}while(!urlValidator.isValid(url));
			log.debug("Valid URL obtained after URL Validation, url=="+url);
//			String url=getRegistrationUrl(userId);			
			String fromEmail=EmailConstants.ADMIN_THRONGIN_EMAIL_ID;
			String[] toEmail=new String[1];
			toEmail[0]=userDto.getUserEmail();
			String emailBody=EmailConstants.getConfirmRegistrationHtmlEmailContent(url);
			String emailSubject=EmailConstants.SUBJECT_HEADER_THRONGIN+EmailConstants.SUBJECT_CONFIRM_REGISTRATION;

			HashMap<String,Object> emailParam=new HashMap<String,Object>();
			emailParam.put("FROM_EMAIL", fromEmail);
			emailParam.put("TO_EMAIL", toEmail);
			emailParam.put("SUBJECT_EMAIL", emailSubject);
			emailParam.put("BODY_EMAIL", emailBody);
			try {
				log.debug("calling sendEmail");
				emailService.sendEmail(emailParam);
			} catch (Exception e) {
				throw new BaseRuntimeException(e);
			}
		}
		return userExists;
	}
	
	@SuppressWarnings("unchecked")
	public Map confirmUserRegistration(String authId) throws Exception {

		authId=authId.replaceAll(" ", "+");
		String responseDecrypted=ZEncrypt.decryptAES(authId);
		String[] splits=responseDecrypted.split("&");
		Long userId=Long.valueOf(splits[0]);
		String confirmKeyword=splits[1];
		Map returnMap=new HashMap<String,String>();
		if(LoginConstants.CONFIRM_REGISTRATION_KEYWORD.contains(confirmKeyword)){

			Map registrationConfirmationMap=loginSPDao.confirmUserRegistration(userId);
			log.debug("|__ registrationConfirmationMap=="+registrationConfirmationMap);
			if(registrationConfirmationMap!=null && !registrationConfirmationMap.isEmpty()){
				String userName=(String) registrationConfirmationMap.get(ZProcConstants.OUT_USER_NAME);
				String userEmail=(String) registrationConfirmationMap.get(ZProcConstants.OUT_USER_EMAIL);
				Long statusId=(Long) registrationConfirmationMap.get(ZProcConstants.OUT_STATUS_ID);
				String isRegistrationSuccessful=(String) registrationConfirmationMap.get(ZProcConstants.OUT_IS_REGISTRATION_SUCCESSFUL);

				if(statusId!=null && statusId.longValue()==LoginConstants.ACTIVE_STATUS_ID.longValue() && 
						StringUtility.isNotNullBlank(isRegistrationSuccessful) && isRegistrationSuccessful.equals(LoginConstants.Y)){
					returnMap.put(LoginConstants.MESSAGE, "Registration confirmed for "+userName+"("+userEmail+")");
					returnMap.put(LoginConstants.MESSAGE_TYPE, MsgType.SUCCESS.getMsgType());
					returnMap.put(LoginConstants.STRUTS_RESULT_NAME, Action.SUCCESS);
					log.info("Registration confirmed for "+userName+"("+userEmail+")");
					return returnMap;
				}
				else if(statusId!=null && statusId.longValue()==LoginConstants.ACTIVE_STATUS_ID.longValue()){
					returnMap.put(LoginConstants.MESSAGE,"Registration already confirmed for "+userName+"("+userEmail+")");
					returnMap.put(LoginConstants.MESSAGE_TYPE, MsgType.INFO.getMsgType());
					log.debug("Registration already confirmed for "+userName+"("+userEmail+")");
					returnMap.put(LoginConstants.STRUTS_RESULT_NAME, Action.INPUT);
					return returnMap;
				}
				else{
					returnMap.put(LoginConstants.MESSAGE,"Registration not confirmed, Please try again or contact at "+EmailConstants.SUPPORT_THRONGIN_EMAIL_ID);
					returnMap.put(LoginConstants.MESSAGE_TYPE, MsgType.WARNING.getMsgType());
					log.debug("Registration not confirmed for "+userName+"("+userEmail+")");
					returnMap.put(LoginConstants.STRUTS_RESULT_NAME, Action.INPUT);
					return returnMap;
				}
			}
			else{
				returnMap.put(LoginConstants.MESSAGE,"Registration not confirmed, Please try again or contact at "+EmailConstants.SUPPORT_THRONGIN_EMAIL_ID);
				returnMap.put(LoginConstants.MESSAGE_TYPE, MsgType.ERROR.getMsgType());
				log.debug("Registration not confirmed");
				returnMap.put(LoginConstants.STRUTS_RESULT_NAME,  Action.INPUT);
				return returnMap;
			}

		}
		else{
			returnMap.put(LoginConstants.MESSAGE,"Invalid Activation Link. To confirm registration, Please click on the url that has been sent to your e-mail id.");
			log.debug("Invalid Activation Link. To confirm registration, Please click on the url that has been sent to your e-mail id.");
			returnMap.put(LoginConstants.STRUTS_RESULT_NAME, Action.INPUT);
			return returnMap;	
		}
			
	}

	
	
	
	@SuppressWarnings("unchecked")
	public String getRegistrationUrl(Long userId) throws Exception{

		log.debug("|__ userId="+userId); 
		int keyWordSize=Integer.valueOf(CommonUtility.getCacheDetail("CONFIRM_REGISTRATION_KEYWORD_SIZE",LoginConstants.CONFIRM_REGISTRATION_KEYWORD_SIZE).toString());
		int randomIndex=CommonUtility.getRandomNumberInRange(1,keyWordSize-1);
		log.debug("|__ randomIndex="+randomIndex);
		List<String> confirmRegistrationKeyword=(List<String>) CommonUtility.getCacheDetail("CONFIRM_REGISTRATION_KEYWORD",LoginConstants.CONFIRM_REGISTRATION_KEYWORD);
		String confirmKeyword=confirmRegistrationKeyword.get(randomIndex);
		log.debug("|__ confirmKeyword="+confirmKeyword);
		String encWord=ZEncrypt.encryptAES(userId+"&"+confirmKeyword);
		log.debug("|__ encWord="+encWord);
		String decWord=ZEncrypt.decryptAES(encWord);
		log.debug("|__ decWord=="+decWord);
		String url=LoginConstants.REGISTRATION_URL_LINK+encWord;
		log.debug("|__ url="+url);
		return url;
	}
	
	public boolean isUserNameAvailable(String userName) {
		int userNameCount=loginDao.getUserNameCount(userName);
		log.debug("|__userNameCount="+userNameCount);
		if(userNameCount>0)
			return false;
		else
			return true;
	}

	public LoginDao getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	public LoginSPDao getLoginSPDao() {
		return loginSPDao;
	}

	public void setLoginSPDao(LoginSPDao loginSPDao) {
		this.loginSPDao = loginSPDao;
	}

	public EmailServiceInterface getEmailService() {
		return emailService;
	}

	public void setEmailService(EmailServiceInterface emailService) {
		this.emailService = emailService;
	}

}
