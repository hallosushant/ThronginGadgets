package com.sushant.verma.common.dto;


public class StatusMstDto{

	
	private Long statusId;

	private String status;

	private String statusDesc;

	private String active;

	
	public Long getStatusId(){
	 return statusId;
	}
	public String getStatus(){
	 return status;
	}
	public String getStatusDesc(){
	 return statusDesc;
	}
	public String getActive(){
	 return active;
	}
	
	public void setStatusId(Long statusId){
	 this.statusId = statusId;
	}
	public void setStatus(String status){
	 this.status = status;
	}
	public void setStatusDesc(String statusDesc){
	 this.statusDesc = statusDesc;
	}
	public void setActive(String active){
	 this.active = active;
	}
}