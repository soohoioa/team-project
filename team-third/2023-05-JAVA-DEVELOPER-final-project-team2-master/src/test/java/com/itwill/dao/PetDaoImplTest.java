package com.itwill.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.TeamprojectAnimalcareApplicationTest;
import com.itwill.entity.Center;
import com.itwill.entity.Pet;
import com.itwill.entity.Product;

class PetDaoImplTest extends TeamprojectAnimalcareApplicationTest{
	@Autowired
	PetDao petDao;
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
void insert() {
		
		Pet pet = Pet.builder()
				.petLocal("서울특별시")
				.petType("프렌치불독")
				.petGender("암컷")
				.petRegisterDate(new Date())
				.petFindPlace("서울특별시")
				.petCharacter("이쁘고 온순하다")
				.build();
		Pet pet2 = Pet.builder()
				.petLocal("서울특별시")
				.petType("진돗개")
				.petGender("수컷")
				.petRegisterDate(new Date())
				.petFindPlace("서울특별시")
				.petCharacter("이쁘고 온순하다")
				.build();
		
		Center center = Center.builder()
				.centerName("범석보호소")
				.centerPhoneNumber("000-000-000")
				.centerLocal("서울특별시 강남구")
				.centerOpenCloseTime("09:00~18:00")
				.build();
		
		pet.setCenter(center);
		pet2.setCenter(center);
	petDao.petInsert(pet);
	petDao.petInsert(pet2);
}
	
	@Test
		@Disabled
void petFind() {
		Pet pet = petDao.petFindById(1L);
		System.out.println(pet);
		
}
	@Test
	@Disabled
	@Transactional
	@Rollback(value = false)
	void updatePet() throws Exception {
		Pet findpet = petDao.petFindById(1L);
		
		findpet.setPetLocal("경기도");
		findpet.setPetType("고양이");
		petDao.petUpdate(findpet);
		
		System.out.println(findpet);
	}
	
	@Test
	@Disabled
	@Transactional
	@Rollback(value = false)
	void deletePet() throws Exception {
		petDao.petDelete(1L);
			}
	
	
	
	//최신등록순
	@Test
	@Disabled
void test2() {
		List<Pet> pet = petDao.findAllByOrderBypetNoDesc();
	System.out.println(pet);
	}
	
	
	//펫 리스트
	@Test
	@Disabled
	void findall() {
		List<Pet> pet = petDao.petFindAll();
	System.out.println(pet);
	}

	//펫 타입리스트
	@Test
	@Disabled
	void findallPetType() {
		
		Pageable pageable = PageRequest.of(0, 5);
		
		Page<Pet> pet = petDao.findAllByOrderByPetType("강아지",pageable);
		System.out.println("Content: " + pet.getContent());
	}
	@Test
void findallPetType2() {
		
		Pageable pageable = PageRequest.of(0, 5);
		
		Page<Pet> pet = petDao.findAllByPetLocal("서울특별시",pageable);
		System.out.println("Content: " + pet.getContent());
	}
	
	/*
	 * //펫 타입리스트
	 * 
	 * @Test //@Disabled void findallPetLocal() { List<Pet> pet =
	 * petDao.findAllByPetLocal("서울특별시"); System.out.println(pet); }
	 */
}


