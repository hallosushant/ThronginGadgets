package com.sushant.verma.common.email;

import java.util.List;

import com.sushant.verma.common.dto.ModelDto;
import com.sushant.verma.common.dto.ReviewDto;

public class EmailConstants {

	public static final String SUPPORT_THRONGIN_EMAIL_ID="support@throngin.com";
	public static final String ADMIN_THRONGIN_EMAIL_ID="admin@throngin.com";
	public static final String CONTACT_THRONGIN_EMAIL_ID="contact@throngin.com";
	public static final String SUSHANT_THRONGIN_EMAIL_ID="sushantverma@throngin.com";
	public static final String RSS_FEED_THRONGIN_EMAIL_ID="rss.feed@throngin.com";
	public static final String RSS_FEED_THRONGIN_AUTHOR="ThrongIn Feed";
	public static final String HOST_ADDRESS_SMTP="smtp.gmail.com";
	public static final int CONNECTION_TIMEOUT_MSEC=5000;
	public static final int IO_TIMEOUT_MSEC = 5000;
	
	
	
	
	
	
	public static final String SUBJECT_HEADER_THRONGIN=" [Throngin Gadgets] ";
	public static final String SUBJECT_HEADER_NEW_MODEL=" [Throngin Gadgets - New Model] ";
	public static final String SUBJECT_HEADER_UPDATE_MODEL=" [Throngin Gadgets - Update Model] ";
	public static final String SUBJECT_HEADER_MODEL_DETAILS=" [Throngin Gadgets - New Model Details] ";
	public static final String SUBJECT_HEADER_EDIT_MODEL_DETAILS=" [Throngin Gadgets - Edited Model Details] ";
	public static final String SUBJECT_HEADER_NEW_REVIEW=" [Throngin Gadgets - New Review] ";
	public static final String SUBJECT_CONFIRM_REGISTRATION=" Confirm Registration";
	
	public static final String FOOTER_EMAIL_DONOT_REPLY="<br/><br/><div style='color: #999999; text-align: center;'><span style='font-size: x-small;'>You are receiving this email because you have registered yourself at <a href='http://www.throngin.com'>ThrongIn</a>.<br/>Please do not reply to this mail.</span></div>";
	
	public static final String CLICK_HERE="click here";

	
	public static final String getConfirmRegistrationHtmlEmailContent(String url) {
		
		StringBuilder emailContent=new StringBuilder();
		emailContent.append("<p>Dear User, Your profile has been created at Throngin.</p>");
		emailContent.append("<br/>Please "+getAnchorLink(url,CLICK_HERE)+" to activate your account (Or) copy and paste the following url into your browser to activate your account:<br/><br/>");
		emailContent.append(url);
		emailContent.append("<br/><br/>All communicaton will be sent to this email. Please ensure that the email address is correct and the mailbox is not full.");
		emailContent.append("<br/>Please verify that you are able to login by visiting the site and using your emailID and password. In the event that you face any issue, please send an email to "+SUPPORT_THRONGIN_EMAIL_ID);
		emailContent.append("<br/><br/>If you did not register with the website and have received this email, it may mean that somebody else has registered on your behalf. Please send an email to "+SUPPORT_THRONGIN_EMAIL_ID);
		emailContent.append(" requesting that the registration with this email address be removed.");
		emailContent.append("<br/><br/>Thanks & Regards");
		emailContent.append("<br/>Throngin Administrator");
		emailContent.append(FOOTER_EMAIL_DONOT_REPLY);
		return emailContent.toString();
	}
	
	public static final String getNewAddedModelHtmlEmailContent(ModelDto modelDto) {
		
		StringBuilder emailContent=new StringBuilder();
		emailContent.append("<p>A new Model added at ThrongIn Gadgets.</p>");
		emailContent.append("<br/>Please find below the details of the same :<br/><br/>");
		emailContent.append("<br/><br/>Model Name : "+modelDto.getModelName());
		emailContent.append("<br/><br/>Model Description : "+modelDto.getModelDesc());
		emailContent.append("<br/><br/>Model Launch Date : "+modelDto.getModelLaunchDate());
		emailContent.append("<br/><br/>Model Price : "+modelDto.getPrice());
		emailContent.append("<br/><br/>Model Image : "+getImage(modelDto.getModelImageUrl(),modelDto.getModelName(),modelDto.getModelName()));
		emailContent.append("<br/><br/>Requesting you to please verify the above details. ");
		emailContent.append("<br/>Thanks & Regards");
		emailContent.append("<br/>Throngin Administrator");
		emailContent.append(FOOTER_EMAIL_DONOT_REPLY);
		return emailContent.toString();
	}

