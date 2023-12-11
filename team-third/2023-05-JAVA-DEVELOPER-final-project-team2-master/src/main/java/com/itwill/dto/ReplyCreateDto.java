package com.itwill.dto;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.itwill.entity.Product;
import com.itwill.entity.ReplyBoard;
import com.itwill.entity.ReportBoard;
import com.itwill.entity.Userinfo;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyCreateDto {
	
	private Long replyBoardNo;
	private Date replyBoardRegisterDate;
	private String replyBoardContent;
	private Integer replyBoardGroupNo;
	private Integer replyBoardStep;
	private Integer replyBoardDepth;
	private Long reportNo;
	private Long userNo;
	private String userName;
	private int status;
	
	
	
	public static ReplyBoard toEntity(ReplyCreateDto replyCreateDto) {
		ReplyBoard replyBoard = ReplyBoard.builder()
											.replyBoardContent(replyCreateDto.getReplyBoardContent())
											.replyBoardRegisterDate(replyCreateDto.getReplyBoardRegisterDate())
											.replyBoardGroupNo(replyCreateDto.getReplyBoardGroupNo())
											.replyBoardStep(replyCreateDto.getReplyBoardStep())
											.replyBoardDepth(replyCreateDto.getReplyBoardDepth())
											.replyBoardNo(replyCreateDto.getReplyBoardNo())
											.build();
		return replyBoard;
	}
	
	public static ReplyCreateDto toDto(ReplyBoard replyBoard) {
		ReplyCreateDto createDto = ReplyCreateDto.builder()
												.replyBoardContent(replyBoard.getReplyBoardContent())
												.replyBoardDepth(replyBoard.getReplyBoardDepth())
												.replyBoardGroupNo(replyBoard.getReplyBoardGroupNo())
												.replyBoardStep(replyBoard.getReplyBoardStep())
												.replyBoardNo(replyBoard.getReplyBoardNo())
												.replyBoardRegisterDate(replyBoard.getReplyBoardRegisterDate())
												.reportNo(replyBoard.getReportBoard().getBoardNo())
												.userNo(replyBoard.getUserinfo().getUserNo())
												.build();
		
		return createDto;
	}
	
}
