package com.mk.utils;

import java.text.SimpleDateFormat;

public class UIHelper {
	
	private static ThreadLocal<SimpleDateFormat> dateFormatterPool = new ThreadLocal<SimpleDateFormat>(){
		 @Override
	        protected SimpleDateFormat initialValue()
	        {
	            return new SimpleDateFormat("dd/MM/yyyy");
	        }
	};
	
	public static String getCategoryURL(String categoryCode)
	{
		return "/mk/pSearch?q="+categoryCode+"&o=4&p=1";
	}

	public static String getImageURL(String imageName) {
		
		return "http://mk.com:8080/mk/resources/image/"+imageName;
	}

	public static String getProductURL(String item_id) {

		return  "/mk/pView?q="+item_id;
	}

	public static String getProductJsonURL(String item_id, String client) {
		return "/app/product/productDetail.do?productId="+item_id;
	}

	public static String getCategoryJsonURL(String categoryId, String client) {
		return "/app/product/browse.do?q="+categoryId+"&o=4&p=1";
	}

	public static ThreadLocal<SimpleDateFormat> getDateFormatterPool() {
		return dateFormatterPool;
	}

}
