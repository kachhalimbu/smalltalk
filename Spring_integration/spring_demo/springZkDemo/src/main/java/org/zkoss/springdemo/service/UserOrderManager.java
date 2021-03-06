/**
 * 
 */
package org.zkoss.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zkoss.springdemo.bean.CartItem;
import org.zkoss.springdemo.bean.Order;
import org.zkoss.springdemo.dao.OrderDAO;

/**
 * @author Ian YT Tsai (Zanyking)
 *
 */
@Component
@Scope("session")
public class UserOrderManager{

	@Autowired
	private OrderDAO orderDao;
	@Autowired
	private UserCredentialManager userCredentialManager;

	public List<Order> findAll() {
		return orderDao.findByUser(userCredentialManager.getUser());
	}

	public Order cancelOrder(Order order) {
		return orderDao.cancelOrder(order);
	}

	public Order createOrder(List<CartItem> cartItems, String orderNote) {
		return orderDao.createOrder(userCredentialManager.getUser(), cartItems, orderNote);
	}
	
}
