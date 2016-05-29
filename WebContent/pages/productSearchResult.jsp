<%@page import="com.mk.services.ProductService"%>
<%@ include file="topContent.jsp" %>
<style type="text/css">

/* product result css Start */
.productResultHolder{
	width: 100%;
	background: white;
	/* overflow: auto; */
/* 	margin-top: 10px; */
}
.productResultMainContent{
 	margin-top: 5px;
	overflow: hidden;
}
.topPanel{
	height: 17px;
/* 	border: 1px solid whitesmoke; */
	margin-top: 10px;
	margin-bottom: 10px;
	padding-left: 10px;
	padding-top: 5px;
	padding-bottom: 5px;
	background: lightgoldenrodyellow;
}
.topPanel div{
	color: #848484;
	float: left;
	font-family: arial,san-serif;
	font-size: 14px;
}
.topPanel div a{
	color: #848484;
	text-decoration: none;
	cursor: pointer;
}
.leftPanel{
	width: 200px;
/*  	height: 1000Px; */
	border-right: 0px solid whitesmoke;
	float: left;
}
.rightPanel{
/* 	height: 1500Px; */
	width: 750px;
/* 	border-right: 1px solid whitesmoke; */
	float: left;
	margin-left: 4px;
}
.rightTopPanelForAdd{
	border: 1px solid whitesmoke;
	background: beige
}
.rightTopHeaderDiv{

}
.searchStringDiv{
	float: left;
	font-family: arial,sans-serif;
	color: #848484;
	margin-left: 5px;
	margin-top: 5px;
}
.searchStringSpan{
	text-transform: uppercase;
}
.resultCountSpan{
	text-transform: capitalize;
	font-size: 14px;
}
.sortBoxDiv{
	float: right;
	font-family: arial,sans-serif;
	font-size: 14px;
	color: #848484;
	margin-top: 5px;
}
.rightTopPanel2{
	border-bottom: 2px solid rgb(243, 205, 205);
	/* height: 50Px; */
 	margin-top: 5px;
	width: 100%;
	float: left;
}
.productHolderOuterdiv{
	height: 350px;
	float: left;
}
/* product result css end */

/* Left side filters Start*/

.filtersHeaderDiv{
	height: 100px;
}
.filtersOuterDiv{
	text-align: left;
	color: #848484;
	margin-left: 20px;
	font-family: arial,sans-serif;
	font-size: 13px;
	margin-right: 20px;
}
.filterCategoryDiv{
	margin-top:10px;
}
.filterCategoryNameDiv{
	background: antiquewhite;
}
.filtersDiv{
	/* margin-left: 20px; */
	padding-bottom: 5px;
/* 	border-bottom: 1px solid rgb(197, 172, 172); */
}
.filterDiv{
	font-size: 12px;
}
.filterDivHover {
	font-size: 12px;
	background: whitesmoke;
}
.selectedfilterCatagory{
	margin-left: 0px;
	text-align: left;
	color: #848484;
	font-family: arial,sans-serif;
	float: left;
	margin-right: 20px;
	margin-top: 3px;
/* 	height: 20px; */
	margin-bottom: 3px;
}
.selectedfilterCatagoryName{
	float: left;
	margin-left: 5px;
	margin-right: 5px;
	font-size: 13px;
}
.selectedfilters{
	float:left;
	margin-left:0px;
	font-size: 12px;
	margin-top: 1px;
}
.selectedfilterName{
	margin-left: 10px;
}
.crossLabel{
	color: rgb(252, 245, 245);
	font-size: 9px;
	/* margin-left: 5px; */
	font-family: monospace;
	border: 1px solid rgb(213, 175, 175);
	border-radius: 24px;
	padding-left: 3px;
	padding-right: 3px;
	cursor: pointer;
	margin-top: 37px;
/* 	position: relative; */
	background-color: rgb(194, 185, 185);
}
/* Left side filters End*/


.scrollTopDiv{
	position: fixed;
	width: 50px;
	height: 30px;
	z-index: 10;
	right: 0px;
	bottom: 100px;
	font-family: arial,sans-serif;
	background: rgb(221, 211, 211);
	color: whitesmoke;
	border-bottom-left-radius: 7px;
	border-top-left-radius: 7px;
	padding-top: 10px;
	cursor: pointer;
	opacity: .5;
}
.scrollTopDivHover{
	background: rgb(160, 160, 160);
	opacity: .8;
}
.loadingOnInfiniteScrollDiv{
	height: 30px;
	width: 100%;
	float: left;
	border: 1px solid whitesmoke;
	margin-bottom: 10px;
	font-family: arial,sans-serif;
	color: rgb(226, 202, 202);
	font-size: 25px;
}
</style>


