package com.mk.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mk.beans.Category;
import com.mk.beans.Kart;
import com.mk.beans.KartItem;
import com.mk.beans.Product;
import com.mk.beans.ProductFilter;
import com.mk.beans.QuantityPriceEntry;
import com.mk.beans.Utility;
import com.mk.beans.Variant;
import com.mk.uibeans.UIFullKart;
import com.mk.uibeans.UIKart;
import com.mk.uibeans.UIKartItem;
import com.mk.uibeans.UIMiniKart;
import com.mk.uibeans.UIOffer;
import com.mk.uibeans.UIQuantityPrice;
import com.mk.utils.CacheParser;
import com.mk.utils.Constants;
import com.mk.utils.UIHelper;


@Repository
public class ProductDao extends BaseDao {


	public Product getProduct(String variantId)
	{
		
		return mongoTemplate.findOne(new Query(Criteria.where("variants.item_id").is(variantId)), Product.class);
		
	 }
	
	public List<Category> searchCategories(int fetchSize, String...keys){
		
		String cacheKey = "ProductDao:searchCategories:"+"k:"+Arrays.asList(keys).toString().toLowerCase();
			
		List<Category> response = null;
		
		String cachedEntry = cacheManager.get(cacheKey, true);
		
		if(cachedEntry != null)
		{
			CacheParser<List<Category>> parser = new CacheParser<List<Category>>();
			response = parser.parse(cachedEntry);
		}
		else
		{
			List<Pattern> regex = new ArrayList<Pattern>();
			for (int i = 0; i < keys.length; i++) {
				regex.add(Pattern.compile(keys[i], Pattern.CASE_INSENSITIVE));
			}
			
			
		
			Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(
					Criteria.where("label").in(regex)),
					Aggregation.limit(fetchSize)
					);
			
			response =  mongoTemplate.aggregate(aggregation, "categories", Category.class).getMappedResults();
			cacheManager.put(cacheKey, response, false);
		}
		
