package com.itwill.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.itwill.entity.ReportBoard;
import com.itwill.entity.ReviewBoard;
import com.itwill.entity.Userinfo;

public interface ReportBoardRepository extends JpaRepository<ReportBoard, Long>{
	

	@Query(value="select * from reportboard where user_id=?1",nativeQuery = true)
	public List<ReportBoard> findByUserId(String userId);
	
	@Query(value="select * from reportboard where user_no=?1",nativeQuery = true)
	public List<ReportBoard> findByUserNo(Long userNo);
	
	@Query(value="select * from reportboard where user_id like '%'|| ?1 || '%'",nativeQuery = true)
	public List<ReportBoard> findAllByLikeUserId(String userId);
	
	@Modifying(clearAutomatically = true)
	@Query(value="update reportboard set board_read_count=board_read_count+1 where board_no=?1",nativeQuery = true)
	public void countReadCount(Long boardNo);
	
	@Query(value="select * from reportboard order by board_no desc",nativeQuery = true)
	public List<ReportBoard> findByBoardNoOrderByBoardNoDesc();
	
	@Query(value="select * from reportboard where board_image=?1",nativeQuery = true)
	public List<ReportBoard> findByBoardImage(String boardImage);
}
