package com.sushant.verma.common.dto;

import java.sql.Timestamp;

public class ManufacturerDto{

	
	private Long manufacturerId;

	private String manufacturerName;

	private Long deviceMstId;

	private Timestamp createdDate;

	private Long statusId;

	
	public Long getManufacturerId(){
	 return manufacturerId;
	}
	public String getManufacturerName(){
	 return manufacturerName;
	}
	public Long getDeviceMstId(){
	 return deviceMstId;
	}
	public Timestamp getCreatedDate(){
	 return createdDate;
	}
	public Long getStatusId(){
	 return statusId;
	}
	
	public void setManufacturerId(Long manufacturerId){
	 this.manufacturerId = manufacturerId;
	}
	public void setManufacturerName(String manufacturerName){
	 this.manufacturerName = manufacturerName;
	}
	public void setDeviceMstId(Long deviceMstId){
	 this.deviceMstId = deviceMstId;
	}
	public void setCreatedDate(Timestamp createdDate){
	 this.createdDate = createdDate;
	}
	public void setStatusId(Long statusId){
	 this.statusId = statusId;
	}
}