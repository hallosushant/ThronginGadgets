package com.sushant.verma.admin.action;

import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.validator.annotations.Validations;
import com.sushant.verma.admin.bll.AdminBllInterface;
import com.sushant.verma.common.action.BaseAction;
import com.sushant.verma.common.utility.StringUtility;
@Validations
public class AdminAjaxAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	private static Logger log=LogManager.getLogger(AdminAjaxAction.class);
	
	private AdminBllInterface adminBllInterface;
	private Long deviceId;
	private Long manufacturerId;
	private List<ListOrderedMap> manufacturerList;
	private List<ListOrderedMap> modelList;
	private String deviceName;
	private String manufacturerName;
	private String detailCategory;
	private String detailCategoryName;
	private List<ListOrderedMap> detailNameList;
	public String execute() throws Exception{
		
		return SUCCESS;
	}
	
	public String fetchManufacturerList(){
		log.debug("|__deviceId="+deviceId);
		if(deviceId!=null && deviceId>0){
			manufacturerList=adminBllInterface.getManufacturerList(deviceId);
			deviceName=getApplicationKeyValue("deviceList", deviceId.toString());
		}
		return SUCCESS;
	}
	
	public String fetchModelList(){
		log.debug("|__manufacturerId="+manufacturerId+" | deviceId="+deviceId);
		if(null!=deviceId && deviceId>0 && manufacturerId!=null && manufacturerId>0){
			modelList=adminBllInterface.getModelList(deviceId,manufacturerId);
			deviceName=getApplicationKeyValue("deviceList", deviceId.toString());
			manufacturerName=getApplicationKeyValue("manufacturerList", manufacturerId.toString());
		}
		return SUCCESS;
	}
	
	public String fetchDetailNameList(){
		log.debug("|__detailCategory="+detailCategory);
		if(StringUtility.isNotNullBlank(detailCategory)){
			detailNameList=adminBllInterface.getDetailNameList(detailCategory);
			detailCategoryName=getApplicationKeyValue("modelDetailCategoryList", detailCategory);
		}
		return SUCCESS;
	}

	
	/*
	 *Getters & Setters 
	 *
	 */
	


	public AdminBllInterface getAdminBllInterface() {
		return adminBllInterface;
	}

	public void setAdminBllInterface(AdminBllInterface adminBllInterface) {
		this.adminBllInterface = adminBllInterface;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public List<ListOrderedMap> getManufacturerList() {
		return manufacturerList;
	}

	public void setManufacturerList(List<ListOrderedMap> manufacturerList) {
		this.manufacturerList = manufacturerList;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDetailCategory() {
		return detailCategory;
	}

	public void setDetailCategory(String detailCategory) {
		this.detailCategory = detailCategory;
	}

	public List<ListOrderedMap> getDetailNameList() {
		return detailNameList;
	}

	public void setDetailNameList(List<ListOrderedMap> detailNameList) {
		this.detailNameList = detailNameList;
	}

	public String getDetailCategoryName() {
		return detailCategoryName;
	}

	public void setDetailCategoryName(String detailCategoryName) {
		this.detailCategoryName = detailCategoryName;
	}

	public Long getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(Long manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public List<ListOrderedMap> getModelList() {
		return modelList;
	}

	public void setModelList(List<ListOrderedMap> modelList) {
		this.modelList = modelList;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	
}
