package com.itwill.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwill.entity.MyPet;
import com.itwill.entity.Userinfo;
import com.itwill.repository.MyPetRepository;
import com.itwill.repository.UserinfoRepository;

@Repository
public class MyPetDaoImpl implements MyPetDao {
	@Autowired
	private MyPetRepository myPetRepository;

	@Override
	public MyPet CreatePet(MyPet myPet) {
		return myPetRepository.save(myPet);
	}

	@Override
	public void DeletePet(Long mypetNo) {
		if (myPetRepository.findById(mypetNo).isPresent()) {
			myPetRepository.deleteById(mypetNo);
		}
	}

	@Override
	public MyPet UpdatePet(MyPet myPet) {
		return myPetRepository.save(myPet);
	}

	@Override
	public List<MyPet> findAll() {
		return myPetRepository.findAll();
	}

	@Override
	public MyPet findByNo(Long mypetNo) {
		MyPet pet = myPetRepository.findById(mypetNo).get();
		return pet;
	}

	@Override
	public List<MyPet> findMyPetListByuserNo(Long userNo) {
		return myPetRepository.findMyPetListByuserNo(userNo);
	}

	@Override
	public void deleteMypetByUserNo(Long mypetNo) {
		myPetRepository.deleteMypetByUserNo(mypetNo);
	}
	
	@Override
	public void deleteMypetAllByUserNo(Long userNo) {
		myPetRepository.deleteMypetAllByUserNo(userNo);
	}

	@Override
	public MyPet findLeaderMyPet(Long userNo) {
		return myPetRepository.findLeaderMyPet(userNo);
		
	}
	
}
