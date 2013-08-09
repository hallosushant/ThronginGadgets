<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/WEB-INF/view/login/loginCheck.jsp" />
<!-- BaseRuntimeException -->
<s:property value="%{msg}" />
<s:actionmessage/>
<s:actionerror/>