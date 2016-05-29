package com.mk.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mk.beans.User;
import com.mk.dao.UserDao;
import com.mk.uibeans.UILoginResponse;
import com.mk.utils.MailService;

@Controller
public class LoginService {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	MailService mailService;
	
	
	@RequestMapping(value="/sendPasswordLink.do", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody() UILoginResponse sendPasswordLink(@RequestParam("emailForgetPassword") String email){
		
		UILoginResponse uILoginResponse = new UILoginResponse(true,"");
		
		User user = userDao.getUserByUsername(email);
		
		if(user != null)
		{
			mailService.sendMail(email, "Morning Kart - Password", "Greetings,\n\n Your password details are as follows.\nUsername: "+email+"\nPassword: "+user.getPassword());
			uILoginResponse.setSuccess(true);
			uILoginResponse.setMessage("Password sent successfully");
		}
		else
		{
			uILoginResponse.setSuccess(false);
			uILoginResponse.setMessage("User does not exist");
		}
		
		
		return uILoginResponse;
	}

	@RequestMapping(value="/registerNewUser.do", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody() UILoginResponse  registerNewUser(@RequestParam("newUserEmail") String email, @RequestParam("newUserPassword") String password ) {
		
		UILoginResponse uILoginResponse = new UILoginResponse(true, "");
		
		 User bean = new User();
		 bean.setLoginId(email);
		 bean.setPassword(password);
		 bean.setPrimaryEmail(email);
		 bean.setCreationTimestamp(new Date());
		 bean.setScreenName(email);
		 bean.setRoles(new ArrayList<String>(Arrays.asList("USER"))); 
		 
		 userDao.save(bean);
		 
		 uILoginResponse.setSuccess(true);
		 uILoginResponse.setMessage("Automatically logging in...");
		
		return uILoginResponse;
	}
}
