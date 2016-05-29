package com.mk.uibeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.mk.beans.Locality;

@JsonInclude(Include.NON_NULL)
public class UILocalitySuggestionItem extends UISuggestionItem implements Serializable {

	String localityId;
	
	String shortDisplayName;
	
	

	public UILocalitySuggestionItem(String label, String category,String imageURL, String url, String localityId, String shortDisplayName) {
		super(label, category, imageURL, url);
		this.localityId = localityId;
		this.shortDisplayName = shortDisplayName;
	}

	public String getLocalityId() {
		return localityId;
	}

	public void setLocalityId(String localityId) {
		this.localityId = localityId;
	}

	public String getShortDisplayName() {
		return shortDisplayName;
	}

	public void setShortDisplayName(String shortDisplayName) {
		this.shortDisplayName = shortDisplayName;
	}

	/*
	 *	following method is for test data for Locality Search auto complete 
	 */
	public static List<UILocalitySuggestionItem> getLocalitySuggestionDummyData(){
		List<UILocalitySuggestionItem> list = new ArrayList<UILocalitySuggestionItem>();

		list.add(new UILocalitySuggestionItem("Shipra Suncity, Vaibhav Khand 3, Indirapuram, Ghaziabad, Uttar Pradesh, Pin 201010","Indirapuram","","","localityId_Shipra_Suncity","Shipra Suncity Indiarapuram"));
		list.add(new UILocalitySuggestionItem("ATS Advantage, Vaibhav Khand 1, Indirapuram, Ghaziabad, Uttar Pradesh, Pin 201010","Indirapuram","","","localityId_ATS","ATS Advantage Indiarapuram"));
		list.add(new UILocalitySuggestionItem("Shipra Riviera, Gyan Khand 3, Indirapuram, Ghaziabad, Uttar Pradesh, Pin 201010","Indirapuram","","","localityId_Shipra_Riviera","Shipra Riviera Indiarapuram"));
		list.add(new UILocalitySuggestionItem("Sector 11, Vasundhara, Ghaziabad, Uttar Pradesh, Pin 201010","Vasundhara","","","localityId_vasundhara_sec_11","Sector 11 Vasundhara Ghaziabad"));
		list.add(new UILocalitySuggestionItem("Sector 10, Vasundhara, Ghaziabad, Uttar Pradesh, Pin 201010","Vasundhara","","","localityId_vasundhara_sec_10","Sector 10 Vasundhara Ghaziabad"));
		list.add(new UILocalitySuggestionItem("Sector 9, Vasundhara, Ghaziabad, Uttar Pradesh, Pin 201010","Vasundhara","","","localityId_vasundhara_sec_9","Sector 9 Vasundhara Ghaziabad"));
		list.add(new UILocalitySuggestionItem("Sector 4, Vaishali, Ghaziabad, Uttar Pradesh, Pin 201010","Vaishali","","","localityId_vaishali_sec_4","Sector 4 Vaishali"));
		list.add(new UILocalitySuggestionItem("Sector 5, Vaishali, Ghaziabad, Uttar Pradesh, Pin 201010","Vaishali","","","localityId_vaishali_sec_5","Sector 5 Vaishali"));
		list.add(new UILocalitySuggestionItem("Sector 6, Vaishali, Ghaziabad, Uttar Pradesh, Pin 201010","Vaishali","","","localityId_vaishali_sec_6","Sector 6 Vaishali"));
		
		return list;
	}

	public static List<UILocalitySuggestionItem> fillData(
			List<Locality> localities) {
		
		List<UILocalitySuggestionItem> list = new ArrayList<UILocalitySuggestionItem>();
		
		for (Iterator iterator = localities.iterator(); iterator.hasNext();) {
			Locality locality = (Locality) iterator
					.next();
			list.add(new UILocalitySuggestionItem(locality.getAddress(),locality.getCity(),"","",locality.getLocalityId(),locality.getDisplayName()));
			
		}
		
		return list;
	}
	
}
