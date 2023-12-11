package com.itwill.dto;

import java.util.Date;

import com.itwill.entity.Userinfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class UserLoginActionDto {
	
	private Long userNo;
	private String userId;
	private String userPassword;
	private int status;
		
	public static Userinfo toEntity(UserLoginActionDto dto) {
		Userinfo userinfo = Userinfo.builder()
				.userNo(dto.getUserNo())
				.userId(dto.getUserId())
				.userPassword(dto.getUserPassword())
				.build();
		return userinfo;
	}
	
}
