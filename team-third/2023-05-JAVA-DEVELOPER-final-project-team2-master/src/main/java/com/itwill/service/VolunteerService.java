package com.itwill.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.dto.VolunteerTimePointDto;
import com.itwill.entity.Volunteer;

public interface VolunteerService {
	
	Volunteer findByVolunteerNo(Long no); // 봉사 목록 찾기
	
	Volunteer insertVolunteer(Volunteer volunteer);
	
	Volunteer updateVolunteer(Volunteer volunteer) throws Exception;

	void deleteVolunteer(Long no) throws Exception;
	
	List<Volunteer> findAllVolunteers(); // 봉사 목록 전체 찾기

	List<Volunteer> findVolunteertByUserNo(Long no); // userNo 로 목록 검색
	
	//List<Volunteer> findByVolunteerListAdmin(Volunteer volunteer); // admin로그인후 date 기준으로 정렬

	
	
	List<Volunteer> findVolunteerListWithPoints(Volunteer volunteer, Integer userPoint);
	// 봉사와 포인트 정보 함께 조회
	
	void addPointsToVolunteer(Long volunteerNo, Integer pointsToAdd);
	// 봉사에 포인트 적립
	
	// 페이징
    Page<Volunteer> volunteerFindAllPage(Pageable pageable);
}
