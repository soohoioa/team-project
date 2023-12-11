package com.itwill.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.dao.CartDao;
import com.itwill.dao.OrderItemDao;
import com.itwill.dao.OrdersDao;
import com.itwill.dao.ProductDao;
import com.itwill.entity.Cart;
import com.itwill.entity.Coupon;
import com.itwill.entity.OrderItem;
import com.itwill.entity.Orders;
import com.itwill.entity.Orderstatus;
import com.itwill.entity.Pet;
import com.itwill.entity.Userinfo;
import com.itwill.repository.OrderStatusRepository;

@Transactional
@Service
public class OrderServiceImpl implements OrderService{
	@Autowired 
	private OrdersDao ordersDao;
	@Autowired 
	private CartDao cartDao;
	@Autowired 
	private OrderItemDao orderItemDao;
	@Autowired
	private ProductDao productDao;
	
	@Override
	public Orders insertOrder(Orders order) {
		return ordersDao.insertOrder(order);
	}
	//배송지변경
	@Override
	public Orders modifyOrder(Orders updateOrder) throws Exception {
		Optional<Orders> orderOptional = Optional.of(ordersDao.findOrderByNo(updateOrder.getOrderNo()));
		Orders uporders=null;
		if(orderOptional.isPresent()) {
			Orders orders = orderOptional.get();
			orders.setOrderAddress(updateOrder.getOrderAddress());
			uporders=ordersDao.updateOrder(orders);
		}else {
			throw new Exception("오류입니다.");
		}
		return uporders;
	}

	@Override
	public void removeOrderByOrderNo(Long orderNo)throws Exception {
		Orders order = ordersDao.findOrderByNo(orderNo);
		if(order==null) {
			throw new Exception("존재하지않습니다.");
		}
		List<OrderItem> orderItemList = order.getOrderItems();
		for (OrderItem orderItem : orderItemList) {
			orderItemDao.deleteOrderItem(orderItem);
		}
		ordersDao.deleteOrderByOrderNo(orderNo);
	}
	//전체주문 조회
	@Override
	public Page<Orders> findOrders(Pageable pageable) {
	
		return ordersDao.findAllOrders(pageable);
	}
	//주문한개 조회
	@Override
	public Orders findOrderByNo(Long orderNo) {
		
		return ordersDao.findOrderByNo(orderNo);
	}


	//전체주문 최신순으로 정렬
	@Override
	public List<Orders> findAllByOrderByOrderNoDesc(Long orderNo) {
		
		return ordersDao.findAllByOrderByOrderNoDesc();
	}
	//회원주문목록조회
	@Override
	public Page<Orders> findOrderById(Long userNo,Pageable pageable) {
		return ordersDao.findOrdersByuserNo(userNo,pageable);
	}
	@Override
	public List<Orders> findOrderByIdDesc(Long userNo) {
		return ordersDao.findAllByUserNoDESC(userNo);
	}
	@Override
	public List<Orders> findAllByOrdersByOrderDate(Date startDate, Date endDate) {
		return ordersDao.findAllByOrdersByOrderDate(startDate,endDate);
	}
	@Override
	public List<Orders> findAllByOrdersByOrderDateByUserNo(Date startDate, Date endDate, Long userNo) {
		return ordersDao.findAllByOrdersByOrderDateByUserNo(startDate,endDate,userNo);
	}
	@Override
	public List<Orders> findOrderById(Long userNo) {
		return ordersDao.findOrdersByuserNo(userNo);
	}

}