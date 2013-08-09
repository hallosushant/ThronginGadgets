$(document).ready(function() {

		$("#detailNameId").change(function(){
			var detailNameId=$('#detailNameId').val();
			if(detailNameId!=-1)
				$("#assignModelDetailSubmitBtnId").attr('disabled', false);
			else
				$("#assignModelDetailSubmitBtnId").attr('disabled', true);
		});

		$("#modelId").change(function(){
			var modelId=$('#modelId').val();
			if(modelId!='')
				$("#selectModelSubmitBtnId").attr('disabled', false);
			else
				$("#selectModelSubmitBtnId").attr('disabled', true);
		});
		
});

function loadDetailCategoryList(detailCategoryId){
	var detailCategory=$(detailCategoryId).serialize();	
	if($('#detailCategoryId').val()!="")
	$.get('fetchDetailNameList_AdminAjaxAction.html',detailCategory,function(data) {
		$("#detailNameId").get(0).options.length = 0;
	    $("#detailNameId").get(0).options[0] = new Option("Select a "+data.detailCategoryName+" Detail Name...", "-1");
	    $.each(data.detailNameList, function(index, item) {
	        $("#detailNameId").get(0).options[$("#detailNameId").get(0).options.length] = new Option(item.DETAIL_NAME,item.DETAIL_NAME_ID );             
	        });
	});
	else{
		$("#detailNameId").get(0).options.length = 0;
	    $("#detailNameId").get(0).options[0] = new Option("First Select Model Detail Category...", "-1");
	}
}

function removeRow(trElement){
	$(trElement).parent().parent().remove();
	var removeIconArray=document.getElementsByName("removeIcon");
	if(removeIconArray.length==1){
		removeIconArray[0].style.display='none';
	}
}


function addRow(){
	var detailNameArray=document.getElementsByName("detailName");
	var detailCategoryArray=document.getElementsByName("detailCategory");
	var detailValueArray=document.getElementsByName("detailValue");

	if(detailNameArray[0].value=='-1' || detailCategoryArray[0].value=='-1' || trimString(detailValueArray[0].value)==''){
		jAlert('Please select the current row details, before adding a new row.','Add Row Error');
		return false;
	}
	
	var rowcontent='<tr id="modelDetailTrId">'+$('#modelDetailTrId').clone().html().replace(/\n/g,"")+'</tr>';
	$('#modelDetailTableId tr:first').after(rowcontent);
	$("#detailNameId").get(0).options.length = 0;
    $("#detailNameId").get(0).options[0] = new Option("First Select Model Detail Category...", "-1");
    $("#detailCategoryId").val('-1');
    $("#detailValueId").val('');
	
	var removeIconArray=document.getElementsByName("removeIcon");
	for(var i=1;i<removeIconArray.length;i++)
		removeIconArray[i].style.display='block';

/*	$(detailCategoryArray[1]).removeAttr("onchange");
	$(detailNameArray[1]).attr('disabled', true);
	$(detailCategoryArray[1]).attr('disabled', true);
	$(detailValueArray[1]).attr('disabled', true);
*/	
	$("#detailCategoryId").focus();
}
			
function checkModelDetailList(){
	var detailCategoryArray=document.getElementsByName("detailCategory");
	
	for(var i=0;i<detailCategoryArray.length;i++){
			var currentRow=parseInt(i)+1;

			var detailCategory=trimString(detailCategoryArray[i].value);
			if(detailCategory=="-1"){
				jAlert("Please Select the Detail Category at row "+currentRow,'Add Row Error');
				return false;
			}

			var detailNameArray=document.getElementsByName("detailName");
			var detailName=trimString(detailNameArray[i].value);
			if(detailName=="-1"){
				jAlert("Please Select the Detail Name at row "+currentRow,'Add Row Error');
				return false;
			}

			var detailValueArray=document.getElementsByName("detailValue");
			var detailValue=trimString(detailValueArray[i].value);
			if(detailValue==""){
				jAlert("Please fill in the Detail Value at row "+currentRow,'Add Row Error');
				return false;
			}
			/*var isDetailValueValid=isFieldValid(detailValue,"^[A-Za-z0-9/()@;:~!,&\s.-]{1,1000}$");
			if(!isDetailValueValid){
				jAlert("Invalid Detail Value  at row "+currentRow,'Add Row Error');
				return false;
			}*/
		}
	var detailNameArray=document.getElementsByName("detailName");
	var detailCategoryArray=document.getElementsByName("detailCategory");
	var detailValueArray=document.getElementsByName("detailValue");

	for(var i=0;i<detailNameArray.length;i++){
		$(detailNameArray[i]).attr('disabled', false);
		$(detailCategoryArray[i]).attr('disabled', false);
		$(detailValueArray[i]).attr('disabled', false);
	}
		return true;
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
