<%@page import="com.mk.services.AppService"%>
<%@page import="com.mk.utils.ContextHolder"%>
<%
String userAgent = request.getHeader("user-agent");
boolean isNotIE = true;
if (userAgent.indexOf("MSIE") > -1) {
	isNotIE = false;
}
%>
			<script>
			var menuCtrl = mkApp.controller('menuCtrl', function($scope, $http, $timeout) {

			$scope.menus = 	<%= ((AppService)ContextHolder.getApplicationContext().getBean("appService")).getMainMenu()%>			

 
// 			$scope.menus = [{
// 				"name":"milk",
// 				"active":false,
// 				"menuItems1":[
// 								{
// 									"label":"Amul Milk",
// 									"url":"https://www.google.com"
// 					            },
// 					            {
// 									"label":"Mother Dairy Milk",
// 									"url":"https://www.google.com"
// 					            },
// 					            {
// 									"label":"Paras Milk",
// 									"url":"https://www.google.com"
// 					            },
// 					            {
// 									"label":"Gopal Milk",
// 									"url":"https://www.google.com"
// 					            },
// 					            {
// 									"label":"Gopal Milk",
// 									"url":"https://www.google.com"
// 					            },
// 					            {
// 									"label":"Dairy Milk",
// 									"url":"https://www.google.com"
// 					            }],
// 								"menuItems2":[
// 												{
// 													"label":"Amul Milk",
// 													"url":"https://www.google.com"
// 									            },
// 									            {
// 													"label":"Mother Dairy Milk",
// 													"url":"https://www.google.com"
// 									            },
// 									            {
// 													"label":"Paras Milk",
// 													"url":"https://www.google.com"
// 									            },
// 									            {
// 													"label":"Gopal Milk",
// 													"url":"https://www.google.com"
// 									            },
// 									            {
// 													"label":"Dairy Milk",
// 													"url":"https://www.google.com"
// 									            }],
// 		        "pramotionalBanner":[
// 						             {
// 						            	 "imageUrl":"resources/image/milkPramotionalBanner1.jpg",
// 						            	 "url":""
// 						             },
// 						             {
// 						            	 "imageUrl":"resources/image/milkPramotionalBanner2.jpg",
// 						            	 "url":""
// 						             }
		         
// 						             ]
			
// 			},
			
// 		    {"name":"butter","active":false,"menuItems1":[{"label":"Butter Milk","url":"https://www.google.com"}]},
// 		    {"name":"bread","active":false,"menuItems1":[{"label":"Bread","url":"https://www.google.com"}]},
// 		    {"name":"biskut","active":false,"menuItems1":[{"label":"Biskuts","url":"https://www.google.com"}]},
// 		    {"name":"curd","active":false,"menuItems1":[{"label":"Curds","url":"https://www.google.com"}]},
// 		    {"name":"vegitable","active":false,"menuItems1":[{"label":"Vegitable","url":"https://www.google.com"}]},
// 		    {"name":"amul product","active":false,"menuItems1":[{"label":"Amul","url":"https://www.google.com"}]},
// 		    {"name":"mother dairy","active":false,"menuItems1":[{"label":"Mother Dairy","url":"https://www.google.com"}]}];
		


			
			
 	});
			</script>
			<div class="menuDiv" id="menuDivOuterDivId" style="display: none;" ng-controller="menuCtrl">
				<div class="menu01ul">
				
					<div ng-repeat="menu in menus" class="menu01">
						<span class="menu01Span" ng-class="{menu01SpanHover: menu.active}" ng-mouseover="menuMouseOver()"  ng-mouseenter="<%if(isNotIE){%>menuMouseEnter(menu)<%}else{ %>menu.active=true<%}%>" ng-mouseleave="<%if(isNotIE){%>menuMouseLeave(menu)<%}else{ %>menu.active=false<%}%>" ng-bind="menu.name"></span>
					</div>
				
					<div class="menu01" style="margin-right: 100px; float:right; display:none;">
						<span class="menu01SpanOffer" >Offers >></span>
					</div>
				</div>
				
				<div ng-repeat="menu in menus" class="menuItemsDiv" ng-show="menu.active" ng-mouseenter="<%if(isNotIE){%>menuMouseEnter(menu)<%}else{ %>menu.active=true<%}%>" ng-mouseleave="<%if(isNotIE){%>menuMouseLeave(menu)<%}else{ %>menu.active=false<%}%>">
					<div class="menuItemsWrap">
						<div ng-repeat="menuItem in menu.menuItems1" class="menuItemDiv"><a href="<%=request.getContextPath()%>/{{menuItem.url}}" class="menuItemLink" ng-bind="menuItem.label"></a></div>
					</div>
					<div class="menuItemsWrap">
						<div ng-repeat="menuItem in menu.menuItems2" class="menuItemDiv"><a href="<%=request.getContextPath()%>/{{menuItem.url}}" class="menuItemLink" ng-bind="menuItem.label"></a></div>
					</div>
					<div ng-repeat="banner in menu.pramotionalBanner" class="menuItemsPramotionalWrap">
						<img alt="" class="menuItemBanerImage" ng-src="<%=request.getContextPath()%>/{{banner.imageUrl}}">
					</div>
				</div>
			</div>
