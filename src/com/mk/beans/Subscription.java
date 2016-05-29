package com.mk.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Subscription implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userId;
	
	private long subscriptionId;
	
	private String label;
	
	private Address deliveryAddress;
	
	private int subscriptionType;

	private List<SubscribedItem> items;
	
	private Date orderTime;
	
	private ArrayList<Offer> offers = null;

	private Date startDate;
	
	private Date endDate;
	
	private boolean customMonday;
	
	private boolean customTuesday;
	
	private boolean customWednesday;
	
	private boolean customThursday;
	
	private boolean customFriday;
	
	private boolean customSaturday;
	
	private boolean customSunday;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(long subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public int getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(int subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public ArrayList<Offer> getOffers() {
		return offers;
	}

	public void setOffers(ArrayList<Offer> offers) {
		this.offers = offers;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean isCustomMonday() {
		return customMonday;
	}

	public void setCustomMonday(boolean customMonday) {
		this.customMonday = customMonday;
	}

	public boolean isCustomTuesday() {
		return customTuesday;
	}

	public void setCustomTuesday(boolean customTuesday) {
		this.customTuesday = customTuesday;
	}

	public boolean isCustomWednesday() {
		return customWednesday;
	}

	public void setCustomWednesday(boolean customWednesday) {
		this.customWednesday = customWednesday;
	}

	public boolean isCustomThursday() {
		return customThursday;
	}

	public void setCustomThursday(boolean customThursday) {
		this.customThursday = customThursday;
	}

	public boolean isCustomFriday() {
		return customFriday;
	}

	public void setCustomFriday(boolean customFriday) {
		this.customFriday = customFriday;
	}

	public boolean isCustomSaturday() {
		return customSaturday;
	}

	public void setCustomSaturday(boolean customSaturday) {
		this.customSaturday = customSaturday;
	}

	public boolean isCustomSunday() {
		return customSunday;
	}

	public void setCustomSunday(boolean customSunday) {
		this.customSunday = customSunday;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<SubscribedItem> getItems() {
		return items;
	}

	public void setItems(List<SubscribedItem> items) {
		this.items = items;
	}

		
}
