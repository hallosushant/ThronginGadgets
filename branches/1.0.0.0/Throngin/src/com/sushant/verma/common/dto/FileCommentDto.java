package com.sushant.verma.common.dto;

import java.sql.Timestamp;

public class FileCommentDto{

	
	private Long fileCommentId;

	private Long fileId;

	private Long userId;

	private String userName;

	private Long statusId;

	private Timestamp createdDate;

	private String comment;

	
	public Long getFileCommentId(){
	 return fileCommentId;
	}
	public Long getFileId(){
	 return fileId;
	}
	public Long getUserId(){
	 return userId;
	}
	public String getUserName(){
	 return userName;
	}
	public Long getStatusId(){
	 return statusId;
	}
	public Timestamp getCreatedDate(){
	 return createdDate;
	}
	public String getComment(){
	 return comment;
	}
	
	public void setFileCommentId(Long fileCommentId){
	 this.fileCommentId = fileCommentId;
	}
	public void setFileId(Long fileId){
	 this.fileId = fileId;
	}
	public void setUserId(Long userId){
	 this.userId = userId;
	}
	public void setUserName(String userName){
	 this.userName = userName;
	}
	public void setStatusId(Long statusId){
	 this.statusId = statusId;
	}
	public void setCreatedDate(Timestamp createdDate){
	 this.createdDate = createdDate;
	}
	public void setComment(String comment){
	 this.comment = comment;
	}
}