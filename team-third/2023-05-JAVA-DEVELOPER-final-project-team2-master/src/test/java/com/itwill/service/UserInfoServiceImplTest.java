package com.itwill.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.TeamprojectAnimalcareApplicationTest;
import com.itwill.entity.Product;
import com.itwill.entity.Userinfo;

class UserInfoServiceImplTest extends TeamprojectAnimalcareApplicationTest{

	
	@Autowired
	UserInfoService userInfoService;
	
	@Test
	@Disabled
	void test() throws Exception {
		Userinfo userinfo=Userinfo.builder()
							.userId("김진우")
							.build();
		Userinfo createUser=userInfoService.create(userinfo);
		System.out.println(createUser);
	
	
	
	}
	
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void test1() throws Exception {
		Userinfo loginUser=userInfoService.login("박태환","1011");
		System.out.println(loginUser);
		
		
		
	}
	@Transactional
	@Rollback(false)
	@Test
	@Disabled
	void test2() throws Exception {
		Userinfo loginUser=userInfoService.findUserByNo(3L);
		loginUser.setUserGender("남");
		userInfoService.update(loginUser);
		System.out.println(loginUser);
		
		}
	
	@Test
	@Disabled
	void test3() throws Exception {
		userInfoService.remove(3L);
		
		
	}
	
	
	@Test
	//@Disabled
	void test4() throws Exception {
		System.out.println(userInfoService.findUserList());
		
	}

	
	
	
	
	
}
