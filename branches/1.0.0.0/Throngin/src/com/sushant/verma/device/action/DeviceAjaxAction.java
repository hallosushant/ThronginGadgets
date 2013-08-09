package com.sushant.verma.device.action;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.validator.annotations.Validations;
import com.sushant.verma.common.action.BaseAction;
import com.sushant.verma.common.constants.ZConstants.MsgType;
import com.sushant.verma.common.dto.ModelDetailDto;
import com.sushant.verma.common.dto.ModelDto;
import com.sushant.verma.common.dto.RatingDto;
import com.sushant.verma.common.dto.ReviewDto;
import com.sushant.verma.common.dto.SearchResultDto;
import com.sushant.verma.common.utility.StringUtility;
import com.sushant.verma.device.DeviceConstants;
import com.sushant.verma.device.bll.DeviceBllInterface;
@Validations
public class DeviceAjaxAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	private static Logger log=LogManager.getLogger(DeviceAjaxAction.class);
	
	private List<ModelDto> modelList;
	private List<ReviewDto> modelReviewList;
	private List<ReviewDto> deviceReviewList;
	private List<ModelDetailDto> modelPhotosList;
	private List<SearchResultDto> modelSearchList;
	private DeviceBllInterface deviceBllImpl;
	private Long dId;
	private Long bId;
	private Long cId;
	private Long mId;
	private Long reviewId;
	private String galleryType;
	private Integer p=1;
	private Long tc; 
	private boolean showMore=true;
	private Float score;
	private String searchStr;
	private String tagsCatg;
	
	public String execute() throws Exception{
		
		return SUCCESS;
	}
	
	public String moreDeviceModels(){
		log.info("dId="+dId+" | bId="+bId+" | galleryType="+galleryType+" | p="+p);
		if(dId!=null && StringUtility.isNotNullBlank(galleryType)){
			String deviceName=getApplicationKeyValue("deviceList", dId.toString());
			if(StringUtility.isNotNullBlank(deviceName)){
				modelList=deviceBllImpl.moreManufacturerModelsWithCount(dId, bId,galleryType,DeviceConstants.MODEL_GALLERY_PAGE_SIZE,p);//deviceBllImpl.moreManufacturerModels(dId, bId,galleryType,DeviceConstants.MODEL_GALLERY_PAGE_SIZE,p);
				if(p*DeviceConstants.MODEL_GALLERY_PAGE_SIZE>=tc)
					showMore=false;
				p+=1;//increase pageNo
				log.debug("modelList Size="+modelList.size());
				if(modelList.isEmpty()){
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
	
	public String moreModels(){
		log.info("dId="+dId+" | bId="+bId+" | galleryType="+galleryType+" | p="+p);
		if(dId!=null && bId!=null && StringUtility.isNotNullBlank(galleryType)){
			String manufacturerName=getApplicationKeyValue("manufacturerList", bId.toString());
			if(StringUtility.isNotNullBlank(manufacturerName)){
				modelList=deviceBllImpl.moreManufacturerModels(dId, bId,galleryType,DeviceConstants.MODEL_GALLERY_PAGE_SIZE,p);
				if(p*DeviceConstants.MODEL_GALLERY_PAGE_SIZE>=tc)
					showMore=false;
				p+=1;//increase pageNo
				log.debug("modelList Size="+modelList.size());
				if(modelList.isEmpty()){
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
				modelList=deviceBllImpl.moreCategoryModels(dId, cId,galleryType,DeviceConstants.MODEL_GALLERY_PAGE_SIZE,p);
				log.debug("modelList Size="+modelList.size());
				if(p*DeviceConstants.MODEL_GALLERY_PAGE_SIZE>=tc)
					showMore=false;
				p+=1;//increase pageNo
				if(modelList.isEmpty()){
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
	
	public String rateThis(){
		log.debug("mId="+mId+" |reviewId="+reviewId+" |score="+score);
		RatingDto ratingDto=new RatingDto();
		ratingDto.setModelId(mId);
		ratingDto.setReviewId(reviewId);
		ratingDto.setStarRating(score);
		deviceBllImpl.rateThis(ratingDto);
		return SUCCESS;
	}
	
	public String fetchModelReviews(){
		log.debug("mId="+mId);
		if(mId!=null){
			modelReviewList=deviceBllImpl.fetchModelReviews(mId);
			log.debug("modelReviewList Size="+modelReviewList.size());
		}else{
			msg="Oops : Please Try again!";
			msgType=MsgType.ERROR.getMsgType();
		}
		return SUCCESS;
	}
	
	public String fetchDeviceLatestReviews(){
		log.debug("dId="+dId);
		if(dId!=null){
			deviceReviewList=deviceBllImpl.fetchDeviceLatestReviews(dId);
			log.debug("deviceReviewList Size="+deviceReviewList.size());
		}else{
			msg="Oops : Please Try again!";
			msgType=MsgType.ERROR.getMsgType();
		}
		return SUCCESS;
	}
	
	public String fetchModelPhotos(){
		log.debug("mId="+mId);
		if(mId!=null){
			modelPhotosList=deviceBllImpl.fetchModelPhotos(mId);
			log.debug("modelPhotosList Size="+modelPhotosList.size());
		}else{
			msg="Oops : Please Try again!";
			msgType=MsgType.ERROR.getMsgType();
		}
		return SUCCESS;
	}
	
	public String zModelSearch(){
		log.debug("zModelSearch | search Str="+searchStr+" | dId="+dId);
		if(StringUtility.isNotNullBlank(searchStr) && dId!=null){
			modelSearchList=deviceBllImpl.zModelSearch(searchStr,dId);
			log.debug("modelSearchList Size="+modelSearchList.size());
		}else{
			msg="Oops : Please Try again!";
			msgType=MsgType.ERROR.getMsgType();
		}
		return SUCCESS;
	}
	
	public String fetchRelatedCatgModels(){
		log.debug("dId="+dId+" | tagsCatg="+tagsCatg+" | mId="+mId);
		if(dId!=null && StringUtility.isNotNullBlank(tagsCatg) && mId!=null)
			modelList=deviceBllImpl.fetchRelatedCatgModels(dId,mId,tagsCatg);
		log.debug("modelList="+modelList);
		return SUCCESS;
	}
	
	public String fetchTopRatedModels() {
		log.debug("dId="+dId);
		if(dId!=null)
			modelList=deviceBllImpl.fetchTopRatedModels(dId);
		log.debug("modelList="+modelList);
		return SUCCESS;		
	}
	
	
	/*
	 *Getters & Setters 
	 *
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

	public Long getCId() {
		return cId;
	}

	public void setCId(Long cId) {
		this.cId = cId;
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

	public Long getTc() {
		return tc;
	}

	public void setTc(Long tc) {
		this.tc = tc;
	}

	public boolean isShowMore() {
		return showMore;
	}

	public void setShowMore(boolean showMore) {
		this.showMore = showMore;
	}

	public List<ReviewDto> getModelReviewList() {
		return modelReviewList;
	}

	public void setModelReviewList(List<ReviewDto> modelReviewList) {
		this.modelReviewList = modelReviewList;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public Long getReviewId() {
		return reviewId;
	}

	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

	public List<ModelDetailDto> getModelPhotosList() {
		return modelPhotosList;
	}

	public void setModelPhotosList(List<ModelDetailDto> modelPhotosList) {
		this.modelPhotosList = modelPhotosList;
	}

	public String getSearchStr() {
		return searchStr;
	}

	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}

	public List<SearchResultDto> getModelSearchList() {
		return modelSearchList;
	}

	public void setModelSearchList(List<SearchResultDto> modelSearchList) {
		this.modelSearchList = modelSearchList;
	}

	public Long getMId() {
		return mId;
	}

	public void setMId(Long mId) {
		this.mId = mId;
	}

	public List<ReviewDto> getDeviceReviewList() {
		return deviceReviewList;
	}

	public void setDeviceReviewList(List<ReviewDto> deviceReviewList) {
		this.deviceReviewList = deviceReviewList;
	}

	public String getTagsCatg() {
		return tagsCatg;
	}

	public void setTagsCatg(String tagsCatg) {
		this.tagsCatg = tagsCatg;
	}

}
