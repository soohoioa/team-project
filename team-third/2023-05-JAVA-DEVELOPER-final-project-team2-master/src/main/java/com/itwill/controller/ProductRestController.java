package com.itwill.controller;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.dto.ProductNameDto;
import com.itwill.dto.ProductInsertDto;
import com.itwill.dto.ProductListDto;
import com.itwill.dto.ProductResponseDto;
import com.itwill.dto.ProductListDto;
import com.itwill.dto.ProductUpdateDto;
import com.itwill.entity.Product;
import com.itwill.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/product")
public class ProductRestController {

	@Autowired
	private ProductService productService;
	
	@Operation(summary = "상품 추가 (관리자)")
	@GetMapping
	// insert
	public ResponseEntity<ProductInsertDto> insertProduct(ProductInsertDto dto, HttpSession session) throws Exception{
		
		productService.insertProduct(ProductInsertDto.toEntity(dto));
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		return new ResponseEntity<ProductInsertDto>(dto, httpHeaders, HttpStatus.CREATED);
	}
	
	
	@Operation(summary = "상품 삭제 (관리자)")
	@DeleteMapping("/{no}")
	// delete
	public ResponseEntity deleteProduct(@PathVariable(name = "no") Long no, HttpSession session) throws Exception{
	
		HttpHeaders httpHeaders = new HttpHeaders();
		
		productService.deleteProduct(no);
		return new ResponseEntity(httpHeaders, HttpStatus.OK);
	}
	
	
	@Operation(summary = "상품 수정 (관리자)")
	@PutMapping
	// update
	public ResponseEntity<ProductListDto> updateProduct(@RequestBody ProductListDto dto) throws Exception{
		Product findProduct = productService.findByProductNo(dto.getProductNo());
		
		 findProduct.setProductImage(dto.getProductImage());
		 findProduct.setProductName(dto.getProductName());
		 findProduct.setProductPrice(dto.getProductPrice());
		 productService.updateProduct(findProduct);	
		 
		 ProductListDto updatedDto = ProductListDto.toDto(findProduct);
		 
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		return new ResponseEntity<ProductListDto>(updatedDto, httpHeaders, HttpStatus.OK);
	}
	
	
	@Operation(summary = "상품 리스트(최신 번호순)")
	@GetMapping("/productNoDescList")
	// productNo 최신번호순
	public ResponseEntity<List<ProductListDto>> productNoDescList() {
		List<Product> products = productService.findAllByOrderByProductNoDesc();
		List<ProductListDto> productsDto = new ArrayList<ProductListDto>();
		
		for (Product product : products) {
			productsDto.add(ProductListDto.toDto(product));
		}
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		return new ResponseEntity<List<ProductListDto>>(productsDto, httpHeaders, HttpStatus.OK);
	}
	
	
	
	@Operation(summary = "상품 리스트(낮은 번호순)")
	@GetMapping("/productNoAscList")
	// productNo 낮은번호순
	public ResponseEntity<List<ProductListDto>> productNoAscList(){
		List<Product> products = productService.findAllByOrderByProductNoAsc();
		List<ProductListDto> productsDto = new ArrayList<ProductListDto>();
		
		for (Product product : products) {
			productsDto.add(ProductListDto.toDto(product));
		}
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		return new ResponseEntity<List<ProductListDto>>(productsDto, httpHeaders, HttpStatus.OK);
	}
	
	
	
	@Operation(summary = "상품 리스트(높은 가격순 정렬)")
	@GetMapping("/productPriceDescList")
	// productNo 높은 가격순
	public ResponseEntity<List<ProductListDto>> productPriceDescList(){
		List<Product> products = productService.findAllByOrderByProductPriceDesc();
		List<ProductListDto> productsDto = new ArrayList<ProductListDto>();
		
		for (Product product : products) {
			productsDto.add(ProductListDto.toDto(product));
		}
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		return new ResponseEntity<List<ProductListDto>>(productsDto, httpHeaders, HttpStatus.OK);
	}
	
	
	
