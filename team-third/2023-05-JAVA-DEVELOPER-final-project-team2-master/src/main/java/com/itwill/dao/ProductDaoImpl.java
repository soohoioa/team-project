package com.itwill.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.itwill.entity.Product;
import com.itwill.repository.ProductRepository;
import com.itwill.repository.ReportBoardRepository;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product insertProduct(Product product) {
		Product savedProduct = productRepository.save(product);
		return savedProduct;
	}

	// 관리자 ~
	@Override
	public Product updateProduct(Product updateProduct) throws Exception {
		return productRepository.save(updateProduct);
	}

	@Override
	public Product findByProductNo(Long no) {
		return productRepository.findById(no).get();
	}
	
	@Override
	public void deleteProduct(Long no) throws Exception {
		productRepository.deleteById(no);
	}

	// 일부 단어 입력으로 제품 검색
	@Override
	public List<Product> findByContains(String productName) { 
		return productRepository.findByContains(productName);
	}

	@Override
	// 선택된 상품의 카테고리와 펫카테고리가 일치하는 모든 상품 출력
	public List<Product> findAllProductByCategory(String productCategory, String productPetCategory) {
		return productRepository.findAllProductByCategory(productCategory, productPetCategory);
	}

	@Override
	public List<Product> findAllProductByPetCategory(String productPetCategory) {
		return productRepository.findAllProductByPetCategory(productPetCategory);
	}
	
	// 높은 가격순 정렬
	@Override
	public List<Product> findAllByOrderByProductPriceDesc() {
		return productRepository.findAllByOrderByProductPriceDesc();
	}

	// 낮은 가격순 정렬
	@Override
	public List<Product> findAllByOrderByProductPriceAsc () {
		return productRepository.findAllByOrderByProductPriceAsc();
	}

	// 평점높은순 정렬
	@Override
	public List<Product> findAllByOrderByProductStarAvgDesc() {
		return productRepository.findAllByOrderByProductStarAvgDesc();
	}

	// 최신번호순 정렬
	@Override
	public List<Product> findAllByOrderByProductNoDesc() {
		return productRepository.findAllByOrderByProductNoDesc();
	}

	// 낮은번호순 정렬
	@Override
	public List<Product> findAllByOrderByProductNoAsc() {
		return productRepository.findAllByOrderByProductNoAsc();
	}

	
	/*************** 펫 카테고리별 정렬 ****************/
	@Override
	public List<Product> findAllByOrderByProductByPetCategoryPriceDesc(String productPetCategory) {
		return productRepository.findAllByOrderByProductByPetCategoryPriceDesc(productPetCategory);
	}

	@Override
	public List<Product> findAllByOrderByProductByPetCategoryPriceAsc(String productPetCategory) {
		return productRepository.findAllByOrderByProductByPetCategoryPriceAsc(productPetCategory);
	}

	@Override
	public List<Product> findAllByOrderByProductByPetCategoryStarAvgDesc(String productPetCategory) {
		return productRepository.findAllByOrderByProductByPetCategoryStarAvgDesc(productPetCategory);
	}

	@Override
	public List<Product> findAllByOrderByProductByPetCategoryNoDesc(String productPetCategory) {
		return productRepository.findAllByOrderByProductByPetCategoryNoDesc(productPetCategory);
	}

	
	/*************** 펫 카테고리 및 프로덕트별 정렬 ****************/
	@Override
	public List<Product> findAllByOrderByProductByPetCategoryByProductCategoryPriceDesc(String productPetCategory,
			String productCategory) {
		return productRepository.findAllByOrderByProductByPetCategoryByProductCategoryPriceDesc(productPetCategory, productCategory);
	}

	@Override
	public List<Product> findAllByOrderByProductByPetCategoryByProductCategoryPriceAsc(String productPetCategory,
			String productCategory) {
		return productRepository.findAllByOrderByProductByPetCategoryByProductCategoryPriceAsc(productPetCategory, productCategory);
	}

	@Override
	public List<Product> findAllByOrderByProductByPetCategoryByProductCategoryStarAvgDesc(String productPetCategory,
			String productCategory) {
		return productRepository.findAllByOrderByProductByPetCategoryByProductCategoryStarAvgDesc(productPetCategory, productCategory);
	}

	@Override
	public List<Product> findAllByOrderByProductByPetCategoryByProductCategoryNoDesc(String productPetCategory,
			String productCategory) {
		return productRepository.findAllByOrderByProductByPetCategoryByProductCategoryNoDesc(productPetCategory, productCategory);
	}

	
	/*************** 페이징 ***************/
	@Override
	public Page<Product> findProductList(Pageable pageable) throws Exception {
		return productRepository.findAll(pageable);
	}

	@Override
	public Page<Product> findProductList(Specification<Product> specification, Pageable pageable) throws Exception {
		return productRepository.findAll(specification, pageable);
				
	}

	/************** 페이징에 필요 ****************/
	@Override
	public Page<Product> findAllByProductPetCategory(String productPetCategory, Pageable pageable) {
		return productRepository.findAllByProductPetCategory(productPetCategory, pageable);
	}

	@Override
	public Page<Product> findAllByProductCategoryAndProductPetCategory(String productCategory, String productPetCategory,  Pageable pageable) {
		return productRepository.findAllByProductCategoryAndProductPetCategory(productPetCategory, productCategory, pageable);
	}

	@Override
	public boolean existsById(Long productNo) {
		// TODO Auto-generated method stub
		return productRepository.existsById(productNo);
	}

	@Override
	public Page<Product> productFindAllPage(Pageable pageable) {
		Page<Product> productList = productRepository.findAll(pageable);
		
		return productList;
	}

	@Override
	public List<Product> findByProductImage(String productImage) {
		
		return productRepository.findByProductImage(productImage);
	}

}
