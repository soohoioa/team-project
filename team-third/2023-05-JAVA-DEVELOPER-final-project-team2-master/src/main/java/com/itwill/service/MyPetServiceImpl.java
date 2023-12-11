package com.itwill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.dao.MyPetDao;
import com.itwill.entity.MyPet;

@Transactional
@Service
public class MyPetServiceImpl implements MyPetService{
	
	@Autowired
	private MyPetDao myPetDao;
	
	// 마이펫 입력
	@Override
	public MyPet Create(MyPet myPet) {
		return myPetDao.CreatePet(myPet);
	}
	
	// 마이펫 삭제
	@Override
	public void Delete(Long myPetNo) {
		myPetDao.DeletePet(myPetNo);
	}
	
	// 마이펫 수정
	@Override
	public MyPet Update(MyPet myPet) {
		return myPetDao.UpdatePet(myPet);
	}
	
	// 마이펫 전체 리스트
	@Override
	public List<MyPet> findAll() {
		return myPetDao.findAll();
	}

	@Override
	public List<MyPet> findMyPetListByuserNo(Long userNo) {
		return myPetDao.findMyPetListByuserNo(userNo);
	}

	// 마이펫 중 1개 삭제
	@Override
	public void deleteMypetByUserNo( Long mypetNo) {
		myPetDao.deleteMypetByUserNo(mypetNo);
	}
	
	
	// 마이펫 전체 삭제
	@Override
	public void deleteMypetAllByUserNo(Long userNo) {
		myPetDao.deleteMypetAllByUserNo(userNo);
	}

	@Override
	public MyPet findLeaderMyPet(Long userNo) {
		
		return myPetDao.findLeaderMyPet(userNo);
	}
	
	
}
