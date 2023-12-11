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
public class UserWriteActionDto {
	
	private Long userNo;
	private String userId;
	private String userPassword;
	private String userName;
	private String userGender;
	private String userAddress;
	private String userPhoneNumber;
	private String userResidentNumber;
	private LocalDateTime userRegisterDate;
	private Integer userPoint;
	
	List<MyPet> myPets = new ArrayList<MyPet>();
	
	public static Userinfo toEntity(UserWriteActionDto dto) {
		Userinfo userinfo = Userinfo.builder()
				.userId(dto.getUserId())
				.userPassword(dto.getUserPassword())
				.userName(dto.getUserName())
				.userGender(dto.getUserGender())
				.userAddress(dto.getUserAddress())
				.userPhoneNumber(dto.getUserPhoneNumber())
				.userResidentNumber(dto.getUserResidentNumber())
				.userRegisterDate(dto.getUserRegisterDate())
				.userPoint(dto.getUserPoint())
				.build();
		return userinfo;
	}
	
}
