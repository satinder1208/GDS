package com.mk.beans;

import java.io.Serializable;

public class Image implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String icon;
	private String thumbnail;
	private String small;
	private String big;
	
	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getSmall() {
		return small;
	}
	public void setSmall(String small) {
		this.small = small;
	}
	public String getBig() {
		return big;
	}
	public void setBig(String big) {
		this.big = big;
	}

}
