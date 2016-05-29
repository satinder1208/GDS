	var mkApp = angular.module('mkApp', []);
	var ctrl = mkApp.controller('mainCtrl', function($scope, $http, $timeout,$compile) {

		$scope.criteria = "";
		$scope.activeLocalityResult = null;
		$scope.selectedLocality = "";
		$scope.activeSearchBox = "true";
		$scope.activeSelectedLocalitySpan = null;
		$scope.myCart = {
			"noOfItem":0,
			"total":0
		};
		$scope.locality = "Select Locality";
		$scope.myCartHtmlLoaded = false;
		$scope.loginDialogHtmlLoaded = false;
		$scope.subscriptionDialogHtmlLoaded = false;
		$scope.afterLoginMenuItems = 
		[
		 	{"title":"Account", "menuItemUrl":"/account/myAccount"},
		 	{"title":"Wallet", "menuItemUrl":"/account/myWallet"},
		 	{"title":"Order", "menuItemUrl":"/account/myOrder"},
		 	{"title":"Subscription", "menuItemUrl":"/account/mySubscription"},
		 	{"title":"Profile", "menuItemUrl":"/account/myProfile"},
		 	{"title":"Logout", "menuItemUrl":"/j_spring_security_logout"}
		];
		$scope.homePageTopBanner = {"bannerImage":"/mk/resources/image/homepage/topBanner.jpg","bannerUrl":"/pView?q=P1004V1"};
		$scope.homePageSlider = 
		[
		 	{"titleTop":"Extra 2% Off","titleBottom":"Milk","bannerImage":"/mk/resources/image/homepage/pramotionContentLeft.jpg","bannerUrl":"/pView?q=P1004V1"},
		 	{"titleTop":"Extra 2% Off","titleBottom":"Milk","bannerImage":"/mk/resources/image/homepage/pramotionContent1.jpg","bannerUrl":"/pView?q=P1007V1"},
		 	{"titleTop":"Extra 2% Off","titleBottom":"Milk","bannerImage":"/mk/resources/image/homepage/pramotionContent3.jpg","bannerUrl":"/pView?q=P1003V1"},
		 	{"titleTop":"Extra 2% Off","titleBottom":"Milk","bannerImage":"/mk/resources/image/homepage/banner5.png","bannerUrl":"/pView?q=P1013V1"},
		 	{"titleTop":"Extra 2% Off","titleBottom":"Milk","bannerImage":"/mk/resources/image/homepage/pramotionContent5.jpg","bannerUrl":"/pSearch?q=C1001&o=4&p=1"}
		];
		$scope.selectedSliderMenuIndex = 0;
		$scope.preSelectedSliderMenuIndex = -1;
		$scope.homePageRightPanelItems = 
		[
		 {"bannerImage":"/mk/resources/image/homepage/rightPanelHomePageItem1.png","bannerUrl":"/pView?q=P1004V1"},
		 {"bannerImage":"/mk/resources/image/homepage/rightPanelHomePageItem2.png","bannerUrl":"/pView?q=P1004V1"},
		 {"bannerImage":"/mk/resources/image/homepage/rightPanelHomePageItem3.png","bannerUrl":"/pView?q=P1004V1"},
		 {"bannerImage":"/mk/resources/image/homepage/rightPanelHomePageItem4.png","bannerUrl":"/pView?q=P1004V1"},
		 {"bannerImage":"/mk/resources/image/rightPanelAdd.jpg","bannerUrl":"/pView?q=P1004V1"},
		 {"bannerImage":"/mk/resources/image/homepage/rightPanelHomePageItem5.png","bannerUrl":"/pView?q=P1004V1"},
		 {"bannerImage":"/mk/resources/image/homepage/rightPanelHomePageItem6.png","bannerUrl":"/pView?q=P1004V1"},
		 {"bannerImage":"/mk/resources/image/homepage/rightPanelHomePageItem7.png","bannerUrl":"/pView?q=P1004V1"}
		];
		$scope.sliderAutorun = true;
		$scope.setSliderIndx = function(){
			if($scope.sliderAutorun){
				$scope.selectedSliderMenuIndex = ($scope.selectedSliderMenuIndex+1)%$scope.homePageSlider.length;
				$scope.fededOutSliderContent();
				$timeout($scope.setSliderIndx, 5000);
			}
		}
		$timeout($scope.setSliderIndx, 5000);
		
		$scope.fededOutSliderContent = function(){
			if($scope.selectedSliderMenuIndex != $scope.preSelectedSliderMenuIndex){
			    var topImage = $('#sliderBannerContentTopImg');
			    var bottomImage = $('#sliderBannerContentBottomImg');
			    bottomImage.attr('src', $scope.homePageSlider[$scope.selectedSliderMenuIndex].bannerImage);
			    topImage.animate({ opacity: 0}, 400,function(){
			    	topImage.attr('src', $scope.homePageSlider[$scope.selectedSliderMenuIndex].bannerImage);
			    	topImage.css("opacity","1");
			    });
			}
			$scope.preSelectedSliderMenuIndex = $scope.selectedSliderMenuIndex;
		}
		
		$scope.homePageLeftPanelThird = { "title": "Dairy Product",
			"items":
			[
			 {"bannerImage":"/mk/resources/image/homepage/leftItemImg1.jpg","bannerUrl":"/pView?q=P1004V1"},
			 {"bannerImage":"/mk/resources/image/homepage/rightPanelHomePageItem2.png","bannerUrl":"/pView?q=P1004V1"},
			 {"bannerImage":"/mk/resources/image/homepage/rightPanelHomePageItem12.png","bannerUrl":"/pView?q=P1004V1"}
			]
			}
		$scope.homePageLeftPanelsingleBanner = {"bannerImage":"/mk/resources/image/homepage/singleBanner.jpg","bannerUrl":"/pView?q=P1004V1"};
		
		$scope.homePageLeftPanelThreeItem2 = { "title": "Featured Product",
				"items":
			[
			 {"bannerImage":"/mk/resources/image/homepage/rightPanelHomePageItem11.png","bannerUrl":"/pView?q=P1004V1"},
			 {"bannerImage":"/mk/resources/image/homepage/rightPanelHomePageItem10.png","bannerUrl":"/pView?q=P1004V1"},
			 {"bannerImage":"/mk/resources/image/homepage/rightPanelHomePageItem9.png","bannerUrl":"/pView?q=P1004V1"}
			]};
			
		initializeCart($scope);
		$scope.goToUrl = function(url){
			if(url != undefined && url != null && url != ''){
				window.location.href = $scope.contextPath+url;
			}
		}
		$scope.$watch("criteria", function(newValue, oldValue) {
			
			if($scope.criteria != ""){
				$http.get($scope.contextPath+'/locality/search.do?q=' + $scope.criteria).success(
					function(data) {
						$scope.localities = data;
					});
				$scope.activeLocalityResult = true;
			}else{
				$scope.localities = null;
			}
		});
		$scope.$watch("selectedLocality", function(newValue, oldValue) {
			if($scope.selectedLocality != ""){
				//alert("You have selected:\n"+$scope.selectedLocality);
			}
		});
		
		$scope.setSelectedLocality = function(selectedLocality){
			$scope.selectedLocality = selectedLocality.shortAddress;
			$scope.activeSearchBox = null;
			$scope.activeSelectedLocalitySpan = 'true';
		};
		
		$scope.clickOnSelectedLocalitySpan = function(){
			$scope.activeSearchBox='true';
			$scope.activeLocalityResult='true';
			$scope.activeSelectedLocalitySpan = null;
			$scope.criteria = "";
		};
		$scope.clickOutsideLocalitySearchPanel = function(obj){
			$scope.activeLocalityResult=null;
		};
		
		// Menu related code:
		$scope.setMenuActivetimeout = null;
		$scope.activeMenu = null;
		$scope.leaveFromMenuFlag = false;
		$scope.setLeaveFromMenuFlagFlaseTimeout = null;
		
		$scope.setLeaveFromMenuFlagFlase = function(){
			$scope.leaveFromMenuFlag = false;
		}
		$scope.setMemuactive = function(){
			$scope.activeMenu.active = true;
		};
		$scope.menuMouseEnter = function(menu){
			if($scope.leaveFromMenuFlag == false){
				$scope.activeMenu = menu;
				$timeout.cancel($scope.setLeaveFromMenuFlagFlaseTimeout);
				$scope.setMenuActivetimeout = $timeout($scope.setMemuactive, 200);
			}else{
				menu.active = true;
			}
		};
		$scope.menuMouseLeave = function(menu){
			if($scope.leaveFromMenuFlag == false){
				$scope.leaveFromMenuFlag = true;
				$timeout.cancel($scope.setMenuActivetimeout);
				$scope.setLeaveFromMenuFlagFlaseTimeout = $timeout($scope.setLeaveFromMenuFlagFlase, 5);
				menu.active = false;
			}
		};
		
		// Following Code is for header
		// Start Header scripts
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
		
		// Product search Auto Complete
		$(function() {	 
			$( "#productSearchInput" ).catcomplete({
				source: $scope.contextPath+"/product/productSuggestion.do",
				select: function( event, ui ) {
					if(ui.item.url != null && ui.item.url != undefined && ui.item.url.trim().length > 0){
						location.href = ui.item.url;
					}
				}
			});
		});
	
		// Locality search Auto Complete
		$(function() {	 
			$( "#localitySelectInputTextBox" ).catcomplete({
				source: $scope.contextPath+"/locality/localitySuggestion.do",
				select: function( event, ui ) {
					// TO DO Code that set locality at server side. Call a ajex request.
					
					//Following code set locality in 
					$scope.locality = ui.item.shortDisplayName;
					$("#localityLabelDivAtHeade").html(ui.item.shortDisplayName);
					//Following code is set small font in case of if locality name exceeds 20 character
					if($scope.locality.length > 18){
						$('.header-locality-div').css("fontSize", "10px");
						if($scope.locality.length > 20){
							$('.locality-icon').css("height", "17px")
						}
					}else{
						$('.header-locality-div').css("fontSize", "13px");
						$('.locality-icon').css("height", "21px");
					}

					//Hide locality select dialog
					$("#localityId").hide();
				},
				close: function( event, ui ) {
					//Clean the value of text box after close the auto complete widget
					$("#localitySelectInputTextBox")[0].value="";
				}
			});
		});
		
		$scope.closeLocalitySearchDialog = function(){
			$("#localityId").hide();
		}
		
		$scope.closeCardAndSubscriptionDialog = function(){
			$("#cardAndSubscriptionId").hide();
			$scope.closeCartUI();
		}
		
	
		//Following is the code for remove header at scrolling down and display down at scrolling up
		$(function(){
			//Keep track of last scroll
			var lastScroll = 0;
			$(window).scroll(function(event){
				//Sets the current scroll position
				var st = $(this).scrollTop();
				//Determines up-or-down scrolling
				if (st > lastScroll){
					//Replace this with your function call for downward-scrolling
					//$("#menuFileHolderdiv").slideUp(300);
					if(window.scrollY > 100){
						$("#pageHeader").slideUp(600, 'easeOutQuart');
					}
				} else {
					//Replace this with your function call for upward-scrolling
					//$("#menuFileHolderdiv").slideDown(300);
					$("#pageHeader").slideDown(600, 'easeOutQuart');
				}
				//Updates scroll position
				lastScroll = st;
			});
		});
		
		// Following method is called when we click on 'My Cart' in header
		$scope.clickOnCart = function(){
			if($scope.myCartHtmlLoaded == false){
				$http.get($scope.contextPath+'/order/viewCart.do').success(
					function(data) {
						var element  = $compile(data)($scope);
						$("#myCartDialogInnerContent").replaceWith(element);
						$("#myCartDialogeLoadingImgId").hide();
						$scope.myCartHtmlLoaded = true;
					}
				);
			}else{
				$http.get($scope.contextPath+'/order/getCart.do?infoType=full').success(
					function(data) {
							$scope.myCart = data;
							$scope.refreshCartData();
							$scope.$apply();
					});
			}
			$("#cardAndSubscriptionId").fadeIn();
		}
		
		// Following method is called when we click on 'Locality' in header
		$scope.clickOnLocality = function(){
			$("#localityId").slideToggle("slow");
		}
		
		// Following method is called when we click on 'Locality' in header, Open Login dialog
		$scope.clickOnLogin = function(){
			if($scope.loginDialogHtmlLoaded == false){
				$http.get($scope.contextPath+'/pages/login.jsp').success(
						function(data) {
							var element  = $compile(data)($scope);
							$("#loginDialogInnerContent").replaceWith(element);
							$scope.loginDialogHtmlLoaded = true;
						}
					);
			}

			//Binding 'Esc' key event so login dialog will close on press 'Esc'
			$(document).keyup(function(event) {
				if(event.which == 27){
					//closeLoginDialog() method is defined in login.jsp
					mainController.scope().closeLoginDialog();
					//Remove event after 'Esc' event done
					$(document).unbind( "keyup" );
				}
			});

			$("#loginId").fadeIn();
		}
		
		// Open Subscription dialog
		$scope.clickOnAddSubscription = function(variantId, qantity){
			// Check that user is loggedIn or Not,
			//If yes then open add subscription UI otherwise open login dialog
			if($scope.user.loggedIn == true){
				// Follwoing code is open add subscription dialog
				if($scope.subscriptionDialogHtmlLoaded == false){
					$http.get($scope.contextPath+'/order/addSubscription.do?q='+variantId+'&qid='+qantity).success(
							function(data) {
								var element  = $compile(data)($scope);
								$("#subscriptionDialogInnerContent").replaceWith(element);
								$scope.subscriptionDialogHtmlLoaded = true;
								$scope.initializeSubcriptionUI();
							}
						);
				}else{
					$http.get($scope.contextPath+'/product/productDetail.do?productId='+variantId).success(
						function(data) {
							$scope.productDetail = data;
							
							var selectedVarientIndex = 0;
							
							for(var i=0;i<$scope.productDetail.variants.length;i++) {
								if($scope.productDetail.variants[i].selected){
									selectedVarientIndex = i;
								}
							}
							$scope.selectedVariant = $scope.productDetail.variants[selectedVarientIndex];
							$scope.selectedQuentityPrice = $scope.selectedVariant.quantityPriceList[qantity-1];
							$scope.selectedSliderImageObj = $scope.selectedVariant.images[0];
						}
					);
				}
				$("#subscriptionDialogId").fadeIn();
				$("html, body").animate({ scrollTop: 0}, 400);
			}else{ // else of if(user.loggedIn == true)
				// Open login dialog
				$scope.clickOnLogin();
			}
		}

		$scope.closeSubcriptionDialog = function(){
			$("#subscriptionDialogId").hide();
			$scope.closeSubcriptionUI();
		}
		// Ends Header scripts
		
		//Top flash message close
		$scope.closeTopflashMsg = function(){
			$('#topFlashMsgDiv').hide();
		};
		
		// Starts: Add to Kart Button Functionality
		$scope.addToCart = function(productId, variantId, quantityId){
			$('#topFlashMsgText').html('We are going to add Item in your Kart');
			$('#topFlashMsgDiv').slideDown("500");
			$.ajax({
				url: "order/addToCart.do?productId="+productId+"&variantId="+variantId+"&quantityId="+quantityId,
				context: document.body
			}).success(function(data, textStatus, jqXHR) {
				$('#signUpProgress').hide();
				if(data.success){
					$('#topFlashMsgText').html(data.flashMessage);
					$scope.myCart=data;
					$scope.$apply();
					$('#topFlashMsgDiv').slideUp("500");
				}else{
					// Setting Error message
					$('#topFlashMsgText').html(data.flashMessage);
				}
			}).error(function( jqXHR, textStatus, errorThrown){
				$('#topFlashMsgText').html('Not able to connect. Please try again.');
			});
			
		};
		
		// Ends: Add to Kart Button Functionality
		
		jQuery( document ).ready(function( $ ) {
			$('.my-cart-div').show();
			$('#menuDivOuterDivId').show();
			$('.mainContent').show();
			$('.loginLogout-div').show();
			//$('.header-locality-div').show();
		});

	});
	
	
