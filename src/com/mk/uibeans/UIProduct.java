package com.mk.uibeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.mk.beans.Category;
import com.mk.beans.DescriptiveSpecification;
import com.mk.beans.QuantityPriceEntry;
import com.mk.beans.StringEntry;
import com.mk.beans.TabularSpecification;
import com.mk.utils.UIHelper;

@JsonInclude(Include.NON_NULL)
public class UIProduct implements Serializable{
	
	String id = "1";
	String name = null;
	UILink brand;
	List<UILink> catagories = new ArrayList<UILink>();
	String shortDescription;
	String detailedDescription;
	List<String> keyFeatures = new ArrayList<String>();
	List<UIVariant> uIVariants = new ArrayList<UIVariant>();
	List<UIOffer> uIOffers = new ArrayList<UIOffer>();
	List<UIProductSpecification> specifications = new ArrayList<UIProductSpecification>();

	public UIProduct(){}
	
	public UIProduct(String id, String name, UILink brand, List<UILink> catagories,
			String shortDescription, String detailedDescription,
			List<String> keyFeatures, List<UIVariant> uIVariants, List<UIOffer> uIOffers, List<UIProductSpecification> specifications) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.catagories = catagories;
		this.shortDescription = shortDescription;
		this.detailedDescription = detailedDescription;
		this.keyFeatures = keyFeatures;
		this.uIVariants = uIVariants;
		this.uIOffers = uIOffers;
		this.specifications = specifications;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UILink getBrand() {
		return brand;
	}
	public void setBrand(UILink brand) {
		this.brand = brand;
	}
	public List<UILink> getCatagories() {
		return catagories;
	}
	public void setCatagories(List<UILink> catagories) {
		this.catagories = catagories;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getDetailedDescription() {
		return detailedDescription;
	}
	public void setDetailedDescription(String detailedDescription) {
		this.detailedDescription = detailedDescription;
	}
	public List<String> getKeyFeatures() {
		return keyFeatures;
	}
	public void setKeyFeatures(List<String> keyFeatures) {
		this.keyFeatures = keyFeatures;
	}
	public List<UIVariant> getVariants() {
		return uIVariants;
	}
	public void setVariants(List<UIVariant> uIVariants) {
		this.uIVariants = uIVariants;
	}
	
	public List<UIOffer> getOffers() {
		return uIOffers;
	}

	public void setOffers(List<UIOffer> uIOffers) {
		this.uIOffers = uIOffers;
	}

	public List<UIProductSpecification> getSpecifications() {
		return specifications;
	}

	public void setSpecifications(List<UIProductSpecification> specifications) {
		this.specifications = specifications;
	}

	/*
	 *	following method is for test data for Product Search auto complete 
	 */
	public static UIProduct getProductDummyData(){
		List<UILink> catagories = new ArrayList<UILink>();
		catagories.add(new UILink("Dairy","/mk"));
		catagories.add(new UILink("Milk","/mk"));
		
		String[] keyFeatures = {"Most hygenic liquid milk from Amul",
				"Available in 500 ml amd 1 lt pack",
				"48 Hour life if kept under refrigeration below 8°C",
				"Pasteurised milk", "6 % FAT", "9 % SNF"};
		
		List<UIOffer> uIOffers = new ArrayList<UIOffer>();
		uIOffers.add(new UIOffer("offer001", "Quantity Specific 1st : One 10 gram dary milk free with 4 leter of Amul Gold Milk"));
		uIOffers.add(new UIOffer("offer001", "Quantity Specific 2nd : One 10 gram dary milk free with 4 leter of Amul Gold Milk"));
		List<UIQuantityPrice> quantityPriceList1 = new ArrayList<UIQuantityPrice>();
		quantityPriceList1.add(new UIQuantityPrice("0", "1 Leter", "48", "48",uIOffers));
		quantityPriceList1.add(new UIQuantityPrice("1", "2 Leter", "96", "98",null));
		quantityPriceList1.add(new UIQuantityPrice("2", "3 Leter", "144", "143.5",null));
		quantityPriceList1.add(new UIQuantityPrice("3", "4 Leter", "191", "190", uIOffers));
		uIOffers = new ArrayList<UIOffer>();
		uIOffers.add(new UIOffer("offer001", "Quantity Specific: One 5 gram dary milk free with 5 leter of Amul Gold Milk"));
		quantityPriceList1.add(new UIQuantityPrice("4", "5 Leter", "238", "236",null));
		
		List<UIImage> images1 = new ArrayList<UIImage>();
		images1.add(new UIImage("resources/image/amul_milk_image_1_400_400.jpg", "resources/image/amul_milk_image_1_100_100.jpg"));
		images1.add(new UIImage("resources/image/mother_image_1_400_400.jpg", "resources/image/mother_image_1_100_100.jpg"));
		
		List<UIQuantityPrice> quantityPriceList2 = new ArrayList<UIQuantityPrice>();
		quantityPriceList2.add(new UIQuantityPrice("0", ".5 Leter", "24", "24",null));
		quantityPriceList2.add(new UIQuantityPrice("1", "1 Leter", "48", "48",null));
		quantityPriceList2.add(new UIQuantityPrice("2", "1.5 Leter", "72", "72",null));
		quantityPriceList2.add(new UIQuantityPrice("3", "2 Leter", "96", "95",null));
		quantityPriceList2.add(new UIQuantityPrice("4", "2.5 Leter", "120", "118", uIOffers));
		
		List<UIImage> images2 = new ArrayList<UIImage>();
		images2.add(new UIImage("resources/image/mother_image_1_400_400.jpg", "resources/image/mother_image_1_100_100.jpg"));
		images2.add(new UIImage("resources/image/amul_milk_image_1_400_400.jpg", "resources/image/amul_milk_image_1_100_100.jpg"));
		images2.add(new UIImage("resources/image/pramotionContent3.jpg", "resources/image/pramotionContent3.jpg"));
		images2.add(new UIImage("resources/image/amul_milk_image_1_400_400.jpg", "resources/image/amul_milk_image_1_100_100.jpg"));
		
		
		uIOffers = new ArrayList<UIOffer>();
		uIOffers.add(new UIOffer("offer1", "Variant specific offer: One Milky bar free with any pouch of this product."));
		List<UIVariant> uIVariants = new ArrayList<UIVariant>();
		UIVariant variant1 = new UIVariant("varient01", "Amul Gold Full Cream Milk 1 leter", "1 Leter Pouch, Rs 48", true, false, quantityPriceList1, images1, uIOffers, null);
		uIVariants.add(variant1);
		UIVariant variant2 = new UIVariant("varient02", "Amul Gold Full Cream Milk .5 leter", ".5 Leter Pouch, Rs 24", true, true, quantityPriceList2, images2,null, null);
		uIVariants.add(variant2);
		
		List<UIProductSpecification> specification = new ArrayList<UIProductSpecification>();
		List<UILabel> tableContent = new ArrayList<UILabel>();
		List<String> descriptions = new ArrayList<String>();
		
		tableContent.add(new UILabel("FAT(%)", "6.0 min"));
		tableContent.add(new UILabel("SNF(%)", "9.0 min"));
		UIProductSpecification uIProductSpecification = new UIProductSpecification("Composition", descriptions, tableContent);
		specification.add(uIProductSpecification);
		
		tableContent = new ArrayList<UILabel>();
		tableContent.add(new UILabel("Energy", "87 kcal"));
		tableContent.add(new UILabel("Energy from FAT", "54 kcal"));
		tableContent.add(new UILabel("Total FAT", "6 gram"));
		tableContent.add(new UILabel("Saturated FAT", "3.7 gram"));
		tableContent.add(new UILabel("Cholesterol", "16 mg"));
		tableContent.add(new UILabel("Total Carbohydrate", "5.0 gram"));
		tableContent.add(new UILabel("Added Sugar", "0 gram"));
		tableContent.add(new UILabel("Protein", "3.3 gram"));
		tableContent.add(new UILabel("Calcium", "150 mg"));
		tableContent.add(new UILabel("Phosphorus", "130 mg"));
		tableContent.add(new UILabel("Sodium", "30 mg"));
		tableContent.add(new UILabel("Thiamine", "42 mcg"));
		tableContent.add(new UILabel("Riboflavin", "120 mcg"));
		tableContent.add(new UILabel("Niacin", "100 mcg"));
		tableContent.add(new UILabel("Folic Acid", "7.5 mcg"));
		tableContent.add(new UILabel("Vit. A(Retinol)", "65 mcg"));
		uIProductSpecification = new UIProductSpecification("Nutritional Information", descriptions, tableContent);
		specification.add(uIProductSpecification);
		
		descriptions = new ArrayList<String>();
		descriptions.add("48 Hours from the date of packing if kept under refrigeration below 8°C");
		uIProductSpecification = new UIProductSpecification("Shelf Life", descriptions, null);
		specification.add(uIProductSpecification);
		
		descriptions = new ArrayList<String>();
		descriptions.add("Under Refrigeration (Below 8°C)");
		uIProductSpecification = new UIProductSpecification("Storage condition", descriptions, null);
		specification.add(uIProductSpecification);
		
		descriptions = new ArrayList<String>();
		descriptions.add("Amul Milk is the most hygenic liquid milk available in the market.");
		descriptions.add("It is pasteurised in state-of-the-art processing plants and pouch-packed to make it conveniently available to consumers.");
		uIProductSpecification = new UIProductSpecification("Product Features", descriptions, null);
		specification.add(uIProductSpecification);
		
		descriptions = new ArrayList<String>();
		descriptions.add("Direct consumption, and Making of : Tea or Coffee, Sweets, Khoa, Curd, Buttermilk, Ghee");
		uIProductSpecification = new UIProductSpecification("Product Application", descriptions, null);
		specification.add(uIProductSpecification);
		
		
		
		uIOffers = new ArrayList<UIOffer>();
		uIOffers.add(new UIOffer("offer1", "Product specific offer: One Milky bar free with any pouch of this product."));
		UIProduct uIProduct = new UIProduct(
				"productId001",
				"Amul Gold Full Cream Milk",
				new UILink("Amul","/mk"),
				catagories,
				"Pasteurised milk Amul milk meets the PFA standards for the respective type of milk.",
				"Amul Milk is the most hygenic liquid milk available in the market. It is pasteurised in state-of-the-art processing plants and pouch-packed to make it conveniently available to consumers.",
				Arrays.asList(keyFeatures),
				uIVariants,
				uIOffers,
				specification
		);
		return uIProduct;
	}

	public static UIProduct fillData(String variantId, com.mk.beans.Product dbProduct) {
		
		List<UILink> catagories = new ArrayList<UILink>();
		
		for (Iterator<Category> iterator = dbProduct.getCategories().iterator(); iterator.hasNext();) {
			Category category = iterator.next();
			catagories.add(new UILink(category.getLabel(), UIHelper.getCategoryURL(category.getCategoryId())));
		}
		
		
		List<UIVariant> uIVariants = new ArrayList<UIVariant>();

		
		for (Iterator<com.mk.beans.Variant> iterator = dbProduct.getVariants().iterator(); iterator.hasNext();) {
			com.mk.beans.Variant variant = iterator.next();
			
			List<UIImage> uIImages = new ArrayList<UIImage>();
			com.mk.beans.Image image = variant.getImageURL();
			uIImages.add(new UIImage(UIHelper.getImageURL(image.getBig()), UIHelper.getImageURL(image.getThumbnail())));
			for (Iterator iterator2 = variant.getMoreImages().iterator(); iterator2.hasNext();) {
				com.mk.beans.Image image2 = (com.mk.beans.Image) iterator2.next();
				uIImages.add(new UIImage(UIHelper.getImageURL(image2.getBig()), UIHelper.getImageURL(image2.getThumbnail())));
				
			}
			
			List<UIQuantityPrice> uIQuantityPrice = new ArrayList<UIQuantityPrice>();
			
			for (Iterator iterator2 = ((com.mk.beans.QuantityPrice)variant.getPrice().get(0)).getValue().iterator(); iterator2
					.hasNext();) {
				QuantityPriceEntry priceEntry = (QuantityPriceEntry)iterator2.next();
				
				List<UIOffer> uIOffers = new ArrayList<UIOffer>();
				
				for (Iterator iterator3 = priceEntry.getOffers().iterator(); iterator3.hasNext();) {
					com.mk.beans.Offer offer = (com.mk.beans.Offer) iterator3.next();
					uIOffers.add(new UIOffer(offer.getOfferId(), offer.getDescription()));
				}
				
				uIQuantityPrice.add(new UIQuantityPrice(priceEntry.getQid().toString(), priceEntry.getLabel(), priceEntry.getMrp().toString(), 
						priceEntry.getSellingPrice().toString(), uIOffers));
				
			}
			
			List<UIOffer> uIOffers = new ArrayList<UIOffer>();
			
			for (Iterator iterator3 = variant.getOffers().iterator(); iterator3.hasNext();) {
				com.mk.beans.Offer offer = (com.mk.beans.Offer) iterator3.next();
				uIOffers.add(new UIOffer(offer.getOfferId(), offer.getDescription()));
			}
			
			uIVariants.add(new UIVariant(variant.getItem_id(), variant.getName(), variant.getDescription(), variant.getCanBeSubscribed().get(0).getValue(), variant.getItem_id().equals(variantId), 
					uIQuantityPrice, uIImages, uIOffers, variant.getKeyFeatures()));
			
		}
		
		List<UIProductSpecification> specification = new ArrayList<UIProductSpecification>();
		
		for (Iterator iterator = dbProduct.getTabularSpecifications().iterator(); iterator.hasNext();) {
			TabularSpecification tabularSpecification = (TabularSpecification) iterator
					.next();
			
			List<UILabel> uILabels = new ArrayList<UILabel>();
			
			for (Iterator iterator2 = tabularSpecification.getTableContent().iterator(); iterator2.hasNext();) {
				StringEntry entry = (StringEntry) iterator2.next();
				uILabels.add(new UILabel(entry.getKey(), entry.getValue()));
			}
			
			specification.add(new UIProductSpecification(tabularSpecification.getTitle(), null, uILabels));
		}
		
		for (Iterator iterator = dbProduct.getDescriptiveSpecifications().iterator(); iterator.hasNext();) {
			DescriptiveSpecification descriptiveSpecification = (DescriptiveSpecification) iterator
					.next();
			
			specification.add(new UIProductSpecification(descriptiveSpecification.getTitle(), Arrays.asList(new String[]{descriptiveSpecification.getDescription()}), null));
		}
		
		List<UIOffer> uIOffers = new ArrayList<UIOffer>();
		
		for (Iterator iterator = dbProduct.getOffers().iterator(); iterator.hasNext();) {
			com.mk.beans.Offer offer = (com.mk.beans.Offer) iterator.next();
			uIOffers.add(new UIOffer(offer.getOfferId(), offer.getDescription()));
		}
		
		UIProduct uIProduct = new UIProduct(
				dbProduct.getProductId(),
				dbProduct.getName(),
				new UILink(dbProduct.getBrand(),UIHelper.getCategoryURL(dbProduct.getBrandId())),
				catagories,
				dbProduct.getDescription(),
				dbProduct.getLongDescription(),
				dbProduct.getKeyFeatures(),
				uIVariants,
				uIOffers,
				specification
		);
		return uIProduct;
	}


}
