package com.itwill.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.itwill.entity.Wish;

public interface WishRepository extends JpaRepository<Wish, Long>{

	// 위시리스트 모두 출력 (userNo)
	//@Query(value = "select w.product_no,w.user_no,w.wish_no,p.product_category,p.product_detail_image,p.product_image,p.product_name,p.product_pet_category,p.product_price,p.product_qty,p.product_star_avg from wish w join product p on w.product_no = p.product_no where user_no=?1", nativeQuery = true)
	@Query(value = "select * from wish where user_no=?1", nativeQuery = true)
	List<Wish> findAllByUserNo(Long userNo);
	
	@Query(value = "select * from wish where wish.user_no=?1 and wish.product_no=?2", nativeQuery = true)
	Wish findByUserNoProductNo(Long userNo, Long productNo);
	
	// 위시리스트에 존재하는지 확인
	boolean existsByUserinfo_UserNoAndProduct_ProductNo(Long userNo, Long productNo);
	
	// 위시리스트에 있는 갯수 출력 (userNo)
	@Query(value = "select count(*) from wish where user_no=?1", nativeQuery = true)
	Integer countWishlist(Long userNo);
	
}


