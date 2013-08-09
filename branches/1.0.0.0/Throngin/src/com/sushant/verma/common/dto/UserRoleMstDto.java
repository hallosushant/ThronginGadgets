package com.sushant.verma.common.dto;

import java.sql.Timestamp;

public class UserRoleMstDto{

	
	private Long userRoleId;

	private String userRole;

	private String userRoleDesc;

	private Timestamp createdDate;

	private Long statusId;

	
	public Long getUserRoleId(){
	 return userRoleId;
	}
	public String getUserRole(){
	 return userRole;
	}
	public String getUserRoleDesc(){
	 return userRoleDesc;
	}
	public Timestamp getCreatedDate(){
	 return createdDate;
	}
	public Long getStatusId(){
	 return statusId;
	}
	
	public void setUserRoleId(Long userRoleId){
	 this.userRoleId = userRoleId;
	}
	public void setUserRole(String userRole){
	 this.userRole = userRole;
	}
	public void setUserRoleDesc(String userRoleDesc){
	 this.userRoleDesc = userRoleDesc;
	}
	public void setCreatedDate(Timestamp createdDate){
	 this.createdDate = createdDate;
	}
	public void setStatusId(Long statusId){
	 this.statusId = statusId;
	}
}