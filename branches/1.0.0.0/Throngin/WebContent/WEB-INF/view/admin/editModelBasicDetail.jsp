<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%String path = request.getContextPath();%>
<s:include value="../common/jDatePicker.jsp"></s:include>
<script src="<%=path%>/js/viewjs/admin/addNewModel.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=path%>/js/viewjs/device/device.js"></script>
<!-- Add New Model -->
<s:if test="%{msg!=null}">
	<div class='<s:property value="%{msgType}"/>'>
		<s:property value="%{msg}" />
	</div>
</s:if>
<div class="result"></div>
<s:form action="openModelBasicDetailsAction" namespace="/" theme="css_xhtml" method="POST" enctype="multipart/form-data"
		id="openModelBasicDetailsFormId" validate="true" name="openModelBasicDetailsForm" focusElement="addNewModel_deviceId">
<div class="sideBySideBlocksLeft sideBySideBlocks" style="width: 100%;">
<div id="showOnTopBlock">
<s:if test="%{!addNewModel}">
<h3>Select Manufacturer!</h3>
<ul>
	<li><s:actionerror /><s:fielderror></s:fielderror> <s:actionmessage /></li>
	<li>
		<table cellpadding="1px" cellspacing="1px" width="100%">
			<tr>
				<td>
					<label for="deviceId" class="label">Device:</label>
				</td>
				<td>
					<s:select list="#application.deviceList" name="deviceId" id="addNewModel_deviceId" onchange="loadManufacturerList()" headerKey="" headerValue="Select Device Type..." cssStyle="width:222px;"/>
				</td>
			</tr>
			<tr>
				<td>
					<label for="manufacturerId" class="label">Manufacturer:</label>
				</td>
				<td>
					<s:select list="#{-1:'First Select the Device Type...'}" onchange="loadModelList()" name="manufacturerId" id="manufacturerId" cssStyle="width:222px;"/>
				</td>
			</tr>
			<tr>
				<td>
					<label for="modelId" class="label">Model:</label>
				</td>
				<td>
					<s:select list="#{-1:'First Select the Manufacturer Type...'}" name="modelId" id="modelId" cssStyle="width:222px;"/>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
<!--					<s:submit name="fetchModelBtn" id="fetchModelBtnId" value="Fetch Models" method="fetchModels" theme="simple"/>-->
					<s:submit name="editModelBtn" id="editModelBtnId" value="Edit Model Basic Details" method="openEditModelBlock"  theme="simple" disabled="true" cssClass="button"/>
				</td>
			</tr>
		</table>
	</li>
</ul>
</s:if>

<s:if test="%{addNewModel}">
<h3>Edit Model : <s:property value="%{modelName}"/> </h3>
<ul>
	<li><s:actionerror /><s:fielderror></s:fielderror> <s:actionmessage /></li>
	<li>
		<s:hidden name="deviceId" value="%{deviceId}"></s:hidden>
		<s:hidden name="manufacturerId" value="%{manufacturerId}"></s:hidden>
		<s:hidden name="modelId" value="%{modelId}"></s:hidden>
		<table cellpadding="2px" cellspacing="2px" width="100%">
			<tr>
				<td>
					Model Name
				</td>
				<td>
					<s:textfield name="modelName" id="modelNameId" value="%{modelDto.modelName}" size="70" maxlength="500" onblur="trimTextField(this);"/>
				</td>
				<td rowspan="3">
					<img src="<%=path%>/images/NoImageSelected.jpg" name="selectedModelImage" id="selectedModelImageId" width="205px" height="230px" border="2px" style="border-color: #eef"/>
				</td>
			</tr>
			<tr>
				<td>
					Launch Date
				</td>
				<td>
					<s:textfield name="modelLaunchDate" id="datePicker" value="%{modelDto.modelLaunchDate}" size="20" maxlength="12"  onblur="trimTextField(this);"></s:textfield>
				</td>
			</tr>
			<tr>
				<td>
					Price (&#x20b9;)
				</td>
				<td>
					<s:textfield name="price" id="price" value="%{modelDto.price}" size="20" maxlength="5" onblur="trimTextField(this);"></s:textfield>
				</td>
			</tr>
			<tr>
				<td>
					Model Description
				</td>
				<td>
					<s:textarea name="modelDesc" id="modelDescId" value="%{modelDto.modelDesc}" cols="62" rows="10" onblur="trimTextField(this);"></s:textarea>
				</td>
			</tr>
			
			<tr>
				<td>
					Model Image Url
				</td>
				<td>
					<s:textfield name="modelImageUrl" id="modelImageUrlId"  value="%{modelDto.modelImageUrl}" size="110" onblur="previewModelImage(this)"></s:textfield>
				</td>
			</tr>
			<tr>
				<td>
					Select Model Category
					
				</td>
				<td>
					<s:hidden id="modelCategoryList" value="%{modelDto.modelCategoryId}" ></s:hidden>
				    <s:if test="%{deviceId==1}">
						<s:checkboxlist name="modelCategoryId" list="#application.mobileCategoryList" cssStyle="vertical" onclick="selected(this)" theme="ztheme_simple"></s:checkboxlist>
					</s:if>
					<s:else>
						<s:select list="#application.modelCategoryList" name="modelCategoryId"	multiple="true" size="5" cssStyle="width:250px"/>	
					</s:else>	
				</td>
			</tr>
			<tr style="display: none;">
				<td>
					Select Model Image
				</td>
				<td>
					<s:file name="modelImage" id="modelImageId" size="50" onchange="previewModelImage(this)"></s:file>
				</td>
			</tr>
			<tr>
				<td colspan="3" align="center">
					<s:submit name="backBtn" value="Back" id="backBtnId" theme="simple"  cssClass="button"></s:submit>
					<s:submit name="submitBtn" method="editModel" id="submitBtnId" value="Submit" align="center" theme="simple" cssClass="button"/>
				</td>
			</tr>
		</table>
		</li>
</ul>
</s:if>
</div>
</div>
<div style="clear: both"></div>
</s:form>