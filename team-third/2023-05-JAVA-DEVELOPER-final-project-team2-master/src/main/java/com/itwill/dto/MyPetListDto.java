package com.itwill.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.itwill.entity.MyPet;
import com.itwill.entity.Userinfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import oracle.net.aso.b;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyPetListDto {

	private Long mypetNo;
	private int mypetSequence;
	private String mypetName;
	private String mypetBirthday;
	private String mypetKind;
	
	
	private String mypetLeader;
	
}
