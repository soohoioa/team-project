package com.itwill.controller;

import java.nio.charset.Charset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.itwill.dto.CouponDto;
import com.itwill.entity.Coupon;
import com.itwill.service.CouponService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;

@Controller
public class CouponController {
	
	@Autowired
	private CouponService couponService;
	
	@Operation(summary = "유저에 따른 쿠폰 뽑기")
	@GetMapping("/couponList")
	public String findAllByUserNo(HttpSession session,Model model)
			throws Exception {
		Long userNo = (Long)session.getAttribute("userNo");
		
		List<Coupon> coupons = couponService.findAllByUserNo(userNo);
		List<CouponDto> couponList = new ArrayList<CouponDto>();
		
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate1 = "";
        String formattedDate2 = "";
        
        if(coupons!=null) {
        	for(int i=0;i<couponService.findAllByUserNo(userNo).size();i++) {
	        	formattedDate1 = couponService.findAllByUserNo(userNo).get(i).getCouponPayday().format(formatter);
	        	formattedDate2 = couponService.findAllByUserNo(userNo).get(i).getCouponExpirationDate().format(formatter);
	        	CouponDto couponDto = CouponDto.toDto(couponService.findAllByUserNo(userNo).get(i));
	        	couponDto.setCouponPayday(formattedDate1);
	        	couponDto.setCouponExpirationDate(formattedDate2);
    			couponList.add(couponDto);
        	}
			
        }else {
        	couponList.add(CouponDto.builder().build());
        }
		
		model.addAttribute("couponList", couponList);
		
		
		return "my-account-coupon";

	}
	
}
