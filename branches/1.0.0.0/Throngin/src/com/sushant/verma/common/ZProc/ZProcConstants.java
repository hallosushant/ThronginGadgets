package com.sushant.verma.common.ZProc;

public class ZProcConstants {
	/**
	 * refers to all the Stored Procedure names used in DB
	 */
	public static final String SP_AUTHENTICATE_USER = "sp_authenticateUser";
	public static final String SP_REGISTER_USER = "sp_registerUser";
	public static final String SP_CONFIRM_USER_REGISTRATION = "sp_confirmUserRegistration";
	public static final String SP_AUTHENTICATE_OPEN_USER = "sp_authenticateOpenUser";
	
	public static final String SP_MANUFACTURER_MODELS = "sp_manufacturerModels";//without count
	public static final String SP_MANUFACTURER_MODELS_COUNT = "sp_manufacturerModels_count";//with count
	public static final String SP_CATEGORY_MODELS = "sp_categoryModels";//without count
	public static final String SP_CATEGORY_MODELS_COUNT = "sp_categoryModels_count";//with count
	public static final String SP_MORE_MANUFACTURER_MODELS_COUNT = "sp_moreManufacturerModels_count";//with count
	public static final String SP_MORE_CATEGORY_MODELS_COUNT="sp_moreCategoryModels_count";//with count
	public static final String SP_DEVICE_MODELS_COUNT = "sp_deviceModels_count";//with count
	public static final String SP_MORE_DEVICE_MODELS_COUNT = "sp_moreDeviceModels_count";//with count
	
	public static final String SP_MODEL_DETAILS="sp_modelDetails";
	public static final String SP_RATE_THIS="sp_rateThis";
	
	public static final String SP_MODELS_LIST = "sp_modelsList";//with count
	public static final String SP_APPROVE_REVIEW = "sp_approveReview";//returns url rewrite rules  
	public static final String SP_MOBILE_ADVANCED_SEARCH = "sp_mobileAdvancedSearch";
	public static final String SP_QUICK_SEARCH="sp_quickSearch";

	/**
	 * refers to all in/out/in_out parameters used in Stored Procedures definition
	 */
	public static final String IN_USER_ID = "@in_userId";
	public static final String IN_USER_NAME = "@in_userName";
	public static final String IN_USER_EMAIL = "@in_userEmail";
    public static final String IN_USER_PWD="@in_password";
    public static final String IN_USER_SALT="@in_salt";
    public static final String IN_USER_ENC_PWD="@in_encPassword";
    public static final String IN_HINT_QUESTION_ID="@in_hintQuestionId";
    public static final String IN_HINT_ANSWER="@in_hintAnswer";
    public static final String IN_STATUS_ID="@in_statusId";	    
    public static final String IN_MANUFACTURER_ID = "@in_manufacturerId";
    public static final String IN_MODEL_ID = "@in_modelId";
    public static final String IN_MODEL_NAME = "@in_modelName";
    public static final String IN_DEVICE_ID = "@in_deviceId";
    public static final String IN_MODEL_CATEGORY_ID = "@in_modelCategoryId";
    public static final String IN_MODEL_IMAGE = "@in_modelImage";
	public static final String IN_GALLERY_TYPE =  "@in_galleryType";
	public static final String IN_PAGE_SIZE =  "@in_pageSize";
	public static final String IN_PAGE_NO =  "@in_pageNo";
	public static final String IN_STAR_RATING =  "@in_starRating";
	public static final String IN_REVIEW_ID = "@in_reviewId";
	public static final String IN_MARKET_TREND = "@in_marketTrend";
	public static final String IN_MOBILE_MANUFACTURER = "@in_mobileManufacturer";
	public static final String IN_NETWORK = "@in_network";
	public static final String IN_MOBILE_PRICE_BAND = "@in_mobilePriceBand";
	public static final String IN_MOBILE_BODY = "@in_mobileBody";
	public static final String IN_TOUCHSCREEN = "@in_touchscreen";
	public static final String IN_TOUCH_SCREEN_TYPE = "@in_touchscreenType";
	public static final String IN_MOBILE_OS = "@in_mobileOS";
	public static final String IN_DATA_CONNECTIVITY = "@in_dataConnectivity";
	public static final String IN_CAMERA = "@in_camera";
	public static final String IN_CAMERA_TYPE = "@in_cameraType";
	public static final String IN_PRIMARY_CAMERA_TYPE = "@in_primaryCameraType";
	public static final String IN_MIN_DISPLAY_SIZE = "@in_minDisplaySize";
	public static final String IN_MAX_DISPLAY_SIZE = "@in_maxDisplaySize";
	public static final String IN_MUSIC = "@in_music";
	public static final String IN_MISC = "@in_misc";
	public static final String IN_REQ_CATG_ID = "@in_reqCatgId";
	public static final String IN_NOT_REQ_CATG_ID = "@in_notReqCatgId";
	public static final String IN_REQ_CATG_ID_LENGTH = "@in_requiredCatgIdLength";
	public static final String IN_SEARCH_STR="@in_searchStr";
	public static final String IN_REVIEW_TITLE="@in_title";
	public static final String IN_REVIEW_DESC="@in_review";

    public static final String OUT_POPULAR_MODEL_COUNT="@out_popularModelCount";
	public static final String OUT_NEW_MODEL_COUNT="@out_newModelCount";
	public static final String OUT_UPCOMING_MODEL_COUNT="@out_upcomingModelCount";
	public static final String OUT_ALL_MODEL_COUNT="@out_allModelCount";
	public static final String OUT_TOTAL_MODEL_COUNT = "@out_totalModelCount";
    public static final String OUT_USER_EXISTS="@out_userExists";
    public static final String OUT_USER_ID = "@out_userId";
    public static final String OUT_USER_NAME = "@out_userName";
    public static final String OUT_USER_EMAIL = "@out_userEmail";
    public static final String OUT_STATUS_ID = "@out_statusId";
    public static final String OUT_IS_REGISTRATION_SUCCESSFUL = "@out_isRegistrationSuccessful";

    /**
     * refers to other references with Stored Procedure calling
     */
	public static final String RESULT_SET = "resultSet";
    public static final String STATUS_Y="Y";
    public static final String STATUS_N="N";
	public static final String UPDATE_COUNT = "#update-count-1";
	
	/**
     * refers to Result Set Names
     */
	public static final String REVIEW_URL_RULES_RESULT_SET = "reviewUrlRulesResultSet";
	public static final String POPULAR_MODELS_RESULT_SET = "popularModelsResultSet";
	public static final String NEW_MODELS_RESULT_SET = "newModelsResultSet";
	public static final String UPCOMING_MODELS_RESULT_SET = "upcomingModelsResultSet";
	public static final String ALL_MODELS_RESULT_SET = "allModelsResultSet";
	public static final String MODEL_BASIC_INFO_RESULT_SET = "modelBasicInfoResultSet";
	public static final String MODEL_PICTURES_RESULT_SET = "modelPicturesResultSet";
	public static final String MODEL_DETAILS_RESULT_SET = "modelDetailsResultSet";
	public static final String MODEL_TAGS_RESULT_SET = "modelTagsResultSet";
	public static final String SEARCHED_MODELS_RESULT_SET = "searchedModelsResultSet";
	
}
