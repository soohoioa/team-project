package com.itwill.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.TeamprojectAnimalcareApplicationTest;
import com.itwill.entity.Coupon;
import com.itwill.entity.Userinfo;

class CouponDaoImplTest extends TeamprojectAnimalcareApplicationTest{
	
	@Autowired
	private CouponDao couponDao;
	@Autowired
	private UserInfoDao userInfoDao;
	
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void test() {
		Userinfo userinfo = userInfoDao.findByNo(11L);
		Coupon coupon = Coupon.builder()
							.couponDiscount(30)
							.couponId(null)
							.couponName("가입쿠폰")
							.userinfo(userinfo)
							.build();
		couponDao.Create(coupon);
		
		 userinfo = userInfoDao.findByNo(11L);
		 coupon = Coupon.builder()
				.couponDiscount(30)
				.couponId(null)
				.couponName("생일쿠폰")
				.userinfo(userinfo)
				.build();
		couponDao.Create(coupon);
}

	
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void test1() {
		couponDao.DelteById(2L);
	}
	
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void test3() {
		System.out.println(couponDao.findById(3L));
	}
		
	@Test
	//@Disabled
	@Transactional
	@Rollback(false)
	void test4() {
		couponDao.deleteExpireCouponByUserNo(3L);
	}	
			
	
	
}
