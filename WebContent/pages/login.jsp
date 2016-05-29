<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
var mainController = angular.element(document.querySelector('[ng-controller=mainCtrl]'));

mainController.scope().closeLoginDialog = function(){
	$("#loginId").hide();
	$("#forgetPassSection").hide();
	$('.login-div').css({height: "200px"});
	$('.outer-login-dialog .login-middle-strip-div .strip').css({height: "67px"});
	// hides all existing error message.
	hideAllErrorMessage();
	//Unbind 'Esc' event
	$(document).unbind( "keyup" );
}


function clickOnLogin(email, password){
	//hides existing error message
	hideAllErrorMessage();
	
	var valid_email = false;
	var valid_password = false;
	

	
	if(email != ''){
		valid_email = validateEmail(email);
	}
	
	//Validation for Password
	if(password != ''){
		valid_password = true;
	}
	
	if(valid_email == false){
		$('#j_username_error').html("Please enter valid Email");
		$('#j_username_error').show();
	}
	
	if(valid_password == false){
		$('#j_password_error').html("Please enter Password");
		$('#j_password_error').show();
	}	
	
	if(valid_email && valid_password){
		$('#loginProgress').show();
		$.ajax({
			url: "/mk/j_spring_security_check",
			data: "&j_username="+email+"&j_password="+password+"",
			context: document.body,
			type: 'POST'
		}).success(function(data, textStatus, jqXHR) {
			
			$('#loginProgress').hide();
			if(data.success){
				// Refresh the URL after getting success fron login service
				window.location.reload(true);
			}else{
				mainController.scope().loginErrorMessage = data.message;
				// Setting Error message recieved from login service
				$('#loginErrorMessage').html(data.message);
				$('#loginErrorMessage').slideDown(200);
			}
		}).error(function( jqXHR, textStatus, errorThrown){
			$('#loginProgress').hide();
			$('#loginErrorMessage').html('Somthing wrong happen.');
			$('#loginErrorMessage').slideDown(200);
		});
	}
}

function clickOnforgetPassword(){
	$('#forgetPassSection').slideToggle();
	
	// Toggel height of login dialog
	if($('.login-div').height() != 310)
		$('.login-div').animate({height: "310px"});
	else
		$('.login-div').animate({height: "200px"});
	
	// Toggel height of 'OR' strip of login dialog
	if($('.outer-login-dialog .login-middle-strip-div .strip').height() != 117)
		$('.outer-login-dialog .login-middle-strip-div .strip').animate({height: "117px"});
	else
		$('.outer-login-dialog .login-middle-strip-div .strip').animate({height: "67px"});
}

//Binding 'Enter' event on Password box of login dialog
$( "#j_password" ).keypress(function(event) {
	if(event.which == 13){
		var email = $("#j_username")[0].value;
		var password = $("#j_password")[0].value;
		
		clickOnLogin(email, password);
	}
});


function clickOnSendLink(){
	//hides existing error message
	hideAllErrorMessage();
	
	var valid_email = false;
	
	var email = $("#emailForgetPassword")[0].value;
	
	
	if(email != ''){
		valid_email = validateEmail(email);
	}
	
	if(valid_email == false){
		$('#emailForgetPassword_error').html("Please enter valid Email");
		$('#emailForgetPassword_error').show();
	}
	
	
	if(valid_email){
		$('#sendLinkProgress').show();
		$.ajax({
			url: "sendPasswordLink.do?emailForgetPassword="+email,
			context: document.body
		}).success(function(data, textStatus, jqXHR) {
			$('#sendLinkProgress').hide();
			// Setting message recieved from sendPasswordLink service
			$('#sendLinkErrorMessage').html(data.message);
			$('#sendLinkErrorMessage').slideDown(200);
		}).error(function( jqXHR, textStatus, errorThrown){
			$('#sendLinkProgress').hide();
			$('#sendLinkErrorMessage').html('Somthing Worng happen.');
			$('#sendLinkErrorMessage').slideDown(200);
		});
	}
}


//Binding 'Enter' event on Email box of forget password
$( "#emailForgetPassword" ).keypress(function(event) {
	if(event.which == 13){
		clickOnSendLink();
	}
});


