<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<% String path=request.getContextPath(); %>
<link rel="stylesheet" href="<%=path%>/css/prettyPhoto.css" type="text/css" media="screen" charset="utf-8" />
<!-- Specification, Price and Reviews -->
<s:i18n name="com.sushant.verma.device">
<s:if test="%{msg!=null}">
	<div class='<s:property value="%{msgType}"/>'>
		<s:property value="%{msg}" />
	</div>
</s:if>
<s:actionmessage/>
<s:actionerror/>
<s:fielderror/>
<s:if test="%{modelDto.modelName!=null}">
<div class="modelDetail">
	<div class="modelHeader">
		<s:url action="modelDetail" id="modelDetailUrl" escapeAmp="false">
			<s:param name="dId"><s:property value="%{dId}"/></s:param>
			<s:param name="bId"><s:property value="%{bId}"/></s:param>
			<s:param name="cId"><s:property value="%{cId}"/></s:param>
			<s:param name="mId"><s:property value="modelDto.modelId"/></s:param>
		</s:url>
		<div class="modelTitle">
			<a href="<s:property value="modelDetailUrl"  escape="false"/>">
				<s:property value="modelDto.modelName"/>
			</a>
		</div>
	</div>
	<div class="basicInfo">
		<div class="leftBlock">
			<div id="slideshow">
				<s:if test="modelPictures.size>0">
					<s:iterator value="%{modelPictures}" id="modelPicturesId" >
						<img alt="<s:property value="modelDto.modelName"/>" src="<s:property value="#modelPicturesId.modelImageUrl"  escape="false"/>" style="display: none;">
					</s:iterator>
				</s:if>
				<s:else>
					<img alt="<s:property value="modelDto.modelName"/>" src="<s:property value="modelDto.modelImageUrl"  escape="false"/>">
				</s:else>
			</div>
		</div>
		<div class="rightBlock">
		<div class="col2">
					
				<div class="details">
					<div class="detailLabel">Brief Detail : </div><s:property value="modelDto.modelDesc"/>
				</div>
				
				<div class="details">	
					<div class="detailLabel">Launch Date : </div><s:date name="modelDto.launchDate" format="MMMMM, yyyy"/>
				</div>
				<div class="modelPrice">	
					<div class="detailLabel">Price (MRP): </div>
						<s:if test="modelDto.price!=null && modelDto.price!=0"> 
			            	&#x20b9;<s:property value="modelDto.price"/>
			            </s:if>
            			<s:else>	
            				<s:text name="text.price.not.available"></s:text>
            			</s:else>
				</div> 
				<div class="details">
					<div class="detailLabel">Rating : </div>
					<form name="starRating">
						<span id="star"></span>
						<span class="ratingCount"><s:property value="modelDto.ratingCount"/></span><SPAN class="ratingVotes"> votes</SPAN>
						<input type="hidden" value="<s:property value="modelDto.starRating"/>" id="modelRating">
						<div id="ratingMsg" class="ratingMsg">Click to rate this model</div>
						<s:hidden name="mId" id="mId" value="%{mId}"></s:hidden>
					</form>
				</div>					
		</div>
		<div class="col3">
				<div class="details">	
					<div class="detailLabel">Tags : </div>
					<ul class="tags">
					<s:iterator value="%{modelTagsList}" id="modelTagsList" status="modelTags">
						<s:set name="currentItrName" scope="request">modelTags_<s:property value="#modelTags.index"/></s:set>
						<s:url action="modelGallery" method="catgModels" id='#currentItrName' escapeAmp="false">
								<s:param name="dId"><s:property value="#modelTagsList.deviceId"/></s:param>
								<s:param name="cId"><s:property value="#modelTagsList.categoryId"/></s:param>
						</s:url>
						<li>
							<a href="<s:property value="#currentItrName" escape="false"/>" 
								rel="follow" title="<s:property value="#modelTagsList.categoryDesc" escape="false"/>">
									<s:property value="#modelTagsList.categoryName"/>
							</a>
							<s:hidden name="tagsCatg" id="tagsCatg_%{#modelTags.index}" value="%{#modelTagsList.categoryId}"></s:hidden>
						</li>
					</s:iterator>
					</ul>
				</div>
				<div class="details">
					<s:include value="../common/advFormats/displayAd_200x200.jsp"></s:include>
				</div>
		</div>
		<div class="addThis"><s:include value="../common/addThis.jsp"></s:include></div>
		<div class="modelActionDiv" style="float: left;">
			<form action="writeReview.html" id="writeModalReviewForm" method="get">
				<s:hidden name="dId" id="deviceId" value="%{dId}" />
				<s:hidden name="bId" id="brandId" value="%{bId}" />
				<s:hidden name="cId" id="catgId" value="%{cId}" />
				<s:hidden name="mId" value="%{mId}" />
				<input type="submit" name="writeReview" id="writeReview" value="Write a Review" class="button">
			</form>
		</div>
		</div>
	</div><!-- end #basicInfo -->
	
	<div class="adspace728x15">
		<SPAN class="advertisementText">Advertisement</SPAN> 
		<s:include value="../common/advFormats/displayAd_728x15.jsp"></s:include>
	</div>
	<div id="menuDetailsContainer">
		<div id="modelMenu">
			<ul>
				<li><a href="#specifications" class="active" id="specifications">Specifications</a></li>
