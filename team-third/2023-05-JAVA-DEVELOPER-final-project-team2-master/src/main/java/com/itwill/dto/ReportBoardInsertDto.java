package com.itwill.dto;

import java.util.Date;

import com.itwill.entity.ReportBoard;
import com.itwill.entity.Userinfo;
import com.itwill.service.UserInfoService;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import oracle.net.aso.b;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportBoardInsertDto {

	private Long boardNo;
	private String boardTitle;
	private Date boardRegisterDate;
	private String boardContent;
	private Date boardFindDate;
	private String boardFindName;
	private String boardFindPhone;
	private String boardImage;

	
	private Long userNo;
	
	public static ReportBoard toEntity(ReportBoardInsertDto reportBoardInsertDto) {
		ReportBoard reportBoard =ReportBoard.builder()
											.boardContent(reportBoardInsertDto.getBoardContent())
											.boardFindDate(reportBoardInsertDto.getBoardFindDate())
											.boardFindName(reportBoardInsertDto.getBoardFindName())
											.boardFindPhone(reportBoardInsertDto.getBoardFindPhone())
											.boardRegisterDate(reportBoardInsertDto.getBoardRegisterDate())
											.boardTitle(reportBoardInsertDto.getBoardTitle())
											.boardImage(reportBoardInsertDto.getBoardImage())
											.build();

		return reportBoard;
	}
	public static ReportBoardInsertDto toDto(ReportBoard reportBoard) {
		ReportBoardInsertDto reportBoardInsertDto =ReportBoardInsertDto.builder()
											.boardNo(reportBoard.getBoardNo())
											.boardContent(reportBoard.getBoardContent())
											.boardFindDate(reportBoard.getBoardFindDate())
											.boardFindName(reportBoard.getBoardFindName())
											.boardFindPhone(reportBoard.getBoardFindPhone())
											.boardRegisterDate(reportBoard.getBoardRegisterDate())
											.boardTitle(reportBoard.getBoardTitle())
											.boardImage(reportBoard.getBoardImage())
											.build();

		return reportBoardInsertDto;
	}
	
}
