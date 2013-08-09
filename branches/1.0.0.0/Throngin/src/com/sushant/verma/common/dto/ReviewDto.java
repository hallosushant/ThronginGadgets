package com.sushant.verma.common.dto;

import java.sql.Timestamp;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;

public class ReviewDto{

	
	private Long reviewId;

	private Long modelId;

	private Long fileId;

	private String title;

	private String review;

	private String author;

	private String reviewDate;

	private String refLink;

	private String imageLink;

	private String videoLink;

	private Timestamp modifiedDate;

	private Long modifiedBy;

	private Float starRating;

	private Long ratingCount;

	private Long deviceId;
	private Long manufacturerId;
	private String modelName;
	private String modelImageUrl;
	private Long statusId;
	private String approve;
	private String urlRewriteRule;
	
	
	public String getUrlRewriteRule() {
		return urlRewriteRule;
	}
	public void setUrlRewriteRule(String urlRewriteRule) {
		this.urlRewriteRule = urlRewriteRule;
	}
	public String getApprove() {
		return approve;
	}
	public void setApprove(String approve) {
		this.approve = approve;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public Long getReviewId(){
	 return reviewId;
	}
	public Long getModelId(){
	 return modelId;
	}
	public Long getFileId(){
	 return fileId;
	}
	@RequiredStringValidator(fieldName="title",message="Review Title is Mandatory",trim=true,shortCircuit=true)
	@StringLengthFieldValidator(fieldName="title",trim=true,minLength="10",maxLength="100",key="key.review.title.lengthValidation",message="Review Title should contain atleast 10 characters and max 100 Characters!",shortCircuit=true)
	public String getTitle(){
	 return title;
	}
	@RequiredStringValidator(fieldName="review",message="Detailed Review is Mandatory",trim=true,shortCircuit=true)
	@StringLengthFieldValidator(fieldName="review",trim=true,minLength="10",maxLength="20000",key="key.review.lengthValidation",message="Detailed Review should contain atleast 10 characters and max 20000 Characters!",shortCircuit=true)
	public String getReview(){
	 return review;
	}
	public String getAuthor(){
	 return author;
	}
	public String getReviewDate(){
	 return reviewDate;
	}
	public String getRefLink(){
	 return refLink;
	}
	public String getImageLink(){
	 return imageLink;
	}
	public String getVideoLink(){
	 return videoLink;
	}
	public Timestamp getModifiedDate(){
	 return modifiedDate;
	}
	public Long getModifiedBy(){
	 return modifiedBy;
	}
	
	public void setReviewId(Long reviewId){
	 this.reviewId = reviewId;
	}
	public void setModelId(Long modelId){
	 this.modelId = modelId;
	}
	public void setFileId(Long fileId){
	 this.fileId = fileId;
	}
	public void setTitle(String title){
	 this.title = title;
	}
	public void setReview(String review){
	 this.review = review;
	}
	public void setAuthor(String author){
	 this.author = author;
	}
	public void setReviewDate(String reviewDate){
	 this.reviewDate = reviewDate;
	}
	public void setRefLink(String refLink){
	 this.refLink = refLink;
	}
	public void setImageLink(String imageLink){
	 this.imageLink = imageLink;
	}
	public void setVideoLink(String videoLink){
	 this.videoLink = videoLink;
	}
	public void setModifiedDate(Timestamp modifiedDate){
	 this.modifiedDate = modifiedDate;
	}
	public void setModifiedBy(Long modifiedBy){
	 this.modifiedBy = modifiedBy;
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
	public Long getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}
	public Long getManufacturerId() {
		return manufacturerId;
	}
	public void setManufacturerId(Long manufacturerId) {
		this.manufacturerId = manufacturerId;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getModelImageUrl() {
		return modelImageUrl;
	}
	public void setModelImageUrl(String modelImageUrl) {
		this.modelImageUrl = modelImageUrl;
	}
	
}