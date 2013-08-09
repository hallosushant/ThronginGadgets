<%@taglib prefix="s" uri="/struts-tags"%>
<%String path = request.getContextPath();%>
<!-- Already a User? Sign in! -->
<s:if test="%{msg!=null}">
	<b><s:property value="%{msg}" /></b>
</s:if>
<div style="text-align: center;">
	<div id="loginForm">
	<h3>Already a User? Sign in!</h3>
		<ul>
			<li><s:actionerror /> <s:actionmessage /></li>
			
			<li class="signInLi">
				<s:form action="login" namespace="/" id="loginFormId"
				validate="true"  
				title="Already a User? Sign in!" name="loginForm">
					<s:hidden name="loginAttempt" value="%{'1'}" />
					<s:textfield name="userEmail" id="userEmailId" label="Email Id" 
						cssErrorClass="validationError" size="40" maxlength="200" required="true"/>
					<s:password name="password" id="pwdId" label="Password" size="40" maxlength="32" required="true" autocomplete="false"/>
					<%--<tr><td align="right" colspan="2"><s:a href="forgotPassword.html">Forgot your Password?</s:a></td></tr>--%>
					<%--<s:checkbox name="rememberMe" value="Remember Me" id="rememberMeId" label="Remember Me" title="This will remember your login credintials for a week!"/>--%>
					<s:if test="%{#session.showCaptcha}">
						<tr>
						    <td class="tdLabel"><label for="loginFormId_captchaImage" class="label">Verification Code*:</label></td>
						    <td valign="baseline"><img src="jcaptchaImg.jpg" id="captchaImg" ></td>
						</tr>
						<tr>
							<td colspan="2" align="right">
								<a  href="#" title="Click to change the Verification Code" shape="rect" style="text-decoration: none;"
								onclick=" document.getElementById('captchaImg').src = document.getElementById('captchaImg').src + '?' + (new Date()).getMilliseconds()">
								<img alt="Click to generate a new verification code" src="<%=path%>/images/refresh_icon.png">Generate new verification code</a>
							</td>
						</tr>
						<s:textfield name="captchaResponse" label="Enter Verification Code*" maxLength="5" size="40"/>					
					</s:if>
					
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
<!--			                <input id="openid_submit" type="submit" value="Sign-In"/>-->
						</s:form>
		            </div>
			    
			<!-- /Simple OpenID Selector -->
			</li>
			<li class="signUpLi">
				Don't have an account? <s:a href="%{signUpURLId}" name="signUpLink" id="signUpId">Sign Up</s:a>
			</li>
		</ul>
	</div>
</div>