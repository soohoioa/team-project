package com.itwill.controller;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.itwill.dto.ReportBoardInsertDto;
import com.itwill.dto.UserLoginActionDto;
import com.itwill.dto.UserWriteActionDto;
import com.itwill.dto.VisitDto;
import com.itwill.entity.Center;
import com.itwill.entity.ReportBoard;
import com.itwill.entity.Userinfo;
import com.itwill.entity.Visit;
import com.itwill.service.ReportBoardService;
import com.itwill.service.UserInfoService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/report")
public class ReportRestController {
	
	@Autowired
	private ReportBoardService reportBoardService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Operation(summary = "유기견신고하기")
	@PostMapping
	public ResponseEntity<ReportBoardInsertDto> registerLostDog(ReportBoardInsertDto reportBoardInsertDto) throws Exception {
		Userinfo userinfo = userInfoService.findUserByNo(reportBoardInsertDto.getUserNo());
		
		ReportBoard reportBoard = ReportBoardInsertDto.toEntity(reportBoardInsertDto);
		
		reportBoard.setUserinfo(userinfo);
		reportBoardService.Create(reportBoard);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		return new ResponseEntity<ReportBoardInsertDto>(reportBoardInsertDto,httpHeaders,HttpStatus.OK);
	}
	
	@Operation(summary = "유기견삭제")
	@DeleteMapping("/delete/{boardNo}")
	public ResponseEntity<ReportBoardInsertDto> reportBoardDelete(@PathVariable(name = "boardNo") Long boardNo){
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		reportBoardService.deleteById(boardNo);
		
		return new ResponseEntity<ReportBoardInsertDto>(httpHeaders, HttpStatus.OK);

	}
	
	@Operation(summary = "신고업데이트")
	@PutMapping("/{boardNo}")
	public ResponseEntity<ReportBoardInsertDto> updateBoard(@PathVariable(name = "boardNo") Long boardNo, @RequestBody ReportBoardInsertDto dto) {
		ReportBoard existingBoard = reportBoardService.findByBoardNo(boardNo);
		
		if(existingBoard != null) {
			if (dto.getBoardContent() != null) {
				existingBoard.setBoardContent(dto.getBoardContent());
			}
			if(dto.getBoardFindDate() !=null) {
				existingBoard.setBoardFindDate(dto.getBoardFindDate());
			}
			if (dto.getBoardFindName() !=null) {
				existingBoard.setBoardFindName(dto.getBoardFindName());
			}
			if (dto.getBoardFindPhone() != null) {
				existingBoard.setBoardFindPhone(dto.getBoardFindPhone());
			}
			if (dto.getBoardRegisterDate() != null) {
				existingBoard.setBoardRegisterDate(dto.getBoardRegisterDate());
			}
			if (dto.getBoardTitle() != null) {
				existingBoard.setBoardTitle(dto.getBoardTitle());
			}
			
			reportBoardService.update(existingBoard);
			return new ResponseEntity<>(ReportBoardInsertDto.toDto(existingBoard),HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
	}
	@Operation(summary = "게시판상세보기")
	@GetMapping("/boards/search")
	public ResponseEntity<ReportBoardInsertDto> findByBoardNo(@RequestParam(name = "no") Long boardNo) {
	    ReportBoard board = reportBoardService.findByBoardNo(boardNo);

	    if (board != null) {
	        ReportBoardInsertDto boardDto = ReportBoardInsertDto.toDto(board);
	        return new ResponseEntity<>(boardDto, HttpStatus.OK);
	    } else {
	        HttpHeaders httpHeaders = new HttpHeaders();
	        httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
	        return new ResponseEntity<>(httpHeaders, HttpStatus.NOT_FOUND);
	    }
	}

	
	@Operation(summary = "유저 게시물 조회")
	@GetMapping("/boards/searchByUser")
	public ResponseEntity<List<ReportBoardInsertDto>> findByUserId(@RequestParam(name = "userNo") Long userNo) {
	    List<ReportBoard> boards = reportBoardService.findByUserNo(userNo);

	    if (!boards.isEmpty()) {
	        List<ReportBoardInsertDto> boardDtoList = new ArrayList<>();
	        for (ReportBoard board : boards) {
	            boardDtoList.add(ReportBoardInsertDto.toDto(board));
	        }
	        return new ResponseEntity<List<ReportBoardInsertDto>>(boardDtoList, HttpStatus.OK);
	    } else {
	        HttpHeaders httpHeaders = new HttpHeaders();
	        httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
	        return new ResponseEntity<List<ReportBoardInsertDto>>(httpHeaders, HttpStatus.NOT_FOUND);
	    }
	}

	
}
