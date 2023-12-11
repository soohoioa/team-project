package com.itwill.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.itwill.entity.Orders;
import com.itwill.repository.OrdersRepository;
@Repository
public class OrdersDaoImpl implements OrdersDao{
	
	@Autowired
	private OrdersRepository ordersRepository;
	
	@Override
	public Orders insertOrder(Orders order) {
		Orders saveOrder=ordersRepository.save(order);
		return saveOrder;
	}

	@Override
	public Orders updateOrder(Orders updateOrder) {
		Orders upOrder=ordersRepository.save(updateOrder);
		return upOrder;
	}

	@Override
	public void deleteOrderByOrderNo(Long orderNo) {
		ordersRepository.deleteById(orderNo);
	}

	@Override
	public Page<Orders> findAllOrders(Pageable pageable) {
		Page<Orders> Orders = ordersRepository.findAllOrdersByPage(pageable);
		return Orders;
		
	}
	@Override
	public Page<Orders> findOrdersByuserNo(Long userNo, Pageable pageable) {
		Page<Orders> Orders = ordersRepository.findAllByUserNo(userNo,pageable);
		return Orders;
	}
	
	@Override
	public Orders findOrderByNo(Long orderNo) {
		Orders order = ordersRepository.findById(orderNo).get();
		return order;
	}


	//전체주문 최신목록으로 조회
	@Override
	public List<Orders> findAllByOrderByOrderNoDesc() {
		List<Orders> orders = ordersRepository.findAllByOrderByOrderNoDesc();
		return orders;
	}

// //기간별로 주문목록 조회
	@Override
	public List<Orders> findAllByOrdersByOrderDate(Date startDate,Date endDate) {
		List<Orders> orders = ordersRepository.findAllByOrdersByOrderDate(startDate, endDate);
		return orders;
	}
	@Override
	public List<Orders> findAllByOrdersByOrderDateByUserNo(Date startDate, Date endDate, Long userNo) {
		List<Orders> orders = ordersRepository.findAllByOrdersByOrderDateByUserNo(startDate,endDate,userNo);
		return orders;
	}


	@Override
	public List<Orders> findAllByUserNoDESC(Long userNo) {
		List<Orders> orders = ordersRepository.findAllByUserNoDESC(userNo);
		return orders;
	}

	@Override
	public List<Orders> findAllOrders() {
		List<Orders> orders = ordersRepository.findAll();
		return orders;
	}

	@Override
	public List<Orders> findOrdersByuserNo(Long userNo) {
		List<Orders> orders = ordersRepository.findAllByUserNo(userNo);
		return orders;
	}

	

	

	
	
	

	

}
