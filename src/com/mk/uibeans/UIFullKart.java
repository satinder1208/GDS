package com.mk.uibeans;

import java.util.ArrayList;
import java.util.List;

public class UIFullKart extends UIKart {

List<UIKartItem> uIKartItems = new ArrayList<UIKartItem>();
	
	
	public UIFullKart(){}
	
	public UIFullKart(boolean success, String message,
			List<UIKartItem> uIKartItems, int noOfItem, String myCartMessage) {
		this.success = success;
		this.flashMessage = message;
		this.uIKartItems = uIKartItems;
		this.myCartMessage = myCartMessage;
	}

	

	public List<UIKartItem> getCartItems() {
		return uIKartItems;
	}

	public void setCartItems(List<UIKartItem> uIKartItems) {
		this.uIKartItems = uIKartItems;
	}

	public int getNoOfItem() {
		return uIKartItems == null ? 0 : uIKartItems.size();
	}


	

	public static UIKart getdummyData(){
		List<UIOffer> uIOffers = new ArrayList<UIOffer>();
		uIOffers.add(new UIOffer("offer001", "Quantity Specific 1st : One 10 gram dary milk free with 4 leter of Amul Gold Milk"));
		uIOffers.add(new UIOffer("offer002", "Quantity Specific 2nd : One 10 gram dary milk free with 4 leter of Amul Gold Milk"));
		List<UIQuantityPrice> quantityPriceList1 = new ArrayList<UIQuantityPrice>();
		quantityPriceList1.add(new UIQuantityPrice("0", "1 Leter", "48", "48",uIOffers));
		quantityPriceList1.add(new UIQuantityPrice("1", "2 Leter", "96", "95",null));
		quantityPriceList1.add(new UIQuantityPrice("2", "3 Leter", "144", "143.5",null));
		quantityPriceList1.add(new UIQuantityPrice("3", "4 Leter", "191", "190", uIOffers));
		uIOffers = new ArrayList<UIOffer>();
		uIOffers.add(new UIOffer("offer001", "Quantity Specific: One 5 gram dary milk free with 5 leter of Amul Gold Milk"));
		quantityPriceList1.add(new UIQuantityPrice("4", "5 Leter", "238", "236",null));
		uIOffers = new ArrayList<UIOffer>();
		uIOffers.add(new UIOffer("offer001", "Product Specific: One 5 gram dary milk free with 5 leter of Amul Gold Milk"));
		UIKartItem uIKartItem = new UIKartItem("resources/image/amul_milk_image_1_100_100.jpg", "variant001", "pView", "Amul Gold Full Cream Milk 1 leter", quantityPriceList1, "2", uIOffers, null);
		
		UIKartItem cartItem2 = new UIKartItem("resources/image/amul_milk_image_1_100_100.jpg", "variant001", "pView", "Amul Gold Full Cream Milk 1 leter", quantityPriceList1, "0", uIOffers, null);
		UIKartItem cartItem3 = new UIKartItem("resources/image/mother_image_1_100_100.jpg", "variant001", "pView", "Amul Gold Full Cream Milk 1 leter", quantityPriceList1, "1", uIOffers, null);
		UIKartItem cartItem4 = new UIKartItem("resources/image/amul_milk_image_1_100_100.jpg", "variant001", "pView", "Amul Gold Full Cream Milk 1 leter", quantityPriceList1, "2", uIOffers, null);
		UIKartItem cartItem5 = new UIKartItem("resources/image/mother_image_1_100_100.jpg", "variant001", "pView", "Amul Gold Full Cream Milk 1 leter", quantityPriceList1, "3", uIOffers, null);
		UIKartItem cartItem6 = new UIKartItem("resources/image/amul_milk_image_1_100_100.jpg", "variant001", "pView", "Amul Gold Full Cream Milk 1 leter", quantityPriceList1, "1", uIOffers, null);
		
		List<UIKartItem> uIKartItems = new ArrayList<UIKartItem>();
		
		uIKartItems.add(uIKartItem);
		uIKartItems.add(cartItem2);
		uIKartItems.add(cartItem3);
		uIKartItems.add(cartItem4);
		uIKartItems.add(cartItem5);
		uIKartItems.add(cartItem6);
		
		UIKart uIKart = new UIFullKart(true, "", uIKartItems, uIKartItems.size(),"");
		return uIKart;
	}
	public static UIKart getdummyData2(){
		List<UIOffer> uIOffers = new ArrayList<UIOffer>();
		uIOffers.add(new UIOffer("offer001", "Quantity Specific 1st : One 10 gram dary milk free with 4 leter of Amul Gold Milk"));
		uIOffers.add(new UIOffer("offer002", "Quantity Specific 2nd : One 10 gram dary milk free with 4 leter of Amul Gold Milk"));
		List<UIQuantityPrice> quantityPriceList1 = new ArrayList<UIQuantityPrice>();
		quantityPriceList1.add(new UIQuantityPrice("0", "1 Leter", "48", "48",uIOffers));
		quantityPriceList1.add(new UIQuantityPrice("1", "2 Leter", "96", "95",null));
		quantityPriceList1.add(new UIQuantityPrice("2", "3 Leter", "144", "143.5",null));
		quantityPriceList1.add(new UIQuantityPrice("3", "4 Leter", "191", "190", uIOffers));
		uIOffers = new ArrayList<UIOffer>();
		uIOffers.add(new UIOffer("offer001", "Quantity Specific: One 5 gram dary milk free with 5 leter of Amul Gold Milk"));
		quantityPriceList1.add(new UIQuantityPrice("4", "5 Leter", "238", "236",null));
		uIOffers = new ArrayList<UIOffer>();
		uIOffers.add(new UIOffer("offer001", "Product Specific: One 5 gram dary milk free with 5 leter of Amul Gold Milk"));
		UIKartItem uIKartItem = new UIKartItem("resources/image/amul_milk_image_1_100_100.jpg", "variant001", "pView", "Amul Gold Full Cream Milk 1 leter", quantityPriceList1, "2", uIOffers, null);
		
		UIKartItem cartItem2 = new UIKartItem("resources/image/amul_milk_image_1_100_100.jpg", "variant001", "pView", "Amul Gold Full Cream Milk 1 leter", quantityPriceList1, "0", uIOffers, null);
		UIKartItem cartItem3 = new UIKartItem("resources/image/mother_image_1_100_100.jpg", "variant001", "pView", "Amul Gold Full Cream Milk 1 leter", quantityPriceList1, "1", uIOffers, null);
		UIKartItem cartItem4 = new UIKartItem("resources/image/amul_milk_image_1_100_100.jpg", "variant001", "pView", "Amul Gold Full Cream Milk 1 leter", quantityPriceList1, "2", uIOffers, null);
		
		List<UIKartItem> uIKartItems = new ArrayList<UIKartItem>();
		
		uIKartItems.add(cartItem2);
		uIKartItems.add(cartItem4);
		
		UIKart uIKart = new UIFullKart(true, "", uIKartItems, uIKartItems.size(),"");
		return uIKart;
	}
	public static UIKart getdummyData3(){
		List<UIOffer> uIOffers = new ArrayList<UIOffer>();
		uIOffers.add(new UIOffer("offer001", "Quantity Specific 1st : One 10 gram dary milk free with 4 leter of Amul Gold Milk"));
		uIOffers.add(new UIOffer("offer002", "Quantity Specific 2nd : One 10 gram dary milk free with 4 leter of Amul Gold Milk"));
		List<UIQuantityPrice> quantityPriceList1 = new ArrayList<UIQuantityPrice>();
		quantityPriceList1.add(new UIQuantityPrice("0", "1 Leter", "48", "48",uIOffers));
		quantityPriceList1.add(new UIQuantityPrice("1", "2 Leter", "96", "95",null));
		quantityPriceList1.add(new UIQuantityPrice("2", "3 Leter", "144", "143.5",null));
		quantityPriceList1.add(new UIQuantityPrice("3", "4 Leter", "191", "190", uIOffers));
		uIOffers = new ArrayList<UIOffer>();
		uIOffers.add(new UIOffer("offer001", "Quantity Specific: One 5 gram dary milk free with 5 leter of Amul Gold Milk"));
		quantityPriceList1.add(new UIQuantityPrice("4", "5 Leter", "238", "236",null));
		uIOffers = new ArrayList<UIOffer>();
		uIOffers.add(new UIOffer("offer001", "Product Specific: One 5 gram dary milk free with 5 leter of Amul Gold Milk"));
		UIKartItem uIKartItem = new UIKartItem("resources/image/amul_milk_image_1_100_100.jpg", "variant001", "pView", "Amul Gold Full Cream Milk 1 leter", quantityPriceList1, "2", uIOffers, null);
		
		UIKartItem cartItem2 = new UIKartItem("resources/image/amul_milk_image_1_100_100.jpg", "variant001", "pView", "Amul Gold Full Cream Milk 1 leter", quantityPriceList1, "4", uIOffers, null);
		UIKartItem cartItem3 = new UIKartItem("resources/image/mother_image_1_100_100.jpg", "variant001", "pView", "Amul Gold Full Cream Milk 1 leter", quantityPriceList1, "1", uIOffers, null);
		UIKartItem cartItem4 = new UIKartItem("resources/image/amul_milk_image_1_100_100.jpg", "variant001", "pView", "Amul Gold Full Cream Milk 1 leter", quantityPriceList1, "2", uIOffers, null);
		
		List<UIKartItem> uIKartItems = new ArrayList<UIKartItem>();
		
		uIKartItems.add(cartItem2);
		uIKartItems.add(cartItem3);
		uIKartItems.add(cartItem4);
		
		UIKart uIKart = new UIFullKart(true, "", uIKartItems, uIKartItems.size(),"");
		return uIKart;
	}

}
