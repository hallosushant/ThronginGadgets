package com.sushant.verma.admin.bll;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sushant.verma.admin.dao.AdminDao;
import com.sushant.verma.common.constants.DbConstants;
import com.sushant.verma.common.dto.ModelDto;
import com.sushant.verma.common.dto.ReviewDto;
import com.sushant.verma.common.email.EmailConstants;
import com.sushant.verma.common.email.EmailServiceInterface;
import com.sushant.verma.common.exception.BaseRuntimeException;
import com.sushant.verma.common.utility.StringUtility;

public class AdminBllImpl implements AdminBllInterface{

	private static Logger log=LogManager.getLogger(AdminBllImpl.class);
	private AdminDao adminDao;
	private EmailServiceInterface emailService;
 
	public List<ReviewDto> fetchDeviceInactiveReviews(Long deviceId) {
		log.debug("|__deviceId="+deviceId);
		return adminDao.fetchDeviceInactiveReviews(deviceId);
	}
	public List<ReviewDto> approveReview(ReviewDto reviewDto) {
		if(reviewDto.getApprove().equalsIgnoreCase(DbConstants.Y_STATUS)){
			reviewDto.setStatusId(DbConstants.ACTIVE_STATUS_ID);		
		}
		else if(reviewDto.getApprove().equalsIgnoreCase(DbConstants.N_STATUS)){
			reviewDto.setStatusId(DbConstants.DISAPPROVED_STATUS_ID);
		}
		return adminDao.approveReview(reviewDto);
	}
	
	public List<ListOrderedMap> getManufacturerList(Long deviceId) {
		log.debug("|__deviceId="+deviceId);
		return adminDao.getManufacturerList(deviceId);
	}


	public List<ListOrderedMap> getDetailNameList(String detailCategory) {
		log.debug("|__detailCategory="+detailCategory);
		return adminDao.getDetailNameList(detailCategory);
	}


	public List<ListOrderedMap> addNewModel(ModelDto modelDto) {
		int modelCount=adminDao.addNewModel(modelDto);
		if(modelCount==1){
			Long modelId=adminDao.getModelId(modelDto.getModelName());
			modelDto.setModelId(modelId);
			adminDao.addModelCategory(modelDto);
			adminDao.updateModelLink(modelDto);
			adminDao.updateCategoryModelLink(modelDto);

			/*
			 * Sending Email for Newly added Model
			 */
			String fromEmail=EmailConstants.ADMIN_THRONGIN_EMAIL_ID;
			String[] toEmail=new String[2];
			toEmail[0]=EmailConstants.SUPPORT_THRONGIN_EMAIL_ID;
			toEmail[1]=EmailConstants.SUSHANT_THRONGIN_EMAIL_ID;
			String emailBody=EmailConstants.getNewAddedModelHtmlEmailContent(modelDto);
			String emailSubject=EmailConstants.SUBJECT_HEADER_NEW_MODEL+modelDto.getModelName();

			HashMap<String,Object> emailParam=new HashMap<String,Object>();
			emailParam.put("FROM_EMAIL", fromEmail);
			emailParam.put("TO_EMAIL", toEmail);
			emailParam.put("SUBJECT_EMAIL", emailSubject);
			emailParam.put("BODY_EMAIL", emailBody);
			try {
				log.debug("calling sendEmail");
				emailService.sendEmail(emailParam);
			} catch (Exception e) {
				log.error("Error Sending Mail for "+emailSubject,e);
			}
		}
		return adminDao.fetchModelLink(modelDto);
	}
	
	public List<ListOrderedMap> getModelDetails(Long modelId) {
		log.debug("|__ modelId="+modelId);
		return adminDao.getModelDetails(modelId);
	}

	public void assignModelDetail(String modelName, Long modelId, List<String> detailName, List<String> detailValue, List<String> detailCategory, String assignType){
		log.debug("|__ modelId="+modelId+
				"\n|__detailName="+detailName+
				"\n|__detailCategory="+detailCategory+
				"\n|__detailValue="+detailValue+
				"\n|__assignType="+assignType);
		adminDao.deleteModelDetail(modelId);
		adminDao.assignModelDetail(modelId,detailName,detailValue,detailCategory);
		/*
		 * Sending Email for Assigned Model Details
		 */
		String fromEmail=EmailConstants.ADMIN_THRONGIN_EMAIL_ID;
		String[] toEmail=new String[2];
		toEmail[0]=EmailConstants.SUPPORT_THRONGIN_EMAIL_ID;
		toEmail[1]=EmailConstants.SUSHANT_THRONGIN_EMAIL_ID;
		String emailBody=EmailConstants.getModelDetailsHtmlEmailContent(modelName,detailValue);
		String emailSubject=null;
		if("add".equalsIgnoreCase(assignType))
			emailSubject=EmailConstants.SUBJECT_HEADER_MODEL_DETAILS+modelName;
		else if("edit".equalsIgnoreCase(assignType))
			emailSubject=EmailConstants.SUBJECT_HEADER_EDIT_MODEL_DETAILS+modelName;
		HashMap<String,Object> emailParam=new HashMap<String,Object>();
		emailParam.put("FROM_EMAIL", fromEmail);
		emailParam.put("TO_EMAIL", toEmail);
		emailParam.put("SUBJECT_EMAIL", emailSubject);
		emailParam.put("BODY_EMAIL", emailBody);
		try {
			log.debug("calling sendEmail");
			emailService.sendEmail(emailParam);
		} catch (Exception e) {
			log.error("Error Sending Mail for "+emailSubject,e);
		}
	}

