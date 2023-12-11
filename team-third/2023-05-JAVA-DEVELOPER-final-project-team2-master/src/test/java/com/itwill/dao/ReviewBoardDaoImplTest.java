package com.itwill.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.time.LocalDateTime;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.itwill.entity.ReviewBoard;

import jakarta.transaction.Transactional;

@SpringBootTest
class ReviewBoardDaoImplTest {
	@Autowired
	ReviewBoardDao reviewBoardDao;
	@Autowired
	UserInfoDao userInfoDao;
	@Autowired
	ProductDao productDao;

	@Test // 생성
	@Disabled
	@Transactional
	@Rollback(false)
	void testCreate() {

		LocalDateTime currentDateTime = LocalDateTime.now();
		ReviewBoard reviewBoard = ReviewBoard.builder()
				.boardTitle("타이틀12")
				.boardContent("내용12")
				.boardDate(currentDateTime)
				.boardStar((double) 3)
				.userinfo(userInfoDao.findByNo(1L))
				.product(productDao.findByProductNo(5L)).build();

		reviewBoardDao.create(reviewBoard);
	}

	@Test
	@Transactional
	@Rollback(value = false)
	@Disabled
	void updatereviewBoard() {
		ReviewBoard updatereviewBoard = reviewBoardDao.findByBoardNo(2L);
		updatereviewBoard.setBoardContent("수정이될려나");
		System.out.println(updatereviewBoard);
	}

	@Test
	@Transactional
	@Disabled
	void selectBoard() {
		ReviewBoard selectBoard = reviewBoardDao.findByBoardNo(22L);
		System.out.println(selectBoard);
	}

	@Test
	@Transactional
	@Rollback(value = false)
	@Disabled
	void deleteBoard() {
		reviewBoardDao.deleteById(1L);
	}

	@Test // 상품번호로 리뷰찾기
	@Transactional
	@Rollback(value = false)
	@Disabled
	void getReviewBoardByProduct_ProductNo() {
		List<ReviewBoard> selectReviewBoard = reviewBoardDao.findByProductNo(1L);
		System.out.println(selectReviewBoard);
	}

	@Test // 유저 아이디로 찾기
	@Transactional
	@Disabled
	void findByReviewBoardUserId() {
		List<ReviewBoard> findByUserIdReviewBoard = reviewBoardDao.findByUserNo(5L);
		System.out.println(findByUserIdReviewBoard);
	}

	@Test // 선택한 별점으로 찾기
	@Transactional
	@Disabled
	void findAllByBoardStar() {
		List<ReviewBoard> findAllByBoardStar = reviewBoardDao.findByStarAll((double) 5L);
		System.out.println(findAllByBoardStar);
	}

	@Test // 별점 높은순
	@Transactional
	@Disabled
	void findAllByOrderByBoardStarDesc() {
		List<ReviewBoard> findAllByOrderByBoardStarDesc = reviewBoardDao.findAllByOrderByBoardStarDesc();
		System.out.println(findAllByOrderByBoardStarDesc);
	}

	@Test // 별점 낮은순
	@Transactional
	@Disabled
	void findAllByOrderByBoardStarAsc() {
		List<ReviewBoard> findAllByOrderByBoardStarAsc = reviewBoardDao.findAllByOrderByBoardStarAsc();
		System.out.println(findAllByOrderByBoardStarAsc);
	}

	@Test // 최신순 정렬(board Date정렬)
	@Transactional
	@Disabled
	void findAllByOrderByBoardNoDesc() {
		List<ReviewBoard> findAllByOrderByBoardDateDesc = reviewBoardDao.findAllByOrderByBoardDateDesc();
		System.out.println(findAllByOrderByBoardDateDesc);
	}

	@Test // 오래된순 정렬(board Date정렬)
	@Transactional
	@Disabled
	void findAllByOrderByBoardNoAsc() {
		List<ReviewBoard> findAllByOrderByBoardDateAsc = reviewBoardDao.findAllByOrderByBoardDateAsc();
		System.out.println(findAllByOrderByBoardDateAsc);
	}

	@Test // 별점 높은순,최신순
	@Transactional
	@Rollback(false)
	@Disabled
	public void findByOrderByBoardStarDescBoardDateDesc() {
		List<ReviewBoard> findByOrderByBoardStarDescBoardDateDesc = reviewBoardDao
				.findByOrderByBoardStarDescBoardDateDesc();

		System.out.println(findByOrderByBoardStarDescBoardDateDesc);
	}

	@Test // 별점 낮은순,최신순
	@Transactional
	@Rollback(false)
	@Disabled
	public void findByOrderByBoardStarAscBoardDateDesc() {
		List<ReviewBoard> findByOrderByBoardStarAscBoardDateDesc = reviewBoardDao
				.findByOrderByBoardStarAscBoardDateDesc();

		System.out.println(findByOrderByBoardStarAscBoardDateDesc);
	}

	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void testCalculateAverageStarRating() {
		// 상품 번호를 사용하여 별점 평균을 계산
		Long productNo = 3L;
		double averageRating = reviewBoardDao.calculateAverageStarRating(productNo);

		// 별점 평균을 검증
		double expectedAverageRating = 3.6; // 예상한 평균 별점
		assertEquals(expectedAverageRating, averageRating, 0.1); // 두 값이 오차 내에서 동일한지 확인

		System.out.println("별점 평균: " + averageRating);
	}

	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void testfindByProductProductNoOrderByBoardStarDesc() {

		Long productNo = 1L;
		List<ReviewBoard> reviews = reviewBoardDao.findAllByProductNoAndOrderByBoardStarDesc(productNo);

		assert !reviews.isEmpty();
		// 리뷰 정렬 확인
		double prevRating = reviews.get(0).getBoardStar();
		for (ReviewBoard review : reviews) {
			assert review.getBoardStar() <= prevRating;
			prevRating = review.getBoardStar();
		}
		System.out.println(reviews);
	}

}
