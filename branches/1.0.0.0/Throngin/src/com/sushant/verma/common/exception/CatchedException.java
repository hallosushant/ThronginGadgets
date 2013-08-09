package com.sushant.verma.common.exception;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class CatchedException extends BaseRuntimeException {
	
	private static final long serialVersionUID = 1646457477785678L;
	private static final Logger log = LogManager.getLogger(CatchedException.class);

	private String exceptionMessage;
	private String exceptionCode ;

	public CatchedException()
	{
		super();
		exceptionMessage="none";
		exceptionCode = "none";
	}
	public CatchedException(String exceptionMessage,String exceptionCode)
	{
		super();
		this.exceptionMessage=exceptionMessage;
		this.exceptionCode = exceptionCode;
		log.error(exceptionMessage);
		log.error(exceptionCode);
	}
	
	public String getExceptionmessage() {
		return exceptionMessage;
	}
	public void setExceptionmessage(String exceptionmessage) {
		this.exceptionMessage = exceptionmessage;
	}
	public String getExceptionCode() {
		return exceptionCode;
	}
	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}	
}
