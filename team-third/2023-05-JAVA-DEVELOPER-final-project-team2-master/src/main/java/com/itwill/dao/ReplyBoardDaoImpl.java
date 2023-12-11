package com.itwill.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwill.entity.ReplyBoard;
import com.itwill.entity.ReportBoard;
import com.itwill.repository.ReplyBoardRepository;
import com.itwill.repository.ReportBoardRepository;
import com.itwill.repository.UserinfoRepository;

@Repository
public class ReplyBoardDaoImpl implements ReplyBoardDao{
	
	@Autowired
	ReplyBoardRepository replyBoardRepository;
	
	@Autowired
	UserinfoRepository userinfoRepository;

	//댓글 작성
	   @Override
	   public ReplyBoard Create(ReplyBoard replyBoard) {
	      Integer MaxGroupNo = replyBoardRepository.findMaxGroupNo();
	      if(MaxGroupNo==null) {
	         MaxGroupNo = 0;
	      }
	      replyBoard.setReplyBoardGroupNo(MaxGroupNo+1);
	      replyBoard.setReplyBoardStep(1);
	      replyBoard.setReplyBoardDepth(0);
	      return replyBoardRepository.save(replyBoard);
	   }

	//대댓글 작성
	@Override
	public ReplyBoard CreateReply(ReplyBoard replyBoard) {
		// 해당 그룹의 최대 스텝 수
		Integer maxStep = replyBoardRepository.findGreatestStepByGroupNo(replyBoard.getReplyBoardGroupNo());
		ReplyBoard board = ReplyBoard.builder()
				
					.replyBoardGroupNo(replyBoard.getReplyBoardGroupNo())
					.replyBoardDepth(replyBoard.getReplyBoardDepth()+1)
					.replyBoardStep(maxStep+1)
					.replyBoardContent(replyBoard.getReplyBoardContent())
					.reportBoard(replyBoard.getReportBoard())
					.userinfo(null)
					.build();
		return replyBoardRepository.save(board);
	}
	


	/*
	 * @Override public void deleteByReplyBoardNo(Long ReplyBoardNo) {
	 * replyBoardRepository.deleteById(ReplyBoardNo);
	 * 
	 * }
	 */
	
	@Override
	public ReplyBoard update(ReplyBoard replyBoard) {
		return replyBoardRepository.save(replyBoard);
	}
	
	@Override
	public List<ReplyBoard> findByUserNo(Long userNo) {
		return replyBoardRepository.findByUserNo(userNo);
	}
	
	
	
	@Override
	public List<ReplyBoard> findAllByOrderByReplyBoardNoAsc(Long boardNo) {
		return replyBoardRepository.findAllByOrderByReplyBoardNoAsc(boardNo);
	}


	@Override
	public void deleteByReplyBoardStepBoardDepthBoardGroupNo(Integer ReplyBoardStep,Integer ReplyBoardDepth,Integer ReplyBoardGroupNo) {
		replyBoardRepository.deleteByReplyBoardStepBoardDepthBoardGroupNo(ReplyBoardStep, ReplyBoardDepth, ReplyBoardGroupNo);
	}

	@Override
	public ReplyBoard findByReplyBoardNo(Long replyBoardNo) {
		ReplyBoard replyBoard = replyBoardRepository.findById(replyBoardNo).get();
		return replyBoard;
	}
	
	@Override
	public Integer findGreatestStepByGroupNo(Integer ReplyBoardGroupNo) {
		return replyBoardRepository.findGreatestStepByGroupNo(ReplyBoardGroupNo);
	}

	
	// 해당 게시물의 댓글 보여주기
	@Override
	public List<ReplyBoard> findAllByReportBoardNo(Long BoardNo) {
		return replyBoardRepository.findAllByReportBoardNo(BoardNo);
	}

	@Override
	public void DeleteByNo(Long replyBoardNo) {
		replyBoardRepository.deleteById(replyBoardNo);
	}
	

}


