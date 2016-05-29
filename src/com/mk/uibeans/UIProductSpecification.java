package com.mk.uibeans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class UIProductSpecification {

	String title;
	
	List<String> descriptions;
	
	List<UILabel> tableContent;

	public UIProductSpecification(String title, List<String> descriptions,
			List<UILabel> tableContent) {
		this.title = title;
		this.descriptions = descriptions;
		this.tableContent = tableContent;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(List<String> descriptions) {
		this.descriptions = descriptions;
	}

	public List<UILabel> getTableContent() {
		return tableContent;
	}

	public void setTableContent(List<UILabel> tableContent) {
		this.tableContent = tableContent;
	}
	
	
}
