package com.itwill.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.TeamprojectAnimalcareApplicationTest;
import com.itwill.entity.Product;
import com.itwill.repository.ProductRepository;

class ProductServiceImplTest extends TeamprojectAnimalcareApplicationTest {

	@Autowired
	ProductService productService;
	
	@Test
	@Disabled
	void insertProductTest() {
		Product product1 = Product.builder()
				.productName("츄르_육포맛")
				.productPrice(3000)
				.productCategory("간식")
				.productImage("cat.jpg")
				//.productStarAvg(3)
				.productQty(2)
				.build();
		Product savedProduct1 = productService.insertProduct(product1);
		System.out.println(savedProduct1);
	}
	
	@Test
	@Disabled
	void findByProductNo() {
		Product findProduct = productService.findByProductNo(2L);
		System.out.println(findProduct);
	}
	
	@Test
	@Disabled
	@Transactional
	@Rollback(value = false)
	void updateProduct() {
		Product findProduct = productService.findByProductNo(1L);
		findProduct.setProductName("츄르_닭고기맛");
		System.out.println(findProduct);
	}
	
	@Test
	@Disabled
	@Transactional
	@Rollback(value = false)
	void deleteProduct() throws Exception {
		productService.deleteProduct(4L);
	}
	
	@Test
	@Disabled
	// 일부 단어 입력으로 제품 검색
	void findByContainsTest() {
		List<Product> findProduct = productService.findByContains("닭");
		System.out.println(findProduct);
	}
	
	@Test
	@Disabled
	void findAllProductByCategory() {
		List<Product> find = productService.findAllProductByCategory("간식", "고양이");
		System.out.println(find);
	}
	
	@Test
	@Disabled
	void findAllProductByPetCategory() {
		List<Product> find = productService.findAllProductByPetCategory("고양이");
		System.out.println(find);
	}
	
	@Test
	@Disabled
	// 높은 가격순 정렬
	void findAllByOrderByProductPriceDesc() {
		List<Product> products = productService.findAllByOrderByProductPriceDesc();
		System.out.println(products);
	}
	
	@Test
	@Disabled
	// 낮은 가격순 정렬
	void findAllByOrderByProductPriceAsc() {
		List<Product> products = productService.findAllByOrderByProductPriceAsc();
		System.out.println(products);
	}
	
	@Test
	@Disabled
	// 평점높은순 정렬
	void findAllByOrderByProductStarAvgDesc() {
		List<Product> products = productService.findAllByOrderByProductStarAvgDesc();
		System.out.println(products);
	}
	
	@Test
	@Disabled
	// 최신번호순 정렬
	void findAllByOrderByProductNoDesc() {
		List<Product> products = productService.findAllByOrderByProductNoDesc();
		System.out.println(products);
	}
	
	@Test
	@Disabled
	// 낮은번호순 정렬
	void findAllByOrderByProductNoAsc() {
		List<Product> products = productService.findAllByOrderByProductNoAsc();
		System.out.println(products);
	}
	
	/*************** 펫 카테고리별 정렬 ****************/
	@Test
	@Disabled
	// 높은 가격순 정렬
	void findAllByOrderByProductByPetCategoryPriceDesc() {
		List<Product> products = productService.findAllByOrderByProductByPetCategoryPriceDesc("고양이");
		System.out.println(products);
	}
	
	@Test
	@Disabled
	// 낮은 가격순 정렬
	void findAllByOrderByProductByPetCategoryPriceAsc() {
		List<Product> products = productService.findAllByOrderByProductByPetCategoryPriceAsc("고양이");
		System.out.println(products);
	}
	
	@Test
	@Disabled
	// 평점높은순 정렬
	void findAllByOrderByProductByPetCategoryStarAvgDesc() {
		List<Product> products = productService.findAllByOrderByProductByPetCategoryStarAvgDesc("고양이");
		System.out.println(products);
	}
	
	@Test
	@Disabled
	// 최신번호순 정렬
	void findAllByOrderByProductByPetCategoryNoDesc() {
		List<Product> products = productService.findAllByOrderByProductByPetCategoryNoDesc("고양이");
		System.out.println(products);
	}
	
	/*************** 펫 카테고리 및 프로덕트별 정렬 ****************/
	
	@Test
	@Disabled
	void findAllByOrderByProductByPetCategoryByProductCategoryPriceDesc() {
		List<Product> products = productService.findAllByOrderByProductByPetCategoryByProductCategoryPriceDesc("고양이", "사료");
		System.out.println(products);
	}
	
	@Test
	@Disabled
	void findAllByOrderByProductByPetCategoryByProductCategoryPriceAsc() {
		List<Product> products = productService.findAllByOrderByProductByPetCategoryByProductCategoryPriceAsc("고양이", "사료");
		System.out.println(products);
	}
	
	@Test
	@Disabled
	void findAllByOrderByProductByPetCategoryByProductCategoryStarAvgDesc() {
		List<Product> products = productService.findAllByOrderByProductByPetCategoryByProductCategoryStarAvgDesc("고양이", "사료");
		System.out.println(products);
	}
	
	@Test
	@Disabled
	void findAllByOrderByProductByPetCategoryByProductCategoryNoDesc() {
		List<Product> products = productService.findAllByOrderByProductByPetCategoryByProductCategoryNoDesc("고양이", "사료");
		System.out.println(products);
	}
	
	/************** 페이징에 필요 ****************/
	@Test
	@Disabled
	void findAllByProductPetCategory() {
		Pageable pageable = PageRequest.of(0, 10);
		
		Page<Product> products = productService.findAllByProductPetCategory("고양이", pageable);
		
		System.out.println(products.getContent());
	}
	
	@Test
	//@Disabled
	void findAllByProductPetCategoryAndProductCategory() {
		Pageable pageable = PageRequest.of(0, 5);
		
		Page<Product> products = productService.findAllByProductCategoryAndProductPetCategory("사료", "강아지", pageable);
		
		System.out.println(products.getContent());
	}
}
