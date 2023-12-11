package com.itwill.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itwill.entity.MyPet;
import com.itwill.entity.Pet;
import com.itwill.entity.Userinfo;

import jakarta.persistence.Column;
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
public class UserOrderViewDto {
	//user orderview 볼때 입력 정보
	private Long userNo;
	private String userName;
	private String userAddress;
	private String userPhoneNumber;
	private Integer userPoint;
	
	
	public static Userinfo toEntity(UserOrderViewDto dto) {
		Userinfo userinfo = Userinfo.builder()
				.userNo(dto.getUserNo())
				.userName(dto.getUserName())
				.userAddress(dto.getUserAddress())
				.userPhoneNumber(dto.getUserPhoneNumber())
				.userPoint(dto.getUserPoint())
				.build();
		return userinfo;
	}
	
	public static UserOrderViewDto toDto(Userinfo entity) {
		UserOrderViewDto userOrderViewDto = UserOrderViewDto.builder()
				.userNo(entity.getUserNo())
				.userName(entity.getUserName())
				.userAddress(entity.getUserAddress())
				.userPhoneNumber(entity.getUserPhoneNumber())
				.userPoint(entity.getUserPoint())
				
				.build();
		return userOrderViewDto;
	}
	
}
