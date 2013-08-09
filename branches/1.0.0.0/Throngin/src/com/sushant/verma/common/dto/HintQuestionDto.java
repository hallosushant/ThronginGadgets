package com.sushant.verma.common.dto;

import java.sql.Timestamp;

public class HintQuestionDto{

	
	private Long hintQuestionId;

	private String hintQuestion;

	private Timestamp createdDate;

	private Long statusId;

	
	public Long getHintQuestionId(){
	 return hintQuestionId;
	}
	public String getHintQuestion(){
	 return hintQuestion;
	}
	public Timestamp getCreatedDate(){
	 return createdDate;
	}
	public Long getStatusId(){
	 return statusId;
	}
	
	public void setHintQuestionId(Long hintQuestionId){
	 this.hintQuestionId = hintQuestionId;
	}
	public void setHintQuestion(String hintQuestion){
	 this.hintQuestion = hintQuestion;
	}
	public void setCreatedDate(Timestamp createdDate){
	 this.createdDate = createdDate;
	}
	public void setStatusId(Long statusId){
	 this.statusId = statusId;
	}
}