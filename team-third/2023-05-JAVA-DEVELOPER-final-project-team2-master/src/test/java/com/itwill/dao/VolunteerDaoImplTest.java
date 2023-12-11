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
import org.springframework.transaction.annotation.Transactional;

import com.itwill.TeamprojectAnimalcareApplicationTest;
import com.itwill.entity.Center;
import com.itwill.entity.Userinfo;
import com.itwill.entity.Volunteer;

@SpringBootTest
class VolunteerDaoImplTest extends TeamprojectAnimalcareApplicationTest{
	
	@Autowired
	VolunteerDao volunteerDao;
	@Autowired
	UserInfoDao userInfoDao;
	@Autowired
	CenterDao centerDao;
	
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void findVolunteerListWithPoints() { // 봉사와 포인트 정보 함께 조회
		Userinfo userinfo = new Userinfo();
		userinfo.setUserNo(9L);
		userinfo.setUserPoint(14000);
		
		Volunteer volunteer = new Volunteer();
		volunteer.setVolunteerNo(62L);
		
		System.out.println(userinfo);
		System.out.println(volunteer);		
	}
		
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void addPointsToVolunteer() {  // 봉사에 포인트 적립
		Userinfo userinfo = new Userinfo();
	    userinfo.setUserNo(9L);  // 유저 번호 설정
	    userinfo.setUserPoint(14000);  // 초기 포인트 설정

	    Volunteer volunteer = new Volunteer();
	    volunteer.setVolunteerNo(62L);  // 봉사 번호
	    volunteer.setVolunteerTime(9);  // 봉사 시간 고정
	    volunteer.setUserinfo(userinfo);  // 유저 정보 설정

	    volunteerDao.addPointsToVolunteer(62L, 3000);
	}
	

	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void testInsertVolunteer() {
		
		Userinfo userinfo = userInfoDao.findByNo(5L);
		
		Center center = centerDao.findByCenterNo(3L);	
	
		Volunteer volunteer = Volunteer.builder()
									   .userinfo(userinfo)
									   .center(center)
									   .volunteerDate(new Date())
									   .volunteerTime(11)
									   .volunteerStatus("테스트t")
									   .build();	
		volunteerDao.insertVolunteer(volunteer);
		
	}

	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void selectVolunteer() {
		Volunteer selectVolunteer = volunteerDao.findByVolunteerNo(2L);
		System.out.println(selectVolunteer);
	}

	@Test
	@Disabled
	@Transactional
	@Rollback(value = false)
	void testUpdateVolunteer() throws Exception{
		Volunteer volunteer = volunteerDao.findByVolunteerNo(2L);
		volunteer.setVolunteerTime(12);
		volunteer.setVolunteerStatus("테스트t ");
		volunteerDao.updateVolunteer(volunteer);
	}

	@Test
	@Disabled
	@Transactional
	@Rollback(value = false)
	void testDeleteVolunteer() throws Exception{
		volunteerDao.deleteVolunteer(1L);
	}
	
	
	@Test
	@Disabled
	@Transactional
	@Rollback(value = false)
	void testFindVolunteerByUserId() {
		List<Volunteer> selectVolunteer = volunteerDao.findVolunteertByUserNo(2L);
		System.out.println(selectVolunteer);
	}
	
}
