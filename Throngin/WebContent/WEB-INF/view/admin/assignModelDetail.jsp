<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%String path = request.getContextPath();%>
<!-- Assign Model Detail -->
<script src="<%=path%>/js/viewjs/admin/assignModelDetail.js" type="text/javascript"></script>
<s:if test="%{msg!=null}">
	<div class='<s:property value="%{msgType}"/>'>
		<s:property value="%{msg}" />
	</div>
</s:if>
<div class="sideBySideBlocksLeft sideBySideBlocks">
<div id="showOnTopBlock">
<s:if test="%{assign==false}">
<h3>Select Model!</h3>
<ul>
	<li><s:actionerror /><s:fielderror></s:fielderror> <s:actionmessage /></li>
	<li><s:form action="assignModelDetail" namespace="/" cssStyle="width:1000px;"
		id="assignModelDetailFormId" validate="true"
		name="assignModelDetailForm">
		<s:select list="#session.modelList" name="modelId" headerKey="" headerValue="Select Phone Model..."
		label="Select Model"  listKey="MODEL_ID" listValue="MODEL_NAME" id="modelId"/>
		<s:hidden name="assignType" value="%{assignType}"></s:hidden>
		<s:submit name="selectModelSubmitBtn" id="selectModelSubmitBtnId" value="Select" method="openAssignBlock" align="center" disabled="true"  cssClass="button"/>		
	</s:form></li>
</ul>
</s:if>

<s:if test="%{assign==true}">
<h3>Assign Model Detail : <SPAN class="modelNameHeader"> <s:property value="%{modelName}"/> </SPAN></h3>
<ul>
	<li><s:actionerror /><s:fielderror></s:fielderror> <s:actionmessage /></li>
	<li><s:form action="assignModelDetail" namespace="/"
		id="assignModelDetailFormId" validate="true" name="assignModelDetailForm">
		<table cellpadding="2px" cellspacing="2px" id="modelDetailTableId" width="1000px">
			<tr align="center" style="background-color: #eef" id="modelDetailHeaderTrId">
				<th width="220px">Detail Category</th>
				<th width="250px">Detail Name</th>
				<th width="480px">Detail Value</th>
				<th style="background-color: #fff"><img alt='Add Row' TITLE='Click to Add a new row' name="plusIcon" src='<%=path%>/images/Plus_icon.gif' style='cursor: pointer;' onclick='addRow()'></th>
			</tr>
			<s:iterator value="%{modelDetailList}" id="modelDetailListId" status="counter">
				<tr id="modelDetailTrId">
					<td><s:select name="detailCategory" id="detailCategoryId" list="#application.modelDetailCategoryList"  cssStyle="width: 210px;"
						 headerKey="-1" headerValue="Select Model Detail Category..." theme="simple" onchange="loadDetailCategoryList(this)"
						 value="%{#modelDetailListId.CATEGORY_ID}"/></td>
						 
					<td>
						<s:if test="%{modelDetailList.size==1}">
							<s:select list="#{-1:'First Select Model Detail Category...'}" name="detailName" id="detailNameId"
							value="%{#modelDetailListId.DETAIL_NAME_ID}" theme="simple"  cssStyle="width: 240px;"/>
						</s:if>
						<s:else>
							<s:select list="#application.detailNameList" name="detailName" id="detailNameId"
							value="%{#modelDetailListId.DETAIL_NAME_ID}" theme="simple"  cssStyle="width: 240px;"/>
						</s:else>
					</td>
		
					<td><s:textarea name="detailValue" id="detailValueId"
							rows="2" cols="60" value="%{#modelDetailListId.DETAIL_VALUE}"  theme="simple"/></td>
					<s:if test="%{modelDetailList.size==1}">
						<td><img alt='Delete this row' TITLE='Click to Delete this row' style='cursor: pointer;display: none;'  name="removeIcon"
								src='<%=path%>/images/remove.png' onclick='removeRow(this)'></td>
					</s:if>
					<s:else>
						<td align="center"><img alt='Delete this row'  TITLE='Click to Delete this row'  style='cursor: pointer;' name="removeIcon"
								src='<%=path%>/images/remove.png' onclick='removeRow(this)'></td>
					</s:else>
				</tr>
			</s:iterator>
		</table>
		<table cellpadding="5px" cellspacing="5px"  width="750px">
		<!--<tr>
				<td colspan="2" align="right">
					<input type="button" class="button" value="Add Row" onclick="addRow()">
				</td>
			</tr> -->
			<tr>
				<td colspan="2" align="center">
					<s:hidden name="assignType" value="%{assignType}"></s:hidden>
					<s:hidden name="modelId" value="%{modelId}"></s:hidden>
					<s:submit name="backBtn" value="Back" id="backBtnId" theme="simple"  cssClass="button"></s:submit>
					<s:submit name="assignModelDetailSubmitBtn" method="assignModelDetail" id="assignModelDetailSubmitBtnId" value="Submit" 
					 align="center" onclick="return checkModelDetailList();"  theme="simple"  cssClass="button"/>
				</td>
			</tr>
		</table>
	</s:form></li>
</ul>

</s:if>

</div>
</div>