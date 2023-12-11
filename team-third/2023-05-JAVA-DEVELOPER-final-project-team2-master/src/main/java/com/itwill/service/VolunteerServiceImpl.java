package com.itwill.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.dao.VolunteerDao;
import com.itwill.entity.Center;
import com.itwill.entity.Volunteer;
import com.itwill.repository.VolunteerRepository;


@Transactional
@Service
public class VolunteerServiceImpl implements VolunteerService{
	
	@Autowired
	VolunteerDao volunteerDao;
	@Autowired
	VolunteerRepository volunteerRepository;

	@Override
	public Volunteer findByVolunteerNo(Long no) {
		return volunteerDao.findByVolunteerNo(no);
	} //봉사 목록 찾기

	@Override
	public Volunteer insertVolunteer(Volunteer volunteer) {
		return volunteerDao.insertVolunteer(volunteer);
	}

	@Override
	public Volunteer updateVolunteer(Volunteer volunteer) throws Exception {
		return volunteerDao.updateVolunteer(volunteer);
	}

	@Override
	public void deleteVolunteer(Long no) throws Exception {
		volunteerDao.deleteVolunteer(no);
	}

	@Override
	public List<Volunteer> findAllVolunteers() {
		return volunteerDao.findVolunteerList();
	} //봉사목록 전체

	@Override
	public List<Volunteer> findVolunteertByUserNo(Long no){
		return volunteerRepository.findVolunteertByUserNo(no);
	} // userNo로 봉사 목록

	
	
	
	@Override
	public List<Volunteer> findVolunteerListWithPoints(Volunteer volunteer, Integer userPoint) {
		// 봉사와 포인트 정보 함께 조회
		return volunteerRepository.findVolunteerListWithPoints(userPoint);
	}

	@Override
	public void addPointsToVolunteer(Long volunteerNo, Integer pointsToAdd) {
		// 봉사에 포인트 적립
		volunteerDao.addPointsToVolunteer(volunteerNo, pointsToAdd);		
	}

	@Override
	public Page<Volunteer> volunteerFindAllPage(Pageable pageable) {
		Page<Volunteer> volunteerList = volunteerDao.volunteerFindAllPage(pageable);
		
		return volunteerList;
	}

	
	
	
	
}
