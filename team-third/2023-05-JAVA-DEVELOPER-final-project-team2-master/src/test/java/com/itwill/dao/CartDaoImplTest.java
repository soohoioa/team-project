package com.itwill.dao;


import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.TeamprojectAnimalcareApplicationTest;
import com.itwill.entity.Cart;
import com.itwill.entity.Product;
import com.itwill.entity.Userinfo;
import com.itwill.repository.CartRepository;


class CartDaoImplTest extends TeamprojectAnimalcareApplicationTest {
	
	@Autowired
	CartDao cartDao;
	@Autowired
	UserInfoDao userInfoDao;
	@Autowired
	ProductDao productDao;
	
	@Test
	@Transactional
	@Rollback(false)
	//@Disabled
	void insertCartTest() {
		/*
		Cart cart1 = Cart.builder()
				.cartNo(null)
				.cartQty(5)
				.userinfo(userInfoDao.findById("전아현"))
				.product(Arrays.asList(cartDao.))
				.cartImage()
				.build();
		
		cartDao.insertCart(cart1);
		Cart cart2 = Cart.builder()
				.cartNo(null)
				.cartQty(5)
				.userinfo(userInfoDao.findById("전아현"))
				.product(productDao.findByProductNo(2L))
				.build();
		cartDao.insertCart(cart2);
		Cart cart3 = Cart.builder()
				.cartNo(null)
				.cartQty(5)
				.userinfo(userInfoDao.findById("전아현"))
				.product(productDao.findByProductNo(3L))
				.build();
		cartDao.insertCart(cart3);
		 */
	}
	
	
	@Test
	@Disabled
	@Transactional
	@Rollback(false)
	void updateCartTest() {
		Cart findCart = cartDao.findByCartNo(1L);
		findCart.setCartQty(50);
		System.out.println(findCart);
	}
	
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void findByCartNoTest() {
		Cart findCart = cartDao.findByCartNo(1L);
		System.out.println(findCart);
	}
	
	
	@Test
	@Disabled
	@Transactional
	@Rollback(false)
	void deleteByUserIdTest(){
		cartDao.deleteByUserId(3L);
	}
	
	@Test
	@Disabled
	void deleteByIdTest() throws Exception{
		cartDao.deleteById(10l);
	}
	
	@Test
	@Disabled
	@Transactional
	@Rollback(false)
	void findAllTest() {
		List<Cart> carts = cartDao.findAll();
		System.out.println(carts);
	}
	
	/*
	@Test
	//@Disabled
	@Transactional
	@Rollback(false)
	void cartTotalPriceTest() {

		Integer totalPrice = cartDao.cartTotalPrice("김창섭");
		System.out.println(totalPrice);
		
	}
	*/
	
	@Test
	@Disabled
	@Transactional
	@Rollback(false)
	void findAllCartbyUserIdTest() {
		List<Cart> carts = cartDao.findAllCartByUserId(3L);
		System.out.println(carts);
	}
	
	@Test
	//@Disabled
	@Transactional
	@Rollback(false)
	void countProductByUserIdTest() {
		Integer count = cartDao.countProductByUserId(2L, 3L);
		System.out.println(count);
	}
	
	/*
	@Test
	@Disabled
	@Transactional
	@Rollback(false)
	// 카트에 중복제품이 있으면 (중복체크) --> 업데이트 돼서 담기도록 
	void updateOverlapCart() {
		Cart count = cartDao.updateOverlapCart(null);
	}
	*/
}
