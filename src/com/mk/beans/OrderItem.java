package com.mk.beans;

import java.io.Serializable;
import java.util.ArrayList;

public class OrderItem implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String variantId;
	
	private ArrayList<Offer> offers = null;

	private QuantityPriceEntry quantityPriceEntry;

	public String getVariantId() {
		return variantId;
	}

	public void setVariantId(String variantId) {
		this.variantId = variantId;
	}

	public ArrayList<Offer> getOffers() {
		return offers;
	}

	public void setOffers(ArrayList<Offer> offers) {
		this.offers = offers;
	}

	public QuantityPriceEntry getQuantityPriceEntry() {
		return quantityPriceEntry;
	}

	public void setQuantityPriceEntry(QuantityPriceEntry quantityPriceEntry) {
		this.quantityPriceEntry = quantityPriceEntry;
	}
	
}
