<style>
.testClass{
	font: 40px arial;
	height: 70px;
}
.myCartItemsDiv{
	font-family: arial, san-sarif;
	color: gray;
}
.myCartItemsDiv .myCartHeader{
	font-family: arial, san-sarif;
	font-size: 15px;
	margin-right: 17px;
	margin-left: 20px;
	border-bottom: 2px solid gray;
}
.myCartItemsDiv .myCartHeader .headertable{
	width: 100%;
}
.myCartItemsDiv .myCartHeader .headertable td{
}
.myCartItemsDiv .myCartBody{
	height: 350px;
	font-size: 13px;
	overflow-y: scroll;
	margin-left: 20px;
}
.myCartItemsDiv .myCartBody .contenttable{
	width: 100%;
	border-spacing: 0px;
}
.myCartItemsDiv .myCartBody .contenttable .tdBorder{
	border-bottom: 1px solid gray;
}
.myCartItemsDiv .myCartBody .contenttable .even{
	background-color: rgb(249, 249, 249);
}
.myCartItemsDiv .myCartBody .contenttable .odd{
	background-color: rgb(255, 252, 252);
}
.myCartItemsDiv .myCartBody .contenttable .mrp{
	font-size: 15px;
	color: rgb(105, 101, 95);
}
.myCartItemsDiv .myCartBody .contenttable .subTotal{
	font-size: 20px;
	color: black;
}

.myCartItemsDiv .itemNameDesc{
/*  	width: 450px; */
}
.myCartItemsDiv .itemImageCell{
	width: 110px;
}

.myCartItemsDiv .otherTd{
	width: 150px;
}

.myCartItemsDiv .removeItemTd{
	width: 30px;
}
.myCartItemsDiv .myCartBody .contenttable .itemDescription{
/* 	width: 400px; */
}
.myCartItemsDiv .offers{
	width: 260px;
	border: 1px rgb(247, 239, 100);
	border-style: dashed;
	font-family: arial, san-serif;
	margin-bottom: 5px;
	background: rgb(252, 252, 252);
	color: rgb(105, 101, 95);
}
.myCartItemsDiv .offers td{
	border-top: 1px rgb(247, 239, 100);
	border-top-style: dashed;
}
.myCartItemsDiv .mycartTotalDiv{
	height: 60px;
	border-top: 2px solid gray;
	margin-left: 20px;
	margin-right: 17px;
	background-color: rgb(249, 249, 249);
}
.myCartItemsDiv .mycartTotal{
	font-size: 25px;
	float: right;
	margin-right: 20px;
	margin-top: 5px;
}
.myCartItemsDiv .mycartTotal .rupeeTotal{
	color: black;
}
.myCartItemsDiv .mycartTotal .totalSaving{
	font-size: 15px;
	float: right;
}
.myCartItemsDiv .mycartbottomContent{
	height: 50px;
	margin-left: 20px;
	margin-right: 17px;
	margin-top: 15px;
	width: 100%;
}
.myCartItemsDiv .mycartbottomContent .proceedToPayment{
	margin-right: 56px;
	float: right;
	font-size: 18px;
	background: #175ed4;
	border: 0px;
	padding: 5px;
}
.myCartItemsDiv .mycartbottomContent .proceedToPayment a{
	text-decoration: none!important;
	color: aliceblue;
}
.myCartItemsDiv .continueShopping{
	margin-right: 56px;
	float: left;
	font-size: 18px;
	background: #f75b16;
	border: 0px;
	padding: 5px;
}
.myCartItemsDiv .continueShopping a{
	text-decoration: none!important;
	color: white;
}

.myCartItemsDiv .noItem{
	height: 300px;
	padding-top: 150px;
}

