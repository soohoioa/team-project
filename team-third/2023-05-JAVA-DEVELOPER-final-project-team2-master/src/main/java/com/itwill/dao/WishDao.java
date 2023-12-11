package com.itwill.dao;

import java.util.List;

import com.itwill.entity.Wish;

public interface WishDao {

	// 위시리스트에 담기
	Wish insertWish(Wish wish);

	// 위시리스트에서 삭제
	void deleteWish(Long no) throws Exception;

	// 위시리스트 모두 출력 (userNo)
	List<Wish> findAllWishByUserNo(Long userNo);

	// 위시리스트에서 하나 찾기
	Wish findByWishNo(Long no);

	// 위시리스트에서 userNo, productNo로 찾아오기
	Wish findByUserNoProductNo(Long userNo, Long productNo);
	
	// 위시리스트에 있는 갯수 출력 (userNo)
	Integer countWishlist(Long userNo);

	// 위시리스트에 있는지 확인
	boolean existsByUserinfo_UserNoAndProduct_ProductNo(Long userNo, Long productNo);
}
