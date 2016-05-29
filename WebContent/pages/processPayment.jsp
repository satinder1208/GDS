<%@page import="com.mk.services.UserService"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="mkApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="shortcut icon" href="<%=request.getContextPath()%>/resources/image/mk_small.png" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery-ui.css">
<script src="<%=request.getContextPath()%>/resources/js/jquery-2.1.1.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery-ui.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/angular.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/mkPay.js"></script>

<script type="text/javascript">

var initializeUser = function(scope){ 
	scope.user =  <%= new ObjectMapper().writer().writeValueAsString(((UserService)ContextHolder.getApplicationContext().
			getBean("userService")).getUser(session).getBody())%>;
}

</script>

<style type="text/css">
body{
	margin: 0px;
    overflow-x: auto;
    min-width: 1100px;
}
.no-item-overlay{
	height: 100%;
	width: 100%;
	position: fixed;
	background-color: rgba(0, 0, 0, 0.9);
	z-index: 11;
	display: block;
	top: 0px;
}
.no-item-overlay table{
	height: 100%;
}
.no-item-overlay .infoMsgDiv{
	background: whitesmoke;
	padding: 10px;
	font-family: arial, san-sarif;
	border-radius: 5px;
	width: 150px;
	display: none;
}
.no-item-overlay .infoMsgDiv .msg{
	margin: 10px;
}


.button{
	background: #FCC4AB;
	font-size: 14px;
	color: white;
	border: 0px;
	padding: 5px;
	cursor: pointer;
}
.buttonHover{
	background: #f75b16;
}
.buttonHover2{
	background: #f75b16;
}

.button2{
	width: 120px;
	background: #175ed4;
	font-size: 14px;
	color: white;
	border: 0px;
	padding: 5px;
	cursor: pointer;
	border-radius: 3px;
}
.addressHeaderNameAddressDiv{
	margin-left: 30px;
}
.addressHeaderNameAddressDiv .addressHeaderNameMobileDiv{
	font-size: 20px;
}
.addressHeaderNameAddressDiv .addressHeaderNameMobileDiv .mobileSpan{
	font-size: 14px;
}
.addressHeaderNameAddressDiv .addressHeaderAddressDiv{
	font-size: 14px;
}
.addressHeaderNameAddressDiv .addressHeaderAddressDiv .flatNo{
	font-size: 14px;
	font-weight: bold;
}



