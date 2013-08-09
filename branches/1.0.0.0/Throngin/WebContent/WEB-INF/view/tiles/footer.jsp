<%@taglib prefix="s" uri="/struts-tags"%>
<%String path=request.getContextPath(); %>
<%-- <div align="center">This is Footer</div> --%>
<div id="footer">
<%--
<div style="position: fixed;bottom: 0;left: 0;"><a href="http://gadgets.throngin.com/downloads/ThrongIn_Gadgets.apk" title="Download ThrongIn Gadgets Android App"><p style="text-color: #059 !Important;background: #059;">Download ThrongIn Gadgets</p><img src="http://reviewunit.com/wp-content/uploads/google_android_download.png"><p style="text-color: #059 !Important;background: #059;">Android App</p>
</a><p><a href="http://gadgets.throngin.com/downloads/ThrongIn_Gadgets.apk" title="Download ThrongIn Gadgets Android App"></a></p></div>
 --%>
	<div class="blockLeft">
		<div class="blockHeader">Popular Mobile Brands</div>
		<ul id="menuBrands">
			<s:url action="modelGallery" id="apple" escapeAmp="false">
				<s:param name="dId">1</s:param>
				<s:param name="bId">4</s:param>
			</s:url>
			<li><a href="<s:property value="#apple" escape="false"/>" ><img src="<%=path%>/images/brandIcons/16x16/Apple-icon.png">Apple</a></li>

			<s:url action="modelGallery" id="blackBerry" escapeAmp="false">
				<s:param name="dId">1</s:param>
				<s:param name="bId">9</s:param>
			</s:url>
			<li><a href="<s:property value="#blackBerry" escape="false"/>" ><img src="<%=path%>/images/brandIcons/16x16/Blackberry-icon.gif">BlackBerry</a></li>

			<s:url action="modelGallery" id="htc" escapeAmp="false">
				<s:param name="dId">1</s:param>
				<s:param name="bId">7</s:param>
			</s:url>
			<li><a href="<s:property value="#htc" escape="false"/>" ><img src="<%=path%>/images/brandIcons/16x16/HTC-icon.gif">HTC</a></li>
			
			<s:url action="modelGallery" id="lg" escapeAmp="false">
				<s:param name="dId">1</s:param>
				<s:param name="bId">5</s:param>
			</s:url>
			<li><a href="<s:property value="#lg" escape="false"/>" ><img src="<%=path%>/images/brandIcons/16x16/Lg-icon.png">LG</a></li>

			<s:url action="modelGallery" id="micromax" escapeAmp="false">
				<s:param name="dId">1</s:param>
				<s:param name="bId">8</s:param>
			</s:url>
			<li><a href="<s:property value="#micromax" escape="false"/>" ><img src="<%=path%>/images/brandIcons/16x16/Micromax-icon.png">Micromax</a></li>

			<s:url action="modelGallery" id="motorola" escapeAmp="false">
				<s:param name="dId">1</s:param>
				<s:param name="bId">3</s:param>
			</s:url>
			<li><a href="<s:property value="#motorola" escape="false"/>" >
				<img src="<%=path%>/images/brandIcons/16x16/Motorola-icon.gif">Motorola</a></li>
			
			<s:url action="modelGallery" id="nokia" escapeAmp="false">
				<s:param name="dId">1</s:param>
				<s:param name="bId">1</s:param>
			</s:url>
			<li><a href="<s:property value="#nokia" escape="false"/>" >
				<img src="<%=path%>/images/brandIcons/16x16/Nokia-icon.png">Nokia</a></li>
			
			<s:url action="modelGallery" id="samsung" escapeAmp="false">
				<s:param name="dId">1</s:param>
				<s:param name="bId">2</s:param>
			</s:url>
			<li><a href="<s:property value="#samsung" escape="false"/>" >
				<img src="<%=path%>/images/brandIcons/16x16/Samsung-icon.png">Samsung</a></li>
			
			<s:url action="modelGallery" id="sony" escapeAmp="false">
				<s:param name="dId">1</s:param>
				<s:param name="bId">6</s:param>
			</s:url>
			<li><a href="<s:property value="#sony" escape="false"/>" ><img src="<%=path%>/images/brandIcons/16x16/Sony-icon.gif">Sony</a></li>
		</ul>

	</div>
	<div class="blockMiddle">
			<div class="blockHeader">Follow US</div>
			<div style="padding-top: 5px;float: left; ">
				<iframe src="//www.facebook.com/plugins/likebox.php?href=http%3A%2F%2Fwww.facebook.com%2FThrongIn&amp;width=405&amp;height=258&amp;show_faces=true&amp;colorscheme=dark&amp;stream=false&amp;border_color=%23001a42&amp;header=false&amp;appId=302268889860278" scrolling="no" frameborder="0" style="border:none; overflow:hidden; width:405px; height:258px;" allowTransparency="true"></iframe>
			</div>
			<div style="padding-top: 20px;float: right;">
				<!-- AddThis Follow BEGIN -->
				<div class="addthis_toolbox addthis_32x32_style addthis_vertical_style">
				<a class="addthis_button_facebook_follow" addthis:userid="ThrongIn"></a>
				<a class="addthis_button_twitter_follow" addthis:userid="ThrongIn"></a>
				<a class="addthis_button_google_follow" addthis:userid="117404971532726116688"></a>
				<a class="addthis_button_youtube_follow" addthis:userid="ThrongIn"></a>
				<a class="addthis_button_pinterest_follow" addthis:userid="ThrongIn"></a>
				<a class="addthis_button_rss_follow" addthis:userid="http://feeds.feedburner.com/ThronginGadgets"></a>
				</div>
				<script type="text/javascript" src="http://s7.addthis.com/js/300/addthis_widget.js#pubid=ra-50dedf7b4f2efe5c"></script>
				<!-- AddThis Follow END -->
			</div>
	</div>
	<div class="blockRight">
			<div class="blockHeader">Mobile Category</div>

		<ul id="menuCatg">
			<s:url action="modelGallery" method="catgModels" id="smartphone" escapeAmp="false">
				<s:param name="dId">1</s:param>
				<s:param name="cId">8</s:param>
			</s:url>
			<li><a href="<s:property value="#smartphone" escape="false"/>" >Smartphone Mobile</a></li>
			
			<s:url action="modelGallery" method="catgModels" id="touchscreen" escapeAmp="false">
				<s:param name="dId">1</s:param>
				<s:param name="cId">4</s:param>
			</s:url>
			<li><a href="<s:property value="#touchscreen" escape="false"/>" >Touchscreen Mobile</a></li>
			
			<s:url action="modelGallery" method="catgModels" id="QWERTY" escapeAmp="false">
				<s:param name="dId">1</s:param>
				<s:param name="cId">5</s:param>
			</s:url>
			<li><a href="<s:property value="#QWERTY" escape="false"/>" >QWERTY Mobile</a></li>
			
			<s:url action="modelGallery" method="catgModels" id="dualSIM" escapeAmp="false">
				<s:param name="dId">1</s:param>
				<s:param name="cId">24</s:param>
			</s:url>
			<li><a href="<s:property value="#dualSIM" escape="false"/>" >Dual SIM</a></li>
			
			<s:url action="modelGallery" method="catgModels" id="threeG" escapeAmp="false">
				<s:param name="dId">1</s:param>
				<s:param name="cId">29</s:param>
			</s:url>
			<li><a href="<s:property value="#threeG" escape="false"/>" >3G Mobile</a></li>

			<s:url action="modelGallery" method="catgModels" id="GSM" escapeAmp="false">
				<s:param name="dId">1</s:param>
				<s:param name="cId">28</s:param>
			</s:url>
			<li><a href="<s:property value="#GSM" escape="false"/>" >GSM Mobile</a></li>

			<s:url action="modelGallery" method="catgModels" id="CDMA" escapeAmp="false">
				<s:param name="dId">1</s:param>
				<s:param name="cId">27</s:param>
			</s:url>
			<li><a href="<s:property value="#CDMA" escape="false"/>" >CDMA Mobile</a></li>

			<s:url action="modelGallery" method="catgModels" id="JavaMobile" escapeAmp="false">
				<s:param name="dId">1</s:param>
				<s:param name="cId">67</s:param>
			</s:url>
			<li><a href="<s:property value="#JavaMobile" escape="false"/>" >Java Mobile</a></li>
			
			<s:url action="modelGallery" method="catgModels" id="cameraMobile" escapeAmp="false">
				<s:param name="dId">1</s:param>
				<s:param name="cId">9</s:param>
			</s:url>
			<li><a href="<s:property value="#cameraMobile" escape="false"/>" >Camera Mobile</a></li>			
		</ul>
	</div>
<div style="clear: both;"></div>
<div style="overflow: auto;padding: 10px 0px;">
	<p class="copyright">&copy;&nbsp;&nbsp;2013 All Rights Reserved </p>
</div>

</div>
<script language="javascript" src="<%=path%>/js/jquery.jsonSuggest.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.raty.min.js"></script>
<s:include value="../common/jAlert.jsp"></s:include>
 <link href="<%=path%>/css/jquery-ui.custom.css" rel="stylesheet" type="text/css" media="screen" />
 <SCRIPT type="text/javascript" src="<%=path%>/js/jquery-ui.custom.min.js"></SCRIPT>
 <s:include value="../common/disqusComment.jsp"></s:include>
 <s:include value="../common/googleAnalytics.jsp"></s:include>
 <s:include value="../common/misc/floatingSidebar.jsp"></s:include>