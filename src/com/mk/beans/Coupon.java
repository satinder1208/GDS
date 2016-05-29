package com.mk.beans;

import java.io.Serializable;
import java.util.Date;

public class Coupon implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String couponCode;
	
	private Date issueDate;
	
	private Date validThrough;
	
	private double worth;
	
	private double sellingPrice;
	
	private String issuedToUserId;
	
	private int status;
	
	private String usedByUserId;
	
	private Date useDate;
	
	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getValidThrough() {
		return validThrough;
	}

	public void setValidThrough(Date validThrough) {
		this.validThrough = validThrough;
	}

	public double getWorth() {
		return worth;
	}

	public void setWorth(double worth) {
		this.worth = worth;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public String getIssuedToUserId() {
		return issuedToUserId;
	}

	public void setIssuedToUserId(String issuedToUserId) {
		this.issuedToUserId = issuedToUserId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUsedByUserId() {
		return usedByUserId;
	}

	public void setUsedByUserId(String usedByUserId) {
		this.usedByUserId = usedByUserId;
	}

	public Date getUseDate() {
		return useDate;
	}

	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
