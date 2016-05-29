<%@page import="com.mk.services.ProductService"%>
<%@page import="com.mk.utils.ContextHolder"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@ include file="topContent.jsp" %>
<style>
.pDetailOuterdiv {
	background: white;
	float: left;
	padding-bottom: 10px;
	width: 100%;
	font-family: arial, san-serif;
	margin-top: 5px;
}

.topPanel {
	height: 17px;
	/* 	border: 1px solid whitesmoke; */
	margin-top: 10px;
	margin-bottom: 10px;
	padding-left: 10px;
	padding-top: 5px;
	padding-bottom: 5px;
}

.topPanel div {
	color: #848484;
	float: left;
	font-family: arial, san-serif;
	font-size: 14px;
}

.topPanel div a {
	color: #848484;
	text-decoration: none;
	cursor: pointer;
}

.productDetailMainContent{
	width: 100%;
	float: left;
}

.productImageHolder {
	width: 420px;
	height: 520px;
	float: left;
	/* 	border: 1px solid lightgoldenrodyellow; */
}

.mainImageDiv {
	height: 410px;
}
.mainImageDiv img {
	max-width: 400px;
	max-height: 400px;
}
.mainImageDiv table {
	height: 400px;
}

.imageSliderOuterDiv {
	height: 110px;
	/* 	border-top: 1px solid lightgoldenrodyellow; */
}

.imageSliderOuterDiv table{
	border-spacing: 0px;
}

.imageSliderInnerDiv {
	margin-left: 1px;
	margin-right: 1px;
}

.imageSliderImageDiv {
	float: left;
	margin-top: 5px;
	margin-bottom: 5px;
}
.imageSliderImageDiv table{
	height: 100px;
	border-spacing: 0px;
}

.imageSliderImg {
	border: 1px solid transparent;
	max-width: 100px;
	max-height: 100px;
}

.imageSliderImgHover {
	border: 1px solid #ffd926;
}

.productDetailRightPanelDiv {
	float: left;
	width: 540px;
	min-height: 520px;
/* 	border: 1px solid lightgoldenrodyellow; */
 	color: #383636 #848484;
	font-family: arial, san-serif;
	font-size: 14px;
}

.brandNamediv {
	margin: 5px;
}
.brandNamediv a{
	text-decoration: none;
	text-transform: capitalize;
	color: #D5C6C6;
}
.productDetailProductNameDiv{
	text-transform: capitalize;
	font-size: 25px;
	margin: 5px;
}
.productDescriptiondiv{
	margin: 5px;
}
.productDetailProductSpecificOfferDiv{
	width: 100%;
}
.productDetailKeyFeatureAndPackagingOuterDiv{
	width: 250px;
	float: left;
/* 	border: 1px solid; */
}

.productDetailKeyFeatureDiv{
	color: #6D6B6B;
	float: left;
}
.sizesOuterDiv{
	color: #6D6B6B;
	font-family: arial, san-serif;
	font-size: 14px;
}
.productDetailBuyAndSubscribeBoxDiv{
	width: 190px;
	float: right;
	border: 1px solid beige;
	padding-left: 30px;
	padding-right: 30px;
	padding-top: 10px;
	padding-bottom: 10px;
	background: beige;
}
.productDetailMRPDiv{
	margin-top: 10px;
}
.productDetailMRPDiv span {
	color: #848484;
}
.productDetailMRPDivLineThrough span{
	text-decoration: line-through;
}
.productDetailSavingDivs{
	margin-top: 10px;
}
.productDetailSellingPriceDiv{
	margin-top: 10px;
}
.productDetailSelectQuantityDiv{
	margin-top: 10px;
}
.productDetailRateSpan{
	font-size: 25px;
	margin-left: 5px;
	font-style: italic;
}
.productDetailSellingPriceUnitSpan{
	font-size: 12px;
	color: #484848;
}
.productDetailAddToCartDiv{
	margin-top: 10px;
	margin-bottom: 5px;
}
.productDetailAddToCartDiv button{
	font-size: 18px;
	width: 100%;
	background: #175ed4;
	color: aliceblue;
	border: 0px;
	padding: 5px;
}
.productDetailSubscriptionDiv{
	margin-top: 5px;
	margin-bottom: 10px;
}
.productDetailSubscriptionDiv button{
	font-size: 18px;
	width: 100%;
	background: #f75b16;
	color: white;
	border: 0px;
	padding: 5px;
}
.kartsbuscriptionOrdiv{
	height: 1px;
	background: #DFDFDF;
	position: relative;
	margin-top: 10px;
	margin-bottom: 10px;
}
.kartsbuscriptionOrdiv span{
	position: absolute;
	left: 80px;
	top: -6px;
	line-height: 10pt;
	padding: 0 2px;
	background: beige;
	font-size: 11px;
}
.genuineOriginaldiv{
 	width: 99%;
	border: 1px solid antiquewhite;
	float: right;
	margin-top: 20px;
	position: relative;
	top: 20px;
	font-size: 12px;
	padding-top: 5px;
	padding-bottom: 5px;
	background: azure;
}
.genuineOriginaldiv span{
	color: rgb(163, 153, 138);
}
.productDetailDescriptionAndRelatedProductLeftPanel{
	width: 75%;
	float: left;
}
.productDetailedDescriptionDiv{
	margin: 5px;
	margin-top: 10px;
	float: left;
	color: #848484;
}

