<%@ include file="accountOuterTop.jsp" %>

<script type="text/javascript">

function initSubscription(){
	var scope = angular.element(document.querySelector('[ng-controller=mainCtrl]')).scope();
	scope.mySubscriptions = 
	[
		{
			"img100x100":"http://localhost:8080/mk/resources/image/amul_curd1_image_1_100_100.jpg",
			"varientName":"Amul Masti Dahi 200 G",
			"brand" : "Amul",
			"subId" : "SB000162845878",
			"address": {
				"name": "vivek Kumar Verma",
				"mobile": "1234567890",
				"flatNo" : "SRA 38 C",
				"localityShortName" : "shipra riviera",
				"localityName" : "Shipra Krishna Vista, Ahinsa Khand 1, Indirapuram, Ghaziabad, Uttar Pradesh, Pin 201010"
			},
			"startDate":"10 Apr 2015",
			"endDate":"10 Apr 2015",
			"daySelection": {
				"value":1,
				"qty": "1 Packet",
				"mrp": "25",
				"price":"24"
			},
			"varientURL":"http://localhost:8080/mk/pView?q=P1004V1"
		},
		{
			"img100x100":"http://localhost:8080/mk/resources/image/amul_curd1_image_1_100_100.jpg",
			"varientName":"Amul Masti Dahi 200 G",
			"brand" : "Amul",
			"subId" : "SB000162845834",
			"address": {
				"name": "vivek Kumar Verma",
				"mobile": "1234567890",
				"flatNo" : "SRA 38 C",
				"localityShortName" : "shipra riviera",
				"localityName" : "Shipra Krishna Vista, Ahinsa Khand 1, Indirapuram, Ghaziabad, Uttar Pradesh, Pin 201010"
			},
			"startDate":"10 Apr 2015",
			"daySelection": {
				"value": 3,
				"qty": {
					"mon":{"q":"1 Packet","mrp":"25", "price":"24"},
					"tue":{"q":"1 Packet","mrp":"25", "price":"24"},
					"wed":{"q":"1 Packet","mrp":"25", "price":"24"},
					"thu":{"q":"1 Packet","mrp":"25", "price":"24"},
					"fri":{"q":"1 Packet","mrp":"25", "price":"24"},
					"sat":{"q":"2 Packet","mrp":"50", "price":"48"},
					"sun":{"q":"2 Packet","mrp":"50", "price":"48"}
				}
			},
			"varientURL":"http://localhost:8080/mk/pView?q=P1004V1"
		},
		{
			"img100x100":"http://localhost:8080/mk/resources/image/amul_curd1_image_1_100_100.jpg",
			"varientName":"Amul Masti Dahi 200 G",
			"brand" : "Amul",
			"subId" : "SB000162845834",
			"address": {
				"name": "vivek Kumar Verma",
				"mobile": "1234567890",
				"flatNo" : "SRA 38 C",
				"localityShortName" : "shipra riviera",
				"localityName" : "Shipra Krishna Vista, Ahinsa Khand 1, Indirapuram, Ghaziabad, Uttar Pradesh, Pin 201010"
			},
			"startDate":"10 Apr 2015",
			"daySelection": {
				"value":1,
				"qty": "2 Packet",
				"mrp": "50",
				"price":"48"
			},
			"varientURL":"http://localhost:8080/mk/pView?q=P1004V1",
			"effectiveFrom":{
				"date":"25 May 2015",
				"address": {
					"name": "vivek Kumar Verma",
					"mobile": "1234567890",
					"flatNo" : "SRA 38 C",
					"localityShortName" : "shipra riviera",
					"localityName" : "Shipra Krishna Vista, Ahinsa Khand 1, Indirapuram, Ghaziabad, Uttar Pradesh, Pin 201010"
				},
				"daySelection": {
					"value": 3,
					"qty": {
						"mon":{"q":"1 Packet","mrp":"25", "price":"24"},
						"tue":{"q":"1 Packet","mrp":"25", "price":"24"},
						"wed":{"q":"1 Packet","mrp":"25", "price":"24"},
						"thu":{"q":"1 Packet","mrp":"25", "price":"24"},
						"fri":{"q":"1 Packet","mrp":"25", "price":"24"},
						"sat":{"q":"2 Packet","mrp":"50", "price":"48"},
						"sun":{"q":"2 Packet","mrp":"50", "price":"48"}
					}
				}
				
			}
		}
];
	scope.showUpcomingOrders = function(id){
		$('#upcomingOtderOuterDivId'+id).slideToggle('fast');
	}

	scope.toggleSub = function(id){
		$('#subBodyId'+id).slideToggle('fast');
		$('#subFooterId'+id).slideToggle('fast');
		$('#subHeadProdNameId'+id).toggle();
	}
	scope.$apply();
}
$('#mySubscriptionMainContentDiv').ready(function() {
	initSubscription();
});
</script>
<style>
.accountMainOuterPage .accountOuterTable .rightTd .mySubscriptionMainContentDiv{
}
.font-default{
	font-family: arial, san-serif;
	font-size: 13px;
}
</style>
<style>
.s_outer {
	display: block;
}
.s_outer .s_row{
	border: solid 1px #ccc;
	-webkit-box-shadow: 0 0 4px #d3cbb8;
	-moz-box-shadow: 0 0 4px #d3cbb8;
	box-shadow: 0 0 4px #d3cbb8;
	margin: 0 4px 20px 4px;
	background-color: #fff;
}
.s_outer .s_row_hover{
	border: 1px solid blue;
}
.s_outer .s_row .s_hed{
	background-color: #f9f9f9;
	border-bottom: solid 1px #e6e6e6;
	padding: 12px 15px;
}
.s_outer .s_row .s_hed table{
	width: 100%;
	cursor: pointer;
}
.s_outer .s_row .s_hed table .sub_id_td{
	width: 250px;
}
.s_outer .s_row .s_hed table .stop_edit_td{
	width: 50px;
}
.s_outer .s_row .s_body{
	margin: 0 15px;
	padding: 10px 0;
	border-bottom: solid 1px #ccc;
	display: none;
}
.s_outer .s_row .s_body .s_b_inner{
	overflow: hidden;
	margin: 0;
	padding: 0;
}
.s_outer .s_row .s_body .s_b_inner .prod_img{
	float: left;
	width: 100px;
}
.prod_img .item-image{
	max-width: 80px;
	width: auto!important;
	width: 80px;
}
.s_outer .s_row .s_body .s_b_inner .prod_detail{
	float: left;
	min-height: 100px;
	width: 200px;
}
.s_outer .s_row .s_body .s_b_inner .subs_dates{
	float: left;
	min-height: 100px;
	width: 100px;
}
.s_outer .s_row .s_body .s_b_inner .subs_dates .start_date{
	margin-top: 5px;
}
.s_outer .s_row .s_body .s_b_inner .subs_dates .end_date{
	margin-top: 10px;
}
.s_outer .s_row .s_body .s_b_inner .qty_day_addr{
	float:left;
	width: 300px;
}
.qty_day_addr_table{
	width: 100%;
}
.s_outer .s_row .s_body .s_b_inner .qty_day_addr .qty_day{
	width: 150px;
}
.s_outer .s_row .s_body .s_b_inner .qty_day_addr .addr{
	
}
.addr .addr_inner{
}
.s_outer .s_row .s_mid{
	margin: 5px 0px;
	border-bottom: solid 1px #ccc;
}
.s_outer .s_row .s_foot{
	margin: 10px 15px;
	cursor: pointer;
	color: #3D3D3D;
	display: none;
}
.s_outer .s_row .s_foot_hover{
	background: #f9f9f9;
	color: black;
}
a{
	color: #666;
	text-decoration: none;
	cursor: pointer;
}
.small_text{
	color: #848484;
	font-size: 10px;
}
.medium_text{
	color: #848484;
	font-size: 12px;
}

