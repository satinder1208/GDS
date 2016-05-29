package com.mk.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product")
public class Product implements Serializable{
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private String _id;
		private String productId;
		private String name;
		private String description;
		private String longDescription;
		private String brand;
		private String brandId;
		
		private Variant variant = null;
		
		private ArrayList<Variant> variants = null;
		
		private ArrayList<String> keyFeatures = null;
		
		private ArrayList<Category> categories = null;
		
		private ArrayList<String> keys = null;
		
		private ArrayList<Offer> offers = null;
		
		private ArrayList<DescriptiveSpecification> descriptiveSpecifications = null;
		
		private ArrayList<TabularSpecification> tabularSpecifications = null;
		
		
		public String getProductId() {
			return productId;
		}

		public void setProductId(String productId) {
			this.productId = productId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getBrand() {
			return brand;
		}

		public void setBrand(String brand) {
			this.brand = brand;
		}

		public String getBrandId() {
			return brandId;
		}

		public void setBrandId(String brandId) {
			this.brandId = brandId;
		}

		public ArrayList<Variant> getVariants() {
			return variants;
		}

		public void setVariants(ArrayList<Variant> variants) {
			this.variants = variants;
		}

		public ArrayList<Category> getCategories() {
			return categories;
		}

		public void setCategories(ArrayList<Category> categories) {
			this.categories = categories;
		}

		public ArrayList<String> getKeys() {
			return keys;
		}

		public void setKeys(ArrayList<String> keys) {
			this.keys = keys;
		}

		public ArrayList<String> getKeyFeatures() {
			return keyFeatures;
		}

		public void setKeyFeatures(ArrayList<String> keyFeatures) {
			this.keyFeatures = keyFeatures;
		}

		public String getLongDescription() {
			return longDescription;
		}

		public void setLongDescription(String longDescription) {
			this.longDescription = longDescription;
		}

		public ArrayList<DescriptiveSpecification> getDescriptiveSpecifications() {
			return descriptiveSpecifications;
		}

		public void setDescriptiveSpecifications(
				ArrayList<DescriptiveSpecification> descriptiveSpecifications) {
			this.descriptiveSpecifications = descriptiveSpecifications;
		}

		public ArrayList<TabularSpecification> getTabularSpecifications() {
			return tabularSpecifications;
		}

		public void setTabularSpecifications(
				ArrayList<TabularSpecification> tabularSpecifications) {
			this.tabularSpecifications = tabularSpecifications;
		}

		public ArrayList<Offer> getOffers() {
			return offers;
		}

		public void setOffers(ArrayList<Offer> offers) {
			this.offers = offers;
		}
		
		public Variant getVariant(String id)
		{
			if(variants != null)
			{
				for (Iterator iterator = variants.iterator(); iterator
						.hasNext();) {
					Variant variant = (Variant) iterator.next();
					
					if(variant.getItem_id().equals(id))
					{
						return variant;
					}
					
				}
			}
			
			return null;
		}

		public String get_id() {
			return _id;
		}

		public void set_id(String _id) {
			this._id = _id;
		}

		public Variant getVariant() {
			return variant;
		}

		public void setVariant(Variant variant) {
			this.variant = variant;
		}
	

}
