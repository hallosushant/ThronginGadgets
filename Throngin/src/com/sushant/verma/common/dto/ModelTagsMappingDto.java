package com.sushant.verma.common.dto;

import java.sql.Timestamp;

public class ModelTagsMappingDto{

	
	private Long modelTagMappingId;

	private Long modelId;

	private Long tagId;

	private Long createdBy;

	private Timestamp createdDate;

	private Long statusId;

	
	public Long getModelTagMappingId(){
	 return modelTagMappingId;
	}
	public Long getModelId(){
	 return modelId;
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
	
	public void setModelTagMappingId(Long modelTagMappingId){
	 this.modelTagMappingId = modelTagMappingId;
	}
	public void setModelId(Long modelId){
	 this.modelId = modelId;
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