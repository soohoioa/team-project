package com.itwill.dto;

import com.itwill.entity.Userinfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoFindDto {

	private String userId;
	private String userName;
	private String userPhoneNumber;
	private Integer status;
	
	public static Userinfo toEntity(UserInfoFindDto dto) {
		Userinfo userinfo = Userinfo.builder()
				.userId(dto.getUserId())
				.userName(dto.getUserName())
				.userPhoneNumber(dto.getUserPhoneNumber())
				.build();
		return userinfo;
	}
	
	public static UserInfoFindDto toDto(Userinfo userinfo) {
		UserInfoFindDto dto = UserInfoFindDto.builder()
											.userId(userinfo.getUserId())
											.userName(userinfo.getUserName())
											.userPhoneNumber(userinfo.getUserPhoneNumber())
											.build();
		return dto;
	}
	
}
