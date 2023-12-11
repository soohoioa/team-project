package com.itwill.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.itwill.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

	// 카트에 담기
	//Cart insertCart(Cart cart);

	// 카트에 담긴 상품수량 변경
	//int update_qty(Cart cart);

	// 카트에 담긴 상품 종류 변경
	//int update_product(Cart cart);

	// 카트에 담긴 상품 삭제
	//int deleteCart(Cart cart);

	// 카트에 담긴 상품 전체삭제
	@Modifying(clearAutomatically = true)
	@Query(value = "delete from cart where user_no=?1", nativeQuery = true)
	void deleteByUserNo(Long no);
	
	// 카트에 담긴 모든 상품 합계 금액
	//@Query(value = "select SUM(p.product_price) from cart c join product p on c.product_no = p.product_no where c.user_id=?1", nativeQuery = true)
	//Integer cartTotalPrice(Long userId);
	
	// 카트 중복체크
	@Query(value = "select count(*) from cart c join userinfo u on c.user_no=u.user_no where c.user_no=?1 and c.product_no=?2", nativeQuery = true)
	Integer countProductByUserNo(Long userNo, Long productNo);
	
	@Query(value = "select c.*, u.user_no as userinfo_user_no from cart c join userinfo u on c.user_no=u.user_no where c.user_no=?1 and c.product_no=?2", nativeQuery = true)
	Cart findByProductUserNo(Long userNo, Long productNo);
	
	// 카트에 중복제품이 있으면 (중복체크) --> 업데이트 돼서 담기도록 
	//@Query(value = "")
	//int productWithKindByUserId(Cart cart);

	// 카트에 담긴 모든 상품 출력
	//List<Cart> findAll();
	
	// 카트에 담긴 모든 상품 출력 (아이디 별)
	@Query(value = "select * from cart where user_no=?1 order by cart_no asc", nativeQuery = true)
	List<Cart> findAllCartByUserNo(Long userNo);
	
	// 카트에 있는 갯수 출력 (userNo)
	@Query(value = "select count(*) from cart where user_no=?1", nativeQuery = true)
	Integer countCart(Long userNo);
}
