package com.sushant.verma.common.dto;

import java.sql.Timestamp;

public class ModelCommentDto{

	
	private Long modelCommentId;

	private Long modelId;

	private Long userId;

	private String userName;

	private Timestamp createdDate;

	private Long statusId;

	private String comment;

	
	public Long getModelCommentId(){
	 return modelCommentId;
	}
	public Long getModelId(){
	 return modelId;
	}
	public Long getUserId(){
	 return userId;
	}
	public String getUserName(){
	 return userName;
	}
	public Timestamp getCreatedDate(){
	 return createdDate;
	}
	public Long getStatusId(){
	 return statusId;
	}
	public String getComment(){
	 return comment;
	}
	
	public void setModelCommentId(Long modelCommentId){
	 this.modelCommentId = modelCommentId;
	}
	public void setModelId(Long modelId){
	 this.modelId = modelId;
	}
	public void setUserId(Long userId){
	 this.userId = userId;
	}
	public void setUserName(String userName){
	 this.userName = userName;
	}
	public void setCreatedDate(Timestamp createdDate){
	 this.createdDate = createdDate;
	}
	public void setStatusId(Long statusId){
	 this.statusId = statusId;
	}
	public void setComment(String comment){
	 this.comment = comment;
	}
}