<script type="text/javascript">

var productCtrl = mkApp.controller('productCtrl', function($scope, $http, $timeout, $window, $interval) {
	$scope.overlayFlag = false;
	$scope.getProductsWhenScrolledFlag = false;
	$scope.scrollTopShow = true;
	$scope.isNextPageAvailable = false;
	$scope.nextPage="";
	$scope.parameters="";

	$scope.productSearchResults = <%= new ObjectMapper().writer().writeValueAsString(((ProductService)ContextHolder.getApplicationContext().
			getBean("productService")).search(request.getParameter("q"), request.getParameter("p"), request.getParameter("o"), request.getParameter("f")).getBody())%>;
	
		
	
// 	                 		                  "filterCategories":[{"filterCategoryName":"Price","isAnyFilterSelected":false,"filters":[{"name":"Rs 0 to Rs 100", "active":false},{"name":"Rs 100 to Rs 200", "active":false},{"name":"Rs 200 to Rs 500", "active":false},{"name":"Rs 500 to Rs 1000", "active":false}]},
// 	                 		                             {"filterCategoryName":"Brand","isAnyFilterSelected":false,"filters":[{"name":"Amul", "active":false},{"name":"Mother Dairy", "active":false},{"name":"Parag", "active":false},{"name":"Gopal", "active":false}]},
// 	                 		                            {"filterCategoryName":"Discounts","isAnyFilterSelected":false,"filters":[{"name":"0% to 2%", "active":false},{"name":"2% to 5%", "active":false},{"name":"5% to 10%", "active":false},{"name":"More then 10%", "active":false}]}],
// 	                 		          "catagories":[{"name":"Dairy","url":"/mk"},{"name":"Milk","url":"/mk"}],
// 	                 		          "searchString":"Milk",
// 	                 		          "productResultCount":25,



	if($scope.productSearchResults.data != undefined){

		$scope.isNextPageAvailable = ($scope.productSearchResults.data.totalPages - $scope.productSearchResults.data.currentPage) > 0;
	   	$scope.nextPage = $scope.productSearchResults.data.nextPage;
	   	$scope.parameters = $scope.productSearchResults.data.parameters;
		
	}
		
	
	$scope.getProductsWhenScrolled = function(){
		$scope.getProductsWhenScrolledFlag = true;
		$scope.overlayFlag = true;
		$http.get('product/browse.do?'+$scope.nextPage).success(
				function(data) {
					if(data.data != undefined){
						//console.log('getProductsWhenScrolled() called');
					    angular.forEach(data.data.products, function(product, key) {
					    	$scope.productSearchResults.data.products.push(product);
					    });
				    	$scope.isNextPageAvailable = (data.data.totalPages - data.data.currentPage) > 0;
				    	$scope.nextPage = data.data.nextPage;
						
					}
					$scope.getProductsWhenScrolledFlag = false;
					$scope.overlayFlag = false;
				});
		

	};

	$scope.anyFilterClicked = function(filterCategories){
		
		var filterString = "";
		
		
		var isAnySelected = false;
		angular.forEach(filterCategories, function(filterCategory) {
			
			var filterKey = filterCategory.key;
			var filterValue = "";
		
			var isAnySelectedInCategory = false;
			angular.forEach(filterCategory.filters, function(filter) {
				
		    	if(filter.active){
		    		isAnySelected = true;
		    		isAnySelectedInCategory = true;
		    		
		    		if(filterValue.length > 0){
		    			filterValue = filterValue + "|" + filter.value;
	    			}
		    		else{
		    			filterValue = filter.value;
		    		}
		    		
		    	}
		    });
			
			if(filterValue.length > 0){
				if(filterString.length > 0){
					filterString = filterString + "@" + filterKey + "@" + filterValue;	
				}
				else{
					filterString = filterKey + "@" + filterValue;
				}
					
				
			}
			
			filterCategory.isAnyFilterSelected = isAnySelectedInCategory;
			
	    });
		
		var params = $scope.parameters+ (filterString.length > 0 ? "&f="+ filterString : "");
		$scope.getProductsWhenScrolledFlag = true;
		$scope.overlayFlag = true;
		$http.get('product/browse.do?'+params).success(
				function(data) {
					if(data.data != undefined){
						$scope.productSearchResults.data.products = data.data.products;
				    	$scope.isNextPageAvailable = (data.data.totalPages - data.data.currentPage) > 0;
				    	$scope.nextPage = data.data.nextPage;
				    	
						
					}
					else{
						$scope.productSearchResults.data.products = [];
				    	$scope.isNextPageAvailable = false;
					}
					$scope.getProductsWhenScrolledFlag = false;
					$scope.overlayFlag = false;
				});
	    
	};
	
	$scope.anyFilterRemoved = function(filterCategories,filter){
		filter.active = false;
		$scope.anyFilterClicked(filterCategories);
	};

    $scope.reloadProduct = function() {
    	if($scope.getProductsWhenScrolledFlag == false && $scope.isNextPageAvailable){
    		$scope.getProductsWhenScrolled();
    	}
    };
    $scope.scrollTop = function(){
    	$("html, body").animate({ scrollTop: "0px" }, 300,"swing");
    	//Following is for AngularJs to do same work with out animation
    	//$window.document.body.scrollTop = 0;
    };

});	

