package com.itwill.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.TeamprojectAnimalcareApplicationTest;
import com.itwill.entity.Pet;

class PetServiceImplTest extends TeamprojectAnimalcareApplicationTest{

	@Autowired
	PetService petService ;
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void findPet() {
		petService.petFindById(10L);
		System.out.println(petService.petFindById(10L));
	}
	
	@Test
	@Transactional
	@Rollback(false)
	//@Disabled
	void petList() {
		petService.petFindAll();
		System.out.println(petService.petFindAll());
	}
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void petSave() {
		Pet pet = Pet.builder()
				.petType("퍼그")
			.
			build();
		petService.petSave(pet);
				
	}
	
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void petUpdate() throws Exception{
		Pet pet=petService.petFindById(1L);
		pet.setPetCharacter("성격이 너무나쁘다");
		Pet updatepet=petService.petUpdate(pet);
		System.out.println(updatepet);
	}
	
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void petdelete() throws Exception{
		petService.petRemove(5L);
		
	}
	
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void petdesc() throws Exception{
		petService.findAllByOrderBypetNoDesc();
		System.out.println(petService.findAllByOrderBypetNoDesc());
		
	}
	
	
	
	
	
	

}
