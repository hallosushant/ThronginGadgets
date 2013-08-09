package com.sushant.verma.admin.action;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;
import com.sun.xml.rpc.processor.model.Model;
import com.sushant.verma.admin.bll.AdminBllInterface;
import com.sushant.verma.common.action.BaseAction;
import com.sushant.verma.common.constants.DbConstants;
import com.sushant.verma.common.constants.FeedConstants;
import com.sushant.verma.common.constants.ZConstants.MsgType;
import com.sushant.verma.common.dto.FeedDto;
import com.sushant.verma.common.dto.ModelDto;
import com.sushant.verma.common.email.EmailConstants;
import com.sushant.verma.common.utility.DateUtility;
import com.sushant.verma.common.utility.StringUtility;
import com.sushant.verma.common.utility.UserDataPasser;
import com.sushant.verma.device.DeviceConstants;
@Validations
public class AddNewModelAction extends BaseAction implements ModelDriven<ModelDto>,SessionAware{
	
	private static final long serialVersionUID = 1L;
	private static Logger log=LogManager.getLogger(AddNewModelAction.class);
	
	private AdminBllInterface adminBllImpl;
	private ModelDto modelDto=new ModelDto();
	private String manufacturerName;
	private String modelName;
	private Boolean addNewModel=false;
	private String addEdit;
	private Map session;
	@SkipValidation
	public String execute() throws Exception{
		
		return SUCCESS;
	}
	
	public String openAddNewModelBlock(){
		log.debug("|__modelDto.getManufacturerId()="+modelDto.getManufacturerId());
		manufacturerName=getApplicationKeyValue("manufacturerList", modelDto.getManufacturerId().toString());
		setAddNewModel(true);
		return SUCCESS;
	}
	
	public String openEditModelBlock(){
		log.debug("|__modelDto.getModelId()="+modelDto.getModelId()+" |__modelDto.getManufacturerId()="+modelDto.getManufacturerId()+" |__deviceId="+modelDto.getDeviceId());
		HashMap<String,Object> modelBasicDetailsMap=adminBllImpl.getModelBasicDetails(modelDto.getDeviceId(),modelDto.getManufacturerId(),modelDto.getModelId());
		List<ModelDto> modelBasicDetails=(List<ModelDto>) modelBasicDetailsMap.get("modelBasicDetails");
		List<Long> modelCatgoryList=(List<Long>) modelBasicDetailsMap.get("modelCatgoryList");
		log.debug("|__modelCatgoryList="+modelCatgoryList);
		modelDto=modelBasicDetails.get(0);
		modelName=modelDto.getModelName();
		modelDto.setModelCategoryId(modelCatgoryList);
		modelDto.setModelLaunchDate(modelDto.getLaunchDate().toString());
		setAddNewModel(true);
		return SUCCESS;
	}
	
