package com.itwill.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.itwill.entity.Adopt;

public interface AdoptService {

	public Adopt insertAdopt(Adopt adopt);

	public Adopt findByAdoptNo(Long no);

	public Adopt updateAdopt(Adopt adopt) throws Exception;

	public void deleteAdopt(Long no) throws Exception;

	public List<Adopt> findAdoptList();

	public List<Adopt> findAdoptsByUserNo(Long no);
}
