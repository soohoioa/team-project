package com.itwill.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwill.entity.Wish;
import com.itwill.repository.WishRepository;

@Repository
public class WishDaoImpl implements WishDao{

	@Autowired
	private WishRepository wishRepository;
	
	@Override
	public Wish insertWish(Wish wish) {
		Wish insert = wishRepository.save(wish);
		return insert;
	}

	@Override
	public void deleteWish(Long no) throws Exception {
		wishRepository.deleteById(no);
	}

	@Override
	public List<Wish> findAllWishByUserNo(Long userNo) {
		return wishRepository.findAllByUserNo(userNo);
	}

	@Override
	public Wish findByWishNo(Long no) {
		return wishRepository.findById(no).get();
	}

	@Override
	public Wish findByUserNoProductNo(Long userNo, Long productNo) {
		return wishRepository.findByUserNoProductNo(userNo, productNo);
	}

	@Override
	public Integer countWishlist(Long userNo) {
		return wishRepository.countWishlist(userNo);
	}

	
	// 위시리스트에 있는지 확인
	@Override
	public boolean existsByUserinfo_UserNoAndProduct_ProductNo(Long userNo, Long productNo) {
		return wishRepository.existsByUserinfo_UserNoAndProduct_ProductNo(userNo, productNo);
	}

	


	
	
}
