package com.mk.uibeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class UIVariant implements Serializable	{
	
	String id;
	String name;
	String packagingTitle;
	Boolean canBeSubscribed;
	Boolean selected;
	List<UIQuantityPrice> quantityPriceList= new ArrayList<UIQuantityPrice>();
	List<UIImage> uIImages = new ArrayList<UIImage>();
	List<UIOffer> uIOffers = new ArrayList<UIOffer>();
	List<String> keyFeatures = new ArrayList<String>();
	
	public UIVariant(String id, String name, String packagingTitle,
			Boolean canBeSubscribed, Boolean selected,
			List<UIQuantityPrice> quantityPriceList, List images, List<UIOffer> uIOffers, List<String> keyFeatures) {
		this.id = id;
		this.name = name;
		this.packagingTitle = packagingTitle;
		this.canBeSubscribed = canBeSubscribed;
		this.selected = selected;
		this.quantityPriceList = quantityPriceList;
		this.uIImages = images;
		this.uIOffers = uIOffers;
		this.keyFeatures = keyFeatures;
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
	public String getPackagingTitle() {
		return packagingTitle;
	}
	public void setPackagingTitle(String packagingTitle) {
		this.packagingTitle = packagingTitle;
	}
	public Boolean getCanBeSubscribed() {
		return canBeSubscribed;
	}
	public void setCanBeSubscribed(Boolean canBeSubscribed) {
		this.canBeSubscribed = canBeSubscribed;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	public List<UIQuantityPrice> getQuantityPriceList() {
		return quantityPriceList;
	}
	public void setQuantityPriceList(List<UIQuantityPrice> quantityPriceList) {
		this.quantityPriceList = quantityPriceList;
	}
	public List<UIImage> getImages() {
		return uIImages;
	}
	public void setImages(List<UIImage> uIImages) {
		this.uIImages = uIImages;
	}
	public List<UIOffer> getOffers() {
		return uIOffers;
	}
	public void setOffers(List<UIOffer> uIOffers) {
		this.uIOffers = uIOffers;
	}
	public List<String> getKeyFeatures() {
		return keyFeatures;
	}
	public void setKeyFeatures(List<String> keyFeatures) {
		this.keyFeatures = keyFeatures;
	}
	
}
