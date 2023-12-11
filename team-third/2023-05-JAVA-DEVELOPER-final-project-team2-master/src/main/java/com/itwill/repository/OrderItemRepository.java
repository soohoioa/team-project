package com.itwill.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.itwill.entity.OrderItem;
import com.itwill.entity.Userinfo;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
	
	//orderno로 주문목록 조회
	 @Query(value = "SELECT * FROM order_item o WHERE o.order_no = :order_no",nativeQuery = true)
	List<OrderItem> findAllOrderItemByOrderNo(@Param("order_no")Long orderNo);
	
	
		 
		 @Query(value = "DELETE FROM OrderItem oi WHERE oi.order_no = :order_no",nativeQuery = true)
		 void deleteOrderItemByOrderNo(@Param("order_no") Long orderNo);
}
