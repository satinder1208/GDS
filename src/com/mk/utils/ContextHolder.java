package com.mk.utils;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ContextHolder implements ApplicationContextAware{

private static ApplicationContext context = null;

private static Map<String, Object> userLocks = Collections.synchronizedMap(new WeakHashMap<String, Object>());

	
	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		
		this.context =context;

	}
	
	public static ApplicationContext getApplicationContext()
	{
		
		return context;
	}
	
	public static Object getUserLock(String userId)
	{
		Object lock = userLocks.get(userId);
		
		if(lock == null)
		{
			lock = new Object();
			userLocks.put(userId, lock);
			
		}
		
		return lock;
	}
}
