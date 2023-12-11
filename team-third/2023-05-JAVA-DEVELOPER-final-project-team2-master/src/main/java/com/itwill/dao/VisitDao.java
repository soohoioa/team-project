package com.itwill.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itwill.entity.Userinfo;
import com.itwill.entity.Visit;

public interface VisitDao {

	Visit createVisit(Visit visit);

	// Visit 클래스의 컬럼들의 데이터를 저장하기위해 Visit타입의 visit객체를 전달
	Visit findByVisitNo(Long visitNo);

	// Visit 클래스의 Long타입의 visitNo매개변수 전달
	Visit updateVisit(Visit visit);

	// Visit 클래스의 컬럼들의 데이터를 수정하기위해 Visit타입의 visit객체를 전달
	void deleteVisit(Long visitNo);

	List<Visit> selectAllVisits();
	// Visit테이블에 저장된 데이터를 가져와서 리스트로 변환

	List<Visit> getVisitsByUserNo(Long userNo);
	//userid로 visit리스트 검색

	// 페이징
	Page<Visit> visitFindAllPage(Pageable pageable);
}
