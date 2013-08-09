<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<% String path=request.getContextPath(); %>
<script type="text/javascript" src="<%=path%>/js/viewjs/device/device.js"></script>
<!-- Search Result -->
<s:i18n name="com.sushant.verma.device">
<div id="searchResult">
	<div class="headerTitle">Search Result : <SPAN class="modelNameHeader">${searchStr}</SPAN> 
		<div style="float: right;display: inline;margin-left: 80%;">
			<SPAN class="modelNameHeader">
				<s:if test="modelList[0].modelCount!=null"> 
					<s:property value="%{modelList[0].modelCount}"/>
      			</s:if>
      			<s:else>	
      				No
      			</s:else>
			</SPAN> Items Found
		</div>
	</div>
	<div class="clearfloat"></div>
	<s:if test="%{msg!=null}">
		<div class='<s:property value="%{msgType}"/>'>
			<s:property value="%{msg}" />
		</div>
	</s:if>
	<div id="searchResultModelGallery">
		<s:iterator value="%{modelList}" id="modelListId" status="count">
			<div class="modelContainer">
				<s:if test="%{#modelListId.modelLink!=null}">
					<s:url action="modelDetail" id="modelDetailUrl" value="%{#modelListId.modelLink}"/>
				</s:if>
				<s:else>
					<s:url action="modelDetail" id="modelDetailUrl">
						<s:param name="searchType" value="%{searchType}"></s:param>
						<s:param name="dId"><s:property value="%{dId}"/></s:param>
						<s:param name="bId"><s:property value="%{bId}"/></s:param>
						<s:param name="cId"><s:property value="%{cId}"/></s:param>
						<s:param name="mId"><s:property value="#modelListId.modelId"/></s:param>
					</s:url>
				</s:else>
				
				<s:hidden name="searchType" id="searchType" value="%{searchType}"></s:hidden>
				<s:hidden name="dId" id="deviceId" value="%{dId}"></s:hidden>
				<s:hidden name="bId" id="brandId" value="%{bId}"></s:hidden>
				<s:hidden name="cId" id="catgId" value="%{cId}"></s:hidden>
				<div class="modelImageBlock">
					<a title='<s:property value="#modelListId.modelName"/>' href="<s:property value="#modelDetailUrl" escape="false"/>" >
						<img src='<s:property value="#modelListId.modelImageUrl"/>' 
								alt='<s:property value="#modelListId.modelName"/>' 
								title='<s:property value="#modelListId.modelName"/>'/>
					</a>
					<span class="productCaption">
						<a title='<s:property value="#modelListId.modelName"/>' href="<s:property value="#modelDetailUrl" escape="false"/>" >
							<s:property value="#modelListId.modelName"/>
						</a>
					</span>
					<div class="price">
						<s:if test="#modelListId.price!=null && #modelListId.price!=0"> 
	           				&#x20b9; <s:property value="#modelListId.price"/>
	           			</s:if>
	           			<s:else>	
	           				-
	           			</s:else>
					</div>
					<%-- 
					<div class="compare width113 checkBoxDiv" id="compare_div_<s:property value="#modelListId.modelId"/>">
						<input type="checkbox" value="<s:property value="#modelListId.modelId"/>" name='compare_<s:property value="#modelListId.modelId"/>' id='compare_<s:property value="#modelListId.modelId"/>' title="Click to add for comparision" onclick="selected(this)">
						<label for='compare_<s:property value="#modelListId.modelId"/>' class="checkboxLabel">Compare</label>
					</div>
					--%>
				</div>
				<div class="modelDesc">
					<s:property value="#modelListId.modelDesc"/>
					<div class="rating">Rating : 
						<span id='star_<s:property value="#count.index"/>'></span>
						<input type="hidden" value="<s:property value="#modelListId.starRating"/>" id='modelRating_<s:property value="#count.index"/>'>
						<input type="hidden" value="<s:property value="%{modelList[0].modelCount}"/>" id="modelCount">
					</div>
					<a class="more" title='<s:property value="#modelListId.modelName"/>' href="<s:property value="#modelDetailUrl" escape="false"/>">more...</a>
				</div>
			</div>
		</s:iterator>
	</div>
	<div style="clear: both;">
		<s:hidden name="qryStr" id="qryStr"></s:hidden>
		<s:hidden name="nextPage" id="nextPage" value="%{p}"></s:hidden>
		<s:if test="showMore && tc>0 && searchType=='advanced'">
				<input type="button" value="more" id="more" name="more" width="700px" class="moreButton" onclick="getMoreSearchedModels('advancedSearchAjaxAction','mobileSearch','<%=path%>')">
				<div><img alt="loading" src="<%=path%>/images/loading.gif" id="loading" style="display: none;"></div>
		</s:if>
		<s:elseif test="showMore && tc>0 && searchType=='quick'">
				<s:hidden name="searchStr" id="searchStr"></s:hidden>
				<s:hidden name="deviceId" id="deviceId"></s:hidden>
				<input type="button" value="more" id="more" name="more" width="700px" class="moreButton" onclick="getMoreQuickSearchedModels('quickSearchAjaxAction','search','<%=path%>')">
				<div><img alt="loading" src="<%=path%>/images/loading.gif" id="loading" style="display: none;"></div>
		</s:elseif>
		
	</div>
</div>
</s:i18n>
<script type="text/javascript" src="js/jquery.raty.min.js"></script>
<SCRIPT type="text/javascript">
var modelCount=$('#modelCount').val();
for(var i=0;i<modelCount;i++){
	var rating=$('#modelRating_'+i).val();
	$('#star_'+i).raty({half: true,score : rating,space	: false,readOnly : true});}
</SCRIPT>