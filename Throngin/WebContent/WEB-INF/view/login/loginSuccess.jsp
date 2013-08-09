<%@taglib prefix="s" uri="/struts-tags"%>
<% String path=request.getContextPath(); %>
<!--  Login SUCCESS-->
<s:property value="%{msg}" />
<s:actionmessage/>
<s:actionerror/>
<s:if test="#session.loggedInUser!=null && #session.loggedInUser.userRole=='Admin'">
<div>

<div class="blockHeader"><B>Admin Functions</B></div>
<ul>
	<li><s:url action="addNewModelAction" id="addNewModelActionUrlId" />
	<s:a href="%{addNewModelActionUrlId}" name="addNewModelActionLink"
		id="addNewModelActionLinkId">
				Add New Model</s:a></li>
	<li><s:url action="openModelBasicDetailsAction" id="openModelBasicDetailsActionUrlId" />
	<s:a href="%{openModelBasicDetailsActionUrlId}" name="openModelBasicDetailsActionLink"
		id="openModelBasicDetailsActionLinkId">
				Edit Model Basic Details</s:a></li>
	<li><s:url action="assignModelDetail" id="assignModelDetailActionUrlId" />
	<s:a href="%{assignModelDetailActionUrlId}" name="assignModelDetailActionLink"
		id="assignModelDetailActionLinkId">
				Assign Model Detail</s:a></li>
	<li><s:url action="editModelDetail" id="editModelDetailActionUrlId" />
	<s:a href="%{editModelDetailActionUrlId}" name="editModelDetailActionLink"
		id="editModelDetailActionLinkId">
				Edit Model Detail</s:a></li>
	<li>
		<s:url action="approveReview" id="approveReviewUrlId" >
		<s:param name="dId">1</s:param>
		</s:url>
	<s:a href="%{approveReviewUrlId}" name="approveReviewLink"
		id="approveReviewLinkId">
				Approve Reviews</s:a></li>
</ul>
</div>
</s:if>
<s:else>
<META HTTP-EQUIV="Refresh" CONTENT="0;URL=<%=path%>/home.html">
</s:else>

