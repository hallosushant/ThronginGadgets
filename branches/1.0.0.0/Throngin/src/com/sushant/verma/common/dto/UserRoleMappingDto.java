package com.sushant.verma.common.dto;

import java.sql.Timestamp;

public class UserRoleMappingDto{

	
	private Long userRoleMappingId;

	private Long userRoleId;

	private Long userId;

	private Timestamp createdDate;

	private Long statusId;

	
	public Long getUserRoleMappingId(){
	 return userRoleMappingId;
	}
	public Long getUserRoleId(){
	 return userRoleId;
	}
	public Long getUserId(){
	 return userId;
	}
	public Timestamp getCreatedDate(){
	 return createdDate;
	}
	public Long getStatusId(){
	 return statusId;
	}
	
	public void setUserRoleMappingId(Long userRoleMappingId){
	 this.userRoleMappingId = userRoleMappingId;
	}
	public void setUserRoleId(Long userRoleId){
	 this.userRoleId = userRoleId;
	}
	public void setUserId(Long userId){
	 this.userId = userId;
	}
	public void setCreatedDate(Timestamp createdDate){
	 this.createdDate = createdDate;
	}
	public void setStatusId(Long statusId){
	 this.statusId = statusId;
	}
}