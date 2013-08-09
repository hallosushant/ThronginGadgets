package com.sushant.verma.common.dto;

import java.sql.Timestamp;

public class DeviceMstDto{

	
	private Long deviceId;

	private String deviceName;

	private String deviceImgPath;

	private Timestamp createdDate;

	private Long statusId;

	
	public Long getDeviceId(){
	 return deviceId;
	}
	public String getDeviceName(){
	 return deviceName;
	}
	public String getDeviceImgPath(){
	 return deviceImgPath;
	}
	public Timestamp getCreatedDate(){
	 return createdDate;
	}
	public Long getStatusId(){
	 return statusId;
	}
	
	public void setDeviceId(Long deviceId){
	 this.deviceId = deviceId;
	}
	public void setDeviceName(String deviceName){
	 this.deviceName = deviceName;
	}
	public void setDeviceImgPath(String deviceImgPath){
	 this.deviceImgPath = deviceImgPath;
	}
	public void setCreatedDate(Timestamp createdDate){
	 this.createdDate = createdDate;
	}
	public void setStatusId(Long statusId){
	 this.statusId = statusId;
	}
}