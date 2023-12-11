package com.itwill.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.TeamprojectAnimalcareApplicationTest;
import com.itwill.entity.OrderItem;
import com.itwill.entity.Orders;
import com.itwill.entity.Orderstatus;
import com.itwill.entity.Product;

class OrderItemDaoImplTest extends TeamprojectAnimalcareApplicationTest {
@Autowired
OrderItemDao orderItemDao;
@Autowired
ProductDao productDao;
@Autowired
OrdersDao ordersDao;
@Autowired
OrderStatusDao orderStatusDao;
	@Test
	@Transactional
	@Rollback(false)
	void insert() {
		
		Product product = productDao.findByProductNo(1L);
		Orders orders = ordersDao.findOrderByNo(2L);
		Orderstatus orderstatus =orderStatusDao.findOrderStatus(1L);
		OrderItem orderItem = OrderItem.builder()
				.oiQty(1)
				.product(product)
				.orders(orders)
				.orderStatus(orderstatus)
				.build();
		
		orderItemDao.insertOrderItem(orderItem);
		System.out.println(orderItem);
	}

}
