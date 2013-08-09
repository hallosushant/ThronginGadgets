<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%String path=request.getContextPath(); %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<META HTTP-EQUIV="Refresh" CONTENT="0;URL=<%=path%>/home.html">
<link href='images/ThrongIn_Icon_16x16.ico' rel='icon' type='image/vnd.microsoft.icon'/>
<title>Throngin Gadgets</title>
</head>
<body>
<%--
<div style="text-align: center;display: block;margin-top: 100px;">
<h2>Welcome</h2>
<div style="text-align: center;display: block;">
<img src="<%=path%>/images/loading.gif"/>
</div>
</div>
 --%>
<s:include value="WEB-INF/view/common/googleAnalytics.jsp"></s:include>
</body>
</html>
