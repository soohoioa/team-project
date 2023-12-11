package com.itwill.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.entity.Userinfo;
import com.itwill.entity.Visit;

public interface VisitRepository extends JpaRepository<Visit, Long> {
	 
	List<Visit> findByUserinfoUserNo(Long userNo);
		

}
