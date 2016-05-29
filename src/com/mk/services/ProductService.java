package com.mk.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mk.beans.Category;
import com.mk.beans.Product;
import com.mk.beans.ProductFilter;
import com.mk.dao.ProductDao;
import com.mk.uibeans.UIProduct;
import com.mk.uibeans.UISearchResult;
import com.mk.uibeans.UISuggestionItem;
import com.mk.utils.Constants;


@Controller
public class ProductService {
	
	@Autowired
	private ProductDao productDao;
	
		

	
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}	
	
	
	@RequestMapping(value="/product/productDetail.do", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody() ResponseEntity<UIProduct>  productDetail(@RequestParam("productId") String productId) {
		
		 HttpHeaders responseHeaders = new HttpHeaders();
		 responseHeaders.add("Content-Type", "application/json; charset=utf-8");
	
		com.mk.beans.Product product = productDao.getProduct(productId);
		
		if(product == null)
		{
			if("debug".equals(productId))
			{
				return new ResponseEntity<UIProduct>(UIProduct.getProductDummyData(), responseHeaders, HttpStatus.CREATED);
			}
			else{
				return new ResponseEntity<UIProduct>(new UIProduct(), responseHeaders, HttpStatus.CREATED);
			}
			
		}
		
		return  new ResponseEntity<UIProduct>(UIProduct.fillData(productId, product), responseHeaders, HttpStatus.CREATED);

	}
	
	@RequestMapping(value="/product/productSuggestion.do", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody() List<UISuggestionItem>  getProductSearchSuggestion(@RequestParam("term") String queryString) {
		
		
		if("debug".equals(queryString))
		{
			return UISuggestionItem.getProductSuggestionDummyData();
		}
		else{
			
			List<com.mk.beans.Product> products = productDao.searchProduct(Constants.DEFAULT_SEARCH_SIZE, Constants.OPTION_PATTERN_MATCH, queryString.split(" "));
			List<Category> categories = productDao.searchCategories(Constants.DEFAULT_BRAND_SEARCH_SIZE, queryString.split(" "));
			
			return UISuggestionItem.fillData(products, categories, "");
		}
		
		
		

	}
	
	
	@RequestMapping(value="/product/browse.do", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody() ResponseEntity<UISearchResult>  search(@RequestParam("q") String keywords, 
			@RequestParam(value="p", required=false, defaultValue = "1") String page,  
			@RequestParam(value="o", required=false, defaultValue = ""+Constants.OPTION_LOGICAL_OR) String operator,
			@RequestParam(value="f", required=false, defaultValue = "") String filter) {
		
		 HttpHeaders responseHeaders = new HttpHeaders();
		 responseHeaders.add("Content-Type", "application/json; charset=utf-8");
	
		
		if("debug".equals(keywords))
		{
			return new ResponseEntity<UISearchResult>(UISearchResult.getDummyData(), responseHeaders, HttpStatus.CREATED);
		}
		else{
			List<Product> products = productDao.searchCategory(Constants.DEFAULT_LIST_SEARCH_SIZE, Integer.parseInt(operator), Integer.parseInt(page), 
					filter, keywords.split("@"));
			
			Category category = productDao.getCategory(keywords.split("@")[0]);
			
			int totalRows = productDao.searchCategoryCount(Constants.DEFAULT_LIST_SEARCH_SIZE, Integer.parseInt(operator), Integer.parseInt(page), 
					filter, keywords.split("@"));
			List<ProductFilter> filterCategories = productDao.getFilters();
			return new ResponseEntity<UISearchResult>(UISearchResult.fillData(products, filterCategories, category, keywords, page, operator, filter, totalRows),
					responseHeaders, HttpStatus.CREATED);

		}
		
		
		

	}
	


}
