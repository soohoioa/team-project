package com.itwill.dto;

import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.itwill.entity.Cart;
import com.itwill.entity.Product;
import com.itwill.entity.Userinfo;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartDto {

	private Long cartNo;
	private Integer cartQty;
	private Long productNo;
	private Long userNo;
	private Integer productQty;
	private Integer status;
	private Product product;
	
	public static Cart toEntity(CartDto dto) {
		Cart inserCart = Cart.builder()
				.cartNo(dto.getCartNo())
				.cartQty(dto.getCartQty())
				.product(Product.builder().productNo(dto.getProductNo()).build())
				.userinfo(Userinfo.builder().userNo(dto.getUserNo()).build())
				.build();
		return inserCart;
	}
	
	public static CartDto toDto(Cart cart) {
		
		return CartDto.builder()
				.cartNo(cart.getCartNo())
				.cartQty(cart.getCartQty())
				.productNo(cart.getProduct().getProductNo())
				.userNo(cart.getUserinfo().getUserNo())
				.build();
	}
	
	
	
/*
	@Operation(summary = "카트 추가")
	@GetMapping
	public ResponseEntity<CartDto> insertCart(CartDto dto) {

		cartService.insertCart(CartDto.toEntity(dto));

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		return new ResponseEntity<CartDto>(dto, httpHeaders, HttpStatus.CREATED);
	}
	*/
}
