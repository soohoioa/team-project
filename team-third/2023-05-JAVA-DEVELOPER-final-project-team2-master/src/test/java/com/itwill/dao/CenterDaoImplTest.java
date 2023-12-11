package com.itwill.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.itwill.entity.Center;
import com.itwill.entity.Visit;
import com.itwill.repository.VisitRepository;

import jakarta.transaction.Transactional;
@SpringBootTest
class CenterDaoImplTest {
	@Autowired
	CenterDao centerDao;

	@Transactional
	@Rollback(false)
	@Test
	//@Disabled
	void insetCenter() {


		Center center = Center.builder()
				.centerNo(null)
				.centerName("동물누리 보호센터")
				.centerPhoneNumber("0507-1317-6943")
				.centerOpenCloseTime("09:00 ~ 18:00")
				.centerLocal("시흥시")
				.centerImage("sihung.jpeg")
				.build();
		centerDao.createCenter(center);
	}
	
	@Test
	@Disabled
	@Transactional
	void  selectCenter() {
		Center selectCenter = centerDao.findByCenterNo(1L);
		System.out.println(selectCenter);
	}
	
	@Test
	@Transactional
	@Rollback(value = false)
	@Disabled
	void deleteCenter()throws Exception {
		centerDao.deleteCenter(5L);
	}
	@Test
	@Transactional
	@Rollback(value = false)
	@Disabled
	void updateCenter() {
		Center findCenter =  centerDao.findByCenterNo(2L);
		findCenter.setCenterName("일이삼보호소");
		System.out.println(findCenter);
	}
	
	@Test
	@Transactional
	@Rollback(value = false)
	@Disabled
	void findByContainsTest() {
		List<Center> findCenter = centerDao.findByName("이");
		System.out.println(findCenter);
	}
}
