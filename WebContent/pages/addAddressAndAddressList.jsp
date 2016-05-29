<%@page import="com.mk.utils.ContextHolder"%>
<%@page import="com.mk.services.UserService"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<script type="text/javascript">

var initializeAddressList = function(scope){ 
	scope.userAddressList =  <%= new ObjectMapper().writer().writeValueAsString(((UserService)ContextHolder.getApplicationContext().getBean("userService")).getAddressList(session).getBody())%>;

	scope.selectedAddress = {name:"",mobile:"",flatNo:"",locality:"",action:"add",buttonLabel:"Add & Continue"};
	scope.addressInputFocusFlags = {name:false,mobile:false,flatNo:false,locality:false};
	scope.addresserrorMsg = {name:"Please enter Name",
								mobile:"Please enter 10 digit mobile no",
								flatNo:"Please enter your flat no.",
								locality:"Please select your society/colony"}
	scope.editedAddress = '';
	scope.defaultAddress = null;
	scope.openSectionId = null;
	scope.addressInfoMessage = '';
	scope.addressNoAddressMessage = 'Please add new address';
	scope.initializeDefaultAddress = function(){
		if(scope.userAddressList.length > 0){
			scope.defaultAddress = scope.userAddressList[0];
			for(var i=0; i<scope.userAddressList.length ; i++){
				if(scope.userAddressList[i].default){
					scope.defaultAddress = scope.userAddressList[i];
				}
			}
		}
	}
	scope.initializeDefaultAddress();
	
	scope.deliverHereOrContinue = function(address){
		scope.defaultAddress = address;
		scope.openSectionId = 'order';
		$('#paymentAddressContent').slideUp('fast');
		$("html, body").animate({ scrollTop: 0}, 600);
	}

	scope.closeAddressDialog = function(){
		if(scope.selectedAddress.action == 'edit'){
			scope.selectedAddress = {name:"",mobile:"",flatNo:"",locality:"",action:"add"};
		}
		$("#newAddressDialog").hide();
	}
	
	// action = 'edit' or 'add'
	scope.openAddressDialog = function(action,address){
		if(action == 'edit'){
			scope.editedAddress = address;
			//Copy address data
			scope.selectedAddress.name = address.name;
			scope.selectedAddress.mobile = address.mobile;
			scope.selectedAddress.flatNo = address.flatNo;
			scope.selectedAddress.addressId = address.addressId;
			scope.selectedAddress.locality = new Object();
			scope.selectedAddress.locality.label = address.localityLabel;
			scope.selectedAddress.locality.localityId = address.localityId;
			
			scope.selectedAddress.action = 'edit';
			scope.selectedAddress.buttonLabel = 'Edit & Continue';
		}else{
			scope.selectedAddress.action = 'add';
			scope.selectedAddress.buttonLabel = 'Add & Continue';
		}
		$("#newAddressDialog").slideDown();
	}

	scope.validateMobile = function(){
		var mobileNo = scope.selectedAddress.mobile;
        var pattern = /^\d{10}$/;
        if (pattern.test(mobileNo)) {
            return true;
        }
		return false;
	}
	
	scope.deleteAddress = function(address){
		$.ajax({
			url: "deleteAddress.do",
			data: "&addressId="+address.addressId,
			context: document.body,
			type: 'GET'
		}).success(function(data, textStatus, jqXHR) {
			
			if(data.success){
				scope.userAddressList.splice(scope.userAddressList.indexOf(address), 1);
				if(scope.defaultAddress == address){
					scope.defaultAddress = null;
					if(scope.userAddressList.length > 0){
						scope.defaultAddress = scope.userAddressList[0];
					}
				}
				scope.addressInfoMessage = 'Address deleted successfully.'
				scope.$apply();
			}else{
				// Setting Error message recieved from login service
				//$('#loginErrorMessage').html(data.message);
				//$('#loginErrorMessage').slideDown(200);
			}
		}).error(function( jqXHR, textStatus, errorThrown){
			//$('#loginProgress').hide();
			//$('#loginErrorMessage').html('Somthing wrong happen.');
			//$('#loginErrorMessage').slideDown(200);
		});

	}
	scope.addEditAddress = function(){
		if(scope.validateAddress()){
			var address = scope.selectedAddress;
			$.ajax({
				url: "addEditAddress.do",
				data: "&name="+address.name+"&mobile="+address.mobile+"&flatNo="+address.flatNo+"&localityId="+address.locality.localityId+"&addressId="+address.addressId+"&action="+address.action,
				context: document.body,
				type: 'GET'
			}).success(function(data, textStatus, jqXHR) {
				
				//$('#loginProgress').hide();
				if(data.success){
					// Refresh the URL after getting success fron login service
					//window.location.reload(true);
					console.log(address.action+' success');
					var tempAddress;
					if(address.action == 'edit'){
						tempAddress = scope.editedAddress;
					}else{
						tempAddress = new Object();
						address.addressId = data.addressId;
					}
					tempAddress.name = address.name;
					tempAddress.mobile = address.mobile;
					tempAddress.flatNo = address.flatNo;
					tempAddress.addressId = address.addressId;
					tempAddress.localityLabel = address.locality.label;
					tempAddress.localityId = address.locality.localityId;
					if(address.action == 'edit'){
						
					}else{
						scope.userAddressList.push(tempAddress);
						scope.selectedAddress = {name:"",mobile:"",flatNo:"",locality:"",action:"add"};
						scope.addressInputFocusFlags = {name:false,mobile:false,flatNo:false,locality:false};
					}
					scope.closeAddressDialog();
					scope.deliverHereOrContinue(tempAddress);
				}else{
					// Setting Error message recieved from login service
					//$('#loginErrorMessage').html(data.message);
					//$('#loginErrorMessage').slideDown(200);
				}
			}).error(function( jqXHR, textStatus, errorThrown){
				//$('#loginProgress').hide();
				//$('#loginErrorMessage').html('Somthing wrong happen.');
				//$('#loginErrorMessage').slideDown(200);
			});
		}
	}
	scope.validateAddress = function(){
		var name = scope.selectedAddress.name;
		var mobile = scope.selectedAddress.mobile;
		var flatNo = scope.selectedAddress.flatNo;
		var locality = scope.selectedAddress.locality;
		
		var returnFlag = true;
		
		if(name == null || name == undefined || name == ""){
			scope.addressInputFocusFlags.name = true;
			returnFlag = false;
		}
		if(scope.validateMobile() == false){
			scope.addressInputFocusFlags.mobile = true;
			returnFlag = false;
		}
		if(flatNo == null || flatNo == undefined || flatNo == ""){
			scope.addressInputFocusFlags.flatNo = true;
			returnFlag = false;
		}
		if(locality == null || locality == undefined || locality == ""){
			scope.addressInputFocusFlags.locality = true;
			returnFlag = false;
		}
		return returnFlag;
	}
	
	// Locality search Auto Complete
	$(function() {	 
		$( "#addressLocalitySelectInputTextBox" ).catcomplete({
			source: "locality/localitySuggestion.do",
			select: function( event, ui ) {
				scope.selectedAddress.locality = ui.item;
				scope.$apply();
			},
			close: function( event, ui ) {
				//Clean the value of text box after close the auto complete widget
				//$("#addressLocalitySelectInputTextBox")[0].value="";
			},change: function(event,ui){
				//Make autocomplete widget to mandatory selectable
				if(ui.item == null){
					$(this).val("");
					scope.selectedAddress.locality = "";
					scope.$apply();
				}else{
					scope.selectedAddress.locality = ui.item;
				}
			}
		});
	});
	
}

