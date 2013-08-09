<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0);
%>
<%--
<script language="JavaScript">
var x=window.history.length;
if (window.history[x]!=window.location)
{
window.history.forward();
}
</script> --%>
<title>Login Check</title>
</head>
<body> 
<s:if test="%{#session.loggedInUser!=null}">
session=<s:property value="#session"/>
session.loggedInUser=<s:property value="#session.loggedInUser"/>
<div align="right">
<b>Session Time: </b><%= new Date(session.getLastAccessedTime())%>

	<s:url action="logoutAction" id="logoutUrlId"/>    
    <s:a href="%{logoutUrlId}" name="logoutLink" id="logoutLinkId">Logout</s:a>
</div>
</s:if>
</body>
</html>