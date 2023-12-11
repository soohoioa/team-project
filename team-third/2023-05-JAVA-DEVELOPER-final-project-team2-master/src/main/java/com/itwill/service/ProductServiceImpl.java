package com.itwill.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.dao.ProductDao;
import com.itwill.entity.Product;
import com.itwill.entity.ReportBoard;
import com.itwill.repository.ProductRepository;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public Product insertProduct(Product product) {
		return productDao.insertProduct(product);
	}

	@Override
	public Product updateProduct(Product updateProduct) throws Exception {
		Product findProduct = productDao.findByProductNo(updateProduct.getProductNo());
		if (findProduct!=null) {
			findProduct.setProductName(updateProduct.getProductName());
			findProduct.setProductPrice(updateProduct.getProductPrice());
			findProduct.setProductImage(updateProduct.getProductImage());
			productDao.updateProduct(findProduct);
		} else {
			throw new Exception("존재하지 않는 제품입니다.");
		}
		return findProduct;
	}

	@Override
	public Product findByProductNo(Long no) {
		Product selectedProduct = productDao.findByProductNo(no);
		return selectedProduct;
	}
	
	@Override
	public void deleteProduct(Long no) throws Exception {
		Product findProduct = productDao.findByProductNo(no);
		if (findProduct == null) {
			throw new Exception("존재하지 않는 제품입니다.");
		}
		productDao.deleteProduct(no);
	}

	// 일부 단어 입력으로 제품 검색
	@Override
	public List<Product> findByContains(String productName) { 
		return productDao.findByContains(productName);
	}

	@Override
	// 선택된 상품의 카테고리와 펫카테고리가 일치하는 모든 상품 출력
	public List<Product> findAllProductByCategory(String productCategory, String productPetCategory) {
		return productDao.findAllProductByCategory(productCategory, productPetCategory);
	}
	

	@Override
	// 펫카테고리별로 상품 출력
	public List<Product> findAllProductByPetCategory(String productPetCategory) {
		return productDao.findAllProductByPetCategory(productPetCategory);
	}
	
	// 높은 가격순 정렬
	@Override
	public List<Product> findAllByOrderByProductPriceDesc() {
		return productDao.findAllByOrderByProductPriceDesc();
	}

	// 낮은 가격순 정렬
	@Override
	public List<Product> findAllByOrderByProductPriceAsc () {
		return productDao.findAllByOrderByProductPriceAsc();
	}

	// 평점높은순 정렬
	@Override
	public List<Product> findAllByOrderByProductStarAvgDesc() {
		return productDao.findAllByOrderByProductStarAvgDesc();
	}

	// 최신번호순 정렬
	@Override
	public List<Product> findAllByOrderByProductNoDesc() {
		return productDao.findAllByOrderByProductNoDesc();
	}

	// 낮은번호순 정렬
	@Override
	public List<Product> findAllByOrderByProductNoAsc() {
		return productDao.findAllByOrderByProductNoAsc();
	}


	/*************** 펫 카테고리별 정렬 ****************/
	@Override
	public List<Product> findAllByOrderByProductByPetCategoryPriceDesc(String productPetCategory) {
		return productDao.findAllByOrderByProductByPetCategoryPriceDesc(productPetCategory);
	}

	@Override
	public List<Product> findAllByOrderByProductByPetCategoryPriceAsc(String productPetCategory) {
		return productDao.findAllByOrderByProductByPetCategoryPriceAsc(productPetCategory);
	}

	@Override
	public List<Product> findAllByOrderByProductByPetCategoryStarAvgDesc(String productPetCategory) {
		return productDao.findAllByOrderByProductByPetCategoryStarAvgDesc(productPetCategory);
	}

	@Override
	public List<Product> findAllByOrderByProductByPetCategoryNoDesc(String productPetCategory) {
		return productDao.findAllByOrderByProductByPetCategoryNoDesc(productPetCategory);
	}

	
	/*************** 펫 카테고리 및 프로덕트별 정렬 ****************/
	@Override
	public List<Product> findAllByOrderByProductByPetCategoryByProductCategoryPriceDesc(String productPetCategory,
			String productCategory) {
		return productDao.findAllByOrderByProductByPetCategoryByProductCategoryPriceDesc(productPetCategory, productCategory);
	}

	@Override
	public List<Product> findAllByOrderByProductByPetCategoryByProductCategoryPriceAsc(String productPetCategory,
			String productCategory) {
		return productDao.findAllByOrderByProductByPetCategoryByProductCategoryPriceAsc(productPetCategory, productCategory);
	}

	@Override
	public List<Product> findAllByOrderByProductByPetCategoryByProductCategoryStarAvgDesc(String productPetCategory,
			String productCategory) {
		return productDao.findAllByOrderByProductByPetCategoryByProductCategoryStarAvgDesc(productPetCategory, productCategory);
	}

	@Override
	public List<Product> findAllByOrderByProductByPetCategoryByProductCategoryNoDesc(String productPetCategory,
			String productCategory) {
		return productDao.findAllByOrderByProductByPetCategoryByProductCategoryNoDesc(productPetCategory, productCategory);
	}

	
	/**************** 페이징 **************/
	public Page<Product> findProductList(Pageable pageable) throws Exception {
		
		Page<Product> productListPage = productDao.findProductList(pageable);
		
		return productListPage;
	}

	
	/************** 페이징에 필요 ****************/
	@Override
	public Page<Product> findAllByProductPetCategory(String productPetCategory, Pageable pageable) {
		return productDao.findAllByProductPetCategory(productPetCategory, pageable);
	}

	@Override
	public Page<Product> findAllByProductCategoryAndProductPetCategory(String productCategory, String productPetCategory,  Pageable pageable) {
		return productDao.findAllByProductCategoryAndProductPetCategory(productPetCategory, productCategory, pageable);
	}

	@Override
	public boolean existsById(Long productNo) {
		// TODO Auto-generated method stub
		return productDao.existsById(productNo);
	}

	@Override
	public Page<Product> productFindAllPage(Pageable pageable) {
		Page<Product> productList = productDao.productFindAllPage(pageable);
		
		return productList;
	}

	@Override
	public List<Product> findByProductImage(String boardImage) {
		
		return productDao.findByProductImage(boardImage);
	}

	
}
