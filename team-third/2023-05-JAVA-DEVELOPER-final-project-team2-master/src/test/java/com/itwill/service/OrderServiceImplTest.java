package com.itwill.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.TeamprojectAnimalcareApplicationTest;
import com.itwill.dao.OrdersDao;
import com.itwill.entity.Orders;
import com.itwill.entity.Userinfo;

class OrderServiceImplTest extends TeamprojectAnimalcareApplicationTest {

	@Autowired
	OrderService orderService;
	@Autowired
	UserInfoService userInfoService;

	@Test
	@Transactional
	@Rollback(false)
	//@Disabled
	void insert() throws Exception{
		Userinfo user=userInfoService.findUserByNo(5L);
	
		Orders orders=Orders.builder()
		.orderAddress("서울특별시")
		.orderDesc("카트삭제되는 주문")
		.orderDate(new Date())
		.userinfo(user)
		.build();
		
		orderService.insertOrder(orders);
	}


	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void update() throws Exception {
		Orders order = orderService.findOrderByNo(1L);
		order.setOrderAddress("남양주시");
		orderService.modifyOrder(order);

	}

	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void delete() throws Exception {
		orderService.removeOrderByOrderNo(1L);
	}

	/*
	 * @Test
	 * 
	 * @Transactional
	 * 
	 * @Rollback(false)
	 * 
	 * @Disabled void findOrders() throws Exception {
	 * System.out.println(orderService.findOrders());
	 * 
	 * }
	 */
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void findOrdersByNo() throws Exception {
		System.out.println(orderService.findOrderByNo(2L));

	}

	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void findOrdersById() throws Exception {
		System.out.println(orderService.findOrderById(2L));

	}

	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void findOrderByIdDesc() throws Exception {
		System.out.println(orderService.findOrderByIdDesc(2L));

	}
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void findAllByOrdersByOrderDateByUserNo() throws Exception {
		//System.out.println(orderService.findAllByOrdersByOrderDateByUserNo(new Date(), null, null));

	}
	

}
