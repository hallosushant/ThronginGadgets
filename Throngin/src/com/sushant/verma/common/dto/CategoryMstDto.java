package com.sushant.verma.common.dto;

import java.sql.Timestamp;

public class CategoryMstDto{

	
	private Long categoryId;

	private String categoryName;

	private String categoryDesc;

	private Long parentCategoryId;
	
	private Long deviceId;

	private Timestamp createdDate;

	private Long createdBy;

	private Long statusId;

	
	public Long getCategoryId(){
	 return categoryId;
	}
	public String getCategoryName(){
	 return categoryName;
	}
	public String getCategoryDesc(){
	 return categoryDesc;
	}
	public Long getParentCategoryId(){
	 return parentCategoryId;
	}
	public Timestamp getCreatedDate(){
	 return createdDate;
	}
	public Long getCreatedBy(){
	 return createdBy;
	}
	public Long getStatusId(){
	 return statusId;
	}
	
	public void setCategoryId(Long categoryId){
	 this.categoryId = categoryId;
	}
	public void setCategoryName(String categoryName){
	 this.categoryName = categoryName;
	}
	public void setCategoryDesc(String categoryDesc){
	 this.categoryDesc = categoryDesc;
	}
	public void setParentCategoryId(Long parentCategoryId){
	 this.parentCategoryId = parentCategoryId;
	}
	public void setCreatedDate(Timestamp createdDate){
	 this.createdDate = createdDate;
	}
	public void setCreatedBy(Long createdBy){
	 this.createdBy = createdBy;
	}
	public void setStatusId(Long statusId){
	 this.statusId = statusId;
	}
	public Long getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}
	
}