<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="com.ckeditor.*" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<% String path=request.getContextPath(); %>
<%@page import="com.ckeditor.CKEditorReplaceTag"%>
<% 
	CKEditorConfig ckSettings = new CKEditorConfig();
	ckSettings.addConfigValue("toolbar","[{name:'basicstyles',items:['Bold','Italic','Underline','Strike','-','RemoveFormat']},{name:'paragraph',items:['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock']},{name:'insert',items:['Smiley']},{name:'document',items:['Maximize','Preview','Print','-','SpellChecker']}]");
	ckSettings.addConfigValue("uiColor" , "#eef");
	ckSettings.addConfigValue("disableObjectResizing" , true);
	ckSettings.addConfigValue("forcePasteAsPlainText" , true);
	ckSettings.addConfigValue("scayt_autoStartup" , true);
	ckSettings.addConfigValue("resize_dir" , "vertical");
%>
<!-- Write Review -->
<s:i18n name="com.sushant.verma.device">
<div class="modelDetail">
	<div class="modelHeader">
		<div class="modelTitle">Write Review on : <SPAN class="modelNameHeader"><s:property value="#application.modelList[mId]"/></SPAN></div>
	</div>
	<s:if test="%{msg!=null}">
	<div class='<s:property value="%{msgType}"/>'>
		<s:property value="%{msg}" />
	</div>
	</s:if>
	<s:actionmessage/>
	<s:actionerror/>
	<s:fielderror/>
	<s:if test="#session.loggedInUser!=null">
	<div id="reviewForm">
		<div id="reviewVeil" style="display: none;">
			<div class="info"><s:text name="text.signin.to.review"></s:text></div>
			<a href="#loginBlock" name="modal" shape="rect" class="reviewVeilSignIn">SignIn</a>
		</div>
		<s:form name="writeReviewForm" id="writeReviewForm" theme="simple">
			<s:hidden name="modelName" value="%{#application.modelList[mId]}"></s:hidden>
			<s:hidden name="mId" value="%{mId}"></s:hidden>
			<s:hidden name="modelId" value="%{mId}"></s:hidden>
			<s:hidden name="dId" id="deviceId" value="%{dId}"></s:hidden>
			<s:hidden name="bId" id="brandId" value="%{bId}"></s:hidden>
			<s:hidden name="cId" id="catgId" value="%{cId}"></s:hidden>
			<s:label for="title" value="Title *:"></s:label>
			<s:textfield name="title" id="title" size="125" cssClass="reviewTitle" maxlength="100"  onblur="trimTextField(this);"></s:textfield>
			
			<s:label for="review" value="Detailed Review *:"></s:label>
			<s:textarea name="review" id="review" cols="95" rows="15" cssClass="reviewDesc"></s:textarea>
			
			<div class="reviewActionBtn">
				<s:submit id="writeReviewFormSubmit" value="Submit" cssClass="button"></s:submit>
				<s:submit action="writeReview"  method="backToModelDetail" type="submit" id="writeReviewForm__backToModelDetail" value="Back"  cssClass="button"></s:submit>
			</div>
		</s:form>
	</div>
	</s:if>
	<s:else>
		<div id="reviewVeil" style="display: block;">
			<div class="info"><s:text name="text.signin.to.review"></s:text></div>
			<a href="#loginBlock" name="modal" shape="rect" class="reviewVeilSignIn">SignIn</a>
		</div>
	</s:else>
</div>
</s:i18n>
<ckeditor:replace replace="review" basePath='/ThrongIn/ckeditor/'  config='<%=ckSettings %>'/>
<script type="text/javascript" src="<%=path%>/js/viewjs/device/device.js"></script>
<SCRIPT type="text/javascript">
CKEDITOR.on( 'dialogDefinition', function( ev ){var dialogName = ev.data.name;var dialogDefinition = ev.data.definition;if ( dialogName == 'link' ){dialogDefinition.removeContents( 'advanced' );}});
$('#writeReviewFormSubmit').click(function(e){e.preventDefault();var title=$('#title').val();var isTitleValid=isFieldValid(title,"^[A-Za-z0-9\\s]{10,100}$");var reviewDesc = CKEDITOR.instances.review.getData();
if(!isTitleValid){jAlert('<b>Review Title</b> is mandatory and should contain only digits & alphapets and atleast 10 characters and max 100 Characters!','Review Submission Error');return false;}
else if(trimString(reviewDesc)=="" || reviewDesc.length<10 || reviewDesc.length>20000){jAlert('<b>Detailed Review</b> is mandatory and should contain atleast 10 characters and max 20000 Characters!','Review Submission Error');return false;}
else{$('#writeReviewFormSubmit').attr('disabled', true);$('#writeReviewForm').attr('action','writeReview!saveModelReview.html');$('#writeReviewForm').submit();}});
//$(document).ready(function() {showReviewVeil();});
</SCRIPT>