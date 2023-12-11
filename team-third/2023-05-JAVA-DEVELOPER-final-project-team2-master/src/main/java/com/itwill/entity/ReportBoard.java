package com.itwill.entity;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
@Table(name = "reportboard")
public class ReportBoard {
    
	@Id
	@SequenceGenerator(name = "REPORTBOARD_BOARD_NO_SEQ", sequenceName = "REPORTBOARD_BOARD_NO_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REPORTBOARD_BOARD_NO_SEQ")
	private Long boardNo;
	private String boardTitle;
	private Date boardRegisterDate;
	private String boardContent;
	private Date boardFindDate;
	@Column(name = "board_readcount")
	private Integer boardReadCount;
	
	private String boardFindName;
	
	@Column(unique = true)
	private String boardFindPhone;
	
	// 유기견 대표이미지
	private String boardImage;
	private String boardFindPlace;
	
	@ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	@JoinColumn(name = "user_no")
	@Builder.Default
	@ToString.Exclude
	private Userinfo userinfo = new Userinfo();
	
	@OneToMany(mappedBy = "reportBoard", cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	@Builder.Default
	@ToString.Exclude
	List<ReplyBoard> replyBoards=new ArrayList<ReplyBoard>();



}