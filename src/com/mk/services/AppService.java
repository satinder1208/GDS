package com.mk.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mk.dao.UtilDao;

@Controller
public class AppService{

	@Autowired
	private UtilDao utilDao;
	

	public String getMainMenu() {
	  
		 
		return utilDao.getMainMenu();		
		
	}
	
	
	public void setUtilDao(UtilDao utilDao) {
		this.utilDao = utilDao;
	}

	
}
