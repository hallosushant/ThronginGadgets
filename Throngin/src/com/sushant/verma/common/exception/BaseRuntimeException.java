package com.sushant.verma.common.exception;

public class BaseRuntimeException extends RuntimeException{

	private static final long serialVersionUID = -3690401809213607786L;
	private String exceptionMessage;
	private String exceptionCode;
	
	public BaseRuntimeException()
	{
		super();
		exceptionMessage="none";
	}
	
	public BaseRuntimeException(String message)
	{
		super();
		exceptionMessage=message;
	}
	public BaseRuntimeException(String message, String code)
	{
		super();
		exceptionMessage=message;
		this.exceptionCode = code;
	}
	public BaseRuntimeException(Exception ex)
	{
		super(ex);
		
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public String getExceptionCode() {
		return exceptionCode;
	}

	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

}
