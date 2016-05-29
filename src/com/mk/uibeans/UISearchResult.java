package com.mk.uibeans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.mk.beans.Category;
import com.mk.beans.Product;
import com.mk.beans.ProductFilter;
import com.mk.beans.ProductFilterItem;
import com.mk.beans.QuantityPriceEntry;
import com.mk.beans.Variant;
import com.mk.utils.Constants;
import com.mk.utils.UIHelper;

public class UISearchResult {
	
	private UISearchData data;
	private List<ProductFilter> filterCategories;
	List<UILink> catagories = new ArrayList<UILink>();
	
	public UISearchData getData() {
		return data;
	}
	public void setData(UISearchData data) {
		this.data = data;
	}
	public List<ProductFilter> getFilterCategories() {
		return filterCategories;
	}
	public void setFilterCategories(List<ProductFilter> filterCategories) {
		this.filterCategories = filterCategories;
	}
	
	public static UISearchResult getDummyData() {
		
		UISearchResult uiSearchResult = new UISearchResult();
		List<ProductFilter> filterCategories = new ArrayList<ProductFilter>();
		
		ProductFilter filter1 = new ProductFilter();
		filter1.setFilterCategoryName("Price");
		filter1.setKey("p");
		
		ProductFilterItem item1 = new ProductFilterItem();
		item1.setName("Rs 1 to Rs 50");
		item1.setValue("1_50");
		
		ProductFilterItem item2 = new ProductFilterItem();
		item2.setName("Rs 51 to Rs 100");
		item2.setValue("51_100");
		
		ProductFilterItem item3 = new ProductFilterItem();
		item3.setName("Rs 100 to Rs 200");
		item3.setValue("100_200");
		
		ProductFilterItem item4 = new ProductFilterItem();
		item4.setName("Rs 200 to Rs 500");
		item4.setValue("200_500");
		
		ProductFilterItem item5 = new ProductFilterItem();
		item5.setName("Rs 500 to Rs 1000");
		item5.setValue("500_1000");
		
		filter1.setFilters(Arrays.asList(new ProductFilterItem[]{item1, item2, item3, item4, item5}));
		filterCategories.add(filter1);
		
		ProductFilter filter2 = new ProductFilter();
		filter2.setFilterCategoryName("Subscription");
		filter2.setKey("s");
		
		ProductFilterItem item6 = new ProductFilterItem();
		item6.setName("Regular delivery items");
		item6.setValue("T");
		
		
		filter2.setFilters(Arrays.asList(new ProductFilterItem[]{item6}));
		filterCategories.add(filter2);
		
		ProductFilter filter3 = new ProductFilter();
		filter3.setFilterCategoryName("Brand");
		filter3.setKey("b");
		
		ProductFilterItem item7 = new ProductFilterItem();
		item7.setName("Amul");
		item7.setValue("C1001");
		
		ProductFilterItem item8 = new ProductFilterItem();
		item8.setName("Mother Dairy");
		item8.setValue("C1005");
		
		
		filter3.setFilters(Arrays.asList(new ProductFilterItem[]{item7, item8}));
		filterCategories.add(filter3);
		
		
		UISearchData uiSearchData = new UISearchData();
		
		uiSearchData.setCurrentPage(1);
		uiSearchData.setNextPage("q=debug&o=4&p=2");
		uiSearchData.setParameters("q=debug&o=4");
		uiSearchData.setTotalPages(3);
		uiSearchData.setTotalRows(30);
		
		
		List<UIImage> images1 = new ArrayList<UIImage>();
		images1.add(new UIImage("resources/image/amul_milk_image_1_400_400.jpg", "resources/image/amul_milk_fc1_image_1_100_100.jpg"));
		
		List<UIVariant> products = new ArrayList<UIVariant>();
		List<UIQuantityPrice> quantityPriceList1 = new ArrayList<UIQuantityPrice>();
		quantityPriceList1.add(new UIQuantityPrice("0", "1 Leter", "48", "48",null));
		quantityPriceList1.add(new UIQuantityPrice("1", "2 Leter", "96", "98",null));
		quantityPriceList1.add(new UIQuantityPrice("2", "3 Leter", "144", "143.5",null));
		quantityPriceList1.add(new UIQuantityPrice("3", "4 Leter", "191", "190", null));
		UIVariant var1 = new UIVariant("P1001V1", "Amul Full Cream 500 ml", null, true, false, quantityPriceList1, images1, null, null);
		
		products.add(var1);
		
		List<UIImage> images2 = new ArrayList<UIImage>();
		images2.add(new UIImage("resources/image/amul_milk_image_1_400_400.jpg", "resources/image/amul_milk_fc1_image_1_100_100.jpg"));
		
		List<UIQuantityPrice> quantityPriceList2 = new ArrayList<UIQuantityPrice>();
		quantityPriceList2.add(new UIQuantityPrice("0", "1 Leter", "48", "48",null));
		quantityPriceList2.add(new UIQuantityPrice("1", "2 Leter", "96", "98",null));
		quantityPriceList2.add(new UIQuantityPrice("2", "3 Leter", "144", "143.5",null));
		quantityPriceList2.add(new UIQuantityPrice("3", "4 Leter", "191", "190", null));
		UIVariant var2 = new UIVariant("P1001V1", "Amul Full Cream 1 litre", null, true, false, quantityPriceList2, images2, null, null);
		
		products.add(var2);
		
		
		
		
		uiSearchData.setProducts(products);
		
		uiSearchResult.setData(uiSearchData);
		uiSearchResult.setFilterCategories(filterCategories);
		
		return uiSearchResult;
	}

