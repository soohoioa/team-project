package com.itwill.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwill.entity.Orderstatus;
import com.itwill.repository.OrderStatusRepository;
@Repository
public class OrderStatusImpl implements OrderStatusDao {

	@Autowired
	OrderStatusRepository orderStatusRepository;
	@Override
	public Orderstatus findOrderStatus(Long osNo) {
		Orderstatus orderstatus=orderStatusRepository.findById(osNo).get();		
		return orderstatus;
	}

}
