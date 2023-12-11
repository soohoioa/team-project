package com.itwill.dto;

import java.util.Date;

import com.itwill.entity.Center;
import com.itwill.entity.Userinfo;
import com.itwill.entity.Visit;

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
public class VisitDto {

	private Long visitNo;

	private Integer visitTime;

	private Date visitDate;

	private String visitStatus;

	private Long userNo;

	private Long centerNo;

	private Userinfo userinfo;
	
	private Center center;
	public static Visit toEntity(VisitDto dto) {
		Visit visit = Visit.builder()
				.visitNo(dto.getVisitNo())
				.visitDate(dto.getVisitDate())
				.visitTime(dto.getVisitTime())
				.visitStatus(dto.getVisitStatus())
				.userinfo(Userinfo.builder().userNo(dto.getUserNo()).build())
				.center(Center.builder().centerNo(dto.getCenterNo()).build())
				.build();
		
		return visit;
	}
		public static VisitDto toDto(Visit visit) {
			VisitDto visitDto = VisitDto.builder()
					.visitNo(visit.getVisitNo())
					.visitDate(visit.getVisitDate())
					.visitTime(visit.getVisitTime())
					.visitStatus(visit.getVisitStatus())
					.userNo(visit.getUserinfo().getUserNo())
					.centerNo(visit.getCenter().getCenterNo())
					.build();
			return visitDto;
		
		
	}
		
		
}
