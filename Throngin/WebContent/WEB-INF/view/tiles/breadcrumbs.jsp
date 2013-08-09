<%@taglib prefix="s" uri="/struts-tags"%>
<% String path=request.getContextPath(); %>
		<ul class="xbreadcrumbs" id="breadcrumbs-1">
	            <li>
	            	<s:url value="/home.html" id="homeLink"></s:url>
	               <s:a href="%{#homeLink}" cssClass="home">Home</s:a>
	            </li>
	        <s:if test="searchType!=null">
	            <li>
	               <a style="text-transform: capitalize"><s:property value="searchType"/></a>
	            </li>
	        </s:if>    
	        <s:if test="dId!=null">
	        	<s:url action="modelGallery" id="device" escapeAmp="false">
					<s:param name="dId" value="%{dId}" />
				</s:url>
	            <li>
	               <a href="<s:property value="#device" escape="false"/>" ><s:property value="#application.deviceList[dId]"/></a>
	            </li>
	        </s:if>
            <s:if test="bId!=null">
            	<s:url action="modelGallery" id="brand" escapeAmp="false">
					<s:param name="dId" value="%{dId}" />
					<s:param name="bId" value="%{bId}" />
				</s:url>
	            <li>
	               <a href="<s:property value="#brand" escape="false"/>" ><s:property value="#application.manufacturerList[bId]"/></a>
	            </li>
	        </s:if>
            <s:if test="cId!=null">
               	<s:url action="modelGallery" method="catgModels" id="catg" escapeAmp="false">
					<s:param name="dId" value="%{dId}" />
					<s:param name="cId" value="%{cId}" />
				</s:url>
            
	            <li>
	               <a href="<s:property value="#catg" escape="false"/>" ><s:property value="#application.modelCategoryList[cId]"/></a>
	            </li>
            </s:if>
            <s:if test="mId!=null && writeReview!=null">
            	<s:url action="modelDetail" id="model" escapeAmp="false">
					<s:param name="dId" value="%{dId}" />
					<s:param name="bId" value="%{bId}" />
					<s:param name="cId" value="%{cId}" />
					<s:param name="mId" value="%{mId}" />
				</s:url>
	            <li><a href="<s:property value="#model" escape="false"/>" ><s:property value="#application.modelList[mId]"/></a></li>
	        </s:if>
	        <s:elseif test="mId!=null">
	        	<li class="current"><a href=""><s:property value="#application.modelList[mId]"/></a></li>
	        </s:elseif>
	        <s:if test="writeReview!=null">
	            <li class="current">
	               <a href=""><s:property value="%{writeReview}"/></a>
	            </li>
            </s:if>
	        
	    </ul>
<div style="float: right;height: 35px;">
   <a title="Download ThrongIn Gadgets Android App" href="http://gadgets.throngin.com/downloads/ThrongIn_Gadgets.apk"><img src="http://gadgets.throngin.com/images/Download_Throngin_Gadgets_Android_App.png"></a>
</div>