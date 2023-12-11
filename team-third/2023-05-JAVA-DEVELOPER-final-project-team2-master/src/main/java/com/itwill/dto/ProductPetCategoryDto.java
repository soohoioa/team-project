package com.itwill.dto;

import com.itwill.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductPetCategoryDto {
	private Long productNo;
	private Integer productPrice;
	private String productImage;
	private String productName;
	private String productCategory;
	private String productPetCategory;
	private Double productStarAvg;
	
	public static Product toEntity(ProductListDto dto) {
		Product product = Product.builder()
				.productNo(dto.getProductNo())
				.productPrice(dto.getProductPrice())
				.productImage(dto.getProductImage())
				.productName(dto.getProductName())
				.productCategory(dto.getProductCategory())
				.productPetCategory(dto.getProductPetCategory())
				.productStarAvg(dto.getProductStarAvg())
				.build();
		return product;
	}
	
	public static ProductPetCategoryDto toDto(Product product) {
		ProductPetCategoryDto dto = ProductPetCategoryDto.builder()
				.productNo(product.getProductNo())
				.productPrice(product.getProductPrice())
				.productImage(product.getProductImage())
				.productName(product.getProductName())
				.productCategory(product.getProductCategory())
				.productPetCategory(product.getProductPetCategory())
				.productStarAvg(product.getProductStarAvg())
				.build();
		return dto;
	}
}
