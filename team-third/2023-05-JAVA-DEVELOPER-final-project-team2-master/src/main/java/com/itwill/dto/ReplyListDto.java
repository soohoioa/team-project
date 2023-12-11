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
public class ReplyListDto {

	private Long userNo;
	private Long reportNo;
	
	public static ReplyBoard toEntity(ReplyListDto replyListDto) {
		ReplyBoard replyBoard = ReplyBoard.builder()
											.build();
		return replyBoard;
	}
	
	public static ReplyListDto toDto(ReplyBoard replyBoard) {
		ReplyListDto createDto = ReplyListDto.builder()
												.build();
		
		return createDto;
	}
	
}
