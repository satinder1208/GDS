package com.mk.uibeans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class UIOffer {

	String offerId;
	String offerText;
	
	public UIOffer(String offerId, String offerText) {
		this.offerId = offerId;
		this.offerText = offerText;
	}

	public String getOfferId() {
		return offerId;
	}
	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}
	public String getOfferText() {
		return offerText;
	}
	public void setOfferText(String offerText) {
		this.offerText = offerText;
	}
	
	
}
