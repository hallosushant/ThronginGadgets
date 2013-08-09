<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<% String path=request.getContextPath(); %>
<script type="text/javascript" src="<%=path%>/js/viewjs/device/device.js"></script>
<!-- Advance Search -->
<s:i18n name="com.sushant.verma.device">
<div id="advancedSearch">
	<div class="headerTitle">Advanced Search</div>
	<s:actionmessage/><s:actionerror/><s:fielderror/>
	<div class="expanderIcon"><a href="#" id="expandAdvancedSearchIcon" name="expandAdvancedSearchIcon" title="Click to Expand"></a></div>
	<div id="advancedSearchTabs" style="display: none;">
		<ul>
			<li><a href="#mobileAdvancedSearch">Mobile</a></li>
			<li><a href="#laptopAdvancedSearch">Laptop</a></li>
			<li><a href="#tabletAdvancedSearch">Tablet</a></li>
		</ul>
		<div id="mobileAdvancedSearch">
			<a href="#" id="expandAdvancedSearchLink" name="expandAdvancedSearchLink" title="Click to Expand" style="float: right;">expand &raquo;</a><br/>
			<s:form action="advancedSearch!mobileSearch" name="advancedMobileSearchForm" id="advancedMobileSearchForm" theme="ztheme_simple">
				<s:hidden name="searchType" value="Advanced Search"></s:hidden>
				<s:hidden name="dId" value="1"></s:hidden>
				<s:hidden name="p" id="p" value="1"></s:hidden>
				<s:hidden name="tc" value="%{tc}"></s:hidden>
				<div id="block">
					<div class="blockTitle">Network<div class="clearLink"><a href="#clearAll" onclick="clearAll('network')">Clear</a></div></div> 
					<s:radio onclick="selectedRadio(this)"  name="network" id="network" list="#{'28':'GSM','27':'CDMA'}" cssStyle="vertical"></s:radio>
				</div>
				<div id="block">
					<div class="blockTitle">Mobile Brands</div>
					<div style="text-align: center;clear: left;"><a href="#selectAll" onclick="selectAll('mobileManufacturer')">Select All</a> | <a href="#clearAll" onclick="clearAll('mobileManufacturer')">Clear All</a></div>
					<s:checkboxlist onclick="selected(this)" name="mobileManufacturer" id="mobileManufacturer" list="#application.mobileManufacturerList" cssStyle="vertical"></s:checkboxlist>
				</div>
				<div id="block">
					<div class="blockTitle">Mobile Price</div>
					<div class="selectDiv">
						<s:select list="#application.mobilePriceBandList" name="mobilePriceBand" id="mobilePriceBand" headerKey="-1" headerValue="--Price Band--"></s:select>
					</div>
				</div>
				<div id="block">
					<div class="blockTitle">Mobile Body Type<div class="clearLink"><a href="#clearAll" onclick="clearAll('mobileBody')">Clear</a></div></div>
					<s:radio onclick="selectedRadio(this)" name="mobileBody" id="mobileBody" list="#application.mobileBodyCatgList" cssStyle="vertical"></s:radio>
				</div>
				<div id="block">
					<div class="blockTitle">Touch Screen Mobile</div>
					<s:radio list="#{'4':'Yes','61':'NO'}" onclick="openBlock(this,'touchscreenBlock')" name="touchscreen" id="touchscreen" cssStyle="vertical"></s:radio>
					<div id="touchscreenBlock" class="subBlock">
						<s:radio onclick="selectedRadio(this)" name="touchscreenType" id="touchscreenType" list="#application.mobileTouchscreenCatgList" cssStyle="vertical"></s:radio>
					</div>
				</div>
				<div id="block">
					<div class="blockTitle">Operating System<div class="clearLink"><a href="#clearAll" onclick="clearAll('mobileOS')">Clear</a></div></div>
					<s:radio onclick="selectedRadio(this)" name="mobileOS" id="mobileOS" list="#application.mobileOSList" cssStyle="vertical"></s:radio>
				</div>
				<div id="block">
					<div class="blockTitle">Data / Connectivity</div>
					<s:checkboxlist onclick="selected(this)" name="dataConnectivity" id="dataConnectivity" list="#application.mobileDataConnectivityList" cssStyle="vertical"></s:checkboxlist>
				</div>
				<div id="block">
					<div class="blockTitle">Camera Mobile</div>
					<s:radio list="#{'9':'Yes','48':'NO'}" onclick="openBlock(this,'cameraBlock')" name="camera" id="camera" cssStyle="vertical"></s:radio>
					<div id="cameraBlock" class="subBlock">
						<s:checkboxlist onclick="selected(this)" name="cameraType" id="cameraType" list="#application.cameraMobileCatgList" cssStyle="vertical"></s:checkboxlist>
						<div class="selectDiv" id="primaryCameraType" style="display: none;">
							<s:select list="#application.primaryCameraCatgList" name="primaryCameraType" id="primaryCameraType" headerKey="-1" headerValue="Primary Camera Type"></s:select>
						</div>
					</div>
				</div>
				<div id="block">
					<div class="blockTitle">Screen Display Size</div>
					<div class="selectDiv">
						<s:select list="#application.mobileDisplaySizeList" name="screenDisplaySize" id="screenDisplaySize" headerKey="-1" headerValue="Screen Display Size--"></s:select>
					</div>
				</div>
				<div id="block">
					<div class="blockTitle">Music Mobile</div>
					<s:checkboxlist onclick="selected(this)" name="music" id="music" list="#application.musicMobileCatgList" cssStyle="vertical"></s:checkboxlist>
				</div>
				<div id="block">
					<div class="blockTitle">Misc./Others</div>
					<s:checkboxlist onclick="selected(this)" name="misc" id="misc" list="#application.miscMobileCatgList" cssStyle="vertical"></s:checkboxlist>
				</div>

				<div id="submitButtonPosition"></div>
				<div class="searchSubmitDiv" id="submitDiv">
					<s:submit value="Search" cssClass="button"></s:submit>
					<s:reset  value="Reset" cssClass="button"></s:reset>
				</div>
			</s:form>	
		</div>
		<div id="laptopAdvancedSearch">
		  	<div  style="text-align: center;margin-top:50px;color: #059; " class="header">
		  		<h2>Comming Soon</h2>
		   	</div>
		</div>
		<div id="tabletAdvancedSearch">
		  	<div  style="text-align: center;margin-top:50px;color: #059; " class="header">
		  		<h2>Comming Soon</h2>
		   	</div>
		</div>
	</div>
