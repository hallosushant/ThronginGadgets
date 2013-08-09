package com.sushant.verma.common.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.sushant.verma.common.constants.ZConstants;
import com.sushant.verma.common.dto.UserDto;
import com.sushant.verma.common.utility.UserDataPasser;

public class SessionValidationInterceptor implements Interceptor {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(SessionValidationInterceptor.class);
	private static final long SESSION_MAX_INACTIVE_INTERVAL=1800000;//30min
	public String intercept(ActionInvocation invocation) throws Exception {
		log.debug("Inside SessionValidationInterceptor");
		final ActionContext actionContext = invocation.getInvocationContext();
		log.debug("invocation context received : " + actionContext);

		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String,Object> session = actionContext.getSession();
		HttpSession httpsession = request.getSession(false);

		String actionName = invocation.getAction().getClass().getSimpleName();
		 String className = invocation.getAction().getClass().getName();

		 ActionProxy ap=invocation.getProxy();
		 log.debug("Before calling action: " + className +" | Action Name1 : "+actionName+" | Action Name : "+ap.getActionName()+" | NameSpace : "+ap.getNamespace()+" | Method Name : "+ap.getMethod()); 
		 if ("ActionSupport".equalsIgnoreCase(actionName)) {
			 actionName = invocation.getInvocationContext().getName();
			 log.debug("Calling actionName : " + actionName);
		 }


		 log.debug("Session Value :: " + session);
		 log.debug("Request Session Value :: " + httpsession);
		 String msg="";
		 if (session == null || session.isEmpty()) {
			 request.getSession(false);
			 msg="Session Expired, Please login again!";
			 addActionError(invocation, msg);
			 log.debug("from action=="+invocation.getAction().getClass().getName());
			 log.debug("getRequestURI=="+request.getRequestURI());
			 log.debug("msg :: " + msg);
			 return "signIn";
		 }
		 if (null == httpsession) {
			 msg="Session Expired, Please login again";
			 addActionError(invocation, msg);
			 log.debug("from action=="+invocation.getAction().getClass().getName());
			 log.debug("getRequestURI=="+request.getRequestURI());
			 log.debug("msg :: " + msg);
			 return "signIn";
		 }
		 if(session.get(ZConstants.LOGGEDIN_USER)==null) {
			 msg="Login required";
			 addActionError(invocation, msg);
			 log.debug("from action=="+invocation.getAction().getClass().getName());
			 log.debug("getRequestURI=="+request.getRequestURI());
			 log.debug("msg :: " + msg);
			 return "signIn";
		 }
		 else{
			 Boolean isAuthorized = false;
			 UserDto userDto =(UserDto) session.get(ZConstants.LOGGEDIN_USER);
			 if(userDto!=null && userDto.getUserRole().equalsIgnoreCase(ZConstants.ADMIN_USER_ROLE)){
				 isAuthorized = true;
			 }
			 if (isAuthorized) {
				 Long interval = System.currentTimeMillis() - httpsession.getLastAccessedTime();
				 log.debug("Inactive interval for session id "+httpsession.getId()+" __ "+interval);
				 if (interval > SESSION_MAX_INACTIVE_INTERVAL)//30min
				 {
					 log.debug("from action=="+invocation.getAction().getClass().getName());
					 log.debug("getRequestURI=="+request.getRequestURI());
					 httpsession.invalidate();
					 return "userInactive";
				 }
				 log.info(" ! Inside If returning invocation.invoke()");
				 userDto =(UserDto) session.get(ZConstants.LOGGEDIN_USER);
				 if(userDto!=null){
					 UserDataPasser.setUserID(userDto.getUserId());
					 UserDataPasser.setUserEmailID(userDto.getUserEmail());
					 UserDataPasser.setUserRole(userDto.getUserRole());
					 UserDataPasser.setUserName(userDto.getUserName());
					 return invocation.invoke();
				 }
				 else{
					 msg="Session Expired, Please login again";
					 addActionError(invocation, msg);
					 return "signIn";
				 }
			 } else {
				 msg="Invalid Access! 'Admin' privledge required";
				 addActionError(invocation, msg);
				 return "signIn";
			 }
		 }
	}

	public void init() {
		// Any resources that need to be initialized before call of intercept()
		// method.
		log.info("! Intializing SessionValidationInterceptor");
	}

	public void destroy() {
		// Called to let an interceptor clean up any resources it has allocated.
		log.info("! Destroying SessionValidationInterceptor");
	}

	private void addActionError(ActionInvocation invocation, String message) {
		Object action = invocation.getAction();
		if(action instanceof ValidationAware) {
			((ValidationAware) action).addActionError(message);
		}
	}
}