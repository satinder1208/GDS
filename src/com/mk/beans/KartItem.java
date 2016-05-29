package com.mk.beans;

import java.io.Serializable;

public class KartItem implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String variantId;
	private int quantity;
	private int maxAllowedQuantity;
	
	public KartItem() {
		
	}
	
	public String getVariantId() {
		return variantId;
	}
	public void setVariantId(String variantId) {
		this.variantId = variantId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getMaxAllowedQuantity() {
		return maxAllowedQuantity;
	}
	public void setMaxAllowedQuantity(int maxAllowedQuantity) {
		this.maxAllowedQuantity = maxAllowedQuantity;
	}
	
	

}
