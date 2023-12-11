package com.itwill.controller;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.itwill.dto.PetDto;
import com.itwill.entity.Pet;
import com.itwill.entity.ReplyBoard;
import com.itwill.entity.ReportBoard;
import com.itwill.entity.Userinfo;
import com.itwill.service.ReplyBoardService;
import com.itwill.service.ReportBoardService;
import com.itwill.service.UserInfoService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;

@Controller
public class ReportController {
	@Autowired
	private ReportBoardService reportBoardService;
	
	@Autowired
	private ReplyBoardService replyBoardService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Operation(summary = "신고게시판 리스트")
	@GetMapping("/reportlist")
	public String ReportList(Model model,@PageableDefault(page =0,size = 9,sort = "boardNo",direction = Sort.Direction.DESC) Pageable page,HttpSession session) {
		int pag = page.getPageNumber();
		int size = page.getPageSize();
		
		Pageable pageable= PageRequest.of(pag,size,Sort.by(Sort.Order.desc("boardNo")));
		//Pageable pageable = PageRequest.of(pag, size, Sort.by(Sort.Order.desc("reportBoardNo")));

		
		List<ReportBoard> reportBoards = reportBoardService.findByBoardNoOrderByBoardNoDesc();
		
		//Page<ReportBoard> reportList= reportBoardService.reportBoardFindAllPage(pageable);
		
		//model.addAttribute("reportBoardList", reportBoards);
		

	    Page<ReportBoard> reportList = reportBoardService.reportBoardFindAllPage(pageable);
	    Long userNo = (Long)session.getAttribute("userNo");
	    if(userNo!=null) {
	    	model.addAttribute("userNo",userNo);
	    }else {
	    	model.addAttribute("userNo",0L);
	    }
	    
	    model.addAttribute("reportBoardList", reportList.getContent()); // 페이지의 내용만을 보내기
	    model.addAttribute("reportList", reportList); // 페이징 정보를 보내기
	    
		return "reportList";
	}
	
	@GetMapping(value="/reportBoardView",params="boardNo")
	public String ReportView(Model model,@RequestParam Long boardNo,HttpSession session) throws Exception {
		ReportBoard reportBoard = reportBoardService.findByBoardNo(boardNo);
		List<ReplyBoard> replyBoardList= replyBoardService.findAllByOrderByReplyBoardNoAsc(boardNo);
		
		for (ReplyBoard replyBoard : replyBoardList) {
			Userinfo findUserinfo = replyBoard.getUserinfo();
			Long userNo = findUserinfo.getUserNo();
			Userinfo replyBoardUserinfo = userInfoService.findUserByNo(userNo);
			replyBoard.setUserinfo(replyBoardUserinfo);
		}
	   
		model.addAttribute("reportBoard", reportBoard);
		model.addAttribute("replyBoardList", replyBoardList);
		
		Long loginUserNo = (Long)session.getAttribute("userNo");
		
		if(loginUserNo!=null) {
			model.addAttribute("loginUserNo",loginUserNo);
		}else {
			model.addAttribute("loginUserNo",0L);
		}
		
		return "reportBoardView";
	}
	
	@GetMapping("/reportWriteForm")
    public String showReportForm(HttpSession session) throws Exception{
		Long userNo = (Long)session.getAttribute("userNo");
		String path = null;
		if(userNo!=null) {
			path = "reportBoard_write_form";
		}
        return "reportBoard_write_form"; 
    }

	  @PostMapping("/reportWrite")
	  public String handleImagePost(@RequestParam("imageFile") MultipartFile file , @RequestParam("boardTitle")String boardTitle,
			  @RequestParam("boardFindDate") @DateTimeFormat(pattern = "yyyy-MM-dd")Date boardFindDate, @RequestParam("boardFindName")String boardFindName,
			  @RequestParam("boardFindPhone")String boardFindPhone, @RequestParam("boardContent")String boardContent ,@RequestParam("boardFindPlace")String boardFindPlace ,HttpSession session, Model model,
			  @PageableDefault(page =0,size = 9,sort = "boardNo",direction = Sort.Direction.DESC) Pageable page,@RequestParam("boardType")Integer boardType) throws Exception{
	    String uploadPath = System.getProperty("user.dir") + "/src/main/resources/static/image/reportboard/";
	    String originalFileName = file.getOriginalFilename();
	    UUID uuid = UUID.randomUUID();
	    String savedFileName = uuid.toString() + "_" + originalFileName;

	    File newFile = new File(uploadPath + savedFileName);
	    
	    file.transferTo(newFile);
	    
	    Long userNo = (Long)session.getAttribute("userNo");
	    
	    Userinfo userinfo = Userinfo.builder().userName("비회원").build();
	    if(userNo!=null) {
	    	userinfo = userInfoService.findUserByNo(userNo);
	    }
	    
	    if(boardType==1) {
	    	boardTitle = "[실종]"+boardTitle;
	    }else {
	    	boardTitle = "[제보]"+boardTitle;
	    }
	    
	    ReportBoard writeReportBoard = ReportBoard.builder()
	    										.boardContent(boardContent)
	    										.boardFindDate(boardFindDate)
	    										.boardFindName(boardFindName)
	    										.boardFindPhone(boardFindPhone)
	    										.boardImage(savedFileName)
	    										.boardTitle(boardTitle)
	    										.boardRegisterDate(new Date())
	    										.boardFindPlace(boardFindPlace)
	    										.userinfo(userinfo)
	    										.build();
	    
	    
	    ReportBoard insertReportBoard = reportBoardService.Create(writeReportBoard);
	    
	    int pag = page.getPageNumber();
		int size = page.getPageSize();
		
		Pageable pageable= PageRequest.of(pag,size,Sort.by(Sort.Order.desc("boardNo")));
		//Pageable pageable = PageRequest.of(pag, size, Sort.by(Sort.Order.desc("reportBoardNo")));

		
		List<ReportBoard> reportBoards = reportBoardService.findByBoardNoOrderByBoardNoDesc();
		
		//Page<ReportBoard> reportList= reportBoardService.reportBoardFindAllPage(pageable);
		
		//model.addAttribute("reportBoardList", reportBoards);
		

	    Page<ReportBoard> reportList = reportBoardService.reportBoardFindAllPage(pageable);
	    userNo = (Long)session.getAttribute("userNo");
	    if(userNo!=null) {
	    	model.addAttribute("userNo",userNo);
	    }else {
	    	model.addAttribute("userNo",0L);
	    }
	    
	    model.addAttribute("reportBoardList", reportList.getContent()); // 페이지의 내용만을 보내기
	    model.addAttribute("reportList", reportList); // 페이징 정보를 보내기
	    
	    return "redirect:reportlist";
	  }
	
