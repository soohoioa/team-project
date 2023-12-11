package com.itwill.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itwill.entity.Pet;

public interface PetDao {

	Pet petFindById(Long petNo); // pk로 찾기

	List<Pet> petFindAll(); // 펫 전부 찾기

	List<Pet> petFindCenterNo(Long centerNo);// centerno로 찾기

	Pet petInsert(Pet pet); // 펫 추가

	void petDelete(Long petNo) throws Exception;

	void deleteByCenterNo(Long centerNo) throws Exception;

	Pet petUpdate(Pet pet) throws Exception;// 펫 업데이트

	List<Pet> findAllByOrderBypetNoDesc();// 펫 no순으로 최신등록순 정렬
	 List<Pet> findAllByOrderByPetType(String petType);

	 List<Pet> findAllByPetLocal(String petLocal);
	 List<Pet> findAllByPetTypeByPetLocal(String petType, String petLocal);

	Page<Pet> petFindAllPage(Pageable pageable);

	Page<Pet> findAllByPetLocal(String petLocal, Pageable pageable);

	Page<Pet> findAllByOrderByPetType(String petType, Pageable pageable);

	Page<Pet> findAllByPetTypeByPetLocal(String petType, String petLocal, Pageable pageable);
}
