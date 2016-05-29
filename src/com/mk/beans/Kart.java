package com.mk.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "kart")
public class Kart implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String kartUUID = UUID.randomUUID().toString();
	
	private String _id;
	private String userId;

	private HashMap<String, KartItem> items = new HashMap<String, KartItem>();

	public int getNoOfItem() {
		
		return items.size();
	}

	

	

	public boolean addItem(String variantId, int quantity, Product variant) {

		KartItem existingItem = items.get(variantId);
		
		if(existingItem != null)
		{
			if(existingItem.getQuantity() + quantity <= existingItem.getMaxAllowedQuantity())
			{
				existingItem.setQuantity(existingItem.getQuantity() + quantity);
			}
			else
			{
				return false;
			}
			
		}
		else{
			
			KartItem kartItem = new KartItem();
			
			items.put(variantId, kartItem);
			
			kartItem.setVariantId(variantId);
			int maxQnt = variant.getVariant(variantId).getMaxAllowedQuantity();
			kartItem.setMaxAllowedQuantity(maxQnt);
			kartItem.setQuantity(quantity);
			
		}
		
		return true;
	}
	
	public void removeAll()
	{
		items.clear();
	}
	
	public void removeItem(String variantId) {

		KartItem existingItem = items.get(variantId);
		
		if(existingItem != null)
		{
			items.remove(variantId);
		}
		
		
	}
	
	
	public void ensureQuantity(String variantId, int quantity) {

		KartItem existingItem = items.get(variantId);
		
		if(existingItem != null)
		{
			
			existingItem.setQuantity(quantity);
			
		}
		
	
	}

	

	public HashMap<String, KartItem> getItems() {
		return items;
	}





	public void setItems(HashMap<String, KartItem> items) {
		this.items = items;
	}





	public String getUserId() {
		return userId;
	}





	public void setUserId(String userId) {
		this.userId = userId;
	}





	public String get_id() {
		return _id;
	}





	public void set_id(String _id) {
		this._id = _id;
	}





	public String getKartUUID() {
		return kartUUID;
	}





	public void setKartUUID(String kartUUID) {
		this.kartUUID = kartUUID;
	}

	
	public void refreshUUID()
	{
		this.kartUUID = UUID.randomUUID().toString();
	}

}
