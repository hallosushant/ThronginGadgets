<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="org.apache.log4j.LogManager"%>
<%@page import="org.apache.log4j.Logger"%>
<%String path=request.getContextPath(); %>
<html>
<head>
<META HTTP-EQUIV="Refresh" CONTENT="5;URL=<%=path%>/home.html">
<link href='images/ThrongIn_Icon_16x16.ico' rel='icon' type='image/vnd.microsoft.icon'/>
<title>Unexpected Error</title>
</head>
<body>
<div style="text-align: center;display: block;margin-top: 100px;">
<img alt="www.throngin.com" src="<%=path%>/images/ThrongInLogo.png" align="top" title="www.throngin.com">
<br></br>
<h2>Oops !!! Something Unexpected Occurred. Redirecting you to the Home Page.</h2>
<div style="text-align: center;display: block;">
<img src="<%=path%>/images/loading.gif"/>
</div>
<%
Logger log=LogManager.getLogger("GlobalException");
int i=(int)(Math.random()*10000);
%>

  <p>
    Please report this error to our Technical Support with Error code: <%=i %>
    Thank you for your cooperation.
    <% 
	  Throwable err=(Throwable)request.getAttribute("exception");
	  log.error("Error number: "+i,err); 
    %>
  </p>
 
    
</div>
</body>
</html>

