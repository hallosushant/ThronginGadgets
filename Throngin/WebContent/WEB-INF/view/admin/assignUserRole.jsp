<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<SCRIPT type="text/javascript">

function setValue(id,value){
	document.getElementById(id).value=value;
}

function clearForm(){
	document.getElementById('userEmailIdSearch').value='';
	document.getElementById('userRoleSearchId').value='0';
}
function checkUserRole(){
	if(document.getElementById('userRoleSelectedId').value==''){
		alert('Please Select User Role to Assign a new Role!');
	}
	else{
	document.getElementById('submitBtnId').disabled=true;
	document.getElementById('assignUserRoleFormModalId').submit();
	}
}
function enableSubmit(){
	document.getElementById('submitBtnId').disabled=false;
}


function enableButton(){
	document.getElementById('assignNewRoleBtnId').disabled=false;
	document.getElementById('removeRoleBtnId').disabled=false;
}

</SCRIPT>
<title>Search User-Role</title>
</head>

<body>
<div class="sideBySideBlocksLeft sideBySideBlocks">
<div id="showOnTopBlock" style="width: 750px;">
<h3>Assign User Role</h3>
<ul>
	<li>
		<h4>Search User using either user email-Id or Role</h4>
	</li>
	<s:if test="hasActionErrors()">
		<li><s:actionerror /></li>
	</s:if>
	<s:if test="hasActionMessages()">
		<li><s:actionmessage /></li>
	</s:if>
	<s:if test="hasFieldErrors()">
		<li><s:fielderror></s:fielderror></li>
	</s:if>
	<s:if test="%{msg!=null}">
		<li><s:property value="%{msg}" /></li>
	</s:if>
	
	<li><s:form name="searchUserForm" theme="css_xhtml"
		action="assignUserRoleAction!searchUser" id="searchUserFormId"
		namespace="/">
		<table class="tableHeader">
			<tr>
				<td class="cellLeft"><s:text name="User Email:" /></td>
				<td class="cellLeft"><s:textfield name="userEmail" cssErrorClass="validationError" 
					id="userEmailIdSearch" maxlength="50" size="30"
					title="Input User Email to search" /></td>
				<td class="cellLeft"><s:text name="User Role:"></s:text></td>
				<td class="cellLeft"><s:select name="userRoleSearch"
					list="#application.userRoleAllList" theme="simple" id="userRoleSearchId"
					headerKey="0" headerValue="Select User Role..." title="User Role" /></td>
			</tr>
			<tr>
				<td colspan="2" class="cellRight"><s:submit name="searchBtn"
					value="Search" id="searchBtnId" title="Click to search User" /></td>
				<td colspan="2" class="cellLeft"><input type="button"
					title="Click to Clear the form!" value="Clear"
					onclick="clearForm()" /></td>
			</tr>
		</table>
	</s:form></li>

	<s:set value="%{userList}"  name="userList"/>
	<s:set value="%{userList.size()}"  name="userListSize"/>
	<s:if test="#userList!=null && #userListSize>0">
		<hr />
		<li><s:form action="assignUserRoleAction"
			method="openAssignBlock" name="assignUserRoleForm"
			id="assignUserRoleFormId" namespace="/">
			<display:table name="userList" cellpadding="5px" class="tableHeader" size="userListSize" 
				pagesize="5" partialList="true" requestURI="" id="userListTableId">
				<display:column title="SELECT" class="cellCenter">
					<input type="radio" name="selectedUserListIndex" onclick="enableButton()" value="<s:property value="#attr.userListTableId_rowNum-1"/>">
					 <s:hidden name="userIdArray" value="%{#attr.userList[#attr.userListTableId_rowNum-1].USER_ID}" />
					 <s:hidden name="userEmailArray" value="%{#attr.userList[#attr.userListTableId_rowNum-1].USER_EMAIL}" />
					 <s:hidden name="currentRoleArray" value="%{#attr.userList[#attr.userListTableId_rowNum-1].ROLE_TYPE}" />
					 <s:hidden name="currentRoleIdArray" value="%{#attr.userList[#attr.userListTableId_rowNum-1].USER_ROLE_MASTER_ID}" />
					 <s:hidden name="activeArray" value="%{#attr.userList[#attr.userListTableId_rowNum-1].ACTIVE}" />
					 <s:hidden name="blockedArray" value="%{#attr.userList[#attr.userListTableId_rowNum-1].IS_BLOCKED}" />
					 <s:hidden name="approvedArray" value="%{#attr.userList[#attr.userListTableId_rowNum-1].APPROVED}" />
					 <s:hidden name="rejectedArray" value="%{#attr.userList[#attr.userListTableId_rowNum-1].IS_REJECTED}" />
				</display:column>
				<display:column property="USER_ID" title="ID" class="cellCenter" />
				<display:column property="USER_EMAIL" title="EMAIL ID"
					class="cellLeft" />
				<display:column property="ACTIVE" title="ACTIVE" class="cellCenter" />
				<display:column property="IS_BLOCKED" title="BLOCKED"
					class="cellCenter" />
				<display:column property="APPROVED" title="APPROVED"
					class="cellCenter" />
				<display:column property="ROLE_TYPE" title="CURRENT ROLE"
					class="cellCenter" />
				<display:column property="IS_REJECTED" title="REJECTED"
					class="cellCenter" />
			</display:table>
			
			<div align="center" class="divSubmit">
				<s:hidden name="assignRole" id="assignRoleId" />
				<s:submit name="assignNewRoleBtn" id="assignNewRoleBtnId" value="Assign New Role" action="assignUserRoleAction" method="openAssignBlock" onclick="setValue('assignRoleId','1')" theme="simple" disabled="true"/>
				<s:if test='#userList.get(0).ROLE_TYPE!=null'>
					<s:submit name="removeRoleBtn" id="removeRoleBtnId" value="Remove Role" action="assignUserRoleAction" method="openAssignBlock" onclick="setValue('assignRoleId','0')" theme="simple" disabled="true"/>
				</s:if>
			</div>
				
				
		</s:form></li>
	</s:if>
	<s:if test="%{modal==1}">
		<li>
		<hr>
		<div style="clear: both"></div>

		<s:form action="assignUserRoleAction!assignRole" namespace="/"
			id="assignUserRoleFormModalId" validate="true"
			name="assignUserRoleFormModal">

			<table class="tableHeader">
				<tr>
					<th>ID</th>
					<th>EMAIL ID</th>
					<th>CURRENT ROLE</th>
					<s:if test="%{assignRole==0}">
						<th>REMOVE ROLE</th>
					</s:if>
					<s:elseif test="%{assignRole==1}">
						<th>ASSIGN ROLE</th>
					</s:elseif>
				</tr>
				<tr class="cellCenter">
					<td><s:property value="userId" /></td>
					<td><s:property value="userEmail" /></td>
					<s:if test="%{assignRole==0}">
						<td><s:property value="currentRole" /></td>
					</s:if>
					<s:elseif test="%{assignRole==1}">
						<td><s:property value="currentRoleList" /></td>
					</s:elseif>
					<s:if test="%{assignRole==0}">
						<td title="Removes the Current Role from this user"><s:url
							action="assignUserRoleAction" id="removeCurrentRoleUrl"
							method="removeCurrentRole">
							<s:param name="userId" value="userId"></s:param>
							<s:param name="userEmail" value="userEmail"></s:param>
							<s:param name="currentRole" value="currentRole"></s:param>
							<s:param name="currentRoleId" value="currentRoleId"></s:param>
						</s:url> <a
							href="<s:property value="#removeCurrentRoleUrl"  escape="false"/>">
						<img alt="Remove Current Role"
							src="<%=path%>/images/remove_user.png"> </a></td>
					</s:if>
					<s:elseif test="%{assignRole==1}">
						<s:hidden name="userId" value="%{userId}" />
						<s:hidden name="userEmail" value="%{userEmail}" />
						<td><s:select name="selectedUserRole" onchange="enableSubmit()"
							list="#application.userRoleList" theme="simple" id="userRoleSelectedId"
							headerKey="" headerValue="Select User Role..." title="User Role" />
						</td>
					</s:elseif>
				</tr>

				<tr class="cellCenter" height="50px" valign="bottom">
					<td colspan="4" align="center">
					<s:if test="%{assignRole==1}">
						<input name="submitBtn" value="submit" onclick="checkUserRole();" 
							id="submitBtnId" disabled="disabled" type="button" 
							title="Click to submit"/>
					</s:if>
					</td>
				</tr>
			</table>
			
			
		</s:form></li>
	</s:if>
</ul>
</div>
</div>
</body>
</html>