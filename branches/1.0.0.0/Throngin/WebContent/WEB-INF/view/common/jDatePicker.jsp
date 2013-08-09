<%String path = request.getContextPath();%>
<!-- jDatePicker -->
 <script type="text/javascript">
 $(document).ready(function() {
	$( "#datePicker" ).datepicker({
		showOn: "both",
		buttonImageOnly: true,
		buttonImage: "images/calendar.gif",
		buttonText: "Calendar",
		changeMonth: true,
		changeYear: true,
		dateFormat:"yy-mm-dd",
		showButtonPanel: true
	});		
});
 </script>