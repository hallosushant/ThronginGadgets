package com.sushant.verma.common.constants;

public class ZConstants {

	public static final String LOGGEDIN_USER="loggedInUser";
	public static final String ADMIN_USER_ROLE="Admin";
	public static final String URL_REWRITE_RULE="urlRewriteRule";
	public enum MsgType{
		INFO("info"),SUCCESS("success"),WARNING("warning"),ERROR("error");
		
		private String msgType;
		
		MsgType(String msgType){
			this.msgType=msgType;
		}

		public String getMsgType() {
			return msgType;
		}
	}
	
	public enum TitleEnum{
		TITLE("TITLE"),ALL("All"),POPULAR("Popular"),NEW("New"),UPCOMING("Upcoming"),RELATED_MODELS("RELATED MODELS"),TOP_RATED_MODELS("TOP RATED MODELS");
		private String title;
		TitleEnum(String title){
			this.title=title;
		}
		public String getTitle() {
			return title;
		}
		
	}
}
