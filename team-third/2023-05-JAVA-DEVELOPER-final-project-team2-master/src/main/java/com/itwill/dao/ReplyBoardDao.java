package com.itwill.dao;

import java.util.List;
import java.util.Optional;

import com.itwill.entity.Product;
import com.itwill.entity.ReplyBoard;
import com.itwill.entity.ReportBoard;

public interface ReplyBoardDao {
	
	// 댓글 작성
	public ReplyBoard Create(ReplyBoard replyBoard);
	
	// 댓글 하나 삭제
	public void DeleteByNo(Long replyBoardNo);
	
	// 댓글 하나 선택
	public ReplyBoard findByReplyBoardNo(Long replyBoardNo);
	
	// 댓글 삭제
	public void deleteByReplyBoardStepBoardDepthBoardGroupNo(Integer ReplyBoardStep,Integer ReplyBoardDepth,Integer ReplyBoardGroupNo);
	
	// 대댓글 작성
	public ReplyBoard CreateReply(ReplyBoard replyBoard);
	
	// 댓글 수정 근데 굳이 필요가 없는거같음 메소드.
	public ReplyBoard update(ReplyBoard replyBoard);
	
	//작성자가 쓴 글 목록 
	public List<ReplyBoard> findByUserNo(Long userNo);
	
	// 오래된 순서
	public List<ReplyBoard> findAllByOrderByReplyBoardNoAsc(Long boardNo);
	
	// 해당 그룹 최대 스텝 수 찾기
	public Integer findGreatestStepByGroupNo(Integer ReplyBoardGroupNo);
	
	//해당 게시물의 댓글 보여주기 
	public List<ReplyBoard> findAllByReportBoardNo(Long BoardNo);
	
	
}
