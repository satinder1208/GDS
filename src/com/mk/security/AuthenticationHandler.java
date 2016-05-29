package com.mk.security;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mk.beans.Kart;
import com.mk.beans.KartItem;
import com.mk.beans.User;
import com.mk.dao.OrderDao;
import com.mk.dao.ProductDao;
import com.mk.dao.UserDao;
import com.mk.uibeans.UILoginResponse;
import com.mk.utils.Constants;

@Service
public class AuthenticationHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler{
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	ProductDao productDao;
	
	public AuthenticationHandler() {
		super();
		
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest req,
			HttpServletResponse res, AuthenticationException ex)
			throws IOException, ServletException {
		
		//TODO
				//update failed login attempt if user exists
		
		HttpSession session = req.getSession();
		session.removeAttribute(Constants.SESSION_USER_KEY);
		
		UILoginResponse uILoginResponse = new UILoginResponse(true, "");
		uILoginResponse.setMessage(ex.getMessage());
		uILoginResponse.setSuccess(false);
		res.setContentType("application/json");
		res.getWriter().write(new ObjectMapper().writer().writeValueAsString(uILoginResponse));
		
		
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest req,
			HttpServletResponse res, Authentication ex) throws IOException,
			ServletException {

		User user = userDao.getUserByUsername(req.getParameter("j_username"));
		user.setLastIP(req.getRemoteAddr());
		user.setLastLogin(new Date());
		
		userDao.save(user);
		
		HttpSession session = req.getSession();
		
		Kart sessionKart = (Kart) session.getAttribute(Constants.SESSION_KART_KEY);
		
		if(sessionKart == null)
		{
			sessionKart = new Kart();
			session.setAttribute(Constants.SESSION_KART_KEY, sessionKart);
			
		}
		
		Kart dbKart = orderDao.getKart(user.getLoginId());
		
		if(dbKart != null)
		{
			for (Iterator iterator = dbKart.getItems().values().iterator(); iterator.hasNext();) {
				KartItem kartItem = (KartItem) iterator.next();
				sessionKart.addItem(kartItem.getVariantId(), kartItem.getQuantity(), productDao.getProduct(kartItem.getVariantId()));
			}
			
			
		}
		
		sessionKart.setUserId(user.getLoginId());
		
		orderDao.persistKart(sessionKart);
		
		
		session.setAttribute(Constants.SESSION_USER_KEY, user);
		
		UILoginResponse uILoginResponse = new UILoginResponse(true, "");
		uILoginResponse.setMessage("Welcome");
		uILoginResponse.setSuccess(true);
		res.setContentType("application/json");
		res.getWriter().write(new ObjectMapper().writer().writeValueAsString(uILoginResponse));
		
	}

}
