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
function checkCategory(){
	if(document.getElementById('categoryNameSearchId').value==''){
		alert('Please select a category to search!');
	}
	else{
	document.getElementById('searchCategoryFormId').submit();
	}
}

function disableMe(id){
	document.getElementById(id).disabled=true;
	document.getElementById('createCategoryFormId').submit();
}
function clearForm(){
	document.getElementById('categoryNameId').value='';
	document.getElementById('parentCategoryListId').value='';
}

</SCRIPT>
<title>Create Category</title>
</head>
<body>
<div class="sideBySideBlocksLeft sideBySideBlocks">
<div id="showOnTopBlock" style="width: 750px;">
<h3>Create Category</h3>
<ul>
	<li>
	<h4>Enter a Category Name &amp; select Parent Category</h4>
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

	<li><s:form name="createCategoryForm" theme="simple"
		action="createCategoryAction!createCategory" id="createCategoryFormId"
		namespace="/">
		<table class="tableHeader">
			<tr>
				<td class="cellLeft"><s:text name="Category Name:" /></td>
				<td class="cellLeft"><s:textfield name="categoryName"
					cssErrorClass="validationError" id="categoryNameId" maxlength="50"
					size="30" title="Enter Category Name" /></td>
				<td class="cellLeft"><s:text name="Parent Category:"></s:text></td>
				<s:if test="#session.CATEGORY_LIST!=null">
				<td class="cellLeft"><s:select
					list="#session.CATEGORY_LIST" listKey="CATEGORY_MST_ID"
					listValue="CATEGORY_NAME" theme="simple" id="parentCategoryListId"
					name="parentCategory" headerKey=""
					headerValue="Select Parent Category..."
					title="Parent Category List" /></td>
				</s:if>
				<s:else>
				<td class="cellLeft"><s:select
					list="#application.categoryList" theme="simple" id="parentCategoryListId"
					name="parentCategory" headerKey=""
					headerValue="Select Parent Category....."
					title="Parent Category List" /></td>
				</s:else>
			</tr>
			<tr>
				<td colspan="2" class="cellRight"><s:submit name="submitBtn" onclick="disableMe('submitBtnId')"
					value="Submit" id="submitBtnId" title="Click to create Category" /></td>
				<td colspan="2" class="cellLeft"><input type="button" onclick="clearForm()"
					title="Click to Clear the form!" value="Clear" /></td>
			</tr>
		</table>
	</s:form></li>
	<li>
	<hr></hr>
	<h4>Select a Category to get Sub-Category List</h4>
	</li>
	<li><s:form name="searchCategoryForm" theme="simple"
		action="createCategoryAction!searchSubCategoryList" id="searchCategoryFormId"
		namespace="/">
		<table class="tableHeader">
			<tr>
				<td class="cellLeft"><s:text name="Category:"/></td>
				<s:if test="#session.CATEGORY_LIST!=null">
				<td class="cellLeft"><s:select name="categoryNameSearch"
					listKey="CATEGORY_MST_ID" listValue="CATEGORY_NAME"
					list="#session.CATEGORY_LIST" theme="simple"
					id="categoryNameSearchId" headerKey=""  
					headerValue="Select Category..." title="Category" /></td>
				</s:if>
				<s:else>
				<td class="cellLeft"><s:select name="categoryNameSearch"
					list="#application.categoryList" theme="simple"
					id="categoryNameSearchId" headerKey=""  
					headerValue="Select Category....." title="Category" /></td>
				</s:else>
				<td class="floatLeft"><input type="button" name="searchBtn" onclick="checkCategory()" 
					value="Search" id="searchBtnId" title="Click to search Category" /></td>
			</tr>
		</table>
	</s:form></li>
	<s:set value="%{subCategoryList}" name="subCategoryList" /> 
		<s:set value="%{subCategoryList.size()}" name="subCategoryListSize"/>
		<s:if test="#subCategoryList!=null && #subCategoryListSize>0">
			<li>
				<display:table name="subCategoryList" cellpadding="5px" pagesize="5" partialList="true"  size="subCategoryListSize" 
					class="tableHeader" id="subCategoryListTableId" requestURI="" >
					<display:column property="SEARCHED_CATEGORY" title="SEARCHED CATEGORY" class="cellCenter"/>
					<display:column property="PARENT_CATEGORY" title="PARENT CATEGORY"
						class="cellCenter" />
					<display:column property="SUB_CATEGORY" title="SUB CATEGORY"
						class="cellCenter" />
				</display:table>
			</li>
		</s:if>
		<s:else>
			<li class="nothingFoundMessage">
				<s:text name="No Parent Or Sub-Category Found!"></s:text>
			</li>			
		</s:else>	
	
</ul>
</div>
</div>
</body>

</html>