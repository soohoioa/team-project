package com.itwill.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.dto.CartDto;
import com.itwill.dto.OrdersDto;
import com.itwill.dto.ProductListDto;
import com.itwill.dto.ProductNameDto;
import com.itwill.entity.Cart;
import com.itwill.entity.MyPet;
import com.itwill.entity.Product;
import com.itwill.entity.Userinfo;
import com.itwill.service.CartService;
import com.itwill.service.MyPetService;
import com.itwill.service.ProductService;
import com.itwill.service.UserInfoService;

import jakarta.servlet.http.HttpSession;
import oracle.net.aso.b;

@Controller
public class CartController {

	@Autowired
	private CartService cartService;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserInfoService userinfoService;
	@Autowired
	private MyPetService myPetService;
	
	@GetMapping("/cartList")
	// 카트 리스트 보기 (유저)
	public String cartList(Model model, HttpSession session) throws Exception{
			if (session.getAttribute("userNo") == null) {
				throw new Exception("로그인 하세요.");
			}
		
		Long userNo=(Long)session.getAttribute("userNo");
		
		//List<CartDto> cartListDto = new ArrayList<>();
		List<Cart> cartList = cartService.findAllCartByUserId(userNo);
		Integer totalPrice = 0;
		
		/*
		for (Cart cart : cartList) {
			cartListDto.add(CartDto.toDto(cart));
		}
		*/
		for (Cart cart : cartList) {
			totalPrice = totalPrice + cart.getProduct().getProductPrice() * cart.getCartQty();
		}
		
		model.addAttribute("cartList", cartList);
		model.addAttribute("totalPrice", totalPrice);
		
		return "cart";
	}
	
	/*
	@GetMapping(value = "/cart")
	// 상품디테일에서 카트에 담기
	public String insertCart(Model model, HttpSession session, @RequestParam Long productNo, @RequestParam Integer productQty) throws Exception{
		if (session.getAttribute("userNo") == null) {
			throw new Exception("로그인 하세요.");
		}
		
		// session에서 userNo가져오기
		Long userNo=(Long)session.getAttribute("userNo");
		// userNo로 user 찾기
		Userinfo user = userinfoService.findUserByNo(userNo);
		// productNo로 product 정보 가져오기
		Product product = productService.findByProductNo(productNo);
		
		Cart selectCart = Cart.builder().build();
		selectCart.setUserinfo(user);
		selectCart.setProduct(product);
		selectCart.setCartQty(productQty);
		//selectCart.setCartQty(product.getProductQty());
		
		cartService.updateOverlapCart(selectCart);
		
		//List<Cart> cartList = cartService.findAllCartByUserId(userNo); 
		int cartCount = cartService.findAllCartByUserId(userNo).size();
		session.setAttribute("cartCount", cartCount);
		model.addAttribute("cart", selectCart);
		
		//model.addAttribute("cartList", cartList);
		
		
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
		
		model.addAttribute("product", product);
		model.addAttribute("products", productListDto);
		model.addAttribute("productName", productNameDto);
		
		
		return "product-details";
	}
	*/
	
	
	@GetMapping(value = "/insertCartMain")
	// 상품리스트에서 카트에 담기
	public String insertCartMain(Model model, HttpSession session, @RequestParam Long productNo, @RequestParam Integer productQty) throws Exception{
		if (session.getAttribute("userNo") == null) {
			throw new Exception("로그인 하세요.");
		}
		
		// session에서 userNo가져오기
		Long userNo=(Long)session.getAttribute("userNo");
		// userNo로 user 찾기
		Userinfo user = userinfoService.findUserByNo(userNo);
		// productNo로 product 정보 가져오기
		Product product = productService.findByProductNo(productNo);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+productNo);
		
		Cart selectCart = Cart.builder().build();
		selectCart.setUserinfo(user);
		selectCart.setProduct(product);
		selectCart.setCartQty(productQty);
		//selectCart.setCartQty(product.getProductQty());
		
		cartService.updateOverlapCart(selectCart);
		
		//List<Cart> cartList = cartService.findAllCartByUserId(userNo); 
		
		
		model.addAttribute("cart", selectCart);
		
		List<ProductListDto> productListDto = new ArrayList<>();
		List<Product> productList = new ArrayList<>();
		
		MyPet myPet = MyPet.builder().build();
		
		productList = productService.findAllByOrderByProductNoDesc();
		
		if(userNo != null) {
			myPet = myPetService.findLeaderMyPet(userNo);
			if (myPet == null) {
				myPet = MyPet.builder().build();
			} else if (product.getProductPetCategory().equals("강아지")) {
				myPet.setMypetKind("강아지");
				productList = productService.findAllProductByPetCategory("강아지");
			} else if (product.getProductPetCategory().equals("고양이")) {
				myPet.setMypetKind("고양이");
				productList = productService.findAllProductByPetCategory("고양이");
			}
		}
		
		for (Product products : productList) {
			productListDto.add(ProductListDto.toDto(products));
		}
		
		int cartCount = cartService.findAllCartByUserId(userNo).size();
		session.setAttribute("cartCount", cartCount);
		model.addAttribute("productList", productListDto);
		model.addAttribute("myPet", myPet);
		
		
		return "shop";
	}
	
	@PostMapping("/delete")
	public String delete(@RequestParam List<String> cartNo) throws Exception {
		
		for (int i = 0; i < cartNo.size(); i++) {
			Long no = Long.valueOf(cartNo.get(i));
			cartService.deleteById(no);
		}
		
		return "cart";
	}
	
	
	
	
	
	
	
	
	
	
	
}