<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%String path=request.getContextPath(); %>
<!-- Model Gallery -->
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
	<s:iterator value="%{compositeModelList}" id="compositeModelListId" status="count">
		<div style="clear: both;"></div>
		<div class="galleryTitle"><s:property value="%{compositeModelList[#count.index][0].title}"/><s:property value="%{galleryTitleSuffix}"/> </div>
		<s:iterator value="#compositeModelListId" id="modelListId" >
			<div class="productContainer">
				<s:url action="modelDetail" id="modelDetailUrl" escapeAmp="false">
					<s:param name="dId"><s:property value="%{dId}"/></s:param>
					<s:param name="bId"><s:property value="%{bId}"/></s:param>
					<s:param name="cId"><s:property value="%{cId}"/></s:param>
					<s:param name="mId"><s:property value="#modelListId.modelId"/></s:param>
				</s:url>
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

		<div style="clear: both;">
			<s:if test="compositeModelList[#count.index][0].modelCount>5">
				<s:if test="bId==null && cId==null">
					<div class="more">
						<s:form action="moreModelGallery" theme="simple">
								<s:hidden name="dId" value="%{compositeModelList[#count.index][0].deviceId}"></s:hidden>
								<s:hidden name="galleryType" value="%{compositeModelList[#count.index][0].title}"></s:hidden>
								<s:hidden name="p" value="%{p}"></s:hidden>
								<s:submit value="more..."  theme="simple" cssClass="button"></s:submit>
						</s:form>
					</div>
				</s:if>
				<s:elseif test="cId==null">
					<div class="more">
						<s:form action="moreModelGallery"  theme="simple">
								<s:hidden name="dId" value="%{compositeModelList[#count.index][0].deviceId}"></s:hidden>
								<s:hidden name="bId" value="%{compositeModelList[#count.index][0].manufacturerId}"></s:hidden>
								<s:hidden name="galleryType" value="%{compositeModelList[#count.index][0].title}"></s:hidden>
								<s:hidden name="p" value="%{p}"></s:hidden>
								<s:submit value="more..."  theme="simple" cssClass="button"></s:submit>
						</s:form>
					</div>
				</s:elseif>
				<s:elseif test="cId!=null">
					<div class="more">
						<s:form action="moreModelGallery"  theme="simple">
								<s:hidden name="dId" value="%{compositeModelList[#count.index][0].deviceId}"></s:hidden>
								<s:hidden name="cId" value="%{cId}"></s:hidden>
								<s:hidden name="galleryType" value="%{compositeModelList[#count.index][0].title}"></s:hidden>
								<s:hidden name="p" value="%{p}"></s:hidden>
								<s:submit value="more..."  theme="simple" cssClass="button"></s:submit>
						</s:form>
					</div>
				</s:elseif>
			</s:if>
		</div>
	</s:iterator>
</div>
</s:i18n>