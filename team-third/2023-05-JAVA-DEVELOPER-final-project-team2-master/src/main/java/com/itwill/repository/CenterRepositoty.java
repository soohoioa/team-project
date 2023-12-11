package com.itwill.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.itwill.entity.Center;


public interface CenterRepositoty extends JpaRepository<Center, Long>{

	@Query(value = "select * from center where center_name like '%'||?1||'%'", nativeQuery = true)
	List<Center> findByContains(String centerName);
}
