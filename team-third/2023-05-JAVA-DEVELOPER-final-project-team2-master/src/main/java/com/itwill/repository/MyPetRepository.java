package com.itwill.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.itwill.entity.MyPet;

public interface MyPetRepository extends JpaRepository<MyPet, Long>{
	
	@Query(value = "select * from mypet where user_no=?1",nativeQuery = true)
	public List<MyPet> findMyPetListByuserNo(Long userNo);
	
	
	// 마이펫 중 1마리 삭제
	@Modifying
	@Query(value="delete from mypet where mypet_no=?1",nativeQuery = true)
	public void deleteMypetByUserNo(Long mypetNo);
	
	// 마이펫 전체 삭제
	@Modifying
	@Query(value="delete from mypet where user_no=?1",nativeQuery = true)
	public void deleteMypetAllByUserNo(Long userNo);
	
	//마이펫 대표 뽑기 
	@Query(value="SELECT * FROM (SELECT * FROM mypet WHERE user_no = ?1 ORDER BY mypet_no) WHERE ROWNUM = 1",nativeQuery = true)
	public MyPet findLeaderMyPet(Long userNo);
	
//	//마이펫 
//	@Query(value="select * from where mypetNo=?",nativeQuery = true)
//	public MyPet findMyPetBymypetNo (Long mypetNo);
//	
	
}
