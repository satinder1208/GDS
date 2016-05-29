package com.mk.beans;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
public class Category implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String categoryId;
	private String label;
	private boolean isBrand;
	private String description;
	
	private ArrayList<Category> relatedCategories = null;
	
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public boolean isBrand() {
		return isBrand;
	}
	public void setBrand(boolean isBrand) {
		this.isBrand = isBrand;
	}
	public ArrayList<Category> getRelatedCategories() {
		return relatedCategories;
	}
	public void setRelatedCategories(ArrayList<Category> relatedCategories) {
		this.relatedCategories = relatedCategories;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}
