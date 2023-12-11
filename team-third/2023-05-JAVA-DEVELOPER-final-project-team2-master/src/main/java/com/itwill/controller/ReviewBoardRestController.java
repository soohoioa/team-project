package com.itwill.controller;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

import com.itwill.dto.ReviewBoardDto;
import com.itwill.dto.VolunteerDto;
import com.itwill.entity.OrderItem;
import com.itwill.entity.Product;
import com.itwill.entity.ReviewBoard;
import com.itwill.entity.Userinfo;
import com.itwill.repository.OrderItemRepository;
import com.itwill.service.OrderItemService;
import com.itwill.service.ProductService;
import com.itwill.service.ReviewBoardService;
import com.itwill.service.UserInfoService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/reviewBoard")
public class ReviewBoardRestController {
	
	@Autowired
	private ReviewBoardService reviewBoardService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Operation(summary = "리뷰작성")
	@PostMapping("/createReviewBoard")
	public ResponseEntity<ReviewBoardDto> createReviewBoard(@RequestBody ReviewBoardDto dto, HttpSession session) throws Exception {
		Long userNo=(Long)session.getAttribute("userNo");
		
		dto.setUserNo(userNo);
		
		ReviewBoard reviewBoardEntity = ReviewBoardDto.toEntity(dto);
		System.out.println(">>>>>>>>>>>>>>>아이템번호:"+dto.getOiNo());
		OrderItem orderItem = orderItemRepository.findById(dto.getOiNo()).get();
		
		System.out.println(orderItem);
		
		reviewBoardEntity.setOrderItem(orderItem);
		
		ReviewBoard preReviewBoard = reviewBoardService.findByOrderItemNo(orderItem.getOiNo());
		
		//;
		if(preReviewBoard==null) {
			// 리뷰작성
			reviewBoardService.create(reviewBoardEntity);
		}else {
			// 업뎃
			//preReviewBoard.setBoardContent(reviewBoardEntity.getBoardContent());
			//preReviewBoard.setBoardStar(reviewBoardEntity.getBoardStar());
			Long boardNo = preReviewBoard.getBoardNo();
			reviewBoardEntity.setBoardNo(boardNo);
			System.out.println(reviewBoardEntity);
			reviewBoardService.update(reviewBoardEntity);
	        // 업데이트된 리뷰 정보를 반환
	        //ReviewBoardDto updatedReviewDto = ReviewBoardDto.toDto(preReviewBoard);
	        //return new ResponseEntity<>(updatedReviewDto, HttpStatus.OK); // HttpStatus.OK를 사용하여 성공 상태 반환
			
		}
		Long productNo = orderItem.getProduct().getProductNo();
		double averageRating = reviewBoardService.calculateAverageStarRating(productNo);
		Product product = productService.findByProductNo(productNo);

		// 평균 점수를 반올림하여 정수로 변환
		int roundedAverageRating = (int) Math.round(averageRating);
		Double doubleRating = (double) roundedAverageRating;
		product.setProductStarAvg(doubleRating);
		productService.updateProduct(product);
		
	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

	    return new ResponseEntity<ReviewBoardDto>(dto, httpHeaders, HttpStatus.CREATED);
	}
	
	
	@Operation(summary = "boardNo로 리뷰 보기")
	@GetMapping("/board/{boardNo}")
	public ResponseEntity<ReviewBoardDto> findByBoardNo(@PathVariable(value = "boardNo") Long boardNo, HttpSession httpSession) throws Exception {    
	    ReviewBoard reviewBoard = reviewBoardService.findByBoardNo(boardNo);

	    if (reviewBoard == null) {
	        // ReviewBoard가 없을 때 NOT_FOUND 상태 반환
	        return ResponseEntity.notFound().build();
	    } else {
	        ReviewBoardDto reviewBoardDto = ReviewBoardDto.toDto(reviewBoard);
	        HttpHeaders httpHeaders = new HttpHeaders();
	        httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));    
	        return new ResponseEntity<>(reviewBoardDto, httpHeaders, HttpStatus.OK);
	    }
	}
	
	@PostMapping("/reviewBoardView")
	public ResponseEntity<ReviewBoardDto> findByUserNoByProductNo(@RequestBody ReviewBoardDto reviewBoardDto,HttpSession session){
		Long userNo = (Long)session.getAttribute("userNo");
		
		ReviewBoard reviewBoard = reviewBoardService.findByOrderItemNo(reviewBoardDto.getOiNo());
		System.out.println(">>>>>>>>>>>>"+reviewBoard);
		ReviewBoardDto dto = null;
		
		if(reviewBoard==null) {
			dto = ReviewBoardDto.builder().build();
			dto.setOiNo(reviewBoardDto.getOiNo());
			dto.setUserNo(userNo);	
		}else {
			dto = ReviewBoardDto.toDto(reviewBoard);
			dto.setOiNo(reviewBoardDto.getOiNo());
			dto.setUserNo(userNo);	
		}
		
		System.out.println(">>>>>>>>>>>>"+dto);
		
		HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        
		return new ResponseEntity<ReviewBoardDto>(dto, httpHeaders, HttpStatus.OK);
	}
	
	@Operation(summary = "no로 review 삭제")
	@DeleteMapping("/{no}")
	public ResponseEntity<Map> deleteReviewBoard(@PathVariable(value = "no") Long boardNo) throws Exception {
		reviewBoardService.deleteById(boardNo);
		return ResponseEntity.status(HttpStatus.OK).body(new HashMap<>());
	}
	
	@Operation(summary = "reviewList 전체 조회")    // admin 인 것 같음..?
	@GetMapping("/all")
	public ResponseEntity<List<ReviewBoardDto>> findAll() {
		// 전체 조회
		
		List<ReviewBoard> reviewBoardList = reviewBoardService.findAll();
		List<ReviewBoardDto> reviewBoardDtoList = new ArrayList<>();
		for (ReviewBoard reviewBoard : reviewBoardList) {
			ReviewBoardDto reviewBoardDto = ReviewBoardDto.toDto(reviewBoard);
			reviewBoardDtoList.add(reviewBoardDto);
		}
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		return new ResponseEntity<>(reviewBoardDtoList, httpHeaders, HttpStatus.OK);
	}
	/*
	@Operation(summary = "productNo로 reviewList 조회")     // controller 예상..
	@GetMapping("/product/{productNo}")
	public ResponseEntity<List<ReviewBoardDto>> findByProductNo(@PathVariable(value = "productNo") Long productNo) {
		// productNo로 reviewboard 리스트 검색
		List<ReviewBoard> findReview=reviewBoardService.findByProductNo(productNo);
		if(findReview.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
		}
		List<ReviewBoardDto> reviewDtoList = new ArrayList<>();
		for (ReviewBoard reviewBoard : findReview) {
			ReviewBoardDto boardDto=ReviewBoardDto.toDto(reviewBoard);
			reviewDtoList.add(boardDto);
		}
		return ResponseEntity.status(HttpStatus.OK).body(reviewDtoList);
	}
	*/
	@Operation(summary = "별점으로 review 찾기")     //restController 예상..
	@GetMapping("/{boardStar}")
	public ResponseEntity<List<ReviewBoardDto>> findByStarAll(@PathVariable(value = "boardStar") Double star) {
		// 선택한 별점으로 찾기
		List<ReviewBoard> reviewBoardList = reviewBoardService.findByStarAll(star);
		if(reviewBoardList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
		}
		List<ReviewBoardDto> reviewBoardDtoList =  new ArrayList<>();		
		
		for (ReviewBoard reviewBoard : reviewBoardList) {
			ReviewBoardDto reviewBoardDto = ReviewBoardDto.toDto(reviewBoard);
			reviewBoardDtoList.add(reviewBoardDto);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(reviewBoardDtoList);
	}
	
	@Operation(summary = "userNo로 리뷰리스트 조회")
	@GetMapping("/user/{userNo}")
	public ResponseEntity<List<ReviewBoardDto>> findByUserNo(@PathVariable(value = "userNo") Long userNo) {
		// 선택된 userNo 리뷰 리스트만 나오기
		List<ReviewBoard> reviewBoards=reviewBoardService.findByUserNo(userNo);
		if(reviewBoards.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
		}
		List<ReviewBoardDto> reviewBoardDtoList = new ArrayList<>();
		
		for (ReviewBoard reviewBoard : reviewBoards) {
			ReviewBoardDto reviewBoardDto = ReviewBoardDto.toDto(reviewBoard);
			reviewBoardDtoList.add(reviewBoardDto);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(reviewBoardDtoList);
	}	
	
	@Operation(summary = "boardNo로 reviewBoard 수정")
	@PutMapping("/{boardNo}")
	public ResponseEntity<ReviewBoardDto> updateReviewBoard(@PathVariable(value = "boardNo") Long boardNo, @RequestBody ReviewBoardDto dto) throws Exception {		
		ReviewBoard existingReviewBoard = reviewBoardService.findByBoardNo(boardNo);		
		
		if(existingReviewBoard!=null) {
			if(dto.getBoardContent()!=null) {
				existingReviewBoard.setBoardContent(dto.getBoardContent());
			}
			if(dto.getBoardStar()!=null) {
				existingReviewBoard.setBoardStar(dto.getBoardStar());
			}
			if(dto.getBoardTitle()!=null) {
				existingReviewBoard.setBoardTitle(dto.getBoardTitle());
			}
			
		// productNo는 수정할수없다.?
			
			reviewBoardService.update(existingReviewBoard);
			ReviewBoardDto reviewBoardDto = ReviewBoardDto.toDto(existingReviewBoard);
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
			
			return new ResponseEntity<>(reviewBoardDto, httpHeaders, HttpStatus.OK);
		
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
		
	
	@Operation(summary = "높은 평점순 정렬")
	@PostMapping("/productDetail")
	public ResponseEntity<List<ReviewBoardDto>> findByProductProductNoOrderByBoardStarDesc(@RequestBody ReviewBoardDto dto) throws Exception {
	    List<ReviewBoard> reviewBoards = reviewBoardService.findByProductProductNoOrderByBoardStarDesc(dto.getProductNo());
	    List<ReviewBoardDto> reviewList = new ArrayList<>();
	    
	    for (ReviewBoard reviewBoard : reviewBoards) {
	        ReviewBoardDto reviewdto = new ReviewBoardDto();
	        reviewdto.setBoardNo(reviewBoard.getBoardNo());
	        reviewdto.setBoardTitle(reviewBoard.getBoardTitle());
	        reviewdto.setBoardContent(reviewBoard.getBoardContent());
	        reviewdto.setBoardDate(reviewBoard.getBoardDate());
	        reviewdto.setBoardStar(reviewBoard.getBoardStar());
	        reviewdto.setUserNo(reviewBoard.getUserinfo().getUserNo());
	        reviewdto.setProductNo(reviewBoard.getProduct().getProductNo());
	        Userinfo findUserinfo = userInfoService.findUserByNo(reviewBoard.getUserinfo().getUserNo());
	        reviewdto.setUserName(findUserinfo.getUserName());
	        reviewList.add(reviewdto);
	    }
	    
	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
	    
	    if (!reviewList.isEmpty()) {
	        return new ResponseEntity<List<ReviewBoardDto>>(reviewList, httpHeaders, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(httpHeaders, HttpStatus.NOT_FOUND);
	    }
	}	
	
	@Operation(summary = "낮은 평점순 정렬")
	@GetMapping("reviewBoards/BoardStarAsc")
	public ResponseEntity<List<ReviewBoardDto>> findAllByOrderByBoardStarAsc() {
	    List<ReviewBoard> reviewBoards = reviewBoardService.findAllByOrderByBoardStarAsc();
	    List<ReviewBoardDto> reviewBoardDtos = new ArrayList<>();

	    for (ReviewBoard reviewBoard : reviewBoards) {
	        ReviewBoardDto dto = new ReviewBoardDto();
	        dto.setBoardNo(reviewBoard.getBoardNo());
	        dto.setBoardTitle(reviewBoard.getBoardTitle());
	        dto.setBoardContent(reviewBoard.getBoardContent());
	        dto.setBoardDate(reviewBoard.getBoardDate());
	        dto.setBoardStar(reviewBoard.getBoardStar());

	        reviewBoardDtos.add(dto);
	    }
	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

	    if (!reviewBoardDtos.isEmpty()) {
	        return new ResponseEntity<>(reviewBoardDtos, httpHeaders, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(httpHeaders, HttpStatus.NOT_FOUND);
	    }
	}
	
	@Operation(summary = "최신 날짜순으로 정렬")
	@PostMapping("productDetailDate")
	public ResponseEntity<List<ReviewBoardDto>> findByProductProductNoOrderByBoardDateDesc(@RequestBody ReviewBoardDto dto) throws Exception {
		 List<ReviewBoard> reviewBoards = reviewBoardService.findByProductProductNoOrderByBoardDateDesc(dto.getProductNo());
		    List<ReviewBoardDto> reviewList = new ArrayList<>();
		    
		    for (ReviewBoard reviewBoard : reviewBoards) {
		        ReviewBoardDto reviewdto = new ReviewBoardDto();
		        reviewdto.setBoardNo(reviewBoard.getBoardNo());
		        reviewdto.setBoardTitle(reviewBoard.getBoardTitle());
		        reviewdto.setBoardContent(reviewBoard.getBoardContent());
		        reviewdto.setBoardDate(reviewBoard.getBoardDate());
		        reviewdto.setBoardStar(reviewBoard.getBoardStar());
		        reviewdto.setUserNo(reviewBoard.getUserinfo().getUserNo());
		        reviewdto.setProductNo(reviewBoard.getProduct().getProductNo());
		        Userinfo findUserinfo = userInfoService.findUserByNo(reviewBoard.getUserinfo().getUserNo());
		        reviewdto.setUserName(findUserinfo.getUserName());
		        reviewList.add(reviewdto);
		    }

	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

	    if (!reviewList.isEmpty()) {
	        return new ResponseEntity<List<ReviewBoardDto>>(reviewList, httpHeaders, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(httpHeaders, HttpStatus.NOT_FOUND);
	    }
	}	
	
	@Operation(summary = "오래된 날짜순으로 정렬")
	@GetMapping("reviewBoards/BoardDateAsc")
	public ResponseEntity<List<ReviewBoardDto>> findAllByOrderByBoardDateAsc() {
	    List<ReviewBoard> reviewBoards = reviewBoardService.findAllByOrderByBoardDateAsc();
	    List<ReviewBoardDto> reviewBoardDtos = new ArrayList<>();

	    for (ReviewBoard reviewBoard : reviewBoards) {
	        ReviewBoardDto dto = new ReviewBoardDto();
	        dto.setBoardNo(reviewBoard.getBoardNo());
	        dto.setBoardTitle(reviewBoard.getBoardTitle());
	        dto.setBoardContent(reviewBoard.getBoardContent());
	        dto.setBoardDate(reviewBoard.getBoardDate());
	        dto.setBoardStar(reviewBoard.getBoardStar());

	        reviewBoardDtos.add(dto);
	    }

	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

	    if (!reviewBoardDtos.isEmpty()) {
	        return new ResponseEntity<>(reviewBoardDtos, httpHeaders, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(httpHeaders, HttpStatus.NOT_FOUND);
	    }
	}

	@Operation(summary = "별점 높은순, 최신순으로 정렬")
	@GetMapping("reviewBoards/BoardStarDescBoardDateDesc")
	public ResponseEntity<List<ReviewBoardDto>> findByOrderByBoardStarDescBoardDateDesc() {
	    List<ReviewBoard> reviewBoards = reviewBoardService.findByOrderByBoardStarDescBoardDateDesc();
	    List<ReviewBoardDto> reviewBoardDtos = new ArrayList<>();

	    for (ReviewBoard reviewBoard : reviewBoards) {
	        ReviewBoardDto dto = new ReviewBoardDto();
	        dto.setBoardNo(reviewBoard.getBoardNo());
	        dto.setBoardTitle(reviewBoard.getBoardTitle());
	        dto.setBoardContent(reviewBoard.getBoardContent());
	        dto.setBoardDate(reviewBoard.getBoardDate());
	        dto.setBoardStar(reviewBoard.getBoardStar());

	        reviewBoardDtos.add(dto);
	    }

	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

	    if (!reviewBoardDtos.isEmpty()) {
	        return new ResponseEntity<>(reviewBoardDtos, httpHeaders, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(httpHeaders, HttpStatus.NOT_FOUND);
	    }
	}
	
	@Operation(summary = "별점 낮은순, 최신순으로 정렬")
	@GetMapping("reviewBoards/BoardStarAscBoardDateDesc")
	public ResponseEntity<List<ReviewBoardDto>> findByOrderByBoardStarAscBoardDateDesc() {
	    List<ReviewBoard> reviewBoards = reviewBoardService.findByOrderByBoardStarAscBoardDateDesc();
	    List<ReviewBoardDto> reviewBoardDtos = new ArrayList<>();

	    for (ReviewBoard reviewBoard : reviewBoards) {
	        ReviewBoardDto dto = new ReviewBoardDto();
	        dto.setBoardNo(reviewBoard.getBoardNo());
	        dto.setBoardTitle(reviewBoard.getBoardTitle());
	        dto.setBoardContent(reviewBoard.getBoardContent());
	        dto.setBoardDate(reviewBoard.getBoardDate());
	        dto.setBoardStar(reviewBoard.getBoardStar());

	        reviewBoardDtos.add(dto);
	    }

	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

	    if (!reviewBoardDtos.isEmpty()) {
	        return new ResponseEntity<>(reviewBoardDtos, httpHeaders, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(httpHeaders, HttpStatus.NOT_FOUND);
	    }
	}
	
	@Operation(summary = "해당상품 평균별점")
	@GetMapping("/averageRating/{productNo}")
	public ResponseEntity<ReviewBoardDto> calculateAverageStarRating(@PathVariable(value = "productNo") Long productNo) {
	    double averageRating = reviewBoardService.calculateAverageStarRating(productNo);

	    // 새로운 ReviewBoardDto 생성
	    ReviewBoardDto reviewBoardDto = new ReviewBoardDto();
	    reviewBoardDto.setAverageRating(averageRating);

	    return ResponseEntity.ok(reviewBoardDto);
	}

	
}