.productDetailedDescriptionDiv table{
	width: 100%;
}
.tdLeft{
	border-bottom: 1px dotted lightgoldenrodyellow;
	border-right: 1px solid lightgoldenrodyellow;
	width: 50%;
}
.tdRight{
	border-bottom: 1px dotted lightgoldenrodyellow;
}
.productDetailedDescriptionHeaderDiv{
}
.productDetailedDescriptionDiv p{
	color: #848484;
}

.rifgtPanelAddsOuterDiv{
	float: right;
 	width: 202px;
}
.rifgtPanelAdds{
	float: right;
 	border: 1px solid whitesmoke;
	background: beige;
	width: 200px;
	margin-top: 10px;
	margin-bottom: 10px;
	margin-left: 20px;
	cursor: pointer;
}

.productsOffers{
	width: 100%;
	border: 1px rgb(247, 239, 100);
	border-style: dashed;
	font-family: arial, san-serif;
	margin-bottom: 5px;
	background: rgb(252, 252, 252);
	color: rgb(105, 101, 95);
}
.productsOffers td{
	border-top: 1px rgb(247, 239, 100);
	border-top-style: dashed;
}
/* related product lists Start*/
.relatedProductsDiv{
	float: left;
	width: 100%;
	border-top: 1px dotted;
}
.relatedProductListOuterDiv{
  	border: 1px solid transparent;
	min-height: 300px;
	margin: 5px;
	margin-top: 10px;
	width: 100%;
	float: left;
	color: #848484;
	overflow: hidden;
}
.relatedProductListInnerDiv{
	width: 99%;
	float: left;
	overflow: hidden;
}
.relatedProductListInnerXLongDiv{
	position: relative;
}
.productHolderOuterdiv{
	float: left;
}

/* related product lists Ends*/


/* slider  Start*/
.leftRightControlSliderDiv{
	width: 100%;
	float: left;
}
.leftRightControlSliderControlButton {
	text-decoration: blink;
	font-size: 30px;
	background: rgb(189, 189, 178);
	color: bisque;
	padding: 0px;
	border: 0px;
	font-family: serif;
}
.leftRightControlSliderControlButtonDisabled{
	background: rgb(243, 243, 235); 
}
/* slider  End*/

