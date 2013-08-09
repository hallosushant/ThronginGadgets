package com.sushant.verma.common.dto;

import java.io.File;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

public class ModelDto implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long modelId;

	private Long manufacturerId;

	private String modelName;
	
	private Integer price;
	
	private Date launchDate;
	
	private String modelDesc;
	
	private File modelImage;

	private String modelImageUrl;
	
	private String modelVideoLink;

	private Float starRating;

	private Long ratingCount;

	private Timestamp modifiedDate;

	private Long modifiedBy;

	private Long statusId;

	private String modelLaunchDate;
	private String title;
	private Long deviceId;
	private Long modelCount;
	private List<Long> modelCategoryId;
	private Long catgId;
	private byte[] modelImageBytes;
	private String modelLink;
	private List<ListOrderedMap> modelCatgoryList;
	
	public String getModelLink() {
		return modelLink;
	}
	public void setModelLink(String modelLink) {
		this.modelLink = modelLink;
	}
	public byte[] getModelImageBytes() {
		return modelImageBytes;
	}
	public void setModelImageBytes(byte[] modelImageBytes) {
		this.modelImageBytes = modelImageBytes;
	}
	public Long getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}
	public List<Long> getModelCategoryId() {
		return modelCategoryId;
	}
	public void setModelCategoryId(List<Long> modelCategoryId) {
		this.modelCategoryId = modelCategoryId;
	}
	public Long getModelId(){
	 return modelId;
	}
	public Long getManufacturerId(){
	 return manufacturerId;
	}
	public String getModelName(){
	 return modelName;
	}
	
	public File getModelImage(){
	 return modelImage;
	}
	public String getModelVideoLink(){
	 return modelVideoLink;
	}

	public Timestamp getModifiedDate(){
	 return modifiedDate;
	}
	public Long getModifiedBy(){
	 return modifiedBy;
	}
	public Long getStatusId(){
	 return statusId;
	}
	
	public void setModelId(Long modelId){
	 this.modelId = modelId;
	}
	public void setManufacturerId(Long manufacturerId){
	 this.manufacturerId = manufacturerId;
	}
	public void setModelName(String modelName){
	 this.modelName = modelName;
	}
	public void setModelImage(File modelImage){
	 this.modelImage = modelImage;
	}
	public void setModelVideoLink(String modelVideoLink){
	 this.modelVideoLink = modelVideoLink;
	}

	public void setModifiedDate(Timestamp modifiedDate){
	 this.modifiedDate = modifiedDate;
	}
	public void setModifiedBy(Long modifiedBy){
	 this.modifiedBy = modifiedBy;
	}
	public void setStatusId(Long statusId){
	 this.statusId = statusId;
	}
	public String getModelImageUrl() {
		return modelImageUrl;
	}
	public void setModelImageUrl(String modelImageUrl) {
		this.modelImageUrl = modelImageUrl;
	}
	public String getModelDesc() {
		return modelDesc;
	}
	public void setModelDesc(String modelDesc) {
		this.modelDesc = modelDesc;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Date getLaunchDate() {
		return launchDate;
	}
	public void setLaunchDate(Date launchDate) {
		this.launchDate = launchDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getModelLaunchDate() {
		return modelLaunchDate;
	}
	public void setModelLaunchDate(String modelLaunchDate) {
		this.modelLaunchDate = modelLaunchDate;
	}
	public Long getModelCount() {
		return modelCount;
	}
	public void setModelCount(Long modelCount) {
		this.modelCount = modelCount;
	}
	public Float getStarRating() {
		return starRating;
	}
	public void setStarRating(Float starRating) {
		this.starRating = starRating;
	}
	public Long getRatingCount() {
		return ratingCount;
	}
	public void setRatingCount(Long ratingCount) {
		this.ratingCount = ratingCount;
	}
	public Long getCatgId() {
		return catgId;
	}
	public void setCatgId(Long catgId) {
		this.catgId = catgId;
	}
	public List<ListOrderedMap> getModelCatgoryList() {
		return modelCatgoryList;
	}
	public void setModelCatgoryList(List<ListOrderedMap> modelCatgoryList) {
		this.modelCatgoryList = modelCatgoryList;
	}

}