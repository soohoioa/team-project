package com.itwill.dto;

import com.itwill.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateDto {

	private Long productNo;
	private String productName;
	private Integer productPrice;
	private String productImage;
	
	public static ProductUpdateDto toDto(Product entity) {
		return ProductUpdateDto.builder()
				.productNo(entity.getProductNo())
				.productName(entity.getProductName())
				.productPrice(entity.getProductPrice())
				.productImage(entity.getProductImage())
				.build();
	}
}