.font15px{
	font-size: 15px;	
}
.font20px{
	font-size: 20px;	
}
.font18px{
	font-size: 18px;	
}
.font12px{
	font-size: 12px;
}
.backgroundlightgoldenrodyellow{
	background: lightgoldenrodyellow;
}
.descriptionFontColor{
	color: #848484;
}
.marginBottom{
	margin-bottom: 20px;
}
.marginTop{
	margin-top: 20px;
}
.fontBold{
	font-weight: bold;
}
</style>
<script type="text/javascript">
var pDetailCtrl = mkApp.controller('pDetailCtrl', function($scope, $http, $timeout, $window, $location) {
	$scope.productDetail = <%= new ObjectMapper().writer().writeValueAsString(((ProductService)ContextHolder.getApplicationContext().getBean("productService")).productDetail(request.getParameter("q")).getBody())%>;
	$scope.rightSideAdds = [/* {"addImgUrl":"resources/image/rightPanelAdd.jpg","url":"/mk"},
	                        {"addText":"Extra 2% discounts on subscription based product.","url":"/mk"},
	                        {"addImgUrl":"resources/image/rightPanelAdd.jpg","url":"/mk"},
	                        {"addText":"Extra 5% discounts on shopping more then Rs 2000.","url":"/mk"} */
	                        ];
	

	$scope.relatedProductList = [
								{
									"title" : "More products from brand Amul",
									"products" : [
											/* {
												"uuid" : "P1002V1",
												"name" : "Amul Slim n Trim Toned Milk (500 ml)",
												"canBeSubscribed" : "true",
												"imageURL" : "milkPramotionalBanner1.jpg",
												"price" : {
													"localityId" : "default",
													"mrp" : "20",
													"sellingPrice" : "20"
												}
											},
											{
												"uuid" : "P1002V2",
												"name" : "Amul Slim n Trim Toned Milk (1 letre)",
												"canBeSubscribed" : "false",
												"imageURL" : "milkPramotionalBanner1.jpg",
												"price" : {
													"localityId" : "default",
													"mrp" : "40",
													"sellingPrice" : "40"
												}
											},
											{
												"uuid" : "P1001V1",
												"name" : "Amul Gold 500 ml",
												"canBeSubscribed" : "true",
												"imageURL" : "milkPramotionalBanner1.jpg",
												"price" : {
													"localityId" : "default",
													"mrp" : "24",
													"sellingPrice" : "24"
												}
											},
											{
												"uuid" : "P1001V2",
												"name" : "Amul Gold 1 letre",
												"canBeSubscribed" : "false",
												"imageURL" : "milkPramotionalBanner1.jpg",
												"price" : {
													"localityId" : "default",
													"mrp" : "48",
													"sellingPrice" : "48"
												}
											}  */]
								},
								{
									"title" : "Simalar products of this product",
									"products" : [
											/* {
												"uuid" : "P1002V1",
												"name" : "Amul Slim n Trim Toned Milk (500 ml)",
												"canBeSubscribed" : "true",
												"imageURL" : "milkPramotionalBanner1.jpg",
												"price" : {
													"localityId" : "default",
													"mrp" : "20",
													"sellingPrice" : "20"
												}
											},
											{
												"uuid" : "P1002V2",
												"name" : "Amul Slim n Trim Toned Milk (1 letre)",
												"canBeSubscribed" : "false",
												"imageURL" : "milkPramotionalBanner1.jpg",
												"price" : {
													"localityId" : "default",
													"mrp" : "40",
													"sellingPrice" : "40"
												}
											},
											{
												"uuid" : "P1001V1",
												"name" : "Amul Gold 500 ml",
												"canBeSubscribed" : "true",
												"imageURL" : "milkPramotionalBanner1.jpg",
												"price" : {
													"localityId" : "default",
													"mrp" : "24",
													"sellingPrice" : "24"
												}
											},
											{
												"uuid" : "P1001V1",
												"name" : "Amul Gold 500 ml",
												"canBeSubscribed" : "true",
												"imageURL" : "milkPramotionalBanner1.jpg",
												"price" : {
													"localityId" : "default",
													"mrp" : "24",
													"sellingPrice" : "24"
												}
											},
											{
												"uuid" : "P1001V1",
												"name" : "Amul Gold 500 ml",
												"canBeSubscribed" : "true",
												"imageURL" : "milkPramotionalBanner1.jpg",
												"price" : {
													"localityId" : "default",
													"mrp" : "24",
													"sellingPrice" : "24"
												}
											},
											{
												"uuid" : "P1001V2",
												"name" : "Amul Gold 1 letre",
												"canBeSubscribed" : "false",
												"imageURL" : "milkPramotionalBanner1.jpg",
												"price" : {
													"localityId" : "default",
													"mrp" : "48",
													"sellingPrice" : "48"
												}
											} */ ]
								},
								{
									"title" : "People who bought this product also bought",
									"products" : [/* 
											{
												"uuid" : "P1002V1",
												"name" : "Amul Slim n Trim Toned Milk (500 ml)",
												"canBeSubscribed" : "true",
												"imageURL" : "milkPramotionalBanner1.jpg",
												"price" : {
													"localityId" : "default",
													"mrp" : "20",
													"sellingPrice" : "20"
												}
											},
											{
												"uuid" : "P1002V2",
												"name" : "Amul Slim n Trim Toned Milk (1 letre)",
												"canBeSubscribed" : "false",
												"imageURL" : "milkPramotionalBanner1.jpg",
												"price" : {
													"localityId" : "default",
													"mrp" : "40",
													"sellingPrice" : "40"
												}
											},
											{
												"uuid" : "P1001V1",
												"name" : "Amul Gold 500 ml",
												"canBeSubscribed" : "true",
												"imageURL" : "milkPramotionalBanner1.jpg",
												"price" : {
													"localityId" : "default",
													"mrp" : "24",
													"sellingPrice" : "24"
												}
											},
											{
												"uuid" : "P1001V2",
												"name" : "Amul Gold 1 letre",
												"canBeSubscribed" : "false",
												"imageURL" : "milkPramotionalBanner1.jpg",
												"price" : {
													"localityId" : "default",
													"mrp" : "48",
													"sellingPrice" : "48"
												}
											}  */]
								},
								{
									"title" : "More products from catagory Dairy",
									"products" : [
											/* {
												"uuid" : "P1002V1",
												"name" : "Amul Slim n Trim Toned Milk (500 ml)",
												"canBeSubscribed" : "true",
												"imageURL" : "milkPramotionalBanner1.jpg",
												"price" : {
													"localityId" : "default",
													"mrp" : "20",
													"sellingPrice" : "20"
												}
											},
											{
												"uuid" : "P1002V2",
												"name" : "Amul Slim n Trim Toned Milk (1 letre)",
												"canBeSubscribed" : "false",
												"imageURL" : "milkPramotionalBanner1.jpg",
												"price" : {
													"localityId" : "default",
													"mrp" : "40",
													"sellingPrice" : "40"
												}
											},
											{
												"uuid" : "P1001V1",
												"name" : "Amul Gold 500 ml",
												"canBeSubscribed" : "true",
												"imageURL" : "milkPramotionalBanner1.jpg",
												"price" : {
													"localityId" : "default",
													"mrp" : "24",
													"sellingPrice" : "24"
												}
											},
											{
												"uuid" : "P1001V1",
												"name" : "Amul Gold 500 ml",
												"canBeSubscribed" : "true",
												"imageURL" : "milkPramotionalBanner1.jpg",
												"price" : {
													"localityId" : "default",
													"mrp" : "24",
													"sellingPrice" : "24"
												}
											},
											{
												"uuid" : "P1001V1",
												"name" : "Amul Gold 500 ml",
												"canBeSubscribed" : "true",
												"imageURL" : "milkPramotionalBanner1.jpg",
												"price" : {
													"localityId" : "default",
													"mrp" : "24",
													"sellingPrice" : "24"
												}
											},
											{
												"uuid" : "P1001V1",
												"name" : "Amul Gold 500 ml",
												"canBeSubscribed" : "true",
												"imageURL" : "milkPramotionalBanner1.jpg",
												"price" : {
													"localityId" : "default",
													"mrp" : "24",
													"sellingPrice" : "24"
												}
											},
											{
												"uuid" : "P1001V1",
												"name" : "Amul Gold 500 ml",
												"canBeSubscribed" : "true",
												"imageURL" : "milkPramotionalBanner1.jpg",
												"price" : {
													"localityId" : "default",
													"mrp" : "24",
													"sellingPrice" : "24"
												}
											},
											{
												"uuid" : "P1001V1",
												"name" : "Amul Gold 500 ml",
												"canBeSubscribed" : "true",
												"imageURL" : "milkPramotionalBanner1.jpg",
												"price" : {
													"localityId" : "default",
													"mrp" : "24",
													"sellingPrice" : "24"
												}
											},
											{
												"uuid" : "P1001V1",
												"name" : "Amul Gold 500 ml",
												"canBeSubscribed" : "true",
												"imageURL" : "milkPramotionalBanner1.jpg",
												"price" : {
													"localityId" : "default",
													"mrp" : "24",
													"sellingPrice" : "24"
												}
											},
											{
												"uuid" : "P1001V2",
												"name" : "Amul Gold 1 letre",
												"canBeSubscribed" : "false",
												"imageURL" : "milkPramotionalBanner1.jpg",
												"price" : {
													"localityId" : "default",
													"mrp" : "48",
													"sellingPrice" : "48"
												}
											}  */]
								} ];

						
												
						var selectedVarientIndex = 0;
						
						for(var i=0;i<$scope.productDetail.variants.length;i++) {
							if($scope.productDetail.variants[i].selected){
								selectedVarientIndex = i;
							}
						}
						$scope.selectedVariant = $scope.productDetail.variants[selectedVarientIndex];
						$scope.selectedQuentityPrice = $scope.selectedVariant.quantityPriceList[0];
						$scope.selectedSliderImageObj = $scope.selectedVariant.images[0];
						$scope.$watch("selectedVariant.images", function(newValue, oldValue) {
							$scope.selectedSliderImageObj = $scope.selectedVariant.images[0];
						});
						$scope.$watch("selectedVariant", function(newValue, oldValue) {
							$scope.selectedQuentityPrice = $scope.selectedVariant.quantityPriceList[0];
						});
						$scope.sliderMouseEnter = function(obj, selectedImage) {
							obj.sliderImgHover = true;
							$scope.selectedSliderImageObj = selectedImage;
						};
						$scope.sliderMouseLeave = function(obj, selectedImage) {
							obj.sliderImgHover = false;
							$scope.selectedSliderImageObj = selectedImage;
						};
						
						//left and right Slider controls Starts
						$scope.initSliderControl = function(totalItems, itenPerPage){
							this.pageNr = 1;
							this.totalPageCount = parseInt(totalItems/itenPerPage + .99);
						}
						$scope.leftControlClick = function(divId, parentdivId){
							var wdt = $("#"+parentdivId).width()-20;
							$( "#"+divId ).animate({ "left": "+="+wdt+"px" }, "slow" );
							this.pageNr = this.pageNr - 1;
						};
						$scope.rightControlClick = function(divId, parentdivId){
							var wdt = $("#"+parentdivId).width()-20;
							$( "#"+divId ).animate({ "left": "-="+wdt+"px" }, "slow" );
							this.pageNr = this.pageNr + 1;
						};
						//left and right Slider controls Ends
					});
