package com.mk.beans;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public class MetaInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpStatus code = HttpStatus.OK;
	
	private String errorDetail="";

	public HttpStatus getCode() {
		return code;
	}

	public void setCode(HttpStatus code) {
		this.code = code;
	}

	public String getErrorDetail() {
		return errorDetail;
	}

	public void setErrorDetail(String errorDetail) {
		this.errorDetail = errorDetail;
	}
	
	

}
