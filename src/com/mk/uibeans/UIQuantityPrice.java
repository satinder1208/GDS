package com.mk.uibeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(Include.NON_NULL)
public class UIQuantityPrice implements Serializable{

	String id;
	String name;
	String mrp;
	String sellingPrice;
	Integer quantity;
	List<UIOffer> uIOffers = new ArrayList<UIOffer>();
	
	public UIQuantityPrice(){}
	
	public UIQuantityPrice(String id, String name, String mrp, String sellingPrice, List<UIOffer> uIOffers) {
		this.id = id;
		this.name = name;
		this.mrp = mrp;
		this.sellingPrice = sellingPrice;
		this.uIOffers = uIOffers;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMrp() {
		return mrp;
	}
	public void setMrp(String mrp) {
		this.mrp = mrp;
	}
	public String getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(String sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public List<UIOffer> getOffers() {
		return uIOffers;
	}
	
	public void setOffers(List<UIOffer> uIOffers) {
		this.uIOffers = uIOffers;
	}
	
}
