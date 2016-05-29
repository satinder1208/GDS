package com.mk.uibeans;

public class UIUser {

	boolean loggedIn = false;
	
	String email;
	
	String name;
	
	String contactNo;
	
//	String localityId;
//	
	String userId;

	
	public UIUser(boolean loggedIn, String email, String name,
			String contactNo,  String userId) {
		this.loggedIn = loggedIn;
		this.email = email;
		this.name = name;
		this.contactNo = contactNo;
//		this.localityId = localityId;
		this.userId = userId;
	}
	
	public UIUser(){}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

//	public String getLocalityId() {
//		return localityId;
//	}
//
//	public void setLocalityId(String localityId) {
//		this.localityId = localityId;
//	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public static UIUser getUserDummayData(){
		UIUser user = new UIUser(true, "test.mail@test1.com", "Manish Sharma", "9898989898", "manishsharma");
		return user;
	}
}
