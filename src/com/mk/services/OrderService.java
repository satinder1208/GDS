package com.mk.services;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
import org.springframework.web.portlet.ModelAndView;

import com.mk.beans.Kart;
import com.mk.beans.KartItem;
import com.mk.beans.Order;
import com.mk.beans.OrderItem;
import com.mk.beans.Product;
import com.mk.beans.QuantityPrice;
import com.mk.beans.QuantityPriceEntry;
import com.mk.beans.SubscribedItem;
import com.mk.beans.Subscription;
import com.mk.beans.Transaction;
import com.mk.beans.User;
import com.mk.beans.Variant;
import com.mk.dao.OrderDao;
import com.mk.dao.ProductDao;
import com.mk.uibeans.UIKart;
import com.mk.uibeans.UIMiniKart;
import com.mk.uibeans.UIResponse;
import com.mk.utils.Constants;
import com.mk.utils.ContextHolder;
import com.mk.utils.UIHelper;


@Controller
public class OrderService {
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private OrderDao orderDao;

	@RequestMapping(value="/order/addToCart.do", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody() UIKart addToCart(@RequestParam("productId") String productId, @RequestParam("variantId") String variantId,
			@RequestParam("quantityId") int quantity, HttpSession session/*, Device device*/) {
		
		/*if(device.isNormal())
		{
			System.out.println("***************** Normal Device *****************");
		}
		else if(device.isMobile() || device.isTablet())
		{
			System.out.println("***************** Android Device *****************");
		}*/
		
		Kart sessionKart = (Kart)session.getAttribute(Constants.SESSION_KART_KEY);
		if(sessionKart == null)
		{
			sessionKart = new Kart();
			session.setAttribute(Constants.SESSION_KART_KEY, sessionKart);
			
		}

		
		if(sessionKart.addItem(variantId, quantity, productDao.getProduct(variantId)))
		{
			calculateTotal(session);
			UIKart uIKart = getCart(Constants.INFO_TYPE_MINI, session).getBody();

			uIKart.setSuccess(true);
			uIKart.setFlashMessage("Item successfully added into your cart");
			return uIKart;
		}
		else
		{
			UIKart uIKart = getCart(Constants.INFO_TYPE_MINI, session).getBody();

			uIKart.setSuccess(false);
			uIKart.setFlashMessage("Exeeding maximum quantity limit");
			return uIKart;
			
		}
		
		
		
		
	}
	
	
	@RequestMapping(value="/order/getCart.do", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody() ResponseEntity<UIKart> getCart(@RequestParam(value="infoType", required=false, defaultValue = Constants.INFO_TYPE_MINI) String infoType, HttpSession session) {
		
		
		 HttpHeaders responseHeaders = new HttpHeaders();
		 responseHeaders.add("Content-Type", "application/json; charset=utf-8");
	
		 Kart sessionKart = (Kart)session.getAttribute(Constants.SESSION_KART_KEY);
			if(sessionKart != null)
			{
				if(Constants.INFO_TYPE_MINI.equals(infoType))
				{
					return new ResponseEntity<UIKart>(productDao.prepareMiniCartResponse(sessionKart), responseHeaders, HttpStatus.CREATED);
				}
				else
				{
					return new ResponseEntity<UIKart>(productDao.prepareCartResponse(sessionKart), responseHeaders, HttpStatus.CREATED);
				}
				
			}
			else{
				return new ResponseEntity<UIKart>(new UIMiniKart(), responseHeaders, HttpStatus.CREATED);
			}
		
		
	}
	
	
	@RequestMapping(value="/order/removeItemFromCart.do", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody() UIKart removeItemFromCart(@RequestParam("variantId") String variantId, HttpSession session) {
		
		Kart sessionKart = (Kart)session.getAttribute(Constants.SESSION_KART_KEY);
		if(sessionKart == null)
		{
			sessionKart = new Kart();
			session.setAttribute(Constants.SESSION_KART_KEY, sessionKart);
			
		}
		sessionKart.removeItem(variantId);
		
		calculateTotal(session);
		
		
		UIKart uIKart = getCart(Constants.INFO_TYPE_FULL, session).getBody();
		uIKart.setSuccess(true);
		uIKart.setMyCartMessage("Item removed from your cart successfully");
		
		
		return uIKart;
	}

	
	@RequestMapping(value="/order/updateQuantityInCart.do", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody() UIKart updateQuantityInCart(@RequestParam("variantId") String variantId, @RequestParam("quantityId") 
	int quantityId, HttpSession session) {
		
		
		Kart sessionKart = (Kart)session.getAttribute(Constants.SESSION_KART_KEY);
		if(sessionKart == null)
		{
			sessionKart = new Kart();
			session.setAttribute(Constants.SESSION_KART_KEY, sessionKart);
			
		}
		sessionKart.ensureQuantity(variantId, quantityId);
		calculateTotal(session);
		
		UIKart uIKart = getCart(Constants.INFO_TYPE_FULL, session).getBody();
		uIKart.setSuccess(true);
		uIKart.setMyCartMessage("Cart successfully updated");
		
		
		
		return uIKart;
	}
	

	@RequestMapping(value="/order/viewCart.do")
	public ModelAndView viewCartHtml(HttpSession session){
		
		return new ModelAndView("viewCart");
	}
	

	@RequestMapping(value="/order/addSubscription.do")
	public ModelAndView addSubscriptionHtml(HttpSession session){
		
		return new ModelAndView("addSubscription");
	}
	
	/*
	 * daySelection == 1:Daily, 2:Alternate, 3:Custom
	 * if daySelection is set on custom then we consider the values of variable customMon, customTue etc.
	 */
	@RequestMapping(value="/order/createSubscription.do", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody() UIResponse createSubscription(
			@RequestParam("addressId") String addressId, 
			@RequestParam("variantId") String variantId,
			@RequestParam("quantityId") int quantityId,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,
			@RequestParam("daySelection") int daySelection,
			@RequestParam("customMon") boolean customMon,
			@RequestParam("customTue") boolean customTue,
			@RequestParam("customWed") boolean customWed,
			@RequestParam("customThu") boolean customThu,
			@RequestParam("customFri") boolean customFri,
			@RequestParam("customSat") boolean customSat,
			@RequestParam("customSun") boolean customSun,
			HttpSession session
	) {
		
		User user = (User) session.getAttribute(Constants.SESSION_USER_KEY);
		UIResponse response = new UIResponse();
		
		if(user == null)
		{
			response.setMessage("Please login first.");
			response.setSuccess(false);
			return response;
		}
		
		Object lock = ContextHolder.getUserLock(user.getLoginId());
		synchronized (lock) {
			
			Subscription subscription = new Subscription();
			subscription.setDeliveryAddress(user.getAddress(addressId));
			subscription.setSubscriptionId(orderDao.getNextOrderId());
			subscription.setOrderTime(new Date());
			subscription.setSubscriptionType(daySelection);
			subscription.setUserId(user.getLoginId());
			try{
				if(startDate != null && "".equals(startDate.trim()) == false)
				{
					subscription.setStartDate(UIHelper.getDateFormatterPool().get().parse(startDate));
					
				}
				
				if(endDate != null && "".equals(endDate.trim()) == false)
				{
					subscription.setEndDate(UIHelper.getDateFormatterPool().get().parse(endDate));
				}
			}
			catch(Exception e)
			{
				throw new RuntimeException("Invalid date format");
			}
			
			
			
			
			SubscribedItem item = new SubscribedItem();
			item.setVariantId(variantId);
			item.setQuantity(quantityId);
			
			List<SubscribedItem> items = new ArrayList<SubscribedItem>();
			items.add(item);
			subscription.setItems(items);
			
			subscription.setCustomMonday(customMon);
			subscription.setCustomTuesday(customTue);
			subscription.setCustomWednesday(customWed);
			subscription.setCustomThursday(customThu);
			subscription.setCustomFriday(customFri);
			subscription.setCustomSaturday(customSat);
			subscription.setCustomSunday(customSun);
			
			orderDao.createSubscription(subscription);
			
			response.setMessage("Subscription created successfully");
			response.setSuccess(true);
			return response;
		}
		
		
	}

	private void calculateTotal(HttpSession session) {

		User user = (User) session.getAttribute(Constants.SESSION_USER_KEY);
		Kart sessionKart = (Kart)session.getAttribute(Constants.SESSION_KART_KEY);
		
		if(user != null && sessionKart != null)
		{
			sessionKart.setUserId(user.getLoginId());
			orderDao.persistKart(sessionKart);
		}
			
	}
	
	@RequestMapping(value="/order/placeOrder.do", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody() UIKart placeOrder(@RequestParam("kartId") String kartId, 
			@RequestParam("addressId") String addressId, @RequestParam("payType") int payType,
			HttpSession session) {
		
		User user = (User) session.getAttribute(Constants.SESSION_USER_KEY);
		Kart sessionKart = (Kart)session.getAttribute(Constants.SESSION_KART_KEY);
		
		if(user == null)
		{
			UIKart uIKart = getCart(Constants.INFO_TYPE_MINI, session).getBody();
			
			uIKart.setSuccess(false);
			uIKart.setFlashMessage("Session not valid, please login again");
			
			return uIKart;
			
		}
		Object lock = ContextHolder.getUserLock(user.getLoginId());
		synchronized (lock) {
			
			long trId = orderDao.getNextTransactionId();
			long oId = orderDao.getNextOrderId();
			
			if(sessionKart == null || kartId.length() == 0 || kartId.equals(sessionKart.getKartUUID()) == false)
			{
				UIKart uIKart = getCart(Constants.INFO_TYPE_MINI, session).getBody();
				
				uIKart.setSuccess(false);
				uIKart.setFlashMessage("Invalid kart status. Please refresh page once");
				
				return uIKart;
				
			}
			
			double worth = 0.0;
			
			Order order = new Order();
			order.setDeliveryAddress(user.getAddress(addressId));
			order.setOrderId(oId);
			order.setOrderTime(new Date());
			order.setOrderType(payType);
			order.setTransactionId(trId);
			order.setUserId(user.getLoginId());
			List<OrderItem> orderItems = new ArrayList<OrderItem>();
			order.setOrderItems(orderItems);
			
			for (Iterator iterator = sessionKart.getItems().values().iterator(); iterator.hasNext();) {
				KartItem item = (KartItem) iterator.next();
				
				OrderItem orderItem = new OrderItem();
				orderItem.setVariantId(item.getVariantId());
				Product product = productDao.getProduct(item.getVariantId());
				Variant variant = product.getVariant(item.getVariantId());
				
				QuantityPriceEntry qpe = ((QuantityPrice)variant.getPrice().get(0)).getQuantityPriceEntry(item.getQuantity());
				
				orderItem.setQuantityPriceEntry(qpe);
				
				worth = worth + qpe.getSellingPrice();
						
				orderItem.setOffers(variant.getOffers());
				orderItems.add(orderItem);
				
			}
			
			Transaction tr = new Transaction();
			tr.setTransactionId(trId);
			tr.setDebitAmount(worth);
			tr.setReferenceId(oId);
			tr.setTransactionDate(new Date());
			tr.setTransactionTypeCode(Constants.TRANSACTION_TYPE_ONE_TIME_ORDER);
			tr.setUserId(user.getLoginId());
			
			orderDao.placeOrder(order, tr);
			
			sessionKart.removeAll();
			
			orderDao.persistKart(sessionKart);
			UIKart uIKart = getCart(Constants.INFO_TYPE_MINI, session).getBody();
			
			uIKart.setSuccess(true);
			uIKart.setFlashMessage("Order placed successfully. Thank you for shopping at Morning Kart");
			
			
			return uIKart;
		}
		
	}
}
