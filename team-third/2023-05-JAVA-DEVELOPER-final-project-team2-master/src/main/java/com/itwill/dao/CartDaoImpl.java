package com.itwill.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwill.entity.Cart;
import com.itwill.repository.CartRepository;

@Repository
public class CartDaoImpl implements CartDao{

	@Autowired
	private CartRepository cartRepository;
	
	@Override
	public Cart insertCart(Cart cart) {
		Cart savedCart = cartRepository.save(cart);
		return savedCart;
	}

	
	@Override
	public Cart update_qty(Cart updateQty) throws Exception {
		return cartRepository.save(updateQty);
	}
	
	
	@Override
	public Cart findByCartNo(Long no) {
		return cartRepository.findById(no).get();
	}


	@Override
	// 카트에 담긴 상품 전체삭제
	public void deleteByUserId(Long no)  {
		cartRepository.deleteByUserNo(no);
	}


	@Override
	// 카트에서 선택한 상품만 삭제
	public void deleteById(Long no) throws Exception {
		cartRepository.deleteById(no);
	}

	
	@Override
	public List<Cart> findAll() {
		return cartRepository.findAll();
	}


	@Override
	// 카트에 담긴 모든 상품 출력 (아이디 별)
	public List<Cart> findAllCartByUserId(Long userNo) {
		return cartRepository.findAllCartByUserNo(userNo);
	}

/*
	@Override
	// 카트에 담긴 모든 상품 합계 금액
	public Integer cartTotalPrice(Long userNo) {
		List<Cart> cartList = cartRepository.findAllCartByUserId(userNo);
		Integer total = 0;
		for (Cart cart : cartList) {
			total = total + cart.getProduct().getProductPrice()*cart.getCartQty();
		}
		return total;
	}
*/
	
	@Override
	// 카트 중복체크
	public Integer countProductByUserId(Long userNo, Long productNo) {
		return cartRepository.countProductByUserNo(userNo, productNo);
	}


	@Override
	// 카트에 중복제품이 있으면 (중복체크) --> 업데이트 돼서 담기도록 
	public Cart updateOverlapCart(Cart overlapCart) {
		int count = cartRepository.countProductByUserNo(overlapCart.getUserinfo().getUserNo(), overlapCart.getProduct().getProductNo());
		Cart overlapCount = null;
		if(count > 0) {
			overlapCount = cartRepository.save(overlapCart);
		} else {
			overlapCount = cartRepository.save(overlapCart);
		}
		return overlapCount;
	}


	@Override
	public Cart findByProductUserNo(Long userNo, Long productNo) {
		Cart cart = cartRepository.findByProductUserNo(userNo, productNo);
		return cart;
	}
	
}
