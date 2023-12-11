package com.itwill.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itwill.entity.Center;
import com.itwill.entity.ReportBoard;

public interface CenterDao {

	public Center createCenter(Center center);
	
	public Center findByCenterNo(Long centerNo);

	public Center updateCenter(Center center);
	
	public void deleteCenter(Long centerNo);
	
	public List<Center> findAllCenters();
	
	public List<Center> findByName(String centerName);//센터이름검색
	
	public Page<Center> centerFindAllPage(Pageable pageable);
}
