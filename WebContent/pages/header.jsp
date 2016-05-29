<%@page import="com.mk.services.UserService"%>
<%@page import="com.mk.services.OrderService"%>
<%@page import="com.mk.utils.ContextHolder"%>
<%@page import="com.mk.utils.Constants"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script type="text/javascript">
 
var initializeCart = function(scope){ 
	scope.contextPath='<%= request.getContextPath()%>';
	scope.logout = function(){window.location.href = scope.contextPath+'/j_spring_security_logout'; return '';}
	scope.user =  <%= new ObjectMapper().writer().writeValueAsString(((UserService)ContextHolder.getApplicationContext().getBean("userService")).getUser(session).getBody())%>;
	
	scope.myCart =  <%= new ObjectMapper().writer().writeValueAsString(((OrderService)ContextHolder.getApplicationContext().getBean("orderService")).getCart(Constants.INFO_TYPE_MINI,session).getBody())%>;
	
	
}		

</script>
<style>

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
.topFlashMsgDiv{
	position: fixed;
	width: 100%;
	height: 60px;
	background: rgb(250, 250, 159);
	border-bottom: 3px rgb(181, 117, 117) solid;
	top: 0px;
	display: none;
	font-family: arial, san-serif;
	z-index: 12;
}
.topFlashMsgDiv .topFlashCross{
	background-image: url("resources/image/icons.png");
	background-repeat: no-repeat;
	background-position: 0 -446px;
	display: inline-block;
	width: 18px;
	height: 18px;
	zoom: 1;
	vertical-align: middle;
}
</style>
	<div class="topFlashMsgDiv" id="topFlashMsgDiv">
		<div align="right" ng-click="closeTopflashMsg()" ><span class="topFlashCross"></span></div>
		<div align="center" id="topFlashMsgText"></div>
	</div>
	<div class="header" id="pageHeader" align="center">
		<table><tr valign="top">
		<td class="logoTd">
		<div style="margin-top: 7px;">
			<div class="logoDiv">
				<a href="<%=request.getContextPath()%>" style="text-decoration: none;">
					<img alt="" style="height: 55px;" src="<%=request.getContextPath()%>/resources/image/mk.png">
					<!--span class="morningSpan">morning</span><span class="kartSpan">Kart</span-->
				</a>
			</div>
		</div>
		</td>
		<td class="searchTd" align="center">
		<div class="productSearchdiv" align="left">
			<input type="text" id="productSearchInput" class="productSearchTextbox" autofocus="autofocus" placeholder="Type Product, Brand..."/>
			<a href="/mk" style="text-decoration: blink;"><span class="searchButtondiv">SEARCH</span></a>
		</div>
		</td>
		<td class="loginOuterTd" align="center">
		<sec:authorize access="isAnonymous()">
			<div class="loginLogout-div" style="display: none;" ng-click="clickOnLogin()" ng-class="{loginLogoutDivHover: loginLogoutDivHover}" ng-mouseenter="loginLogoutDivHover = true" ng-mouseleave="loginLogoutDivHover = false" >
				<div><span class="login-icon"></span></div>
				<div>Login</div>
			</div>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
			<div class="loginLogout-div" style="display: none;" ng-class="{afterLoginLogoutDivHover: loginLogoutDivHover}" ng-mouseenter="loginLogoutDivHover = true" ng-mouseleave="loginLogoutDivHover = false" >
				<div>Hi.. </div>
				<div ng-bind="(user.name == null || user.name == undefined || user.name == '') ? ((user.email == null || user.email == undefined || user.email == '') ? logout() : user.email) : user.name"></div>
				<div><span class="after-login-icon"></span></div>
				<div align="left" ng-show="loginLogoutDivHover">
					<div class="afterLoginMenuItem" ng-repeat="menuItem in afterLoginMenuItems" ng-class="{afterLoginMenuItemHover: afterLoginMenuItemHover}" ng-mouseenter="afterLoginMenuItemHover = true" ng-mouseleave="afterLoginMenuItemHover = false" >
						<a href="<%=request.getContextPath()%>{{menuItem.menuItemUrl}}" ng-bind="menuItem.title"></a>
					</div>
				</div>
			</div>
		</sec:authorize>
		<div class="header-locality-div" style="display: none;" ng-click="clickOnLocality()" ng-class="{headerLocalityDivHover: headerLocalityDivHover}" ng-mouseenter="headerLocalityDivHover = true" ng-mouseleave="headerLocalityDivHover = false" >
			<div><span class="locality-icon"></span></div>
			<div id="localityLabelDivAtHeade" ng-bind="locality"></div>
		</div>
		<div class="my-cart-div" style="display: none;" ng-click="clickOnCart()" ng-class="{myCartDivHover: myCartDivHover}" ng-mouseenter="myCartDivHover = true" ng-mouseleave="myCartDivHover = false">
			<span class="cart-icon"></span>
			<span class="my-cart-span">My Cart</span>
			<br>
			<span class="my-cart-number-span" ng-bind="myCart.noOfItem"></span>
			<span class="my-cart-items-span">Items</span>
		</div>
		</td></tr></table>
	</div>
	