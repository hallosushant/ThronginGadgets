package com.sushant.verma.device.bll;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sushant.verma.common.ZProc.ZProcConstants;
import com.sushant.verma.common.constants.DbConstants;
import com.sushant.verma.common.constants.ZConstants.TitleEnum;
import com.sushant.verma.common.dto.AdvancedSearchDto;
import com.sushant.verma.common.dto.ModelDetailDto;
import com.sushant.verma.common.dto.ModelDto;
import com.sushant.verma.common.dto.RatingDto;
import com.sushant.verma.common.dto.ReviewDto;
import com.sushant.verma.common.dto.SearchResultDto;
import com.sushant.verma.common.email.EmailConstants;
import com.sushant.verma.common.email.EmailServiceInterface;
import com.sushant.verma.common.utility.DateUtility;
import com.sushant.verma.common.utility.StringUtility;
import com.sushant.verma.device.dao.DeviceDao;
import com.sushant.verma.device.dao.ModelGallerySPDao;

public class DeviceBllImpl implements DeviceBllInterface {
	
	private static Logger log=LogManager.getLogger(DeviceBllImpl.class);

	private DeviceDao deviceDao;
	private ModelGallerySPDao modelGallerySPDao;
	private EmailServiceInterface emailService;
	
	@SuppressWarnings("unchecked")
	public List<List<ModelDto>> fetchManufacturerModels(Long deviceId,Long manufacturerId) {
		log.info("@ fetchManufacturerModels | deviceId="+deviceId+" | manufacturerId="+manufacturerId);
		Map manufacturerModelsDBResult=modelGallerySPDao.fetchManufacturerModels(deviceId, manufacturerId);
		
        List<List<ModelDto>> manufacturerModelsList=new ArrayList<List<ModelDto>>();            

        List<ModelDto> popularModels=(List) manufacturerModelsDBResult.get(ZProcConstants.POPULAR_MODELS_RESULT_SET);
		log.debug("popularModels="+popularModels);
		if(!popularModels.isEmpty()){
			Long popularModelsCount=(Long) manufacturerModelsDBResult.get(ZProcConstants.OUT_POPULAR_MODEL_COUNT);
			popularModels.get(0).setModelCount(popularModelsCount);
			log.debug("popularModelsCount="+popularModelsCount+" | dtoVal="+popularModels.get(0).getModelCount());
			manufacturerModelsList.add(popularModels);
		}
		
		List<ModelDto> newModels=(List) manufacturerModelsDBResult.get(ZProcConstants.NEW_MODELS_RESULT_SET);
		log.debug("newModels="+newModels);
		if(!newModels.isEmpty()){
			Long newModelsCount=(Long) manufacturerModelsDBResult.get(ZProcConstants.OUT_NEW_MODEL_COUNT);
			newModels.get(0).setModelCount(newModelsCount);
			log.debug("newModelsCount="+newModelsCount+" | dtoVal="+newModels.get(0).getModelCount());
			manufacturerModelsList.add(newModels);
		}
		
		List<ModelDto> upComingModels=(List) manufacturerModelsDBResult.get(ZProcConstants.UPCOMING_MODELS_RESULT_SET);
		log.debug("upComingModels="+upComingModels);
		if(!upComingModels.isEmpty()){
			Long upComingModelsCount=(Long) manufacturerModelsDBResult.get(ZProcConstants.OUT_UPCOMING_MODEL_COUNT);
			upComingModels.get(0).setModelCount(upComingModelsCount);
			log.debug("upComingModelsCount="+upComingModelsCount+" | dtoVal="+upComingModels.get(0).getModelCount());
			manufacturerModelsList.add(upComingModels);
		}
		
		List<ModelDto> allModels=(List) manufacturerModelsDBResult.get(ZProcConstants.ALL_MODELS_RESULT_SET);
		log.debug("allModels="+allModels);
		if(!allModels.isEmpty()){
			Long allModelsCount=(Long) manufacturerModelsDBResult.get(ZProcConstants.OUT_ALL_MODEL_COUNT);
			allModels.get(0).setModelCount(allModelsCount);
			log.debug("allModelsCount="+allModelsCount+" | dtoVal="+allModels.get(0).getModelCount());
			manufacturerModelsList.add(allModels);
		}
		
		return manufacturerModelsList;
	}
	

