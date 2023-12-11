package com.itwill.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.itwill.entity.Center;
import com.itwill.entity.Userinfo;
import com.itwill.entity.Visit;

import jakarta.transaction.Transactional;

@SpringBootTest
class VisitServiceImplTest {
	@Autowired
	VisitService visitService;

	@Autowired
	CenterService centerService;

	@Autowired
	UserInfoService userInfoService;

	@Transactional
	@Rollback(value = false)
	@Disabled
	@Test
	void insertVisit() throws Exception {
		Visit insertVisit = Visit.builder()
				.visitNo(null)
				.userinfo(userInfoService.findUserByNo(3L))
				.visitTime(null)
				.visitDate(null)
				.center(centerService.findByCenterNo(3L))
				.visitStatus("접수중")
				.build();
		visitService.createVisit(insertVisit);
	}

	@Test
	@Transactional
	@Disabled
	void selectVisit() {
		Visit selectVisit = visitService.findByVisitNo(2L);
		System.out.println(selectVisit);

	}
	@Test
	@Transactional
	@Rollback(value = false)
	@Disabled
	void deleteVisit()throws Exception {
		visitService.deleteVisit(3L);
	}
	@Test
	@Transactional
	@Rollback(value = false)
	@Disabled
	void updateVisit() {
		Visit findVisit = visitService.findByVisitNo(2L);
		findVisit.setVisitStatus("접수완료");
	}
	
		@Test
		@Transactional
		@Rollback(value = false)
		@Disabled
		void findVisitsByUserId() {
		    List<Visit> selectVisit = visitService.getVisitsByUserNo(2L);
		    System.out.println(selectVisit);
		}

		
	

}
