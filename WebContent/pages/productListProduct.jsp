<style>
.productHolder{
	border: 2px solid white;
	border-left: 1px solid #d8d8d8;
	float: left;
/* 	height: 300px; */
	width: 163px;
	margin: 5px;
	font-family:  arial,sans-serif;
}
.productVisibleDiv{
	margin-top: 7px;
	margin-bottom: 7px;
}
.productHolderHover{
	border: 2px solid #ffd926;
}
.productListImagediv{
	height: 170px;
}
.productListProductImg{
	max-width: 150px;
	max-height: 150px;
}
.productBrandNameDiv{
	text-align: left;
	font-family: arial,sans-serif;
	font-size: 12px;
/* 	text-transform:uppercase; */
	padding-left: 5px;
	padding-right: 5px;
}
.productNameDiv {
	text-align: left;
	font-family: Museo,Helvetica,arial,san-serif;
	font-size: 12px;
	padding-left: 5px;
	padding-right: 5px;
}
.productBrandNameDiv a{
	text-align: left;
	color: #D5C6C6;
	text-decoration: none
}
.productNameDiv a{
	color: #000;
	font-size: 14px;
	text-decoration: none
}

.productRateDiv{
}
.mrpDiv{
	color: #848484;
}
.mrpSpan{
	margin-left: 10px;
	text-decoration: line-through;
	font-size: 12px;
}
.savingSpan{
	margin-left: 10px;
	font-size: 12px;
}

.sellingPriceDiv{
	font-size: 20px;
	font-weight: bold;
}
.addToCartDiv{
	height: 30px;
}
.addToCartDiv span{
	float:left;
}
.addToCartDiv a{
	background: #175ed4;
	display: block;
	color: #fff;
/* 	font-weight: bold; */
	font-size: 12px;
/* 	text-transform: uppercase; */
	width: 71px;
	margin-left: 7px;
	height: 21px;
	text-decoration: none;
	text-align: center;
	border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	padding-top: 8px;
	border-bottom: 3px solid #134baa;	
}

.addToCartDivSubscribe span{
	float:left;
}
.addToCartDivSubscribe a{
	background: #f75b16;
	color: white;
	display: block;
/* 	font-weight: bold; */
	font-size: 12px;
/* 	text-transform: uppercase; */
	width: 71px;
	margin-left: 7px;
	height: 21px;
	text-decoration: none;
	text-align: center;
	border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	padding-top: 8px;
	border-bottom: 3px solid #f75b16;	
}

</style>

					<div  class="productHolder" ng-class="{productHolderHover: hover}" ng-mouseenter="hover = true" ng-mouseleave="hover = false" >
						<div class="productVisibleDiv">
							<div class="productListImagediv">
								<a href="pView?q={{product.id}}"><img alt="" class="productListProductImg" ng-src="{{product.images[0].img100x100}}"></a>
							</div>
							
							<div class="productNameDiv"><a href="pView?q={{product.id}}">{{product.name}}</a></div>
							<div style="margin-top: 5px; margin-bottom: 5px;">
								<select ng-model="product.selectedQuantity" ng-options="quantityPrice.name for quantityPrice in product.quantityPriceList"></select>
							</div>
							<div class="productRateDiv">
							
								<div class="mrpDiv" ng-show="(product.selectedQuantity.mrp - product.selectedQuantity.sellingPrice) > 0"><span class="mrpSpan">MRP: {{product.selectedQuantity.mrp}}</span><span class="savingSpan">You save Rs. {{(product.selectedQuantity.mrp - product.selectedQuantity.sellingPrice)}}</span></div>
								<div class="sellingPriceDiv">Rs. {{product.selectedQuantity.sellingPrice}}</div>
							</div>
							<div class="addToCartDiv">
								<span><a href="#" ng-click="addToCart(productDetail.id, product.id, product.selectedQuantity.id)">+ Kart</a></span>
								<span ng-show="product.canBeSubscribed" class="addToCartDivSubscribe"><a href="#" ng-click="clickOnAddSubscription(product.id, product.selectedQuantity.id)">Subscribe</a></span>
							</div>
						</div>
					</div>
					
			
