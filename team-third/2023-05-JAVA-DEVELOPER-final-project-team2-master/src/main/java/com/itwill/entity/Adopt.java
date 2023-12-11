package com.itwill.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringExclude;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString(callSuper = true)
public class Adopt {

	@Id
	@SequenceGenerator(name = "Adopt_adopt_no_SEQ", sequenceName = "Adopt_adopt_no_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Adopt_adopt_no_SEQ")
	private Long adoptNo; // pk
	private Integer adoptTime;
	private Date adoptDate;
	private String adoptStatus;

	/*
	 * N:1
	 */
	@Builder.Default
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_no")
	@ToString.Exclude
	private Userinfo userinfo = new Userinfo();

	@Builder.Default
	@OneToOne(fetch = FetchType.LAZY)
	@ToString.Exclude
	@JoinColumn(name = "pet_no")
	private Pet pet = new Pet();

}