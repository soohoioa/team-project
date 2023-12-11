package com.itwill.dto;

import java.util.Date;
import java.util.List;

import com.itwill.entity.OrderItem;
import com.itwill.entity.Orders;
import com.itwill.entity.Orderstatus;
import com.itwill.entity.Product;
import com.itwill.entity.Userinfo;

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
public class OrderItemDto {

	private Long oiNo;
	private Integer oiQty;
	private Long orderNo;
	
	
	@Default
	private Orderstatus orderstatus=new Orderstatus();
	@Default
	private Product product=new Product();
	private String reviewStatus;
	//private int orderStatusNo;
	private Long osNo;
//	private Long orderId;
	
	public static OrderItemDto toDto(OrderItem entity) {
		OrderItemDto orderItemDto = OrderItemDto.builder()
									.oiNo(entity.getOiNo())
									.oiQty(entity.getOiQty())
									.orderNo(entity.getOrders().getOrderNo())
									.product(entity.getProduct())
									.orderstatus(entity.getOrderStatus())
								 .build();
		
		return orderItemDto;
}

	public static OrderItem toEntity(OrderItemDto dto) {
		OrderItem orderItem = OrderItem.builder()
									.oiNo(dto.getOiNo())
									.oiQty(dto.getOiQty())
									.orders(Orders.builder().orderNo(dto.getOrderNo()).build())
									.orderStatus(dto.getOrderstatus())
									.product(dto.getProduct())
								 .build();
		
		return orderItem;
}
	

}