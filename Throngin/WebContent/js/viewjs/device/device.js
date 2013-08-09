function isFieldValid(fieldValue,regExpression){var regEx=new RegExp(regExpression);if(fieldValue.match(regEx)){return true;}else{return false;}}
function trimString(field){return field.replace(/\s/g,"");}
function showReviewVeil(){var d=new Date();$.get($("#contextPath").val()+"/fetchLoggedInUser_AjaxLoginAction.html?_="+d.getTime(),null,function(data){var user=data.userDto;if(user==null){$("#reviewVeil").show();$("#loginMsg").show();$("a[name=modal]").click();}else{$("#reviewVeil").hide();}});}
function selectAll(me){var myObj=document.getElementsByName(me);for(var i=0;i<myObj.length;i++){myObj[i].checked=true;var myId=myObj[i].getAttribute("id");$("#"+myId).parent().addClass("checkBoxDivSelect");}}
function clearAll(me){var myObj=document.getElementsByName(me);for(var i=0;i<myObj.length;i++){myObj[i].checked=false;var myId=myObj[i].getAttribute("id");$("#"+myId).parent().removeClass("checkBoxDivSelect");}}
function selected(me){var myId=me.getAttribute("id");if(me.checked){$("#"+myId).parent().addClass("checkBoxDivSelect");}else{$("#"+myId).parent().removeClass("checkBoxDivSelect");}if(me.checked&&me.value=="46"){$("#primaryCameraType").slideDown("slow");}else{if(!me.checked&&me.value=="46"){$("#primaryCameraType").slideUp("slow");}}}
function selectedRadio(me){var myName=me.getAttribute("name");var myNameArray=document.getElementsByName(myName);for(var i=0;i<myNameArray.length;i++){var myId=myNameArray[i].getAttribute("id");if(myNameArray[i].checked){$("#"+myId).parent().addClass("checkBoxDivSelect");}else{$("#"+myId).parent().removeClass("checkBoxDivSelect");}}}
function openBlock(me,thisBlock){selectedRadio(me);var myName=me.getAttribute("name");var myNameArray=document.getElementsByName(myName);for(var i=0;i<myNameArray.length;i++){var myId=myNameArray[i].getAttribute("id");if(myNameArray[i].checked&&i==0){$("#"+thisBlock).slideDown("slow");break;}else{$("#"+thisBlock).slideUp("slow");break;}}}
function getMoreSearchedModels(action,method,contextPath){$("#more").hide();$("#loading").parent().addClass("ctr");$("#loading").show();var moreSearchQryStr=$("#qryStr").val().replace("&p=","&_=")+"&p="+$("#nextPage").val();var productContainer="<div class='modelContainer'><div class='modelImageBlock'><a title='{title}' href='{contextPath}{modelLink}'><img src='{modelImageUrl}' alt='{modelName}' title='{modelName}'></a><span class='productCaption'><a title='{modelName}' href='{contextPath}{modelLink}'>{modelName}</a></span><div class='price'>&#x20b9; {price}</div></div><div class='modelDesc'>{modelDesc}<div class='rating'>Rating :<span id='star_{page}_{index}'></span><input type='hidden' value='{starRating}' id='modelRating_{index}'></div><a class='more' title='{modelName}' href='{contextPath}{modelLink}'>more...</a></div></div>";$.get($("#contextPath").val()+"/"+method+"_"+action+".html",moreSearchQryStr,function(data){$.each(data.modelList,function(index,item){var pcI=productContainer.replace(/{title}/g,item.modelName);pcI=pcI.replace(/{modelImageUrl}/g,item.modelImageUrl);pcI=pcI.replace(/{modelId}/g,item.modelId);pcI=pcI.replace(/{modelName}/g,item.modelName);pcI=pcI.replace(/{price}/g,item.price);pcI=pcI.replace(/{modelDesc}/g,item.modelDesc);pcI=pcI.replace(/{page}/g,data.p);pcI=pcI.replace(/{index}/g,index);pcI=pcI.replace(/{starRating}/g,item.starRating);pcI=pcI.replace(/{contextPath}/g,contextPath);pcI=pcI.replace(/{searchType}/g,$("#searchType").val());pcI=pcI.replace(/{deviceId}/g,$("#deviceId").val());pcI=pcI.replace(/{brandId}/g,$("#brandId").val());pcI=pcI.replace(/{catgId}/g,$("#catgId").val());pcI=pcI.replace(/{modelLink}/g,item.modelLink);$("#searchResultModelGallery").append(pcI);var rating=item.starRating;$("#star_"+data.p+"_"+index).raty({half:true,score:rating,space:false,readOnly:true});});$("#nextPage").val(data.p);if(data.showMore){$("#more").show();}$("#loading").hide();});}
function getMoreQuickSearchedModels(action,method,contextPath){$("#more").hide();$("#loading").parent().addClass("ctr");$("#loading").show();var moreSearchQryStr="searchStr="+$("#searchStr").val()+"&p="+$("#nextPage").val()+"&dId="+$("#deviceId").val();var productContainer="<div class='modelContainer'> <div class='modelImageBlock'><a title='{title}' href='{contextPath}{modelLink}'><img src='{modelImageUrl}' alt='{modelName}' title='{modelName}'></a><span class='productCaption'><a title='{modelName}' href='{contextPath}{modelLink}'>{modelName}</a></span><div class='price'>&#x20b9; {price}</div></div><div class='modelDesc'>{modelDesc}<div class='rating'>Rating :<span id='star_{page}_{index}'></span><input type='hidden' value='{starRating}' id='modelRating_{index}'></div><a class='more' title='{modelName}' href='{contextPath}{modelLink}'>more...</a></div></div>";$.get($("#contextPath").val()+"/"+method+"_"+action+".html",moreSearchQryStr,function(data){$.each(data.modelList,function(index,item){var pcI=productContainer.replace(/{title}/g,item.modelName);pcI=pcI.replace(/{modelImageUrl}/g,item.modelImageUrl);pcI=pcI.replace(/{modelId}/g,item.modelId);pcI=pcI.replace(/{modelName}/g,item.modelName);pcI=pcI.replace(/{price}/g,item.price);pcI=pcI.replace(/{modelDesc}/g,item.modelDesc);pcI=pcI.replace(/{page}/g,data.p);pcI=pcI.replace(/{index}/g,index);pcI=pcI.replace(/{starRating}/g,item.starRating);pcI=pcI.replace(/{contextPath}/g,contextPath);pcI=pcI.replace(/{searchType}/g,$("#searchType").val());pcI=pcI.replace(/{deviceId}/g,$("#deviceId").val());pcI=pcI.replace(/{brandId}/g,$("#brandId").val());pcI=pcI.replace(/{catgId}/g,$("#catgId").val());pcI=pcI.replace(/{modelLink}/g,item.modelLink);$("#searchResultModelGallery").append(pcI);var rating=item.starRating;$("#star_"+data.p+"_"+index).raty({half:true,score:rating,space:false,readOnly:true});});$("#nextPage").val(data.p);if(data.showMore){$("#more").show();}$("#loading").hide();});}
function getTopRatedModels(){$("#loading").show();var dId=$("#deviceId").serialize();var d=new Date();if($("#deviceId").val()!=""){$.get($("#contextPath").val()+"/fetchTopRatedModels_DeviceAjaxAction.html?_="+d.getTime(),dId,function(data){if(data.modelList!=null&&data.modelList.length>0){$("#topRatedSideBar").append('<div class="blockHeader">Top Rated Models</div>');var topRatedContainer='<div class="productThumb"><div style="float: left;"><a href="{contextPath}{modelLink}"><img src="{modelImageUrl}" class="model-thumb" alt="{modelName}" /></a><div class="raty" ><div id="{star}"></div><span class="ratyCount">{ratingCount}<SPAN> votes</SPAN></span></div></div><a href="{contextPath}{modelLink}"><span class="caption">{modelName}</span><span class="desc">{modelDesc} ...</span></a></div>';$.each(data.modelList,function(index,item){var trcI=topRatedContainer.replace(/{modelName}/g,item.modelName);trcI=trcI.replace(/{modelDesc}/g,item.modelDesc.substring(0,80));trcI=trcI.replace(/{modelImageUrl}/g,item.modelImageUrl);trcI=trcI.replace(/{modelId}/g,item.modelId);trcI=trcI.replace(/{deviceId}/g,item.deviceId);trcI=trcI.replace(/{brandId}/g,item.manufacturerId);trcI=trcI.replace(/{price}/g,item.price);trcI=trcI.replace(/{star}/g,"star_"+index);trcI=trcI.replace(/{ratingCount}/g,item.ratingCount);trcI=trcI.replace(/{starRating}/g,item.starRating);trcI=trcI.replace(/{contextPath}/g,$("#contextPath").val());trcI=trcI.replace(/{modelLink}/g,item.modelLink);$("#topRatedSideBar").append(trcI);var rating=item.starRating;$("#star_"+index).raty({readOnly:true,hints:["Bad","Poor","Average","Good","Superb"],score:rating});});}else{$("#topRatedSideBar").append('<div class="blockHeader">Top Rated Models</div><SPAN>No Models found</SPAN>');}$("#loading").hide();});}}