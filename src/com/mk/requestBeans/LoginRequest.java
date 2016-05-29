package com.mk.requestBeans;

import java.io.Serializable;

public class LoginRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String username = null;
	private String password = null;
	private String deviceId = null;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	

}
