package com.itwill.service;

import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.dao.PetDao;
import com.itwill.dto.PetDto;
import com.itwill.entity.Adopt;
import com.itwill.entity.Pet;
import com.itwill.entity.Product;

@Transactional
@Service
public class PetServiceImpl implements PetService {
	@Autowired
	private PetDao petDao;

	@Override
	public Pet petFindById(Long petNo) {
		Pet pet = petDao.petFindById(petNo);
		return pet;
	}

	@Override
	public Page<Pet> petFindAllPage(Pageable pageable) {
		Page<Pet> petList = petDao.petFindAllPage(pageable);
		return petList;
	}

	public List<Pet> petFindAll() {
		List<Pet> petList = petDao.petFindAll();
		return petList;
	}

	@Override
	public Pet petSave(Pet pet) {

		return petDao.petInsert(pet);
	}

	@Override
	public void petRemove(Long petNo) throws Exception {

		petDao.petDelete(petNo);
	}

	@Override
	public Pet petUpdate(Pet pet) throws Exception {
		return petDao.petUpdate(pet);
	}

	@Override
	public List<Pet> findAllByOrderBypetNoDesc() {

		return petDao.findAllByOrderBypetNoDesc();
	}

	@Override
	public Page<Pet> findAllByOrderBypetType(String petType, Pageable pageable) {
		return petDao.findAllByOrderByPetType(petType, pageable);
	}

	@Override
	public Page<Pet> findAllByPetLocal(String petLocal, Pageable pageable) {
		return petDao.findAllByPetLocal(petLocal, pageable);
	}

	@Override
	public Page<Pet> findAllByPetTypeByPetLocal(String petType, String petLocal, Pageable pageable) {
		return petDao.findAllByPetTypeByPetLocal(petType, petLocal, pageable);
	}

	/*
	 * @Override public Page<Pet> findAllByOrderBypetType(String petType) { return
	 * petDao.findAllByOrderByPetType(petType); }
	 * 
	 * @Override public Page<Pet> findAllByPetLocal(String petLocal) {
	 * 
	 * return petDao.findAllByPetLocal(petLocal); }
	 */

	@Override
	public List<Pet> petFindCenterNo(Long centerNo) {
		List<Pet> petList = petDao.petFindCenterNo(centerNo);
		return petList;
	}

	@Override
	public void deleteByCenterNo(Long centerNo) throws Exception {
		petDao.deleteByCenterNo(centerNo);
		
	}

	@Override
	public List<Pet> findAllByOrderBypetType(String petType) {
		List<Pet> petList=petDao.findAllByOrderByPetType(petType);
		return petList;
	}
	
	@Override
	public List<Pet> findAllByPetLocal(String petLocal) {
		List<Pet> petList=petDao.findAllByPetLocal(petLocal);
		return petList;
	}

	@Override
	public List<Pet> findAllByPetTypeByPetLocal(String petType, String petLocal) {
		return petDao.findAllByPetTypeByPetLocal(petType, petLocal);
	}

}
