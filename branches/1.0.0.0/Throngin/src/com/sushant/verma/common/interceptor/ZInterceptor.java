package com.sushant.verma.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.sushant.verma.common.constants.ZConstants;
import com.sushant.verma.common.dto.UserDto;

public class ZInterceptor implements Interceptor,StrutsStatics{

	private static final long serialVersionUID = 8193040070521930422L;
	private static Logger log = LogManager.getLogger(ZInterceptor.class);
	private static Logger monitorlog = LogManager.getLogger("monitorlog");

	public String intercept(ActionInvocation invocation) throws Exception {
		log.info(" ! Inside ZInterceptor-Interceptor");
		String className = invocation.getAction().getClass().getName();
		long startTime = System.currentTimeMillis();
		// get references to the App Context, Session, and Request objects
		final ActionContext context = invocation.getInvocationContext ();
		HttpServletRequest request = (HttpServletRequest) context.get(HTTP_REQUEST);
		HttpSession session =  request.getSession (true);
		// Is there a "user" object stored in the user's HttpSession?
		UserDto user = (UserDto)session.getAttribute(ZConstants.LOGGEDIN_USER);
		if (user == null) {
			// The user has not logged in yet.
			log.debug("User NOT found in the Session");
			// Is the user attempting to log in right now?
			String loginIdentifier = request.getParameter("openid_identifier");
			String openIdEndpoint =  request.getParameter("openid.op_endpoint");
			// we can know the user is trying to login right now if the "openid_identifier" is a Request parm
			if (! StringUtils.isBlank (loginIdentifier) ) {
				// The user is attempting to log in.
				log.debug("The user is attempting to log in with openID");
				// Process the user's login attempt.
				return invocation.invoke ();
			}
			// we know the user has just auth'd with the OpenID provider if the "openid.op_endpoint" is a Request parm
			else if(! StringUtils.isBlank (openIdEndpoint) ) {
				// The user has logged in with an OpenId provider
				log.debug("The user has logged in with an OpenId provider");
				// Process the user's login attempt.
				return invocation.invoke ();
			}
			else {
				log.debug("The User is not going to login, forwading to the requested url");
				log.debug("Forwarding to the invoked pt");
				invocation.invoke();
			}
		}else{
			// user is already logged in
			log.debug("User " + user.getUserName().toString() + " found in the Session!");
			invocation.invoke();
		}
		String reqURL=saveReceivingURL(request, session);
		String remoteAddr = (String) request.getRemoteAddr();
		long endTime = System.currentTimeMillis();
		monitorlog.info(className+" | "+reqURL+" | remoteAddr="+remoteAddr+" | Time taken: " + (endTime - startTime) + " ms");
//		return invocation.invoke();
		return Action.NONE;
	}

	private String saveReceivingURL(HttpServletRequest request, HttpSession session) {
        log.debug("Entering saveReceivingURL()");
        
        // extract the receiving URL from the HTTP request
        final StringBuffer receivingURL = request.getRequestURL();
        final String queryString = request.getQueryString();

        // if there is a query string then we'll need that too
        if (queryString != null && queryString.length() > 0) {
            receivingURL.append("?").append(queryString);
        }
        
        log.debug("Original URL: " + receivingURL.toString());
        
        // save the original URL in the Session
        // we're going to need to redirect the user back to this URL after login is completed
        session.setAttribute("originalURL", receivingURL.toString());
        return receivingURL.toString();
    }
	
	public void destroy() {
		log.info(" ! Destroying ZInterceptor");
	}

	public void init() {
		log.info(" ! Initialiazing ZInterceptor");		
	}
	
}
