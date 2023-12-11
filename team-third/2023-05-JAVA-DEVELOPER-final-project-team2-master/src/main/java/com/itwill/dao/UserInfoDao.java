package com.itwill.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itwill.entity.Userinfo;

public interface UserInfoDao {
	
	public Userinfo CreateUser(Userinfo userinfo);
	
	public void DeleteUserByNo(Long userNo);
	
	public Userinfo UpdateUser(Userinfo userinfo);
	
	public List<Userinfo> findAll();
	
	public Userinfo findByNo(Long userNo);
	
	//아이디 중복체크 
	public Integer countByUserId(String userId); //이게 필요한가?
	
	public Userinfo findByUserPhone(String userPhoneNumber);
	
	//비밀번호 찾기 
	public Userinfo findPasswordByUserIdPhoneNumber(String userId , String userPhoneNumber);
	
	public Userinfo findByUserId(String userId);
	
	//아이디찾기
	public Userinfo findUserIdByNameAndPhoneNumber(String userName,String userPhoneNumber);
	
	public Page<Userinfo> findAllPage(Pageable pageable);
	
}