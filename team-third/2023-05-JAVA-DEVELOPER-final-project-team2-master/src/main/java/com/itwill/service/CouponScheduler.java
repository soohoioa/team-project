package com.itwill.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.entity.Coupon;
import com.itwill.entity.MyPet;
import com.itwill.entity.Userinfo;

@Component
@Service
public class CouponScheduler {

	@Autowired
	private CouponService couponService;

	@Autowired
	private MyPetService myPetService;

	@Autowired
	private UserInfoService userInfoService;

	@Scheduled(cron = "0 0 0 * * ?")
	@Transactional
	public void CreateBirthdayCoupon() throws Exception {
		List<Userinfo> userinfoList = userInfoService.findUserList();
		Coupon birthCoupon = Coupon.builder().couponDiscount(30).couponName("생일쿠폰").build();
		birthCoupon.setCouponDate(30L);
		
		for (Userinfo userinfo : userinfoList) {
			MyPet myPetLeader = myPetService.findLeaderMyPet(userinfo.getUserNo());
			if (myPetLeader!=null) {
				// 2022년 != 올해 -> 주겟지>? , null 주겟지?
				if(userinfo.getUserCouponYear()==null || LocalDateTime.now().getYear()!=userinfo.getUserCouponYear()) {
					if(LocalDateTime.now().getMonthValue()==myPetLeader.getMypetBirthday().getMonthValue()) {
						if(LocalDateTime.now().getDayOfMonth()==myPetLeader.getMypetBirthday().getDayOfMonth()) {
							birthCoupon.setUserinfo(userinfo);
							couponService.Create(birthCoupon);
							userinfo.setUserCouponYear(LocalDateTime.now().getYear());
							userInfoService.update(userinfo);
						}
					}
				}
			}
		}
	}
	
	@Scheduled(cron = "0 0 0 * * ?")
	@Transactional
	public void DeleteCoupon() throws Exception {
		List<Coupon> couponList = couponService.findAll();
		for (Coupon coupon : couponList) {
			/*
			 * if(LocalDateTime.now().getYear) {
			 * 
			 * }
			 */
			if(LocalDateTime.now().getMonthValue()==coupon.getCouponExpirationDate().getMonthValue()) {
				if(LocalDateTime.now().getDayOfMonth()==coupon.getCouponExpirationDate().getDayOfMonth()) {
					couponService.Delete(coupon.getCouponId());
				}
			}
		}
	}

}
