package com.itwill.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.entity.Orderstatus;
import com.itwill.entity.Userinfo;

public interface OrderStatusRepository extends JpaRepository<Orderstatus, Long>{
	
}
