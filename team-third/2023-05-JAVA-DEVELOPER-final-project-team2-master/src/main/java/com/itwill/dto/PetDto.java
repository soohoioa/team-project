package com.itwill.dto;

import java.util.Date;

import com.itwill.entity.Center;
import com.itwill.entity.Pet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class PetDto {

	
	private Long petNo;
	 private String petLocal;
	 private String petType;
	 private String petGender;
	 private Date  petRegisterDate;
	 private String petFindPlace;
	 private String petCharacter;
	 private String petImage;
	 
	 private Long centerNo;
	 private Center center;
	 
	 
	 
	 public static Pet toEntity(PetDto petDto) {
		 return Pet.builder()
		 .petNo(petDto.getPetNo())
		 .petLocal(petDto.getPetLocal())
		 .petGender(petDto.getPetGender())
		 .petRegisterDate(petDto.getPetRegisterDate())
		 .petFindPlace(petDto.getPetFindPlace())
         .petCharacter(petDto.getPetCharacter())
         .petImage(petDto.getPetImage())
         .center(Center.builder().centerNo(petDto.getCenterNo()).build())
         //.center()
		 .build();
		  
	 }
	 
	 public static PetDto toDto(Pet petEntity) {
	return PetDto.builder()
			.petNo(petEntity.getPetNo())
			.petLocal(petEntity.getPetLocal())
			.petGender(petEntity.getPetGender())
			.petRegisterDate(petEntity.getPetRegisterDate())
			.petCharacter(petEntity.getPetCharacter())
	         .petImage(petEntity.getPetImage())
	         .petType(petEntity.getPetType())
	         .petFindPlace(petEntity.getPetFindPlace())
	         .centerNo(petEntity.getCenter().getCenterNo())
	         .center(petEntity.getCenter())
			.build();
		  
	 }
	 
}
