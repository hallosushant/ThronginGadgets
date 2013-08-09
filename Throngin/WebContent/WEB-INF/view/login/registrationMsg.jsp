<%@taglib prefix="s" uri="/struts-tags"%>
<%String path = request.getContextPath();%>
<!-- Registration -->
<s:actionerror/><s:actionmessage/><s:fielderror/>
<s:if test="%{msg!=null}">
	<div class='<s:property value="%{msgType}"/>'>
		<s:property value="%{msg}" escapeHtml="false"/>
	</div>
</s:if>
