package com.itwill.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.TeamprojectAnimalcareApplicationTest;
import com.itwill.entity.Center;
import com.itwill.entity.Userinfo;
import com.itwill.entity.Volunteer;
import com.itwill.repository.VolunteerRepository;

class VolunteerServiceImplTest extends TeamprojectAnimalcareApplicationTest{
	
	@Autowired
	VolunteerService volunteerService;
	@Autowired
	UserInfoService userInfoService;
	@Autowired
	CenterService centerService;
	
	@Test
	@Disabled
	@Transactional
	@Rollback(false)
	void testFindByVolunteerNo() {
		System.out.println(volunteerService.findByVolunteerNo(5L));
	}

	@Test
	@Disabled
	@Transactional
	@Rollback(false)
	void testInsertVolunteer() throws Exception{
		
		Userinfo userinfo = userInfoService.findUserByNo(5L);
		Center center = centerService.findByCenterNo(2L);
		
		Volunteer volunteer = Volunteer.builder()
										.volunteerDate(new Date())
										.volunteerTime(20)
										.volunteerStatus("서비스테스트t")
										.center(center)
										.userinfo(userinfo)
										.build();
		volunteerService.insertVolunteer(volunteer);
	}

	@Test
	@Disabled
	@Transactional
	@Rollback(value = false)
	void testUpdateVolunteer() throws Exception{
		Volunteer volunteer = volunteerService.findByVolunteerNo(4L);
		volunteer.setVolunteerStatus("테스트진행중t");
		volunteerService.updateVolunteer(volunteer);
	}

	@Test
	@Disabled
	@Transactional
	@Rollback(value = false)
	void testDeleteVolunteer() throws Exception{
		volunteerService.deleteVolunteer(4L);
	}

	@Test
	@Disabled
	@Transactional
	void testFindAllVolunteers() {	
		System.out.println(volunteerService.findAllVolunteers());
	}

	@Test
	//@Disabled
	@Transactional
	@Rollback(value = false)
	void findVolunteertByUserNo() {
		
		System.out.println(volunteerService.findVolunteertByUserNo(2L));
	}

}