</script>

<style>
.button{
	background: #FCC4AB;
	font-size: 14px;
	color: white;
	border: 0px;
	padding: 5px;
}
.buttonHover{
	background: #f75b16;
	cursor: pointer;
}
.buttonHover2{
	background: #f75b16;
	cursor: pointer;
}

.button2{
	width: 120px;
	background: #175ed4;
	font-size: 14px;
	color: white;
	border: 0px;
	padding: 5px;
	cursor: pointer;
	border-radius: 3px;
}
</style>
<!-- Following is style for Add Address -->
<style>
.locality-input-box{
	border: 2px solid aqua;
	padding-left: 5px;
	height: 30px;
}

.address-overlay{
	height: 100%;
	width: 100%;
	position: fixed;
	background-color: rgba(0, 0, 0, 0.90);
	z-index: 11;
	display: block;
	top: 0px;
	left: 0px;
	display: none;
}
.address-overlay .address-div{
	width: 500px;
	top: 100px;
	position: relative;
	border: 10px solid;
	border-color: rgba(0, 0, 0, 0.20);
}
.address-overlay .address-div .address-main-content-div{
	background-color: white;
	width: 100%;
	height: 100%;
	padding-bottom: 10px;
	font-family: arial, san-sarif;
}

.addressDialogInnerContent{
}
.addressDialogInnerContent .newAddressHeader{
	padding-bottom: 10px;
	border-bottom: 1px solid;
}
.addressDialogInnerContent .newAddressBody{
	margin-top: 10px;
}
.addressDialogInnerContent input{
	height: 25px;
	padding-left: 5px;
}
.addressDialogInnerContent textarea{
	height: 50px;
	width: 300px;
}
.newAddressTable{
	font-size: 14px;
}
.newAddressTable tr{
	height: 40px;
}
.addressDialogInnerContent .error{
	font-size: 11px;
	color: red;
	padding-left: 5px;
	display: block;
}
.addressDialogInnerContent .errorBox{
	border: 1px solid red;
	background: antiquewhite;
}