	public List<ListOrderedMap> getModelList(int state) {
		return adminDao.getModelList(state);
	}
	public List<ListOrderedMap> getModelList(Long deviceId, Long manufacturerId) {
		return adminDao.getModelList(deviceId,manufacturerId);
	}
	

	public List<ListOrderedMap> getUserList(String userEmail,Long userRoleId) {
		if(StringUtility.isNotNullBlank(userEmail) || userRoleId.longValue()!=0){
		List<ListOrderedMap> userList=adminDao.getUserList(userEmail,userRoleId);
		log.debug("|__userList=="+userList);
		return userList;
		}
		else
			return null;
	}
	
	public void removeCurrentRole(Long userId, Long currentRoleId) {
		if(null!=userId && null!=currentRoleId){
			adminDao.removeCurrentRole(userId,currentRoleId);
		}
		else
			throw new BaseRuntimeException();
	}
	
	public void assignUserRole(Long userId, Long selectedUserRole) {
		if(null!=userId && null!=selectedUserRole)
			adminDao.assignUserRole(userId,selectedUserRole);
		else
			throw new BaseRuntimeException();
	}


	public List<ListOrderedMap> getCategoryList() {
		return adminDao.getCategoryList();
	}

	public List<ListOrderedMap> createCategory(String categoryName, Long parentCategory) throws Exception {
		if(StringUtility.areNotNullBlank(categoryName,parentCategory.toString())){
			adminDao.createCategory(categoryName,parentCategory);
			return this.getCategoryList();
		}
		else
			throw new BaseRuntimeException();
	}


	public List<ListOrderedMap> searchSubCategoryList(Long categoryNameSearch) {
		if(StringUtility.isNotNullBlank(categoryNameSearch.toString()))
			return adminDao.searchSubCategoryList(categoryNameSearch);
		else
			throw new BaseRuntimeException();
	}
	


	public void createAttribute(String attributeName, String attributeDesc) throws Exception {
		log.debug("|__attributeName=="+attributeName+"\n|__attributeDesc=="+attributeDesc);
		if(StringUtility.areNotNullBlank(attributeName,attributeDesc))
			adminDao.createAttribute(attributeName,attributeDesc);
	}

	public List<ModelDto> fetchAllModels() {
		return adminDao.allModels();
	}

	public HashMap<String,Object> getModelBasicDetails(Long deviceId, Long manufacturerId, Long modelId) {
		log.debug("|__deviceId="+deviceId+" |__manufacturerId="+manufacturerId+" |__modelId="+modelId);
		List<ModelDto> modelBasicDetails=adminDao.getModelBasicDetails(deviceId, manufacturerId, modelId);
		List modelCatgoryList=adminDao.getModelCatgoryList(deviceId, manufacturerId, modelId);
		HashMap<String,Object> modelBasicDetailsMap=new HashMap<String,Object>();
		modelBasicDetailsMap.put("modelBasicDetails", modelBasicDetails);
		modelBasicDetailsMap.put("modelCatgoryList", modelCatgoryList);
		return modelBasicDetailsMap;
	}
	

	public Map<String,List<ListOrderedMap>> editModel(ModelDto modelDto) {
		List<ListOrderedMap> currentModelLinks=adminDao.fetchModelLink(modelDto);
		int modelCount=adminDao.updateModel(modelDto);
		if(modelCount==1){
			adminDao.deleteModelCategories(modelDto.getModelId());
			adminDao.addModelCategory(modelDto);
			adminDao.updateModelLink(modelDto);
			adminDao.updateCategoryModelLink(modelDto);

			/*
			 * Sending Email for Updated Model
			 */
			String fromEmail=EmailConstants.ADMIN_THRONGIN_EMAIL_ID;
			String[] toEmail=new String[2];
			toEmail[0]=EmailConstants.SUPPORT_THRONGIN_EMAIL_ID;
			toEmail[1]=EmailConstants.SUSHANT_THRONGIN_EMAIL_ID;
			String emailBody=EmailConstants.getUpdatedModelHtmlEmailContent(modelDto);
			String emailSubject=EmailConstants.SUBJECT_HEADER_UPDATE_MODEL+modelDto.getModelName();

			HashMap<String,Object> emailParam=new HashMap<String,Object>();
			emailParam.put("FROM_EMAIL", fromEmail);
			emailParam.put("TO_EMAIL", toEmail);
			emailParam.put("SUBJECT_EMAIL", emailSubject);
			emailParam.put("BODY_EMAIL", emailBody);
			try {
				log.debug("calling sendEmail");
				emailService.sendEmail(emailParam);
			} catch (Exception e) {
				log.error("Error Sending Mail for "+emailSubject,e);
			}
		}
		List<ListOrderedMap> newModelLinks=adminDao.fetchModelLink(modelDto);
		HashMap<String,List<ListOrderedMap>> modelLinkMap=new HashMap<String,List<ListOrderedMap>>();
		modelLinkMap.put("removeModelLinks",currentModelLinks);
		modelLinkMap.put("newModelLinks",newModelLinks);
		return modelLinkMap;
	}
	
	/* 
	 * Getters & Setters
	 */

	public AdminDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	public EmailServiceInterface getEmailService() {
		return emailService;
	}

	public void setEmailService(EmailServiceInterface emailService) {
		this.emailService = emailService;
	}

}
