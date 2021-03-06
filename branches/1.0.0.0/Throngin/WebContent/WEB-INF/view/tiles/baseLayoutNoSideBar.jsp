<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%String path=request.getContextPath(); %>
<html>
<head>
	<s:include value="commonHeader.jsp"></s:include>
	<s:if test="%{metaDescription!=null}">
		<meta name="description" content='<s:property value="%{metaDescription}"/>' />
	</s:if>
	<s:else>
		<meta name="description" content='ThrongIn Gadgets offers free Gadgets Specifications, User and Expert Reviews with videos, Price & User Rating with photos, Images and News of all new, upcoming, fetured and hot & latest mobiles, laptops  & tablets' />
	</s:else>
	<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body id="home">

	<div id="tilesNavbar">
		<tiles:insertAttribute name="navbar" />
	</div>
<div id="tilesWrap">
	<div style="clear: both"></div>	
	<div id="tilesHeader">
		<tiles:insertAttribute name="header" />
	</div>
	<div style="clear: both"></div>
	<div id="tilesBreadcrumb">
		<tiles:insertAttribute name="breadcrumb" />
	</div>
	<div style="clear: both"></div>

	<div id="tilesContentFull">
		<tiles:insertAttribute name="body" />
	</div>
</div>
<div id="tilesFooter">
	<tiles:insertAttribute name="footer" />
</div>
</body>
</html>
<s:include value="../common/noscript.jsp"></s:include>