	public String addNewModel() throws Exception{
		log.debug("|__modelImage=="+modelDto.getModelImage()+
				"\n|__deviceId="+modelDto.getDeviceId()+
				"\n|__manufacturerId="+modelDto.getManufacturerId()+
				"\n|__modelCategoryId=="+modelDto.getModelCategoryId()+
				"\n|__modelName="+modelDto.getModelName()+
				"\n|__modelImageUrl="+modelDto.getModelImageUrl()+
				"\n|__ModelDesc="+modelDto.getModelDesc()+
				"\n|__modelLaunchDate="+modelDto.getModelLaunchDate()+
				"\n|__Price="+modelDto.getPrice());
		if (modelDto.getModelImage()!= null) { 
		   // This returns an inputstream which you can save to your database or write to a file
			InputStream imageStream=new FileInputStream(modelDto.getModelImage());
			byte[] imageBytes= IOUtils.toByteArray(imageStream);
			modelDto.setModelImageBytes(imageBytes);
			log.debug("|__model image byte []=="+modelDto.getModelImageBytes());
		}
		if (modelDto.getModelImageUrl()!= null || modelDto.getModelImageBytes()!=null) {
			
			modelDto.setModifiedBy(UserDataPasser.getUserID());
			modelDto.setStatusId(DbConstants.ACTIVE_STATUS_ID);
			
			List<ListOrderedMap> modelUrlLinkList=adminBllImpl.addNewModel(modelDto);
			log.debug("modelUrlLinkList="+modelUrlLinkList);
			if(!modelUrlLinkList.isEmpty()){
				String modelLink=(String) modelUrlLinkList.get(0).get("RULE");
				modelUrlLinkList.remove(0);
				ArrayList<String> urlRewriteRuleList=new ArrayList<String>();
				for(int i=0;i<modelUrlLinkList.size();i++){
					urlRewriteRuleList.add(modelUrlLinkList.get(i).get("RULE").toString());
				}
				super.addUrlRules(urlRewriteRuleList);
				try{
					/*
					 *Creating Feed for the added Model 
					 */
					FeedDto feedDto=new FeedDto();
					feedDto.setGuid(super.contextPath+modelLink);
					feedDto.setLink(super.getServerContext(request)+modelLink);
					feedDto.setPubDate(DateUtility.getRFC822Date(new Date()));
					feedDto.setAuthor(EmailConstants.RSS_FEED_THRONGIN_EMAIL_ID+" ("+EmailConstants.RSS_FEED_THRONGIN_AUTHOR+")");
					feedDto.setTitle(modelDto.getModelName()+DeviceConstants.SPEC_PRICE_REVIEWS);
					feedDto.setModelName(modelDto.getModelName());
					feedDto.setModelImageUrl(modelDto.getModelImageUrl());
					feedDto.setDescription(modelDto.getModelDesc());
					
					String[] launchDateParts=modelDto.getModelLaunchDate().split("-");
					Calendar cal=Calendar.getInstance();
					cal.set(Calendar.YEAR, Integer.valueOf(launchDateParts[0]));
					cal.set(Calendar.MONTH, Integer.valueOf(launchDateParts[1]));
					cal.set(Calendar.DATE, Integer.valueOf(launchDateParts[2]));
					feedDto.setLaunchDate(DateUtility.getMonthYear(cal.getTime()));
					if(modelDto.getPrice()!=null)
						feedDto.setPrice(modelDto.getPrice().toString());
					else
						feedDto.setPrice("NOT AVAILABLE");
					String itemXmlNode=FeedConstants.getNewModelItemNode(feedDto);
					feedDto.setItemXmlNode(itemXmlNode);
					super.addRssFeedItem(feedDto);
				}catch(Exception e){
					log.error("Exception Catched while adding Feed,e=",e);
				}
			}
			msg=modelDto.getModelName()+" added successfully!";
			msgType=MsgType.SUCCESS.getMsgType();
		}
		else{
			msg="Error: Unable to add "+modelDto.getModelName();
			msgType=MsgType.ERROR.getMsgType();
			setAddNewModel(true);
			return INPUT;
		}
			
		setAddNewModel(false);
		return SUCCESS;
	}

