package com.itwill.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwill.entity.OrderItem;
import com.itwill.repository.OrderItemRepository;
import com.itwill.repository.OrdersRepository;

@Repository
public class OrderItemDaoImpl implements OrderItemDao {

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public OrderItem insertOrderItem(OrderItem orderItem) {
		return orderItemRepository.save(orderItem);
	}

	@Override
	public OrderItem updateOrderItem(OrderItem orderItem) {
		Optional<OrderItem> optional = orderItemRepository.findById(orderItem.getOiNo());
		OrderItem item = optional.get();
		
		item.setOiNo(orderItem.getOiNo());
		item.setOiQty(orderItem.getOiQty());
		item.setOrders(orderItem.getOrders());
		item.setOrderStatus(orderItem.getOrderStatus());
		item.setProduct(orderItem.getProduct());
		
		OrderItem saveItem = orderItemRepository.save(item);
		
		return saveItem;
	}

	@Override
	public void deleteOrderItem(OrderItem orderItem) {
		orderItemRepository.delete(orderItem);
	}

	@Override
	public List<OrderItem> findAll() {
		return orderItemRepository.findAll();
	}

	@Override
	public OrderItem findbyoiNo(Long oiNo) {
		
		return orderItemRepository.findById(oiNo).get();
	}
	
	/*
	@Override
	public OrderItem updateOrderItem(OrderItem updateorderItem) throws Exception {
		return orderItemRepository.save(updateorderItem);
	}

	@Override
	public void deleteOrderItemByOrderNo(Long orderNo) throws Exception {
		orderItemRepository.deleteOrderItemByOrderNo(orderNo);

	}

	@Override
	public List<OrderItem> findAllOrderItem() {
		return orderItemRepository.findAll();
	}

	@Override
	public List<OrderItem> findAllOrderItemByOrderNo(Long orderNo) {
		return orderItemRepository.findAllOrderItemByOrderNo(orderNo);
	}
	*/
}