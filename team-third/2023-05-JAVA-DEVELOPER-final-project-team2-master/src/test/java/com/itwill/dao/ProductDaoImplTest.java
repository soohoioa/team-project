package com.itwill.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.TeamprojectAnimalcareApplication;
import com.itwill.TeamprojectAnimalcareApplicationTest;

import com.itwill.entity.Product;
import com.itwill.repository.ProductRepository;

class ProductDaoImplTest extends TeamprojectAnimalcareApplicationTest {

	@Autowired
	ProductDao productDao;
	
	
	@Test
	@Disabled
	void insertProductTest() {
		Product product1 = Product.builder()
				.productName("츄르_멸치맛")
				.productPrice(3000)
				.productCategory("간식")
				.productImage("cat.jpg")
				//.productStarAvg(3)
				.productQty(2)
				.build();
		Product savedProduct1 = productDao.insertProduct(product1);
		System.out.println(savedProduct1);
	}
	
	@Test
	@Disabled
	void findByProductNo() {
		Product findProduct = productDao.findByProductNo(2L);
		System.out.println(findProduct);
	}
	
	@Test
	@Disabled
	@Transactional
	@Rollback(value = false)
	void updateProduct() {
		Product findProduct = productDao.findByProductNo(1L);
		findProduct.setProductName("츄르_닭고기맛");
		System.out.println(findProduct);
	}
	
	@Test
	@Disabled
	@Transactional
	@Rollback(value = false)
	void deleteProduct() throws Exception {
		productDao.deleteProduct(4L);
	}
	
	@Test
	@Disabled
	// 일부 단어 입력으로 제품 검색
	void findByContainsTest() {
		List<Product> findProduct = productDao.findByContains("닭");
		System.out.println(findProduct);
	}
	
	@Test
	//@Disabled
	void findAllProductByCategory() {
		List<Product> find = productDao.findAllProductByCategory("간식", "강아지");
		System.out.println(find);
	}
	
	@Test
	@Disabled
	// 높은 가격순 정렬
	void findAllByOrderByProductPriceDesc() {
		List<Product> products = productDao.findAllByOrderByProductPriceDesc();
		System.out.println(products);
	}
	
	@Test
	@Disabled
	// 낮은 가격순 정렬
	void findAllByOrderByProductPriceAsc() {
		List<Product> products = productDao.findAllByOrderByProductPriceAsc();
		System.out.println(products);
	}
	
	@Test
	@Disabled
	// 평점높은순 정렬
	void findAllByOrderByProductStarAvgDesc() {
		List<Product> products = productDao.findAllByOrderByProductStarAvgDesc();
		System.out.println(products);
	}
	
	@Test
	@Disabled
	// 최신번호순 정렬
	void findAllByOrderByProductNoDesc() {
		List<Product> products = productDao.findAllByOrderByProductNoDesc();
		System.out.println(products);
	}
	
	@Test
	@Disabled
	// 낮은번호순 정렬
	void findAllByOrderByProductNoAsc() {
		List<Product> products = productDao.findAllByOrderByProductNoAsc();
		System.out.println(products);
	}

}
