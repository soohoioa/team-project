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
public class UserInfoPassFindDto {

	private String userId;
	private String userName;
	private String userPassword;
	private String userPhoneNumber;
	private Integer status;
	
	public static Userinfo toEntity(UserInfoPassFindDto dto) {
		Userinfo userinfo = Userinfo.builder()
				.userId(dto.getUserId())
				.userName(dto.getUserName())
				.userPhoneNumber(dto.getUserPhoneNumber())
				.userPassword(dto.getUserPassword())
				.build();
		return userinfo;
	}
	
	public static UserInfoPassFindDto toDto(Userinfo userinfo) {
		UserInfoPassFindDto dto = UserInfoPassFindDto.builder()
											.userId(userinfo.getUserId())
											.userName(userinfo.getUserName())
											.userPhoneNumber(userinfo.getUserPhoneNumber())
											.userPassword(userinfo.getUserPassword())
											.build();
		return dto;
	}
	
}