	public static final String getModelDetailsHtmlEmailContent(String modelName,List<String> detailValue) {
		
		StringBuilder emailContent=new StringBuilder();
		emailContent.append("<p>Model Details assigned for "+modelName+" at ThrongIn Gadgets.</p>");
		emailContent.append("<br/>Please find below the details of the same :<br/><br/><ul>");
		for(String detail : detailValue){
			if(detail.contains("http"))
				emailContent.append("<li>Model Image : "+getImage(detail,modelName,modelName)+"</li>");
			else
				emailContent.append("<li>"+detail+"</li>");
		}
		
		emailContent.append("</ul><br/><br/>Requesting you to please verify the above details. ");
		emailContent.append("<br/>Thanks & Regards");
		emailContent.append("<br/>Throngin Administrator");
		emailContent.append(FOOTER_EMAIL_DONOT_REPLY);
		return emailContent.toString();
	}

	public static String getNewModelReviewHtmlEmailContent(ReviewDto reviewDto) {
		StringBuilder emailContent=new StringBuilder();
		emailContent.append("<p>A new Review added at ThrongIn Gadgets for "+reviewDto.getModelName()+" by "+reviewDto.getAuthor()+"</p>");
		emailContent.append("<br/>Please find below the details of the same :<br/><br/>");
		emailContent.append("<br/><br/>Model Name : "+reviewDto.getModelName());
		emailContent.append("<br/><br/>Review Title : "+reviewDto.getTitle());
		emailContent.append("<br/><br/>Detailed Review : "+reviewDto.getReview());
		emailContent.append("<br/><br/>Author : "+reviewDto.getAuthor());
		emailContent.append("<br/><br/>Requesting you to please verify & approve the above details. ");
		emailContent.append("<br/>Thanks & Regards");
		emailContent.append("<br/>Throngin Administrator");
		emailContent.append(FOOTER_EMAIL_DONOT_REPLY);
		return emailContent.toString();
	}
	
	public static final String getAnchorLink(String url,String displayLink){
		String anchorLink="<a href=\""+url+"\">"+displayLink+"</a>";
		System.out.println("|__anchorLink=="+anchorLink);
		return anchorLink;
	}

	public static final String getImage(String url,String title,String altText){
		String image="<img src=\""+url+"\" alt=\""+altText+"\" title=\""+title+"\">";
		System.out.println("|__imageLink=="+image);
		return image;
	}


	public static final String getUpdatedModelHtmlEmailContent(ModelDto modelDto) {
		
		StringBuilder emailContent=new StringBuilder();
		emailContent.append("<p>A Model updated at ThrongIn Gadgets.</p>");
		emailContent.append("<br/>Please find below the details of the same :<br/><br/>");
		emailContent.append("<br/><br/>Model Name : "+modelDto.getModelName());
		emailContent.append("<br/><br/>Model Description : "+modelDto.getModelDesc());
		emailContent.append("<br/><br/>Model Launch Date : "+modelDto.getModelLaunchDate());
		emailContent.append("<br/><br/>Model Price : "+modelDto.getPrice());
		emailContent.append("<br/><br/>Model Image : "+getImage(modelDto.getModelImageUrl(),modelDto.getModelName(),modelDto.getModelName()));
		emailContent.append("<br/><br/>Requesting you to please verify the above details. ");
		emailContent.append("<br/>Thanks & Regards");
		emailContent.append("<br/>Throngin Administrator");
		emailContent.append(FOOTER_EMAIL_DONOT_REPLY);
		return emailContent.toString();
	}
}
