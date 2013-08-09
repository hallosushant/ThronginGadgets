package com.sushant.verma.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sushant.verma.common.utility.StringUtility;

public class LoginInterceptor extends AbstractInterceptor implements  StrutsStatics {

	/**
	 * 
	 */
	private static final long serialVersionUID = -871481538651230543L;
	private static final Logger log = LogManager.getLogger(LoginInterceptor.class);
	private static final String USER_HANDLE = "loggedInUser";
	private static final String LOGIN_ATTEMPT = "loginAttempt";


	public void init() {
		log.info("! Intializing LoginInterceptor");
	}

	public void destroy() {
		log.info("! Destroying LoginInterceptor");
	}

	public String intercept(ActionInvocation invocation) throws Exception {

		final ActionContext context = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) context.get(HTTP_REQUEST);
		HttpSession session = request.getSession(true);
		log.debug("|__request="+request+"\t|__session="+session);
		// Is there a "user" object stored in the user's HttpSession?
		Object user = session.getAttribute(USER_HANDLE);
		log.debug("|__user object="+user+
				"\n|__sessionId="+session.getId());
		
		Long interval = System.currentTimeMillis() - session.getLastAccessedTime();
		log.debug("Inactive interval for session id "+session.getId()+" __ "+interval);
		if (user != null && interval > 300000)
		{
			session.invalidate();
			return "userInactive";
		}

		if (user == null) {
			// The user has not logged in yet.

			// Is the user attempting to log in right now?
			String loginAttempt = request.getParameter(LOGIN_ATTEMPT);
			log.debug("|__loginAttempt="+loginAttempt);
			/* The user is attempting to log in. */
			if (StringUtility.isNotNullBlank(loginAttempt)) {
				log.info(" ! Inside If returning invocation.invoke()");
				return invocation.invoke();
			}
			else{
			addActionError(invocation, "You must be authenticated to access this page");
			log.debug("from action=="+invocation.getAction().getClass().getName());
			log.debug("getRequestURI=="+request.getRequestURI());
			return "signIn";
			}
		} else {
			log.info(" ! Inside else returning invocation.invoke()");
			return invocation.invoke();
		}
	}
	
	private void addActionError(ActionInvocation invocation, String message) {
		Object action = invocation.getAction();
		if(action instanceof ValidationAware) {
			((ValidationAware) action).addActionError(message);
		}
	}

}
