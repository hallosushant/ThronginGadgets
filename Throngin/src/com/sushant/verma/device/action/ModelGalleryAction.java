package com.sushant.verma.device.action;


import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.validator.annotations.Validations;
import com.sushant.verma.common.action.BaseAction;
import com.sushant.verma.common.constants.ZConstants.MsgType;
import com.sushant.verma.common.dto.ModelDto;
import com.sushant.verma.common.utility.StringUtility;
import com.sushant.verma.device.DeviceConstants;
import com.sushant.verma.device.bll.DeviceBllInterface;
@Validations
public class ModelGalleryAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private static Logger log=LogManager.getLogger(ModelGalleryAction.class);

	private Long dId;
	private Long bId;
	private Long cId;
	private String galleryType;
	private Integer p=1;
	private String pageTitle;
	private String galleryTitleSuffix;
	private final String GALLERY=" Gallery";
	private boolean showMore=true;
	private List<List<ModelDto>> compositeModelList;
	private List<ModelDto> modelList;
	private DeviceBllInterface deviceBllImpl;
	
	public String execute() throws Exception{
		log.info("Inside execute | dId="+dId+" | bId="+bId);
		if(bId!=null){
			String manufacturerName=getApplicationKeyValue("manufacturerList", bId.toString());
			if(StringUtility.isNotNullBlank(manufacturerName))
				galleryTitleSuffix=" "+manufacturerName+" Models";
		}
		else{
			String deviceName=getApplicationKeyValue("deviceList", dId.toString());
			if(StringUtility.isNotNullBlank(deviceName))
				galleryTitleSuffix=" "+deviceName+" Models";
		}
		if(dId!=null){
				compositeModelList=deviceBllImpl.fetchManufacturerModels(dId, bId);
				log.debug("compositeModelList Size="+compositeModelList.size());
				if(compositeModelList.isEmpty()){
					msg="No Records Found";
					msgType=MsgType.INFO.getMsgType();
				}
		}else{
			msg="Oops : Please Try again!";
			msgType=MsgType.ERROR.getMsgType();
		}
		return SUCCESS;
	}
	
	public String moreModels(){
		log.info("dId="+dId+" | bId="+bId+" | galleryType="+galleryType+" | p="+p);
		if(bId!=null){
			String manufacturerName=getApplicationKeyValue("manufacturerList", bId.toString());
			if(StringUtility.isNotNullBlank(manufacturerName))
				galleryTitleSuffix=" "+manufacturerName+" Models";
		}
		else{
			String deviceName=getApplicationKeyValue("deviceList", dId.toString());
			if(StringUtility.isNotNullBlank(deviceName))
				galleryTitleSuffix=" "+deviceName+" Models";
		}

		if(dId!=null && StringUtility.isNotNullBlank(galleryType)){
			//modelList=deviceBllImpl.moreManufacturerModels(dId, bId,galleryType,DeviceConstants.MODEL_GALLERY_PAGE_SIZE,p);
			modelList=deviceBllImpl.moreManufacturerModelsWithCount(dId, bId,galleryType,DeviceConstants.MODEL_GALLERY_PAGE_SIZE,p);

			if(modelList.isEmpty()){
				msg="No Records Found";
				msgType=MsgType.INFO.getMsgType();
				return INPUT;
			}else{
				Long totalCount=modelList.get(0).getModelCount();
				if(p*DeviceConstants.MODEL_GALLERY_PAGE_SIZE>=totalCount)// p = currentPageNo
					showMore=false;
				p+=1;//increase pageNo
				log.debug("modelList Size="+modelList.size());
			}
		}else{
			msg="Oops : Please Try again!";
			msgType=MsgType.ERROR.getMsgType();
			return INPUT;
		}
		pageTitle=modelList.get(0).getTitle()+galleryTitleSuffix+GALLERY;
		return "more";
	}
	
	public String catgModels(){
		log.info("Inside execute | dId="+dId+" | cId="+cId);
		if(dId!=null && cId!=null){
			String categoryName=getApplicationKeyValue("modelCategoryList", cId.toString());
			if(StringUtility.isNotNullBlank(categoryName)){
				galleryTitleSuffix=" "+categoryName+" Models";
				compositeModelList=deviceBllImpl.fetchCategoryModels(dId, cId);
				log.debug("compositeModelList Size="+compositeModelList.size());
				if(compositeModelList.isEmpty()){
					msg="No Records Found";
					msgType=MsgType.INFO.getMsgType();
				}
			}else{
				msg="No Records Found";
				msgType=MsgType.INFO.getMsgType();
			}
		}else{
			msg="Oops : Please Try again!";
			msgType=MsgType.ERROR.getMsgType();
		}
		return SUCCESS;
	}
	
	public String moreCatgModels(){
		log.info("dId="+dId+" | cId="+cId+" | galleryType="+galleryType+" | p="+p);
		if(dId!=null && cId!=null && StringUtility.isNotNullBlank(galleryType)){
			String categoryName=getApplicationKeyValue("modelCategoryList", cId.toString());
			if(StringUtility.isNotNullBlank(categoryName)){
				galleryTitleSuffix=" "+categoryName+" Models";
				//modelList=deviceBllImpl.moreCategoryModels(dId, cId,galleryType,DeviceConstants.MODEL_GALLERY_PAGE_SIZE,p);
				modelList=deviceBllImpl.moreCategoryModelsWithCount(dId, cId,galleryType,DeviceConstants.MODEL_GALLERY_PAGE_SIZE,p);

				if(modelList.isEmpty()){
					msg="No Records Found";
					msgType=MsgType.INFO.getMsgType();
					return INPUT;
				}else{
					Long totalCount=modelList.get(0).getModelCount();
					if(p*DeviceConstants.MODEL_GALLERY_PAGE_SIZE>=totalCount)// p = currentPageNo
						showMore=false;
					p+=1;//increase pageNo
					log.debug("modelList Size="+modelList.size());
				}
			}else{
				msg="No Records Found";
				msgType=MsgType.INFO.getMsgType();
				return INPUT;
			}
		}else{
			msg="Oops : Please Try again!";
			msgType=MsgType.ERROR.getMsgType();
			return INPUT;
		}
		pageTitle=modelList.get(0).getTitle()+galleryTitleSuffix+GALLERY;
		return "more";
	}
	
	/*
	 * Getters & Setters
	 */
	
	public List<ModelDto> getModelList() {
		return modelList;
	}
	public void setModelList(List<ModelDto> modelList) {
		this.modelList = modelList;
	}
	public DeviceBllInterface getDeviceBllImpl() {
		return deviceBllImpl;
	}
	public void setDeviceBllImpl(DeviceBllInterface deviceBllImpl) {
		this.deviceBllImpl = deviceBllImpl;
	}
	public Long getDId() {
		return dId;
	}
	public void setDId(Long dId) {
		this.dId = dId;
	}
	public Long getBId() {
		return bId;
	}
	public void setBId(Long bId) {
		this.bId = bId;
	}

	public String getGalleryType() {
		return galleryType;
	}

	public void setGalleryType(String galleryType) {
		this.galleryType = galleryType;
	}

	public Integer getP() {
		return p;
	}

	public void setP(Integer p) {
		this.p = p;
	}

	public List<List<ModelDto>> getCompositeModelList() {
		return compositeModelList;
	}

	public void setCompositeModelList(List<List<ModelDto>> compositeModelList) {
		this.compositeModelList = compositeModelList;
	}

	public Long getCId() {
		return cId;
	}

	public void setCId(Long cId) {
		this.cId = cId;
	}

	public String getGalleryTitleSuffix() {
		return galleryTitleSuffix;
	}

	public void setGalleryTitleSuffix(String galleryTitleSuffix) {
		this.galleryTitleSuffix = galleryTitleSuffix;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public boolean isShowMore() {
		return showMore;
	}

	public void setShowMore(boolean showMore) {
		this.showMore = showMore;
	}
	
}
