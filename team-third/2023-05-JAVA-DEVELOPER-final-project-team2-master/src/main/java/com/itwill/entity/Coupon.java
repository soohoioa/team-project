package com.itwill.entity;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
@Table(name = "COUPON")
public class Coupon {
     @Id
     @SequenceGenerator(name = "Coupon_coupon_id_SEQ",allocationSize = 1,initialValue = 1)
     @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Coupon_coupon_id_SEQ")
     private Long couponId;
	 private String couponName;
	 private Integer couponDiscount;
	 private LocalDateTime couponPayday;
	 private LocalDateTime couponExpirationDate;
	 
	 @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	 @Builder.Default
	 @JoinColumn(name = "user_no")
	 @ToString.Exclude
	 private Userinfo userinfo = new Userinfo();
	 
	 public void setCouponDate(Long days) {
		 this.couponPayday = LocalDateTime.now();
		 this.couponExpirationDate = couponPayday.plusDays(days);
	 }
	 
}