	@Operation(summary = "상품 리스트(낮은 가격순 정렬)")
	@GetMapping("/productPriceAscList")
	// productNo 낮은 가격순
	public ResponseEntity<List<ProductListDto>> productPriceAscList(){
		List<Product> products = productService.findAllByOrderByProductPriceAsc();
		List<ProductListDto> productsDto = new ArrayList<ProductListDto>();
		
		for (Product product : products) {
			productsDto.add(ProductListDto.toDto(product));
		}
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		return new ResponseEntity<List<ProductListDto>>(productsDto, httpHeaders, HttpStatus.OK);
	}
	
	
	
	@Operation(summary = "상품 리스트(평점 높은순 정렬)")
	@GetMapping("/productStarAvgDescList")
	// productNo 평점 높은순
	public ResponseEntity<List<ProductListDto>> productStarAvgDescList(){
		List<Product> products = productService.findAllByOrderByProductStarAvgDesc();
		List<ProductListDto> productsDto = new ArrayList<ProductListDto>();
		
		for (Product product : products) {
			productsDto.add(ProductListDto.toDto(product));
		}
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		return new ResponseEntity<List<ProductListDto>>(productsDto, httpHeaders, HttpStatus.OK);
	}
	
	
	@Operation(summary = "상품 전체 리스트")
	@GetMapping("/products")
	public ResponseEntity<List<ProductListDto>> findAllByOrderByProductNoDesc(){
		List<Product> productList = productService.findAllByOrderByProductNoDesc();
		List<ProductListDto> productDtoList = new ArrayList<>();
		
		for (Product product : productList) {
			ProductListDto productDto = ProductListDto.toDto(product);
			productDtoList.add(productDto);
		}
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		return new ResponseEntity<>(productDtoList, httpHeaders, HttpStatus.OK);
	}
	
	
	@Operation(summary = "상품 검색")
	@PostMapping("/products/search")
	public ResponseEntity<List<ProductListDto>> search(@RequestBody ProductListDto productdto){
		List<ProductListDto> productListDto = new ArrayList<ProductListDto>();
		List<Product> findList = productService.findByContains(productdto.getProductName());
		
		for (Product product : findList) {
			ProductListDto productDto = ProductListDto.toDto(product);
			productListDto.add(productDto);
		}
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		return new ResponseEntity<List<ProductListDto>>(productListDto, httpHeaders, HttpStatus.OK);
	}
	
	
	
	@Operation(summary = "선택된 상품의 카테고리와 펫카테고리가 일치하는 모든 상품 출력")
	@PostMapping("/products/categorySearch")
	public ResponseEntity<List<ProductListDto>> categorySearch(@RequestBody ProductListDto dto) {
		List<ProductListDto> productListDto = new ArrayList<>();
		List<Product> findList = productService.findAllProductByCategory(dto.getProductCategory(), dto.getProductPetCategory());
		
		for (Product product : findList) {
			ProductListDto productDto = ProductListDto.toDto(product);
			productListDto.add(productDto);
		}
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		return new ResponseEntity<List<ProductListDto>>(productListDto, httpHeaders, HttpStatus.OK);
	}
	
	
	
	@Operation(summary = "펫카테고리별로 상품 출력")
	@PostMapping("/products/petCategoryFind")
	public ResponseEntity<List<ProductListDto>> petCategoryFind(@RequestBody ProductListDto dto){
		List<ProductListDto> productListDto = new ArrayList<>();
		List<Product> findList = productService.findAllProductByPetCategory(dto.getProductPetCategory());
		
		for (Product product : findList) {
			ProductListDto productDto = ProductListDto.toDto(product);
			productListDto.add(productDto);
		}
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		return new ResponseEntity<List<ProductListDto>>(productListDto, httpHeaders, HttpStatus.OK);
	}
	
	@PostMapping("/detail")
	public ResponseEntity<ProductUpdateDto> ProductDetail(@RequestBody ProductUpdateDto productUpdateDto) {
		
		Product product = productService.findByProductNo(productUpdateDto.getProductNo());
		ProductUpdateDto dto = ProductUpdateDto.toDto(product);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		return new ResponseEntity<ProductUpdateDto>(dto, httpHeaders, HttpStatus.OK);
	}
	
	
}



