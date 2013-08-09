package com.sushant.verma.login;

import java.util.Arrays;
import java.util.List;

import com.sushant.verma.common.ResourceLoader.ResourceEnums;

public class LoginConstants {
	public static  List<String> CONFIRM_REGISTRATION_KEYWORD=Arrays.asList(ResourceEnums.WordList.wordList.getProperty().split(","));
	public static  int CONFIRM_REGISTRATION_KEYWORD_SIZE=Integer.parseInt(ResourceEnums.WordList.wordCount.getProperty());
	public static  String REGISTRATION_URL_LINK = "http://gadgets.throngin.com/confirmRegistration.html?authId=";
	public static  String GOOGLE_OPEN_LOGIN_LINK="https://www.google.com/accounts/o8/id";
	public static  String YAHOO_OPEN_LOGIN_LINK="https://me.yahoo.com";
	public static final Long ACTIVE_STATUS_ID=new Long(1);
	public static final Long INACTIVE_STATUS_ID=new Long(2);
	public static final String Y = "Y";
	public static final String N = "N";
	public static final int ONE = 1;
	public static final int ZERO = 0;
	
	public static final String MESSAGE="MESSAGE";
	public static final String MESSAGE_TYPE="MESSAGE_TYPE";
	public static final String STRUTS_RESULT_NAME="STRUTS_RESULT_NAME";
	public static final String SHOW_CAPTCHA="showCaptcha";
	
	public static final Long REGISTERED_USER_ROLE_ID=2L;
	public static final Long OPEN_USER_ROLE_ID=4L;
	public static final List<String> PWD_USER_ROLES=Arrays.asList("Registered User","Admin");
}
