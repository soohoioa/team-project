package com.itwill.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.contains;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.itwill.TeamprojectAnimalcareApplicationTest;
import com.itwill.entity.Coupon;
import com.itwill.entity.Userinfo;

import jakarta.transaction.Transactional;

class CouponServiceImplTest extends TeamprojectAnimalcareApplicationTest {

	@Autowired
	CouponService couponService;

	@Test
	@Disabled
	void test() {
		Userinfo userinfo = Userinfo.builder().userId("장희주").build();
		Coupon coupon = Coupon.builder().couponName("생일쿠폰").couponDiscount(20)
								.userinfo(userinfo).build();


		Coupon createCoupon = couponService.Create(coupon);
		System.out.println(createCoupon);
	}

	@Transactional
	@Rollback(false)
	@Disabled
	@Test
	void test1() {
		couponService.Delete(3L);
	}

	@Transactional
	@Rollback(false)
	@Disabled
	@Test
	void test2() {

		List<Coupon> coupons = couponService.findAll();
		System.out.println(coupons);
	}

	@Transactional
	@Rollback(false)
	//@Disabled
	@Test
	void test3() {
		List<Coupon> coupons = couponService.findAllByUserNo(17L);
		System.out.println(coupons);
	}

}
