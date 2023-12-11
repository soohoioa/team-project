package com.itwill.dao;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.itwill.entity.Coupon;

public interface CouponDao {
	
	public Coupon Create(Coupon coupon);
	
	public void DelteById(Long couponId);
	
	public List<Coupon> autoDeleteExpiredCoupons();
	
	public List<Coupon> findAll();
	
	public Coupon findById(Long couponId);
	
	//유저에 대한 쿠폰 리스트 뽑기
	public List<Coupon> findAllByUserNo(Long userNo);
	
	//유저에 따른 쿠폰 삭제 기능 
	public void deleteExpireCouponByUserNo(Long userNo);
	
}

