package com.sushant.verma.admin.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ModelDriven;
import com.sushant.verma.admin.bll.AdminBllInterface;
import com.sushant.verma.common.action.BaseAction;
import com.sushant.verma.common.constants.DbConstants;
import com.sushant.verma.common.constants.ZConstants.MsgType;
import com.sushant.verma.common.dto.ReviewDto;
import com.sushant.verma.common.utility.CommonUtility;
import com.sushant.verma.common.utility.StringUtility;
import com.sushant.verma.device.action.WriteReviewAction;

public class ApproveReviewAction extends BaseAction implements ModelDriven<ReviewDto>{
	private static final long serialVersionUID = 1L;
	private static Logger log=LogManager.getLogger(WriteReviewAction.class);
	private Long dId;
	private Long rId;
	private List<ReviewDto> deviceInactiveReviews;
	private ReviewDto reviewDto=new ReviewDto();
	private AdminBllInterface adminBllImpl;

	public String execute() throws Exception{
		log.debug("dId="+dId);
		deviceInactiveReviews=adminBllImpl.fetchDeviceInactiveReviews(dId);
		if(!deviceInactiveReviews.isEmpty()){
			reviewDto=deviceInactiveReviews.get(0);
			log.debug("reviewDto="+CommonUtility.getDTOasXML(reviewDto));
		}else if(StringUtility.isNotNullBlank(msg)){
			log.debug("Device last review approved and Inactive Reviews list is blank");
			msg=msg+"<br/><br/>No reviews pending for approval.";
		}else{
			log.debug("Device Inactive Reviews list is blank");
			msg="No reviews pending for approval.";
			msgType=MsgType.INFO.getMsgType();
		}
		return SUCCESS;
	}

	public String approveReview() throws Exception{
		log.debug("dId="+dId+" | rId="+rId+" | approve="+reviewDto.getApprove());
		log.debug("reviewDto="+CommonUtility.getDTOasXML(reviewDto));
		List<ReviewDto> reviewUrlRuleList=adminBllImpl.approveReview(reviewDto);
		log.debug("reviewUrlRuleList="+reviewUrlRuleList);
		if(!reviewUrlRuleList.isEmpty()){
			ArrayList<String> urlRewriteRuleList=new ArrayList<String>();
			for(int i=0;i<reviewUrlRuleList.size();i++){
				urlRewriteRuleList.add(reviewUrlRuleList.get(i).getUrlRewriteRule());
			}
			super.addUrlRules(urlRewriteRuleList);
		}
		if(reviewDto.getApprove().equalsIgnoreCase(DbConstants.Y_STATUS)){
			msg="Approved : Review titled '"+reviewDto.getTitle()+"' for "+reviewDto.getModelName();
			msgType=MsgType.SUCCESS.getMsgType();
		}
		else if(reviewDto.getApprove().equalsIgnoreCase(DbConstants.N_STATUS)){
			msg="Disapproved : Review titled '"+reviewDto.getTitle()+"' for "+reviewDto.getModelName();
			msgType=MsgType.SUCCESS.getMsgType();
		}
		
		return "approved";
	}
	
	
	public ReviewDto getModel() {
		// TODO Auto-generated method stub
		return reviewDto;
	}
	
	/*
	 * Getters & Setters
	 */
	
	public Long getDId() {
		return dId;
	}

	public ReviewDto getReviewDto() {
		return reviewDto;
	}

	public void setReviewDto(ReviewDto reviewDto) {
		this.reviewDto = reviewDto;
	}

	public void setDId(Long dId) {
		this.dId = dId;
	}

	public Long getRId() {
		return rId;
	}

	public void setRId(Long rId) {
		this.rId = rId;
	}

	public List<ReviewDto> getDeviceInactiveReviews() {
		return deviceInactiveReviews;
	}

	public void setDeviceInactiveReviews(List<ReviewDto> deviceInactiveReviews) {
		this.deviceInactiveReviews = deviceInactiveReviews;
	}

	public AdminBllInterface getAdminBllImpl() {
		return adminBllImpl;
	}

	public void setAdminBllImpl(AdminBllInterface adminBllImpl) {
		this.adminBllImpl = adminBllImpl;
	}
}
