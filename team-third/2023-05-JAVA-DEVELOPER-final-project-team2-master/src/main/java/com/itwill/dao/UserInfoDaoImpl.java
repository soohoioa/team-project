package com.itwill.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.entity.Userinfo;
import com.itwill.repository.UserinfoRepository;

@Repository
public class UserInfoDaoImpl implements UserInfoDao {
	@Autowired
	UserinfoRepository userinfoRepository;
	
	@Override
	public Userinfo CreateUser(Userinfo userinfo) {
		userinfoRepository.save(userinfo);
		return userinfo;
	}
	
	@Override
	public void DeleteUserByNo(Long userNo) {
		userinfoRepository.deleteById(userNo);
	}
	
	@Override
	public List<Userinfo> findAll() {
		return userinfoRepository.findAll();
	}
	
	@Override
	public Userinfo findByNo(Long userNo) {
		Optional<Userinfo> op = userinfoRepository.findById(userNo);
		Userinfo findUserinfo = op.get();
		return findUserinfo;
	}
	
	@Override
	public Userinfo UpdateUser(Userinfo userinfo) {
		return userinfoRepository.save(userinfo);
	}
	
	/* 아이디 중복체크 */
	@Override
	public Integer countByUserId(String userId) {
		return userinfoRepository.countByUserId(userId);
	}
	
	// 이메일로 아이디찾기
	@Override
	public Userinfo findByUserPhone(String userPhoneNumber) {
		return userinfoRepository.findByUserPhone(userPhoneNumber);
	}
	
	// 아이디,폰번호로 비밀번호찾기
	@Override
	public Userinfo findPasswordByUserIdPhoneNumber(String userId, String userPhoneNumber) {
		return userinfoRepository.findPasswordByUserIdPhoneNumber(userId, userPhoneNumber);
	}
	
	// 아이디로 객체 찾기
	@Override
	public Userinfo findByUserId(String userId) {
		return userinfoRepository.findByUserId(userId);
	}
	
	// 이름과 폰번호로 아이디 찾기
	@Override
	public Userinfo findUserIdByNameAndPhoneNumber(String userName, String userPhoneNumber) {
		return userinfoRepository.findUserIdByNameAndPhoneNumber(userName, userPhoneNumber);
	}

	@Override
	public Page<Userinfo> findAllPage(Pageable pageable) {
		Page<Userinfo> userList = userinfoRepository.findAll(pageable);
		return userList;
	}
	
}
