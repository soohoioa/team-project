package com.itwill.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.itwill.entity.ReportBoard;
import com.itwill.repository.ReportBoardRepository;

@Repository
public class ReportBoardDaoImpl implements ReportBoardDao{
	
	@Autowired
	ReportBoardRepository reportBoardRepository;
	
	@Override
	public ReportBoard Create(ReportBoard reportBoard) {
		return reportBoardRepository.save(reportBoard);
	}
	
	@Override
	public void deleteById(Long reportBoard_no) {
		reportBoardRepository.deleteById(reportBoard_no);
	}
	
	@Override
	public ReportBoard update(ReportBoard reportBoard) {
		if(reportBoardRepository.findById(reportBoard.getBoardNo()).isPresent()) {
			reportBoardRepository.save(reportBoard);
		}
		return reportBoard;
	}
	
	//사용자가 쓴 글 목록
	@Override
	public List<ReportBoard> findByUserId(String userId) {
		return reportBoardRepository.findByUserId(userId);
	}
	
	//like 검색 기능 
	@Override
	public List<ReportBoard> findAllByLikeUserId(String userId) {
		return reportBoardRepository.findAllByLikeUserId(userId);
	}

	//게시판 상세보기
	@Override
	public ReportBoard findByBoardNo(Long reportNo) {
		return reportBoardRepository.findById(reportNo).get();
	}
	
	@Override
	public void countReadCount(Long boardNo) {
		reportBoardRepository.countReadCount(boardNo);
	}

	@Override
	public List<ReportBoard> findByUserNo(Long userNo) {
		
		return reportBoardRepository.findByUserNo(userNo);
	}

	@Override
	public List<ReportBoard> findAll() {
		return reportBoardRepository.findAll();
	}

	@Override
	public List<ReportBoard> findByBoardNoOrderByBoardNoDesc() {
		return reportBoardRepository.findByBoardNoOrderByBoardNoDesc();
	}

	@Override
	public Page<ReportBoard> reportBoardFindAllPage(Pageable pageable) {
		Page<ReportBoard> reportList= reportBoardRepository.findAll(pageable);
		return reportList;
	}

	@Override
	public List<ReportBoard> findByBoardImage(String boardImage) {
		
		return reportBoardRepository.findByBoardImage(boardImage);
	}


	
}
