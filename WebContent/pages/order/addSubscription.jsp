<%@page import="com.mk.services.ProductService"%>
<%@page import="com.mk.utils.ContextHolder"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>

<script type="text/javascript">
var scope = angular.element(document.querySelector('[ng-controller=mainCtrl]')).scope();
scope.closeSubcriptionUI = function(){
};

scope.subscriptionUILabel = {
		"title":"Subscription",
		"offers":"Current Offers:"
}

scope.initializeSubcriptionUI = function(){
	scope.productDetail = <%= new ObjectMapper().writer().writeValueAsString(((ProductService)ContextHolder.getApplicationContext().getBean("productService")).productDetail(request.getParameter("q")).getBody())%>;
	var selectedQid	= 	<%=request.getParameter("qid")%>;										
	var selectedVarientIndex = 0;
	
	for(var i=0;i<scope.productDetail.variants.length;i++) {
		if(scope.productDetail.variants[i].selected){
			selectedVarientIndex = i;
		}
	}
	scope.selectedVariant = scope.productDetail.variants[selectedVarientIndex];
	scope.selectedQuentityPrice = scope.selectedVariant.quantityPriceList[selectedQid - 1];
	scope.selectedSliderImageObj = scope.selectedVariant.images[0];
							
	initializeAddressList(scope);
							

	scope.startDate = ''; 
	scope.endDate='';
	scope.daySelection = 1;
	scope.customMon = false;
	scope.customTue = false;
	scope.customWed = false;
	scope.customThu = false;
	scope.customFri = false;
	scope.customSat = false;
	scope.customSun = false;
	scope.termAndCondition = false;
	scope.firstClickOnTermAndCondition = false;
	if(scope.userAddressList.length==0){
		scope.slideDownAddressList();
	}
	$(function() {
	   $( "#startDatePicker" ).datepicker({
	     numberOfMonths: 2,
	     showButtonPanel: true,
	     dateFormat: 'dd/mm/yy',
	     minDate: 1,
	     maxDate: "+2M",
	     onClose: function( selectedDate ) {
	         $( "#endDatePicker" ).datepicker( "option", "minDate", selectedDate );
	     }
	   });
	});
	$( "#startDatePicker" ).datepicker( "setDate", 1 );
	$(function() {
	   $( "#endDatePicker" ).datepicker({
	     numberOfMonths: 2,
	     showButtonPanel: true,
	     dateFormat: 'dd/mm/yy'
	   });
	});

};

scope.createSubscription = function(){
	if(scope.validateSubscription()){
		$.ajax({
			url: "order/createSubscription.do",
			data:
				"&addressId="+ scope.defaultAddress.addressId+ 
				"&variantId="+ scope.selectedVariant.id+
				"&quantityId="+ scope.selectedQuentityPrice.id+
				"&startDate="+ $("#startDatePicker")[0].value+
				"&endDate="+ scope.endDate+
				"&daySelection="+ scope.daySelection+
				"&customMon="+ scope.customMon+
				"&customTue="+ scope.customTue+
				"&customWed="+ scope.customWed+
				"&customThu="+ scope.customThu+
				"&customFri="+ scope.customFri+
				"&customSat="+ scope.customSat+
				"&customSun="+ scope.customSun,
			context: document.body,
			type: 'GET'
		}).success(function(data, textStatus, jqXHR) {
			
			if(data.success){
				$("#subscriptionDialogId").hide();
				$('#topFlashMsgText').html(data.message);
				$('#topFlashMsgDiv').slideDown("500");
				$('#topFlashMsgDiv').slideUp("500");
			}else{
				
			}
		}).error(function( jqXHR, textStatus, errorThrown){
			
		});
	}

};
scope.validateSubscription = function(){
	if(scope.defaultAddress == null || scope.defaultAddress == undefined){
		$(".paymentSectionsOuterDiv").effect("shake");
		return false;
	}else if(scope.termAndCondition == false){
		$("#tosDiv").effect("shake");
		return false;
	}else{
		return true;
	}
};
scope.slideDownAddressList = function(){
	scope.openSectionId = 'address';
	$('#paymentAddressContent').slideDown('fast');
	if(scope.userAddressList.length != 0){
		if(scope.userAddressList.length <= 3){
			$("html, body").animate({ scrollTop: 230}, 600);
		}else{
			$("html, body").animate({ scrollTop: 290}, 600);
		}
	}
};

