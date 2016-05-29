package com.mk.utils;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mk.dao.UtilDao;
import com.mk.security.SystemException;
import com.sun.mail.util.BASE64EncoderStream;

@Service
public class CacheManager {
	
	private HashMap<String, String> cache = new HashMap<String, String>();
	
	private static final boolean TURN_CACHE_OFF = false;
	
	Thread cleanerThread = null;
	
	@Autowired
	private UtilDao utilDao;
	
	public void clearCache()
	{
		System.out.println("******************Clearing Cache***********************");
		cache.clear();
		utilDao.clearCache();
	}
	
	//@PostConstruct
	public void startCleaner()
	{
		if(cleanerThread != null && cleanerThread.isAlive())
		{
			return;
		}
		else
		{
			cleanerThread = new Thread(){
				
				@Override
				public void run() {
					synchronized (this) {
						try {
							wait(1000 * 60 * 60);
							
							if(new Date().getHours() == 1)
							{
								clearCache();
							}
							
							
						} catch (Throwable e) {
							System.out.println("************ERROR: Alert: CacheManager cleaner thread is causing problem.**************"+e.getMessage());
						}
					}
				}
			};
			cleanerThread.setDaemon(true);
			cleanerThread.start();
		}
	}
	
	public String get(String key, boolean useApplicationCache)
	{
		if(TURN_CACHE_OFF)
		{
			return null; 
		}
		
		String entry = null;
		
		if(useApplicationCache)
		{
			entry = cache.get(key);
		}
		
		if(entry == null)
		{
			entry = utilDao.getCachedEntry(key);
			
			if(useApplicationCache)
			{
				cache.put(key, entry);
			}
			
		}
		
		return entry;
	}
	
	public void put(String key, Object entryObject, boolean useApplicationCache)
	{
		if(TURN_CACHE_OFF)
		{
			return; 
		}
		
		try{
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(entryObject);
			String entry = new String(BASE64EncoderStream.encode( bos.toByteArray()));
			oos.close();
			utilDao.saveCacheEntry(key, entry);
			
			if(useApplicationCache)
			{
				cache.put(key, entry);
			}
		} catch (Exception e) {
			throw new SystemException(e.getMessage());
		}
		
		
	}
	
	
	
	
	

}