	  // 신고게시판 수정
	  @PostMapping("/reportUpdate")
	  public String handleImagePostUpdate(@RequestParam("imageFile") MultipartFile file , @RequestParam("boardTitle")String boardTitle,
			  @RequestParam("boardFindDate") @DateTimeFormat(pattern = "yyyy-MM-dd")Date boardFindDate, @RequestParam("boardFindName")String boardFindName,
			  @RequestParam("boardFindPhone")String boardFindPhone, @RequestParam("boardContent")String boardContent ,@RequestParam("boardFindPlace")String boardFindPlace ,HttpSession session, Model model,
			  @PageableDefault(page =0,size = 9,sort = "boardNo",direction = Sort.Direction.DESC) Pageable page,@RequestParam("boardNo")Long boardNo) throws Exception{
	   
		  System.out.println("###########"+file.getOriginalFilename());
		List<ReportBoard> findreportBoard = reportBoardService.findByBoardImage(file.getOriginalFilename());
		System.out.println("###############"+findreportBoard);
		String savedFileName = "";
		if(findreportBoard.size()>0) {
			savedFileName = file.getOriginalFilename();
		}else {
			String uploadPath = System.getProperty("user.dir") + "/src/main/resources/static/image/reportboard/";
		    String originalFileName = file.getOriginalFilename();
		    UUID uuid = UUID.randomUUID();
		    savedFileName = uuid.toString() + "_" + originalFileName;

		    File newFile = new File(uploadPath + savedFileName);
		    
		    file.transferTo(newFile);
		}
	    
	    Long userNo = (Long)session.getAttribute("userNo");
	    
	    Userinfo userinfo = Userinfo.builder().userName("비회원").build();
	    if(userNo!=null) {
	    	userinfo = userInfoService.findUserByNo(userNo);
	    }
	    System.out.println("############"+savedFileName);
	    ReportBoard writeReportBoard = ReportBoard.builder()
	    										.boardNo(boardNo)
	    										.boardContent(boardContent)
	    										.boardFindDate(boardFindDate)
	    										.boardFindName(boardFindName)
	    										.boardFindPhone(boardFindPhone)
	    										.boardImage(savedFileName)
	    										.boardTitle(boardTitle)
	    										.boardRegisterDate(new Date())
	    										.boardFindPlace(boardFindPlace)
	    										.userinfo(userinfo)
	    										.build();
	    
	    
	    ReportBoard insertReportBoard = reportBoardService.update(writeReportBoard);
	    System.out.println("##############"+insertReportBoard);
	    int pag = page.getPageNumber();
		int size = page.getPageSize();
		
		Pageable pageable= PageRequest.of(pag,size,Sort.by(Sort.Order.desc("boardNo")));
	    Page<ReportBoard> reportList = reportBoardService.reportBoardFindAllPage(pageable);
	    userNo = (Long)session.getAttribute("userNo");
	    if(userNo!=null) {
	    	model.addAttribute("userNo",userNo);
	    }else {
	    	model.addAttribute("userNo",0L);
	    }
	    
	    model.addAttribute("reportBoardList", reportList.getContent()); // 페이지의 내용만을 보내기
	    model.addAttribute("reportList", reportList); // 페이징 정보를 보내기
	    
	    return "redirect:reportlist";
	  }
	  
	/*
	@Operation(summary = "신고게시판 상세보기")
	@GetMapping("/{boardNo}")
	public String ReportView(Model model,@RequestParam(name = "boardNo") Long boardNo) {
		ReportBoard reportBoard = reportBoardService.findByBoardNo(boardNo);
		List<ReplyBoard> replyBoard=replyBoardService.findAllByReportBoardNo(boardNo);
		model.addAttribute("reportBoard", reportBoard);
		
		model.addAttribute("replyBoardList", replyBoard);
		
		return "reportBoardView";
	}
	
	 */
	
	@Operation(summary = "신고게시판 수정폼으로 이동")
	@GetMapping("/reportUpdateForm")
	public String ReportUpdate(Model model,@RequestParam(name = "boardNo")Long boardNo) {
		ReportBoard reportBoard = reportBoardService.findByBoardNo(boardNo);
		
		model.addAttribute("reportBoard", reportBoard);
		
		return "reportBoard_update_form";
	}
	
	
	
	
}
