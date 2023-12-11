package com.itwill.dao;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwill.entity.Coupon;
import com.itwill.repository.CouponRepository;

@Repository
public class CouponDaoImpl implements CouponDao {

	@Autowired
	private CouponRepository couponRepository;

	@Override
	public Coupon Create(Coupon coupon) {
		couponRepository.save(coupon);
		return coupon;
	}

	@Override
	public void DelteById(Long couponId) {
		if (couponRepository.findById(couponId).isPresent()) {
			couponRepository.deleteById(couponId);
		}
	}

	@Override
	public Coupon findById(Long couponId) {
		return couponRepository.findById(couponId).get();
	}

	@Override
	public List<Coupon> findAll() {
		return couponRepository.findAll();
	}

	// 만료된 쿠폰찾기
	@Override
	public List<Coupon> autoDeleteExpiredCoupons() {
		return couponRepository.findByExpirationDateBefore();
	}

	@Override
	public List<Coupon> findAllByUserNo(Long userNo) {
		return couponRepository.findAllByUserNo(userNo);
	}

	@Override
	public void deleteExpireCouponByUserNo(Long userNo) {
		couponRepository.deleteExpireCouponByUserNo(userNo);
	}
	
}
	


