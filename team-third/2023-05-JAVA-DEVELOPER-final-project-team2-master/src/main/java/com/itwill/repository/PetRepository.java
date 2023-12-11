package com.itwill.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itwill.entity.Center;
import com.itwill.entity.Pet;
import com.itwill.entity.Product;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
	// 최신 등록순으로 정렬
	List<Pet> findAllByOrderByPetNoDesc();

	// centerno로 찾기
	List<Pet> findByCenterCenterNo(Long centerNo);

	// 펫 타입으로 정렬
	@Query(value = "select * from Pet where pet_type = :petType", nativeQuery = true)
	Page<Pet> findByPetType(@Param("petType") String petType, Pageable pageable);
	
	@Query(value = "select * from Pet where pet_type = :petType", nativeQuery = true)
	List<Pet> findByPetType(@Param("petType") String petType);

	// 지역으로 정렬
	@Query(value = "select * from Pet where pet_local =:petLocal", nativeQuery = true)
	Page<Pet> findByPetLocal(@Param("petLocal") String petLocal, Pageable pageable);
	
	@Query(value = "select * from Pet where pet_local =:petLocal", nativeQuery = true)
	List<Pet> findByPetLocal(@Param("petLocal") String petLocal);

	// 선택된 펫과 지역 모두 조회
	@Query(value = "select * from Pet where pet_type = :petType and pet_local = :petLocal", nativeQuery = true)
	Page<Pet> findAllPetTypeByPetLocal(@Param("petType") String petType, @Param("petLocal") String petLocal,
			Pageable pageable);
	
	@Query(value = "select * from Pet where pet_type = :petType and pet_local = :petLocal", nativeQuery = true)
	List<Pet> findAllPetTypeByPetLocal(@Param("petType") String petType, @Param("petLocal") String petLocal
			);
	
	//해당센터넘버를 포함하는 펫삭제
	@Modifying
	@Query("DELETE FROM Pet p WHERE p.center.centerNo = :centerNo")
	void deleteByCenterNo(@Param("centerNo") Long centerNo);
}
