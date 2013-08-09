<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="com.ckeditor.*" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<% String path=request.getContextPath(); %>
<%@page import="com.ckeditor.CKEditorReplaceTag"%><html>
<% 
	CKEditorConfig ckSettings = new CKEditorConfig();
	ckSettings.addConfigValue("toolbar","[['Source'],{name:'basicstyles',items:['Bold','Italic','Underline','Strike','-','RemoveFormat']},{name:'paragraph',items:['NumberedList','BulletedList','-','Blockquote','-','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock']},{name:'insert',items:['Link','Unlink','-','HorizontalRule','Smiley']},{name:'document',items:['Maximize','Preview','Print','-','SpellChecker']}]");
	ckSettings.addConfigValue("uiColor" , "#eef");
	ckSettings.addConfigValue("disableObjectResizing" , true);
	ckSettings.addConfigValue("forcePasteAsPlainText" , true);
	ckSettings.addConfigValue("scayt_autoStartup" , true);
	ckSettings.addConfigValue("resize_dir" , "vertical");
%>
<!-- Write Review -->
<s:i18n name="com.sushant.verma.admin">
<div class="modelDetail">
	<div class="modelHeader">
		<div class="modelTitle">Approve Review <SPAN class="modelNameHeader"><s:property value="%{reviewDto.modelName}"/></SPAN></div>
	</div>
	<s:if test="%{msg!=null}">
	<div class='<s:property value="%{msgType}"/>'>
		<s:property value="%{msg}" escapeHtml="false"/>
	</div>
	</s:if>
	<s:actionmessage/>
	<s:actionerror/>
	<s:fielderror/>
<s:if test="%{deviceInactiveReviews.size()>0}">	
	<div id="reviewForm">
		<div id="reviewVeil" style="display: none;">
			<div class="info"><s:text name="text.signin.to.review"></s:text></div>
			<a href="#loginBlock" name="modal" shape="rect" class="reviewVeilSignIn">SignIn</a>
		</div>
		<s:form name="approveReviewForm" id="approveReviewForm" theme="simple">
			<s:hidden name="mId" value="%{reviewDto.modelId}"></s:hidden>
			<s:hidden name="modelId" value="%{reviewDto.modelId}"></s:hidden>
			<s:hidden name="dId" id="deviceId" value="%{reviewDto.deviceId}"></s:hidden>
			<s:hidden name="bId" id="brandId" value="%{reviewDto.manufacturerId}"></s:hidden>
			<s:hidden name="rId" id="reviewId" value="%{reviewDto.reviewId}"></s:hidden>
			<s:hidden name="deviceId" value="%{reviewDto.deviceId}"></s:hidden>
			<s:hidden name="brandId" value="%{reviewDto.manufacturerId}"></s:hidden>
			<s:hidden name="reviewId" value="%{reviewDto.reviewId}"></s:hidden>
			<s:hidden name="modelName" value="%{reviewDto.modelName}"></s:hidden>
			
			<s:label for="title" value="Title *:"></s:label>
			<s:textfield name="title" value="%{reviewDto.title}" id="title" size="125" cssClass="reviewTitle" maxlength="100"></s:textfield>
			
			<s:label for="review" value="Detailed Review *:"></s:label>
			<s:textarea name="review" id="review" value="%{reviewDto.review}" cols="95" rows="15" cssClass="reviewDesc"></s:textarea>
			<s:hidden name="" id="reviewHtml" value="%{reviewDto.review}"></s:hidden>
			
			<s:label for="approve" value="Approve Review *:"></s:label>
			<s:select name="approve" list="#{'Y':'Yes','N':'No'}"></s:select>
			<div class="reviewActionBtn">
				<s:submit id="approveReviewFormSubmit" value="Submit" cssClass="button"></s:submit>
			</div>
		</s:form>
	</div>
</s:if>
</div>
</s:i18n>
<ckeditor:replace replace="review" basePath='/ckeditor/'  config='<%=ckSettings %>'/>
<script type="text/javascript" src="<%=path%>/js/viewjs/device/device.js"></script>
<SCRIPT type="text/javascript">
CKEDITOR.on( 'dialogDefinition', function( ev ){
	var dialogName = ev.data.name;
	var dialogDefinition = ev.data.definition;
	if ( dialogName == 'link' )
	{
		dialogDefinition.removeContents( 'advanced' );
	}
});


$('#approveReviewFormSubmit').click(function(e){
//	alert("approveReviewFormSubmit");
	e.preventDefault();
	var title=$('#title').val();
//	alert("title="+title);
	var isTitleValid=isFieldValid(title,"^[A-Za-z0-9\\s]{10,100}$");
//	alert("isTitleValid="+isTitleValid);
	var reviewDesc = CKEDITOR.instances.review.getData();
//	alert("reviewDesc="+reviewDesc);
	if(!isTitleValid){
		jAlert('<b>Review Title</b> is mandatory and should contain only digits & alphapets and atleast 10 characters and max 100 Characters!','Review Submission Error');
		return false;
	}else if(trimString(reviewDesc)=="" || reviewDesc.length<10 || reviewDesc.length>20000){
		jAlert('<b>Detailed Review</b> is mandatory and should contain atleast 10 characters and max 20000 Characters!','Review Submission Error');
		return false;
	}else{
		$('#approveReviewFormSubmit').attr('disabled', true);
		$('#approveReviewForm').attr('action','approveReview!approveReview.html');
		$('#approveReviewForm').submit();
	}
});

$(document).ready(function() {
	showReviewVeil();
	var reviewHtml=$('#reviewHtml').val();
	CKEDITOR.instances.review.setData(reviewHtml);
});

</SCRIPT>