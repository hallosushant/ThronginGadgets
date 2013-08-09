package com.sushant.verma.common.action;


import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.validator.annotations.Validations;
import com.sushant.verma.common.bll.CommonBllInterface;
import com.sushant.verma.common.dto.ModelDto;
import com.sushant.verma.device.DeviceConstants;
@Validations
@SuppressWarnings("unchecked")
public class WelcomePageAction extends BaseAction{
	
	
	private static Logger log=LogManager.getLogger(WelcomePageAction.class);
	private static final long serialVersionUID = -5473342218650521038L;
	private CommonBllInterface commonBllRef;
	private List<ModelDto> popularModels;
	private List<ModelDto> newModels;
	private List<ModelDto> upcomingModels;
	private List<ModelDto> featuredModels;
	private Long dId;

	
	public String execute() throws Exception{
		log.info("@WelcomePageAction execute | dId="+dId);
		Map modelListMap=commonBllRef.getModelList(dId);
		popularModels=(List<ModelDto>) modelListMap.get(DeviceConstants.POPULAR_MODELS);
		newModels=(List<ModelDto>) modelListMap.get(DeviceConstants.NEW_MODELS);
		upcomingModels=(List<ModelDto>) modelListMap.get(DeviceConstants.UPCOMING_MODELS);
		featuredModels=(List<ModelDto>) modelListMap.get(DeviceConstants.FEATURED_MODELS);
		return SUCCESS;
	}
	
	/*
	 * Getters & Setters
	 */

	public CommonBllInterface getCommonBllRef() {
		return commonBllRef;
	}
	public void setCommonBllRef(CommonBllInterface commonBllRef) {
		this.commonBllRef = commonBllRef;
	}
	public Long getDId() {
		return dId;
	}
	public void setDId(Long dId) {
		this.dId = dId;
	}
	public List<ModelDto> getPopularModels() {
		return popularModels;
	}
	public void setPopularModels(List<ModelDto> popularModels) {
		this.popularModels = popularModels;
	}
	public List<ModelDto> getNewModels() {
		return newModels;
	}
	public void setNewModels(List<ModelDto> newModels) {
		this.newModels = newModels;
	}
	public List<ModelDto> getUpcomingModels() {
		return upcomingModels;
	}
	public void setUpcomingModels(List<ModelDto> upcomingModels) {
		this.upcomingModels = upcomingModels;
	}
	public List<ModelDto> getFeaturedModels() {
		return featuredModels;
	}
	public void setFeaturedModels(List<ModelDto> featuredModels) {
		this.featuredModels = featuredModels;
	}
	
}
