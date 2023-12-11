package com.itwill.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwill.entity.Adopt;
import com.itwill.repository.AdoptRepository;
@Repository
public class AdoptDaoImpl implements AdoptDao{

	@Autowired
	AdoptRepository adoptRepository;
	
	@Override
	public Adopt insertAdopt(Adopt adopt) {
		Adopt savedAdopt=adoptRepository.save(adopt);
		return savedAdopt;
	}

	@Override
	public Adopt findByAdoptNo(Long no) {
		Adopt selectedAdopt=adoptRepository.findById(no).get();
		return selectedAdopt;
	}

	@Override
	public Adopt updateAdopt(Adopt updateAdopt) throws Exception {
		Optional<Adopt> findAdoptOptional = adoptRepository.findById(updateAdopt.getAdoptNo());
		
		if(findAdoptOptional.isPresent()) {
			Adopt adopt=findAdoptOptional.get();
			return adoptRepository.save(adopt);
		}else {
			throw new Exception("찾을 수 없는 Adopt : " + updateAdopt.getAdoptNo());
		}
		
	}

	@Override
	public void deleteAdopt(Long no) throws Exception {
		Optional<Adopt> selectedAdopt=adoptRepository.findById(no);
		if(selectedAdopt.isPresent()) {
			adoptRepository.delete(selectedAdopt.get());
		}
	}

	@Override
	public List<Adopt> findAdoptList() {
		return adoptRepository.findAll();
	}

	
	@Override
	public List<Adopt> findAdoptsByUserNo(Long userNo) {
		List<Adopt> adoptList=adoptRepository.findAdoptsByUserNo(userNo);
		return adoptList;
	
	}
	
}
