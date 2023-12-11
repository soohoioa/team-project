package com.itwill.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.itwill.entity.ReviewBoard;

public interface ReviewBoardRepository extends JpaRepository<ReviewBoard, Long> {

	public List<ReviewBoard> findAllByOrderByBoardDateDesc(); // 최신순 정렬(board Date정렬)

	public List<ReviewBoard> findAllByOrderByBoardDateAsc(); // 오래된순 정렬(board Date정렬)

	public List<ReviewBoard> findAllByOrderByBoardStarDesc(); // 높은 평점순 정렬

	public List<ReviewBoard> findAllByOrderByBoardStarAsc(); // 낮은 평점순 정렬

	List<ReviewBoard> findByProductProductNo(Long productNo);// productNo로 reviewboard 리스트 검색

	List<ReviewBoard> findByOrderByBoardStarDescBoardDateDesc(); // 별점 높은순,최신순

	List<ReviewBoard> findByOrderByBoardStarAscBoardDateDesc(); // 별점 낮은순,최신순


	List<ReviewBoard> findByProductProductNoOrderByBoardStarDesc(Long productNo);
//별점높음
	List<ReviewBoard> findByProductProductNoOrderByBoardDateDesc(Long productNo);//최신

	public List<ReviewBoard> findAllByBoardStar(Double star);

	@Query(value = "SELECT * FROM ReviewBoard WHERE user_no = ?1", nativeQuery = true)
	List<ReviewBoard> findByUserNo(Long no); // 선택된 userId 리뷰 리스트만 나오기

	@Query("SELECT AVG(r.boardStar) FROM ReviewBoard r WHERE r.product.productNo = :productNo")
	Double calculateAverageStarRating(@Param("productNo") Long productNo); // 상품 번호를 사용하여 별점 평균계산
	
	@Modifying
	@Query(value ="select * from reviewboard where user_no=?1 and product_no=?2",nativeQuery = true)
	public List<ReviewBoard> findByUserNoAndProductNo(Long userNo, Long productNo);
	
	@Query(value = "select * from reviewboard where oi_no=?1",nativeQuery = true)
	public ReviewBoard findByOrderItemNo(Long oiNo);
}
