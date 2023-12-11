package com.itwill.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.itwill.entity.Volunteer;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long>{
	
	/*
	@Query(value = "SELECT * FROM volunteer WHERE user_no = ?1", nativeQuery = true)
	List<Volunteer> findVolunteertByUserNo(@Param("userNo") Long userNo);
	*/

	@Query(value = "SELECT * FROM volunteer WHERE user_no = ?1", nativeQuery = true)
	List<Volunteer> findVolunteertByUserNo(@Param("user_no") Long no);
	
	@Query(value = "SELECT v.*, u.user_point FROM volunteer v JOIN userinfo u ON v.user_no = u.user_no;", nativeQuery = true)
	List<Volunteer> findVolunteerListWithPoints(Integer userPoint);
	// 봉사와 포인트 정보 함께 조회
}
