package com.itwill.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.itwill.entity.ReportBoard;

public interface ReportBoardService {

	// 유기견 게시판 글 작성
	public ReportBoard Create(ReportBoard repordBoard);

	// 유기견 게시판 글 삭제
	public void deleteById(Long reportBoardNo);

	// 유기견 게시판 글 업뎃
	public ReportBoard update(ReportBoard reportBoard);

	// 마이페이지에 사용자가 쓴 글 목록 확인
	public List<ReportBoard> findByUserId(String userId);

	// 마이페이지에 사용자가 쓴 글 목록 확인
	public List<ReportBoard> findByUserNo(Long userNo);

	// like검색기능
	public List<ReportBoard> findAllByLikeUserId(String userId);

	// 게시판 상세보기
	public ReportBoard findByBoardNo(Long reportNo);

	// 조회수 1증가
	public void countReadCount(Long boardNo);

	// 게시판 전체 글 뽑기
	public List<ReportBoard> findAll();
	
	public List<ReportBoard> findByBoardNoOrderByBoardNoDesc();
	
	public Page<ReportBoard> reportBoardFindAllPage(Pageable pageable); 
	
	public List<ReportBoard> findByBoardImage(String boardImage);
	
}
