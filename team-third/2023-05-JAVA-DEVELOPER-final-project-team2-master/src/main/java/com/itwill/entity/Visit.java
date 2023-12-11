package com.itwill.entity;

import java.time.LocalDate;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "visit")
public class Visit {

	@Id
	@SequenceGenerator(name = "Visit_visit_no_SEQ", sequenceName = "Visit_visit_no_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Visit_visit_no_SEQ")
	private Long visitNo;// PK

	private Integer visitTime;

	private Date visitDate;

	private String visitStatus;

	@Builder.Default
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "user_no")
	@ToString.Exclude
	private Userinfo userinfo = new Userinfo();

	@Builder.Default
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "center_no")
	@ToString.Exclude
	private Center center = new Center();
	
	
}
