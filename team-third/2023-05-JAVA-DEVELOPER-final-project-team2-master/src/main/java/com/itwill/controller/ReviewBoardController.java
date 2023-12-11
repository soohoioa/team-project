package com.itwill.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itwill.dto.ReviewBoardDto;
import com.itwill.entity.Product;
import com.itwill.entity.ReviewBoard;
import com.itwill.entity.Userinfo;
import com.itwill.service.ProductService;
import com.itwill.service.ReviewBoardService;
import com.itwill.service.UserInfoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/reviewBoard")
public class ReviewBoardController {

	@Autowired
	private ReviewBoardService reviewBoardService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserInfoService userInfoService;

	// admin이 리뷰리스트 볼 때
	@GetMapping("/admin/reviewBoardList")
	public String reviewBoardList(Model model) {
		List<ReviewBoard> reviewBoardList = reviewBoardService.findAll();
		List<ReviewBoardDto> reviewBoardDtoList = new ArrayList<>();
		for (ReviewBoard reviewBoard : reviewBoardList) {
			reviewBoardDtoList.add(ReviewBoardDto.toDto(reviewBoard));
		}
		model.addAttribute("reviewBoardDtoList", reviewBoardDtoList);
		return "reviewboard"; // 링크수정하기
	}

	// productNo로 리뷰리스트 볼 때
	@GetMapping("/product/{productNo}")
	public String findByProductNoReviewBoardList(Model model, @PathVariable(name = "productNo") Long productNo) {
		List<ReviewBoard> reviewBoards = reviewBoardService.findByProductNo(productNo);
		List<ReviewBoardDto> reviewBoardDtos = new ArrayList<>();

		for (ReviewBoard reviewBoard : reviewBoards) {
			reviewBoardDtos.add(ReviewBoardDto.toDto(reviewBoard));
		}
		model.addAttribute("reviewBoardDtos", reviewBoardDtos);
		return ""; // 링크 수정하기
	}

	@GetMapping("/productNoReview")
	public String productNoReviewList(Model model, @RequestParam Long productNo) throws Exception {
		Product product = productService.findByProductNo(productNo);
		List<ReviewBoard> reviewList = reviewBoardService.findByProductNo(product.getProductNo());
		model.addAttribute("reviewList", reviewList);
		
		return "product-details";

	}

	@GetMapping("/productNoReviewSort")
	public String getProductDetails(Model model, @RequestParam Long productNo, @RequestParam String sortOrder) {
		Product product = productService.findByProductNo(productNo);
		model.addAttribute("product", product);

		List<ReviewBoard> reviewList;

		if ("별점순".equals(sortOrder)) {
			reviewList = reviewBoardService.findByProductProductNoOrderByBoardStarDesc(productNo);
		} else {
			reviewList = reviewBoardService.findByProductProductNoOrderByBoardDateDesc(productNo);
		}

		model.addAttribute("reviewList", reviewList);

		return "product-details";
	}
	
	
	/*
	// 로그인 후 마이페이지에서 리뷰작성
	@PostMapping("/create-reviewBoard")
	public String createReviewBoard(@RequestParam Double boardStar, @RequestParam String boardContent, HttpSession session, Model model) throws Exception {
	    Long userNo = (Long) session.getAttribute("userNo");

	    ReviewBoard reviewBoard = new ReviewBoard();
	    reviewBoard.setBoardContent(boardContent);
	    reviewBoard.setBoardStar(boardStar);

	    Userinfo userinfo = userInfoService.findUserByNo(userNo);
	    reviewBoard.setUserinfo(userinfo);
	    reviewBoardService.create(reviewBoard);

	    model.addAttribute("userinfo", userinfo);
	    
	    return "order-list";
	}
	*/
	
	
	/*
	// 로그인 후 마이페이지에서 리뷰작성
	@GetMapping("/volunteerByUserNo")
	public String createReviewBoard(@RequestParam Double boardStar, @RequestParam String boardContent,
			HttpSession session, Model model) throws Exception {
		Long userNo = (Long) session.getAttribute("userNo");

		ReviewBoard reviewBoard = new ReviewBoard();
		reviewBoard.setBoardContent(boardContent);
		reviewBoard.setBoardStar(boardStar);

		// Product product = productService.findByProductNo(productNo);
		Userinfo userinfo = userInfoService.findUserByNo(userNo);
		reviewBoard.setUserinfo(userinfo);
		// reviewBoard.setProduct(product);
		List<ReviewBoard> userReviews = reviewBoardService.findByUserNo(userNo);
		model.addAttribute("userinfo", userinfo);
		model.addAttribute("userReviews", userReviews);

		return "order-list";
	}
	*/



}
