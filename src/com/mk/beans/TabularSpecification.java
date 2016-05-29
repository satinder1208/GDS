package com.mk.beans;

import java.io.Serializable;
import java.util.ArrayList;

public class TabularSpecification implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String title;
	
	ArrayList<StringEntry> tableContent;

	public TabularSpecification(String title, ArrayList<StringEntry> tableContent) {
		this.title = title;
		this.tableContent = tableContent;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	public ArrayList<StringEntry> getTableContent() {
		return tableContent;
	}

	public void setTableContent(ArrayList<StringEntry> tableContent) {
		this.tableContent = tableContent;
	}
	
	
}