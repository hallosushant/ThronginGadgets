package com.sushant.verma.common.dto;

import java.sql.Timestamp;

public class ModelCategoryMappingDto{

	
	private Long modelCategoryMappingId;

	private Long categoryId;

	private Long modelId;

	private Timestamp createdDate;

	private Long createdBy;

	private Timestamp modifiedDate;

	private Long modifiedBy;

	private Long statusId;

	
	public Long getModelCategoryMappingId(){
	 return modelCategoryMappingId;
	}
	public Long getCategoryId(){
	 return categoryId;
	}
	public Long getModelId(){
	 return modelId;
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
	
	public void setModelCategoryMappingId(Long modelCategoryMappingId){
	 this.modelCategoryMappingId = modelCategoryMappingId;
	}
	public void setCategoryId(Long categoryId){
	 this.categoryId = categoryId;
	}
	public void setModelId(Long modelId){
	 this.modelId = modelId;
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