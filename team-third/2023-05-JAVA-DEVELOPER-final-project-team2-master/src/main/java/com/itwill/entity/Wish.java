package com.itwill.entity;

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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wish {

	@Id
	@SequenceGenerator(name = "Wish_wish_no_SEQ", sequenceName = "Wish_wish_no_SEQ", initialValue = 1,allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Wish_wish_no_SEQ")
	private Long wishNo;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_no")
	@Builder.Default
	private Product product = new Product();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_no")
	@Builder.Default
	private Userinfo userinfo = new Userinfo();
	
	
}