	@SuppressWarnings("unchecked")
	public List<ModelDto> moreManufacturerModelsWithCount(Long deviceId, Long manufacturerId,String galleryType, Integer pageSize, Integer pageNo) {
		log.info("deviceId="+deviceId+" | manufacturerId="+manufacturerId+" | galleryType="+galleryType+" | pageSize="+pageSize+" | pageNo="+pageNo);
		Map moreModelsDBResult=modelGallerySPDao.moreManufacturerModelsWithCount(deviceId,manufacturerId,galleryType,pageSize,pageNo);
		List<ModelDto> moreModels=(List<ModelDto>) moreModelsDBResult.get(ZProcConstants.RESULT_SET);
		log.debug("moreModels="+moreModels);
		if(!moreModels.isEmpty()){
			Long moreModelsCount=(Long) moreModelsDBResult.get(ZProcConstants.OUT_TOTAL_MODEL_COUNT);
			moreModels.get(0).setModelCount(moreModelsCount);
			log.debug("moreModelsCount="+moreModelsCount+" | dtoVal="+moreModels.get(0).getModelCount());
			return moreModels;
		}
		return new ArrayList<ModelDto>();
	}	

	public List<ModelDto> moreManufacturerModels(Long deviceId, Long manufacturerId,String galleryType, Integer pageSize, Integer pageNo) {
		
		if(StringUtility.isNotNullBlank(galleryType) && galleryType.equals(TitleEnum.POPULAR.getTitle())){
			return deviceDao.morePopularModels(deviceId, manufacturerId,pageSize,pageNo);
		}
		else if(StringUtility.isNotNullBlank(galleryType) && galleryType.equals(TitleEnum.NEW.getTitle())){
			String limitDate=DateUtility.getDateAfter(new Date(),DateUtility.NINTY_DAYS_BACK, DateUtility.YYYY_MM_DD_FORMAT);
			return deviceDao.moreNewModels(deviceId, manufacturerId,limitDate,pageSize,pageNo);
		}
		else if(StringUtility.isNotNullBlank(galleryType) && galleryType.equals(TitleEnum.UPCOMING.getTitle())){
			return deviceDao.moreUpcomingModels(deviceId, manufacturerId,pageSize,pageNo);
		}
		else{//All Type
			return deviceDao.moreModels(deviceId, manufacturerId,pageSize,pageNo);
		}
	}	
	
	
	public List<List<ModelDto>> fetchCategoryModels(Long deviceId, Long categoryId){
		log.info("@ fetchCategoryModels | deviceId="+deviceId+" | categoryId="+categoryId);
		List<List<ModelDto>> categoryModelsList=modelGallerySPDao.fetchCategoryModels(deviceId, categoryId);
		return categoryModelsList;
	}
	
	@SuppressWarnings("unchecked")
	public List<ModelDto> moreCategoryModelsWithCount(Long deviceId, Long categoryId,String galleryType, Integer pageSize, Integer pageNo) {
		log.info("@ moreCategoryModelsWithCount | deviceId="+deviceId+" | categoryId="+categoryId+" | galleryType="+galleryType+" | pageSize="+pageSize+" | pageNo="+pageNo);
		Map moreModelsDBResult=modelGallerySPDao.moreCategoryModelsWithCount(deviceId,categoryId,galleryType,pageSize,pageNo);
		List<ModelDto> moreModels=(List<ModelDto>) moreModelsDBResult.get(ZProcConstants.RESULT_SET);
		log.debug("moreModels="+moreModels);
		if(!moreModels.isEmpty()){
			Long moreModelsCount=(Long) moreModelsDBResult.get(ZProcConstants.OUT_TOTAL_MODEL_COUNT);
			moreModels.get(0).setModelCount(moreModelsCount);
			log.debug("moreModelsCount="+moreModelsCount+" | dtoVal="+moreModels.get(0).getModelCount());
			return moreModels;
		}
		return new ArrayList<ModelDto>();
	}
	
