package com.sushant.verma.common.dto;

import java.sql.Timestamp;

public class UserDto{

	
	private Long userId;

	private String userName;

	private String userEmail;

	private String userMobile;

	private Long hintQuestionId;

	private String hintAnswer;

	private Timestamp createdDate;

	private String createdBy;

	private Long statusId;

	private String userRole;
	
	private String nickname;
	private String language;
	private String country;
	private byte[] salt;
	private byte[] encPwd;
	private String userPwd;
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Long getUserId(){
	 return userId;
	}
	public String getUserName(){
	 return userName;
	}
	public String getUserEmail(){
	 return userEmail;
	}
	public Long getHintQuestionId(){
	 return hintQuestionId;
	}
	public String getHintAnswer(){
	 return hintAnswer;
	}
	public Timestamp getCreatedDate(){
	 return createdDate;
	}
	public String getCreatedBy(){
	 return createdBy;
	}
	public Long getStatusId(){
	 return statusId;
	}
	
	public void setUserId(Long userId){
	 this.userId = userId;
	}
	public void setUserName(String userName){
	 this.userName = userName;
	}
	public void setUserEmail(String userEmail){
	 this.userEmail = userEmail;
	}
	public void setHintQuestionId(Long hintQuestionId){
	 this.hintQuestionId = hintQuestionId;
	}
	public void setHintAnswer(String hintAnswer){
	 this.hintAnswer = hintAnswer;
	}
	public void setCreatedDate(Timestamp createdDate){
	 this.createdDate = createdDate;
	}
	public void setCreatedBy(String createdBy){
	 this.createdBy = createdBy;
	}
	public void setStatusId(Long statusId){
	 this.statusId = statusId;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public void setOpenid(String identity) {
		// TODO Auto-generated method stub
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public byte[] getSalt() {
		return salt;
	}
	public void setSalt(byte[] salt) {
		this.salt = salt;
	}
	public byte[] getEncPwd() {
		return encPwd;
	}
	public void setEncPwd(byte[] encPwd) {
		this.encPwd = encPwd;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	
}