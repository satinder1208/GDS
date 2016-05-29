package com.mk.services;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;

@Controller
public class AccountService {

	@RequestMapping(value="/account/{type}")
	public ModelAndView addSubscriptionHtml(@PathVariable("type") String type, HttpSession session){
		
		return new ModelAndView();
	}

}
