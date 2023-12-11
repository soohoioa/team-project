package com.itwill.dto;

import com.itwill.entity.Orderstatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrderStatusDto {
	private Long osNo;
	private String osImage;
	private String osDesc;
	
	
	
	
	public static OrderStatusDto toDto(Orderstatus entity) {
		OrderStatusDto orderStatusDto=OrderStatusDto.builder()
				.osNo(entity.getOsNo())
				.osImage(entity.getOsImage())
				.osDesc(entity.getOsDesc())
				.build();
		return orderStatusDto;
		
	}
}


