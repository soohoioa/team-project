package com.itwill.dto;

import com.itwill.entity.Product;

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
public class ProductSearchDto {
	private String productName;
	
	public static ProductSearchDto toDto(Product product) {
		ProductSearchDto productSearchDto = ProductSearchDto.builder()
				.productName(product.getProductName())
				.build();
		return productSearchDto;
	}
}