.addressDialogInnerContent .addAddressButtonDiv{
	padding-top: 10px;
	padding-bottom: 10px;
}
.hidden{
	display: none;
}
.addAddressButton{
	font-size: 14px;
	background: #f75b16;
	color: white;
	border: 0px;
	padding: 2px;
	cursor: pointer;
	border: 2px solid transparent;
}
.addAddressButtonHover{
	border: 2px solid rgb(253, 146, 146);
}

</style>

<!-- Following is style for Address List -->

<style>
.paymentAddressContent{
	font-family: arial, san-sarif;
	display: none;
}
.paymentAddressContent .addressListOuterDiv{
	padding-bottom: 10px;
	padding-left: 30px;
	padding-right: 30px;
}
.paymentAddressContent .addressListOuterDiv .addressInfoMessage{
	color: rgb(116, 116, 116);
	font-family: arial, sans-serif;
	font-size: 14px;
	margin: 5px;
}
.paymentAddressContent .addressListOuterDiv .addressInfoMessage span{
	background-color: rgb(252, 242, 242);
	padding-left: 5px;
	padding-right: 5px;
	border: 1px solid rgb(116, 116, 116);
}
.paymentAddressContent .addressListOuterDiv .addressDetailOuterDiv{
	width: 250px;
	min-height: 220px;
	border: 2px solid antiquewhite;
	background: rgb(253, 250, 246);
	margin: 5px;
	float: left;
	font-family: arial, san-sarif;
	font-size: 14px;
}
.paymentAddressContent .addressListOuterDiv .addressDetailOuterDivHover{
	border: 2px solid orange;
	background: white;
}
.paymentAddressContent .addressListOuterDiv .addressDetailOuterDivDefaultAddress{
	border-top: 2px solid orange;
	background: white;
}
.paymentAddressContent .addressListOuterDiv .addressDetailOuterDiv .editDelete{
	height: 20px;
}

.paymentAddressContent .addressListOuterDiv .addressDetailOuterDiv .editDelete .deleteAddressIcon{
	background-image: url("resources/image/icons2.png");
	background-repeat: no-repeat;
	background-position: 2px -457px;
	display: inline-block;
	width: 20px;
	height: 19px;
	zoom: 1;
	vertical-align: middle;
	cursor: pointer;
}
.paymentAddressContent .addressListOuterDiv .addressDetailOuterDiv .editDelete .deleteAddressIconHover{
	background-position: 2px -439px;
}
.paymentAddressContent .addressListOuterDiv .addressDetailOuterDiv .editDelete .editAddressIcon{
	background-image: url("resources/image/icons2.png");
	background-repeat: no-repeat;
	background-position: 2px -476px;
	display: inline-block;
	width: 20px;
	height: 19px;
	zoom: 1;
	vertical-align: middle;
	cursor: pointer;
}
.paymentAddressContent .addressListOuterDiv .addressDetailOuterDiv .editDelete .editAddressIconHover{
	background-position: 2px -493px;
}

