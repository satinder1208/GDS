package com.mk.beans;

import java.io.Serializable;
import java.util.List;

public class ProductFilter implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String filterCategoryName;
	private boolean isAnyFilterSelected;
	private String key;
	
	private List<ProductFilterItem> filters;

	public String getFilterCategoryName() {
		return filterCategoryName;
	}

	public void setFilterCategoryName(String filterCategoryName) {
		this.filterCategoryName = filterCategoryName;
	}

	public boolean isAnyFilterSelected() {
		return isAnyFilterSelected;
	}

	public void setAnyFilterSelected(boolean isAnyFilterSelected) {
		this.isAnyFilterSelected = isAnyFilterSelected;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<ProductFilterItem> getFilters() {
		return filters;
	}

	public void setFilters(List<ProductFilterItem> filters) {
		this.filters = filters;
	}
	
	

}