.myCartItemsDiv .myCartOverlay{
	position: absolute;
	top: 0px;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.2);
}
.myCartItemsDiv .myCartOverlay table{
	height: 100%;
}
.myCartItemsDiv .myCartMsg{
	width: 50%;
	background-color: rgb(250, 211, 211);
	border: 1px solid red;
	border-radius: 4px;
	color: rgb(75, 75, 75);
}
.myCartItemsDiv .myCartMsg .cross{
	float: right;
	margin-right: 5px;
	font-size: 12px;
	margin-top: 3px;
	color: white;
	cursor: pointer;
}
</style>
<div class="myCartItemsDiv">
	<div class="myCartMsg" ng-show="myCart.myCartMessage">
		<div class="cross" ng-click="closeCartUI()">X</div>
		<div align="center">{{myCart.myCartMessage}}</div>
	</div>
	<div class="noItem" ng-hide="myCart.cartItems.length > 0">
		<div>
			No Items in the Cart
		</div>
		<div>
			<div class="continueShopping" ng-click="continueShopping()" style="float: none;width: 200px; margin-right: 0px; margin-top: 20px;">
				<a href="#"><< Continue Shopping</a>
			</div>
		</div>
	</div>
	<div ng-show="myCart.cartItems.length > 0">
		<div class="myCartHeader">
			<table class="headertable">
				<tr align="center">
					<td class="itemImageCell"></td>
					<td class="itemNameDesc">ITEM ({{myCart.cartItems.length}})</td>
					<td class="otherTd">QANTITY</td>
					<td class="otherTd">MRP</td>
					<td class="otherTd">SUBTOTAL</td>
					<td class="removeItemTd"></td>
				</tr>
			</table>
		</div>
		<div class="myCartBody">
			<table class="contenttable">
				<tr align="center" ng-repeat="item in myCart.cartItems">
					<td class="itemImageCell" ng-class="{tdBorder: $index < (myCart.cartItems.length-1)}">
						<div>
							<img alt=""  ng-src="{{item.img100x100}}">
						</div>
					</td>
					<td class="itemNameDesc" ng-class="{tdBorder: $index < (myCart.cartItems.length-1)}">
						<div class="itemDescription">
							<div class="itemName">{{item.varientName}}</div>
							<div>
								<table class="offers" ng-show="item.productOffers.length > 0 || item.variantOffers.length > 0 || item.selectedQuantity.offers.length > 0">
									<tr><th align="left">Offers:</th></tr>
									<tr class="offer" ng-repeat="offer in item.productOffers"><td>{{offer.offerText}}</td></tr>
									<tr class="offer" ng-repeat="offer in item.variantOffers"><td>{{offer.offerText}}</td></tr>
									<tr class="offer" ng-repeat="offer in item.selectedQuantity.offers"><td>{{offer.offerText}}</td></tr>
								</table>
							</div>
						</div>
					</td>
					<td class="otherTd even" ng-class="{tdBorder: $index < (myCart.cartItems.length-1)}">
						<select ng-model="item.selectedQuantity" title="Update Quantity" ng-change="updateQuantityInCart(item.variantId, item.selectedQuantity.id)" ng-options="quantityPrice.name for quantityPrice in item.quantityPriceList"></select>
					</td>
					<td class="otherTd mrp odd" ng-class="{tdBorder: $index < (myCart.cartItems.length-1)}">Rs. {{item.selectedQuantity.mrp}}</td>
					<td class="otherTd subTotal even" ng-class="{tdBorder: $index < (myCart.cartItems.length-1)}">Rs. {{item.selectedQuantity.sellingPrice}}</td>
					<td class="removeItemTd odd" ng-class="{tdBorder: $index < (myCart.cartItems.length-1)}">
						<span class="cross-icon" title="Remove Item" ng-click="removeItemFromCart(item.variantId)"></span>
					</td>
				</tr>
			</table>
		</div>
		<div class="mycartTotalDiv">
			<div class="mycartTotal">
				<div><span>Total Amount Payable: </span><span class="rupeeTotal">Rs. {{myCart.sellingPriceTotal}}</span></div>
				<div class="totalSaving">Total Saving Rs. {{myCart.mrpTotal - myCart.sellingPriceTotal}}</div>
			</div>
		</div>
		<div class="mycartbottomContent" ng-hide="mycartbottomContentHideFlag">
			<div class="proceedToPayment">
				<a href="pay">Proceed to Payment >></a>
			</div>
			<div class="continueShopping" ng-click="continueShopping()">
				<a href="#"><< Continue Shopping</a>
			</div>
		</div>
	</div>
	<div class="myCartOverlay" id="myCartOverlayId" ng-show="myCartOverlayFlag">
		<table>
			<tr>
				<td>
					<img alt="" src="<%=request.getContextPath()%>/resources/image/progress.gif">
				</td>
			</tr>
		</table>
	</div>
</div>