.pageHeader{
	height: 60px;
	background: black;
	width: 100%;
	min-width: 1024px;
	box-shadow: 0px 1px 1px grey;
}
.logoDiv{
	float:left;
	font-family: "Comic Sans MS", cursive, sans-serif;
	font-size: 36px;
	font-weight: 700;
	padding-left: 10px;
	height: 60px;
}
.morningSpan{
	color:rgb(255, 235, 8);
}
.kartSpan{
	color: orangered;
}
#myCartPayContent{
	display: none;
}
.cross-icon {
	background-image: url("./resources/image/icons.png");
	background-repeat: no-repeat;
	background-position: 0 -447px;
	display: inline-block;
	width: 18px;
	height: 20px;
	zoom: 1;
	vertical-align: middle;
	cursor: pointer;
}
.paymentSectionsOuterDiv{
	width: 950px;
	box-shadow: 0px 0px 1px grey;
}
.paymentSectionsOuterDiv .paymentSectionsHeader{
	background-color: rgb(252, 252, 252);
	font-family: arial, san-sarif;
	border: 0px solid gray;
	margin-top: 10px;
	font-size: 17px;
	padding: 12px;
	color: rgb(79, 79, 79);
	margin-bottom: 15px;
	cursor: pointer;
}
.paymentSectionsOuterDiv .bottomMyCartPayBlock{
	padding-top: 10px;
	padding-bottom: 10px;
}
.paymentSectionsOuterDiv .continueToPayment{
	margin-right: 10px;
	font-family: arial, san-sarif;
	font-size: 18px;
	background: #f75b16;
	border: 0px;
	padding: 5px;
	width: 100px;
}
.paymentSectionsOuterDiv .continueToPayment a{
	text-decoration: none!important;
	color: white;
}
.paymentLoginContent{
	width: 600px;
	padding-bottom: 10px;
}
.ui-autocomplete-category{
	font-weight: bold;
	padding: 5px;
	padding-left: 10px;
}
.ui-autocomplete-item-div{
	height: 40px;
}
.ui-autocomplete-item-img-div{
	float: left;
}
.ui-autocomplete-item-img{
	height: 40px;
}
.ui-autocomplete-item-name-div{
	float:left;
	height: 30px;
	margin-top: 10px;
	margin-left: 5px;
}
.ui-autocomplete-item-name-span{
}
.ui-widget{
	font-family: Arial, san-sarif;
	font-size: 12px;
}
.ui-widget-content{
}
.ui-menu .ui-menu-item{
	padding: 3px;
	padding-left: 25px;
}
.ui-widget-content .ui-state-focus,
.ui-widget-header .ui-state-focus {
	background: #f2f2f2;
	font-weight: normal;
	color: #212121;
}
</style>
<title>Payment</title>
</head>
<body ng-controller="mainCtrl">
	<div class="no-item-overlay" id="noItemOverlay" ng-show="myCart.cartItems.length == 0" align="center">
		<table>
			<tr>
				<td>
					<div class="infoMsgDiv" align="center">
						<div class="msg">No Item In Cart</div>
						<div>
							<input type="button" class="button2" value="Start Shopping" ng-click="gotoHome()" name="goToHome">
						</div>
					</div>
					<div id="loadProcessPaymentPage" align="center">
						<img alt="" src="<%=request.getContextPath()%>/resources/image/progress.gif">
						<div style="color: white;">Loading..</div>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<div align="center">
		<div class="pageHeader" id="pageHeader" align="center">
			<div style="height: 100%;float: left;width: 200px;">
				<div class="logoDiv"><span class="morningSpan">morning</span><span class="kartSpan">Kart</span></div>
			</div>
		</div>
		<div class="paymentSectionsOuterDiv" align="center">
				<div class="paymentSectionsHeader" style="cursor: default;" align="left">
				<table>
					<tr>
						<td style="width: 160px;">Login Email Id</td>
						<td style="width: 600px;">
							<div class="addressHeaderNameAddressDiv" ng-show="openSectionId != 'login'">
								<div class="addressHeaderNameMobileDiv">{{user.email}} </div>
							</div>
						</td>
						<td style="width: 150px;">
							<div ng-show="openSectionId != 'login'">
								<input type="button" class="button2" value="Logout" name="logoutButton" ng-click="logout()">
							</div>
						</td>
					</tr>
				</table>
			</div>
			<div id="paymentLoginContent" class="paymentLoginContent">
				<div align="center">
				<sec:authorize access="isAnonymous()">
					<%@ include file="login.jsp" %>
				</sec:authorize>
				</div>
			</div>
		</div>
		<%@ include file="addAddressAndAddressList.jsp" %>
		<div class="paymentSectionsOuterDiv" align="justify">
			<div class="paymentSectionsHeader" ng-click="openSectionId = 'order'">
				<table>
					<tr>
						<td style="width: 160px;">Order Items Details</td>
						<td style="width: 600px;">
							<div class="addressHeaderNameAddressDiv" ng-show="openSectionId != 'order'">
								<div class="addressHeaderNameMobileDiv">
									<span>({{myCart.cartItems.length}}) Item</span>
									<span>Total Rs. {{myCart.sellingPriceTotal}}</span>
								</div>
							</div>
						</td>
						<td style="width: 150px;" ng-show="openSectionId != 'order'">
							<div>
								<input type="button" class="button2" value="Review Order" name="logoutButton">
							</div>
						</td>
					</tr>
				</table>
			</div>
			<div id="myCartPayContent">
				<div align="center">
					<%@ include file="order/viewCart.jsp" %>
				</div>
				<div align="right" class="bottomMyCartPayBlock">
					<div class="continueToPayment" ng-click="CartContinueToPayment()">
						<a href="#">Continue >></a>
					</div>
				</div>
			</div>
		</div>
		<div class="paymentSectionsOuterDiv" align="justify">
			<div class="paymentSectionsHeader" ng-click="openSectionId = 'paymentMethod'">
				<table>
					<tr>
						<td style="width: 160px;">Payment Method</td>
						<td style="width: 600px;">
						</td>
						<td style="width: 150px;" ng-show="openSectionId != 'order'">
						</td>
					</tr>
				</table>
			</div>
			<div id="paymentMethodContent">
				<div align="center">
					<div>
						<input type="radio" ng-model="paySelection" ng-value="1"> Cash on delivery</div>
						<input type="radio" ng-model="paySelection" ng-value="2"> Debit from wallet after delivery</div>
						<input type="button" class="button2" value="Place Order" name="placeButton" ng-click="placeOrder(myCart.kartUUID, paySelection)">
					</div>
				</div>
			</div>
			
		</div>
</body>
</html>