<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<% String path=request.getContextPath(); %>
<script src="js/viewjs/device/moreModelGallery.js" type="text/javascript"></script>
<!-- More Model Gallery -->
<s:i18n name="com.sushant.verma.device">
<s:if test="%{msg!=null}">
	<div class='<s:property value="%{msgType}"/>'>
		<s:property value="%{msg}" />
	</div>
</s:if>
<s:actionmessage/>
<s:actionerror/>
<s:fielderror/>
<div class="gallery">
			<div style="clear: both;"></div>
			<div class="galleryTitle"><s:property value="%{modelList[0].title}"/><s:property value="%{galleryTitleSuffix}"/></div>
			<div id="modelGalleryDiv">
				<s:iterator value="%{modelList}" id="modelListId" >
					<div class="productContainer">
						<s:url action="modelDetail" id="modelDetailUrl" escapeAmp="false">
							<s:param name="dId"><s:property value="%{dId}"/></s:param>
							<s:param name="bId"><s:property value="%{bId}"/></s:param>
							<s:param name="cId"><s:property value="%{cId}"/></s:param>
							<s:param name="mId"><s:property value="#modelListId.modelId"/></s:param>
						</s:url>
						<s:hidden name="dId" id="deviceId" value="%{dId}"></s:hidden>
						<s:hidden name="bId" id="brandId" value="%{bId}"></s:hidden>
						<s:hidden name="cId" id="catgId" value="%{cId}"></s:hidden>
					
				        <ul>
				            <li class="product_image">
				                <a title='<s:property value="#modelListId.modelName"/>' href="<s:property value="#modelDetailUrl" escape="false"/>" >
				                   <img src='<s:property value="#modelListId.modelImageUrl"/>' 
									alt='<s:property value="#modelListId.modelName"/>' 
									title='<s:property value="#modelListId.modelName"/>'/>
				                </a>
				                <SPAN class="productCaption">
				                	<a title='<s:property value="#modelListId.modelName"/>' href="<s:property value="#modelDetailUrl" escape="false"/>" >
				                		<s:property value="#modelListId.modelName"/>
				                	</a>
				                </SPAN>
				            </li>
				            <li>
				            	<DIV class="description">
				            		<s:property value="#modelListId.modelDesc"/>
				            		<div class="price">
				            			<s:if test="#modelListId.price!=null && #modelListId.price!=0"> 
				            				&#x20b9; <s:property value="#modelListId.price"/>
				            			</s:if>
				            			<s:else>	
				            				<s:text name="text.price.not.available"></s:text>
				            			</s:else>
		            				</div>
				            		<a class="more" title='<s:property value="#modelListId.modelName"/>' href="<s:property value="#modelDetailUrl" escape="false"/>">more...</a>
				            	</DIV>
				            </li>
				        </ul>
				     </div>					
				</s:iterator>
			</div>
			<div style="clear: both;">
			<s:if test="bId==null && cId==null && showMore">
				<form name="moreModels" id="moreModelsForm" style="width: 100%">
					<s:hidden name="dId" value="%{dId}" id="dId"></s:hidden>
					<s:hidden name="galleryType" value="%{modelList[0].title}" id="galleryType"></s:hidden>
					<s:hidden name="tc" value="%{modelList[0].modelCount}" id="tc"></s:hidden>
					<s:hidden name="p" value="%{p}" id="p"></s:hidden>
					<input type="button" value="more" id="more" name="more" width="700px" class="moreButton" onclick="getMoreModels('DeviceAjaxAction','moreDeviceModels','<%=path%>')">
					<div><img alt="loading" src="<%=path%>/images/loading.gif" id="loading" style="display: none;"></div>
				</form>
			</s:if>
			<s:elseif test="cId==null && showMore">
				<form name="moreModels" id="moreModelsForm" style="width: 100%">
					<s:hidden name="dId" value="%{modelList[0].deviceId}" id="dId"></s:hidden>
					<s:hidden name="bId" value="%{modelList[0].manufacturerId}" id="bId"></s:hidden>
					<s:hidden name="galleryType" value="%{modelList[0].title}" id="galleryType"></s:hidden>
					<s:hidden name="tc" value="%{modelList[0].modelCount}" id="tc"></s:hidden>
					<s:hidden name="p" value="%{p}" id="p"></s:hidden>
					<input type="button" value="more" id="more" name="more" width="700px" class="moreButton" onclick="getMoreModels('DeviceAjaxAction','moreModels','<%=path%>')">
					<div><img alt="loading" src="<%=path%>/images/loading.gif" id="loading" style="display: none;"></div>
				</form>
			</s:elseif>
			<s:elseif test="cId!=null && showMore">
				<form name="moreModels" id="moreModelsForm" style="width: 100%">
					<s:hidden name="dId" value="%{modelList[0].deviceId}" id="dId"></s:hidden>
					<s:hidden name="cId" value="%{cId}" id="cId"></s:hidden>
					<s:hidden name="galleryType" value="%{modelList[0].title}" id="galleryType"></s:hidden>
					<s:hidden name="tc" value="%{modelList[0].modelCount}" id="tc"></s:hidden>
					<s:hidden name="p" value="%{p}" id="p"></s:hidden>
					<input type="button" value="more" id="more" name="more" width="700px" class="moreButton" onclick="getMoreModels('DeviceAjaxAction','moreCatgModels','<%=path%>')">
					<div><img alt="loading" src="<%=path%>/images/loading.gif" id="loading" style="display: none"></div>
				</form>
			</s:elseif>
			</div>
			
</div>
</s:i18n>