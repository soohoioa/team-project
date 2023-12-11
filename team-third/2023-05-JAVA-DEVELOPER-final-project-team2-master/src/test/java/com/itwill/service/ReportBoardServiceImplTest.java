package com.itwill.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.TeamprojectAnimalcareApplicationTest;
import com.itwill.dao.ReportBoardDao;
import com.itwill.dao.UserInfoDao;
import com.itwill.entity.ReportBoard;
import com.itwill.entity.Userinfo;

class ReportBoardServiceImplTest extends TeamprojectAnimalcareApplicationTest{
	
	@Autowired
	UserInfoDao userInfoDao;
	
	@Autowired
	ReportBoardService reportBoardService;
	
	@Test
	@Transactional
	@Rollback(false)
	//@Disabled
	void test() {
		Userinfo userinfo = userInfoDao.findByUserId("ccc@naver.com");
		ReportBoard reportBoard = ReportBoard.builder()
									.boardContent("내용")
									.boardReadCount(0)
									.boardRegisterDate(null)
									.boardTitle("제목")
									.userinfo(userinfo)
									.build();
		reportBoardService.Create(reportBoard);
	}
	
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void test2() {
		reportBoardService.deleteById(9L);
	}
	
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void test3() {
		ReportBoard reportBoard = reportBoardService.findByBoardNo(8L);
		reportBoard.setBoardContent("내용수정");
		reportBoardService.update(reportBoard);
	}
	
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void test4() {
		 System.out.println(reportBoardService.findByUserId("김창섭")); 
	}
	
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void test5() {
		System.out.println(reportBoardService.findAllByLikeUserId("창"));  
		System.out.println(reportBoardService.findAllByLikeUserId("섭"));  
		System.out.println(reportBoardService.findAllByLikeUserId("김"));  
	}
	
	@Test
	 @Transactional
	 @Rollback(false)
	 @Disabled
	void test6() {
		System.out.println(reportBoardService.findAll()); 
	
	}
	
}
