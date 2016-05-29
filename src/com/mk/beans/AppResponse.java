package com.mk.beans;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public class AppResponse<T>  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MetaInfo meta = new MetaInfo();
	
	private T response;
	
	public AppResponse(T response, HttpStatus status) {
		this.response = response;
		this.meta.setCode(status);
	}

	public MetaInfo getMeta() {
		return meta;
	}

	public void setMeta(MetaInfo meta) {
		this.meta = meta;
	}

	public T getResponse() {
		return response;
	}

	public void setResponse(T response) {
		this.response = response;
	}
	
	

}
