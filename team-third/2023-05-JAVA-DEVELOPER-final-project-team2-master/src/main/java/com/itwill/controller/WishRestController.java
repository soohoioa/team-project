package com.itwill.controller;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.dto.CartDto;
import com.itwill.dto.WishlistDto;
import com.itwill.dto.WishlistInsertDto;
import com.itwill.entity.Product;
import com.itwill.entity.Userinfo;
import com.itwill.entity.Wish;
import com.itwill.service.ProductService;
import com.itwill.service.UserInfoService;
import com.itwill.service.WishService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/wish")
public class WishRestController {
	
	// 서진님... 왜 컨트롤러 안보이냐고  
	@Autowired
	private WishService wishService;
	@Autowired
	private UserInfoService userinfoService;
	@Autowired
	private ProductService productService;
	
	
	
	@Operation(summary = "위시리스트 추가")
	@PostMapping
	// insert
	public ResponseEntity<WishlistInsertDto> insertWishlist(@RequestBody WishlistInsertDto dto, HttpSession session) throws Exception {
		if (session.getAttribute("userNo") == null) {
			throw new Exception("로그인 하세요.");
		}
		
		Wish wishlist = WishlistInsertDto.toEntity(dto);
		Long userNo = (Long) session.getAttribute("userNo");
		
		boolean tf = wishService.existsByUserinfo_UserNoAndProduct_ProductNo(userNo, dto.getProductNo());
		
		if(tf == true) {
			dto.setStatus(1);
		} else if(tf == false) {
			dto.setStatus(2);
			
			wishlist.setUserinfo(userinfoService.findUserByNo(userNo));
			wishlist.setProduct(productService.findByProductNo(dto.getProductNo()));
			
			wishService.insertWish(wishlist);
			int wishCount = wishService.findAllWishByUserNo(userNo).size();
			session.setAttribute("wishCount", wishCount);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+wishlist);
		}
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		return new ResponseEntity<WishlistInsertDto>(dto, httpHeaders, HttpStatus.CREATED);
	}
	
	
	@Operation(summary = "위시리스트에 담긴 갯수 출력")
	@GetMapping("/countWishlist/{userNo}")
	public ResponseEntity<Integer> countWishlist(@PathVariable(name = "userNo") Long userNo, HttpSession session) {
		Integer count = wishService.countWishlist(userNo);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		return new ResponseEntity<>(count, httpHeaders, HttpStatus.OK);
	}
	
	
	@Operation(summary = "위시리스트 삭제")
	@DeleteMapping("/{no}")
	// delete
	public void deleteWish(@PathVariable(name = "no") Long no,HttpSession session) throws Exception{
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>실행1");
		wishService.deleteWish(no);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>실행2");
		Long loginUserNo=(Long) session.getAttribute("userNo");
		Userinfo loginUserCheck = userinfoService.findUserByNo(loginUserNo);
		wishService.deleteWish(loginUserNo);
		int wishCount = wishService.findAllWishByUserNo(loginUserCheck.getUserNo()).size();
		session.setAttribute("wishCount", wishCount);
		
	}
	
	
	
	
	@Operation(summary = "위시리스트 보기")
	@GetMapping("/find/{userNo}")
	public ResponseEntity<List<WishlistInsertDto>> findAllWish(@PathVariable(name = "userNo") Long no) {
		List<Wish> wishList = wishService.findAllWishByUserNo(no);
		List<WishlistInsertDto> wishDtoList = new ArrayList<>();
		
		for (Wish wish : wishList) {
			WishlistInsertDto wishlistInsertDtos = WishlistInsertDto.toDto(wish);
			wishDtoList.add(wishlistInsertDtos);
		}
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		return new ResponseEntity<List<WishlistInsertDto>>(wishDtoList, httpHeaders, HttpStatus.OK);
	}
	
	
	
	public ResponseEntity<String> checkWish(HttpSession session, @RequestParam Long productNo){
		if (session.getAttribute("userNo") == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
	    }

	    Long userNo = (Long) session.getAttribute("userNo");

	    // WishService에 해당 메소드 추가해주세요.
	    boolean existsInWish = wishService.existsByUserinfo_UserNoAndProduct_ProductNo(userNo, productNo);
	    		
	    if (existsInWish) {
	        return ResponseEntity.ok("이미 존재하는 상품입니다.");
	    } else {
	        return ResponseEntity.ok("위시리스트에 상품이 담겼습니다.");
	    }
	}
}