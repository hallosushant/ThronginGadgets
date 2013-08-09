package com.sushant.verma.common.dto;


public class PopularityDto{

	
	private Long popularityId;

	private Long deviceId;

	private Long manufacturerId;

	private Long categoryId;

	private Long modelId;

	private Integer popularityIndex;

	
	public Long getPopularityId(){
	 return popularityId;
	}
	public Long getDeviceId(){
	 return deviceId;
	}
	public Long getManufacturerId(){
	 return manufacturerId;
	}
	public Long getCategoryId(){
	 return categoryId;
	}
	public Long getModelId(){
	 return modelId;
	}
	public Integer getPopularityIndex(){
	 return popularityIndex;
	}
	
	public void setPopularityId(Long popularityId){
	 this.popularityId = popularityId;
	}
	public void setDeviceId(Long deviceId){
	 this.deviceId = deviceId;
	}
	public void setManufacturerId(Long manufacturerId){
	 this.manufacturerId = manufacturerId;
	}
	public void setCategoryId(Long categoryId){
	 this.categoryId = categoryId;
	}
	public void setModelId(Long modelId){
	 this.modelId = modelId;
	}
	public void setPopularityIndex(Integer popularityIndex){
	 this.popularityIndex = popularityIndex;
	}
}