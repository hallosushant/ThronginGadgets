<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%String path=request.getContextPath(); String remoteAddr=request.getRemoteAddr();%>
<link href="<%=path%>/css/allThrongin.min.css" rel="stylesheet" type="text/css"	media="screen" />
<link rel="alternate" type="application/rss+xml" title="ThrongIn Gadgets - RSS" href="http://feeds.feedburner.com/ThronginGadgets" />
<script>window.jQuery || document.write('<script src="<%=path%>/js/jquery.js"><\/script>')</script>
<script type="text/javascript" src="<%=path%>/js/viewjs/tiles/navbar.js"></script>
<!-- Navbar -->
<s:i18n name="com.sushant.verma.common">
	<input type="hidden" name="contextPath" id="contextPath" value="<%=path%>">
	<input type="hidden" name="remoteAddr" id="remoteAddr" value="<%=remoteAddr%>">
	<s:hidden name="mId" id="mId" value="%{mId}"></s:hidden>
	<s:hidden name="dId" id="deviceId" value="%{dId}"></s:hidden>
	<s:hidden name="bId" id="brandId" value="%{bId}"></s:hidden>
	<s:hidden name="cId" id="catgId" value="%{cId}"></s:hidden>
	<s:url value="/home.html" id="homeLink"></s:url>
	<div id="nav" align="center">		
		<ul id="main">
			<li class="throngInHome"><img src="<%=path%>/images/ThrongIn_Logo.png"/><s:a href="%{#homeLink}" cssClass="home">ThrongIn</s:a></li>
			<li id="home" class="current_page_item"><s:a href="%{#homeLink}" cssClass="home">Home</s:a></li>
			<li onmouseover="showMenu(this,'MobilesMenu')" onmouseout="hideMenu(this,'MobilesMenu')">
				<s:url action="modelGallery" id="mobiles" escapeAmp="false">
					<s:param name="dId">1</s:param>
				</s:url>
				<a href="<s:property value="#mobiles" escape="false"/>" >Mobiles</a>
				<div id="MobilesMenu" class="thrColFix" style="display:none;">
					<div id="container">
					 <div id="col1">
					 	<p class="header">Mobiles</p>
						<img alt="mobiles" src="<%=path%>/images/device/mobile.png">
						<!-- end #col1 --></div>
					  <div id="col2">
					  <h3>Mobile Brands</h3>
						<ul id="menuBrands">
							<s:url action="modelGallery" id="apple" escapeAmp="false"><s:param name="dId">1</s:param><s:param name="bId">4</s:param></s:url>
							<li><a href="<s:property value="#apple" escape="false"/>" ><img src="<%=path%>/images/brandIcons/16x16/Apple-icon.png">Apple</a></li>
							<s:url action="modelGallery" id="blackBerry" escapeAmp="false"><s:param name="dId">1</s:param><s:param name="bId">9</s:param></s:url>
							<li><a href="<s:property value="#blackBerry" escape="false"/>" ><img src="<%=path%>/images/brandIcons/16x16/Blackberry-icon.gif">BlackBerry</a></li>
							<s:url action="modelGallery" id="htc" escapeAmp="false"><s:param name="dId">1</s:param><s:param name="bId">7</s:param></s:url>
							<li><a href="<s:property value="#htc" escape="false"/>" ><img src="<%=path%>/images/brandIcons/16x16/HTC-icon.gif">HTC</a></li>
							<s:url action="modelGallery" id="lg" escapeAmp="false"><s:param name="dId">1</s:param><s:param name="bId">5</s:param></s:url>
							<li><a href="<s:property value="#lg" escape="false"/>" ><img src="<%=path%>/images/brandIcons/16x16/Lg-icon.png">LG</a></li>
							<s:url action="modelGallery" id="micromax" escapeAmp="false"><s:param name="dId">1</s:param><s:param name="bId">8</s:param></s:url>
							<li><a href="<s:property value="#micromax" escape="false"/>" ><img src="<%=path%>/images/brandIcons/16x16/Micromax-icon.png">Micromax</a></li>
							<s:url action="modelGallery" id="motorola" escapeAmp="false"><s:param name="dId">1</s:param><s:param name="bId">3</s:param></s:url>
							<li><a href="<s:property value="#motorola" escape="false"/>" ><img src="<%=path%>/images/brandIcons/16x16/Motorola-icon.gif">Motorola</a></li>
							<s:url action="modelGallery" id="nokia" escapeAmp="false"><s:param name="dId">1</s:param><s:param name="bId">1</s:param></s:url>
							<li><a href="<s:property value="#nokia" escape="false"/>" ><img src="<%=path%>/images/brandIcons/16x16/Nokia-icon.png">Nokia</a></li>
							<s:url action="modelGallery" id="samsung" escapeAmp="false"><s:param name="dId">1</s:param><s:param name="bId">2</s:param></s:url>
							<li><a href="<s:property value="#samsung" escape="false"/>" ><img src="<%=path%>/images/brandIcons/16x16/Samsung-icon.png">Samsung</a></li>
							<s:url action="modelGallery" id="sony" escapeAmp="false"><s:param name="dId">1</s:param><s:param name="bId">6</s:param></s:url>
							<li><a href="<s:property value="#sony" escape="false"/>" ><img src="<%=path%>/images/brandIcons/16x16/Sony-icon.gif">Sony</a></li>
						</ul>
						<!-- end #col2 --></div>
					   <div id="col3">
						<h3>Mobile Category</h3>
						<ul id="menuCatg">
							<s:url action="modelGallery" method="catgModels" id="smartphone" escapeAmp="false"><s:param name="dId">1</s:param><s:param name="cId">8</s:param></s:url>
							<li><a href="<s:property value="#smartphone" escape="false"/>" >Smartphone Mobile</a></li>
							<s:url action="modelGallery" method="catgModels" id="touchscreen" escapeAmp="false"><s:param name="dId">1</s:param><s:param name="cId">4</s:param></s:url>
							<li><a href="<s:property value="#touchscreen" escape="false"/>" >Touchscreen Mobile</a></li>
							<s:url action="modelGallery" method="catgModels" id="QWERTY" escapeAmp="false"><s:param name="dId">1</s:param><s:param name="cId">5</s:param></s:url>
							<li><a href="<s:property value="#QWERTY" escape="false"/>" >QWERTY Mobile</a></li>
							<s:url action="modelGallery" method="catgModels" id="dualSIM" escapeAmp="false"><s:param name="dId">1</s:param><s:param name="cId">24</s:param></s:url>
							<li><a href="<s:property value="#dualSIM" escape="false"/>" >Dual SIM</a></li>
							<s:url action="modelGallery" method="catgModels" id="threeG" escapeAmp="false"><s:param name="dId">1</s:param><s:param name="cId">29</s:param></s:url>
							<li><a href="<s:property value="#threeG" escape="false"/>" >3G Mobile</a></li>
							<s:url action="modelGallery" method="catgModels" id="GSM" escapeAmp="false"><s:param name="dId">1</s:param><s:param name="cId">28</s:param></s:url>
							<li><a href="<s:property value="#GSM" escape="false"/>" >GSM Mobile</a></li>
							<s:url action="modelGallery" method="catgModels" id="CDMA" escapeAmp="false"><s:param name="dId">1</s:param><s:param name="cId">27</s:param></s:url>
							<li><a href="<s:property value="#CDMA" escape="false"/>" >CDMA Mobile</a></li>
							<s:url action="modelGallery" method="catgModels" id="JavaMobile" escapeAmp="false"><s:param name="dId">1</s:param><s:param name="cId">67</s:param></s:url>
							<li><a href="<s:property value="#JavaMobile" escape="false"/>" >Java Mobile</a></li>
							<s:url action="modelGallery" method="catgModels" id="cameraMobile" escapeAmp="false"><s:param name="dId">1</s:param><s:param name="cId">9</s:param></s:url>
							<li><a href="<s:property value="#cameraMobile" escape="false"/>" >Camera Mobile</a></li>
						</ul>
						<!-- end #col3 --></div>
					  <div id="col4">
					  	<div style="text-align: center;">
						  	<a href="http://gadgets.throngin.com/downloads/ThrongIn_Gadgets.apk" title="Download ThrongIn Gadgets Android App">
						  		<p>Download ThrongIn Gadgets</p>
						  		<img src="http://gadgets.throngin.com/images/Download_Throngin_Gadgets_Android_App_sq.png">
						  		<p>Android App</p>
							</a>
						</div>
						<%--
					  	<span class="advertisementText">Advertisement</span>
						<s:include value="../common/advFormats/displayAd_250x250.jsp"></s:include>
						 --%>
					  <!-- end #col4 --></div>
						<br class="clearfloat" />
					<!-- end #container --></div>
				</div>
			</li>

			<li onmouseover="showMenu(this,'LaptopsMenu')" onmouseout="hideMenu(this,'LaptopsMenu')">
				<a style="cursor: pointer">Laptops</a>
				<div id="LaptopsMenu" class="thrColFix" style="display:none;">
					<div id="container">
					 <div id="col1">
					 	<p class="header">Laptops</p>
						<img alt="mobiles" src="<%=path%>/images/device/laptop.png">
						<!-- end #col1 --></div>
					  	<div  style="text-align: center;margin-top:50px;color: #059; " class="header">
					  		<h2>Comming Soon</h2>
					   	</div>
						<br class="clearfloat" />
					<!-- end #container --></div>
				</div>
			</li>
			<li onmouseover="showMenu(this,'TabletsMenu')" onmouseout="hideMenu(this,'TabletsMenu')">
				<a style="cursor: pointer">Tablets</a>
				<div id="TabletsMenu" class="thrColFix" style="display:none;">
					<div id="container">
					 <div id="col1">
					 	<p class="header">Tablets</p>
						<img alt="mobiles" src="<%=path%>/images/device/tablet-pc.jpg">
						<!-- end #col1 --></div>
					  	<div  style="text-align: center;margin-top:50px;color: #059; " class="header">
					  		<h2>Comming Soon</h2>
					   	</div>					   
						<br class="clearfloat" />
					<!-- end #container --></div>
				</div>
			</li>
			<s:if test="%{#session.loggedInUser==null}">
				<s:url action="signIn" id="signInURLId" />
				<s:url action="signUp" id="signUpURLId" />
				<li class="liRight">
					<s:a href="%{signUpURLId}" name="signUpLink" id="signUpId" rel="nofollow">Sign Up</s:a>
				</li>
				<li class="liRight">
