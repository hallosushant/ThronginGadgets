package com.sushant.verma.device;

import com.sushant.verma.common.ResourceLoader.ResourceEnums.ZProps;


public class DeviceConstants {

	public static final Long ACTIVE_STATUS_ID=1L;
	public static final Long INACTIVE_STATUS_ID=2L;
	public static final String Y = "Y";
	public static final String N = "N";
	public static final int ONE = 1;
	public static final int ZERO = 0;
	
	public static final String MESSAGE="MESSAGE";
	public static final String STRUTS_RESULT_NAME="STRUTS_RESULT_NAME";
	public static final Integer MODEL_GALLERY_PAGE_SIZE=Integer.valueOf(ZProps.modelGalleryPageSize.getProperty());
	public static final String FEATURED_MODELS="FEATURED_MODELS";
	public static final String POPULAR_MODELS="POPULAR_MODELS";
	public static final String NEW_MODELS="NEW_MODELS";
	public static final String UPCOMING_MODELS="UPCOMING_MODELS";
	public static final String SPEC_PRICE_REVIEWS = " Specification, Price and Reviews";
}