function clickOnSignUp(){
	//hides existing error message
	hideAllErrorMessage();
	
	var valid_email = false;
	var valid_password = false;
	
	var email = $("#newUserEmail")[0].value;
	var password = $("#newUserPassword")[0].value;
	
	
	if(email != ''){
		valid_email = validateEmail(email);
	}
	
	//Validation for Password
	if(password != ''){
		valid_password = true;
	}
	
	if(valid_email == false){
		$('#newUserEmail_error').html("Please enter valid Email");
		$('#newUserEmail_error').show();
	}
	
	if(valid_password == false){
		$('#newUserPassword_error').html("Please enter Password");
		$('#newUserPassword_error').show();
	}	
	
	if(valid_email && valid_password){
		$('#signUpProgress').show();
		$.ajax({
			url: "registerNewUser.do?newUserEmail="+email+"&newUserPassword="+password,
			context: document.body
		}).success(function(data, textStatus, jqXHR) {
			alert(JSON.stringify(data));
			$('#signUpProgress').hide();
			if(data.success){
				// Refresh the URL after getting success fron login service
				clickOnLogin(email, password);
				
			}else{
				// Setting Error message recieved from login service
				$('#newUserErrorMessage').html(data.message);
				$('#newUserErrorMessage').slideDown(200);
			}
		}).error(function( jqXHR, textStatus, errorThrown){
			$('#signUpProgress').hide();
			$('#newUserErrorMessage').html('Somthing wrong happen.');
			$('#newUserErrorMessage').slideDown(200);
		});
	}
}

//Binding 'Enter' event on password box of Sign up section
$( "#newUserPassword" ).keypress(function(event) {
	if(event.which == 13){
		clickOnSignUp();
	}
});

function hideAllErrorMessage(){
	$('#j_username_error').hide();
	$('#j_password_error').hide();
	$('#loginErrorMessage').hide();
	$('#newUserEmail_error').hide();
	$('#newUserPassword_error').hide();
	$('#newUserErrorMessage').hide();
	$('#emailForgetPassword_error').hide();
	$('#sendLinkErrorMessage').hide();
}

function validateEmail(email) { 
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
} 
</script>
<style>
.outer-login-dialog{
	height: 100%;
	font-family: arial, san-sarif;
}
.outer-login-dialog .login-section{
	width: 260px;
	float: left;
	margin-left: 20px;
}

.outer-login-dialog .progress{
	float: left;
	position: absolute;
	display: none;
}
.outer-login-dialog .login-header-text{
	font-size: 20px;
	margin-left: -5px;
}
.outer-login-dialog .error-message{
	color: red;
	font-size: 13px;
	border: 1px solid;
	background-color: rgb(253, 234, 234);
	display: none;
}
.outer-login-dialog .field-error-message{
	color: red;
	font-size: 12px;
	margin-top: 10px;
	margin-left: 4px;
	background-color: rgb(253, 234, 234);
	display: none;
}
.outer-login-dialog .login-middle-strip-div{
/* 	height: 200px; */
	float: left;
}
.outer-login-dialog .login-middle-strip-div .strip{
	width: 2px;
	height: 67px;
	background: antiquewhite;
}
.outer-login-dialog .login-middle-strip-div .login-middle-strip-text{
	border: 1px solid antiquewhite;
	border-radius: 15px;
	height: 26px;
	width: 26px;
	color: rgb(182, 170, 154);
	background: antiquewhite;
	
}
.outer-login-dialog .login-middle-strip-div .login-middle-strip-text div{
	margin-top: 4px
}

.login-table-div{
	font-size: 15px;
	font-size: 15px;
	margin-top: 5px;
}

.login-table-div tr{
	height: 42px
}

.padding-top-3px{
	padding-top: 4px;
}
.outer-login-dialog .submit-button{
	font-size: 15px;
	width: 80px;
	background: #6486BD;
	color: aliceblue;
	border: 0px;
	padding: 5px;
}
.outer-login-dialog .new-user-section{
	width: 260px;
	float: left;
	margin-left: 20px;
/* 	height: 200px; */
}
.outer-login-dialog .forget-your-pass-text{
	font-size: 11px;
	color: blueviolet;
	cursor: pointer;
}
.outer-login-dialog .forgetYourPassHover{
	color: blue;
}
.forget-pass-section{
	margin-top: 10px;
	display: none;
}

