	var mkApp = angular.module('mkApp', []);
	var ctrl = mkApp.controller('mainCtrl', function($scope, $http, $timeout,$compile) {

		$scope.criteria = "hi";
		$scope.myCartOverlayFlag = true;
		$scope.myCart = {};
		loadCartScript();
		$scope.mycartbottomContentHideFlag = true;
		$scope.onLoadFlag = false;
		
		initializeAddressList($scope);
		initializeUser($scope);
		
		if($scope.user.loggedIn){
			if($scope.userAddressList != null && $scope.userAddressList.length > 0){
				$scope.openSectionId = 'order';
			}else{
				$scope.openSectionId = 'address';
			}
		}else{
			$scope.openSectionId = 'login';
		}
		$scope.$watch("openSectionId", function(newValue, oldValue) {
			$scope.openSection(newValue);
			$scope.addressInfoMessage = '';
		});

		$scope.CartContinueToPayment = function(){
			//$('#myCartPayContent').slideUp('fast');
			$scope.openSectionId = 'paymentMethod';
		}
		$scope.togglePaymentMyCart = function(){
			$('#myCartPayContent').slideToggle('fast');
		}
		$scope.togglePaymentLogin = function(){
			$('#paymentLoginContent').slideToggle('fast');
		}
		$scope.openSection = function(section){
			if(section == "login"){
				$('#paymentLoginContent').slideDown('fast');
				$('#myCartPayContent').slideUp('fast');
				$('#paymentAddressContent').slideUp('fast');
				$('#paymentMethodContent').slideUp('fast');
			}else if(section == "order"){
				$('#paymentLoginContent').slideUp('fast');
				$('#myCartPayContent').slideDown('fast');
				$('#paymentAddressContent').slideUp('fast');
				$('#paymentMethodContent').slideUp('fast');
				
				$("html, body").animate({ scrollTop: 230}, 600);
				
			}else if(section == 'address'){
				$('#paymentLoginContent').slideUp('fast');
				$('#myCartPayContent').slideUp('fast');
				$('#paymentAddressContent').slideDown('fast');
				$('#paymentMethodContent').slideUp('fast');
			}else if(section == 'paymentMethod'){
				$('#paymentLoginContent').slideUp('fast');
				$('#myCartPayContent').slideUp('fast');
				$('#paymentAddressContent').slideUp('fast');
				$('#paymentMethodContent').slideDown('fast');
			}

			
		}
		
		
		//follwoing widget is for autocomplete functionality (This code is copied from mkapp.js)
		$.widget( "custom.catcomplete", $.ui.autocomplete, {
			_create: function() {
				this._super();
				this.widget().menu( "option", "items", "> :not(.ui-autocomplete-category)" );
			},
			_renderMenu: function( ul, items ) {
				var that = this,
				currentCategory = "";
				$.each( items, function( index, item ) {
					var li;
					if ( item.category != currentCategory ) {
						ul.append( "<li class='ui-autocomplete-category'>" + item.category + "</li>" );
						currentCategory = item.category;
					}
					li = that._renderItemData( ul, item );
					if ( item.category ) {
						li.attr( "aria-label", item.category + " : " + item.label );
						if(item.imageURL != null && item.imageURL != undefined && item.imageURL.trim().length > 0){
							li.html("<div class='ui-autocomplete-item-div'><div class='ui-autocomplete-item-img-div'><img class='ui-autocomplete-item-img' src='"+item.imageURL+"'></div>"+"<div class='ui-autocomplete-item-name-div'><span class='ui-autocomplete-item-name-span'>"+item.label+"</span></div></div>");
						}
					}
				});
			}
		});
		
		$scope.logout = function(){
			// Send logout request
			$.ajax({
				url: "/mk/j_spring_security_logout",
				data: "",
				context: document.body,
				type: 'POST'
			}).success(function(data, textStatus, jqXHR) {
				
				$scope.gotoHome();
				
			}).error(function( jqXHR, textStatus, errorThrown){
				$scope.gotoHome();
			});
			
		}
		$scope.gotoHome = function(){
			window.location.href = "/mk";
		}
		
		$scope.placeOrder = function(kartId, payId)
		{
			
			$.ajax({
				url: "order/placeOrder.do?kartId="+kartId+"&addressId="+$scope.defaultAddress.addressId+"&payType="+payId,
				context: document.body
			}).success(function(data, textStatus, jqXHR) {
				if(data.success){
					alert(data.flashMessage);
					$scope.gotoHome();
				}else{
					// Setting Error message
					alert(data.flashMessage);
				}
			}).error(function( jqXHR, textStatus, errorThrown){
				alert('Unable to connect. Please try again.');
			});
		}

		
		$scope.openSection('login');
		
		jQuery( document ).ready(function( $ ) {
			  $('.no-item-overlay .infoMsgDiv').show();
			  $('#loadProcessPaymentPage').hide();
		});
		
	});
	
