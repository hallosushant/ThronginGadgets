package com.sushant.verma.common.dto;

import java.sql.Timestamp;

public class ModelFileMappingDto{

	
	private Long modelFileMappingId;

	private Long modelId;

	private Long fileId;

	private Timestamp createdDate;

	private Long createdBy;

	private Timestamp modifiedDate;

	private Long modifiedBy;

	private Long statusId;

	
	public Long getModelFileMappingId(){
	 return modelFileMappingId;
	}
	public Long getModelId(){
	 return modelId;
	}
	public Long getFileId(){
	 return fileId;
	}
	public Timestamp getCreatedDate(){
	 return createdDate;
	}
	public Long getCreatedBy(){
	 return createdBy;
	}
	public Timestamp getModifiedDate(){
	 return modifiedDate;
	}
	public Long getModifiedBy(){
	 return modifiedBy;
	}
	public Long getStatusId(){
	 return statusId;
	}
	
	public void setModelFileMappingId(Long modelFileMappingId){
	 this.modelFileMappingId = modelFileMappingId;
	}
	public void setModelId(Long modelId){
	 this.modelId = modelId;
	}
	public void setFileId(Long fileId){
	 this.fileId = fileId;
	}
	public void setCreatedDate(Timestamp createdDate){
	 this.createdDate = createdDate;
	}
	public void setCreatedBy(Long createdBy){
	 this.createdBy = createdBy;
	}
	public void setModifiedDate(Timestamp modifiedDate){
	 this.modifiedDate = modifiedDate;
	}
	public void setModifiedBy(Long modifiedBy){
	 this.modifiedBy = modifiedBy;
	}
	public void setStatusId(Long statusId){
	 this.statusId = statusId;
	}
}