		return response;
	}

	public List<Product> searchProduct(int fetchSize, int operator, String...keys){

		String cacheKey = "ProductDao:searchProduct:"+"k:"+Arrays.asList(keys).toString().toLowerCase();
		
		List<Product> response = null;
		
		String cachedEntry = cacheManager.get(cacheKey, true);
		
		if(cachedEntry != null)
		{
			CacheParser<List<Product>> parser = new CacheParser<List<Product>>();
			response = parser.parse(cachedEntry);
		}
		else
		{
			List<Pattern> regex = new ArrayList<Pattern>();
			for (int i = 0; i < keys.length; i++) {
				regex.add(Pattern.compile(keys[i], Pattern.CASE_INSENSITIVE));
			}
			
			ProjectionOperation projection = Aggregation.project().and("product").
	                nested(Aggregation.bind("productId", "productId").
	                        and("name", "name").
	                        and("description", "description").
	                        and("longDescription", "longDescription").
	                        and("brand", "brand").
	                        and("brandId", "brandId").
	                        and("variants", "variants").
	                        and("keyFeatures", "keyFeatures").
	                        and("categories", "categories").
	                        and("offers", "offers").
	                        and("descriptiveSpecifications", "descriptiveSpecifications").
	                        and("tabularSpecifications", "tabularSpecifications")
	                        );
			
			ProjectionOperation mapping = Aggregation.project().and("details.productId").as("productId").
	                and("details.name").as("name").
	                and("details.description").as("description").
	                and("details.longDescription").as("longDescription").
	                and("details.brand").as("brand").
	                and("details.brandId").as("brandId").
	                and("details.variants").as("variants").
	                and("details.keyFeatures").as("keyFeatures").
	                and("details.categories").as("categories").
	                and("details.offers").as("offers").
	                and("details.descriptiveSpecifications").as("descriptiveSpecifications").
	                and("details.tabularSpecifications").as("tabularSpecifications");
	                        
	                        
			Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(
					Criteria.where("keys").in(regex)), 
					Aggregation.unwind("keys"),
					Aggregation.match(Criteria.where("keys").in(regex)),
					projection,
					Aggregation.group("product.productId").count().as("relatedTags").addToSet("product").as("details"),
					Aggregation.sort(Direction.DESC, "relatedTags"),
					Aggregation.unwind("details"),
					mapping,
					Aggregation.limit(fetchSize)
					); 
			
			response = mongoTemplate.aggregate(aggregation, "product", Product.class).getMappedResults();
			cacheManager.put(cacheKey, response, false);
			
		}
		
		return response;
		
	}

	public List<Product> searchCategory(int fetchSize, int operator, int page, String filterString, String...categoryIds){
		
		String cacheKey = "ProductDao:searchCategory:"+"f:"+Arrays.asList(filterString).toString().toLowerCase()
			+"|c:"+Arrays.asList(categoryIds).toString().toLowerCase();
			
			List<Product> response = null;
			
			String cachedEntry = cacheManager.get(cacheKey, true);
			
			if(cachedEntry != null)
			{
				CacheParser<List<Product>> parser = new CacheParser<List<Product>>();
				response = parser.parse(cachedEntry);
			}
			else
			{
	
				ProjectionOperation projection = Aggregation.project().and("productId").as("productId").
		                and("name").as("name").
		                and("description").as("description").
		                and("longDescription").as("longDescription").
		                and("brand").as("brand").
		                and("brandId").as("brandId").
		                and("variants").as("variant").
		                and("keyFeatures").as("keyFeatures").
		                and("categories").as("categories").
		                and("offers").as("offers").
		                and("descriptiveSpecifications").as("descriptiveSpecifications").
		                and("tabularSpecifications").as("tabularSpecifications");
		                        
				List<String> brandIds = null;
		//		List<DBObject> priceFilters = new ArrayList<DBObject>();
				boolean subscriptionFilter = false;
				
				if(filterString != null && filterString.length() > 0)
				{
					String[] filters = filterString.split("@");
					
					if(filters.length % 2 != 0)
						throw new RuntimeException("Invalid filters");
					
					for (int i = 0; i < filters.length; i = i + 2) {
						switch (filters[i]) {
						case "b":
								brandIds = Arrays.asList(filters[i+1].split("\\|"));
							break;
							
		//				case "p":
		//						String[] priceFilterStrings = filters[i+1].split("\\|");
		//						
		//						for (int j = 0; j < priceFilterStrings.length; j++) {
		//							String [] range = priceFilterStrings[j].split("_");
		//							
		//							if(range.length != 2)
		//								throw new RuntimeException("Invalid price range");
		//							
		//							priceFilters.add(new BasicDBObject("variants.price.sellingPrice", new BasicDBObject("$gte", Double.parseDouble(range[0]))
		//							.append("$lte", Double.parseDouble(range[1]))));
		//						}
		//						
		//				break;
						
						case "s":
							if(filters[i+1].equals("T"))
								subscriptionFilter = true;
							
							break;
		
						default:
							break;
						}
					}
				}
				
				
				Criteria criteria = null;
				if(operator == Constants.OPTION_LOGICAL_AND)
				{
					criteria = Criteria.where("categories.categoryId").all(Arrays.asList(categoryIds));
				}
				else
				{
					criteria = Criteria.where("categories.categoryId").in(Arrays.asList(categoryIds));
				}
				
				
				if(brandIds != null)
				{
					criteria.and("brandId").in(brandIds);
				}
				
				Criteria vCriteria = null;
				
				if(subscriptionFilter)
				{
					vCriteria = Criteria.where("variants.canBeSubscribed.value").is(true);
				}
				
				Aggregation aggregation = null;
				
				if(vCriteria != null)
					{
					aggregation = Aggregation.newAggregation(Aggregation.match(
							criteria), 
							Aggregation.unwind("variants"),
							Aggregation.match(vCriteria),
							Aggregation.skip(fetchSize * (page - 1)),
							Aggregation.limit(fetchSize),
							projection
							); 
					}
				else{
					aggregation = Aggregation.newAggregation(Aggregation.match(criteria), 
							Aggregation.unwind("variants"),
							Aggregation.skip(fetchSize * (page - 1)),
							Aggregation.limit(fetchSize),
							projection
							); 
				}
						
				
				response = mongoTemplate.aggregate(aggregation, "product", Product.class).getMappedResults();
				cacheManager.put(cacheKey, response, false);
				
			}
			
			return response;
	}

	public int searchCategoryCount(int fetchSize, int operator, int page, String filterString, String...categoryIds){
		
		
		String cacheKey = "ProductDao:searchCategoryCount:"+"f:"+Arrays.asList(filterString).toString().toLowerCase()
				+"|c:"+Arrays.asList(categoryIds).toString().toLowerCase();
				
				int response = 0;
				
				String cachedEntry = cacheManager.get(cacheKey, true);
				
				if(cachedEntry != null)
				{
					CacheParser<Integer> parser = new CacheParser<Integer>();
					response = parser.parse(cachedEntry);
				}
				else
				{
           
			List<String> brandIds = null;
	//		List<DBObject> priceFilters = new ArrayList<DBObject>();
			boolean subscriptionFilter = false;
			
			if(filterString != null && filterString.length() > 0)
			{
				String[] filters = filterString.split("@");
				
				if(filters.length % 2 != 0)
					throw new RuntimeException("Invalid filters");
				
				for (int i = 0; i < filters.length; i = i + 2) {
					switch (filters[i]) {
					case "b":
							brandIds = Arrays.asList(filters[i+1].split("\\|"));
						break;
						
	//				case "p":
	//						String[] priceFilterStrings = filters[i+1].split("\\|");
	//						
	//						for (int j = 0; j < priceFilterStrings.length; j++) {
	//							String [] range = priceFilterStrings[j].split("_");
	//							
	//							if(range.length != 2)
	//								throw new RuntimeException("Invalid price range");
	//							
	//							priceFilters.add(new BasicDBObject("variants.price.sellingPrice", new BasicDBObject("$gte", Double.parseDouble(range[0]))
	//							.append("$lte", Double.parseDouble(range[1]))));
	//						}
	//						
	//				break;
					
					case "s":
						if(filters[i+1].equals("T"))
							subscriptionFilter = true;
						
						break;
	
					default:
						break;
					}
				}
			}
			
			
			Criteria criteria = null;
			if(operator == Constants.OPTION_LOGICAL_AND)
			{
				criteria = Criteria.where("categories.categoryId").all(Arrays.asList(categoryIds));
			}
			else
			{
				criteria = Criteria.where("categories.categoryId").in(Arrays.asList(categoryIds));
			}
			
			
			if(brandIds != null)
			{
				criteria.and("brandId").in(brandIds);
			}
			
			Criteria vCriteria = null;
			
			if(subscriptionFilter)
			{
				vCriteria = Criteria.where("variants.canBeSubscribed.value").is(true);
			}
		
			Aggregation aggregation =  null;
			
			if(vCriteria != null)
			{
				aggregation = Aggregation.newAggregation(Aggregation.match(criteria), 
						Aggregation.unwind("variants"),
						Aggregation.match(vCriteria),
						Aggregation.group().count().as("total")
						
						); 
			}
		else{
				aggregation = Aggregation.newAggregation(Aggregation.match(criteria), 
					Aggregation.unwind("variants"),
					Aggregation.group().count().as("total")
					
					); 
		}
			
	     
			
			List<Utility> utilities = mongoTemplate.aggregate(aggregation, "product", Utility.class).getMappedResults();
			
			if(utilities.size() == 0)
			{
				response = 0;
			}
			else{
				response = utilities.get(0).getTotal();
			}
			
			cacheManager.put(cacheKey, response, false);
			
		}
		
		return response;
	}
	

	public UIKart prepareCartResponse(Kart sessionKart) {
		
		UIFullKart response = new UIFullKart();
		
		List<UIKartItem> uIKartItems = new ArrayList<UIKartItem>();
		
		response.setCartItems(uIKartItems);
		
		response.setKartUUID(sessionKart.getKartUUID());
		
		for (Iterator iterator = sessionKart.getItems().values().iterator(); iterator.hasNext();) {
			
			KartItem kartItem = (KartItem) iterator.next();
			Product product = getProduct(kartItem.getVariantId());
			Variant selectedVariant = product.getVariant(kartItem.getVariantId());
			if(selectedVariant != null)
			{
				
				List<UIQuantityPrice> uIQuantityPrice = new ArrayList<UIQuantityPrice>();
				
				for (Iterator iterator2 = ((com.mk.beans.QuantityPrice)selectedVariant.getPrice().get(0)).getValue().iterator(); iterator2
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
				
				List<UIOffer> variantOffers = new ArrayList<UIOffer>();
				
				for (Iterator iterator3 = selectedVariant.getOffers().iterator(); iterator3.hasNext();) {
					com.mk.beans.Offer offer = (com.mk.beans.Offer) iterator3.next();
					variantOffers.add(new UIOffer(offer.getOfferId(), offer.getDescription()));
				}
				
				
				List<UIOffer> productOffers = new ArrayList<UIOffer>();
				
				for (Iterator iterator2 = product.getOffers().iterator(); iterator2.hasNext();) {
					com.mk.beans.Offer offer = (com.mk.beans.Offer) iterator2.next();
					productOffers.add(new UIOffer(offer.getOfferId(), offer.getDescription()));
				}
				
				
				
				UIKartItem uIKartItem = new UIKartItem(selectedVariant.getImageURL().getThumbnail(), selectedVariant.getItem_id(), 
						UIHelper.getProductURL(selectedVariant.getItem_id()), selectedVariant.getName(), 
						uIQuantityPrice, kartItem.getQuantity()+"", productOffers, variantOffers);
				
				uIKartItems.add(uIKartItem);
			}
			
		}
		
		return response;
		
	}


	public UIKart prepareMiniCartResponse(Kart sessionKart) {
		
		UIMiniKart response = new UIMiniKart();
		response.setKartUUID(sessionKart.getKartUUID());
		response.setNoOfItems(sessionKart.getNoOfItem());
		return response;
	}
	
	public List<ProductFilter> getFilters()
	{
		
		return mongoTemplate.find(new Query(Criteria.where("filterCategoryName").in(Arrays.asList("Subscription", "Brand"))), 
				ProductFilter.class, "settings");
	}

	public Category getCategory(String categoryId) {
		
		return mongoTemplate.findOne(new Query(Criteria.where("categoryId").is(categoryId)), Category.class);
	}

}
