package com.itwill.dto;

import com.itwill.entity.Center;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CenterListDto {

	private Long centerNo;
	// 센터번호
	private String centerName;
	// 센터이름
	private String centerPhoneNumber;
	// 센터전화번호
	private String centerLocal;
	// 센터지역
	private String centerOpenCloseTime;
	// 센터영업시간
	private String centerImage;
	
	public static Center toEntity(CenterListDto dto) {
		Center center = Center.builder()
				.centerNo(dto.getCenterNo())
				.centerName(dto.getCenterName())
				.centerPhoneNumber(dto.getCenterPhoneNumber())
				.centerLocal(dto.getCenterLocal())
				.centerOpenCloseTime(dto.getCenterOpenCloseTime())
				.centerImage(dto.getCenterImage())
				.build();
		return center;
	}

	public static CenterListDto toDto(Center center) {
		CenterListDto centerListDto = CenterListDto.builder()
		            .centerNo(center.getCenterNo())
		            .centerName(center.getCenterName())
		            .centerPhoneNumber(center.getCenterPhoneNumber())
		            .centerLocal(center.getCenterLocal())
		            .centerOpenCloseTime(center.getCenterOpenCloseTime())
		            .centerImage(center.getCenterImage())
		            .build();
		return centerListDto;
}
}
