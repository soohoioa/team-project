package com.itwill.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itwill.dto.UserLoginActionDto;
import com.itwill.dto.UserWriteActionDto;
import com.itwill.entity.Userinfo;

public interface UserInfoService {
	
	/*
	 * 회원가입
	 */
	/**************1.반환값사용*******************/
	Userinfo create(Userinfo user) throws Exception;
	/*********************************************/

	/*
	 * 회원로그인
	 * 
	 * 0:아이디존재안함
	 * 1:패쓰워드 불일치
	 * 2:로그인성공
	 */	
	Userinfo login(String userId, String password) throws Exception;
	/*
	 * 회원로그아웃
	 */

	/*
	 * 회원상세보기
	 */
	Userinfo findUserByNo(Long userNo) throws Exception;

	/*
	 * 회원수정
	 */
	Userinfo update(Userinfo user) throws Exception;

	/*
	 * 회원탈퇴
	 */
	void remove(Long userNo) throws Exception;

	/*
	 * 전체회원리스트
	 */
	List<Userinfo> findUserList() throws Exception;
	
	/* 아이디 중복체크 */
	boolean countByUserId(String userId) throws Exception;
	
	boolean checkPassword(UserLoginActionDto userLoginActionDto) throws Exception;
	
	
	
	public Userinfo findUserIdByNameAndPhoneNumber(String userName,String userPhoneNumber);
	
	public Userinfo findPasswordByUserIdPhoneNumber(String userId, String userPhoneNumber);
	
	public Page<Userinfo> findAllPage(Pageable pageable);
	
}
