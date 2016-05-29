package com.mk.beans;

import java.io.Serializable;

public class DescriptiveSpecification implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String title;
	String description;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
