package com.itwill.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.itwill.entity.Userinfo;
import com.itwill.entity.Volunteer;
import com.itwill.repository.UserinfoRepository;
import com.itwill.repository.VolunteerRepository;

@Repository
public class VolunteerDaoImpl implements VolunteerDao{
	@Autowired
	VolunteerRepository volunteerRepository;
	@Autowired
	UserinfoRepository userinfoRepository;

	@Override
	public Volunteer insertVolunteer(Volunteer volunteer) {
		volunteerRepository.save(volunteer);
		return volunteer;
	}

	@Override
	public Volunteer findByVolunteerNo(Long no) {
		return volunteerRepository.findById(no).get();
	}

	@Override
	public Volunteer updateVolunteer(Volunteer updateVolunteer) throws Exception {
		Optional<Volunteer> findVolunteerOptional = volunteerRepository.findById(updateVolunteer.getVolunteerNo());
		
		if(findVolunteerOptional.isPresent()) {
			Volunteer volunteer = findVolunteerOptional.get();
			return volunteerRepository.save(volunteer);
			
		} else {
			throw new Exception("존재안함" + updateVolunteer.getVolunteerNo());
		}
		
		
	}
	
	/*
	@Override
	public void deleteVolunteer(Long no) {
		volunteerRepository.deleteById(no);		
	}
	*/
	
	@Override
	public void deleteVolunteer(Long no) throws Exception{
		Optional<Volunteer> selectVolunteer = volunteerRepository.findById(no);
		if(selectVolunteer.isPresent()) {
			volunteerRepository.delete(selectVolunteer.get());
		}
		
	}
	

	@Override
	public List<Volunteer> findVolunteerList() {
		return volunteerRepository.findAll();
	}

	
	@Override
	public List<Volunteer> findVolunteertByUserNo(Long userNo) {
		return volunteerRepository.findVolunteertByUserNo(userNo);
	}

	
	
	
	@Override
	public List<Volunteer> findVolunteerListWithPoints(Long volunteerNo,Integer userPoint) { 
		// 봉사와 포인트 정보 함께 조회		
		return volunteerRepository.findVolunteerListWithPoints(userPoint);
	}

	@Override
	public void addPointsToVolunteer(Long volunteerNo, Integer userPoint) {
	    Volunteer volunteer = volunteerRepository.findById(volunteerNo)
	            .orElseThrow(() -> new IllegalArgumentException("Volunteer not found with id: " + volunteerNo));

	    // NPE가 발생하는 것을 방지
	    Userinfo userinfo = volunteer.getUserinfo();
	    if (userinfo != null) {
	        Integer currentPoints = userinfo.getUserPoint();
	        // 현재 포인트가 null이 아니라면 더하기 연산을 수행하고 저장
	        if (currentPoints != null) {
	            userinfo.setUserPoint(currentPoints + userPoint);
	            userinfoRepository.save(userinfo);
	        } else {
	            // 현재 포인트가 null이라면 초기화 후 더하기 연산을 수행하고 저장
	            userinfo.setUserPoint(userPoint);
	            userinfoRepository.save(userinfo);
	        }
	    } else {
	        throw new IllegalStateException("Userinfo not found for Volunteer with id: " + volunteerNo);
	    }
	}

	@Override
	public Page<Volunteer> volunteerFindAllPage(Pageable pageable) {
		Page<Volunteer> volunteerList = volunteerRepository.findAll(pageable);
		
		return volunteerList;
	}


	
}
