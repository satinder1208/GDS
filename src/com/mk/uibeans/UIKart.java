package com.mk.uibeans;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public abstract class UIKart {
	
	protected String kartUUID;

	protected boolean success;
	
	protected String flashMessage;
	
	protected String myCartMessage;
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public String getFlashMessage() {
		return flashMessage;
	}

	public void setFlashMessage(String flashMessage) {
		this.flashMessage = flashMessage;
	}

	public String getMyCartMessage() {
		return myCartMessage;
	}

	public void setMyCartMessage(String myCartMessage) {
		this.myCartMessage = myCartMessage;
	}
	
	
	abstract public int getNoOfItem();

	public String getKartUUID() {
		return kartUUID;
	}

	public void setKartUUID(String kartUUID) {
		this.kartUUID = kartUUID;
	}

	


}
