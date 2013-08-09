<%@taglib prefix="s" uri="/struts-tags"%>
<%String path = request.getContextPath();%>
<!-- Don't have an Account? Register! -->   
<script type="text/javascript">
$(document).ready(function() {
		$("#userName").blur(function(){
			var formInput=$(this).serialize();	
			$.get('checkUserNameAvailability_AjaxLoginAction.html',formInput,function(data) {
				$('#userName').parent().append('<label class="errorMessage" id="userNameAvailabilityMsg">'+data.isUserNameAvailable+'</label>');
			});
		});
		$("#userName").focus(function(){
			$('#userNameAvailabilityMsg').remove();
		});	
});
</script>
<div class="sideBySideBlocksLeft sideBySideBlocks">
	<div id="showOnTopBlock">
		<h3>Don't have an Account? Register!</h3>
		<ul>
			<li><s:actionerror /> <s:actionmessage /></li>
			<li>

		<s:form name="signUpForm" id="signUpFormId" namespace="/" action="signUpAction" cssStyle="width:">
			<s:textfield name="userName" id="userName" label="Select User Name" size="40" maxlength="45" />
			<s:textfield name="userEmail" label="Your Email-Id*" size="40" maxlength="200"/>
			<s:password name="password" label="Choose Password*" size="40" maxlength="32" autocomplete="false"/>
			<s:password name="confirmPassword" label="Confirm Password*" size="40" maxlength="32" required="true" autocomplete="false"/>
			<s:select list="#application.hintQuestionList" name="selectedHintQuestion" id="selectedHintQuestionId" 
			headerKey="0" headerValue="Select Security Question..." label="Security Question" 
			key="HINT_QUESTION_ID" value="HINT_QUESTION" required="true" title="Select a Security Question whose answer is known to you only."/>
			<s:textfield name="hintAnswer" label="Hint Answer*" size="40" maxlength="100"/>
			
			<tr>
			    <td class="tdLabel"><label for="signUpFormId_captchaImage" class="label">Verification Code*:</label></td>
			    <td valign="baseline"><img src="jcaptchaImg.jpg" id="captchaImg" ></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<a  href="#" title="Click to change the Verification Code" shape="rect" style="text-decoration: none;"
					onclick=" document.getElementById('captchaImg').src = document.getElementById('captchaImg').src + '?' + (new Date()).getMilliseconds()">
					<img alt="" src="<%=path%>/images/refresh_icon.png">Try a new code</a>
				</td>
			</tr>
			<s:if test="%{msg.equals('wrongVerificationCode')}">
			<tr>
			    <td align="center" valign="top" colspan="2"><span class="errorMessage">Wrong Verification Code, Please try again.</span></td>
			</tr>
			</s:if>
			<s:textfield name="captchaResponse" label="Enter Verification Code*" maxLength="5" size="40"/>
			
			<s:submit value="Register" name="submitButton" id="submitButtonId" align="center"  cssClass="button"/>
		
		</s:form></li>
		</ul>
	</div>
</div>