.upcm_odr_div{
	height: 200px;
	display: none;
}
.stopAndEdit{
	height: 20px;
}
.s_outer .mrp{
	text-decoration: line-through;
}
.s_outer .selling_price{
	color: rgb(87, 87, 87);
	font-weight: bold;
}
.stopAndEdit .stopSubscriptionIcon{
	background-image: url("../resources/image/icons2.png");
	background-repeat: no-repeat;
	background-position: 2px -457px;
	display: inline-block;
	width: 20px;
	height: 19px;
	zoom: 1;
	vertical-align: middle;
	cursor: pointer;
}
.stopAndEdit .stopSubscriptionIconHover{
	background-position: 2px -439px;
}
.stopAndEdit .editSubscriptionIcon{
	background-image: url("../resources/image/icons2.png");
	background-repeat: no-repeat;
	background-position: 2px -476px;
	display: inline-block;
	width: 20px;
	height: 19px;
	zoom: 1;
	vertical-align: middle;
	cursor: pointer;
}
.stopAndEdit .editSubscriptionIconHover{
	background-position: 2px -493px;
}

</style>

<div class="s_outer font-default">
	<div 
		ng-repeat="subscription in mySubscriptions" 
		class="s_row" 
		ng-class="{s_row_hover: hover}" 
		ng-mouseleave="hover=false" 
		ng-mouseenter="hover=true" >
		
		<div class="s_hed">
			<table>
				<tr>
					<td class="sub_id_td" ng-click="toggleSub($index)">
						<div class="medium_text">
							<span>Subscription ID : </span>
							<span ng-bind="subscription.subId"></span>&nbsp;
						</div>
					</td>
					<td ng-click="toggleSub($index)">
						<div align="left" id="subHeadProdNameId{{$index}}"><span class="small_text" ng-bind="subscription.varientName"></span></div>
					</td>
					<td class="stop_edit_td">
						<div class="stopAndEdit" align="right">
							<span class="stopSubscriptionIcon" title="Close Subscription" ng-click="" ng-class="{stopSubscriptionIconHover: stopSubscriptionIconHoverFlag}" ng-mouseenter="stopSubscriptionIconHoverFlag = true; hover = false" ng-mouseleave="stopSubscriptionIconHoverFlag = false; hover = true"></span>
							<span class="editSubscriptionIcon" title="Edit Subscription" ng-click="clickOnAddSubscription('P1004V1', '1')"  ng-class="{editSubscriptionIconHover: editSubscriptionIconHoverFlag}" ng-mouseenter="editSubscriptionIconHoverFlag = true; hover = false" ng-mouseleave="editSubscriptionIconHoverFlag = false; hover = true"></span>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div class="s_body" id="subBodyId{{$index}}">
			<div class="s_b_inner">
				<div class="prod_img">
					<a href="{{subscription.varientURL}}" target="_blank">
						<img class="item-image" alt="" ng-src="{{subscription.img100x100}}">
					</a>
				</div>
				<div class="prod_detail">
					<a href="{{subscription.varientURL}}" target="_blank" ng-bind="subscription.varientName"></a>
				</div>
				<div class="subs_dates">
					<div class="small_text start_date">Start Date</div>
					<div class="small_text" ng-bind="subscription.startDate"></div>
					<div class="small_text end_date" ng-show="subscription.endDate">End Date</div>
					<div class="small_text" ng-show="subscription.endDate" ng-bind="subscription.endDate"></div>
				</div>
				<div class="qty_day_addr">
					<table class="qty_day_addr_table">
						<tr valign="top">
							<td class="qty_day">
								<div class="small_text" ng-show="subscription.daySelection.value == '1'">
								<span ng-bind="subscription.daySelection.qty"></span> Daily (&#8377;
								<span class="selling_price" ng-bind="subscription.daySelection.price"></span>
								<span class="mrp small_text" ng-bind="subscription.daySelection.mrp"></span>
								)
								</div>
								<div class="medium_text" ng-show="subscription.daySelection.value == '2'">
								<span ng-bind="subscription.daySelection.qty"></span> Alternate (&#8377;
								<span class="selling_price" ng-bind="subscription.daySelection.price"></span>
								<span class="mrp small_text" ng-bind="subscription.daySelection.mrp"></span>
								)
								</div>
								<div class="small_text" ng-show="subscription.daySelection.value == '3'">
									<table>
									<tr ng-show="subscription.daySelection.qty.mon"> <td>MON</td><td>{{subscription.daySelection.qty.mon.q}} (&#8377; <span class="selling_price" ng-bind="subscription.daySelection.qty.mon.price"></span> <span class="mrp small_text" ng-bind="subscription.daySelection.qty.mon.mrp"></span>)</td></tr>
									<tr ng-show="subscription.daySelection.qty.tue"> <td>TUE</td><td>{{subscription.daySelection.qty.tue.q}} (&#8377; <span class="selling_price" ng-bind="subscription.daySelection.qty.tue.price"></span> <span class="mrp small_text" ng-bind="subscription.daySelection.qty.tue.mrp"></span>)</td></tr>
									<tr ng-show="subscription.daySelection.qty.wed"> <td>WED</td><td>{{subscription.daySelection.qty.wed.q}} (&#8377; <span class="selling_price" ng-bind="subscription.daySelection.qty.wed.price"></span> <span class="mrp small_text" ng-bind="subscription.daySelection.qty.wed.mrp"></span>)</td></tr>
									<tr ng-show="subscription.daySelection.qty.thu"> <td>THU</td><td>{{subscription.daySelection.qty.thu.q}} (&#8377; <span class="selling_price" ng-bind="subscription.daySelection.qty.thu.price"></span> <span class="mrp small_text" ng-bind="subscription.daySelection.qty.thu.mrp"></span>)</td></tr>
									<tr ng-show="subscription.daySelection.qty.fri"> <td>FRI</td><td>{{subscription.daySelection.qty.fri.q}} (&#8377; <span class="selling_price" ng-bind="subscription.daySelection.qty.fri.price"></span> <span class="mrp small_text" ng-bind="subscription.daySelection.qty.fri.mrp"></span>)</td></tr>
									<tr ng-show="subscription.daySelection.qty.sat"> <td>SAT</td><td>{{subscription.daySelection.qty.sat.q}} (&#8377; <span class="selling_price" ng-bind="subscription.daySelection.qty.sat.price"></span> <span class="mrp small_text" ng-bind="subscription.daySelection.qty.sat.mrp"></span>)</td></tr>
									<tr ng-show="subscription.daySelection.qty.sun"> <td>SUN</td><td>{{subscription.daySelection.qty.sun.q}} (&#8377; <span class="selling_price" ng-bind="subscription.daySelection.qty.sun.price"></span> <span class="mrp small_text" ng-bind="subscription.daySelection.qty.sun.mrp"></span>)</td></tr>
									</table>
								</div>
							</td>
							<td class="addr">
								<div class="addr_inner medium_text">
									<div>{{subscription.address.name}}</div>
									<div>{{subscription.address.mobile}}</div>
									<div>{{subscription.address.flatNo}}</div>
									<div>{{subscription.address.localityName}}</div>
								</div>
							</td>
						</tr>
						<tr ng-show="subscription.effectiveFrom" align="center">
							<td colspan="2">
								<div class="s_mid">
								</div>
								<div>Following is effective From Date {{subscription.effectiveFrom.date}}</div>
							</td>
						</tr>
						<tr valign="top" ng-show="subscription.effectiveFrom">
							<td class="qty_day">
								<div class="small_text" ng-show="subscription.effectiveFrom.daySelection.value == '1'">{{subscription.effectiveFrom.daySelection.qty}} Daily</div>
								<div class="small_text" ng-show="subscription.effectiveFrom.daySelection.value == '2'">{{subscription.effectiveFrom.daySelection.qty}} Alternate</div>
								<div class="small_text" ng-show="subscription.effectiveFrom.daySelection.value == '3'">
									<table>
									<tr ng-show="subscription.effectiveFrom.daySelection.qty.mon"> <td>MON</td><td>{{subscription.effectiveFrom.daySelection.qty.mon.q}} (&#8377; <span class="selling_price" ng-bind="subscription.effectiveFrom.daySelection.qty.mon.price"></span> <span class="mrp small_text" ng-bind="subscription.effectiveFrom.daySelection.qty.mon.mrp"></span>)</td></tr>
									<tr ng-show="subscription.effectiveFrom.daySelection.qty.tue"> <td>TUE</td><td>{{subscription.effectiveFrom.daySelection.qty.tue.q}} (&#8377; <span class="selling_price" ng-bind="subscription.effectiveFrom.daySelection.qty.tue.price"></span> <span class="mrp small_text" ng-bind="subscription.effectiveFrom.daySelection.qty.tue.mrp"></span>)</td></tr>
									<tr ng-show="subscription.effectiveFrom.daySelection.qty.wed"> <td>WED</td><td>{{subscription.effectiveFrom.daySelection.qty.wed.q}} (&#8377; <span class="selling_price" ng-bind="subscription.effectiveFrom.daySelection.qty.wed.price"></span> <span class="mrp small_text" ng-bind="subscription.effectiveFrom.daySelection.qty.wed.mrp"></span>)</td></tr>
									<tr ng-show="subscription.effectiveFrom.daySelection.qty.thu"> <td>THU</td><td>{{subscription.effectiveFrom.daySelection.qty.thu.q}} (&#8377; <span class="selling_price" ng-bind="subscription.effectiveFrom.daySelection.qty.thu.price"></span> <span class="mrp small_text" ng-bind="subscription.effectiveFrom.daySelection.qty.thu.mrp"></span>)</td></tr>
									<tr ng-show="subscription.effectiveFrom.daySelection.qty.fri"> <td>FRI</td><td>{{subscription.effectiveFrom.daySelection.qty.fri.q}} (&#8377; <span class="selling_price" ng-bind="subscription.effectiveFrom.daySelection.qty.fri.price"></span> <span class="mrp small_text" ng-bind="subscription.effectiveFrom.daySelection.qty.fri.mrp"></span>)</td></tr>
									<tr ng-show="subscription.effectiveFrom.daySelection.qty.sat"> <td>SAT</td><td>{{subscription.effectiveFrom.daySelection.qty.sat.q}} (&#8377; <span class="selling_price" ng-bind="subscription.effectiveFrom.daySelection.qty.sat.price"></span> <span class="mrp small_text" ng-bind="subscription.effectiveFrom.daySelection.qty.sat.mrp"></span>)</td></tr>
									<tr ng-show="subscription.effectiveFrom.daySelection.qty.sun"> <td>SUN</td><td>{{subscription.effectiveFrom.daySelection.qty.sun.q}} (&#8377; <span class="selling_price" ng-bind="subscription.effectiveFrom.daySelection.qty.sun.price"></span> <span class="mrp small_text" ng-bind="subscription.effectiveFrom.daySelection.qty.sun.mrp"></span>)</td></tr>
									</table>
								</div>
							</td>
							<td class="addr">
								<div class="addr_inner medium_text">
									<div>{{subscription.effectiveFrom.address.name}}</div>
									<div>{{subscription.effectiveFrom.address.mobile}}</div>
									<div>{{subscription.effectiveFrom.address.flatNo}}</div>
									<div>{{subscription.effectiveFrom.address.localityName}}</div>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div class="s_foot" id="subFooterId{{$index}}" ng-click="showUpcomingOrders($index)"
			ng-class="{s_foot_hover: foothover}" 
			ng-mouseleave="foothover=false" 
			ng-mouseenter="foothover=true" 
			>
			Upcoming orders based on this subscription >>>
		</div>
		<div class="upcm_odr_div" id="upcomingOtderOuterDivId{{$index}}">
		
		</div>
	</div>
	
</div>

<%@ include file="accountOuterBottom.jsp" %>
