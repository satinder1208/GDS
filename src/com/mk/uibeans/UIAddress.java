package com.mk.uibeans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mk.beans.Address;
import com.mk.beans.User;

public class UIAddress {
	String name;
	
	String mobile;
	
	String flatNo;
	
	String localityId;
	
	String localityLabel;
	
	String addressId;
	
	boolean isDefault;

	
	public UIAddress(String name, String mobile, String flatNo,
			String localityId, String localityLabel, String addressId, boolean isDefault) {
		this.name = name;
		this.mobile = mobile;
		this.flatNo = flatNo;
		this.localityId = localityId;
		this.localityLabel = localityLabel;
		this.addressId = addressId;
		this.isDefault = isDefault;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFlatNo() {
		return flatNo;
	}

	public void setFlatNo(String flatNo) {
		this.flatNo = flatNo;
	}

	public String getLocalityId() {
		return localityId;
	}

	public void setLocalityId(String localityId) {
		this.localityId = localityId;
	}

	public String getLocalityLabel() {
		return localityLabel;
	}

	public void setLocalityLabel(String localityLabel) {
		this.localityLabel = localityLabel;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	
	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public static List<UIAddress> getDummyaddressList(){
		List<UIAddress> addresses = new ArrayList<UIAddress>();
		
		UIAddress address = null; 

		address = new UIAddress("Rakesh Sharma", "9876543210", "40 B", "L001", "ATS Haciendas, Tower H2, Ahinsa Khand 1, Indirapuram, Ghaziabad, Uttar Pradesh, Pin 201010", "address001",false);
		addresses.add(address);

		address = new UIAddress("Manish Sharma", "9876543210", "43 E", "L001", "Shipra Krishna Vista, Ahinsa Khand 1, Indirapuram, Ghaziabad, Uttar Pradesh, Pin 201010", "address002",true);
		addresses.add(address);
		
		return addresses;
	}

	public static List<UIAddress> fillData(User user) {
		List<UIAddress> addresses = new ArrayList<UIAddress>();


		if(user.getAddresses() != null)
		{
			for (Iterator iterator = user.getAddresses().iterator(); iterator.hasNext();) {
				Address dbAddress = (Address) iterator.next();
				UIAddress address = new UIAddress(dbAddress.getName(), dbAddress.getMobile(), dbAddress.getAddressLine1() , 
						dbAddress.getLocalityId(), dbAddress.getLocalityName(), dbAddress.getAdressId(),dbAddress.isDefault());
				addresses.add(address);

			}

		}
				
		return addresses;
	}
	
}
