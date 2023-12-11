package com.itwill.dto;

import java.util.Date;
import java.util.List;

import com.itwill.entity.Orders;
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
public class OrderUpdateDto {

	private Long orderNo;
	private String orderAddress;
	
	public static OrderUpdateDto toDto(Orders entity) {
	OrderUpdateDto updateDto = OrderUpdateDto.builder()
										.orderNo(entity.getOrderNo())
										.orderAddress(entity.getOrderAddress())
										.build();
		
	return updateDto;
	}
	
	
	public static Orders toEntity(OrderUpdateDto dto) {

		Orders order = Orders.builder()
				.orderNo(dto.getOrderNo())
				.orderAddress(dto.getOrderAddress())
				.build();
		
		return order;
		}
	
}
