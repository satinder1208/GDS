package com.mk.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mk.beans.Address;
import com.mk.beans.Locality;
import com.mk.beans.User;
import com.mk.dao.LocalityDao;
import com.mk.dao.OrderDao;
import com.mk.dao.UserDao;
import com.mk.uibeans.UIAddress;
import com.mk.uibeans.UIAddressResponse;
import com.mk.uibeans.UIUser;
import com.mk.utils.Constants;

@Controller
public class UserService {
	
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private UserDao userDao;

	@Autowired
	private LocalityDao localityDao;


	@RequestMapping(value="/addEditAddress.do", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody() UIAddressResponse addEditAddress(HttpSession session,
			@RequestParam("name") String name,
			@RequestParam("mobile") String mobile,
			@RequestParam("flatNo") String flatNo,
			@RequestParam("localityId") String localityId,
			@RequestParam("addressId") String addressId,
			@RequestParam("action") String action){
		
		UIAddressResponse addressResponse = new UIAddressResponse();
		if(action.equalsIgnoreCase("edit")){
			User user = (User) session.getAttribute(Constants.SESSION_USER_KEY);
			 if(user != null)
			 {
				 if(user.getAddresses()!= null)
				 {
					 for (Iterator iterator = user.getAddresses().iterator(); iterator
							.hasNext();) {
						Address address = (Address) iterator.next();
						if(address.getAdressId().equals(addressId))
						{
							address.setName(name);
							address.setMobile(mobile);
							address.setAddressLine1(flatNo);
							address.setLocalityId(localityId);
							Locality locality = localityDao.getLocality(localityId);
							address.setLocalityName(locality.getAddress());
						}
					}
				 }
				 
				 userDao.save(user);
			 }
			addressResponse.setMessage("Address edited successfully");
			addressResponse.setSuccess(true);
		}else{
			User user = (User) session.getAttribute(Constants.SESSION_USER_KEY);
			 if(user != null)
			 {
				 Address newAddress = new Address();
				 newAddress.setName(name);
				 newAddress.setMobile(mobile);
				 newAddress.setAdressId(UUID.randomUUID().toString());
				 newAddress.setAddressLine1(flatNo);
				 newAddress.setLocalityId(localityId);
				 Locality locality = localityDao.getLocality(localityId);
				 newAddress.setLocalityName(locality.getAddress());
				 
				 if(user.getAddresses() == null)
				 {
					 user.setAddresses(new ArrayList<Address>());
				 }
				 user.getAddresses().add(newAddress);
				 
				 userDao.save(user);
			 }
			addressResponse.setMessage("Address added successfully");
			addressResponse.setAddressId("address007");
			addressResponse.setSuccess(true);
		}
		
		return addressResponse;
	}
	
	@RequestMapping(value="/deleteAddress.do", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody() UIAddressResponse deleteAddress(HttpSession session,
			@RequestParam("addressId") String addressId
		){
		UIAddressResponse addressResponse = new UIAddressResponse();
		User user = (User) session.getAttribute(Constants.SESSION_USER_KEY);
		 if(user != null)
		 {
			 if(user.getAddresses()!= null)
			 {
				 for (Iterator iterator = user.getAddresses().iterator(); iterator
						.hasNext();) {
					Address address = (Address) iterator.next();
					if(address.getAdressId().equals(addressId))
					{
						iterator.remove();
					}
				}
			 }
			 
			 userDao.save(user);
		 }
		 
		 
		addressResponse.setMessage("Address deleted successfully");
		addressResponse.setSuccess(true);
		
		return addressResponse;
	}

	@RequestMapping(value="/getAddressList.do", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody() ResponseEntity<List<UIAddress>> getAddressList(HttpSession session) {
		
		
		 HttpHeaders responseHeaders = new HttpHeaders();
		 responseHeaders.add("Content-Type", "application/json; charset=utf-8");
		 
		 User user = (User) session.getAttribute(Constants.SESSION_USER_KEY);
		 if(user != null)
		 {
			 return new ResponseEntity<List<UIAddress>>(UIAddress.fillData(user), responseHeaders, HttpStatus.CREATED);
		 }
		 else{
			 return new ResponseEntity<List<UIAddress>>(new ArrayList<UIAddress>(), responseHeaders, HttpStatus.CREATED);
		 }
			
		 
	}
	
	@RequestMapping(value="/getUser.do", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody() ResponseEntity<UIUser> getUser(HttpSession session){
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json; charset=utf-8");
		User user = (User) session.getAttribute(Constants.SESSION_USER_KEY);
		 if(user != null)
		 {
			 return new ResponseEntity<UIUser>(new UIUser(true, user.getLoginId(), user.getFirstName() == null ? null :user.getFirstName() + " "+ user.getLastName(), 
					 user.getPrimaryMobile(), user.getLoginId()), responseHeaders, HttpStatus.CREATED);
		 }
		 else{
			 return new ResponseEntity<UIUser>(new UIUser(), responseHeaders, HttpStatus.CREATED);
		 }
		
	}

}
