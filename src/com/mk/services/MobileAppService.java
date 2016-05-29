package com.mk.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mk.beans.AppResponse;
import com.mk.beans.User;
import com.mk.dao.ProductDao;
import com.mk.dao.UserDao;
import com.mk.security.Security;
import com.mk.uibeans.UILoginResponse;


@Controller
public class MobileAppService {
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private UserDao userDao;
		
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}	
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}	
	
	@RequestMapping(value="/app/authenticate", method=RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody() ResponseEntity<AppResponse<UILoginResponse>>  authenticate(@RequestBody String loginRequest) {
		
	 HttpHeaders responseHeaders = new HttpHeaders();
	 responseHeaders.add("Content-Type", "application/json; charset=utf-8");
	 responseHeaders.add("Access-Control-Allow-Origin", "*");
	 
	 String message = null;
	 JSONObject jsonRequest = null;
	 
	try {
		
		message = Security.decrypt(loginRequest);
		jsonRequest = new JSONObject(message);
		
		if(jsonRequest.getString("username") == null || jsonRequest.getString("password") == null)
		{
			 return new ResponseEntity<AppResponse<UILoginResponse>>(
					 new AppResponse<UILoginResponse>(new UILoginResponse(false, "Bad request"), HttpStatus.NO_CONTENT), 
					 responseHeaders, HttpStatus.NO_CONTENT);
			
		}
		
		if(jsonRequest.getString("username").equals("gd") && jsonRequest.getString("password").equals("test"))
		{
			return new ResponseEntity<AppResponse<UILoginResponse>>(
					new AppResponse<UILoginResponse>(new UILoginResponse(true, "Logged in with demo id"), HttpStatus.OK), 
					 responseHeaders, HttpStatus.OK);
			
		}
		else{
			User user = userDao.authenticate(jsonRequest.getString("username"), jsonRequest.getString("password"));
			if(user != null){
				
				if(user.isActive())
				{
					return new ResponseEntity<AppResponse<UILoginResponse>>(
							new AppResponse<UILoginResponse>(new UILoginResponse(true, "Authenticated successfully"), HttpStatus.OK), 
							 responseHeaders, HttpStatus.OK);
					
				}
				else
				{
					return new ResponseEntity<AppResponse<UILoginResponse>>(
							new AppResponse<UILoginResponse>(new UILoginResponse(true, "User account disabled"), HttpStatus.OK), 
							 responseHeaders, HttpStatus.UNAUTHORIZED);
					
				}
				
				
			}
			else{
			
				return new ResponseEntity<AppResponse<UILoginResponse>>(
						new AppResponse<UILoginResponse>(new UILoginResponse(false, "Invalid credentials"), HttpStatus.NOT_ACCEPTABLE), 
						 responseHeaders, HttpStatus.UNAUTHORIZED);
				
			}
			
			
		}
	
	} catch (Exception e) {
		
		 return new ResponseEntity<AppResponse<UILoginResponse>>(
				 new AppResponse<UILoginResponse>(new UILoginResponse(false, "Bad request"), HttpStatus.BAD_REQUEST), 
				 responseHeaders, HttpStatus.BAD_REQUEST);
		
		
	} 	
}	
	
		
	@RequestMapping(value="/app/signup", method=RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody() ResponseEntity<AppResponse<UILoginResponse>>  signup(@RequestBody String loginRequest) {
			
		 HttpHeaders responseHeaders = new HttpHeaders();
		 responseHeaders.add("Content-Type", "application/json; charset=utf-8");
		 responseHeaders.add("Access-Control-Allow-Origin", "*");
		 
		 String message = null;
		 JSONObject jsonRequest = null;
		 
		try {
			
			message = Security.decrypt(loginRequest);
			jsonRequest = new JSONObject(message);
			
			if(jsonRequest.getString("username") == null || jsonRequest.getString("password") == null)
			{
				 return new ResponseEntity<AppResponse<UILoginResponse>>(
						 new AppResponse<UILoginResponse>(new UILoginResponse(false, "Bad request"), HttpStatus.NO_CONTENT), 
						 responseHeaders, HttpStatus.NO_CONTENT);
				
			}
			
			User user = userDao.getUserByUsername(jsonRequest.getString("username"));
			if(user != null){
				
				return new ResponseEntity<AppResponse<UILoginResponse>>(
						new AppResponse<UILoginResponse>(new UILoginResponse(true, "Username already exist"), HttpStatus.OK), 
						 responseHeaders, HttpStatus.PRECONDITION_FAILED);
				
			}
			else{
				
		
				UILoginResponse uILoginResponse = new UILoginResponse(true, "");
				
				 User bean = new User();
				 bean.setLoginId(jsonRequest.getString("username"));
				 bean.setPassword(jsonRequest.getString("password"));
				 
				 bean.setRoles(new ArrayList<String>(Arrays.asList("USER"))); 
				 
				 userDao.save(bean);
				 
				 uILoginResponse.setSuccess(true);
				 uILoginResponse.setMessage("Automatically logging in...");
				
				return new ResponseEntity<AppResponse<UILoginResponse>>(
						new AppResponse<UILoginResponse>(new UILoginResponse(false, "User account created"), HttpStatus.NOT_ACCEPTABLE), 
						 responseHeaders, HttpStatus.CREATED);
			
			
				
			}
				
		} catch (Exception e) {
			
			 return new ResponseEntity<AppResponse<UILoginResponse>>(
					 new AppResponse<UILoginResponse>(new UILoginResponse(false, "Bad request"), HttpStatus.BAD_REQUEST), 
					 responseHeaders, HttpStatus.BAD_REQUEST);
			
			
		} 	
	}




}
