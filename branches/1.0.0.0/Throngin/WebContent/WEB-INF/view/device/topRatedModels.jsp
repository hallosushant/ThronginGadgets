<%@taglib uri="/struts-tags" prefix="s"%>
<% String path=request.getContextPath(); %>
<script type="text/javascript" src="<%=path%>/js/viewjs/device/device.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	getTopRatedModels();
});
</script>
<div>
	<div id="topRatedSideBar">
		<%-- Holds Place for TOP RATED MODELS loaded from ajax call device.js.getTopRatedModels() --%>
	</div>
</div>
