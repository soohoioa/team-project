package com.itwill.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.itwill.dao.CenterDao;
import com.itwill.entity.Cart;
import com.itwill.entity.Center;
import com.itwill.repository.CartRepository;
@Service
public class CenterServiceImpl implements CenterService {
	private final CenterDao centerDao;
	@Autowired
    public CenterServiceImpl(CenterDao centerDao) {
        this.centerDao = centerDao;
	}
	@Override
	public Center createCenter(Center center) {
		// 센터 리스트생성
		return centerDao.createCenter(center);
	}

	@Override
	public Center findByCenterNo(Long centerNo) {
		// 센터 검색
		return centerDao.findByCenterNo(centerNo);
	}

	@Override
	public Center updateCenter(Center center) {
		// 센터 수정
		return centerDao.updateCenter(center);
	}

	@Override
	public void deleteCenter(Long centerNo) {
		// 센터 삭제
		centerDao.deleteCenter(centerNo);
	}
	
	@Override
	public List<Center> findAllCenters() {
		// 모든 센터리스트 검색
		return centerDao.findAllCenters();
	}

	@Override
	public List<Center> findByName(String centerName) {
		// 센터 이름으로 검색
		return centerDao.findByName(centerName);
	}
	
	@Override
	public Page<Center> centerFindAllPage(Pageable pageable) {
		Page<Center> centerList = centerDao.centerFindAllPage(pageable);
		return centerList;
	}
	
}