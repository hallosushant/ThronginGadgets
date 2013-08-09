package com.sushant.verma.login.action;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.sushant.verma.common.action.BaseAction;
import com.sushant.verma.common.constants.ZConstants;
import com.sushant.verma.common.constants.ZConstants.MsgType;
import com.sushant.verma.common.dto.UserDto;
import com.sushant.verma.common.email.EmailConstants;
import com.sushant.verma.common.utility.CommonUtility;
import com.sushant.verma.login.bll.LoginBllInterface;

public class FbLoginAction  extends BaseAction implements SessionAware, ServletResponseAware{


	private static final long serialVersionUID = 1L;
	private static Logger log=LogManager.getLogger(FbLoginAction.class);
	private LoginBllInterface loginBllInterface;
	private String userName;
	private String userEmail;
    private HttpServletResponse response;
    private Map<String, Object> session;


	public String execute() throws Exception{
		log.debug("|__userName="+userName+" | userEmail="+userEmail);
		UserDto userDto=new UserDto();
		userDto.setUserEmail(userEmail);
        userDto.setUserName(userName);
        //CommonUtility.getDTOasXML(user);
        userDto=loginBllInterface.authOpenUser(userDto);

        String desitinationURL = (String)session.get("originalURL");
        if(null==userDto){
        	msg="Unable to authenticate your login details,please try again or contact at "+EmailConstants.CONTACT_THRONGIN_EMAIL_ID;
        	msgType=MsgType.ERROR.getMsgType();
        	if (desitinationURL != null) {
        		if(desitinationURL.contains("home.html?"))
        			desitinationURL=desitinationURL.substring(0,desitinationURL.indexOf("?"));
        		String seperator=(desitinationURL.contains("?")?"&":"?");
        		desitinationURL+=seperator+"msg="+msg+"&msgType="+msgType;
        	}
        }
        log.info("User: " + CommonUtility.getDTOasXML(userDto));
        session.put(ZConstants.LOGGEDIN_USER,userDto );

        if (desitinationURL == null) {
            log.debug("No destination URL provided, will send to Home");
            return "home";
        }
        else {
        	if(desitinationURL.contains("home.html?"))
    			desitinationURL=desitinationURL.substring(0,desitinationURL.indexOf("?"));
            log.debug("Redirecting to : " + desitinationURL);
            response.sendRedirect(desitinationURL);
            return NONE;
        }
	}



	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public LoginBllInterface getLoginBllInterface() {
		return loginBllInterface;
	}

	public void setLoginBllInterface(LoginBllInterface loginBllInterface) {
		this.loginBllInterface = loginBllInterface;
	}

}
