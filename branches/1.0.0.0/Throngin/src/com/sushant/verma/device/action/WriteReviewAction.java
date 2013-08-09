package com.sushant.verma.device.action;


import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;
import com.sushant.verma.common.action.BaseAction;
import com.sushant.verma.common.constants.ZConstants;
import com.sushant.verma.common.constants.ZConstants.MsgType;
import com.sushant.verma.common.dto.ReviewDto;
import com.sushant.verma.common.dto.UserDto;
import com.sushant.verma.device.bll.DeviceBllInterface;
@Validations
@SuppressWarnings("unchecked")
public class WriteReviewAction extends BaseAction implements ModelDriven<ReviewDto>,SessionAware{

	private static final long serialVersionUID = 1L;
	private static Logger log=LogManager.getLogger(WriteReviewAction.class);
	private Long dId;
	private Long bId;
	private Long cId;
	private Long mId;
	private String writeReview;
	private String modelName;
	private Map session;
	private ReviewDto reviewDto=new ReviewDto();
	private DeviceBllInterface deviceBllImpl;
	
	@SkipValidation
	public String execute() throws Exception{
		log.info("| mId="+mId+" | dId="+dId+" | bId="+bId+" | cid="+cId);
		modelName=getApplicationKeyValue("modelList", mId.toString());
		pageTitle="Write Review on "+modelName;
		metaDescription="Login and Write Detailed Review with caption and description on "+modelName;
		return SUCCESS;
	}

	public String saveModelReview(){
		log.info("| mId="+mId+" | dId="+dId+" | bId="+bId+" | cid="+cId);
//		CommonUtility.getDTOasXML(reviewDto);
		if(session.get(ZConstants.LOGGEDIN_USER)==null){
			return INPUT;
		}
		String author=((UserDto)session.get(ZConstants.LOGGEDIN_USER)).getUserName();
		Long modifiedBy=((UserDto)session.get(ZConstants.LOGGEDIN_USER)).getUserId();
		reviewDto.setAuthor(author);
		reviewDto.setModifiedBy(modifiedBy);
		Integer insertCount=deviceBllImpl.saveModelReview(reviewDto);
		if(insertCount.intValue()==1){
			log.info("| mId="+mId+" | dId="+dId+" | bId="+bId+" | cid="+cId);
			mId=reviewDto.getModelId();
			msg="Review Saved Successfully and will appear after being approved by our moderators!";
			msgType=MsgType.SUCCESS.getMsgType();
			return "modelDetail";
		}else
			return INPUT;
	}
	@SkipValidation
	public String backToModelDetail(){
		log.info("| mId="+mId+" | dId="+dId+" | bId="+bId+" | cid="+cId);
		mId=reviewDto.getModelId();
		return "modelDetail";
	}
	
	/*
	 * Getters & Setters
	 */
	



	@VisitorFieldValidator
	public ReviewDto getModel() {
		// TODO Auto-generated method stub
		return reviewDto;
	}
	
	public Long getMId() {
		return mId;
	}

	public void setMId(Long mId) {
		this.mId = mId;
	}

	public ReviewDto getReviewDto() {
		return reviewDto;
	}

	public void setReviewDto(ReviewDto reviewDto) {
		this.reviewDto = reviewDto;
	}

	public DeviceBllInterface getDeviceBllImpl() {
		return deviceBllImpl;
	}

	public void setDeviceBllImpl(DeviceBllInterface deviceBllImpl) {
		this.deviceBllImpl = deviceBllImpl;
	}

	public Map getSession() {
		return session;
	}

	public void setSession(Map session) {
		this.session = session;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
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

	public String getWriteReview() {
		return writeReview;
	}

	public void setWriteReview(String writeReview) {
		this.writeReview = writeReview;
	}

}