	public static UISearchResult fillData(List<Product> products, List<ProductFilter> filterCategories, Category selectedCategory, 
			String keywords, String page, String operator, String filter, int totalRows)
	{
		UISearchResult uiSearchResult = new UISearchResult();
		UISearchData uiSearchData = new UISearchData();
		
		uiSearchData.setCurrentPage(Integer.parseInt(page));
		uiSearchData.setNextPage("q="+keywords+"&o="+operator+"&p="+(Integer.parseInt(page)+1)+(filter == null || filter.length() == 0 ? "" : "&f="+filter));
		uiSearchData.setParameters("q="+keywords+"&o="+operator+"&p="+page+(filter == null || filter.length() == 0 ? "" : "&f="+filter));
		uiSearchData.setTotalRows(totalRows);
		
		List<UIVariant> uIVariants = new ArrayList<UIVariant>();

		
		for (Iterator<Product> iterator = products.iterator(); iterator.hasNext();) {
			Product product = iterator.next();
			Variant variant = product.getVariant();
			
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
			
			uIVariants.add(new UIVariant(variant.getItem_id(), variant.getName(), variant.getDescription(), variant.getCanBeSubscribed().get(0).getValue(), false, 
					uIQuantityPrice, uIImages, uIOffers, variant.getKeyFeatures()));
			
		}
		
		uiSearchData.setProducts(uIVariants);
		uiSearchResult.setData(uiSearchData);
		
		uiSearchResult.setFilterCategories(filterCategories);
		
		int totalPages = 
				((totalRows - (totalRows % Constants.DEFAULT_LIST_SEARCH_SIZE) ) / Constants.DEFAULT_LIST_SEARCH_SIZE) + 
				((totalRows % Constants.DEFAULT_LIST_SEARCH_SIZE) == 0 ? 0: 1);
										
		uiSearchData.setTotalPages(totalPages);
		
		List<UILink> uiCategories = new ArrayList<UILink>();
		
		if(selectedCategory.getRelatedCategories() != null)
		{
			for (Iterator<Category> iterator = selectedCategory.getRelatedCategories().iterator(); iterator.hasNext();) {
				Category category = iterator.next();
				uiCategories.add(new UILink(category.getLabel(), UIHelper.getCategoryURL(category.getCategoryId())));
			}
		}
		
		uiCategories.add(new UILink(selectedCategory.getLabel(), UIHelper.getCategoryURL(selectedCategory.getCategoryId())));
		uiSearchResult.setCatagories(uiCategories);
		
		return uiSearchResult;
	}
	public List<UILink> getCatagories() {
		return catagories;
	}
	public void setCatagories(List<UILink> catagories) {
		this.catagories = catagories;
	}
}
