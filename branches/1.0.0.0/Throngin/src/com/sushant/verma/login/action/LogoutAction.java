package com.sushant.verma.login.action;

import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.sushant.verma.common.action.BaseAction;

public class LogoutAction extends BaseAction implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3141903542092808723L;
	private static Logger log=LogManager.getLogger(LogoutAction.class);
	private Map<String, Object> session;
	
	public String execute(){
		log.info("Start Logout Action");
//		ServletActionContext.getRequest().getSession().invalidate();
		session.put("loggedInUser", null);
//		log.debug("loggedIn User "+session.get("loggedIn")); 
//		log.debug(" loggedOut "+session.get("loggedIn"));
//		session.remove("loggedIn");
		log.info("User Logged Out");
		return "logout";
	}

	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	
}