.paymentAddressContent .addressListOuterDiv .addressDetailOuterDiv .addressContent{
	margin-left: 20px;
	margin-right: 20px;
	cursor: pointer;
}
.paymentAddressContent .addressListOuterDiv .addressDetailOuterDiv .addressContent .name{
	font-size: 16px;
	border-bottom: 1px solid antiquewhite;
	padding-top: 5px;
	padding-bottom: 5px;
	min-height: 23px;
}
.paymentAddressContent .addressListOuterDiv .addressDetailOuterDiv .addressContent .nameHover{
	font-size: 20px;
}
.paymentAddressContent .addressListOuterDiv .addressDetailOuterDiv .addressContent .nameDefaultAddress{
	font-size: 20px;
}
.paymentAddressContent .addressListOuterDiv .addressDetailOuterDiv .addressContent .mobile{
	font-size: 16px;
	border-bottom: 1px solid antiquewhite;
	padding-top: 5px;
	padding-bottom: 5px;
}
.paymentAddressContent .addressListOuterDiv .addressDetailOuterDiv .addressContent .flatNo{
	padding-top: 5px;
}
.paymentAddressContent .addressListOuterDiv .addressDetailOuterDiv .addressContent .addressLocalityLabel{
	padding-bottom: 5px;
	border-bottom: 1px solid antiquewhite;
}


.paymentAddressContent .addressListOuterDiv .addressDetailOuterDiv .deliverHereButtonDiv{
	padding-top: 10px;
	padding-bottom: 10px;
}
.paymentAddressContent .addressListOuterDiv .addressDetailOuterDiv .deliverHereButton{
	font-size: 14px;
	background: #FCC4AB;
	color: white;
	border: 0px;
	padding: 2px;
	cursor: pointer;
	border: 2px solid transparent;
}
.paymentAddressContent .addressListOuterDiv .addressDetailOuterDiv .deliverHereButtonHover{
	background: #f75b16;
}
.paymentAddressContent .addressListOuterDiv .addressDetailOuterDiv .deliverHereButtonDefaultAddress{
	background: #f75b16;
}

.openNewAddressDialogButtonOuterDiv{
	border-top: 1px solid antiquewhite;
	padding-top: 10px;
	padding-bottom: 10px;
	background: rgb(253, 250, 246);
	cursor: pointer;
}
.openNewAddressDialogButtonOuterDivHover{
	background: white;
}
.openNewAddressDialogButtonOuterDivEmptyAddressList{
	background: white;
}

