package com.itwill.dao;

import java.util.List;


import com.itwill.entity.OrderItem;

public interface OrderItemDao {
	
	public OrderItem insertOrderItem(OrderItem orderItem);
	
	public OrderItem updateOrderItem(OrderItem orderItem);
	
	public void deleteOrderItem(OrderItem orderItem);
	
	public List<OrderItem> findAll();
	
	public  OrderItem findbyoiNo(Long oiNo);



}
/*
 * int insertOrder(Order order,Payment payment) throws Exception; int
 * updateOrder(Order order) throws Exception; int deleteOrder(int orderNo)
 * throws Exception; int insertPayment(Payment payment) throws Exception;
 * 
 * int insertOrderItem(OrderItem orderItem) throws Exception; int
 * updateOrderItem(OrderItem orderItem) throws Exception; int
 * deleteOrderItem(int orderNo) throws Exception;
 * 
 * List<Order> findAllOrders() throws Exception; Order findOrderByNo(int
 * orderNo) throws Exception; List<Order> findOrdersByDate(Date startDate, Date
 * endDate) throws Exception; List<Order> findOrdersById(String userId) throws
 * Exception;
 * 
 * List<OrderItem> findAllOrderItems() throws Exception; OrderItem
 * findOrderItemByNo(int oiNo) throws Exception; List<OrderItem>
 * findOrderItemsByOrder(int orderNo) throws Exception;
 */

/*
 * import com.itwill.entity.OrderItem;
 * 
 * public interface OrderItemDao {
 * 
 * //orderItem insert OrderItem insertOrderItem(OrderItem orderItem);
 * 
 * 
 * OrderItem updateOrderItem(OrderItem updateoOrderItem) throws Exception;
 * 
 * void deleteOrderItemByOrderNo(Long orderNo) throws Exception;
 * 
 * List<OrderItem> findAllOrderItem(); List<OrderItem>
 * findAllOrderItemByOrderNo(Long orderNo);
 * 
 * 
 * 
 * }
 */