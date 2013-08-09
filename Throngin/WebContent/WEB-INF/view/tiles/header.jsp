<%@taglib prefix="s" uri="/struts-tags"%>
<div id="searchContainer">
	<s:form name="zModelSearchForm" id="zModelSearchForm" theme="simple" action="quickSearch" method="get">
		<input type="text" name="searchStr" id="zModelSearch" autocomplete="off" maxlength="50">
		<s:select list="#application.deviceList" name="dId" id="zModelSearchForm_deviceId" theme="simple" cssClass="searchDevice"/>
		<s:submit value="Search" id="zModelSearchForm_searchBtn" cssClass="searchButton" theme="simple" disabled="true"></s:submit>
		<s:hidden name="mId" value="" id="zModelSearchForm_searchMId"></s:hidden>
		
		<div class="rghtSearchLinks">
			<s:url action="advancedSearch" id="advancedSearch">
				<s:param name="searchType">Advanced Search</s:param>
			</s:url>
				<a href="<s:property value="#advancedSearch" escape="false"/>" rel="nofollow">Advanced Search</a>
		</div>
		
	</s:form>
<div class="rghtBlock">
<!-- AddThis Button BEGIN -->
<div class="addthis_toolbox addthis_default_style" addthis:url="http://www.throngin.com">
<a class="addthis_button_facebook_like" fb:like:layout="button_count"></a>
<a class="addthis_button_tweet"></a>
<a class="addthis_button_google_plusone" g:plusone:size="medium"></a>
</div>
<script type="text/javascript" src="http://s7.addthis.com/js/250/addthis_widget.js#pubid=ra-50dedf7b4f2efe5c"></script>
<!-- AddThis Button END -->
</div>
</div>