.forget-pass-section .header-text{
	font-size: 15px;
	font-weight: bold;
	color: rgb(113, 113, 133);
}
.forget-pass-section .main-text{
	font-size: 11px;
}
.forget-pass-section .email-span{
	font-size: 15px;
}
.outer-login-dialog .bottom-div{
/* 	height: 10px; */
/* 	width: 100%; */
/* 	background: antiquewhite; */
/* 	float: left; */
}
.vertical-align-top{
	vertical-align: top;
}
</style>
<div class="outer-login-dialog" align="left">
	<table>
		<tbody>
			<tr>
				<td class="vertical-align-top">
					<div class="login-section">
						<form name='loginForm' action="j_spring_security_check" method='POST'>
							<div align="center"><span class="error-message" id="loginErrorMessage"></div>
							<div class="login-header-text">Login</span></div>
							<div class="login-table-div">
								<table>
									<tr>
										<td valign="top" class="padding-top-3px">Email :</td>
										<td valign="top">
											<input type='text' name='j_username' id="j_username" value=''>
											<span class="field-error-message" id="j_username_error"></span>
										</td>
									</tr>
									<tr>
										<td valign="top" class="padding-top-3px">Password:</td>
										<td valign="top">
											<input type='password' name='j_password' id="j_password" />
											<span class="field-error-message" id="j_password_error"></span>
										</td>
									</tr>
								</table>
							</div>
							<div align="center">
								<div class="progress" id="loginProgress">
									<img alt="" src="<%=request.getContextPath()%>/resources/image/progress.gif" class="">
								</div>
								<input name="submit" class="submit-button" type="button" value="LOGIN" onclick="clickOnLogin($('#j_username')[0].value, $('#j_password')[0].value)"/>
								<span class="forget-your-pass-text" onclick="clickOnforgetPassword()" ng-class="{forgetYourPassHover: forgetYourPassHoverFlag}" ng-mouseenter="forgetYourPassHoverFlag = true" ng-mouseleave="forgetYourPassHoverFlag = false">Forget Your Password?</span>
							</div>
							<c:if test="${not empty param['error']}"> 
					    		<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" /> 
					    		<c:remove var = "SPRING_SECURITY_LAST_EXCEPTION" scope = "session" />
							</c:if>
						</form>
							<div class="forget-pass-section" id="forgetPassSection">
							<div align="center"><span class="error-message" id="sendLinkErrorMessage"></span></div>
								<div class="header-text">
									Forget Your Password?
								</div>
								<div class="main-text">
									Enter your Email address to receive the forget password: 
								</div>
								<div align="center">
									<span class="email-span">Email :</span><input type="text" name="emailForgetPassword" id="emailForgetPassword">
									<span class="field-error-message" id="emailForgetPassword_error"></span>
								</div>
								<div align="center">
									<div class="progress" style="margin-left: 50px;" id="sendLinkProgress">
										<img alt="" src="<%=request.getContextPath()%>/resources/image/progress.gif" class="">
									</div>
									<input type="button" class="submit-button" value="Send Link" onclick="clickOnSendLink()">
								</div>
							</div>
					</div>
				</td>
				<td class="vertical-align-top">
					<div class="login-middle-strip-div" align="center">
						<div class="strip"></div>
						<div class="login-middle-strip-text"><div>OR</div></div>
						<div class="strip"></div>
					</div>
				</td>
				<td class="vertical-align-top">
					<div class="new-user-section">
						<form action="">
							<div align="center"><span class="error-message" id="newUserErrorMessage"></div>
							<div class="login-header-text">New User? Sign up</div>
							<div class="login-table-div">
								<table>
									<tr>
										<td valign="top" class="padding-top-3px">Email :</td>
										<td valign="top">
											<input type='text' name='newUserEmail' id='newUserEmail' value=''>
											<span class="field-error-message" id="newUserEmail_error"></span>
										</td>
									</tr>
									<tr>
										<td valign="top" class="padding-top-3px">Password:</td>
										<td valign="top">
											<input type='text' name='newUserPassword' id='newUserPassword' />
											<span class="field-error-message" id="newUserPassword_error"></span>
										</td>
									</tr>
								</table>
							</div>
							<div align="center">
								<div class="progress" style="margin-left: 50px;" id="signUpProgress">
									<img alt="" src="<%=request.getContextPath()%>/resources/image/progress.gif" class="">
								</div>
								<input name="submit" class="submit-button" type="button" value="SIGN UP" onclick="clickOnSignUp()" />
							</div>
						</form>
					</div>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="bottom-div"></div>
</div>