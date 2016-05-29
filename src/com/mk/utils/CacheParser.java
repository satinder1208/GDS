package com.mk.utils;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import com.mk.security.SystemException;
import com.sun.mail.util.BASE64DecoderStream;


public class CacheParser<T> {

	public T parse(String cachedEntry) {
		
		T response = null;
		
		try {
	    	byte [] data = BASE64DecoderStream.decode(cachedEntry.getBytes());
			ByteArrayInputStream bis = new ByteArrayInputStream(data);
			ObjectInputStream ois = new ObjectInputStream(bis);
			response = (T) ois.readObject();	
			ois.close();
		} catch (Exception e) {
			throw new SystemException(e.getMessage());
		}
		
		return response;
	}

}
