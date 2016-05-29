<table class="homePageMainContentTable">
	<tr>
		<td class="homaePageLeftTd" valign="top">
			<div class="homePageTopBanner" ng-show="homePageTopBanner">
				<img alt="" ng-click="goToUrl(homePageTopBanner.bannerUrl)" ng-src="{{homePageTopBanner.bannerImage}}">
			</div>
			<div class="homePageSliderOuterDiv" ng-show="homePageSlider.length > 0">
				<div class="sliderBannerContent">
					<a ng-href="<%=request.getContextPath()%>{{homePageSlider[selectedSliderMenuIndex].bannerUrl}}">
					<img alt="" id="sliderBannerContentBottomImg" ng-src="{{homePageSlider[3].bannerImage}}">
					<img alt="" id="sliderBannerContentTopImg" ng-src="{{homePageSlider[0].bannerImage}}">
					</a>
				</div>
				<div class="sliderMenuDiv">
					<table class="sliderMenuTable">
						<tr>
							<td ng-click="goToUrl(sliderItem.bannerUrl)" align="center" ng-repeat="sliderItem in homePageSlider" class="td"  ng-class="{tdHover: selectedSliderMenuIndex == $index}" ng-mouseenter="$parent.selectedSliderMenuIndex = $index; $parent.sliderAutorun = false; fededOutSliderContent()">
								<div class="menuItemTop">{{sliderItem.titleTop}}</div>
								<div class="menuItemBottom">{{sliderItem.titleBottom}}</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="homePageLeftPanelThreeItemDiv" ng-show="homePageLeftPanelThird">
				<table class="outerTable">
					<thead ng-show="homePageLeftPanelThird.title">
						<tr>
							<td colspan="3" class="outerTableHeaderTd" >{{homePageLeftPanelThird.title}}</td>
						</tr>
					</thead>
					<tr>
						<td class="td" align="{{$index == 0 ? 'left': ( ($index == 1) ? 'center' : 'right' )}}" ng-repeat="panelItem in homePageLeftPanelThird.items">
							<div ng-click="goToUrl(panelItem.bannerUrl)" class="tdDiv"  ng-class="{tdDivHover: homePageLeftPanelThirdHover}" ng-mouseenter="homePageLeftPanelThirdHover = true" ng-mouseleave="homePageLeftPanelThirdHover = false">
								<table style="height: 100%;"><tr><td><img alt="" ng-src="{{panelItem.bannerImage}}"> </td></tr></table>
							</div>
						</td>
					<tr>
				</table>
			</div>
			<div class="homePageLeftPanelSingleItemDiv" ng-show="homePageLeftPanelsingleBanner" ng-class="{homePageLeftPanelSingleItemDivHover: homePageLeftPanelSingleHover}" ng-mouseenter="homePageLeftPanelSingleHover = true" ng-mouseleave="homePageLeftPanelSingleHover = false">
				<img alt="" ng-click="goToUrl(homePageLeftPanelsingleBanner.bannerUrl)" ng-src="{{homePageLeftPanelsingleBanner.bannerImage}}">
			</div>
			<div class="homePageLeftPanelThreeItemDiv" ng-show="homePageLeftPanelThreeItem2">
				<table class="outerTable">
					<thead ng-show="homePageLeftPanelThreeItem2.title">
						<tr>
							<td colspan="3" class="outerTableHeaderTd" >{{homePageLeftPanelThreeItem2.title}}</td>
						</tr>
					</thead>
					<tr>
						<td class="td" align="{{$index == 0 ? 'left': ( ($index == 1) ? 'center' : 'right' )}}" ng-repeat="panelItem in homePageLeftPanelThreeItem2.items">
							<div ng-click="goToUrl(panelItem.bannerUrl)" class="tdDiv"  ng-class="{tdDivHover: homePageLeftPanelThirdHover}" ng-mouseenter="homePageLeftPanelThirdHover = true" ng-mouseleave="homePageLeftPanelThirdHover = false">
								<table style="height: 100%;"><tr><td><img alt="" ng-src="{{panelItem.bannerImage}}"> </td></tr></table>
							</div>
						</td>
					<tr>
				</table>
			</div>
		</td>
		<td class="homePageRightTd" valign="top">
			<div class="rightPanelItemDiv" ng-repeat="rightPanelItem in homePageRightPanelItems" ng-class="{rightPanelItemDivHover:leftItemDivHover}" ng-mouseenter="leftItemDivHover = true" ng-mouseleave="leftItemDivHover = false">
				<img alt="" ng-click="goToUrl(rightPanelItem.bannerUrl)" ng-src="{{rightPanelItem.bannerImage}}">
			</div>
		</td>
	</tr>
</table>