//Following directive is to bind get next set of product list when scroll went bottom
mkApp.directive("whenScrolled", function ($window) {
    return function(scope, element, attr) {
        angular.element($window).bind("scroll", function() {
            if ((this.document.body.scrollTop+this.innerHeight+10) >= this.document.body.scrollHeight) {
                scope.$apply(attr.whenScrolled);
            }
        });
    };
});
</script>
	<div class="productResultHolder" ng-controller="productCtrl" >
	 	<div class="overlayDiv" ng-show="overlayFlag">
	 		<img alt="" src="<%=request.getContextPath()%>/resources/image/progress.gif" class="">
	 	</div>
	 	<div class="scrollTopDiv" ng-show="scrollTopShow" ng-click="scrollTop()" ng-class="{scrollTopDivHover: hoverscrolltop}" ng-mouseenter="hoverscrolltop = true" ng-mouseleave="hoverscrolltop = false" >^ TOP</div>
		<div class="productResultMainContent" when-scrolled="reloadProduct()">
			<div class="topPanel">
				<div><a href="/mk">Home</a></div>
				<div ng-repeat="catagory in productSearchResults.catagories">&nbsp > &nbsp<a href="{{catagory.url}}">{{catagory.name}}</a></div>
			</div>
			<div class="leftPanel">
				<div class="filtersHeaderDiv"></div>
				<div class="filtersOuterDiv">
					<div ng-repeat="filterCategory in productSearchResults.filterCategories" class="filterCategoryDiv">
						<div class="filterCategoryNameDiv">{{filterCategory.filterCategoryName}}</div>
						<div class="filtersDiv">
							<div ng-repeat="filter in filterCategory.filters" class="filterDiv" ng-class="{filterDivHover: hover}" ng-mouseenter="hover = true" ng-mouseleave="hover = false">
								<input type="checkbox" ng-change="anyFilterClicked(productSearchResults.filterCategories)" ng-model="filter.active"> {{filter.name}}
							</div>
						</div>
					</div>
				</div>
				
			</div>
			<div class="rightPanel">
				
				<div class="rightTopHeaderDiv">
					<div class="searchStringDiv"><span class="searchStringSpan">{{productSearchResults.data.searchString}}</span> <span class="resultCountSpan"> (Showing {{productSearchResults.data.productResultCount}} Results) </span></div>
					<!-- <div class="sortBoxDiv">
						Sort by:
						<select>
							<option>Ralavence</option>
							<option>Price High to Low</option>
							<option>Price Low to High</option>
							<option>Popularity</option>
						</select>
					</div> -->
				</div>
				<div class="rightTopPanel2">

					<div ng-repeat="filterCategory in productSearchResults.filterCategories" class="selectedfilterCatagory">
						<div ng-show="filterCategory.isAnyFilterSelected" class="selectedfilterCatagoryName">{{filterCategory.filterCategoryName}}:</div>
						<div ng-repeat="filter in filterCategory.filters" ng-show="filter.active" class="selectedfilters"><span class="selectedfilterName">{{filter.name}}</span> <span class="crossLabel" ng-click="anyFilterRemoved(productSearchResults.filterCategories,filter)">X</span></div>
					</div>

				
				</div>
				<div ng-repeat="product in productSearchResults.data.products" class="productHolderOuterdiv" >
					<span ng-show="product.selectedQuantity == undefined ?  product.selectedQuantity = product.quantityPriceList[0] : true "/>
					<%@ include file="productListProduct.jsp" %>
				</div>
				<div class="loadingOnInfiniteScrollDiv" ng-show="getProductsWhenScrolledFlag">Loading...</div>
			</div>
		</div>
	</div>
<%@ include file="bottomContent.jsp" %>
