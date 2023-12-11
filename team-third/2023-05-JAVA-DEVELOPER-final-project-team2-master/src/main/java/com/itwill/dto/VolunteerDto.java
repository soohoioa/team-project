package com.itwill.dto;

import java.util.Date;

import com.itwill.entity.Center;
import com.itwill.entity.Userinfo;
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
public class VolunteerDto {
	
	private Long userNo;
	private Long volunteerNo;
	private Integer volunteerTime;
	private Date volunteerDate;
	private String volunteerStatus;
	private Long centerNo;
	
	private Userinfo userinfo;
	
	public static Volunteer toEntity(VolunteerDto dto) {
		Volunteer volunteer = Volunteer.builder()
						.userinfo(Userinfo.builder().userNo(dto.getUserNo()).build())
						.volunteerNo(dto.getVolunteerNo())
				 		.volunteerTime(dto.getVolunteerTime())
				 		.volunteerDate(dto.getVolunteerDate())
				 		.volunteerStatus(dto.getVolunteerStatus())			
				 		.center(Center.builder().centerNo(dto.getCenterNo()).build())
				 		.build();
		return volunteer;		
	}
	
	public static VolunteerDto toDto(Volunteer volunteer) {
		VolunteerDto volunteerDto = VolunteerDto.builder()
								.userNo(volunteer.getUserinfo().getUserNo())
								.volunteerNo(volunteer.getVolunteerNo())
								.volunteerTime(volunteer.getVolunteerTime())
								.volunteerDate(volunteer.getVolunteerDate())
								.volunteerStatus(volunteer.getVolunteerStatus())
								.centerNo(volunteer.getCenter().getCenterNo())
								.build();
		return volunteerDto;
	}
	
	
}