	public String editModel() throws Exception{
		log.debug("|__modelId=="+modelDto.getModelId()+
				"|__modelImage=="+modelDto.getModelImage()+
				"\n|__deviceId="+modelDto.getDeviceId()+
				"\n|__manufacturerId="+modelDto.getManufacturerId()+
				"\n|__modelCategoryId=="+modelDto.getModelCategoryId()+
				"\n|__modelName="+modelDto.getModelName()+
				"\n|__modelImageUrl="+modelDto.getModelImageUrl()+
				"\n|__ModelDesc="+modelDto.getModelDesc()+
				"\n|__modelLaunchDate="+modelDto.getModelLaunchDate()+
				"\n|__Price="+modelDto.getPrice());
		if (modelDto.getModelImage()!= null) { 
		   // This returns an inputstream which you can save to your database or write to a file
			InputStream imageStream=new FileInputStream(modelDto.getModelImage());
			byte[] imageBytes= IOUtils.toByteArray(imageStream);
			modelDto.setModelImageBytes(imageBytes);
			log.debug("|__model image byte []=="+modelDto.getModelImageBytes());
		}
		if (modelDto.getModelImageUrl()!= null || modelDto.getModelImageBytes()!=null) {
			
			modelDto.setModifiedBy(UserDataPasser.getUserID());
			modelDto.setStatusId(DbConstants.ACTIVE_STATUS_ID);
			
			Map<String, List<ListOrderedMap>> modelLinkMap=adminBllImpl.editModel(modelDto);
			List<ListOrderedMap> removeModelLinks=modelLinkMap.get("removeModelLinks");
			log.debug("removeModelLinks="+removeModelLinks);
			if(!removeModelLinks.isEmpty()){
				String modelLink=(String) removeModelLinks.get(0).get("RULE");
				removeModelLinks.remove(0);
				ArrayList<String> urlRewriteRuleList=new ArrayList<String>();
				for(int i=0;i<removeModelLinks.size();i++){
					urlRewriteRuleList.add(removeModelLinks.get(i).get("RULE").toString());
				} 
				super.removeUrlRules(modelDto.getModelId(),"modelId");
			}
			
			
			List<ListOrderedMap> newModelLinks=modelLinkMap.get("newModelLinks"); 
			log.debug("newModelLinks="+newModelLinks);
			if(!newModelLinks.isEmpty()){
				String modelLink=(String) newModelLinks.get(0).get("RULE");
				newModelLinks.remove(0);
				ArrayList<String> urlRewriteRuleList=new ArrayList<String>();
				for(int i=0;i<newModelLinks.size();i++){
					urlRewriteRuleList.add(newModelLinks.get(i).get("RULE").toString());
				}
				super.addUrlRules(urlRewriteRuleList);
				/*try{
					
					 *Creating Feed for the added Model 
					 
					FeedDto feedDto=new FeedDto();
					feedDto.setGuid(super.contextPath+modelLink);
					feedDto.setLink(super.getServerContext(request)+modelLink);
					feedDto.setPubDate(DateUtility.getRFC822Date(new Date()));
					feedDto.setAuthor(EmailConstants.RSS_FEED_THRONGIN_EMAIL_ID+" ("+EmailConstants.RSS_FEED_THRONGIN_AUTHOR+")");
					feedDto.setTitle(modelDto.getModelName()+DeviceConstants.SPEC_PRICE_REVIEWS);
					feedDto.setModelName(modelDto.getModelName());
					feedDto.setModelImageUrl(modelDto.getModelImageUrl());
					feedDto.setDescription(modelDto.getModelDesc());
					
					String[] launchDateParts=modelDto.getModelLaunchDate().split("-");
					Calendar cal=Calendar.getInstance();
					cal.set(Calendar.YEAR, Integer.valueOf(launchDateParts[0]));
					cal.set(Calendar.MONTH, Integer.valueOf(launchDateParts[1]));
					cal.set(Calendar.DATE, Integer.valueOf(launchDateParts[2]));
					feedDto.setLaunchDate(DateUtility.getMonthYear(cal.getTime()));
					if(modelDto.getPrice()!=null)
						feedDto.setPrice(modelDto.getPrice().toString());
					else
						feedDto.setPrice("NOT AVAILABLE");
					String itemXmlNode=FeedConstants.getNewModelItemNode(feedDto);
					feedDto.setItemXmlNode(itemXmlNode);
					super.addRssFeedItem(feedDto);
				}catch(Exception e){
					log.error("Exception Catched while adding Feed,e=",e);
				}*/
			}
			msg=modelDto.getModelName()+" updated successfully!";
			msgType=MsgType.SUCCESS.getMsgType();
		}
		else{
			msg="Error: Unable to update "+modelDto.getModelName();
			msgType=MsgType.ERROR.getMsgType();
			setAddNewModel(true);
			return INPUT;
		}
			
		setAddNewModel(false);
		return SUCCESS;
	}
	
