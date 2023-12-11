package com.itwill.controller;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.dto.CouponDto;
import com.itwill.dto.UserLoginActionDto;
import com.itwill.entity.Coupon;
import com.itwill.entity.Userinfo;
import com.itwill.service.CouponService;
import com.itwill.service.UserInfoService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/coupon")
public class CouponRestController {

	@Autowired
	private CouponService couponService;

	@Autowired
	private UserInfoService userInfoService;

	@Operation(summary = "쿠폰 생성")
	@PutMapping("/{userNo}")
	public ResponseEntity<CouponDto> insertCoupon(@PathVariable(name = "userNo") Long userNo) throws Exception {

		
		Userinfo findUserinfo = userInfoService.findUserByNo(userNo);
		Coupon coupon = Coupon.builder().couponName("생일쿠폰").couponDiscount(30).userinfo(findUserinfo).build();
		coupon.setCouponDate(30L);

		coupon = couponService.Create(coupon);
		CouponDto couponDto = CouponDto.toDto(coupon);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		return new ResponseEntity<CouponDto>(couponDto, httpHeaders, HttpStatus.OK);
	}

	@Operation(summary = "유저에 따른 쿠폰 뽑기")
	@GetMapping("/{userNo}")
	public ResponseEntity<List<CouponDto>> findAllByUserNo(@PathVariable(name = "userNo") Long userNo)
			throws Exception {
		couponService.deleteExpireCouponByUserNo(userNo);
		List<Coupon> coupons = couponService.findAllByUserNo(userNo);
		List<CouponDto> couponList = new ArrayList<CouponDto>();

		for (Coupon coupon : coupons) {
			CouponDto couponDto = CouponDto.toDto(coupon);
			couponList.add(couponDto);
		}

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		return new ResponseEntity<List<CouponDto>>(couponList, httpHeaders, HttpStatus.OK);

	}

}
