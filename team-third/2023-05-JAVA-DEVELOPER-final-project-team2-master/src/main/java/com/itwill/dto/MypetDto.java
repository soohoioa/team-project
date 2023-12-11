package com.itwill.dto;

import java.time.LocalDateTime;
import java.util.Date;

import com.itwill.entity.MyPet;
import com.itwill.entity.Userinfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MypetDto {
	
	private Long mypetNo;
	private String mypetName;
	private LocalDateTime mypetBirthday;
	private String mypetKind; 
	private Long userNo;
	private Integer mypetStatus;
	 
	 public static MyPet toEntity(MypetDto mypetDto) {
		 return MyPet.builder()
					 .mypetNo(mypetDto.getMypetNo())
					 .mypetName(mypetDto.getMypetName())
					 .mypetBirthday(mypetDto.getMypetBirthday())
					 .mypetKind(mypetDto.getMypetKind())
					 .userinfo(Userinfo.builder().userNo(mypetDto.getUserNo()).build())
					 .build();
	 }
		  
	 public static MypetDto toDto(MyPet mypetEntity) {
		 return MypetDto.builder()
						.mypetNo(mypetEntity.getMypetNo())
						.mypetName(mypetEntity.getMypetName())
						.mypetBirthday(mypetEntity.getMypetBirthday())
						.mypetKind(mypetEntity.getMypetKind())
						.userNo(mypetEntity.getUserinfo().getUserNo())
						.build();
	}

}
