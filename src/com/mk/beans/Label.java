package com.mk.beans;

import java.io.Serializable;

public class Label implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String label;
	
	String value;

	public Label(String label, String value) {
		super();
		this.label = label;
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}