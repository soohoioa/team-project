package com.itwill.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.itwill.entity.ReviewBoard;

import jakarta.transaction.Transactional;

@SpringBootTest
class ReviewBoardServiceImplTest {

	@Autowired
	ReviewBoardService reviewBoardService;

	@Autowired
	UserInfoService userInfoService;

	@Autowired
	ProductService productService;

	@Test // 생성
	@Disabled
	@Transactional
	@Rollback(false)
	void testCreate() throws Exception {
		LocalDateTime currentDateTime=LocalDateTime.now();
		ReviewBoard reviewBoard = ReviewBoard.builder()
			
				.boardTitle("별로네요")
				.boardContent("별로에요")
				.boardDate(currentDateTime)
				.boardStar((double) 1)
				.userinfo(userInfoService.findUserByNo(14L))
				.product(productService.findByProductNo(15L))
				.build();

		reviewBoardService.create(reviewBoard);

	}
	
	@Test
	@Transactional
	@Rollback(value = false)
	@Disabled
	void updatereviewBoard() {
		ReviewBoard updatereviewBoard = reviewBoardService.findByBoardNo(22L);
		updatereviewBoard.setBoardContent("수정이될려나");
		System.out.println(updatereviewBoard);
	}
	
	@Test
	@Transactional
	@Disabled
	void selectBoard() {
		ReviewBoard selectBoard = reviewBoardService.findByBoardNo(22L);
		System.out.println(selectBoard);
	}
	
	@Test
	@Transactional
	@Rollback(value = false)
	@Disabled
	void deleteBoard() {
		reviewBoardService.deleteById(22L);
	}


	@Test // 상품번호로 리뷰찾기
	@Transactional
	@Rollback(value = false)
	//@Disabled
	void getReviewBoardByProduct_ProductNo() {
		List<ReviewBoard> selectReviewBoard = reviewBoardService.findByProductNo(15L);
		System.out.println(selectReviewBoard);
	}
	
	@Test//유저 아이디로 찾기
	@Transactional
	@Disabled 
	void findByReviewBoardUserId() {
		List<ReviewBoard> findByUserIdReviewBoard = reviewBoardService.findByUserNo(4L);
		System.out.println(findByUserIdReviewBoard);
	}
	
	@Test//선택한 별점으로 찾기
	@Transactional
	@Disabled
	void findAllByBoardStar() {
		List<ReviewBoard> findAllByBoardStar = reviewBoardService.findByStarAll((double) 5L);
		System.out.println(findAllByBoardStar);
	}
	@Test//별점 높은순
	@Transactional
	@Disabled
	void findAllByOrderByBoardStarDesc() {
		List<ReviewBoard> findAllByOrderByBoardStarDesc = reviewBoardService.findAllByOrderByBoardStarDesc();
		System.out.println(findAllByOrderByBoardStarDesc);
	}
	
	@Test//별점 낮은순
	@Transactional
	@Disabled
	void findAllByOrderByBoardStarAsc() {
		List<ReviewBoard> findAllByOrderByBoardStarAsc = reviewBoardService.findAllByOrderByBoardStarAsc();
		System.out.println(findAllByOrderByBoardStarAsc);
	}
	
	@Test// 최신순 정렬(board Date정렬)
	@Transactional
	@Disabled
	void findAllByOrderByBoardNoDesc() {
		List<ReviewBoard> findAllByOrderByBoardDateDesc = reviewBoardService.findAllByOrderByBoardDateDesc();
		System.out.println(findAllByOrderByBoardDateDesc);
	}
	
	
	@Test// 오래된순 정렬(board Date정렬)
	@Transactional
	@Disabled
	void findAllByOrderByBoardNoAsc() {
		List<ReviewBoard> findAllByOrderByBoardDateAsc = reviewBoardService.findAllByOrderByBoardDateAsc();
		System.out.println(findAllByOrderByBoardDateAsc);
	}
	
	
	
	  @Test//별점 높은순,최신순	  
	  @Transactional  
	  @Rollback(false) 
	  @Disabled 
	  public void findByOrderByBoardStarDescBoardDateDesc() { 
		  List<ReviewBoard> findByOrderByBoardStarDescBoardDateDesc = reviewBoardService.findByOrderByBoardStarDescBoardDateDesc();

		  System.out.println(findByOrderByBoardStarDescBoardDateDesc); 
	  }
	 
	  @Test//별점 낮은순,최신순	  
	  @Transactional  
	  @Rollback(false) 
	  @Disabled 
	  public void findByOrderByBoardStarAscBoardDateDesc() { 
		  List<ReviewBoard> findByOrderByBoardStarAscBoardDateDesc = reviewBoardService.findByOrderByBoardStarAscBoardDateDesc();
		  
		  System.out.println(findByOrderByBoardStarAscBoardDateDesc); 
	  }
	 
	   @Test
	      @Transactional
	      @Rollback(false) 
	     @Disabled 
	      void testCalculateAverageStarRating() {
	          // 상품 번호를 사용하여 별점 평균을 계산
	          Long productNo = 3L;
	          double averageRating = reviewBoardService.calculateAverageStarRating(productNo);

	          // 별점 평균을 검증 
	          double expectedAverageRating = 3.6; // 예상한 평균 별점
	          assertEquals(expectedAverageRating, averageRating, 0.1); // 두 값이 오차 내에서 동일한지 확인
	          
	          System.out.println("별점 평균: " + averageRating);
	      }
	  
	  
}
