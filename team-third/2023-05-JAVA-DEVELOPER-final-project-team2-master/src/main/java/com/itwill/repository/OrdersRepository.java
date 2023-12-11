package com.itwill.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itwill.entity.Orders;
import com.itwill.entity.Userinfo;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long>{
	
	@Query("SELECT o FROM Orders o")
	public Page<Orders> findAllOrdersByPage(Pageable pageable);
	//최신 등록순으로 조회
	List<Orders> findAllByOrderByOrderNoDesc();

	// userno로 주문목록 조회
	@Query(value = "SELECT * FROM Orders o WHERE o.user_no = :user_no", nativeQuery = true)
	Page<Orders> findAllByUserNo(@Param("user_no") Long userNo,Pageable pageable);
	// userno로 주문목록 조회
	@Query(value = "SELECT * FROM Orders o WHERE o.user_no = :user_no", nativeQuery = true)
	List<Orders> findAllByUserNo(@Param("user_no") Long userNo);

	// userno로 최신 주문목록 조회
	@Query(value = "SELECT * FROM Orders o WHERE o.user_no = :user_no ORDER BY o.order_no DESC", nativeQuery = true)
	List<Orders> findAllByUserNoDESC(@Param("user_no") Long userNo);

	// 기간별로 주문목록 조회
	@Query(value = "SELECT * FROM orders o WHERE o.order_date >= ?1 AND o.order_date <= ?2 ", nativeQuery = true)
	List<Orders> findAllByOrdersByOrderDate(Date startDate, Date endDate);

	// 기간별로회원 주문 목록 조회
	@Query(value = "SELECT * FROM orders o WHERE o.order_date >= ?1 AND o.order_date <= ?2  AND o.user_no= ?3", nativeQuery = true)
	List<Orders> findAllByOrdersByOrderDateByUserNo(Date startDate, Date endDate, Long userNo);
	
	
}
