package com.itwill.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.itwill.entity.Visit;

import jakarta.transaction.Transactional;

@SpringBootTest
class VisitDaoImplTest {

	@Autowired
	VisitDao visitDao;

	@Autowired
	UserInfoDao userInfoDao;

	@Autowired
	CenterDao centerDao;

	@Transactional
	@Rollback(false)
	@Test
	@Disabled
	void insetVisit() {
		Visit visit = Visit.builder()

				.visitDate(null)
				.visitStatus("방문완료")
				.visitTime(null)
				.userinfo(userInfoDao.findByNo(2L))
				.center(centerDao.findByCenterNo(2L))
				.build();
		visitDao.createVisit(visit);
	}

	@Test
	@Transactional
	@Disabled
	void selectVisit() {
		Visit selectVisit = visitDao.findByVisitNo(2L);
		System.out.println(selectVisit);
	}

	@Test
	@Transactional
	@Rollback(value = false)
	@Disabled
	void deleteVisit() throws Exception {
		visitDao.deleteVisit(1L);
	}

	@Test
	@Transactional
	@Rollback(value = false)
	@Disabled
	void updateVisit() {
		Visit findVisit = visitDao.findByVisitNo(2L);
		findVisit.setVisitStatus("대기중");
		System.out.println(findVisit);
	}

	@Test
	@Transactional
	@Rollback(value = false)
	@Disabled
	void findVisitsByUserId() {
		List<Visit> selectVisit = visitDao.getVisitsByUserNo(2L);
		System.out.println(selectVisit);
	}

}
