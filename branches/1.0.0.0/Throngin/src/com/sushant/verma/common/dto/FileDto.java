package com.sushant.verma.common.dto;

import java.sql.Timestamp;

public class FileDto{

	
	private Long fileId;

	private String fileName;

	private Byte file;

	private String fileDesc;

	private String fileType;

	private String fileExtension;

	private String filePath;

	private Long categoryId;

	private Timestamp createdDate;

	private Long createdBy;

	private Timestamp modifiedDate;

	private Long modifiedBy;

	private Long statusId;

	
	public Long getFileId(){
	 return fileId;
	}
	public String getFileName(){
	 return fileName;
	}
	public Byte getFile(){
	 return file;
	}
	public String getFileDesc(){
	 return fileDesc;
	}
	public String getFileType(){
	 return fileType;
	}
	public String getFileExtension(){
	 return fileExtension;
	}
	public String getFilePath(){
	 return filePath;
	}
	public Long getCategoryId(){
	 return categoryId;
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
	
	public void setFileId(Long fileId){
	 this.fileId = fileId;
	}
	public void setFileName(String fileName){
	 this.fileName = fileName;
	}
	public void setFile(Byte file){
	 this.file = file;
	}
	public void setFileDesc(String fileDesc){
	 this.fileDesc = fileDesc;
	}
	public void setFileType(String fileType){
	 this.fileType = fileType;
	}
	public void setFileExtension(String fileExtension){
	 this.fileExtension = fileExtension;
	}
	public void setFilePath(String filePath){
	 this.filePath = filePath;
	}
	public void setCategoryId(Long categoryId){
	 this.categoryId = categoryId;
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