<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% String path=request.getContextPath(); %>
<title>UserInactive</title>
<div style="position: relative ;top:200px;text-align: center;">
    <img alt="www.throngin.com" src="<%=path%>/images/ThrongInLogo.png" align="top" title="www.throngin.com">
    <br></br> 
    You have been logged out due to inactivity.
    <br></br> 
	Click &nbsp; <a href="<s:url action ="signIn"/>">here</a>&nbsp; to login again    
    </div>
<br>

