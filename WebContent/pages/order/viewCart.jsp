<%@page import="com.mk.utils.Constants"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="com.mk.utils.ContextHolder"%>
<%@page import="com.mk.services.OrderService"%>
<script type="text/javascript">
function loadCartScript(){
	angular.element(document.querySelector('[ng-controller=mainCtrl]')).scope().myCartOverlayFlag = false;
	angular.element(document.querySelector('[ng-controller=mainCtrl]')).scope().closeCartUI = function(){
		angular.element(document.querySelector('[ng-controller=mainCtrl]')).scope().myCart.myCartMessage='';
	};
	angular.element(document.querySelector('[ng-controller=mainCtrl]')).scope().updateQuantityInCart = function(variantId, selectedQuantityId){
		angular.element(document.querySelector('[ng-controller=mainCtrl]')).scope().myCartOverlayFlag = true;
		$.ajax({
			url: "order/updateQuantityInCart.do",
			data: "&variantId="+variantId+"&quantityId="+selectedQuantityId+"",
			context: document.body,
			type: 'GET'
		}).success(function(data, textStatus, jqXHR) {
			if(data.success){
				angular.element(document.querySelector('[ng-controller=mainCtrl]')).scope().myCart = data;
				angular.element(document.querySelector('[ng-controller=mainCtrl]')).scope().refreshCartData();
			}else{
			}
			angular.element(document.querySelector('[ng-controller=mainCtrl]')).scope().myCartOverlayFlag = false;
			angular.element(document.querySelector('[ng-controller=mainCtrl]')).scope().$apply();
		}).error(function( jqXHR, textStatus, errorThrown){
		});
	};
	angular.element(document.querySelector('[ng-controller=mainCtrl]')).scope().removeItemFromCart = function(variantId){
		angular.element(document.querySelector('[ng-controller=mainCtrl]')).scope().myCartOverlayFlag = true;
		$.ajax({
			url: "order/removeItemFromCart.do",
			data: "&variantId="+variantId,
			context: document.body,
			type: 'GET'
		}).success(function(data, textStatus, jqXHR) {
			if(data.success){
				angular.element(document.querySelector('[ng-controller=mainCtrl]')).scope().myCart = data;
				angular.element(document.querySelector('[ng-controller=mainCtrl]')).scope().refreshCartData();
			}else{
			}
			angular.element(document.querySelector('[ng-controller=mainCtrl]')).scope().myCartOverlayFlag = false;
			angular.element(document.querySelector('[ng-controller=mainCtrl]')).scope().$apply();
		}).error(function( jqXHR, textStatus, errorThrown){
		});
	};
	angular.element(document.querySelector('[ng-controller=mainCtrl]')).scope().continueShopping = function(){
		$("#cardAndSubscriptionId").hide();
	};
	
	angular.element(document.querySelector('[ng-controller=mainCtrl]')).scope().myCartOverlayFlag = false;
	
	angular.element(document.querySelector('[ng-controller=mainCtrl]')).scope().myCart = <%= new ObjectMapper().writer().writeValueAsString(((OrderService)ContextHolder.getApplicationContext().getBean("orderService")).getCart(Constants.INFO_TYPE_FULL,session).getBody())%>;
	
	angular.element(document.querySelector('[ng-controller=mainCtrl]')).scope().refreshCartData = function(){
		var myCart = angular.element(document.querySelector('[ng-controller=mainCtrl]')).scope().myCart;
		myCart.mrpTotal = 0 ; 
		myCart.sellingPriceTotal = 0 ; 
		for(var i=0;myCart.cartItems!= undefined && i<myCart.cartItems.length;i++) {
			var item = myCart.cartItems[i];
			item.selectedQuantity = item.quantityPriceList[0];
			for(var j=0;j<item.quantityPriceList.length;j++){
				var quantityObj = item.quantityPriceList[j];
				if(quantityObj.id == item.selectedQuantityId){
					item.selectedQuantity = quantityObj;
					myCart.mrpTotal = myCart.mrpTotal + parseFloat(quantityObj.mrp);
					myCart.sellingPriceTotal = myCart.sellingPriceTotal + parseFloat(quantityObj.sellingPrice);
				}
			}
		}
	}
	angular.element(document.querySelector('[ng-controller=mainCtrl]')).scope().refreshCartData();
}	
loadCartScript();	
</script>
<%@ include file="viewCartHtmlContent.jsp" %>