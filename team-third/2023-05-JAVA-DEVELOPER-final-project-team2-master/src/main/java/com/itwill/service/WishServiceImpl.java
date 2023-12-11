package com.itwill.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.dao.UserInfoDao;
import com.itwill.dao.WishDao;
import com.itwill.entity.Wish;
import com.itwill.repository.WishRepository;
@Transactional
@Service
public class WishServiceImpl implements WishService {

	@Autowired
	private WishRepository wishrepoRepository;
	@Autowired
	private WishDao wishDao;
	@Autowired
	private UserInfoDao userinfoDao;
	
	
	@Override
	public Wish insertWish(Wish insertWish) {
		
		Wish findWish = wishrepoRepository.findByUserNoProductNo(insertWish.getUserinfo().getUserNo(), insertWish.getProduct().getProductNo());

		if (findWish != null) {
			System.out.println("이미 존재하는 상품입니다.");
		} else {
			Wish wish = new Wish();
			wish.setProduct(insertWish.getProduct());
			wish.setUserinfo(insertWish.getUserinfo());

			wishDao.insertWish(wish);
		}
		return insertWish;
	}

	@Override
	public void deleteWish(Long no) throws Exception {
		wishDao.deleteWish(no);
		
	}

	@Override
	public List<Wish> findAllWishByUserNo(Long userNo) {
		return wishDao.findAllWishByUserNo(userNo);
	}

	@Override
	public Wish findByWishNo(Long no) {
		Wish selectedWish = wishDao.findByWishNo(no);
		return selectedWish;
	}

	@Override
	public Wish findByUserNoProductNo(Long userNo, Long productNo) {
		Wish selectedWish = wishDao.findByUserNoProductNo(userNo, productNo);
		return selectedWish;
	}

	@Override
	public Integer countWishlist(Long userNo) {
		return wishDao.countWishlist(userNo);
	}

	@Override
	public boolean existsByUserinfo_UserNoAndProduct_ProductNo(Long userNo, Long productNo) {
		return wishDao.existsByUserinfo_UserNoAndProduct_ProductNo(userNo, productNo);
	}


	
}