<%--			<li><a href="#reviews" class="" id="reviews">Reviews</a></li> --%>
				<li><a href="#photos" class="" id="photos">Photos</a></li>
				<li><a href="#videos" class="" id="videos">Videos</a></li>
				<li><a href="#news" class="" id="news">News</a></li>
<%--			<li><a href="#comments" class="" id="comments">Comments</a></li> --%>
			</ul>
		</div>
		<s:hidden name="mId" id="mId" value="%{mId}"></s:hidden>
		<s:hidden name="dId" id="deviceId" value="%{dId}"></s:hidden>
		<s:hidden name="bId" id="brandId" value="%{bId}"></s:hidden>
		<s:hidden name="cId" id="catgId" value="%{cId}"></s:hidden>
		
		<s:hidden name="modelName" id="modelName" value="%{modelDto.modelName}"></s:hidden>
		<s:hidden name="activeModelMenu" id="activeModelMenu" value="%{activeModelMenu}"></s:hidden>
		<div id="menuDetail">
			<div class="loadingCircle" style="display: none;" id="loading" >
				<img alt="loading" src="<%=path%>/images/loadingCircle.gif" id="loading" >
			</div>
			<div class="modelMenuDetail" id="modelMenuDetail">
				<table id="modelSpecifications" >
					<s:iterator id="modelDetailListId" value="%{modelDetailList}" status="count">
						<s:if test="#count.index==0">
							<tr class="specificationsHeader"><th colspan="2"><s:property value="#modelDetailListId.categoryName"/></th></tr>
						</s:if>
						<s:elseif test="#count.index>0 && #modelDetailListId.categoryName!=modelDetailList[#count.index-1].categoryName">
							<tr class="specificationsHeader"><th colspan="2"><s:property value="#modelDetailListId.categoryName"/></th></tr>
						</s:elseif>
							<tr>
								<td class="specificationLabel"><s:property value="#modelDetailListId.detailName"/></td>
								<td><s:property value="#modelDetailListId.detailValue"/></td>
							</tr>
					</s:iterator>
				</table>
			</div>
			<div style="clear: both;"></div>
			<div class="adspace728x90">
				<SPAN class="advertisementText">Advertisement</SPAN> 
				<s:include value="../common/advFormats/displayAd_728x90.jsp"></s:include>
			</div>
		</div>
	</div>
	<div id="menuDetailsContainer">
		<div id="modelMenu">
			<ul>
				<li><a href="#reviews" class="active" id="reviews">Reviews</a></li>
			</ul>
		</div>		
		<s:hidden name="modelName" id="modelName" value="%{modelDto.modelName}"></s:hidden>
		<s:hidden name="activeModelMenu" id="activeModelMenu" value="%{activeModelMenu}"></s:hidden>
		<div id="menuDetail">
			<div style="clear: both;"></div>
			<div class="modelMenuDetail" id="modelMenuDetail">
				<div id="modelReviews">
				<s:iterator id="modelReviewListId" value="%{modelReviewList}" status="count">
					<div class="reviewContainer" id="reviewContainer">
						<ul>
							<li class="reviewHeader">
								<div class="reviewTitle" id='<s:property value="#modelReviewListId.reviewId"/>'>
									<a href="<%=path%><s:property value="#modelReviewListId.refLink"/>#<s:property value="#modelReviewListId.reviewId"/>">
									<s:property value="#modelReviewListId.title"/>
									</a>
								 
								<%-- <s:property value="#modelReviewListId.title"/> --%>
								</div>
								<div class="reviewDetails">
									By <span class="reviewAuthor"><s:property value="#modelReviewListId.author"/></span> on <span class="reviewDate"><s:property value="#modelReviewListId.reviewDate"/></span>
								</div>
								<div class="reviewStarRating">
									<form id="starRatingForm_${count.index}" class="starRatingForm">
									<div class="detailLabel">Rating : </div>
										<span id="star_${count.index}"></span>
										<span class="ratingCount"><s:property value="#modelReviewListId.ratingCount"/></span><SPAN class="ratingVotes"> votes</SPAN>
										<input type="hidden" value='<s:property value="#modelReviewListId.starRating"/>' id="reviewRating_${count.index}">
										<div id="ratingMsg_${count.index}" class="ratingMsg">Click to rate this review</div>
										<input type="hidden" name="reviewId" id="reviewId" value='<s:property value="#modelReviewListId.reviewId"/>'>
									</form>
								</div>
							</li>
							<li>
								<div class="review" id="jreview_${count.index}">
									<s:property value="#modelReviewListId.review" escapeHtml="false"/>
								</div>
							</li>
						</ul>
					</div>
				</s:iterator>
				<s:if test="%{modelReviewList.size<1}"><div class="info">No Reviews Found, Be the first to review!</div></s:if>
					<div class="writeReview">
							<form action="writeReview.html" id="writeModalReviewForm" method="get">
								<s:hidden name="dId" id="deviceId" value="%{dId}" />
								<s:hidden name="bId" id="brandId" value="%{bId}" />
								<s:hidden name="cId" id="catgId" value="%{cId}" />
								<s:hidden name="mId" value="%{mId}" />
								<input type="submit" name="writeReview" id="writeReview" value="Write a Review" class="button">
							</form>
					</div>
				</div>
			</div>
			<div class="adspace728x90">
				<SPAN class="advertisementText">Advertisement</SPAN> 
				<s:include value="../common/advFormats/displayAd_728x90.jsp"></s:include>
			</div>
			
		</div>
	</div>
