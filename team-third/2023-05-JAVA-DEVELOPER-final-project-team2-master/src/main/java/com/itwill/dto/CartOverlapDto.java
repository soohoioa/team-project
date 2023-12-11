package com.itwill.dto;

import com.itwill.entity.Cart;
import com.itwill.entity.Product;
import com.itwill.entity.Userinfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartOverlapDto {

	private Long userNo;
	private Long productNo;
	private Integer cartQty;
	
	
	public static Cart toEntity(CartOverlapDto dto) {
		Cart updateCart = Cart.builder()
				.userinfo(Userinfo.builder().userNo(dto.getUserNo()).build())
				.product(Product.builder().productNo(dto.getProductNo()).build())
				.cartQty(dto.getCartQty())
				.build();
		return updateCart;
	}
	
	
	public static CartOverlapDto toDto(Cart cart) {
		
		return CartOverlapDto.builder()
				.userNo(cart.getUserinfo().getUserNo())
				.productNo(cart.getProduct().getProductNo())
				.cartQty(cart.getCartQty())
				.build();
	}
	
	
}