</div>
</s:i18n>
<SCRIPT type="text/javascript">

$(document).ready(function() {
	$( "#advancedSearchTabs" ).tabs().css('display', 'block');
	
	var advancedMobileSearchFormSer=$('#advancedMobileSearchForm').serialize();
	$('#qryStr').val(advancedMobileSearchFormSer);
	
	$(window).scroll(function() {
		posYTop = $('#advancedSearchTabs').offset().top;
		posYBtm = $('#advancedSearchTabs').height()-posYTop;
		winTop=$(window).scrollTop();
		winHgt=$(window).height();
		expanderHeight=(posYTop<winTop?(posYBtm>winTop?winTop:posYBtm):posYTop);
		
		$('#expandAdvancedSearchIcon').css('top',expanderHeight);

		fixedBtmDivTop=$('#fixedBtmDiv').offset().top;
		fixedTopDivTop=$('#fixedTopDiv').offset().top;
		submitButtonPositionTop=$('#submitButtonPosition').offset().top;
		
		submitDivTop=fixedBtmDivTop-posYTop-30;
		if(submitButtonPositionTop>fixedBtmDivTop)
			$('#submitDiv').css({'top':submitDivTop,'position':'absolute'});
		else 
			$('#submitDiv').css({'top':'10px','position':'relative'});
		
//		console.log(posYTop+" | "+submitButtonPositionTop+" | "+fixedBtmDivTop+" | "+posYBtm+" | "+winTop+" | "+expanderHeight);
	});
	var advancedSearchTabsHeight;
	var maxAdvancedSearchTabsHeight=3450;
	var minAdvancedSearchTabsHeight=1800;
	$('#expandAdvancedSearchIcon').toggle(function() {
		$('#tilesLeftSidebar').css('width','53.5%');
		$('#tilesContent').css('width','44%');
		$(this).addClass('active').attr('title','Click to Collapse');
		$('#expandAdvancedSearchLink').text('« collapse').attr('title','Click to Collapse');
		advancedSearchTabsHeight=$('#advancedSearchTabs').height();
//		console.log("advancedSearchTabsHeight exp="+advancedSearchTabsHeight);
		$('#advancedSearchTabs').css('height',minAdvancedSearchTabsHeight);
	}, function() {
		$('#tilesLeftSidebar').css('width','20%');
		$('#tilesContent').css('width','77.5%');
//		console.log("advancedSearchTabsHeight coll="+advancedSearchTabsHeight);
		$('#advancedSearchTabs').css('height',(advancedSearchTabsHeight>maxAdvancedSearchTabsHeight?advancedSearchTabsHeight:maxAdvancedSearchTabsHeight));
//		console.log("advancedSearchTabsHeight coll after="+advancedSearchTabsHeight);
		$(this).removeClass('active').attr('title','Click to Expand');
		$('#expandAdvancedSearchLink').text('expand »').attr('title','Click to Expand');
	});	

	$('#expandAdvancedSearchLink').click(function() {
		//console.log('click');
		$('#expandAdvancedSearchIcon').click();
	});		
	
	$('input[type=reset]').click(function(){
		$('#advancedMobileSearchForm').children().children('.checkBoxDivSelect').removeClass('checkBoxDivSelect');
		$('#advancedMobileSearchForm').find('input[type=radio],input[type=checkbox]').each(function(){
				$(this).attr('checked', false);
			});
	});
});

</SCRIPT>