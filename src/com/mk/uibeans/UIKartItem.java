package com.mk.uibeans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class UIKartItem {

	String img100x100;
	
	String variantId;
	
	String productURL;
	
	String varientName;
	
	List<UIQuantityPrice> quantityPriceList;
	
	String selectedQuantityId;
	
	List<UIOffer> productOffers;
	
	List<UIOffer> variantOffers;
	
	

	public UIKartItem(String img100x100, String variantId, String productURL,
			String varientName, List<UIQuantityPrice> quantityPriceList,
			String selectedQuantityId, List<UIOffer> productOffers,
			List<UIOffer> variantOffers) {
		this.img100x100 = img100x100;
		this.variantId = variantId;
		this.productURL = productURL;
		this.varientName = varientName;
		this.quantityPriceList = quantityPriceList;
		this.selectedQuantityId = selectedQuantityId;
		this.productOffers = productOffers;
		this.variantOffers = variantOffers;
	}

	public String getImg100x100() {
		return img100x100;
	}

	public void setImg100x100(String img100x100) {
		this.img100x100 = img100x100;
	}

	public String getVariantId() {
		return variantId;
	}

	public void setVariantId(String variantId) {
		this.variantId = variantId;
	}

	public String getProductURL() {
		return productURL;
	}

	public void setProductURL(String productURL) {
		this.productURL = productURL;
	}

	public String getVarientName() {
		return varientName;
	}

	public void setVarientName(String varientName) {
		this.varientName = varientName;
	}

	public List<UIQuantityPrice> getQuantityPriceList() {
		return quantityPriceList;
	}

	public void setQuantityPriceList(List<UIQuantityPrice> quantityPriceList) {
		this.quantityPriceList = quantityPriceList;
	}

	public String getSelectedQuantityId() {
		return selectedQuantityId;
	}

	public void setSelectedQuantityId(String selectedQuantityId) {
		this.selectedQuantityId = selectedQuantityId;
	}

	public List<UIOffer> getProductOffers() {
		return productOffers;
	}

	public void setProductOffers(List<UIOffer> productOffers) {
		this.productOffers = productOffers;
	}

	public List<UIOffer> getVariantOffers() {
		return variantOffers;
	}

	public void setVariantOffers(List<UIOffer> variantOffers) {
		this.variantOffers = variantOffers;
	}
	
	
}
