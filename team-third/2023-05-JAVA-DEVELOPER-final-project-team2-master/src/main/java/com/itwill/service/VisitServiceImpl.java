package com.itwill.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.itwill.dao.VisitDao;
import com.itwill.entity.Visit;
import com.itwill.repository.VisitRepository;

@Service
public class VisitServiceImpl implements VisitService {

	@Autowired
	private VisitDao visitDao;
	@Autowired
	VisitRepository visitRepository;

	@Override
	public Visit createVisit(Visit visit) {
		// 견학신청 리스트생성
		return visitDao.createVisit(visit);
	}

	@Override
	public Visit findByVisitNo(Long visitNo) {
		// 견학 리스트 찾기
		return visitDao.findByVisitNo(visitNo);
	}

	@Override
	public Visit updateVisit(Visit visit) {
		// 견학 리스트 수정(ex 접수중~접수완료)
		return visitDao.updateVisit(visit);
	}

	@Override
	public void deleteVisit(Long visitNo) {
		// 견학 리스트 삭제
		visitDao.deleteVisit(visitNo);
	}

	@Override
	public List<Visit> selectAllVisits() {
		// 견학 리스트 출력
		return visitDao.selectAllVisits();
	}

	@Override
	public List<Visit> getVisitsByUserNo(Long userNo) {

		return visitRepository.findByUserinfoUserNo(userNo);
	}

	@Override
	public Page<Visit> visitFindAllPage(Pageable pageable) {
		Page<Visit> visitList = visitDao.visitFindAllPage(pageable);
		
		return visitList;
	}
}