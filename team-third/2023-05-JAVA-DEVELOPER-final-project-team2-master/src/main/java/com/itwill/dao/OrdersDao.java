package com.itwill.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.itwill.entity.OrderItem;
import com.itwill.entity.Orders;

public interface OrdersDao {
	
	//주문완료
	Orders insertOrder(Orders order) ;
	// 주문변경
	Orders updateOrder(Orders updateOrder) ;
	//주문삭제
	void deleteOrderByOrderNo(Long orderNo) ;


	//모든주문 찾기
	Page<Orders> findAllOrders(Pageable pageable) ;
	List<Orders> findAllOrders() ;
	
	
	//order 1개 찾기
	Orders findOrderByNo(Long orderNo) ;

	//id로 주문찾기
	//List<Orders> findOrdersByuserNo(Long userNo) ;
	
	//id로 최신주문정렬찾기
		List<Orders> findAllByUserNoDESC(Long userNo);
	
	//최근주문별로 조회
	List<Orders> findAllByOrderByOrderNoDesc();
	
	// 관리자모드 날짜별 기간으로 조회
	List<Orders> findAllByOrdersByOrderDate(Date startDate,Date endDate);
	//회원모드 날짜별 기간으로 조회
	 List<Orders> findAllByOrdersByOrderDateByUserNo(Date startDate,Date endDate,Long userNo);
	Page<Orders> findOrdersByuserNo(Long userNo, Pageable pageable);
	List<Orders> findOrdersByuserNo(Long userNo);
	//Page<Orders> findAllOrders();
	
}
