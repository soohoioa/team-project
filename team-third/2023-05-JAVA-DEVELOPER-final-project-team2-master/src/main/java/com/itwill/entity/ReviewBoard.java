package com.itwill.entity;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reviewboard")
@ToString(callSuper = true)
public class ReviewBoard {

	@Id
	@SequenceGenerator(name = "ReviewBoard_board_no_SEQ", sequenceName = "ReviewBoard_board_no_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ReviewBoard_board_no_SEQ")
	private Long boardNo; // PK
	private String boardTitle;
	private String boardContent;
	private LocalDateTime boardDate;
	private Double boardStar;

	@Builder.Default
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_no")
	@ToString.Exclude
	private Userinfo userinfo = new Userinfo();
	
	@Builder.Default
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_no")
	@ToString.Exclude
	private Product product = new Product();
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "oi_no")
	@Builder.Default
	private OrderItem orderItem = new OrderItem();
}
