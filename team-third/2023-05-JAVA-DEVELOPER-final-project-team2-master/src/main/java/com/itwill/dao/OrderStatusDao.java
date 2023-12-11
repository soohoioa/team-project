package com.itwill.dao;

import com.itwill.entity.Orderstatus;

public interface OrderStatusDao {

	Orderstatus findOrderStatus(Long osNo);

}