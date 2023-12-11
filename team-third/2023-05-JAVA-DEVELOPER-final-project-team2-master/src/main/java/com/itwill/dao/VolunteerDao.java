package com.itwill.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itwill.entity.Volunteer;

public interface VolunteerDao {

	public Volunteer insertVolunteer(Volunteer volunteer);
	
	public Volunteer findByVolunteerNo(Long no);

	public Volunteer updateVolunteer(Volunteer volunteer) throws Exception;
	
	public void deleteVolunteer(Long no) throws Exception;
	
	// 전체조회
	public List<Volunteer> findVolunteerList();

	// userNo 로 Volunteer 조회
	public List<Volunteer> findVolunteertByUserNo(Long userNo);	
	
	
	
	// 봉사와 포인트 정보 함께 조회
    public List<Volunteer> findVolunteerListWithPoints(Long volunteerNo,Integer userPoint);
	
    // 봉사에 포인트 적립
    public void addPointsToVolunteer(Long volunteerNo, Integer pointsToAdd);
	// pointsToAdd는 적립할 포인트의 양을 나타내는 변수?

    // 페이징
    Page<Volunteer> volunteerFindAllPage(Pageable pageable);
    
}
