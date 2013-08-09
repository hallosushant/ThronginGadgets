<%@taglib prefix="s" uri="/struts-tags"%>
<%String path=request.getContextPath(); %>
<STYLE type="text/css">
	body { font-size: 12px; }
</STYLE>
<script type="text/javascript" src="js/jquery-easing-1.3.pack.js"></script>
<script type="text/javascript" src="js/jquery-easing-compatibility.1.2.pack.js"></script>
<script type="text/javascript" src="js/coda-slider.1.1.1.pack.js"></script>
<script type="text/javascript" src="js/jquery.cycle.all.js"></script>
<script type="text/javascript">
var theInt = null;
var $crosslink, $navthumb,$moversRowNav;
var curclicked = 0;
theInterval=function(cur){clearInterval(theInt);if(typeof cur!="undefined"){curclicked=cur;}$crosslink.removeClass("active-thumb");$moversRowNav.removeClass("active-moversRowNav");$navthumb.eq(curclicked).parent().parent().addClass("active-moversRowNav");$navthumb.eq(curclicked).parent().addClass("active-thumb");$(".stripNav ul li a").eq(curclicked).trigger("click");theInt=setInterval(function(){$crosslink.removeClass("active-thumb");$moversRowNav.removeClass("active-moversRowNav");$navthumb.eq(curclicked).parent().parent().addClass("active-moversRowNav");$navthumb.eq(curclicked).parent().addClass("active-thumb");$(".stripNav ul li a").eq(curclicked).trigger("click");curclicked++;if(6==curclicked){curclicked=0;}},5000);};
$(function(){$("#main-photo-slider").codaSlider();$navthumb=$(".nav-thumb");$crosslink=$(".cross-link");$moversRowNav=$(".movers-row-nav");$navthumb.hover(function(){var $this=$(this);theInterval($this.parent().attr("href").slice(1)-1);});theInterval();$("#slider-wrap").show();});
$(document).ready(function(){
	getDeviceReviewList();getTopRatedModels();var maxItems=5;$("#featuredProducts .productsWrapper li").each(function(i){if(i%maxItems==0&&i>=maxItems){$("#featuredProducts .productsWrapper").append("<ul class='productfeaturelist'></ul>");}$("#featuredProducts .productsWrapper .productfeaturelist").last().append(this);});$("#featuredProducts").append('<a id="archiveNext" class="fixPNG archiveNext" href="javascript:;">N</a> <a id="archivePrev" class="fixPNG archivePrev" href="javascript:;">P</a>');$("#featuredProducts .productsWrapper").cycle({fx:"scrollHorz",speed:"600",timeout:0,next:".archiveNext",prev:".archivePrev"});$("#page-wrap-right").cycle({pause:true,speed:"600",timeout:3000});$("#reviewList").cycle({fx:"scrollVert",pause:true,height:600,speed:"6000",timeout:3000});
	$(".panel").each(function(index,item){var featuredStarRating=$("#featuredModelRating_"+index).val();$("#featuredStar_"+index).raty({readOnly:true,hints:["Bad","Poor","Average","Good","Superb"],score:featuredStarRating});});
});
</script>
<div id="topRow" class="pageRows">
<s:if test="%{msg!=null}">
	<div class='<s:property value="%{msgType}"/>'>
		<s:property value="%{msg}" />
	</div>
