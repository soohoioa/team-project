package com.itwill.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Date;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.TeamprojectAnimalcareApplicationTest;
import com.itwill.entity.MyPet;
import com.itwill.entity.Userinfo;

class MyPetDaoImplTest extends TeamprojectAnimalcareApplicationTest{

	@Autowired
	MyPetDao myPetDao;
	@Autowired
	UserInfoDao userInfoDao;
	
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void test() {
		
		Userinfo userinfo = userInfoDao.findByNo(4L);
		MyPet myPet = MyPet.builder()
							.mypetNo(null)
							.mypetName("보리")
							.mypetKind("강아지")
							//.mypetBirthday(new Date())
							.userinfo(userinfo)
							.build();
		
		myPetDao.CreatePet(myPet);
		
		
		userinfo = userInfoDao.findByNo(5L);
		myPet = MyPet.builder()
					.mypetNo(null)
					.mypetName("율무")
					.mypetKind("강아지")
					//.mypetBirthday(new Date())
					.userinfo(userinfo)
					.build();
		
		myPetDao.CreatePet(myPet);
		
		userinfo = userInfoDao.findByNo(10L);
		myPet = MyPet.builder()
					.mypetNo(null)
					.mypetName("나비")
					.mypetKind("고양이")
					//.mypetBirthday(new Date())
					.userinfo(userinfo)
					.build();
		
		myPetDao.CreatePet(myPet);
		
	}
	
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void test2() {
		myPetDao.DeletePet(2L);
	}
	
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void test3() {
		MyPet pet= myPetDao.findByNo(5L);
		pet.setMypetName("두부");
		myPetDao.UpdatePet(pet);
	}
	
	@Test
	@Transactional
	@Rollback(false)
	//@Disabled
	void test4() {
		System.out.println(myPetDao.findMyPetListByuserNo(1L));	
		System.out.println(myPetDao.findLeaderMyPet(1L));	
	}
	
	

}
