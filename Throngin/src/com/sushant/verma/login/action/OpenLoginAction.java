package com.sushant.verma.login.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.sushant.verma.common.action.BaseAction;
import com.sushant.verma.common.constants.ZConstants;
import com.sushant.verma.common.constants.ZConstants.MsgType;
import com.sushant.verma.common.dto.UserDto;
import com.sushant.verma.common.email.EmailConstants;
import com.sushant.verma.login.bll.LoginBllInterface;

public class OpenLoginAction  extends BaseAction implements SessionAware,ApplicationAware,ServletRequestAware, ServletResponseAware{


	private static final long serialVersionUID = 1L;
	private static Logger log=LogManager.getLogger(OpenLoginAction.class);
	private LoginBllInterface loginBllInterface;
	private UserDto userDto;
	// this is the only form field we will be looking for from OpenID Selector on the front end
    private String openid_identifier;
    
    // we'll need access to the Servlet spec objects, rather than just their attribute or parm maps
    private HttpServletRequest request;
    private HttpServletResponse response;
    
    // we'll be storing the User object in the Session
    private Map<String, Object> session;
    
    // the OpenIdAuthenticator class needs access to the application to store a OpenId4Java related object
    private Map<String, Object> application;
    
    // we'll need to send this to the OpenId provider so it knows where to send its response 
    private final String returnAction = "/openLogin!authenticateOpenId.html";

    // the OpenID Selector form will submit to this Action method
    public String execute() throws Exception {

        log.debug("Entering validateOpenId()");
        
        // get rid of trailing slash
        if (getOpenid_identifier().endsWith("/")) {
            setOpenid_identifier(getOpenid_identifier().substring(0, getOpenid_identifier().length() - 1));
        }

        log.debug("The requested OpenId identifier is: " + getOpenid_identifier());

        // determine a return_to URL where the application will receive
        // the authentication responses from the OpenID provider
        String returnUrl = getServerContext(request) + returnAction;
        
        // construct the destination Url to send to the Open Id provider
        String destinationUrl = OpenIdAuthenticator.getValidateOpenIdUrl(returnUrl, this.getOpenid_identifier(), session, application); 
        log.debug("destinationUrl="+destinationUrl);
        // redirect to the Auth Request
        response.sendRedirect(destinationUrl);
        
        // no need to return a view
        return NONE;
    }
    
    @SuppressWarnings("unchecked")
	public String authenticateOpenId() throws Exception {
        log.debug("Entering authenticateOpenId()");

        Map<String,String[]> parmList = request.getParameterMap();

        // extract the receiving URL from the HTTP request
        final StringBuffer receivingURL = request.getRequestURL();
        final String queryString = request.getQueryString();

        if (queryString != null && queryString.length() > 0) {
            receivingURL.append("?").append(request.getQueryString());
        }
        
        log.debug(receivingURL.toString().replaceAll("&", "\n"));

        // verify the user has authenticated with the Open Id provider and 
        // get a reference to the authenticated User
        userDto = OpenIdAuthenticator.getAuthenticatedUser(parmList, receivingURL, session, application);

        // save the user to the DB
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
        // add the user to the HTTP Session
        session.put(ZConstants.LOGGEDIN_USER,userDto );
        
        // retrieve the original URL from the Session
       

        // was a destination URL provided?
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


   /* private String getServerContext(final HttpServletRequest request) {
        // Get the base url.
        final StringBuilder serverPath = new StringBuilder();
        
        serverPath.append(request.getScheme() + "://");
        serverPath.append(request.getServerName());

        if (request.getServerPort() != 80) {
            serverPath.append(":" + request.getServerPort());
        }
        serverPath.append(request.getContextPath());
        
        return serverPath.toString();
    }
    
*/
    public String getOpenid_identifier() {
        return openid_identifier;
    }

    public void setOpenid_identifier(String openid_identifier) {
        this.openid_identifier = openid_identifier;
    }

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public Map<String, Object> getApplication() {
		return application;
	}

	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}

	public String getReturnAction() {
		return returnAction;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		
	}

	public LoginBllInterface getLoginBllInterface() {
		return loginBllInterface;
	}

	public void setLoginBllInterface(LoginBllInterface loginBllInterface) {
		this.loginBllInterface = loginBllInterface;
	}

}
