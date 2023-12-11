package com.itwill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.dao.AdoptDao;
import com.itwill.dao.ReviewBoardDao;
import com.itwill.entity.Adopt;
import com.itwill.entity.ReviewBoard;
import com.itwill.repository.ReviewBoardRepository;

@Service
@Transactional
public class ReviewBoardServiceImpl implements ReviewBoardService {

	@Autowired
	private ReviewBoardDao reviewBoardDao;

	@Autowired
	ReviewBoardRepository reviewBoardRepository;

	@Override
	public ReviewBoard create(ReviewBoard reviewBoard) {
		return reviewBoardDao.create(reviewBoard);
	}

	@Override
	public ReviewBoard update(ReviewBoard reviewBoard) {
		return reviewBoardDao.update(reviewBoard);
	}

	@Override
	public void deleteById(Long boardNo) {
		reviewBoardDao.deleteById(boardNo);

	}

	@Override
	public List<ReviewBoard> findAll() {
		return reviewBoardDao.findAll();
	}

	@Override
	public List<ReviewBoard> findByProductNo(Long productNo) {
		// productNo로 reviewboard 리스트 검색
		return reviewBoardDao.findByProductNo(productNo);
	}

	@Override
	public List<ReviewBoard> findByStarAll(Double star) {
		// 선택한 별점으로 찾기
		return reviewBoardDao.findByStarAll(star);
	}

	@Override
	public List<ReviewBoard> findByUserNo(Long no) {
		// 선택된 userNo 리뷰 리스트만 나오기
		return reviewBoardDao.findByUserNo(no);
	}

	@Override
	public List<ReviewBoard> findAllByOrderByBoardStarDesc() {
		// 높은 평점순 정렬
		return reviewBoardDao.findAllByOrderByBoardStarDesc();
	}

	@Override
	public List<ReviewBoard> findAllByOrderByBoardStarAsc() {
		// 낮은 평점순 정렬
		return reviewBoardDao.findAllByOrderByBoardStarAsc();
	}

	@Override
	public List<ReviewBoard> findAllByOrderByBoardDateDesc() {
		// 최신순 정렬(board Date정렬)
		return reviewBoardDao.findAllByOrderByBoardDateDesc();
	}

	@Override
	public List<ReviewBoard> findAllByOrderByBoardDateAsc() {
		// 오래된 순 정렬(board Date정렬)
		return reviewBoardDao.findAllByOrderByBoardDateAsc();
	}

	@Override
	public List<ReviewBoard> findByOrderByBoardStarDescBoardDateDesc() {
		// 별점 높은순,최신순
		return reviewBoardDao.findByOrderByBoardStarDescBoardDateDesc();
	}

	@Override
	public List<ReviewBoard> findByOrderByBoardStarAscBoardDateDesc() {
		// 별점 낮은순,최신순
		return reviewBoardDao.findByOrderByBoardStarAscBoardDateDesc();
	}

	@Override
	public ReviewBoard findByBoardNo(Long BoardNo) {
		return reviewBoardDao.findByBoardNo(BoardNo);
	}
	
	@Override
	public double calculateAverageStarRating(Long productNo) {
		//상품 번호를 사용하여 별점 평균계산
		return reviewBoardRepository.calculateAverageStarRating(productNo);
	}
	
	 @Override
	    public List<ReviewBoard> findByProductProductNoOrderByBoardStarDesc(Long productNo) {
	        return reviewBoardRepository.findByProductProductNoOrderByBoardStarDesc(productNo);
	    }

	@Override
	public List<ReviewBoard> findByProductProductNoOrderByBoardDateDesc(Long productNo) {
		// 최신
		return reviewBoardRepository.findByProductProductNoOrderByBoardDateDesc(productNo);
	}

	@Override
	public List<ReviewBoard> findByUserNoAndProductNo(Long userNo, Long productNo) {
		return reviewBoardDao.findByUserNoAndProductNo(userNo, productNo);
	}

	@Override
	public ReviewBoard findByOrderItemNo(Long oiNo) {
		return reviewBoardDao.findByOrderItemNo(oiNo);
	}
}
