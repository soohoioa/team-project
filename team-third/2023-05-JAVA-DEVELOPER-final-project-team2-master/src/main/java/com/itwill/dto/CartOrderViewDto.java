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
public class CartOrderViewDto {

	private Long cartNo;
	//private Integer cartQty;
	
	//private Long userNo;
//	private ProductNameDto productNameDto;
	/*
	 * public static Cart toEntity(CartOrderViewDto dto) { Cart inserCart =
	 * Cart.builder() .cartNo(dto.getCartNo()) .cartQty(dto.getCartQty())
	 * .product(Product.builder().productNo(dto.getProductNameDto().getProductNo().
	 * build()) .userinfo(Userinfo.builder().userNo(dto.getUserNo()).build())
	 * .build(); return inserCart; }
	 */
	
	/*
	 * public static CartOrderViewDto toDto(Cart cart) {
	 * 
	 * return CartOrderViewDto.builder() .cartNo(cart.getCartNo())
	 * .cartQty(cart.getCartQty())
	 * .productNameDto(ProductNameDto.builder().productNo(cart.getProduct().
	 * getProductNo()).build()) .userNo(cart.getUserinfo().getUserNo()) .build();
	 * 
	 * }
	 */
	
	
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
