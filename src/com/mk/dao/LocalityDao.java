package com.mk.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mk.beans.Locality;
import com.mk.utils.CacheParser;


@Repository
public class LocalityDao extends BaseDao{

	
//	public String find(int fetchSize, String...keys)
//	{
//	
//		String cacheKey = "LocalityDao:find:"+"k:"+Arrays.asList(keys).toString().toLowerCase();
//		
//		String response = cacheManager.get(cacheKey, false);
//		
//		if(response == null)
//		{
//			List<Pattern> paterns = new ArrayList<Pattern>();
//			 
//			 for (int i = 0; i < keys.length; i++) {
//				 paterns.add(Pattern.compile(keys[i], Pattern.CASE_INSENSITIVE));
//			 }
//			
//			DBObject match = new BasicDBObject("$match", new BasicDBObject("keys", new BasicDBObject("$in", paterns)).append("isActive", "true") );
//
//			DBObject fields = new BasicDBObject("address", 1);
//			fields.put("_id", "$address");
//			DBObject project = new BasicDBObject("$project", fields );
//
//			DBObject groupFields = new BasicDBObject( "_id", new BasicDBObject("address", "$address"));
//			groupFields.put("relatedTags", new BasicDBObject( "$sum", 1));
//			DBObject group = new BasicDBObject("$group", groupFields);
//
//			DBObject limit = new BasicDBObject("$limit", fetchSize);
//
//			DBObject sort = new BasicDBObject("$sort", new BasicDBObject("relatedTags", -1));
//			
//			DBObject finalFields = new BasicDBObject("_id", 0);
//			finalFields.put("address", "$_id.address");
//			DBObject finalProject = new BasicDBObject("$project", finalFields );
//			
//			
//			List<DBObject> pipeline = Arrays.asList(match, new BasicDBObject("$unwind", "$keys" ), match, project, group, limit, sort, finalProject);
//			
//			AggregationOptions aggregationOptions = AggregationOptions.builder().batchSize(100).outputMode(AggregationOptions.OutputMode.CURSOR).allowDiskUse(true).build();
//
//			Cursor cursor = mongoTemplate.getCollection("locality").aggregate(pipeline, aggregationOptions);
//			
//			List<DBObject> result = new ArrayList<DBObject>();
//			
//			
//			try{
//				
//				while(cursor.hasNext()){
//					
//					DBObject dbObject = cursor.next();
//		            result.add(dbObject);
//		            
//				}
//			}
//			finally
//			{
//				cursor.close();
//			}
//			
//			response = "["+StringUtils.join(result.toArray(), ",")+"]";
//			
//			cacheManager.put(cacheKey, response, false);
//		}
//		
//			
//		
//		
//		return response;
//	 }
	
	public List<Locality> searchLocality(int fetchSize, String...keys){

		String cacheKey = "LocalityDao:find:"+"k:"+Arrays.asList(keys).toString().toLowerCase();
		
		List<Locality> response = null;
		
		String cachedEntry = cacheManager.get(cacheKey, true);
		
		if(cachedEntry != null)
		{
			CacheParser<List<Locality>> parser = new CacheParser<List<Locality>>();
			response = parser.parse(cachedEntry);
		    
		}
		else
		{
			List<Pattern> regex = new ArrayList<Pattern>();
			for (int i = 0; i < keys.length; i++) {
				regex.add(Pattern.compile(keys[i], Pattern.CASE_INSENSITIVE));
			}
			
			ProjectionOperation projection = Aggregation.project().and("locality").
	                nested(Aggregation.bind("localityId", "localityId").
	                        and("areaId", "areaId").
	                        and("zoneId", "zoneId").
	                        and("displayName", "displayName").
	                        and("address", "address").
	                        and("description", "description").
	                        and("society", "society").
	                        and("area", "area").
	                        and("city", "city").
	                        and("pincode", "pincode").
	                        and("state", "state").
	                        and("isActive", "isActive").
	                        and("imageURL", "imageURL")
	                        );
			
			ProjectionOperation mapping = Aggregation.project().and("details.localityId").as("localityId").
	                and("details.areaId").as("areaId").
	                and("details.zoneId").as("zoneId").
	                and("details.displayName").as("displayName").
	                and("details.address").as("address").
	                and("details.description").as("description").
	                and("details.society").as("society").
	                and("details.area").as("area").
	                and("details.city").as("city").
	                and("details.pincode").as("pincode").
	                and("details.state").as("state").
	                and("details.isActive").as("isActive").
	                and("details.imageURL").as("imageURL");
	                        
	                        
			Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(
					Criteria.where("keys").in(regex)), 
					Aggregation.unwind("keys"),
					Aggregation.match(Criteria.where("keys").in(regex)),
					projection,
					Aggregation.group("locality.localityId").count().as("relatedTags").addToSet("locality").as("details"),
					Aggregation.sort(Direction.DESC, "relatedTags"),
					Aggregation.unwind("details"),
					mapping,
					Aggregation.limit(fetchSize)
					); 
			
			response = mongoTemplate.aggregate(aggregation, "locality", Locality.class).getMappedResults();
			cacheManager.put(cacheKey, response, false);

		}
		
		return response;
		
	}

	public Locality getLocality(String localityId)
	{
		
		return mongoTemplate.findOne(new Query(Criteria.where("localityId").is(localityId)), Locality.class);
		
		
	 }
	

}
