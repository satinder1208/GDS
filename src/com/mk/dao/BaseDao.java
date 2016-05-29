package com.mk.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mk.utils.CacheManager;


@Repository
public class BaseDao {

	@Autowired
	protected MongoTemplate mongoTemplate;
	
	@Autowired
	CacheManager cacheManager;

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

}
