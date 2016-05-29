<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="mkApp">
<head>
<meta charset="utf-8">
<title>Morning Kart</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/resources/image/mk_small.png" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery-ui.css">
<script src="<%=request.getContextPath()%>/resources/js/jquery-2.1.1.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery-ui.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/angular.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/mkApp.js"></script>
	
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/template_1.css">
<script>
</script>
<style>
</style>
</head>
<body class="body" id="htmlBodyId" ng-controller="mainCtrl" ng-click="clickOutsideLocalitySearchPanel('body')" >
	<%@ include file="header.jsp" %>
	<div class="backGroundTopStrip"></div>
	
	<div class="card-and-subscription-overlay" id="cardAndSubscriptionId" align="center">
		<div class="card-and-subscription-div">
			<div class="card-and-subscription-main-content-div">
				<div>
					<div align="right"><span class="cross-icon" ng-click="closeCardAndSubscriptionDialog()"></span></div>
					<div style="margin-bottom: 10px;"><img alt="" id="myCartDialogeLoadingImgId" src="<%=request.getContextPath()%>/resources/image/progress.gif"></div>
				</div>
				<div id="myCartDialogInnerContent"></div>
			</div>
		</div>
	</div>
	
	<div class="locality-overlay" id="localityId" align="center">
		<div class="locality-div">
			<div class="locality-main-content-div">
				<div align="right"><span class="cross-icon" ng-click="closeLocalitySearchDialog()"></span></div>
				<div class="select-locality">Select your locality</div>
				<div>
					<input type="text" id="localitySelectInputTextBox" class="input-box" placeholder="Type your Society or Area or City ..."> 
				</div>
			
			</div>
		</div>
	</div>
	
	
	<div class="login-overlay" id="loginId" align="center">
		<div class="login-div">
			<div class="login-main-content-div">
				<div align="right"><span class="cross-icon" ng-click="closeLoginDialog()"></span></div>
				<div id="loginDialogInnerContent"></div>
			</div>
		</div>
	</div>
	
	
	<div class="subscription-overlay" id="subscriptionDialogId" align="center">
		<div class="subscription-div">
			<div class="subscription-main-content-div">
				<div align="right"><span class="cross-icon" ng-click="closeSubcriptionDialog()"></span></div>
				<div id="subscriptionDialogInnerContent"></div>
			</div>
		</div>
	</div>
	
	<div class="mainContentHolder" align="center">
		<div class="menuFileHolderdiv" id="menuFileHolderdiv">
			<%@ include file="menu.jsp" %>
		</div>
		<div class="mainContent" style="display: none;">
