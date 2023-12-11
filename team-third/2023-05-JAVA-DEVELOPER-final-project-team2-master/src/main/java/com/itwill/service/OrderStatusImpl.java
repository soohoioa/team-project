package com.itwill.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.itwill.dao.OrderStatusDao;
import com.itwill.entity.Orderstatus;

public class OrderStatusImpl implements OrderStatus {

	@Autowired
	OrderStatusDao orderStatusDao;
	@Override
	public Orderstatus findByOrderstatus(Long osNo) {
		
		return orderStatusDao.findOrderStatus(osNo);
	}

}
