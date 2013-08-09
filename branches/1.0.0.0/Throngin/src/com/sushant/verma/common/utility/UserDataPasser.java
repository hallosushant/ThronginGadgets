package com.sushant.verma.common.utility;


public class UserDataPasser {

	private static ThreadLocal<String> userEmailID = new ThreadLocal<String>();
	private static ThreadLocal<Long> userID = new ThreadLocal<Long>();
	private static ThreadLocal<String> userRole = new ThreadLocal<String>();
	private static ThreadLocal<String> userName = new ThreadLocal<String>();

	public static void setUserEmailID(String userEmailId) {
		UserDataPasser.userEmailID.set(userEmailId);
	}

	public static String getUserEmailID() {
		return userEmailID.get();
	}
	public static void setUserID(Long userId) {
		UserDataPasser.userID.set(userId);
	}

	public static Long getUserID() {
		return userID.get();
	}
	public static void setUserRole(String userRole) {
		UserDataPasser.userRole.set(userRole);
	}

	public static String getUserRole() {
		return userRole.get();
	}
	
	public static void setUserName(String userName) {
		UserDataPasser.userName.set(userName);
	}

	public static String getUserName() {
		return userName.get();
	}
}
