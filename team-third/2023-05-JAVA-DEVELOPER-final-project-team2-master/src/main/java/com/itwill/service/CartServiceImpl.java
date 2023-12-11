package com.itwill.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.dao.CartDao;
import com.itwill.dao.UserInfoDao;
import com.itwill.entity.Cart;
import com.itwill.entity.Userinfo;
import com.itwill.repository.CartRepository;

@Transactional
@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private UserInfoDao userinfoDao;
	@Autowired
	private CartDao cartDao;
	@Autowired
	private CartRepository cartRepository;

	@Override
	public Cart insertCart(Cart cart) {
		Cart savedCart = cartDao.insertCart(cart);
		return savedCart;
	}

	@Override
	public Cart update_qty(Cart updateQty) throws Exception {
		Optional<Cart> findCartOptional = cartRepository.findById(updateQty.getCartNo());
		Cart updatedCart = null;
		if(findCartOptional.isPresent()) {
			updatedCart = findCartOptional.get();
			updatedCart.setCartQty(updateQty.getCartQty());
			cartDao.update_qty(updatedCart);
		} else {
			throw new Exception("존재하지 않습니다.");
		}
		return updatedCart;
	}
	
	@Override
	public Cart findByCartNo(Long no) {
		Cart findCart = cartRepository.findById(no).get();
		return findCart;
	}

	@Override
	public void deleteByUserId(Long no) {
		cartDao.deleteByUserId(no);
	}

	@Override
	public void deleteById(Long no) throws Exception {
		cartDao.deleteById(no);
	}

	@Override
	public Integer cartTotalPrice(Long userNo) {
		List<Cart> cartList = cartDao.findAllCartByUserId(userNo);
		Integer total = 0;
		for (Cart cart : cartList) {
			total = total + cart.getProduct().getProductPrice() * cart.getCartQty();
		}
		return total;
		
	} 

	@Override
	public List<Cart> findAll() {
		List<Cart> carts = cartDao.findAll();
		return carts;
	}

	@Override
	public List<Cart> findAllCartByUserId(Long userNo) {
		List<Cart> carts = cartDao.findAllCartByUserId(userNo);
		return carts;
	}

	
	@Override
	// 카트 중복체크
	public Integer countProductByUserId(Long userNo, Long productNo) {
		return cartDao.countProductByUserId(userNo, productNo);
	}
	
	@Override
	// 중복된 상품이 있는 카트 정보 출력
	public Cart findByProductUserNo(Long userNo, Long productNo) {
		Cart cart = cartDao.findByProductUserNo(userNo, productNo);
		return cart;
	}

	@Override
	// 카트에 중복제품이 있으면 (중복체크) --> 업데이트 돼서 담기도록 
	public Cart updateOverlapCart(Cart cart) {
		
		int count = cartDao.countProductByUserId(cart.getUserinfo().getUserNo(), cart.getProduct().getProductNo());
		Cart overlapCount = null;
		if(count > 0) {
			Cart updateCart = cartDao.findByProductUserNo(cart.getUserinfo().getUserNo(), cart.getProduct().getProductNo());
			int qty = cart.getCartQty();
			int updateQty = updateCart.getCartQty() + qty;
			updateCart.setCartQty(updateQty);
			overlapCount = cartRepository.save(updateCart);
		} else {
			overlapCount = cartRepository.save(cart);
		}
		return overlapCount;
	}

	
}
