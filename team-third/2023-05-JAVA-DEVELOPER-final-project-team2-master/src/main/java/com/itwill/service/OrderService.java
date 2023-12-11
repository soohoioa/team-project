package com.itwill.service;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itwill.entity.Orders;

public interface OrderService {

	// order insert 시 장바구니 전체삭제 ,
	Orders insertOrder(Orders order);

	Orders modifyOrder(Orders order) throws Exception;

	// 주문삭제 관리자전용
	void removeOrderByOrderNo(Long orderNo) throws Exception;

	// 주문 번호로 조회
	Orders findOrderByNo(Long orderNo);

	// 주문 전체 조회 , 관리자전용
	Page<Orders> findOrders(Pageable pageable);
	
	// 회원아이디로 주문조회
	Page<Orders> findOrderById(Long userNo,Pageable pageable);
	List<Orders> findOrderById(Long userNo);

	List<Orders> findOrderByIdDesc(Long userNo);

	//
	List<Orders> findAllByOrderByOrderNoDesc(Long orderNo);

	// 날짜별 기간으로 조회
	List<Orders> findAllByOrdersByOrderDate(Date startDate, Date endDate);
	
	public List<Orders> findAllByOrdersByOrderDateByUserNo(Date startDate, Date endDate, Long userNo);
	
}
