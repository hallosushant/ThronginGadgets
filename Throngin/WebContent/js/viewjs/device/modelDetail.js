$(function(){$("#slideshow").after('<div class="modelPicViews"><ul class="modelThumbs" id="modelThumbsNav">').cycle({fastOnEvent:true,speed:"slow",timeout:0,pager:"#modelThumbsNav",pagerEvent:"mouseover",pagerAnchorBuilder:function(idx,slide){return'<li><a href="#"><img src="'+slide.src+'" /></a></li>';}});});
var clientIP=$("#remoteAddr").val();
$(document).ready(function(){
//	var activeModelMenu=$("#activeModelMenu").val();
//	if(activeModelMenu=="reviews"){$("#modelMenuDetail").children().hide();$("#modelReviews").show();$("#modelMenu").find("a").removeClass("active");$("#reviews").addClass("active");}
//	else{if(activeModelMenu=="specifications"){$("#modelMenuDetail").children().hide();$("#modelSpecifications").show();$("#modelMenu").find("a").removeClass("active");$("#specifications").addClass("active");}}
	fetchRelatedCatgModels();
//	$(function(){$.getJSON("http://jsonip.appspot.com?callback=?",DisplayIP);});
//	function DisplayIP(response){clientIP=response.ip;$("#ipaddress").html("Your IP Address is "+response.ip);}
	$("#specifications").click(function(){$("#modelMenuDetail").children().hide();$("#modelSpecifications").show();$("#modelMenu").find("a").removeClass("active");$("#specifications").addClass("active");});
	var reviewShown=false;$("#reviews").click(function(){$("#modelMenuDetail").children().hide();if(!reviewShown){$import($("#contextPath").val()+"/js/jquery.jtruncate.pack.js");getReviewList();reviewShown=true;}else{$(".reviewContainer").show();$("#modelMenu").find("a").removeClass("active");$("#reviews").addClass("active");}});
	var photosShown=false;$("#photos").click(function(){$("#modelMenuDetail").children().hide();if(!photosShown){$import($("#contextPath").val()+"/js/jquery.prettyPhoto.js");getModelPhotos();photosShown=true;}else{$("#modelPhotos").show();$("#modelMenu").find("a").removeClass("active");$("#photos").addClass("active");}});
	var videoShown=false;$("#videos").click(function(){$("#modelMenuDetail").children().hide();if(!videoShown){$import($("#contextPath").val()+"/js/jquery.prettyPhoto.js");getModelVideos();videoShown=true;}else{$("#videoContainer").show();$("#modelMenu").find("a").removeClass("active");$("#videos").addClass("active");}});
	var newsShown=false;$("#news").click(function(){$("#modelMenuDetail").children().hide();if(!newsShown){$import($("#contextPath").val()+"/js/jquery.prettyPhoto.js");getModelNews();newsShown=true;}else{$("#newsContainer").show();$("#modelMenu").find("a").removeClass("active");$("#news").addClass("active");}});
	var commentsShown=false;$("#comments").click(function(){$("#modelMenuDetail").children().hide();if(!commentsShown){getModelComments();commentsShown=true;}else{$("#disqus_thread").show();$("#modelMenu").find("a").removeClass("active");$("#comments").addClass("active");}});
});
function getModelComments(){$("#modelMenu").find("a").removeClass("active");$("#comments").addClass("active");$("#loading").show();var commentContainer='<div id="disqus_thread"></div>';$("#modelMenuDetail").append(commentContainer);var disqus_shortname="codezila";var disqus_developer=1;var disqus_src="http://"+disqus_shortname+".disqus.com/embed.js";$import(disqus_src);$("#loading").hide();}
function getModelNews(){$("#modelMenu").find("a").removeClass("active");$("#news").addClass("active");$("#loading").show();var modelName=$("#modelName").val();var baseLink="https://ajax.googleapis.com/ajax/services/search/news?v=1.0&ned=in&rsz=8&hl=en";var modelName="&q="+modelName.replace(/\s/g,"+");var d=new Date();var fullLink=baseLink+modelName+"&_="+d.getTime()+"&callback=?";$.getJSON(fullLink,function(data){var entries=data.responseData.results||[];if(entries.length>0){var newsContainer='<div id="newsContainer"><div id="modelNews"></div></div>';$("#modelMenuDetail").append(newsContainer);var html=['<ul class="modelNewsList">'];for(var i=0;i<entries.length;i++){var entry=entries[i];var title=entry.titleNoFormatting.substr(0,100);var desc=entry.content.substr(0,300);var publishedDate=getDateDDMMYYYYHHMMSS(entry.publishedDate);var thumbnailUrl=$("#contextPath").val()+"/images/no-image-available.png";if($.isEmptyObject(entry.image)==false){var thumbnailUrl=entry.image.url;}var newsUrl=entry.unescapedUrl+"?iframe=true&width=95%&height=95%";html.push("<li>",'<a href="'+newsUrl+'" rel="prettyNews" title="'+desc+'">','<div id="videoImgBlock">','<img class="modelNewsThumbImg" src="',thumbnailUrl,'" alt="'+title+'" height="110"/>',"</div>",'<div id="modelNewsDesc">','<div class="titlec">',title,"</div>",'<div class="desc">',desc,"...</div></div>",'<div class="publishedDate">',publishedDate,"</div>","</a>","</li>");}html.push('</ul><br style="clear: left;"/>');document.getElementById("modelNews").innerHTML=html.join("");$("a[rel^='prettyNews']").prettyPhoto({theme:"light_rounded",animation_speed:"fast",slideshow:8000,autoplay_slideshow:false,allow_resize:true,counter_separator_label:" of ",horizontal_padding:20,deeplinking:false,social_tools:false,overlay_gallery:false});}else{var noNewsContainer='<div id="modelPhotos"><div class="photosContainer noPhotos"><div class="info">No News Found!</div></div></div>';$("#modelMenuDetail").append(noNewsContainer);}$("#loading").hide();});}
function getModelVideos(){$("#modelMenu").find("a").removeClass("active");$("#videos").addClass("active");$("#loading").show();var modelName=$("#modelName").val();var link="http://gdata.youtube.com/feeds/api/videos?alt=json-in-script&max-results=20&format=5";var modelName="&q="+modelName.replace(/\s/g,"+");var d=new Date();var fullLink=link+modelName+"&_="+d.getTime()+"&callback=?";$.getJSON(fullLink,function(data){var entries=data.feed.entry||[];if(entries.length>0){var videoContainer='<div id="videoContainer"><div id="modelVideos"></div></div>';$("#modelMenuDetail").append(videoContainer);var html=['<ul class="videos">'];for(var i=0;i<entries.length;i++){var entry=entries[i];var title=entry.title.$t.substr(0,30);var prettyTitle=entry.title.$t.substr(0,70);var desc=entry.media$group.media$description.$t.substr(0,90);var duration=entry.media$group.yt$duration.seconds.toHHMMSS();var viewCount="";if($.isEmptyObject(entry.yt$statistics)==false){viewCount=entry.yt$statistics.viewCount+" views";}var thumbnailUrl=entries[i].media$group.media$thumbnail[0].url;var playerUrl=entries[i].media$group.media$player[0].url;html.push("<li>",'<a href="'+playerUrl+'" rel="prettyVideos" title="'+desc+'">','<div id="videoImgBlock">','<img class="videoThumbImg" src="',thumbnailUrl,'" alt="'+prettyTitle+'" height="110"/>','<div class="duration">',duration,"</div>","</div>",'<div id="videoDescBlock">','<div class="titlec">',title,"...</div>",'<div class="desc">',desc,"...</div></div>",'<div class="viewCount">',viewCount,"</div>","</a>","</li>");}html.push('</ul><br style="clear: left;"/>');document.getElementById("modelVideos").innerHTML=html.join("");$("a[rel^='prettyVideos']").prettyPhoto({theme:"light_rounded",animation_speed:"fast",slideshow:8000,autoplay_slideshow:false,allow_resize:true,counter_separator_label:" of ",horizontal_padding:20,deeplinking:false,social_tools:false,overlay_gallery:false});}else{var noVideoContainer='<div id="modelPhotos"><div class="photosContainer noPhotos"><div class="info">No Videos Found!</div></div></div>';$("#modelMenuDetail").append(noVideoContainer);}$("#loading").hide();});}
String.prototype.toHHMMSS=function(){sec_numb=parseInt(this);var hours=Math.floor(sec_numb/3600);var minutes=Math.floor((sec_numb-(hours*3600))/60);var seconds=sec_numb-(hours*3600)-(minutes*60);if(hours<10){hours="0"+hours;}if(minutes<10){minutes="0"+minutes;}if(seconds<10){seconds="0"+seconds;}if(hours=="00"){return minutes+":"+seconds;}else{return hours+":"+minutes+":"+seconds;}};
function getDateDDMMYYYYHHMMSS(dateString){var date=new Date(dateString);var dd=date.getDate();var mm=date.getMonth()+1;var yy=date.getFullYear();var h=date.getHours();var m=date.getMinutes();var s=date.getSeconds();date=dd+"/"+mm+"/"+yy+" "+h+":"+m+":"+s;return date;}
function getModelPhotos(){$("#modelMenu").find("a").removeClass("active");$("#photos").addClass("active");$("#loading").show();var mId=$("#mId").serialize();var d=new Date();if($("#mId").val()!=""){$.get($("#contextPath").val()+"/fetchModelPhotos_DeviceAjaxAction.html?_="+d.getTime(),mId,function(data){$("#modelMenuDetail").append('<div id="modelPhotos"><div class="photosContainer" id="photosContainer">');if(data.modelPhotosList.length>0){var modelPhotos='<a href="{imageSrc}" rel="prettyPhoto[model_gal]"><img alt="{imageAlt}" src="{imageSrc}" width="100px" height="100px"></a>';var allModelPhoto="";$.each(data.modelPhotosList,function(index,item){var mpI=modelPhotos.replace(/{imageAlt}/g,$("#modelName").val());mpI=mpI.replace(/{imageSrc}/g,item.detailValue);allModelPhoto+=mpI;});$("#photosContainer").append(allModelPhoto);}getNGoogleImages(64);$("a[rel^='prettyPhoto']").prettyPhoto({theme:"light_rounded",animation_speed:"fast",slideshow:8000,autoplay_slideshow:false,allow_resize:true,counter_separator_label:" of ",horizontal_padding:20,default_width:"500px",default_height:"344px",deeplinking:false,social_tools:false,overlay_gallery:false});$("#loading").hide();});}}
function getNGoogleImages(totalImageSize){var resultSize=5;var pages=totalImageSize/resultSize;for(var start=0;start<pages;start++){fetchGoogleImages(start*resultSize,resultSize);}}
function fetchGoogleImages(start,resultSize){var link="https://ajax.googleapis.com/ajax/services/search/images?v=1.0&rsz="+resultSize+"&start="+start+"&safe=active&imgsz=large&userip="+clientIP;var query="&q="+$("#modelName").val().replace(/\s/g,"+");var d=new Date();var fullLink=link+query+"&_="+d.getTime()+"&callback=?";$.getJSON(fullLink,displayGoogleImages);}
function displayGoogleImages(data){var response=data.responseData;if(response!=null){var photosArray=data.responseData.results;var modelPhotos='<a href="{fullImageSrc}" rel="prettyPhoto[model_gal]" title="Image src : {title}"><img alt="{imageAlt}" src="{thumbImageSrc}" width="100px" height="100px"></a>';var allModelPhoto="";$.each(photosArray,function(index,item){var title=item.titleNoFormatting.substr(0,30);var originalContextUrl=item.originalContextUrl;var thumbImageSrc=item.unescapedUrl;if($.isEmptyObject(item.tbUrl)==false){var thumbImageSrc=item.tbUrl;}var fullImageSrc=item.unescapedUrl;var srcLink="<a href='"+originalContextUrl+"' target='_blank'>"+title+"</a>";var mpI=modelPhotos.replace(/{imageAlt}/g,$("#modelName").val());mpI=mpI.replace(/{thumbImageSrc}/g,thumbImageSrc);mpI=mpI.replace(/{fullImageSrc}/g,fullImageSrc);mpI=mpI.replace(/{title}/g,srcLink);allModelPhoto+=mpI;});$("#photosContainer").append(allModelPhoto);$("a[rel^='prettyPhoto']").prettyPhoto({theme:"light_rounded",animation_speed:"fast",slideshow:8000,autoplay_slideshow:false,allow_resize:true,counter_separator_label:" of ",horizontal_padding:20,default_width:"500px",default_height:"344px",deeplinking:false,social_tools:false,overlay_gallery:false});}}
function getReviewList(){$("#modelMenu").find("a").removeClass("active");$("#reviews").addClass("active");$("#loading").show();var mId=$("#mId").serialize();var d=new Date();if($("#mId").val()!=""){$.get($("#contextPath").val()+"/fetchModelReviews_DeviceAjaxAction.html?_="+d.getTime(),mId,function(data){if(data.modelReviewList.length>0){var reviewContainer='<div class="reviewContainer" id="reviewContainer"><ul><li class="reviewHeader"><div class="reviewTitle"><a href="{contextPath}{refLink}#{reviewId}">{title}</a></div><div class="reviewDetails">By <span class="reviewAuthor">{author}</span> on <span class="reviewDate">{reviewDate}</span></div><div class="reviewStarRating"><form id="{starRatingForm}" class="starRatingForm"><div class="detailLabel">Rating : </div><span id="{star}"></span><span class="ratingCount">{ratingCount}</span><SPAN class="ratingVotes"> votes</SPAN><input type="hidden" value="{starRating}" id="reviewRating"><div id="{ratingMsg}" class="ratingMsg">Click to rate this review</div><input type="hidden" name="reviewId" id="reviewId" value="{reviewId}"></form></div></li><li><div class="review" id="jreview">{review}</div></li></ul><div class="writeReview"><form action="writeReview.html" id="writeModalReviewForm" method="get"><input type="hidden" name="dId" id="deviceId" value="{deviceId}"><input type="hidden" name="bId" id="brandId" value="{brandId}"><input type="hidden" name="cId" id="catgId" value="{catgId}"><input type="hidden" name="mId" value="{modelId}"><input type="submit" name="writeReview" id="writeReview" value="Write a Review" class="button"></form></div></div>';$.each(data.modelReviewList,function(index,item){var rcI=reviewContainer.replace(/{title}/g,item.title);rcI=rcI.replace(/{author}/g,item.author);rcI=rcI.replace(/{reviewDate}/g,item.reviewDate);rcI=rcI.replace(/{review}/g,item.review);rcI=rcI.replace(/{starRatingForm}/g,"jstarRatingForm_"+index);rcI=rcI.replace(/{star}/g,"jstar_"+index);rcI=rcI.replace(/{ratingMsg}/g,"jratingMsg_"+index);rcI=rcI.replace(/{ratingCount}/g,item.ratingCount);rcI=rcI.replace(/{starRating}/g,item.starRating);rcI=rcI.replace(/{reviewId}/g,item.reviewId);rcI=rcI.replace(/{modelId}/g,$("#mId").val());rcI=rcI.replace(/{deviceId}/g,$("#deviceId").val());rcI=rcI.replace(/{brandId}/g,$("#brandId").val());rcI=rcI.replace(/{catgId}/g,$("#catgId").val());rcI=rcI.replace(/{refLink}/g,item.refLink);rcI=rcI.replace(/{contextPath}/g,$("#contextPath").val());$("#modelMenuDetail").append(rcI);var rating=item.starRating;var ratingMsg=$("#jratingMsg_"+index);if(rating=="0.0"){ratingMsg.text("(Be the first to Rate!)");}$("#jstar_"+index).raty({half:true,hints:["Bad","Poor","Average","Good","Superb"],score:rating,precision:true,space:false,starHalf:"star-half-big.png",starOff:"star-off-big.png",starOn:"star-on-big.png",click:function(score,evt){var starForm=$("#jstarRatingForm_"+index).serialize();$.get($("#contextPath").val()+"/rateThis_DeviceAjaxAction.html",starForm,function(data){ratingMsg.text("Thanks, Rating Submitted!");});$(this).parent().find("img").css({opacity:0.5});$(this).parent().find("#jstar_"+index).raty("readOnly",true);var currentRatingCount=$(this).parent().find(".ratingCount");currentRatingCount.html(parseInt(currentRatingCount.text())+1);}});});$("#jreview").jTruncate({length:300,minTrail:20,moreText:"Read More",lessText:"...collapse",ellipsisText:"...",moreAni:"fast",lessAni:"fast"});}else{var nrC='<div class="reviewContainer noReviews"><div class="info">No Reviews Found, Be the first to review!</div><div class="writeReview"><form action="writeReview.html" id="writeModalReviewForm"  method="get"><input type="hidden" name="dId" id="deviceId" value="{deviceId}"><input type="hidden" name="bId" id="brandId" value="{brandId}"><input type="hidden" name="cId" id="catgId" value="{catgId}"><input type="hidden" name="mId" value="{modelId}"><input type="submit" name="writeReview"  id="writeReview" value="Write a Review" class="button"></form></div></div>';nrC=nrC.replace(/{modelId}/g,$("#mId").val());nrC=nrC.replace(/{deviceId}/g,$("#deviceId").val());nrC=nrC.replace(/{brandId}/g,$("#brandId").val());nrC=nrC.replace(/{catgId}/g,$("#catgId").val());$("#modelMenuDetail").append(nrC);}$("#loading").hide();});}}
function fetchRelatedCatgModels(){$("#loading").show();var dId=$("#deviceId").serialize();var mId=document.getElementById("mId").value;var catgs=document.getElementsByName("tagsCatg");var tagsCatg="";$.each(catgs,function(index,item){tagsCatg+=item.value;if(catgs.length-1!=index){tagsCatg+=",";}});var d=new Date();if(tagsCatg!=""){$.get($("#contextPath").val()+"/fetchRelatedCatgModels_DeviceAjaxAction.html?_="+d.getTime(),dId+"&tagsCatg="+tagsCatg+"&mId="+mId,function(data){if(data.modelList!=null&&data.modelList.length>0){var productContainer='<li class="productItem"><a href="{contextPath}{modelLink}"><img id="{modelName}" src="{modelImageUrl}" alt="{modelName}" border="0" /></a><a class="productCaption" href="{contextPath}{modelLink}">{modelName}</a><span class="price"></span>&#x20b9; <span class="priceInfo">{price}</span></li>';$.each(data.modelList,function(index,item){var pcI=productContainer.replace(/{modelName}/g,item.modelName);pcI=pcI.replace(/{modelImageUrl}/g,item.modelImageUrl);pcI=pcI.replace(/{modelId}/g,item.modelId);pcI=pcI.replace(/{deviceId}/g,item.deviceId);pcI=pcI.replace(/{brandId}/g,item.manufacturerId);pcI=pcI.replace(/{price}/g,item.price);pcI=pcI.replace(/{contextPath}/g,$("#contextPath").val());pcI=pcI.replace(/{modelLink}/g,item.modelLink);$("#productfeaturelist").append(pcI);});}else{$("#productfeaturelist").append("<SPAN>No Related Models found</SPAN>");}$("#loading").hide();});}}