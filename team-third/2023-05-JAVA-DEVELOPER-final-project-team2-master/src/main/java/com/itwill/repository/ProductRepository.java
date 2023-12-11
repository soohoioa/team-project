package com.itwill.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.itwill.entity.Product;
import com.itwill.entity.Userinfo;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
	
	// 일부 단어 입력으로 제품 검색
	@Query(value = "select * from product where product_name like '%'||?1||'%'", nativeQuery = true)
	List<Product> findByContains(String productName);
	
	// 선택된 상품의 카테고리와 펫카테고리가 일치하는 모든 상품 출력
	@Query(value = "select * from product where product_category = ?1 and product_pet_category = ?2", nativeQuery = true)
	List<Product> findAllProductByCategory(String productCategory, String productPetCategory);
	
	// 펫카테고리별로 상품 출력
	@Query(value = "select * from product where product_pet_category = ?1", nativeQuery = true)
	List<Product> findAllProductByPetCategory(String productPetCategory);
	
	// 높은 가격순 정렬
	//@Query(value = "select * from product order by product_price desc", nativeQuery = true)
	List<Product> findAllByOrderByProductPriceDesc();
	
	// 낮은 가격순 정렬
	List<Product> findAllByOrderByProductPriceAsc();
	
	// 평점높은순 정렬
	List<Product> findAllByOrderByProductStarAvgDesc();
	
	// 최신번호순 정렬
	List<Product> findAllByOrderByProductNoDesc();
	
	// 낮은번호순 정렬
	List<Product> findAllByOrderByProductNoAsc();
	
	/*************** 펫 카테고리별 정렬 ****************/
	// 높은 가격순 정렬
	@Query(value = "SELECT * FROM Product p WHERE p.product_pet_category = ?1 ORDER BY p.product_price DESC", nativeQuery = true)
	List<Product> findAllByOrderByProductByPetCategoryPriceDesc(String productPetCategory);
	
	// 낮은 가격순 정렬
	@Query(value = "SELECT * FROM Product p WHERE p.product_pet_category = ?1 ORDER BY p.product_price ASC", nativeQuery = true)
	List<Product> findAllByOrderByProductByPetCategoryPriceAsc(String productPetCategory);
	
	// 평점높은순 정렬
	@Query(value = "SELECT * FROM Product p WHERE p.product_pet_category = ?1 ORDER BY p.product_star_avg DESC", nativeQuery = true)
	List<Product> findAllByOrderByProductByPetCategoryStarAvgDesc(String productPetCategory);
	
	// 최신번호순 정렬
	@Query(value = "SELECT * FROM Product p WHERE p.product_pet_category = ?1 ORDER BY p.product_no DESC", nativeQuery = true)
	List<Product> findAllByOrderByProductByPetCategoryNoDesc(String productPetCategory);
	
	/*************** 펫 카테고리 및 프로덕트별 정렬 ****************/
	@Query(value = "select * from product where product_pet_category = ?1 and product_category = ?2 order by product_price desc", nativeQuery = true)
	List<Product> findAllByOrderByProductByPetCategoryByProductCategoryPriceDesc(String productPetCategory, String productCategory);
	
	@Query(value = "select * from product where product_pet_category = ?1 and product_category = ?2 order by product_price asc", nativeQuery = true)
	List<Product> findAllByOrderByProductByPetCategoryByProductCategoryPriceAsc(String productPetCategory, String productCategory);
	
	@Query(value = "select * from product where product_pet_category = ?1 and product_category = ?2 order by product_star_avg desc", nativeQuery = true)
	List<Product> findAllByOrderByProductByPetCategoryByProductCategoryStarAvgDesc(String productPetCategory, String productCategory);
	
	@Query(value = "select * from product where product_pet_category = ?1 and product_category = ?2 order by product_no desc", nativeQuery = true)
	List<Product> findAllByOrderByProductByPetCategoryByProductCategoryNoDesc(String productPetCategory, String productCategory);
	
	/************** 페이징에 필요 ****************/
	// 펫 카테고리가 일치하는 모든 상품 출력(query 사용 X)
	Page<Product> findAllByProductPetCategory(String productPetCategory, Pageable pageable);
	
	// 상품의 카테고리와 펫 카테고리가 일치하는 모든 상품 출력(query 사용 X)
	Page<Product> findAllByProductCategoryAndProductPetCategory(String productCategory, String productPetCategory,  Pageable pageable);
	
	@Query(value="select * from product where product_image=?1",nativeQuery = true)
	public List<Product> findByProductImage(String productImage);
}
