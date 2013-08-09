package com.sushant.verma.common.dto;

import java.io.Serializable;
import java.util.List;


public class AdvancedSearchDto implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String searchType;
	private Long dId;
	private Long mId;
	private String marketTrend;
	private String[] mobileManufacturer;
	private String network;
	private Long mobilePriceBand;
	private String mobileBody;
	private String touchscreen;
	private String[] touchscreenType;
	private String mobileOS;
	private String[] dataConnectivity;
	private String camera;
	private String[] cameraType;
	private Long primaryCameraType;
	private Long screenDisplaySize;
	private String[] music;
	private String[] misc;
	private List<ModelDto> modelList;
	private Integer p;
	private Long tc; 
	private boolean showMore;

	public Long getDId() {
		return dId;
	}
	public void setDId(Long dId) {
		this.dId = dId;
	}
	public Long getMId() {
		return mId;
	}
	public void setMId(Long mId) {
		this.mId = mId;
	}
	public String getMarketTrend() {
		return marketTrend;
	}
	public void setMarketTrend(String marketTrend) {
		this.marketTrend = marketTrend;
	}
	public String[] getMobileManufacturer() {
		return mobileManufacturer;
	}
	public void setMobileManufacturer(String[] mobileManufacturer) {
		this.mobileManufacturer = mobileManufacturer;
	}
	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	public Long getMobilePriceBand() {
		return mobilePriceBand;
	}
	public void setMobilePriceBand(Long mobilePriceBand) {
		this.mobilePriceBand = mobilePriceBand;
	}
	public String getMobileBody() {
		return mobileBody;
	}
	public void setMobileBody(String mobileBody) {
		this.mobileBody = mobileBody;
	}
	public String getTouchscreen() {
		return touchscreen;
	}
	public void setTouchscreen(String touchscreen) {
		this.touchscreen = touchscreen;
	}
	public String[] getTouchscreenType() {
		return touchscreenType;
	}
	public void setTouchscreenType(String[] touchscreenType) {
		this.touchscreenType = touchscreenType;
	}
	public String getMobileOS() {
		return mobileOS;
	}
	public void setMobileOS(String mobileOS) {
		this.mobileOS = mobileOS;
	}
	public String[] getDataConnectivity() {
		return dataConnectivity;
	}
	public void setDataConnectivity(String[] dataConnectivity) {
		this.dataConnectivity = dataConnectivity;
	}
	public String getCamera() {
		return camera;
	}
	public void setCamera(String camera) {
		this.camera = camera;
	}
	public String[] getCameraType() {
		return cameraType;
	}
	public void setCameraType(String[] cameraType) {
		this.cameraType = cameraType;
	}
	public Long getPrimaryCameraType() {
		return primaryCameraType;
	}
	public void setPrimaryCameraType(Long primaryCameraType) {
		this.primaryCameraType = primaryCameraType;
	}
	public String[] getMusic() {
		return music;
	}
	public void setMusic(String[] music) {
		this.music = music;
	}
	public String[] getMisc() {
		return misc;
	}
	public void setMisc(String[] misc) {
		this.misc = misc;
	}
	public Long getScreenDisplaySize() {
		return screenDisplaySize;
	}
	public void setScreenDisplaySize(Long screenDisplaySize) {
		this.screenDisplaySize = screenDisplaySize;
	}
	public List<ModelDto> getModelList() {
		return modelList;
	}
	public void setModelList(List<ModelDto> modelList) {
		this.modelList = modelList;
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
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	
	
	
}