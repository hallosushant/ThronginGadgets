package com.sushant.verma.common.dto;

import java.sql.Timestamp;

public class DetailNameMstDto{

	
	private Long detailNameId;

	private String detailName;

	private Long categoryId;

	private Timestamp createdDate;

	private Long statusId;

	
	public Long getDetailNameId(){
	 return detailNameId;
	}
	public String getDetailName(){
	 return detailName;
	}
	public Long getCategoryId(){
	 return categoryId;
	}
	public Timestamp getCreatedDate(){
	 return createdDate;
	}
	public Long getStatusId(){
	 return statusId;
	}
	
	public void setDetailNameId(Long detailNameId){
	 this.detailNameId = detailNameId;
	}
	public void setDetailName(String detailName){
	 this.detailName = detailName;
	}
	public void setCategoryId(Long categoryId){
	 this.categoryId = categoryId;
	}
	public void setCreatedDate(Timestamp createdDate){
	 this.createdDate = createdDate;
	}
	public void setStatusId(Long statusId){
	 this.statusId = statusId;
	}
}