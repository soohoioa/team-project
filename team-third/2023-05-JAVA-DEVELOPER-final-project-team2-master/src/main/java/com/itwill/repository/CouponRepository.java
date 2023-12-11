package com.itwill.repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.itwill.entity.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

	@Modifying
	@Query(value = "select * from coupon where coupon_expiration_date < sysdate", nativeQuery = true)
	public List<Coupon> findByExpirationDateBefore();

	// 유저에 대한 쿠폰 리스트 뽑기
	@Modifying
	@Query(value = "select * from coupon where user_no=?1", nativeQuery = true)
	public List<Coupon> findAllByUserNo(Long userNo);

	// 유저에 따른 만료쿠폰 제거
	@Modifying
	@Query(value = "delete from coupon where coupon.coupon_expiration_date< sysdate and coupon.user_no=?1", nativeQuery = true)
	void deleteExpireCouponByUserNo(Long userNo);

}
