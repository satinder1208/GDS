package com.mk.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class QuantityPrice implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String key;
	private ArrayList<QuantityPriceEntry> value;
	




	public String getKey() {
		return key;
	}



	public void setKey(String key) {
		this.key = key;
	}



	public ArrayList<QuantityPriceEntry> getValue() {
		return value;
	}



	public void setValue(ArrayList<QuantityPriceEntry> value) {
		this.value = value;
	}
	
	
	public QuantityPriceEntry getQuantityPriceEntry(int qId)
	{
		QuantityPriceEntry q = null;
		for (Iterator iterator = getValue().iterator(); iterator.hasNext();) {
			QuantityPriceEntry entry = (QuantityPriceEntry) iterator.next();
			if(qId == entry.getQid())
			{
				q = entry;
			}
		}
		
		return q;
	}
	

}
