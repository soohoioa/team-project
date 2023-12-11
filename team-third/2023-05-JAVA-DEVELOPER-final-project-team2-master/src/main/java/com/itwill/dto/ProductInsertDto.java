package com.itwill.dto;

import com.itwill.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ProductInsertDto {

	private Long productNo;
	private String productName;
	private Integer productPrice;
	private String productCategory;
	private String productPetCategory;
	// 제품 대표이미지
	private String productImage;
	// 제품 상세이미지
	private String productDetailImage;
	
	public static Product toEntity(ProductInsertDto dto) {
		Product insertProduct = Product.builder()
				.productNo(dto.getProductNo())
				.productName(dto.getProductName())
				.productPrice(dto.getProductPrice())
				.productCategory(dto.getProductCategory())
				.productPetCategory(dto.getProductPetCategory())
				.productImage(dto.getProductImage())
				.productDetailImage(dto.getProductDetailImage())
				.build();
		return insertProduct;
	}
}
