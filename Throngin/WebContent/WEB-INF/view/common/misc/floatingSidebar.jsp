<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<style type="text/css">
	#floatingSidebar {width: 468px;  min-height: 100px;margin: 10px 0 15px 0;padding: 15px 10px;float: left;position:fixed;left:0px;top: 154px;border: 2px solid #059;z-index: 91;
		box-shadow:5px 5px 10px #888;-moz-box-shadow:5px 5px 10px #888;-webkit-box-shadow:5px 5px 10px #888;
		border-radius: 5px 5px;-moz-border-radius: 5px 5px;-webkit-border-radius: 5px 5px;background: #FBFBFB;background-image: -moz-linear-gradient(top,#fff,#FBFBFB);background-image: -webkit-gradient(linear,left bottom,left top,color-stop(0,#FBFBFB),color-stop(1,#fff));filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr='#FFFFFF',endColorstr='#eeeeff');-ms-filter: "progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr='#FFFFFF', endColorstr='#eeeeff')";}
	#floatingSidebar ul.cloud {list-style: none!important;}
	#mobileBrands li.cloudTag {background-color: #67adcf;display: inline;margin: 5px 5px 0 0;overflow: visible;padding: 10px 5px;width: auto;float: left;text-shadow: 0 1px 0 rgba(0,0,0,.3);font-size: 14px;border: 1px solid #FBFBFB;border-radius: 3px;}
	#mobileBrands li a:hover{color: #059!important;background-color: #fff!important;}
	#mobileBrands li a{color:#fff!important;font-family:monospace!important;text-decoration:none;padding: 10px 25px!important;}
	#floatingPin{float: left;position:fixed;left:-44px;top: 154px;background-color: #eef;
		box-shadow:5px 5px 10px #888;-moz-box-shadow:5px 5px 10px #888;-webkit-box-shadow:5px 5px 10px #888;
	}
	.rotate {
/* Safari */
-webkit-transform: rotate(90deg);
/* Firefox */
-moz-transform: rotate(90deg);
/* IE */
-ms-transform: rotate(90deg);
/* Opera */
-o-transform: rotate(90deg);
/* Internet Explorer */
filter: progid:DXImageTransform.Microsoft.BasicImage(rotation=0);
}
</style>
<div id="floatingPin" class="rotate" style="display: none;">
	<div class="blockHeader" style="cursor: pointer;" onmouseover="showFloatingSidebar()">Mobile Brands</div>
</div>
<div id="floatingSidebar" style="display: none;">
<div align="right" style="float: right;">
		<a href="#" class="close" title="Click to Close" onclick="hideFloatingSidebar()"><img align="top" alt="Close It" src="/images/closeIcon.png" width="25px" height="25px"></a>
</div>
<div class="blockHeader">Mobile Brands</div>
  <ul id="mobileBrands" class="cloud">
	<li class='cloudTag'><a href='http://gadgets.throngin.com/mobile/gallery/Nokia.html' class=''>Nokia</a></li>
	<li class='cloudTag'><a href='http://gadgets.throngin.com/mobile/gallery/Samsung.html' class=''>Samsung</a></li>
	<li class='cloudTag'><a href='http://gadgets.throngin.com/mobile/gallery/Motorola.html' class=''>Motorola</a></li>
	<li class='cloudTag'><a href='http://gadgets.throngin.com/mobile/gallery/Apple.html' class=''>Apple</a></li>
	<li class='cloudTag'><a href='http://gadgets.throngin.com/mobile/gallery/LG.html' class=''>LG</a></li>
	<li class='cloudTag'><a href='http://gadgets.throngin.com/mobile/gallery/Sony-Ericsson.html' class=''>Sony</a></li>
	<li class='cloudTag'><a href='http://gadgets.throngin.com/mobile/gallery/HTC.html' class=''>HTC</a></li>
	<li class='cloudTag'><a href='http://gadgets.throngin.com/mobile/gallery/Micromax.html' class=''>Micromax</a></li>
	<li class='cloudTag'><a href='http://gadgets.throngin.com/mobile/gallery/BlackBerry.html' class=''>BlackBerry</a></li>
	<li class='cloudTag'><a href='http://gadgets.throngin.com/mobile/gallery/Fly.html' class=''>Fly</a></li>
	<li class='cloudTag'><a href='http://gadgets.throngin.com/mobile/gallery/Huawei.html' class=''>Huawei</a></li>
	<li class='cloudTag'><a href='http://gadgets.throngin.com/mobile/gallery/IBall.html' class=''>IBall</a></li>
	<li class='cloudTag'><a href='http://gadgets.throngin.com/mobile/gallery/Intex.html' class=''>Intex</a></li>
	<li class='cloudTag'><a href='http://gadgets.throngin.com/mobile/gallery/Karbonn.html' class=''>Karbonn</a></li>
	<li class='cloudTag'><a href='http://gadgets.throngin.com/mobile/gallery/Lava.html' class=''>Lava</a></li>
	<li class='cloudTag'><a href='http://gadgets.throngin.com/mobile/gallery/Lemon.html' class=''>Lemon</a></li>
	<li class='cloudTag'><a href='http://gadgets.throngin.com/mobile/gallery/Lenovo.html' class=''>Lenovo</a></li>
	<li class='cloudTag'><a href='http://gadgets.throngin.com/mobile/gallery/Onida.html' class=''>Onida</a></li>
	<li class='cloudTag'><a href='http://gadgets.throngin.com/mobile/gallery/Spice.html' class=''>Spice</a></li>
	<li class='cloudTag'><a href='http://gadgets.throngin.com/mobile/gallery/Videocon.html' class=''>Videocon</a></li>
	<li class='cloudTag'><a href='http://gadgets.throngin.com/mobile/gallery/Acer.html' class=''>Acer</a></li>
	<li class='cloudTag'><a href='http://gadgets.throngin.com/mobile/gallery/Asus.html' class=''>Asus</a></li>
	<li class='cloudTag'><a href='http://gadgets.throngin.com/mobile/gallery/Dell.html' class=''>Dell</a></li>
	</ul>
</div>
<script type="text/javascript">
$(document).ready(function(){initFloatingSidebar();});
$(window).resize(function() {initFloatingSidebar();});
function initFloatingSidebar(){var winWidth=$(window).width();if(parseInt(winWidth)>=1160){$("#floatingPin").show();}else{$("#floatingPin").hide();}}
function hideFloatingSidebar(){$("#floatingSidebar").hide("slow");$("#floatingPin").show();}
function showFloatingSidebar(){$("#floatingSidebar").show("slow");$("#floatingPin").hide();}
</script>
