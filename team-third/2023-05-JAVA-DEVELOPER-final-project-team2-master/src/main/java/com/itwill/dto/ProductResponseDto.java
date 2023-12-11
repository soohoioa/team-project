package com.itwill.dto;

import com.itwill.entity.Product;

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
public class ProductResponseDto {

	private Long productNo;
	
	private String productName;
	private Integer productPrice;
	private String productPetCategory;
	private String productImage;
	private String productDetailImage;
	
	public static ProductResponseDto toDto(Product entity) {
		return ProductResponseDto.builder()
				.productNo(entity.getProductNo())
				.productName(entity.getProductName())
				.productPrice(entity.getProductPrice())
				.productPetCategory(entity.getProductPetCategory())
				.productImage(entity.getProductImage())
				.productDetailImage(entity.getProductDetailImage())
				.build();
	}
}
