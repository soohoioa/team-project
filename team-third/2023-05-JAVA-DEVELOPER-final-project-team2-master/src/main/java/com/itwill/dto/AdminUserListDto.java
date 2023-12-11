package com.itwill.dto;

import com.itwill.entity.Userinfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserListDto {

	private Long userNo;
	private String userId;
	private String userName;
	private Integer userPoint;
	private String userGender;
	private String userAddress;
	private String userPhoneNumber;
	
	
	
	public static Userinfo toEntity(AdminUserListDto dto) {
		Userinfo user = Userinfo.builder()
				.userNo(dto.getUserNo())
				.userId(dto.getUserId())
				.userName(dto.getUserName())
				.userPoint(dto.getUserPoint())
				.userGender(dto.getUserGender())
				.userAddress(dto.getUserAddress())
				.userPhoneNumber(dto.getUserPhoneNumber())
				.build();
		return user;
		
		}
	
	
	public static AdminUserListDto toDto(Userinfo userinfo) {
		AdminUserListDto dto = AdminUserListDto.builder()
				.userNo(userinfo.getUserNo())
				.userId(userinfo.getUserId())
				.userName(userinfo.getUserName())
				.userPoint(userinfo.getUserPoint())
				.userGender(userinfo.getUserGender())
				.userAddress(userinfo.getUserAddress())
				.userPhoneNumber(userinfo.getUserPhoneNumber())
				.build();
		return dto;
	}
}