</script>
<style>
/* Following style is used in addAddressAndAddressList.jsp */
/*Start: style of addAddressAndAddressList.jsp*/
/********** START : This style is also existed in productDetail.jsp page **********************************/
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
.brandNamediv {
	margin: 5px;
}
.brandNamediv a{
	text-decoration: none;
	text-transform: capitalize;
	color: #D5C6C6;
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
/********** END : This style is also existed in productDetail.jsp page **********************************/
.paymentSectionsOuterDiv{
	width: 900px;
	box-shadow: 0px 0px 1px grey;
}
.paymentSectionsOuterDiv .paymentSectionsHeader{
	background-color: rgb(252, 252, 252);
	font-family: arial, san-sarif;
	border: 0px solid gray;
	margin-top: 10px;
	font-size: 17px;
	padding: 12px;
	color: rgb(79, 79, 79);
	margin-bottom: 15px;
	cursor: pointer;
}
.paymentSectionsOuterDiv .bottomMyCartPayBlock{
	padding-top: 10px;
	padding-bottom: 10px;
}
.paymentSectionsOuterDiv .continueToPayment{
	margin-right: 10px;
	font-family: arial, san-sarif;
	font-size: 18px;
	background: #f75b16;
	border: 0px;
	padding: 5px;
	width: 100px;
}
.paymentSectionsOuterDiv .continueToPayment a{
	text-decoration: none!important;
	color: white;
}

.addressHeaderNameAddressDiv{
	margin-left: 30px;
}
.addressHeaderNameAddressDiv .addressHeaderNameMobileDiv{
	font-size: 20px;
}
.addressHeaderNameAddressDiv .addressHeaderNameMobileDiv .mobileSpan{
	font-size: 14px;
}
.addressHeaderNameAddressDiv .addressHeaderAddressDiv{
	font-size: 14px;
}
.addressHeaderNameAddressDiv .addressHeaderAddressDiv .flatNo{
	font-size: 14px;
	font-weight: bold;
}


/*End: style of addAddressAndAddressList.jsp*/

.addSubscriptionDialogOuterDiv{
	font-family: arial, san-sarif;
}
.addSubscriptionDialogOuterDiv .addSubscriptionLabel{
	font-size: 14px;
	border-bottom: 1px solid beige;
}
.addSubscriptionDialogOuterDiv .myCartBody{
	font-size: 13px;
	margin-left: 20px;
}
.addSubscriptionDialogOuterDiv .myCartBody .contenttable{
	border-spacing: 0px;
	width: 100%;
}

.addSubscriptionDialogOuterDiv .myCartBody .contenttable .itemImageCell{
	
}
.addSubscriptionDialogOuterDiv .myCartBody .contenttable .itemImageCell img{
	max-width: 100px;
	max-height: 100px; 
}
.addSubscriptionDialogOuterDiv .datePickerTableDiv{
	margin: 5px;
}

.addSubscriptionDialogOuterDiv .datePickerTableDiv .datePickerTable{
	width: 100%;
	font-size: 14px;
	margin-left: 15px;
}
.addSubscriptionDialogOuterDiv .datePickerTableDiv .datePickerTable tr{
	height: 50px;
}
.addSubscriptionDialogOuterDiv .datePickerTableDiv .datePickerTable .radiBoxDiv{
	float: left;
	margin-right: 10px;
	cursor: pointer;
	padding: 5px;
	border-radius: 5px;
}
.addSubscriptionDialogOuterDiv .datePickerTableDiv .datePickerTable .radiBoxDivHover{
	background: beige;
}
.addSubscriptionDialogOuterDiv .datePickerTableDiv .datePickerTable .dateSelectionLabel{
	float: left;
	padding-top: 5px;
	padding-bottom: 5px;
	border-radius: 5px;
}
.addSubscriptionDialogOuterDiv .datePickerTableDiv .datePickerTable .checkBoxDiv{
	float: left;
	margin-right: 5px;
	font-size: 11px;
}
.addSubscriptionDialogOuterDiv .datePickerTableDiv .datePickerTable .customLabel{
	float: left;
	margin-right: 10px;
}
.addSubscriptionDialogOuterDiv .datePickerTableDiv .datePickerTable .endDateTdDiv{
	opacity: .3;
	float: left;
}
.addSubscriptionDialogOuterDiv .datePickerTableDiv .datePickerTable .endDateTdDivHover{
	opacity: 1;
}
.addSubscriptionDialogOuterDiv .datePickerTableDiv .datePickerTable .dateFormateSpan{
	color: gray;
	font-size: 12px;
}
.addSubscriptionDialogOuterDiv .createSubscriptionButtonOuterDiv{
	border-top: 1px solid antiquewhite;
	padding-top: 10px;
	padding-bottom: 10px;
	background: rgb(250, 236, 217);
}
.addSubscriptionDialogOuterDiv .createSubscriptionButtonOuterDivHover{
	background: white;
	cursor: pointer;
}
.addSubscriptionDialogOuterDiv .createSubscriptionButtonOuterDivEmptyAddressList{
	background: white;
}

.tosppCheckboxDiv{
	float: left;
	margin-right: 10px;
	height: 30px;
}
.tosppCheckboxTextDiv a{
	text-decoration: none;
	color: blue;
}
.tosppCheckboxTextDiv .iAgreeTextDiv{
}
.tosppCheckboxTextDiv .warningTextDiv{
	margin-top: 10px;
	color: red;
}
</style>
<div class="addSubscriptionDialogOuterDiv">
	<div class="addSubscriptionLabel" ng-bind="subscriptionUILabel.title"></div>
	<div>
		<div class="myCartBody">
			<table class="contenttable">
				<tr align="center">
					<td class="itemImageCell">
						<div style="margin: 10px;">
							<img alt=""  ng-src="{{selectedVariant.images[0].img100x100}}">
						</div>
					</td>
					<td class="itemNameDesc">
						<div class="itemDescription">
							<div class="brandNamediv" align="left"><a href="#">{{productDetail.brand.name}}</a>
								<span class="productDetailProductNameDiv" style="font-size: 20px;">{{selectedVariant.name}}</span>
							</div>
							
							<div>
								<table class="productsOffers" ng-show="productDetail.offers.length > 0 || selectedVariant.offers.length > 0 || selectedQuantity.offers.length > 0">
									<tr><th align="left" ng-bind="subscriptionUILabel.offers"></th></tr>
									<tr class="offer" ng-repeat="offer in productDetail.offers"><td>{{offer.offerText}}</td></tr>
									<tr class="offer" ng-repeat="offer in selectedVariant.offers"><td>{{offer.offerText}}</td></tr>
									<tr class="offer" ng-repeat="offer in selectedQuantity.offers"><td>{{offer.offerText}}</td></tr>
								</table>
							</div>
						</div>
					</td>
					<td class="otherTd">
					<div style="margin: 10px;">
						<div class="productDetailMRPDiv productDetailMRPDivLineThrough">
							Current MRP <span>Rs {{selectedQuentityPrice.mrp}}</span>
						</div>
						<div class="productDetailSellingPriceDiv">
							Current Sell Price <span class="productDetailRateSpan"> Rs {{selectedQuentityPrice.sellingPrice}}</span>
						</div>
						<div class="productDetailSelectQuantityDiv">
							<span>Quantity:</span>
							<select ng-model="selectedQuentityPrice" ng-options="quantityPrice.name for quantityPrice in selectedVariant.quantityPriceList"></select>
						</div>
					</div>
					</td> 
				</tr>
			</table>
		</div>
	</div>
	<%@ include file="../addAddressAndAddressList.jsp" %>
	<div class="datePickerTableDiv">
		<table class="datePickerTable">
			<tbody>
				<tr>
					<td>Start Date: <input type="text" readonly="readonly" id="startDatePicker"><span class="dateFormateSpan"> (DD/MM/YYYY)</span></td>
					<td>
						<div class="endDateTdDiv" ng-class="{endDateTdDivHover: endDateTdHover}" ng-mouseenter="endDateTdHover = true" ng-mouseleave="endDateTdHover = false">
							End Date: <input type="text" ng-model="endDate" id="endDatePicker"><span class="dateFormateSpan"> (DD/MM/YYYY)</span>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2"> <div class="dateSelectionLabel">Day Selection:</div>
  						<div class="radiBoxDiv" ng-click="daySelection = 1" ng-class="{radiBoxDivHover: daySelectionDivHover1}" ng-mouseenter="daySelectionDivHover1 = true" ng-mouseleave="daySelectionDivHover1 = false"><input type="radio" ng-model="daySelection" ng-value="1"> Daily</div>
						<div class="radiBoxDiv" ng-click="daySelection = 2" ng-class="{radiBoxDivHover: daySelectionDivHover2}" ng-mouseenter="daySelectionDivHover2 = true" ng-mouseleave="daySelectionDivHover2 = false"><input type="radio" ng-model="daySelection" ng-value="2"> Alternate</div>
						<div class="radiBoxDiv" ng-click="daySelection = 3" ng-class="{radiBoxDivHover: daySelectionDivHover3}" ng-mouseenter="daySelectionDivHover3 = true" ng-mouseleave="daySelectionDivHover3 = false">
							<div class="customLabel"><input type="radio" ng-model="daySelection" ng-value="3"> Custom </div>
							<div class="checkBoxDiv" ng-click="customMon = !customMon"><input type="checkbox">MON</div>
							<div class="checkBoxDiv" ng-click="customTue = !customTue"><input type="checkbox">TUE</div>
							<div class="checkBoxDiv" ng-click="customWed = !customWed"><input type="checkbox">WED</div>
							<div class="checkBoxDiv" ng-click="customThu = !customThu"><input type="checkbox">THU</div>
							<div class="checkBoxDiv" ng-click="customFri = !customFri"><input type="checkbox">FRI</div>
							<div class="checkBoxDiv" ng-click="customSat = !customSat"><input type="checkbox">SAT</div>
							<div class="checkBoxDiv" ng-click="customSun = !customSun"><input type="checkbox">SUN</div>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" valign="top">
						<div id="tosDiv">
							<div class="tosppCheckboxDiv"><input type="checkbox" ng-click="firstClickOnTermAndCondition = true" ng-model="termAndCondition"></div>
							<div class="tosppCheckboxTextDiv">
								<div class="iAgreeTextDiv">
								I agree to the MorningKart 
								<a target="_blank" href="<%=request.getContextPath()%>/termsOfService">Terms of Service</a> 
								and 
								<a target="_blank" href="<%=request.getContextPath()%>/privacyPolicy">Privacy Policy</a>.
								</div>
								<div class="warningTextDiv" ng-show="!termAndCondition && firstClickOnTermAndCondition">In order to use our services, you must agree to MorningKart's Terms of Service.</div>
							</div>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="createSubscriptionButtonOuterDiv" ng-click="createSubscription()" ng-class="{createSubscriptionButtonOuterDivHover: (termAndCondition && defaultAddress != null && defaultAddress != undefined)}" >
		<div>
			<input type="button" class="button" value="Start Subscription" name="openNewAddressDialog" ng-class="{buttonHover: (termAndCondition && defaultAddress != null && defaultAddress != undefined)}" >
		</div>
	</div>
</div>