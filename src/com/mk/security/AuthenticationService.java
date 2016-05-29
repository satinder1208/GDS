package com.mk.security;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mk.dao.UserDao;

@Service
public class AuthenticationService implements UserDetailsService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		com.mk.beans.User user = userDao.getUserByUsername(username);
		
		if(user != null)
		{
			for (Iterator<String> iterator = user.getRoles().iterator(); iterator.hasNext();) {
				String role = iterator.next();
				authorities.add(new GrantedAuthorityImpl("ROLE_"+role));
			}
		
			User userDetail = new User(user.getLoginId(),user.getPassword(),user.isActive(),true,true,true,authorities);
			
			
			return userDetail;
		}
		else{
			throw new UsernameNotFoundException("User does not exist. Please sign-up to login.");
		}
	
		
	}
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	
}
