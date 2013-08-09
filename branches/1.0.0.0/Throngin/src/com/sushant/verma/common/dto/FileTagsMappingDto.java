package com.sushant.verma.common.dto;

import java.sql.Timestamp;

public class FileTagsMappingDto{

	
	private Long fileTagsMappingId;

	private Long fileId;

	private Long tagId;

	private Long createdBy;

	private Timestamp createdDate;

	private Long statusId;

	
	public Long getFileTagsMappingId(){
	 return fileTagsMappingId;
	}
	public Long getFileId(){
	 return fileId;
	}
	public Long getTagId(){
	 return tagId;
	}
	public Long getCreatedBy(){
	 return createdBy;
	}
	public Timestamp getCreatedDate(){
	 return createdDate;
	}
	public Long getStatusId(){
	 return statusId;
	}
	
	public void setFileTagsMappingId(Long fileTagsMappingId){
	 this.fileTagsMappingId = fileTagsMappingId;
	}
	public void setFileId(Long fileId){
	 this.fileId = fileId;
	}
	public void setTagId(Long tagId){
	 this.tagId = tagId;
	}
	public void setCreatedBy(Long createdBy){
	 this.createdBy = createdBy;
	}
	public void setCreatedDate(Timestamp createdDate){
	 this.createdDate = createdDate;
	}
	public void setStatusId(Long statusId){
	 this.statusId = statusId;
	}
}