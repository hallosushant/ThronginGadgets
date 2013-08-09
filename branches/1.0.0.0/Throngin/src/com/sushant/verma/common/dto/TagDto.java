package com.sushant.verma.common.dto;

import java.sql.Timestamp;

public class TagDto{

	
	private Long tagId;

	private String tagName;

	private String tagDesc;

	private Long createdBy;

	private Timestamp createdDate;

	private Long statusId;

	
	public Long getTagId(){
	 return tagId;
	}
	public String getTagName(){
	 return tagName;
	}
	public String getTagDesc(){
	 return tagDesc;
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
	
	public void setTagId(Long tagId){
	 this.tagId = tagId;
	}
	public void setTagName(String tagName){
	 this.tagName = tagName;
	}
	public void setTagDesc(String tagDesc){
	 this.tagDesc = tagDesc;
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