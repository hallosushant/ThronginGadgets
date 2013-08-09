package com.sushant.verma.device.action;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.validator.annotations.Validations;
import com.sushant.verma.common.action.BaseAction;
@Validations
public class DeviceHomeAction extends BaseAction{
	
	
	private static Logger log=LogManager.getLogger(DeviceHomeAction.class);
	private static final long serialVersionUID = -5473342218650521038L;

	private String msg;
	public String execute() throws Exception{
		log.info("Inside execute");
		return SUCCESS;
	}
	
	
	
	
	
	
	/*
	 * Getters & Setters
	 */
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
}