	public List<ModelDto> moreCategoryModels(Long deviceId, Long categoryId,String galleryType, Integer pageSize, Integer pageNo){
		log.info("@ moreCategoryModels | deviceId="+deviceId+" | categoryId="+categoryId+" | pageSize="+pageSize+" | pageNo="+pageNo);
		if(StringUtility.isNotNullBlank(galleryType) && galleryType.equals(TitleEnum.POPULAR.getTitle())){
			return deviceDao.moreCatgPopularModels(deviceId, categoryId,pageSize,pageNo);
		}
		else if(StringUtility.isNotNullBlank(galleryType) && galleryType.equals(TitleEnum.NEW.getTitle())){
			String limitDate=DateUtility.getDateAfter(new Date(),DateUtility.NINTY_DAYS_BACK, DateUtility.YYYY_MM_DD_FORMAT);
			return deviceDao.moreCatgNewModels(deviceId, categoryId,limitDate,pageSize,pageNo);
		}
		else if(StringUtility.isNotNullBlank(galleryType) && galleryType.equals(TitleEnum.UPCOMING.getTitle())){
			return deviceDao.moreCatgUpcomingModels(deviceId, categoryId,pageSize,pageNo);
		}
		else{//All Type
			return deviceDao.moreCatgModels(deviceId, categoryId,pageSize,pageNo);
		}
		
	}
	
	public List<ReviewDto> fetchModelReviews(Long modelId){
		if(modelId!=null)
			return deviceDao.fetchModelReviews(modelId);
		else
			return null;
	}
	
	public List<ReviewDto> fetchDeviceLatestReviews(Long deviceId){
		if(deviceId!=null)
			return deviceDao.fetchDeviceLatestReviews(deviceId);
		else
			return null;
	}
	
	public void rateThis(RatingDto ratingDto){
		deviceDao.rateThis(ratingDto);
	}
	

	public Integer saveModelReview(ReviewDto reviewDto) {
		Integer modelReviewCount= deviceDao.saveModelReview(reviewDto);
		
		/*
		 * Sending Email for Newly added Review
		 */
		String fromEmail=EmailConstants.ADMIN_THRONGIN_EMAIL_ID;
		String[] toEmail=new String[2];
		toEmail[0]=EmailConstants.SUPPORT_THRONGIN_EMAIL_ID;
		toEmail[1]=EmailConstants.SUSHANT_THRONGIN_EMAIL_ID;
		String emailBody=EmailConstants.getNewModelReviewHtmlEmailContent(reviewDto);
		String emailSubject=EmailConstants.SUBJECT_HEADER_NEW_REVIEW+reviewDto.getModelName();

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
		
		return modelReviewCount;
	}


	public List<ModelDetailDto> fetchModelPhotos(Long modelId) {
		if(modelId!=null)
			return deviceDao.fetchModelPhotos(modelId);
		else
			return null;

	}