</script>
<div class="pDetailOuterdiv" ng-controller="pDetailCtrl">
	<div class="topPanel backgroundlightgoldenrodyellow">
		<div><a href="/mk">Home</a></div>
		<div ng-repeat="catagory in productDetail.catagories">&nbsp > &nbsp<a href="{{catagory.url}}" ng-bind="catagory.name"></a></div>
		<div>&nbsp > &nbsp<span ng-bind="productDetail.name"></span></div>
	</div>
	<div class="productDetailMainContent" >
		<div class="productImageHolder">
			<div class="mainImageDiv">
				<table><tr><td>
					<img alt="" ng-src="{{selectedSliderImageObj.img400x400}}">
				</td></tr></table>
			</div>
			<div class="imageSliderOuterDiv">
				<table><tr><td>
					<div ng-repeat="image in selectedVariant.images" class="imageSliderImageDiv">
						<table><tr><td>
							<img alt="" class="imageSliderImg" ng-src="{{image.img100x100}}" ng-class="{imageSliderImgHover: sliderImgHover}" ng-mouseenter="sliderMouseEnter(this, image)" ng-mouseleave="sliderMouseLeave(this, image)" >
						</td></tr></table>
					</div>
				</td></tr></table>
			</div>
		</div>
		<div class="productDetailRightPanelDiv" align="left">
			<div class="brandNamediv"><a href="#" ng-bind="productDetail.brand.name"></a></div>
			<div class="productDetailProductNameDiv" ng-bind="selectedVariant.name"></div>
			<div class="productDescriptiondiv" ng-bind="productDetail.shortDescription"></div>
			<div class="productDetailProductSpecificOfferDiv">
			<table class="productsOffers" ng-show="productDetail.offers || selectedVariant.offers || selectedQuentityPrice.offers">
				<tr><th align="left">Offers:</th></tr>
				<tr class="offer" ng-repeat="offer in productDetail.offers"><td ng-bind="offer.offerText"></td></tr>
				<tr class="offer" ng-repeat="offer in selectedVariant.offers"><td ng-bind="offer.offerText"></td></tr>
				<tr class="offer" ng-repeat="offer in selectedQuentityPrice.offers"><td ng-bind="offer.offerText"></td></tr>
			</table>
			</div>
			<div class="productDetailKeyFeatureAndPackagingOuterDiv">
				<div class="productDetailKeyFeatureDiv">
					<ul>
						<li ng-repeat="keyFeature in productDetail.keyFeatures" ng-bind="keyFeature"></li>
					</ul>
				</div>
				<div class="sizesOuterDiv" ng-show="productDetail.variants.length > 1">
					Sizes:
					<div ng-repeat="pVariant in productDetail.variants">
						<input type="radio" name="selectedProductVariantRadio" ng-model="$parent.selectedVariant" ng-value="pVariant">{{pVariant.packagingTitle}}
					</div>
				</div>
			</div>
			<div class="productDetailBuyAndSubscribeBoxDiv">
				<div class="productDetailMRPDiv productDetailMRPDivLineThrough">
					MRP <span>Rs {{selectedQuentityPrice.mrp}}</span>
				</div>
				<div class="productDetailSavingDivs" ng-show="(selectedQuentityPrice.mrp - selectedQuentityPrice.sellingPrice) > 0">
					You saved Rs {{selectedQuentityPrice.mrp - selectedQuentityPrice.sellingPrice}}
				</div>
				<div class="productDetailSellingPriceDiv">
					Sell Price <span class="productDetailRateSpan"> Rs {{selectedQuentityPrice.sellingPrice}}</span>
				</div>
				<div class="productDetailSelectQuantityDiv">
					<span>Quantity:</span>
					<select ng-model="selectedQuentityPrice" ng-options="quantityPrice.name for quantityPrice in selectedVariant.quantityPriceList"></select>
				</div>
				<div class="productDetailAddToCartDiv" align="center">
					<button ng-click="addToCart(productDetail.id, selectedVariant.id, selectedQuentityPrice.id)"> Add to Kart</button>
				</div>
				<div align="center" class="kartsbuscriptionOrdiv" ng-show="selectedVariant.canBeSubscribed"><span class="descriptionFontColor">OR</span></div>
				<div class="productDetailSubscriptionDiv" align="center" ng-show="selectedVariant.canBeSubscribed">
					<button ng-click="clickOnAddSubscription(selectedVariant.id, selectedQuentityPrice.id)"> Go For Subscription</button>
				</div>
			</div>
			<div class="genuineOriginaldiv" align="center">
				<span>All products sold on Morningkart are branded and</span> <span class="fontBold">100% Pure</span>
			</div>
		</div>
	</div>
	<div class="productDetailDescriptionAndRelatedProductLeftPanel">
		<div align="left" class="productDetailedDescriptionDiv">
			<div class="backgroundlightgoldenrodyellow font20px">Description of {{productDetail.name}}</div>
			<div ng-show="productDetail.detailedDescription"><p class="font15px">{{productDetail.detailedDescription}}</p></div>
			<div ng-repeat="specification in productDetail.specifications" >
				<div class="backgroundlightgoldenrodyellow font18px">{{specification.title}}</div>
				<p ng-repeat="description in specification.descriptions" class="font15px" >{{description}}</p>
				<table ng-show = "specification.tableContent" class="font15px descriptionFontColor">
					<tr ng-repeat="content in specification.tableContent">
						<td class="tdLeft">{{content.label}}</td>
						<td class="tdRight">{{content.value}}</td>
					</tr>
				</table>
				<div class="marginBottom"></div>
			</div>
		</div>
		<div class="relatedProductsDiv marginTop" ng-show="relatedProductList" >
			<div ng-repeat="relatedProducts in relatedProductList" class="relatedProductListOuterDiv" >
				<div class="font20px" style="color: #3C3939;" align="left" ><span>{{relatedProducts.title}}</span></div>
				<div class="relatedProductListInnerDiv" id="relatedProductListInnerDivId{{$index}}">
					<div class="relatedProductListInnerXLongDiv" id="relatedProductListInnerXLongDivId{{$index}}" style="width: {{relatedProducts.products.length * 180}}px;">
						<div ng-repeat="product in relatedProducts.products" class="productHolderOuterdiv" >
							<%@ include file="productListProduct.jsp" %>
						</div>
					</div>
				</div>
				<div class="leftRightControlSliderDiv" id="leftRightControlSliderDivId{{$index}}" ng-init="initSliderControl(relatedProducts.products.length, 4)">
					<button class="leftRightControlSliderControlButton" ng-class="{leftRightControlSliderControlButtonDisabled: (pageNr == 1)}" ng-Disabled="pageNr == 1" ng-click="leftControlClick('relatedProductListInnerXLongDivId'+$index, 'leftRightControlSliderDivId'+$index)" >&nbsp;&lt;&nbsp;</button>
					<button class="leftRightControlSliderControlButton" ng-class="{leftRightControlSliderControlButtonDisabled: (pageNr == totalPageCount)}" ng-Disabled="pageNr == totalPageCount" ng-click="rightControlClick('relatedProductListInnerXLongDivId'+$index, 'leftRightControlSliderDivId'+$index)">&nbsp;&gt;&nbsp;</button>
				</div>
			</div>
		</div>
	</div>
	<div class="rifgtPanelAddsOuterDiv">
		<div class="rifgtPanelAdds" ng-repeat="add in rightSideAdds" >
			<p ng-show="add.addText">{{add.addText}}</p>
			<img ng-show="add.addImgUrl" alt="" ng-src="{{add.addImgUrl}}">
		</div>
	</div>

</div>
<%@ include file="bottomContent.jsp" %>
