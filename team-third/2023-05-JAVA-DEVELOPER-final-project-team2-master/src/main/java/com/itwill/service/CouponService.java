package com.itwill.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;

import com.itwill.dao.CouponDao;
import com.itwill.entity.Coupon;


public interface CouponService {
	
	// 쿠폰지급
	public Coupon Create(Coupon coupon);
	// 쿠폰삭제
	public void Delete(Long couponId);
	// 쿠폰 리스트
	public List<Coupon> findAll();
	// 쿠폰 찾기
	public Coupon findById(Long couponId);
	//사용자 유저에 따른 쿠폰 뽑기
	public List<Coupon> findAllByUserNo(Long userNo);
	
	// 만료일 쿠폰 찾기
	public List<Coupon> autoDeleteExpiredCoupons();
	
	// 유저에 따른 만료 쿠폰 
	public void deleteExpireCouponByUserNo(Long userNo);
	
	
}
