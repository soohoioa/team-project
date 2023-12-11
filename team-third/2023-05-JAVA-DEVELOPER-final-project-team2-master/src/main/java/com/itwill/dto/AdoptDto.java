package com.itwill.dto;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.itwill.entity.Adopt;
import com.itwill.entity.Pet;
import com.itwill.entity.Userinfo;
import com.itwill.service.PetService;
import com.itwill.service.UserInfoService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AdoptDto {
	private Long userNo;
	private Long adoptNo;
	private Integer adoptTime;
	private Date adoptDate;
	private String adoptStatus;
	private Long petNo;
	
	public static Adopt toEntity(AdoptDto dto) {
		Adopt adopt= Adopt.builder()
				.userinfo(Userinfo.builder().userNo(dto.getUserNo()).build())
				.adoptNo(dto.getAdoptNo())
				.adoptDate(dto.getAdoptDate())
				.adoptStatus(dto.getAdoptStatus())
				.adoptTime(dto.getAdoptTime())
				.pet(Pet.builder().petNo(dto.getPetNo()).build())
				.build();
		
		return adopt;
	}

	public static AdoptDto fromEntity(Adopt adopt) {
        return AdoptDto.builder()
        		.userNo(adopt.getUserinfo().getUserNo())
                .adoptNo(adopt.getAdoptNo())
                .adoptDate(adopt.getAdoptDate())
                .adoptStatus(adopt.getAdoptStatus())
                .adoptTime(adopt.getAdoptTime())
                .petNo(adopt.getPet().getPetNo())
                .build();
    }
	

	
	
	
}
