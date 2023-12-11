package com.itwill.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.itwill.TeamprojectAnimalcareApplicationTest;
import com.itwill.dao.MyPetDao;
import com.itwill.dao.UserInfoDao;
import com.itwill.entity.MyPet;
import com.itwill.entity.Userinfo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.transaction.Transactional;

class MyPetServiceImplTest extends TeamprojectAnimalcareApplicationTest{

	@Autowired
	MyPetService myPetService;
	
	@Autowired
	MyPetDao myPetDao;
	
	@Autowired
	UserInfoService userInfoService;
	
	
	@Transactional
	@Rollback(false)
	@Disabled
	@Test
	void test() {
		Userinfo userinfo=Userinfo.builder()
				.userNo(null)
				.build();
		
		
		MyPet myPet = MyPet.builder()
							.mypetNo(null)
							.mypetName("바니")
							//.mypetBirthday(new Date())
							.mypetKind("강아지")
							.userinfo(userinfo)
							.build();
				
			
		MyPet createMyPet=myPetService.Create(myPet);
		System.out.println(createMyPet);
		
	}
	
	
	@Transactional
	@Rollback(false)
	@Disabled
	@Test
	void test1() {
		
		myPetService.Delete(4L);
		
	}
	
	@Transactional
	@Rollback(false)
	@Disabled
	@Test
	void test2() throws Exception {
		
	
		Userinfo loginUser=userInfoService.findUserByNo(11L);
		
		
	  System.out.println(loginUser.getMyPets()); 
				
		
		
		
	}
	
	@Transactional
	@Rollback(false)
	@Disabled
	@Test
	void test3() throws Exception {
		Userinfo loginUser=userInfoService.findUserByNo(4L);
		List<MyPet> petList = loginUser.getMyPets();
		MyPet myPet = petList.get(0);
		myPet.setMypetName("봉남이");
		System.out.println(myPet);
		
	}
	
	@Transactional
	@Rollback(false)
	//@Disabled
	@Test
	void test4()  {
	System.out.println(myPetService.findAll());
		
		
	}
		
		
	
		
	
	
	

}
