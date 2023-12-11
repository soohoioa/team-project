package com.itwill.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.itwill.entity.Pet;
import com.itwill.repository.PetRepository;

@Repository
public class PetDaoImpl implements PetDao {
	@Autowired
	PetRepository petRepository;
	
	@Override
	public Pet petFindById(Long petNo) {
		Pet pet = petRepository.findById(petNo).get();	
		return pet;
	}

	@Override
	
	public Page<Pet> petFindAllPage(Pageable pageable) {
		Page<Pet> petList = petRepository.findAll(pageable);
		return petList;
	}
	
	/*
	 * public List<Pet> petFindAll() { List<Pet> petList = petRepository.findAll();
	 * return petList; }
	 */
	 
	@Override
	public Pet petInsert(Pet pet) {
		Pet petSave = petRepository.save(pet);
		return petSave;
	}

	@Override
	public void petDelete(Long petNo) throws Exception {
		
		petRepository.deleteById(petNo);
	}
		
	

	@Override
	public Pet petUpdate(Pet updatepet)throws Exception {
		return petRepository.save(updatepet);
	}

	//최신등록순 정렬
	@Override
	public List<Pet> findAllByOrderBypetNoDesc() {
		List<Pet> petList = petRepository.findAllByOrderByPetNoDesc();
		return petList;
	}
	
	
	 @Override public List<Pet> findAllByOrderByPetType(String petType) {
	 List<Pet> petList = petRepository.findByPetType(petType);
	 return petList; }
	  
	 @Override public List<Pet> findAllByPetLocal(String petLocal) { 
		 List<Pet>
	  petList = petRepository.findByPetLocal(petLocal); 
		 return petList; }
	 

		@Override
		public List<Pet> petFindAll() {
			List<Pet> petList = petRepository.findAll();
			return petList;
		}

		@Override
		public Page<Pet> findAllByPetLocal(String petLocal, Pageable pageable) {
			Page<Pet> petList = petRepository.findByPetLocal(petLocal,pageable);
			return petList;
		}

		@Override
		public Page<Pet> findAllByOrderByPetType(String petType, Pageable pageable) {
			Page<Pet> petList = petRepository.findByPetType(petType,pageable);
			return petList;
		}

		@Override
		public Page<Pet> findAllByPetTypeByPetLocal(String petType, String petLocal, Pageable pageable) {
			Page<Pet> petList = petRepository.findAllPetTypeByPetLocal(petType,petLocal,pageable);
			return petList;
		}

		@Override
		public List<Pet> petFindCenterNo(Long centerNo) {
			List<Pet> petList = petRepository.findByCenterCenterNo(centerNo);
			return petList;
		}
		
		@Override
		public void deleteByCenterNo(Long centerNo) {
			petRepository.deleteByCenterNo(centerNo);
			
		}
		@Override
		public List<Pet> findAllByPetTypeByPetLocal(String petType, String petLocal) {
			List<Pet> petList =petRepository.findAllPetTypeByPetLocal(petType, petLocal);
			return petList;
		}
	
	

}
