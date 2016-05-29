package com.mk.uibeans;

import java.util.List;

public class UISearchData {
	
	private List<UIVariant> products;
	private int totalPages;
	private int totalRows;
	private int currentPage;
	private String nextPage;
	private String parameters;
	
	public List<UIVariant> getProducts() {
		return products;
	}
	public void setProducts(List<UIVariant> products) {
		this.products = products;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public String getNextPage() {
		return nextPage;
	}
	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}
	public String getParameters() {
		return parameters;
	}
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}
	
	
}
