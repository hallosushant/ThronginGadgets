package com.sushant.verma.common.dto;

import java.sql.Timestamp;

public class ModelDetailDto{

	
	private Long modelDetailId;

	private Long modelId;

	private Long detailNameId;

	private String detailName;

	private String detailValue;

	private Long categoryId;

	private String categoryName;

	private Timestamp modifiedDate;

	private Long modifiedBy;

	private Long statusId;

	
	public Long getModelDetailId(){
	 return modelDetailId;
	}
	public Long getModelId(){
	 return modelId;
	}
	public Long getDetailNameId(){
	 return detailNameId;
	}
	public String getDetailName(){
	 return detailName;
	}
	public String getDetailValue(){
	 return detailValue;
	}
	public Long getCategoryId(){
	 return categoryId;
	}
	public String getCategoryName(){
	 return categoryName;
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
	
	public void setModelDetailId(Long modelDetailId){
	 this.modelDetailId = modelDetailId;
	}
	public void setModelId(Long modelId){
	 this.modelId = modelId;
	}
	public void setDetailNameId(Long detailNameId){
	 this.detailNameId = detailNameId;
	}
	public void setDetailName(String detailName){
	 this.detailName = detailName;
	}
	public void setDetailValue(String detailValue){
	 this.detailValue = detailValue;
	}
	public void setCategoryId(Long categoryId){
	 this.categoryId = categoryId;
	}
	public void setCategoryName(String categoryName){
	 this.categoryName = categoryName;
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