</s:if>
	<div id="page-wrap">
												
		<div class="slider-wrap" id="slider-wrap"  style="display: none;">
			<div id="main-photo-slider" class="csw">
				<div class="panelContainer">
					<s:iterator value="featuredModels" id="featuredModelsId" status="count">
						<div class="panel" title="<s:property value="#featuredModelsId.modelName"/>">
							<s:url action="modelDetail" id="featuredModelDetailUrl" escapeAmp="false">
								<s:param name="dId"><s:property value="#featuredModelsId.deviceId"/></s:param>
								<s:param name="bId"><s:property value="#featuredModelsId.manufacturerId"/></s:param>
								<s:param name="cId"></s:param>
								<s:param name="mId"><s:property value="#featuredModelsId.modelId"/></s:param>
							</s:url>
							<div class="wrapper">
								<a href="<s:property value="#featuredModelDetailUrl" escape="false"/>">
									<img class="floatLeft featuredImg" src="<s:property value="#featuredModelsId.modelImageUrl"/>" alt="<s:property value="#featuredModelsId.modelName"/>" />
								</a>
								<h1>
									<a href="<s:property value="#featuredModelDetailUrl" escape="false"/>">
										<span class="caption"><s:property value="#featuredModelsId.modelName"/></span>
									</a>
								</h1>
								<ul>
									<li><s:property value="%{#featuredModelsId.modelDesc}"/></li>
									<li class="featuredStarRating">
										<span id="featuredStar_${count.index}"></span>
										<span class="featuredRatingCount">
											<s:property value="#featuredModelsId.ratingCount"/>
											<input type="hidden" id='featuredModelRating_${count.index}' value='${featuredModelsId.starRating}'>
										</span>
										<SPAN class="featuredRatingVotes"> votes</SPAN>
									</li>
									<li class="featuredPrice">&#x20b9; <s:property value="%{#featuredModelsId.price}"/></li>
									<li><a style="float: right;" href="<s:property value="#featuredModelDetailUrl" escape="false"/>">
											Specifications & Reviews
										</a>
									</li>
								</ul>
							</div>
						</div>	
					</s:iterator>
				</div>
			</div>
			<div id="movers-row">
				<s:iterator value="featuredModels" id="featuredModelsThumbId" status="count">
						<div class="panel" title="<s:property value="#featuredModelsThumbId.modelName"/>">
							<div class="movers-row-nav">
								<s:if test="#count.index==0">
									<a href='#<s:property value="#count.count"/>' class="cross-link active-thumb">
										<img src="<s:property value="#featuredModelsThumbId.modelImageUrl"/>" class="nav-thumb" alt="<s:property value="#featuredModelsThumbId.modelName"/>" />
										<s:property value="#featuredModelsThumbId.modelName"/>
									</a>
								</s:if>
								<s:else>
									<a href='#<s:property value="#count.count"/>' class="cross-link">
										<img src="<s:property value="#featuredModelsThumbId.modelImageUrl"/>" class="nav-thumb" alt="<s:property value="#featuredModelsThumbId.modelName"/>" />
										<s:property value="#featuredModelsThumbId.modelName"/>
									</a>
								</s:else>
							</div>
						</div>	
				</s:iterator>
			</div>
	
		</div>
	</div>
	<div id="page-wrap-right">
		<s:iterator value="newModels" id="newModelsId" status="count">
			<div class="productThumb">
				<s:url action="modelDetail" id="modelDetailUrl" escapeAmp="false">
					<s:param name="dId"><s:property value="#newModelsId.deviceId"/></s:param>
					<s:param name="bId"><s:property value="#newModelsId.manufacturerId"/></s:param>
					<s:param name="cId"></s:param>
					<s:param name="mId"><s:property value="#newModelsId.modelId"/></s:param>
				</s:url>
				
				<a title='<s:property value="#newModelsId.modelName"/>' href="<s:property value="#modelDetailUrl" escape="false"/>" >
					<img src='<s:property value="#newModelsId.modelImageUrl"/>' class="nav-thumb" alt='<s:property value="#newModelsId.modelName"/>' />
					<span class="caption"><s:property value="#newModelsId.modelName"/></span>
					<span class="desc"><s:property value="#newModelsId.modelDesc"/></span>
				</a>
			</div>
		</s:iterator>
	</div>
	<div class="topRowAdContainer adspace336x280">
		<s:include value="common/advFormats/displayAd_336x228.jsp"></s:include>
	</div>
</div>
<div style="clear: both;"></div>
<div id="row2" class="pageRows">
<div id="featuredProducts">
	<div class="blockHeader">Popular Models</div>
	<div class="productsWrapper">
		<ul class="productfeaturelist">
			<s:iterator value="popularModels" id="popularModelsId" status="count">
				<s:url action="modelDetail" id="modelDetailUrl" escapeAmp="false">
					<s:param name="dId"><s:property value="#popularModelsId.deviceId"/></s:param>
					<s:param name="bId"><s:property value="#popularModelsId.manufacturerId"/></s:param>
					<s:param name="cId"></s:param>
					<s:param name="mId"><s:property value="#popularModelsId.modelId"/></s:param>
				</s:url>
				<li class="productItem">
					<a title='<s:property value="#popularModelsId.modelName"/>' href="<s:property value="#modelDetailUrl" escape="false"/>" >
	                   <img src='<s:property value="#popularModelsId.modelImageUrl"/>' border="0" 
						alt='<s:property value="#popularModelsId.modelName"/>' 
						title='<s:property value="#popularModelsId.modelName"/>'/>
	                </a>
	                <a class="productCaption" href='<s:property value="#modelDetailUrl" escape="false"/>'><s:property value="#popularModelsId.modelName"/></a>
	                <span class="price">Price</span>
	                <span class="priceInfo">&#x20b9; <s:property value="#popularModelsId.price"/></span>			
		        </li>
			</s:iterator>
		</ul>
	</div>
</div>
</div>

<div style="clear: both;"></div>
<div id="row3" class="pageRows">
	<div id="highlyRated">
	</div>
	<div id="latestReviews">
		<div class="blockHeader">Latest Reviews</div>
		<div  id="reviewList">
			<ul class="reviewList">
			</ul>
		</div>
	</div>
	<div style="padding: 5px;float: left;margin: 20px 0px;">
		<s:include value="common/advFormats/displayAd_728x90.jsp"></s:include>
	</div>
	
</div>