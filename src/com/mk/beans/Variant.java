package com.mk.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Variant implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String item_id;
	private String name;
	private String description;
	private boolean	isActive = true;
	private ArrayList<BooleanEntry> canBeSubscribed = null;
	private ArrayList<BooleanEntry> inStock = null;
	private ArrayList<BooleanEntry> availability = null;
	private ArrayList<QuantityPrice> price = null;
	private Image imageURL;
	private ArrayList<Image> moreImages = null;
	private ArrayList<String> keyFeatures = null;
	
	private ArrayList<Offer> offers = null;

	
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public ArrayList<BooleanEntry> getCanBeSubscribed() {
		return canBeSubscribed;
	}
	public void setCanBeSubscribed(ArrayList<BooleanEntry> canBeSubscribed) {
		this.canBeSubscribed = canBeSubscribed;
	}
	public ArrayList<BooleanEntry> getInStock() {
		return inStock;
	}
	public void setInStock(ArrayList<BooleanEntry> inStock) {
		this.inStock = inStock;
	}
	public ArrayList<BooleanEntry> getAvailability() {
		return availability;
	}
	public void setAvailability(ArrayList<BooleanEntry> availability) {
		this.availability = availability;
	}
	public ArrayList<QuantityPrice> getPrice() {
		return price;
	}
	public void setPrice(ArrayList<QuantityPrice> price) {
		this.price = price;
	}
	public Image getImageURL() {
		return imageURL;
	}
	public void setImageURL(Image imageURL) {
		this.imageURL = imageURL;
	}
	public ArrayList<String> getKeyFeatures() {
		return keyFeatures;
	}
	public void setKeyFeatures(ArrayList<String> keyFeatures) {
		this.keyFeatures = keyFeatures;
	}
	public ArrayList<Image> getMoreImages() {
		return moreImages;
	}
	public void setMoreImages(ArrayList<Image> moreImages) {
		this.moreImages = moreImages;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ArrayList<Offer> getOffers() {
		return offers;
	}
	public void setOffers(ArrayList<Offer> offers) {
		this.offers = offers;
	}
	public int getMaxAllowedQuantity() {
		
		int max = 0;
		
		for (Iterator iterator = price.get(0).getValue().iterator(); iterator.hasNext();) {
			QuantityPriceEntry entry = (QuantityPriceEntry) iterator.next();
			if(max < entry.getQid())
			{
				max = entry.getQid();
			}
		}
		
		return max;
	}
		
	

}
