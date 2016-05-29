package com.mk.beans;

import java.io.Serializable;
import java.util.ArrayList;

public class QuantityPriceEntry implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer qid;
	private String label;
	private Double mrp;
	private Double sellingPrice;
	
	private ArrayList<Offer> offers = null;
	
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Double getMrp() {
		return mrp;
	}
	public void setMrp(Double mrp) {
		this.mrp = mrp;
	}
	public Double getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public Integer getQid() {
		return qid;
	}
	public void setQid(Integer qid) {
		this.qid = qid;
	}
	public ArrayList<Offer> getOffers() {
		return offers;
	}
	public void setOffers(ArrayList<Offer> offers) {
		this.offers = offers;
	}
	
	

}