	public List<SearchResultDto> zModelSearch(String searchStr,Long deviceId){
		log.debug("zModelSearch | search Str="+searchStr+" | deviceId="+deviceId);
		if(searchStr!=null){
			return deviceDao.zModelSearch(searchStr,deviceId);
		}else
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<ModelDto> mobileAdvancedSearch(AdvancedSearchDto advancedSearchDto,Integer pageSize, Integer pageNo){
		StringBuilder requiredCatgId=new StringBuilder();
		StringBuilder notRequiredCatgId=new StringBuilder();
		
		String deviceId=advancedSearchDto.getDId().toString();
		//String marketTrend=advancedSearchDto.getMarketTrend();
		
		/*
		 * Network
		 */
		String network=advancedSearchDto.getNetwork();
		if(StringUtility.isNotNullBlank(network))
			requiredCatgId.append(network).append(",");			
		
		/*
		 * Mobile Brands
		 */
		StringBuilder mobileManufacturerBuffer=new StringBuilder();
		String[] mobileManufacturer=advancedSearchDto.getMobileManufacturer();
		if(null!=mobileManufacturer && mobileManufacturer.length>0)
			for(String i:mobileManufacturer)
				mobileManufacturerBuffer.append(i).append(",");

		/*
		 * Mobile Price band
		 */
		Long mobilePriceBand=advancedSearchDto.getMobilePriceBand();
		if(null!=mobilePriceBand && mobilePriceBand.longValue()>0)
			requiredCatgId.append(mobilePriceBand.toString()).append(",");

		/*
		 * Mobile Body
		 */
		String mobileBody=advancedSearchDto.getMobileBody();
		if(StringUtility.isNotNullBlank(mobileBody))
			requiredCatgId.append(mobileBody).append(",");			

		/*
		 * TouchScreen
		 */
		String touchScreen=advancedSearchDto.getTouchscreen();
		if(StringUtility.isNotNullBlank(touchScreen)){
			if(touchScreen.equals(DbConstants.TOUCHSCREEN_CATG_ID.toString())){
				requiredCatgId.append(touchScreen).append(",");
				/*
				 * Touchscreen Type
				 */
				String[] touchScreenType=advancedSearchDto.getTouchscreenType();
				if(null!=touchScreenType && touchScreenType.length>0)
					for(String i:touchScreenType)
						requiredCatgId.append(i).append(",");
			}
			else if(touchScreen.equals(DbConstants.NO_TOUCHSCREEN_CATG_ID.toString())){
				notRequiredCatgId.append(touchScreen).append(",");
			}
		}

		/*
		 * Mobile OS
		 */
		String mobileOS=advancedSearchDto.getMobileOS();
		if(StringUtility.isNotNullBlank(mobileOS))
			requiredCatgId.append(mobileOS).append(",");
		
		/*
		 * Data Connectivity
		 */
		String[] dataConnectivity=advancedSearchDto.getDataConnectivity();
		if(null!=dataConnectivity && dataConnectivity.length>0)
			for(String i:dataConnectivity)
				requiredCatgId.append(i).append(",");
		
		/*
		 * Camera
		 */
		String camera=advancedSearchDto.getCamera();
		if(StringUtility.isNotNullBlank(camera)){
			if(camera.equals(DbConstants.CAMERA_MOBILE_CATG_ID.toString())){
				requiredCatgId.append(camera).append(",");
				/*
				 * Camera Type
				 */
				String[] cameraType=advancedSearchDto.getCameraType();
				if(null!=cameraType && cameraType.length>0){
					for(String i:cameraType)
						requiredCatgId.append(i).append(",");
					/*
					 * Primary Camera Type
					 */
					Long primaryCameraType=advancedSearchDto.getPrimaryCameraType();
					if(null!=primaryCameraType)
						requiredCatgId.append(primaryCameraType.toString()).append(",");
				}
			}
			else if(camera.equals(DbConstants.NO_CAMERA_MOBILE_CATG_ID.toString())){
				notRequiredCatgId.append(camera).append(",");
			}
		}
		
		/*
		 * Screen Display Size
		 */
		Long scrDisplaySize=advancedSearchDto.getScreenDisplaySize();
		if(null!=scrDisplaySize && scrDisplaySize.longValue()>0)
			requiredCatgId.append(scrDisplaySize.toString()).append(",");

		/*
		 * Music
		 */
		String[] music=advancedSearchDto.getMusic();
		if(null!=music && music.length>0)
			for(String i:music)
				requiredCatgId.append(i).append(",");

		/*
		 * Misc
		 */
		String[] misc=advancedSearchDto.getMisc();
		if(null!=misc && misc.length>0)
			for(String i:misc)
				requiredCatgId.append(i).append(",");
		
		
		String mobileManufacturerStr=mobileManufacturerBuffer.toString();
		if(StringUtility.isNotNullBlank(mobileManufacturerStr)){
			int mobileManufacturerStrLength=mobileManufacturerStr.length();
			mobileManufacturerStr=mobileManufacturerStr.substring(0, mobileManufacturerStrLength-1);
		}
		
		String requiredCatgIdStr=requiredCatgId.toString();
		Integer requiredCatgIdLength=new Integer(0);
		if(StringUtility.isNotNullBlank(requiredCatgIdStr)){
			int requiredCatgIdStrLength=requiredCatgIdStr.length();
			requiredCatgIdStr=requiredCatgIdStr.substring(0, requiredCatgIdStrLength-1);
			requiredCatgIdLength=requiredCatgIdStr.split(",").length;
		}

		String notRequiredCatgIdStr=notRequiredCatgId.toString();
		if(StringUtility.isNotNullBlank(notRequiredCatgIdStr)){
			int notRequiredCatgIdStrLength=notRequiredCatgIdStr.length();
			notRequiredCatgIdStr=notRequiredCatgIdStr.substring(0, notRequiredCatgIdStrLength-1);
		}else
			notRequiredCatgIdStr=null;
		
		log.debug("deviceId="+deviceId+"\n pageNo="+pageNo+"\nmobileManufacturerStr="+mobileManufacturerStr+"\nrequiredCatgIdStr="+requiredCatgIdStr+"\nrequiredCatgIdLength="+requiredCatgIdLength+"\nnotRequiredCatgIdStr="+notRequiredCatgIdStr);
		Map results =modelGallerySPDao.mobileAdvancedSearch(deviceId,pageSize,pageNo,mobileManufacturerStr,requiredCatgIdStr,requiredCatgIdLength,notRequiredCatgIdStr);
        List<ModelDto> modelList=(List) results.get(ZProcConstants.SEARCHED_MODELS_RESULT_SET);
        log.debug("modelList="+modelList);
		if(!modelList.isEmpty()){
			Long modelsCount=(Long) results.get(ZProcConstants.OUT_TOTAL_MODEL_COUNT);
			modelList.get(0).setModelCount(modelsCount);
			log.debug("modelsCount="+modelsCount+" | dtoVal="+modelList.get(0).getModelCount());
			return modelList;
		}
		return new ArrayList<ModelDto>(); 
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ModelDto> quickSearch(Long deviceId, String searchStr,Integer pageSize, Integer pageNo){
		log.debug("@quickSearch | deviceId="+deviceId+"\n searchStr="+searchStr+"\n pageSize="+pageSize+"\n pageNo="+pageNo);
		Map results =modelGallerySPDao.quickSearch(deviceId,searchStr,pageSize,pageNo);
        List<ModelDto> modelList=(List) results.get(ZProcConstants.SEARCHED_MODELS_RESULT_SET);
        log.debug("modelList="+modelList);
		if(!modelList.isEmpty()){
			Long modelsCount=(Long) results.get(ZProcConstants.OUT_TOTAL_MODEL_COUNT);
			modelList.get(0).setModelCount(modelsCount);
			log.debug("modelsCount="+modelsCount+" | dtoVal="+modelList.get(0).getModelCount());
			return modelList;
		}
		return new ArrayList<ModelDto>(); 
		
	}


	public List<ModelDto> fetchRelatedCatgModels(Long deviceId,Long modelId, String tagsCatg) {
		
		return deviceDao.fetchRelatedCatgModels(deviceId,modelId, tagsCatg);
	}

	public List<ModelDto> fetchTopRatedModels(Long deviceId) {
		return deviceDao.fetchTopRatedModels(deviceId);
	}
	
	/*
	 * Getters & Setters
	 */
	
	public DeviceDao getDeviceDao() {
		return deviceDao;
	}

	public void setDeviceDao(DeviceDao deviceDao) {
		this.deviceDao = deviceDao;
	}
	public ModelGallerySPDao getModelGallerySPDao() {
		return modelGallerySPDao;
	}
	public void setModelGallerySPDao(ModelGallerySPDao modelGallerySPDao) {
		this.modelGallerySPDao = modelGallerySPDao;
	}
	public EmailServiceInterface getEmailService() {
		return emailService;
	}
	public void setEmailService(EmailServiceInterface emailService) {
		this.emailService = emailService;
	}

}
