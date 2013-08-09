<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%String path=request.getContextPath(); %>
<html>
<head>
<META HTTP-EQUIV="Refresh" CONTENT="3;URL=<%=path%>/home.html">
<link href='images/ThrongIn_Icon_16x16.ico' rel='icon' type='image/vnd.microsoft.icon'/>
<title>Page Not Found</title>
</head>
<body>
<div style="text-align: center;display: block;margin-top: 100px;">
<img alt="www.throngin.com" src="<%=path%>/images/ThrongInLogo.png" align="top" title="www.throngin.com">
<br></br>
<h2>Oops !!! The Requested Page was not found. Redirecting you to the Home Page.</h2>
<div style="text-align: center;display: block;">
<img src="<%=path%>/images/loading.gif"/>
</div>
</div>
</body>
</html>
