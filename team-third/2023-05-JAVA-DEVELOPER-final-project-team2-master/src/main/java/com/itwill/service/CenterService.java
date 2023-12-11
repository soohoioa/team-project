package com.itwill.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itwill.entity.Center;

public interface CenterService {
	
	Center createCenter(Center center);
	
	Center findByCenterNo(Long centerNo);
	
	Center updateCenter(Center center);
	
	void deleteCenter(Long centerNo);
	
	List<Center> findAllCenters();
	
	List<Center> findByName(String centerName);
	
	public Page<Center> centerFindAllPage(Pageable pageable);
}
