package com.mk.uibeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.mk.beans.Category;
import com.mk.beans.Product;
import com.mk.beans.Variant;
import com.mk.utils.Constants;
import com.mk.utils.UIHelper;

@JsonInclude(Include.NON_NULL)
public class UISuggestionItem implements Serializable{

	String id;
	
	String label;
	
	String category;
	
	String imageURL;
	
	String url;

	public UISuggestionItem(){}
	
	public UISuggestionItem(String label, String category, String imageURL, String url) {
		this.label = label;
		this.category = category;
		this.imageURL = imageURL;
		this.url = url;
	}

	public UISuggestionItem(String label, String category, String url) {
		this.label = label;
		this.category = category;
		this.url = url;
	}

	public UISuggestionItem(String label, String category) {
		this.label = label;
		this.category = category;
	}

	public UISuggestionItem(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/*
	 *	following method is for test data for Product Search auto complete 
	 */
	public static List<UISuggestionItem> getProductSuggestionDummyData(){
		List<UISuggestionItem> list = new ArrayList<UISuggestionItem>();

		list.add(new UISuggestionItem("Amul","Brand"));
		list.add(new UISuggestionItem("Mother Dairy","Brand"));
		list.add(new UISuggestionItem("Paras","Brand"));
		list.add(new UISuggestionItem("Gopal","Brand"));
		list.add(new UISuggestionItem("Milk","Category"));
		list.add(new UISuggestionItem("Toned Milk","Category"));
		list.add(new UISuggestionItem("Full Cream Milk","Category"));
		list.add(new UISuggestionItem("Curd (Dahi)","Category"));
		list.add(new UISuggestionItem("Amul Gold Full Cream Milk","Product","resources/image/amul_milk_image_1_100_100.jpg","pView"));
		list.add(new UISuggestionItem("Mother Dairy Full Cream Milk","Product","resources/image/mother_image_1_100_100.jpg","pView"));
		
		return list;
	}

	public static List<UISuggestionItem> fillData(List<Product> products, List<Category> categories, String client) {
		
		if("app".equals(client))
		{

			
			List<UISuggestionItem> list = new ArrayList<UISuggestionItem>();

			for (Iterator iterator = categories.iterator(); iterator.hasNext();) {
				Category category = (Category) iterator.next();
				
				if(category.isBrand())
				{
					UISuggestionItem item = new UISuggestionItem( category.getLabel(), Constants.SEARCH_RESULT_BRAND, 
							UIHelper.getCategoryJsonURL(category.getCategoryId(), client) ) ;
					item.setId(category.getCategoryId());
					list.add(item);
				}
				
				
			}
			
			for (Iterator iterator = categories.iterator(); iterator.hasNext();) {
				Category category = (Category) iterator.next();
				
				if(!category.isBrand())
				{
					UISuggestionItem item = new UISuggestionItem( category.getLabel(), Constants.SEARCH_RESULT_CATEGORY, 
							UIHelper.getCategoryJsonURL(category.getCategoryId(), client) ) ;
					item.setId(category.getCategoryId());
					
					list.add(item);
				}
				
				
			}
			
			
			for (Iterator iterator = products.iterator(); iterator.hasNext();) {
				Product product = (Product) iterator.next();
				for (Iterator iterator2 = product.getVariants().iterator(); iterator2.hasNext();) {
					
					Variant variant = (Variant) iterator2.next();
					UISuggestionItem item = new UISuggestionItem(variant.getName(), Constants.SEARCH_RESULT_PRODUCT, null, 
							UIHelper.getProductJsonURL(variant.getItem_id(), client));
					item.setId(variant.getItem_id());
					list.add(item);
				}
				
			}
			
			return list.size() > 10 ? list.subList(0, 10) : list;
			
		
		}
		else{
			List<UISuggestionItem> list = new ArrayList<UISuggestionItem>();

			for (Iterator iterator = categories.iterator(); iterator.hasNext();) {
				Category category = (Category) iterator.next();
				
				if(category.isBrand())
				{
					list.add(new UISuggestionItem( category.getLabel(), Constants.SEARCH_RESULT_BRAND, UIHelper.getCategoryURL(category.getCategoryId()) ) );
				}
				
				
			}
			
			for (Iterator iterator = categories.iterator(); iterator.hasNext();) {
				Category category = (Category) iterator.next();
				
				if(!category.isBrand())
				{
					list.add(new UISuggestionItem( category.getLabel(), Constants.SEARCH_RESULT_CATEGORY, UIHelper.getCategoryURL(category.getCategoryId()) ) );
				}
				
				
			}
			
			
			for (Iterator iterator = products.iterator(); iterator.hasNext();) {
				Product product = (Product) iterator.next();
				for (Iterator iterator2 = product.getVariants().iterator(); iterator2.hasNext();) {
					
					Variant variant = (Variant) iterator2.next();
					
					list.add(new UISuggestionItem(variant.getName(), Constants.SEARCH_RESULT_PRODUCT, 
							UIHelper.getImageURL(variant.getImageURL().getIcon()), UIHelper.getProductURL(variant.getItem_id())));
				}
				
			}
			
			return list.size() > 10 ? list.subList(0, 10) : list;

		}
		
				
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
