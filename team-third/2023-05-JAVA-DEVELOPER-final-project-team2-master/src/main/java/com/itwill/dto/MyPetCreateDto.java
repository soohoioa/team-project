package com.itwill.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.itwill.entity.MyPet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyPetCreateDto {
	
	private List<MypetDto> myPets = new ArrayList<MypetDto>();
	
}
