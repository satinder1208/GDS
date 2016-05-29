package com.mk.dao;

import java.util.Date;
import java.util.Iterator;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mk.beans.Address;
import com.mk.beans.Kart;
import com.mk.beans.KartItem;
import com.mk.beans.Order;
import com.mk.beans.OrderItem;
import com.mk.beans.Subscription;
import com.mk.beans.Transaction;
import com.mk.utils.Constants;


@Repository
public class OrderDao extends BaseDao {

	

	public void persistKart(Kart sessionKart) {
		
		Kart existingKart = mongoTemplate.findOne(new Query(Criteria.where("userId").is(sessionKart.getUserId())), Kart.class);
		
		if(existingKart != null)
		{
			mongoTemplate.remove(existingKart);
		}
		
		sessionKart.refreshUUID();
		
		mongoTemplate.save(sessionKart, "kart");
		
	}

	public Kart getKart(String loginId) {
		
		return mongoTemplate.findOne(new Query(Criteria.where("userId").is(loginId)), Kart.class);
		
		
	}

	public void placeOrder(Order order, Transaction transaction) {
		
		mongoTemplate.save(order, "orders");
		mongoTemplate.save(transaction, "transactions");
		
		
	}
	
	public synchronized long getNextOrderId()
	{
		long id = new Date().getTime();
		try {
			wait(1L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public synchronized long getNextTransactionId()
	{
		long id = new Date().getTime();
		try {
			wait(1L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return id;
	}

	public void createSubscription(Subscription subscription) {
		
		mongoTemplate.save(subscription, "subscriptions");
		
		
	}
	

		
	

}