	public String editBasicDetails()  throws Exception{
		log.debug("|__modelImage=="+modelDto.getModelImage()+
				"\n|__deviceId="+modelDto.getDeviceId()+
				"\n|__manufacturerId="+modelDto.getManufacturerId()+
				"\n|__modelCategoryId=="+modelDto.getModelCategoryId()+
				"\n|__modelName="+modelDto.getModelName()+
				"\n|__modelImageUrl="+modelDto.getModelImageUrl()+
				"\n|__ModelDesc="+modelDto.getModelDesc()+
				"\n|__modelLaunchDate="+modelDto.getModelLaunchDate()+
				"\n|__Price="+modelDto.getPrice());
		if (modelDto.getModelImageUrl()!= null || modelDto.getModelImageBytes()!=null) {
			
			modelDto.setModifiedBy(UserDataPasser.getUserID());
			modelDto.setStatusId(DbConstants.ACTIVE_STATUS_ID);
			
			List<ListOrderedMap> modelUrlLinkList=adminBllImpl.addNewModel(modelDto);
			log.debug("modelUrlLinkList="+modelUrlLinkList);
			if(!modelUrlLinkList.isEmpty()){
				String modelLink=(String) modelUrlLinkList.get(0).get("RULE");
				modelUrlLinkList.remove(0);
				ArrayList<String> urlRewriteRuleList=new ArrayList<String>();
				for(int i=0;i<modelUrlLinkList.size();i++){
					urlRewriteRuleList.add(modelUrlLinkList.get(i).get("RULE").toString());
				}
				super.addUrlRules(urlRewriteRuleList);
				try{
					/*
					 *Creating Feed for the added Model 
					 */
					FeedDto feedDto=new FeedDto();
					feedDto.setGuid(super.contextPath+modelLink);
					feedDto.setLink(super.getServerContext(request)+modelLink);
					feedDto.setPubDate(DateUtility.getRFC822Date(new Date()));
					feedDto.setAuthor(EmailConstants.RSS_FEED_THRONGIN_EMAIL_ID+" ("+EmailConstants.RSS_FEED_THRONGIN_AUTHOR+")");
					feedDto.setTitle(modelDto.getModelName()+DeviceConstants.SPEC_PRICE_REVIEWS);
					feedDto.setModelName(modelDto.getModelName());
					feedDto.setModelImageUrl(modelDto.getModelImageUrl());
					feedDto.setDescription(modelDto.getModelDesc());
					
					String[] launchDateParts=modelDto.getModelLaunchDate().split("-");
					Calendar cal=Calendar.getInstance();
					cal.set(Calendar.YEAR, Integer.valueOf(launchDateParts[0]));
					cal.set(Calendar.MONTH, Integer.valueOf(launchDateParts[1]));
					cal.set(Calendar.DATE, Integer.valueOf(launchDateParts[2]));
					feedDto.setLaunchDate(DateUtility.getMonthYear(cal.getTime()));
					if(modelDto.getPrice()!=null)
						feedDto.setPrice(modelDto.getPrice().toString());
					else
						feedDto.setPrice("NOT AVAILABLE");
					String itemXmlNode=FeedConstants.getNewModelItemNode(feedDto);
					feedDto.setItemXmlNode(itemXmlNode);
					super.addRssFeedItem(feedDto);
				}catch(Exception e){
					log.error("Exception Catched while adding Feed,e=",e);
				}
			}
			msg=modelDto.getModelName()+" added successfully!";
			msgType=MsgType.SUCCESS.getMsgType();
		}
		else{
			msg="Error: Unable to add "+modelDto.getModelName();
			msgType=MsgType.ERROR.getMsgType();
			setAddNewModel(true);
			return INPUT;
		}
			
		setAddNewModel(false);
		return SUCCESS;
	}
	
