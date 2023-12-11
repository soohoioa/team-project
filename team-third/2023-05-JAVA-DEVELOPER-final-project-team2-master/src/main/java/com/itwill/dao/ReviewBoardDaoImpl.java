package com.itwill.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwill.entity.ReviewBoard;
import com.itwill.repository.ReviewBoardRepository;

@Repository
public class ReviewBoardDaoImpl implements ReviewBoardDao {
	@Autowired
	ReviewBoardRepository reviewBoardRepository;

	@Override
	public ReviewBoard create(ReviewBoard reviewBoard) {
		return reviewBoardRepository.save(reviewBoard);
	}

	@Override
	public ReviewBoard update(ReviewBoard reviewBoard) {
		if (reviewBoardRepository.findById(reviewBoard.getBoardNo()).isPresent()) {
			reviewBoardRepository.save(reviewBoard);
		}
		return reviewBoard;
	}

	@Override
	public void deleteById(Long boardNo) {
		if (reviewBoardRepository.findById(boardNo).isPresent()) {
			reviewBoardRepository.deleteById(boardNo);
		}

	}

	@Override
	public List<ReviewBoard> findAll() {
		return reviewBoardRepository.findAll();
	}

	@Override
	public List<ReviewBoard> findByStarAll(Double star) {
		return reviewBoardRepository.findAllByBoardStar(star);
	}

	@Override
	public List<ReviewBoard> findByProductNo(Long productNo) {
		// productNo로 reviewboard 리스트 검색
		return reviewBoardRepository.findByProductProductNo(productNo);
	}

	// userId로 리뷰 리스트 나오기
	@Override
	public List<ReviewBoard> findByUserNo(Long no) {
		return reviewBoardRepository.findByUserNo(no);
	}

	// 높은평점순 정렬
	@Override
	public List<ReviewBoard> findAllByOrderByBoardStarDesc() {
		return reviewBoardRepository.findAllByOrderByBoardStarDesc();
	}

	// 낮은평점순 정렬
	@Override
	public List<ReviewBoard> findAllByOrderByBoardStarAsc() {
		return reviewBoardRepository.findAllByOrderByBoardStarAsc();

	}

	// 최신순 정렬(board Date정렬)
	@Override
	public List<ReviewBoard> findAllByOrderByBoardDateDesc() {
		return reviewBoardRepository.findAllByOrderByBoardDateDesc();
	}

	// 오래된순 정렬(board Date정렬)
	@Override
	public List<ReviewBoard> findAllByOrderByBoardDateAsc() {
		return reviewBoardRepository.findAllByOrderByBoardDateAsc();
	}

	// 별점 높은순,최신순

	@Override
	public List<ReviewBoard> findByOrderByBoardStarDescBoardDateDesc() {
		return reviewBoardRepository.findByOrderByBoardStarDescBoardDateDesc();
	}

//별점 낮은순,최신순

	@Override
	public List<ReviewBoard> findByOrderByBoardStarAscBoardDateDesc() {
		return reviewBoardRepository.findByOrderByBoardStarAscBoardDateDesc();
	}

	@Override
	public ReviewBoard findByBoardNo(Long BoardNo) {
		
		return reviewBoardRepository.findById(BoardNo).get();
	}

	@Override
	public double calculateAverageStarRating(Long productNo) {
		//상품 번호를 사용하여 별점 평균계산
		return reviewBoardRepository.calculateAverageStarRating(productNo);
	}

	@Override
	public List<ReviewBoard> findAllByProductNoAndOrderByBoardStarDesc(Long productNo) {
		// 별점높음
		return reviewBoardRepository.findByProductProductNoOrderByBoardStarDesc(productNo);
	}

	@Override
	public List<ReviewBoard> findByProductProductNoOrderByBoardDateDesc(Long productNo) {
		// 최신
		return reviewBoardRepository.findByProductProductNoOrderByBoardDateDesc(productNo);
	}

	@Override
	public List<ReviewBoard> findByUserNoAndProductNo(Long userNo, Long productNo) {
		return reviewBoardRepository.findByUserNoAndProductNo(userNo, productNo);
	}

	@Override
	public ReviewBoard findByOrderItemNo(Long oiNo) {
		return reviewBoardRepository.findByOrderItemNo(oiNo);
	}

}