</div>


<div style="clear: both;"></div>
<div id="row2" class="pageRows">
<div id="featuredProducts">
	<div class="blockHeader">Related Models</div>
	<div class="productsWrapper">
		<ul id="productfeaturelist" class="productfeaturelist">
			
		</ul>
	</div>
</div>
</div>
<div style="clear: both;"></div>
<div class="clear20"></div>
<div class="disqusCommentHeader"><div class="disqusCommentTitle">Comments</div></div>
<div class="disqusComment">
	<div id="disqus_thread"></div>
</div>
</s:if>
<div style="clear: both;"></div>
</s:i18n>
<script type="text/javascript" src="<%=path%>/js/jquery.cycle.all.js"></script>
<script type="text/javascript" src="<%=path%>/js/viewjs/device/modelDetail.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.raty.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.jtruncate.pack.js"></script>
<SCRIPT type="text/javascript">
var disqus_identifier=$("#modelName").val().replace(/\s/g,"-");var rating=$("#modelRating").val();var ratingMsg=$("#ratingMsg");if(rating=="0.0"){ratingMsg.text("(Be the first to Rate!)");}
$("#star").raty({half:true,hints:["Bad","Poor","Average","Good","Superb"],score:rating,precision:true,space:false,starHalf:"star-half-big.png",starOff:"star-off-big.png",starOn:"star-on-big.png",click:function(score,evt){var starForm=$(this).parent().serialize();$.get("rateThis_DeviceAjaxAction.html",starForm,function(data){ratingMsg.text("Thanks, Rating Submitted!");});$(this).parent().find("img").css({opacity:0.5});$(this).parent().find("#star").raty("readOnly",true);var currentRatingCount=$(this).parent().find(".ratingCount");currentRatingCount.html(parseInt(currentRatingCount.text())+1);}});
$(".reviewStarRating").each(function(index,item){var rating=$("#reviewRating_"+index).val();var ratingMsg=$("#ratingMsg_"+index);if(rating=="0.0"){ratingMsg.text("(Be the first to Rate!)");}$("#star_"+index).raty({path:"<%=path%>/images/",half:true,hints:["Bad","Poor","Average","Good","Superb"],score:rating,precision:true,space:false,starHalf:"star-half-big.png",starOff:"star-off-big.png",starOn:"star-on-big.png",click:function(score,evt){var starForm=$("#starRatingForm_"+index).serialize();$.get("rateThis_DeviceAjaxAction.html",starForm,function(data){ratingMsg.text("Thanks, Rating Submitted!");});$(this).parent().find("img").css({opacity:0.5});$(this).parent().find("#star_"+index).raty("readOnly",true);var currentRatingCount=$(this).parent().find(".ratingCount");currentRatingCount.html(parseInt(currentRatingCount.text())+1);}});});
$(".review").each(function(index,item){$("#jreview_"+index).jTruncate({length:1000,truncateElement:"</p>",moreText:"Read More",lessText:"...collapse",ellipsisText:"..."});});
</SCRIPT>