<!--					<s:a href="%{signInURLId}" name="signInLink" id="signInLinkId">SignIn</s:a>-->
						<a href="#loginBlock" name="modal">SignIn</a>
				</li>
			</s:if>
			<s:else>
				<s:url action="logoutAction" id="logoutUrlId"/>
			    <li class="liRight">
			    	<s:a href="%{logoutUrlId}" name="logoutLink" id="logoutLinkId" theme="simple">Logout</s:a>
			    </li>
			    <li class="userHome">
					<s:url value="/userhome.html" id="homeLink"></s:url>
	               	<s:a href="%{#homeLink}" cssClass="home"></s:a>
				</li>
			    <li>
			    	<p class="welcomeUser">Welcome <s:property value="#session.loggedInUser.userName" /></p>
			    </li>
			</s:else>
		</ul>	
	</div>
	<div id="navSub">
		<s:a href="%{#homeLink}" cssClass="gadgetsLogo">Gadgets</s:a>
	</div>
	<a href="#loginBlock" name="modal" style="display: none;"></a>
	<div style="clear: both"></div>
	
<div id="boxes">
<div id="loginBlock" class="window">
	<div class="loginLoading" style="display: none;"><img alt="loading" src="<%=path%>/images/loadingCircle.gif" id="loadingCircle"></div>
	<div id="loginForm">
	<div align="right">
		<a href="#" class="close" title="Click to Close" /><img align="top" alt="Close It" src="<%=path%>/images/closeIcon.png"></a>
	</div>
	
	<h3>Already a User? Sign in!</h3>
		<ul>
			<li><div id="loginMsg" style="display: none">
					<div class="info"><s:text name="text.signin.to.review"></s:text></div>
				</div>
				<s:actionerror /> <s:actionmessage />
			</li>
			<li class="signInLi">
				<s:form action="login" namespace="/" id="loginFormModalId"
				validate="true"  
				title="Already a User? Sign in!" name="loginFormModal">
					<s:hidden name="loginAttempt" value="%{'1'}" />
					<s:textfield name="userEmail" id="userEmailId" label="Email Id" 
						cssErrorClass="validationError" size="40" maxlength="200" required="true"/>
					<s:password name="password" id="pwdId" label="Password" size="40" maxlength="32" required="true" autocomplete="false"/>
				<%--<tr><td align="right" colspan="2"><s:a href="forgotPassword.html">Forgot your Password?</s:a></td></tr>--%>
				<%--<s:checkbox name="rememberMeModal" value="Remember Me" id="rememberMeModalId" label="Remember Me" title="This will remember your login credintials for a week!"/>--%>
					<s:if test="%{#session.showCaptcha}">
						<s:hidden id="winDiv" value="3"></s:hidden>
						<tr>
						    <td class="tdLabel"><label for="loginFormId_captchaImage" class="label">Verification Code*:</label></td>
						    <td valign="baseline"><img src="jcaptchaImg.jpg" id="captchaImgModal" ></td>
						</tr>
						<tr>
							<td colspan="2" align="right">
								<a  href="#" title="Click to change the Verification Code" shape="rect" style="text-decoration: none;"
								onclick=" document.getElementById('captchaImgModal').src = document.getElementById('captchaImgModal').src + '?' + (new Date()).getMilliseconds()">
								<img alt="Click to generate a new verification code" src="<%=path%>/images/refresh_icon.png">Generate new verification code</a>
							</td>
						</tr>
						<s:textfield name="captchaResponse" label="Enter Verification Code*" maxLength="5" size="40"/>					
					</s:if>
					<s:else>
						<s:hidden id="winDiv" value="2"></s:hidden>
					</s:else>
					<s:submit name="loginSubmit" id="loginSubmitId" value="Sign In"	 cssClass="button"/>
					
			</s:form></li>
			<li style="padding: 5px;color: #059;font-size: small;border-bottom: 1px dotted #aaa;">
				 <p style="text-align: left;">Else click your account provider:</p>
			</li>
			<li>
			<!-- Simple OpenID Selector -->
				
		           <div id="openid_choice">
		                <div id="openidBtns" style="text-align: center;">
		                	<ul>
			                	<li title="Click to Signin with Google"  id="google" class="loginBtn google" onclick="javascript:openSignin('google');"></li>
			                	<li title="Click to Signin with Facebook" id="fbBtn" class="loginBtn facebook" onclick="javascript:fbLogin();"></li>
			                	<li title="Click to Signin with Yahoo"  id="yahoo" class="loginBtn yahoo" onclick="javascript:openSignin('yahoo');"></li>
		                	</ul>
			               		<div style="display: none;">
			                		<div id="fb-root"></div>
			                		<div class="fb-login-button" scope="email,user_checkins">Login with Facebook</div>
			                	</div> 
								<div align="center">
			                		<s:form action="fbLogin" id="fbLoginForm" name="fbLoginForm" theme="simple">
								        <s:hidden name="userName" id="fbUserName"></s:hidden>
								        <s:hidden name="userEmail" id="fbUserEmail"></s:hidden>
							        </s:form>
								</div>
		                </div>
		            </div>
		            <div id="openid_input_area">
		            	<s:form action="openLogin" id="openLoginForm" name="openLoginForm" theme="simple">
		                		<input id="openid_identifier" name="openid_identifier" type="hidden" value="http://" />
<%--			                <input id="openid_submit" type="submit" value="Sign-In"/>--%>
						</s:form>
		            </div>
			    
			<!-- /Simple OpenID Selector -->
			</li>
			<li class="signUpLi">
				Don't have an account? <s:a href="%{signUpURLId}" name="signUpLink" id="signUpId" rel="nofollow">Sign Up</s:a>
			</li>
		</ul>
	</div>
</div>
<!-- Mask to cover the whole screen -->
  <div id="mask"></div>
</div>
<div id="fixedTopDiv" style="color: #059">.</div>
<div id="fixedBtmDiv" style="color: #fff">.</div>
</s:i18n>
<SCRIPT type="text/javascript">
window.fbAsyncInit = function() {FB.init({appId:'576653389016460',status:true,cookie:true,xfbml:true,oauth:true});};
(function(d){var js,id='facebook-jssdk';if(d.getElementById(id)){return;}js=d.createElement('script');js.id=id;js.async=true;js.src="//connect.facebook.net/en_US/all.js";d.getElementsByTagName('head')[0].appendChild(js);}(document));
</SCRIPT>