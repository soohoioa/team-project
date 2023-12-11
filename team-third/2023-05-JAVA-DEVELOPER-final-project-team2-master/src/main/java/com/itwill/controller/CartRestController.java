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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.dto.CartDto;
import com.itwill.dto.CartOverlapDto;
import com.itwill.dto.CartTotalPriceDto;
import com.itwill.dto.ProductListDto;
import com.itwill.dto.ProductNameDto;
import com.itwill.entity.Cart;
import com.itwill.entity.Product;
import com.itwill.entity.Userinfo;
import com.itwill.service.CartService;
import com.itwill.service.ProductService;
import com.itwill.service.UserInfoService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/cart")
public class CartRestController {

	@Autowired
	private CartService cartService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private ProductService productService;
	
	@Operation(summary = "카트 추가")
	@PostMapping("/inserted")
	public ResponseEntity<CartDto> insertCart(@RequestBody CartDto dto, HttpSession session) throws Exception{
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		dto.setStatus(1);
		System.out.println(session.getAttribute("userNo"));
		if (session.getAttribute("userNo") == null) {
			dto.setStatus(0);
			return new ResponseEntity<CartDto>(dto, httpHeaders, HttpStatus.CREATED);
		}
		System.out.println(">>>>>>>>>>>>>"+dto);
		// session에서 userNo가져오기
		Long userNo=(Long)session.getAttribute("userNo");
		// userNo로 user 찾기
		Userinfo user = userInfoService.findUserByNo(userNo);
		// productNo로 product 정보 가져오기
		//Long productNo = dto.getProductNo();
		Product product = productService.findByProductNo(dto.getProductNo());
		
		Cart selectCart = Cart.builder().build();
		selectCart.setUserinfo(user);
		selectCart.setProduct(product);
		selectCart.setCartQty(dto.getProductQty());
		
		System.out.println(">>>>>>>>>>>>>"+selectCart);
		
		cartService.updateOverlapCart(selectCart);
		
		int cartCount = cartService.findAllCartByUserId(userNo).size();
		session.setAttribute("cartCount", cartCount);
		
		System.out.println(">>>>>>>>>>>>>>>>>");
		return new ResponseEntity<CartDto>(dto, httpHeaders, HttpStatus.CREATED);
	}
	
	
	@Operation(summary = "카트 번호로 한 개 삭제")
	@DeleteMapping("/delete/{cartNo}")
	public void deleteByCartNo(@PathVariable(name = "cartNo") Long cartNo, HttpSession session) throws Exception {
		if (session.getAttribute("userNo") == null) {
			throw new Exception("로그인 하세요.");
		}
		Long loginUserNo=(Long) session.getAttribute("userNo");
		Userinfo loginUserCheck = userInfoService.findUserByNo(loginUserNo);
		cartService.deleteById(cartNo);
		int cartCount = cartService.findAllCartByUserId(loginUserCheck.getUserNo()).size();
		session.setAttribute("cartCount", cartCount);
	}

	
	@Operation(summary = "유저 아이디로 카트 전체 삭제")
	@DeleteMapping("/delete/user/{userNo}")
	public ResponseEntity<CartDto> deleteByUserNo(@PathVariable(name = "userNo") Long userNo, HttpSession session) throws Exception {

		if (session.getAttribute("userNo") == null) {
			throw new Exception("로그인 하세요.");
		}
		
		cartService.deleteByUserId(userNo);
		
		HttpHeaders httpHeaders = new HttpHeaders();

		return new ResponseEntity<CartDto>(httpHeaders, HttpStatus.OK);
	}

	
	@Operation(summary = "상품 수량 업데이트")
	@PutMapping("/{cartNo}")
	public ResponseEntity<CartDto> updateCartQty(@PathVariable(name = "cartNo") Long cartNo, @RequestBody CartDto dto, HttpSession session) throws Exception {
		if (session.getAttribute("userNo") == null) {
			throw new Exception("로그인 하세요.");
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>업뎃"+cartNo);
		Cart findCart = cartService.findByCartNo(cartNo);

		findCart.setCartQty(dto.getCartQty());
		cartService.update_qty(findCart);

		CartDto updatedDto = CartDto.toDto(findCart);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		return new ResponseEntity<CartDto>(updatedDto, httpHeaders, HttpStatus.OK);
	}

	
	@Operation(summary = "카트번호로 선택하기")
	@GetMapping("/{cartNo}")
	public ResponseEntity<CartDto> findByCartNo(@PathVariable(name = "cartNo") Long cartNo, HttpSession session) throws Exception {
		if (session.getAttribute("userNo") == null) {
			throw new Exception("로그인 하세요.");
		}
		
		Cart findCart = cartService.findByCartNo(cartNo);
		CartDto cartDto = CartDto.toDto(findCart);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		return new ResponseEntity<CartDto>(cartDto, httpHeaders, HttpStatus.OK);
	}

	
	@Operation(summary = "유저 카트 리스트")
	@GetMapping("/cartList/{userNo}")
	public ResponseEntity<List<CartDto>> cartList(@PathVariable(name = "userNo") Long no, HttpSession session) throws Exception{
		if (session.getAttribute("userNo") == null) {
			throw new Exception("로그인 하세요.");
		}
		
		List<Cart> cartList = cartService.findAllCartByUserId(no);
		List<CartDto> cartsDto = new ArrayList<CartDto>();

		for (Cart cart : cartList) {
			cartsDto.add(CartDto.toDto(cart));
		}
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		return new ResponseEntity<List<CartDto>>(cartsDto, httpHeaders, HttpStatus.OK);
	}

	
	@Operation(summary = "중복 상품 업데이트")
	@PutMapping
	public ResponseEntity<List<CartDto>> updateOverlapCart(CartOverlapDto dto, HttpSession session) throws Exception {
		if (session.getAttribute("userNo") == null) {
			throw new Exception("로그인 하세요.");
		}
		
		Cart updateCart = cartService.updateOverlapCart(CartOverlapDto.toEntity(dto));
		CartOverlapDto cartOverlapDto = CartOverlapDto.toDto(updateCart);
		
		List<Cart> cartList = cartService.findAllCartByUserId(dto.getUserNo());
		List<CartDto> cartsDto = new ArrayList<CartDto>();

		for (Cart cart : cartList) {
			cartsDto.add(CartDto.toDto(cart));
		}
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		return new ResponseEntity<List<CartDto>>(cartsDto, httpHeaders, HttpStatus.OK);
	}
	
	
	@Operation(summary = "카트에 있는 모든 상품 가격")
	@GetMapping("/totalPrice/{userNo}")
	public ResponseEntity<CartTotalPriceDto> cartTotalPrice(@PathVariable(name = "userNo") Long userNo, HttpSession session) throws Exception{
		if (session.getAttribute("userNo") == null) {
			throw new Exception("로그인 하세요.");
		}
		
		Integer totalPrice = cartService.cartTotalPrice(userNo);
		CartTotalPriceDto total = new CartTotalPriceDto();
		total.setTotalPrice(totalPrice);

		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		return new ResponseEntity<CartTotalPriceDto>(total, httpHeaders, HttpStatus.OK);
	}
	
	/*
	@Operation(summary = "카트에 있는 모든 상품 가격")
	@PostMapping("/updateCart")
	public ResponseEntity<Cart> updateCart(HttpSession session,@RequestBody Integer cartQty) throws Exception{
		Long userNo = (Long)session.getAttribute("userNo");
		
		if (userNo == null) {
			throw new Exception("로그인 하세요.");
		}
		
		System.out.println(">>>>>>>cartqty"+cartQty);
		 000
		Integer totalPrice = cartService.cartTotalPrice(userNo);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		return new ResponseEntity<Cart>(total, httpHeaders, HttpStatus.OK);
	}
	*/
	
	
	

	 
}