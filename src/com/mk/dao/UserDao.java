package com.mk.dao;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mk.beans.User;


@Repository
public class UserDao extends BaseDao {

	public User getUserByUsername(String username)
	{
		
		return mongoTemplate.findOne(new Query(Criteria.where("loginId").is(username)), User.class);
		
		
	 }
	
	public User authenticate(String username, String password)
	{
		
		return mongoTemplate.findOne(new Query(Criteria.where("loginId").is(username).and("password").is(password)), User.class);
		
		
	 }
	
	public void save(User user) {
		
		mongoTemplate.save(user, "users");

	}
	
	
	

}
