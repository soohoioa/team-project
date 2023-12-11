package com.itwill.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.TeamprojectAnimalcareApplicationTest;
import com.itwill.entity.Userinfo;

class UserInfoDaoImplTest extends TeamprojectAnimalcareApplicationTest{
	
	@Autowired
	UserInfoDao userinfoDao;
	
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void test1() {
		Userinfo userinfo = Userinfo.builder()
							.userId("heoseungbum")
							.userName("테스트222")
							.build();
		System.out.println(userinfo);
		userinfoDao.CreateUser(userinfo);
	}
	@Test
	@Disabled
	void test2() {
		userinfoDao.DeleteUserByNo(8L);
	}
	
	@Test
	@Disabled
	@Transactional
	void test3() {
		System.out.println(userinfoDao.findAll());
	}
	@Test
	@Disabled
	@Transactional
	void test4() {
		System.out.println(userinfoDao.findByNo(3L));
	}
	@Test
	@Disabled
	@Transactional
	@Rollback(false)
	void test5() {
		Userinfo userinfo = userinfoDao.findByNo(3L);
		userinfo.setUserAddress("제주도");
		System.out.println(userinfoDao.UpdateUser(userinfo));
	}
	@Test
	@Disabled
	@Transactional
	
	void test6() {
		 
		 System.out.println(userinfoDao.findByUserId("박태환"));
		
	}
	@Test
	@Disabled
	@Transactional
	void test7() {
		
		System.out.println(userinfoDao.findByUserPhone("010-7111-1111"));
		
	}
	
	@Test
	//@Disabled
	@Transactional
	void test8() {
		
		System.out.println(userinfoDao.findPasswordByUserIdPhoneNumber("박태환", "010-7111-1111"));
		
	}
	
	
	
	

}
