package com.itwill.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.itwill.entity.OrderItem;
import com.itwill.entity.Orders;
import com.itwill.entity.Userinfo;
import com.itwill.service.UserInfoService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrdersDto {
	
	private Long orderNo;
	private Date orderDate;
	private Integer orderPrice;
	private String orderAddress;
	private String orderDesc;
	private Long userNo;
	
	private String couponId;
	private String userPoint;
	
	private Userinfo userinfo;
	private List<OrderItemDto> orderItemDtos = new ArrayList();
	private int reviewStatus;
	private List<Long> cartNo;
	
	public static OrdersDto toDto(Orders entity) {
		OrdersDto orderDto = OrdersDto.builder()
										.orderNo(entity.getOrderNo())
										.orderDate(entity.getOrderDate())
										.orderAddress(entity.getOrderAddress())
										.orderDesc(entity.getOrderDesc())
										.userNo(entity.getUserinfo().getUserNo())
										.orderPrice(entity.getOrderPrice())
										.build();

		List<OrderItemDto> orderItemDtoList = new ArrayList<>();
		for (OrderItem orderItem : entity.getOrderItems()) {
			
			orderItemDtoList.add(OrderItemDto.toDto(orderItem));
		}
		orderDto.setOrderItemDtos(orderItemDtoList);
		
		return orderDto;
	}

	public static Orders toEntity(OrdersDto dto) {

		Orders order = Orders.builder().orderNo(dto.getOrderNo()).orderDate(dto.getOrderDate())
				.orderAddress(dto.getOrderAddress()).orderDesc(dto.getOrderDesc()).orderPrice(dto.getOrderPrice())
				.userinfo(Userinfo.builder().userNo(dto.getUserNo()).build()).build();
		/*
		 * List<OrderItem> orderItemList = new ArrayList<>(); for (OrderItemDto
		 * orderItemDto : dto.getOrderItemDtos()) {
		 * orderItemList.add(OrderItemDto.toEntity(orderItemDto)); }
		 * order.setOrderItems(orderItemList);
		 */
		return order;
	}
	
	

}


