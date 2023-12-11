package com.itwill.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.dto.ProductListDto;
import com.itwill.dto.ProductNameDto;
import com.itwill.dto.WishlistInsertDto;
import com.itwill.entity.MyPet;
import com.itwill.entity.Product;
import com.itwill.entity.Userinfo;
import com.itwill.entity.Wish;
import com.itwill.service.MyPetService;
import com.itwill.service.ProductService;
import com.itwill.service.UserInfoService;
import com.itwill.service.WishService;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

@Controller
public class WishController {

	@Autowired
	private WishService wishService;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserInfoService userinfoService;
	@Autowired
	private MyPetService myPetService;
	
	@GetMapping("/wishList")
	// 위시리스트
	public String Wishlist(HttpSession session, Model model) throws Exception{
		if (session.getAttribute("userNo") == null) {
			throw new Exception("로그인 하세요.");
		}
		
		Long userNo=(Long)session.getAttribute("userNo");
		
		List<Wish> wishlist = wishService.findAllWishByUserNo(userNo);
		/*
		  List<WishlistInsertDto> wishlistDto = new ArrayList<WishlistInsertDto>();
		  
		  for (Wish wish : wishlist) { wishlistDto.add(WishlistInsertDto.toDto(wish));
		  }
		 */
		model.addAttribute("wishList", wishlist);
		
		return "wishlist";
	}

	/*
	@GetMapping("/wishList/{userNo}")
	// 위시리스트
	public String Wishlist(Model model, @PathVariable(name = "userNo") Long userNo) {
		List<WishlistInsertDto> wishlistDto = new ArrayList<>();
		List<Wish> wishlist = wishService.findAllWishByUserNo(userNo);
		
		for (Wish wish : wishlist) {
			wishlistDto.add(WishlistInsertDto.toDto(wish));
		}
		model.addAttribute("wishlist", wishlistDto);
		
		return "wishlist";
	}
	*/
	
	
	
	@GetMapping(value = "/insertWish")
	// 상품 디테일에서 위시리스트 추가
	public String insertWish(Model model, HttpSession session, @RequestParam Long productNo) throws Exception{
		if (session.getAttribute("userNo") == null) {
			throw new Exception("로그인 하세요.");
		}
		
		// session에서 userNo가져오기
		Long userNo = (Long) session.getAttribute("userNo");
		// userNo로 user 찾기
		Userinfo user = userinfoService.findUserByNo(userNo);
		// productNo로 product 정보 가져오기
		Product product = productService.findByProductNo(productNo);
		
		Wish insertWish = Wish.builder().build();
		insertWish.setProduct(product);
		insertWish.setUserinfo(user);
		
		wishService.insertWish(insertWish);
		
		model.addAttribute("wish", insertWish);
		
		product = productService.findByProductNo(productNo);
		String findProductName = productService.findByProductNo(productNo).getProductName();
		int firstSpaceIndex = findProductName.indexOf(" ");
		
		
		if (firstSpaceIndex >= 0) {
			findProductName = findProductName.substring(0, firstSpaceIndex);// 첫 번째 공백까지 잘라내기
		}
		List<Product> productNameList = productService.findByContains(findProductName);

		List<ProductListDto> productListDto = new ArrayList<>();
		List<ProductNameDto> productNameDto = new ArrayList<>();
		List<Product> products = productService.findAllProductByCategory(product.getProductCategory(), product.getProductPetCategory());
		
		for (Product productCategory : products) {
			productListDto.add(ProductListDto.toDto(productCategory));
		}

		for (Product productName : productNameList) {
			productNameDto.add(ProductNameDto.toDto(productName));
		}
		int wishCount = wishService.findAllWishByUserNo(userNo).size();
		Wish wishProductCount = wishService.findByUserNoProductNo(userNo, productNo);
		
		session.setAttribute("wishCount", wishCount);
		session.setAttribute("wishProductCount", wishProductCount);
		
		model.addAttribute("product", product);
		model.addAttribute("products", productListDto);
		model.addAttribute("productName", productNameDto);
		
		
		return "product-details";
	}
	
	
	
	
	@GetMapping(value = "/insertWishMain")
	// 상품 디테일에서 위시리스트 추가
	public String insertWishMain(Model model, HttpSession session, @RequestParam Long productNo) throws Exception{
		if (session.getAttribute("userNo") == null) {
			throw new Exception("로그인 하세요.");
		}
		
		// session에서 userNo가져오기
		Long userNo = (Long) session.getAttribute("userNo");
		// userNo로 user 찾기
		Userinfo user = userinfoService.findUserByNo(userNo);
		// productNo로 product 정보 가져오기
		Product product = productService.findByProductNo(productNo);
		
		Wish insertWish = Wish.builder().build();
		insertWish.setProduct(product);
		insertWish.setUserinfo(user);
		
		wishService.insertWish(insertWish);
		
		model.addAttribute("wish", insertWish);
	
		List<ProductListDto> productListDto = new ArrayList<>();
		List<Product> productList = new ArrayList<>();
		
		MyPet myPet = MyPet.builder().build();
		
		productList = productService.findAllByOrderByProductNoDesc();
		
		if(userNo != null) {
			myPet = myPetService.findLeaderMyPet(userNo);
			if (myPet == null) {
				myPet = MyPet.builder().build();
			} else {
				productList = productService.findAllProductByPetCategory(myPet.getMypetKind());
			}
		}
		
		for (Product products : productList) {
			productListDto.add(ProductListDto.toDto(products));
		}
		int wishCount = wishService.findAllWishByUserNo(userNo).size();
		session.setAttribute("wishCount", wishCount);
		model.addAttribute("productList", productListDto);
		model.addAttribute("myPet", myPet);
		
		
		return "shop";
	}
	
	
	
	
	
	
	
}
