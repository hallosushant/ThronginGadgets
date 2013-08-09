package com.sushant.verma.common.dto;

import java.sql.Timestamp;

public class FileDetailDto{

	
	private Long fileDetailId;

	private Long fileId;

	private String detailName;

	private String detailValue;

	private Timestamp createdDate;

	private Long createdBy;

	private Timestamp modifiedDate;

	private Long modifiedBy;

	private Long statusId;

	
	public Long getFileDetailId(){
	 return fileDetailId;
	}
	public Long getFileId(){
	 return fileId;
	}
	public String getDetailName(){
	 return detailName;
	}
	public String getDetailValue(){
	 return detailValue;
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
	
	public void setFileDetailId(Long fileDetailId){
	 this.fileDetailId = fileDetailId;
	}
	public void setFileId(Long fileId){
	 this.fileId = fileId;
	}
	public void setDetailName(String detailName){
	 this.detailName = detailName;
	}
	public void setDetailValue(String detailValue){
	 this.detailValue = detailValue;
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