	public String getRssXml(){
		List<ModelDto> allModels=adminBllImpl.fetchAllModels();
		for(ModelDto modelDto:allModels){
			try{
				/*
				 *Creating Feed for the added Model 
				 */
				FeedDto feedDto=new FeedDto();
				feedDto.setGuid(super.contextPath+modelDto.getModelLink());
//				feedDto.setLink(super.getServerContext(request)+modelDto.getModelLink());
				feedDto.setLink("http://gadgets.throngin.com"+modelDto.getModelLink());
				feedDto.setPubDate(DateUtility.getRFC822Date(modelDto.getModifiedDate()));
				feedDto.setAuthor(EmailConstants.RSS_FEED_THRONGIN_EMAIL_ID+" ("+EmailConstants.RSS_FEED_THRONGIN_AUTHOR+")");
				feedDto.setTitle(modelDto.getModelName()+DeviceConstants.SPEC_PRICE_REVIEWS);
				feedDto.setModelName(modelDto.getModelName());
				feedDto.setModelImageUrl(modelDto.getModelImageUrl());
				feedDto.setDescription(modelDto.getModelDesc());
				
				String[] launchDateParts=modelDto.getLaunchDate().toString().split("-");
				Calendar cal=Calendar.getInstance();
				cal.set(Calendar.YEAR, Integer.valueOf(launchDateParts[0]));
				cal.set(Calendar.MONTH, Integer.valueOf(launchDateParts[1]));
				cal.set(Calendar.DATE, Integer.valueOf(launchDateParts[2]));
				feedDto.setLaunchDate(DateUtility.getMonthYear(cal.getTime()));
				feedDto.setPrice(modelDto.getPrice().toString());
				String itemXmlNode=FeedConstants.getNewModelItemNode(feedDto);
				feedDto.setItemXmlNode(itemXmlNode);
				super.addRssFeedItem(feedDto);
			}catch(Exception e){
				log.error("Exception Catched while adding Feed,e=",e);
			}
		}
		return NONE;
	}
	
	public void validate() {
		String methodName=ActionContext.getContext().getActionInvocation().getProxy().getMethod();
		log.debug("|__methodName="+methodName);
		if(StringUtility.isNotBlank(methodName)){
			if(methodName.equals("openAddNewModelBlock")){
				if(modelDto.getManufacturerId()==null || modelDto.getManufacturerId()<0)
					addFieldError("manufacturerId", "Please select Manufacturer!");
			}
			else if(methodName.equals("addNewModel") || "editBasicDetails".equals(methodName)){
				setAddNewModel(true);
				if(modelDto.getModelImage()==null && modelDto.getModelImageUrl()==null)
					addFieldError("modelImage", "Please select either Model Image or image url!");
				else if(modelDto.getModelCategoryId()==null || modelDto.getModelCategoryId().isEmpty())
					addFieldError("modelCategoryId", "Please select Model Category!");
				else if(StringUtility.isNullBlank(modelDto.getModelName()))
					addFieldError("modelName", "Please select Model Name!");
				else if(StringUtility.isNullBlank(modelDto.getModelDesc()))
					addFieldError("modelDesc", "Please enter Model Description!");
				else if(StringUtility.isNotNullBlank(modelDto.getModelDesc()) && modelDto.getModelDesc().length()>4000)
					addFieldError("modelDesc", "Model Description cannot exceed 340 characters!");

			}else if("openEditModelBlock".equals(methodName)){
				if(modelDto.getModelId()==null || modelDto.getModelId()<0)
					addFieldError("modelId", "Please select Model!");
			}
		}
	}
	
	

	@VisitorFieldValidator
	public ModelDto getModel() {
		// TODO Auto-generated method stub
		return modelDto;
	}


	
	
	/*
	 *Getters & Setters 
	 *
	 */
	



	public ModelDto getModelDto() {
		return modelDto;
	}

	public void setModelDto(ModelDto modelDto) {
		this.modelDto = modelDto;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public Boolean getAddNewModel() {
		return addNewModel;
	}

	public void setAddNewModel(Boolean addNewModel) {
		this.addNewModel = addNewModel;
	}

	public AdminBllInterface getAdminBllImpl() {
		return adminBllImpl;
	}

	public void setAdminBllImpl(AdminBllInterface adminBllImpl) {
		this.adminBllImpl = adminBllImpl;
	}

	public Map getSession() {
		return session;
	}

	public void setSession(Map session) {
		this.session = session;
	}

	public String getAddEdit() {
		return addEdit;
	}

	public void setAddEdit(String addEdit) {
		this.addEdit = addEdit;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}




}
