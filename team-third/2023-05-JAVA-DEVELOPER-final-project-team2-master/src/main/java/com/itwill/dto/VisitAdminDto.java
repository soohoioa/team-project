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
public class VisitAdminDto {

    private Long visitNo;
    private Integer visitTime;
    private Date visitDate;
    private String visitStatus;
    private Long userNo;
    private Long centerNo;
    private String centerName; // 추가

    private Userinfo userinfo;

    public static VisitAdminDto toDto(Visit visit) {
        return VisitAdminDto.builder()
                .visitNo(visit.getVisitNo())
                .visitDate(visit.getVisitDate())
                .visitTime(visit.getVisitTime())
                .visitStatus(visit.getVisitStatus())
                .userNo(visit.getUserinfo().getUserNo())
                .centerNo(visit.getCenter().getCenterNo())
                .centerName(visit.getCenter().getCenterName()) 
                .userinfo(visit.getUserinfo())
                .build();
    }
}
