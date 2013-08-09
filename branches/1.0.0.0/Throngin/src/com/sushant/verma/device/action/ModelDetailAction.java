package com.sushant.verma.device.action;


import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.validator.annotations.Validations;
import com.sushant.verma.common.ZProc.ZProcConstants;
import com.sushant.verma.common.action.BaseAction;
import com.sushant.verma.common.constants.ZConstants;
import com.sushant.verma.common.dto.CategoryMstDto;
import com.sushant.verma.common.dto.ModelDetailDto;
import com.sushant.verma.common.dto.ModelDto;
import com.sushant.verma.common.dto.ReviewDto;
import com.sushant.verma.device.DeviceConstants;
import com.sushant.verma.device.DeviceEnum.ModelMenuEnum;
import com.sushant.verma.device.bll.DeviceBllInterface;
import com.sushant.verma.device.bll.ModelBllInterface;
@Validations
public class ModelDetailAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private static Logger log=LogManager.getLogger(ModelDetailAction.class);
	
	private Long dId;
	private Long bId;
	private Long cId;
	private Long mId;
	private Long rId;
	private ModelDto modelDto;
	private String modelName;
	private List<ModelDto> modelPictures;
	private List<ModelDetailDto> modelDetailList;
	private List<CategoryMstDto> modelTagsList;
	private List<ReviewDto> modelReviewList;
	private ModelBllInterface modelBllImpl;
	private DeviceBllInterface deviceBllImpl;
	private String activeModelMenu;
	
	@SuppressWarnings("unchecked")
	public String execute() throws Exception{
		log.info("| mId="+mId+" | dId="+dId+" | bId="+bId+" | cid="+cId);
		if(mId==null || mId.longValue()<1){
			msgType=ZConstants.MsgType.ERROR.getMsgType();
			msg="NO Records Found!!!";
			return INPUT;
		}
		/*modelName=getApplicationKeyValue("modelList", mId.toString());
		log.debug("modelName="+modelName);
		if(StringUtility.isNullBlank(modelName)){
			msgType=ZConstants.MsgType.ERROR.getMsgType();
			msg="NO Records Found!!!";
			return INPUT;
		}*/
		Map result=modelBllImpl.fetchModelDetails(mId);
		List<ModelDto> modelBasicInfo=(List<ModelDto>) result.get(ZProcConstants.MODEL_BASIC_INFO_RESULT_SET);
		modelDto=modelBasicInfo.get(0);
		modelName=modelDto.getModelName();//for title to be passed in tiles.xml
		pageTitle=modelName+DeviceConstants.SPEC_PRICE_REVIEWS;
		metaDescription=modelDto.getModelName()+" : "+modelDto.getModelDesc();
		modelPictures=(List<ModelDto>) result.get(ZProcConstants.MODEL_PICTURES_RESULT_SET);
		modelDetailList=(List<ModelDetailDto>) result.get(ZProcConstants.MODEL_DETAILS_RESULT_SET);
		modelTagsList=(List<CategoryMstDto>) result.get(ZProcConstants.MODEL_TAGS_RESULT_SET);
		//CommonUtility.getDTOasXML(modelDto);
		modelReviewList=deviceBllImpl.fetchModelReviews(mId);
		activeModelMenu=ModelMenuEnum.SPECIFICATIONS.getMenu();
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String review(){
		log.info("| mId="+mId+" | dId="+dId+" | bId="+bId+" | cid="+cId+" | rId="+rId);
		Map result=modelBllImpl.fetchModelDetails(mId);
		List<ModelDto> modelBasicInfo=(List<ModelDto>) result.get(ZProcConstants.MODEL_BASIC_INFO_RESULT_SET);
		modelDto=modelBasicInfo.get(0);
		modelName=modelDto.getModelName();//for title to be passed in tiles.xml
		modelPictures=(List<ModelDto>) result.get(ZProcConstants.MODEL_PICTURES_RESULT_SET);
		modelDetailList=(List<ModelDetailDto>) result.get(ZProcConstants.MODEL_DETAILS_RESULT_SET);
		modelTagsList=(List<CategoryMstDto>) result.get(ZProcConstants.MODEL_TAGS_RESULT_SET);
		modelReviewList=deviceBllImpl.fetchModelReviews(mId);
		for(ReviewDto reviewDto:modelReviewList){
			if(reviewDto.getReviewId().equals(rId)){
				pageTitle=modelName+" User Review - "+reviewDto.getTitle();
				metaDescription=reviewDto.getTitle();
			}
		}
		activeModelMenu=ModelMenuEnum.REVIEWS.getMenu();
		return SUCCESS;
	}
	
	
	/*
	 * Getters & Setters
	 */
	

	public ModelDto getModelDto() {
		log.debug(" getter modelDto="+modelDto);
		return modelDto;
	}

	public void setModelDto(ModelDto modelDto) {
		this.modelDto = modelDto;
	}

	public ModelBllInterface getModelBllImpl() {
		log.debug(" getter modelBllImpl="+modelBllImpl);
		return modelBllImpl;
	}
	public void setModelBllImpl(ModelBllInterface modelBllImpl) {
		this.modelBllImpl = modelBllImpl;
	}
	
	public Long getDId() {
		return dId;
	}

	public void setDId(Long dId) {
		this.dId = dId;
	}
	public Long getMId() {
		log.debug(" getter mId="+mId);
		return mId;
	}
	public void setMId(Long mId) {
		this.mId = mId;
	}
	public List<ModelDto> getModelPictures() {
		return modelPictures;
	}
	public void setModelPictures(List<ModelDto> modelPictures) {
		this.modelPictures = modelPictures;
	}
	public List<ModelDetailDto> getModelDetailList() {
		return modelDetailList;
	}
	public void setModelDetailList(List<ModelDetailDto> modelDetailList) {
		this.modelDetailList = modelDetailList;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public List<CategoryMstDto> getModelTagsList() {
		return modelTagsList;
	}
	public void setModelTagsList(List<CategoryMstDto> modelTagsList) {
		this.modelTagsList = modelTagsList;
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

	public Long getRId() {
		return rId;
	}

	public void setRId(Long rId) {
		this.rId = rId;
	}

	public List<ReviewDto> getModelReviewList() {
		return modelReviewList;
	}

	public void setModelReviewList(List<ReviewDto> modelReviewList) {
		this.modelReviewList = modelReviewList;
	}

	public DeviceBllInterface getDeviceBllImpl() {
		return deviceBllImpl;
	}

	public void setDeviceBllImpl(DeviceBllInterface deviceBllImpl) {
		this.deviceBllImpl = deviceBllImpl;
	}

	public String getActiveModelMenu() {
		return activeModelMenu;
	}

	public void setActiveModelMenu(String activeModelMenu) {
		this.activeModelMenu = activeModelMenu;
	}

}
