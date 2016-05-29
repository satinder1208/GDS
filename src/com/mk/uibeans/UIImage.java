package com.mk.uibeans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class UIImage {

	String img400x400;
	String img100x100;
	
	public UIImage(String img400x400, String img100x100) {
		this.img400x400 = img400x400;
		this.img100x100 = img100x100;
	}

	public String getImg400x400() {
		return img400x400;
	}
	public void setImg400x400(String img400x400) {
		this.img400x400 = img400x400;
	}
	public String getImg100x100() {
		return img100x100;
	}
	public void setImg100x100(String img100x100) {
		this.img100x100 = img100x100;
	}
	
	
}
