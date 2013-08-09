function previewModelImage(file){
	file.value=trimString(file.value);
	  if (navigator.appName == "Netscape")
	  {
		  previewImageNetscape(file);
	  }
	  else
	  {
		  previewImageIE(file);
	  }

}

function previewImageNetscape(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#selectedModelImageId').attr('src', e.target.result);
        };
        reader.readAsDataURL(input.files[0]);
    }
    else
    	previewImageIE(input);
}

function previewImageIE(input) {
	input.value=trimString(input.value);
	var imageFile=input.value;
	var Img = new Image();
    Img.src = input.value;
    if(imageFile.search("fakepath")>0){
    	var fakePathMsg="Please check your Internet Options, to allow local file upload. <br/> Go to <em>Internet Explorer>Tools>Internet Option>Security>Custom</em>,  find \"Include local directory path when uploading files to a server\" (it is quite a ways down) and click on \"Enable\"";
    	jAlert(fakePathMsg,'File Upload Error');
    	return false;
    }
    document.getElementById('selectedModelImageId').src = Img.src;
}

function isFieldValid(fieldValue,regExpr){
	if(fieldValue.match(regExpr)){
		return true;
	}
	else{
		return false;
	}
	
}

function trimString(field)
{
	return field.replace(/\s/g,"");
}

$(document).ready(function() {
	$("#manufacturerId").change(function(){
		var manufacturerId=$('#manufacturerId').val();
		if(manufacturerId!=-1)
			$("#addModelBtnId").attr('disabled', false);
		else
			$("#addModelBtnId").attr('disabled', true);
	});
	$("#modelId").change(function(){
		var modelId=$('#modelId').val();
		if(modelId!=-1)
			$("#editModelBtnId").attr('disabled', false);
		else
			$("#editModelBtnId").attr('disabled', true);
	});
	checkModelCategories();
});


function checkModelCategories(){
	var modelCategoryList=$("#modelCategoryList").val().replace("[","").replace("]","").split(",");
	for(var i=0;i<modelCategoryList.length;i++){
		modelCategoryList[i]=modelCategoryList[i].trim();
	}
	var allModelCategoryId=document.getElementsByName("modelCategoryId");
	for(var i=0;i<allModelCategoryId.length;i++){
		if(modelCategoryList.indexOf(allModelCategoryId[i].value)>-1){
			document.getElementById(allModelCategoryId[i].id).checked=true;
			selected(document.getElementById(allModelCategoryId[i].id));
		}
	}
	previewModelImage(document.getElementById("modelImageUrlId"));

}

function loadManufacturerList(){
	var device=$('#addNewModel_deviceId').serialize();	
	if($('#addNewModel_deviceId').val()!="")
	$.get('fetchManufacturerList_AdminAjaxAction.html',device,function(data) { 
		$("#manufacturerId").get(0).options.length = 0;
	    $("#manufacturerId").get(0).options[0] = new Option("Select a "+data.deviceName+" Manufacturer...", "-1");
	    $.each(data.manufacturerList, function(index, item) {
	        $("#manufacturerId").get(0).options[$("#manufacturerId").get(0).options.length] = new Option(item.MANUFACTURER_NAME,item.MANUFACTURER_ID );             
	        });
	});
	else{
		$("#manufacturerId").get(0).options.length = 0;
	    $("#manufacturerId").get(0).options[0] = new Option("Select Device Type...", "-1");
	}
}

function loadModelList(){
	var manufacturer=$('#manufacturerId').val();	
	var device=$('#addNewModel_deviceId').val();	
	var d=new Date();
	if($('#manufacturerId').val()!="" && $('#addNewModel_deviceId').val()!="")
	$.get('fetchModelList_AdminAjaxAction.html?deviceId='+device+"&manufacturerId="+manufacturer+"&_="+d.getTime(),function(data) { 
		$("#modelId").get(0).options.length = 0;
	    $("#modelId").get(0).options[0] = new Option("Select a "+data.manufacturerName+" Model...", "-1");
	    $.each(data.modelList, function(index, item) {
	        $("#modelId").get(0).options[$("#modelId").get(0).options.length] = new Option(item.MODEL_NAME,item.MODEL_ID );             
	        });
	});
	else{
		$("#modelId").get(0).options.length = 0;
	    $("#modelId").get(0).options[0] = new Option("Select Manufacturer ...", "-1");
	}
}