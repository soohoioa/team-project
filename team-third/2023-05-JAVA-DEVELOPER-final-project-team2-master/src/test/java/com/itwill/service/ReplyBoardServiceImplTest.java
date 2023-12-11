package com.itwill.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.TeamprojectAnimalcareApplicationTest;
import com.itwill.entity.ReplyBoard;

class ReplyBoardServiceImplTest extends TeamprojectAnimalcareApplicationTest{
	
	@Autowired
	private ReplyBoardService replyBoardService;
	
	@Transactional
	@Rollback(false)
	@Disabled
	@Test
	void insert() {
		ReplyBoard replyBoard = ReplyBoard.builder()
								.replyBoardContent("내용")
								.replyBoardDepth(null)
								.replyBoardGroupNo(null)
								.replyBoardRegisterDate(null)
								.replyBoardStep(null)
								.userinfo(null)
								.reportBoard(null)
								.build();
		
		replyBoardService.Create(null);
	}
	
	@Transactional
	@Rollback(false)
	@Disabled
	@Test
	void insertReply() {
		ReplyBoard replyBoard = replyBoardService.findByReplyBoardNo(1L);
		replyBoardService.CreateReply(replyBoard);
		
	}
	
	@Disabled
	@Test
	void delete() {
		ReplyBoard replyBoard=  replyBoardService.findByReplyBoardNo(1L);
		replyBoardService.deleteByReplyBoardStepBoardDepthBoardGroupNo(replyBoard.getReplyBoardStep(),replyBoard.getReplyBoardDepth(),replyBoard.getReplyBoardGroupNo());
		
	}
	
	@Disabled
	@Test
	void update() {
		ReplyBoard replyBoard=  replyBoardService.findByReplyBoardNo(2L);
		replyBoard.setReplyBoardContent("희주짱나");
		replyBoardService.update(replyBoard);
		
	}
	@Transactional
	@Rollback(false)
	//@Disabled
	@Test
	void findAllByReportBoardNo() {
		
	List<ReplyBoard> board=replyBoardService.findAllByReportBoardNo(2L);
		System.out.println(board);
		
		
		
	}
	
	
	
}
