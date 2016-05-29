package com.mk.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.mk.beans.Category;
import com.mk.utils.CacheParser;
import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;


@Repository
public class UtilDao extends BaseDao{

	public void clearCache() {

		mongoTemplate.getCollection("cache").remove(new BasicDBObject());
		
	}

	public String getCachedEntry(String key) {
		
		String response = null;
		
		DBCursor cursor = mongoTemplate.getCollection("cache").find(new BasicDBObject("key", key));
		
		try{
			if(cursor.hasNext())
			{
				DBObject dbObject = cursor.next();
				response = dbObject.get("entry").toString();
				
			}
		}
		finally
		{
			cursor.close();
		}
		
		
		
		return response;
	}

	public void saveCacheEntry(String key, String entry) {

		mongoTemplate.getCollection("cache").insert(new BasicDBObject("key",key).append("entry",entry));
		
	}

	public String getMainMenu() {
	
		String cacheKey = "UtilDao:getMainMenu";
		
		String response = null;
		String cachedEntry = cacheManager.get(cacheKey, true);
		
		if(cachedEntry != null)
		{
			CacheParser<String> parser = new CacheParser<String>();
			response = parser.parse(cachedEntry);
		}
		else
		{
		
			Cursor cursor = mongoTemplate.getCollection("mainMenu").find(new BasicDBObject(),new BasicDBObject("_id",0));
			
			List<DBObject> result = new ArrayList<DBObject>();
			
			try{
				
				while(cursor.hasNext()){
					
					DBObject dbObject = cursor.next();
		            result.add(dbObject);
		            
				}
			}
			finally
			{
				cursor.close();
			}
			
			response = "["+StringUtils.join(result.toArray(), ",")+"]";
			
			cacheManager.put(cacheKey, response, true);
		}
		
			
		
		
		return response;
	 }
	
	

}