</style>
<!-- Following is code for Add Address -->
<div class="address-overlay" id="newAddressDialog" align="center">
	<div class="address-div">
		<div class="address-main-content-div">
			<div align="right"><span class="cross-icon" ng-click="closeAddressDialog()"></span></div>
			<div id="addressDialogInnerContent" class="addressDialogInnerContent">
				<div class="newAddressHeader">
					<span>New shipping Address</span>
				</div>
				<div class="newAddressBody">
					<table class="newAddressTable">
						<tbody>
							<tr>
								<td>Name</td>
								<td>
									<input type="text" name="addressUserName" maxlength="100" ng-model="selectedAddress.name" ng-blur="addressInputFocusFlags.name=true" ng-class="{errorBox: (addressInputFocusFlags.name==true && selectedAddress.name == '')}">
									<span class="hidden" ng-class="{error: (addressInputFocusFlags.name==true && selectedAddress.name == '')}" >{{addresserrorMsg.name}}</span>
								</td>
							</tr>
							<tr>
								<td>Mobile</td>
								<td>
									<input type="text" name="addressUserMobile" maxlength="10" ng-model="selectedAddress.mobile"  ng-blur="addressInputFocusFlags.mobile=true"  ng-class="{errorBox: (addressInputFocusFlags.mobile==true && (validateMobile() == false))}">
									<span class="hidden" ng-class="{error: (addressInputFocusFlags.mobile==true &&  (validateMobile() == false))}">{{addresserrorMsg.mobile}}</span>
								</td>
							</tr>
							<tr>
								<td>House/Flat no</td>
								<td>
									<input type="text" name="houseFlatNo" maxlength="100" ng-model="selectedAddress.flatNo"  ng-blur="addressInputFocusFlags.flatNo=true"  ng-class="{errorBox: (addressInputFocusFlags.flatNo==true && selectedAddress.flatNo == '')}">
									<span class="hidden" ng-class="{error: (addressInputFocusFlags.flatNo==true && selectedAddress.flatNo == '')}">{{addresserrorMsg.flatNo}}</span>
								</td>
							</tr>
							<tr>
								<td>Locality/Area/Society</td>
								<td>
									<textarea name="locality" id="addressLocalitySelectInputTextBox" placeholder="Type your society/colony/RWA name" rows="4" cols="50" class="locality-input-box" ng-model="selectedAddress.locality.label" ng-blur="addressInputFocusFlags.locality=true" ng-class="{errorBox: (addressInputFocusFlags.locality==true && selectedAddress.locality == '')}"></textarea>
									<br>
									<span class="hidden" ng-class="{error: (addressInputFocusFlags.locality==true && selectedAddress.locality == '')}">{{addresserrorMsg.locality}}</span>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="addAddressButtonDiv">
					<input type="button" class="addAddressButton" style="height: 26px;" name="Add Address" value="{{selectedAddress.buttonLabel}}" ng-click="addEditAddress()" ng-class="{addAddressButtonHover: hover1}" ng-mouseenter="hover1 = true" ng-mouseleave="hover1 = false">
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Following is code for Address List -->
<div class="paymentSectionsOuterDiv" align="center">
	<div class="paymentSectionsHeader" align="left" ng-click="openSectionId = 'address' ; slideDownAddressList();">
		<table>
			<tr>
				<td style="width: 160px;">Shipping Address</td>
				<td style="width: 600px;">
					<div class="addressHeaderNameAddressDiv" ng-show="openSectionId != 'address'">
						<div class="addressHeaderNameMobileDiv">{{defaultAddress.name}} <span class="mobileSpan">{{defaultAddress.mobile}}</span></div>
						<div class="addressHeaderAddressDiv"> <span class="flatNo">{{defaultAddress.flatNo}}</span>, {{defaultAddress.localityLabel}}</div>
					</div>
				</td>
				<td style="width: 150px;" ng-show="openSectionId != 'address'">
					<div>
						<input type="button" class="button2" value="Change Address" name="logoutButton">
					</div>
				</td>
			</tr>
		</table>
	</div>
		
	<div id="paymentAddressContent" class="paymentAddressContent">
		<div class="addressListOuterDiv">
			<table>
				<tr>
					<td>
						<div align="center" ng-show="addressInfoMessage" class="addressInfoMessage"><span>{{addressInfoMessage}}</span></div>
						<div align="center" ng-show="userAddressList.length == 0" class="addressInfoMessage"><span>{{addressNoAddressMessage}}</span></div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="addressDetailOuterDiv" ng-repeat="address in userAddressList" ng-class="{addressDetailOuterDivHover: hover, addressDetailOuterDivDefaultAddress:(address==defaultAddress)}" ng-mouseenter="hover = true" ng-mouseleave="hover = false">
							<div class="editDelete" align="right">
								<span class="deleteAddressIcon" title="Delete" ng-click="deleteAddress(address)" ng-class="{deleteAddressIconHover: deleteAddressIconHoverFlag}" ng-mouseenter="deleteAddressIconHoverFlag = true; hover = false" ng-mouseleave="deleteAddressIconHoverFlag = false; hover = true"></span>
								<span class="editAddressIcon" title="Edit" ng-click="openAddressDialog('edit',address)"  ng-class="{editAddressIconHover: editAddressIconHoverFlag}" ng-mouseenter="editAddressIconHoverFlag = true; hover = false" ng-mouseleave="editAddressIconHoverFlag = false; hover = true"></span>
							</div>
							<div class="addressContent" ng-click="deliverHereOrContinue(address)">
								<div class="name" ng-class="{nameHover: hover, nameDefaultAddress:(address==defaultAddress)}">{{address.name}}</div>
								<div class="mobile">{{address.mobile}}</div>
								<div class="flatNo">{{address.flatNo}}</div>
								<div class="addressLocalityLabel">{{address.localityLabel}}</div>
								<div class="deliverHereButtonDiv" align="center">
									<input type="button" class="deliverHereButton" value="{{(address==defaultAddress)?'Continue':'Deliver Here'}}" ng-class="{deliverHereButtonHover: hover, deliverHereButtonDefaultAddress:(address==defaultAddress)}" >
								</div>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div class="openNewAddressDialogButtonOuterDiv" ng-click="openAddressDialog('add','')" ng-class="{openNewAddressDialogButtonOuterDivHover: addressFooterHover, openNewAddressDialogButtonOuterDivEmptyAddressList: userAddressList.length == 0}" ng-mouseenter="addressFooterHover = true" ng-mouseleave="addressFooterHover = false">
			<div>
				<input type="button" class="button" value="Add New Address" name="openNewAddressDialog" ng-class="{buttonHover: addressFooterHover, buttonHover2: userAddressList.length == 0}" >
			</div>
		</div>
	</div>
</div>
	