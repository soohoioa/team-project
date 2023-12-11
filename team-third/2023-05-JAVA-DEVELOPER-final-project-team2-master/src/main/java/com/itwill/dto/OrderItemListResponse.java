package com.itwill.dto;

import java.util.List;

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
public class OrderItemListResponse {
	private List<OrderItemDto> orderItemDtos;
    private List<OrderStatusDto> orderStatusDtos;

}


