package com.itwill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.dao.OrderItemDao;
import com.itwill.entity.OrderItem;

@Transactional
@Service
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	private OrderItemDao orderItemDao;
	
	@Override
	public OrderItem insertOrderItem(OrderItem orderItem) {
		return orderItemDao.insertOrderItem(orderItem);
	}
	@Override
	public OrderItem updateOrderItem(OrderItem orderItem) {
		return orderItemDao.updateOrderItem(orderItem);
	}
	@Override
	public void deleteOrderItem(OrderItem orderItem) {
		orderItemDao.deleteOrderItem(orderItem);
	}
	@Override
	public List<OrderItem> findAll() {
		return orderItemDao.findAll();
	}
	@Override
	public OrderItem findByOiNo(Long oiNo) {
		
		return orderItemDao.findbyoiNo(oiNo);
	}
 
}
