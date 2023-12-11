package com.itwill.dto;

import com.itwill.entity.Volunteer;

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
public class VolunteerTimePointDto {
	
	private Long userNo;
	private Long volunteerNo;
	private Integer volunteerTime;
	private Integer userPoint;
	
	
	public static VolunteerTimePointDto toDto(Volunteer volunteer) {
		
		VolunteerTimePointDto volunteerTimePointDto = VolunteerTimePointDto.builder()
				.userNo(volunteer.getUserinfo().getUserNo())
				.volunteerNo(volunteer.getVolunteerNo())
				.volunteerTime(volunteer.getVolunteerTime())
				.userPoint(volunteer.getUserinfo().getUserPoint())
				.build();
		
		return volunteerTimePointDto;
	}
	
}
