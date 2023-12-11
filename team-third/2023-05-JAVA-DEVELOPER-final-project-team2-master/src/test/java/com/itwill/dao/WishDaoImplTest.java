package com.itwill.dao;	

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.TeamprojectAnimalcareApplicationTest;
import com.itwill.entity.Wish;

class WishDaoImplTest extends TeamprojectAnimalcareApplicationTest{

	@Autowired
	WishDao wishDao;
	@Autowired
	UserInfoDao userInfoDao;
	@Autowired
	ProductDao productDao;
	
	@Test
	@Disabled
	@Transactional
	@Rollback(false)
	void insert() {
		
		Wish insert = Wish.builder()
				.product(productDao.findByProductNo(1L))
				.userinfo(userInfoDao.findByNo(2L))
				.wishNo(null)
				.build();
		wishDao.insertWish(insert);
	}

	@Test
	@Disabled
	@Transactional
	@Rollback(false)
	void delete() throws Exception{
		wishDao.deleteWish(1L);
	}
	
	@Test
	//@Disabled
	@Transactional
	@Rollback(false)
	void findAll() {
		List<Wish> wishList = wishDao.findAllWishByUserNo(15L);
		System.out.println(wishList);
	}
	
}
