package com.itwill.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.itwill.dto.ProductCatListDto;
import com.itwill.dto.ProductDogListDto;
import com.itwill.dto.ProductInsertDto;
import com.itwill.dto.ProductListDto;
import com.itwill.dto.ProductListPageDto;
import com.itwill.dto.ProductNameDto;
import com.itwill.dto.ProductPetCategoryDto;
import com.itwill.dto.ProductPriceAscDto;
import com.itwill.dto.ProductPriceDescDto;
import com.itwill.dto.ProductProductNoDescDto;
import com.itwill.entity.MyPet;
import com.itwill.entity.Product;
import com.itwill.entity.ReviewBoard;
import com.itwill.service.MyPetService;
import com.itwill.service.ProductService;
import com.itwill.service.ReviewBoardService;
import com.itwill.service.UserInfoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired 
	private MyPetService myPetService;
	@Autowired
	private ReviewBoardService reviewBoardService;
	
	/*
	// 상품 등록
	public String insertProduct(ProductInsertDto dto) {
		
		productService.insertProduct(ProductInsertDto.toEntity(dto));
		
		return "redirect:shop.html";
	}
	*/
	
	
	@GetMapping("/searchProduct")
	public String searchProduct() {
		
		productService.findByContains(null);
		
		return "shop";
	}
	
	
	@GetMapping("/productInsertForm")
    public String productInsertForm() {
        return "product_insert_form"; 
    }
	
	/*
	@PostMapping("/insertProduct")
	// 상품등록 (관리자)
	public String insertProduct(@RequestParam("imageFile1") MultipartFile file1, @RequestParam("imageFile2") MultipartFile file2, @RequestParam("productName") String productName, 
								@RequestParam("productPrice") Integer productPrice, @RequestParam("productCategory") String productCategory, @RequestParam("productPetCategory") String productPetCategory) throws Exception {
		
		String uploadPath1 = System.getProperty("user.dir") + "/src/main/resources/static/image/product/";
	    String originalFileName1 = file1.getOriginalFilename();
	    UUID uuid1 = UUID.randomUUID();
	    String savedFileName1 = uuid1.toString() + "_" + originalFileName1;

	    File newFile1 = new File(uploadPath1 + savedFileName1);
	    
	    file1.transferTo(newFile1);
	    
	    String uploadPath2 = System.getProperty("user.dir") + "/src/main/resources/static/image/product/";
	    String originalFileName2 = file2.getOriginalFilename();
	    UUID uuid2 = UUID.randomUUID();
	    String savedFileName2 = uuid2.toString() + "_" + originalFileName2;
	    
	    File newFile2 = new File(uploadPath2 + savedFileName2);
	    
	    file2.transferTo(newFile2);
	   
		Product createProduct = Product.builder()
									.productName(productName)
									.productPrice(productPrice)
									.productCategory(productCategory)
									.productPetCategory(productPetCategory)
									.productImage(savedFileName1)
									.productDetailImage(savedFileName2)
									.build();
	    
	    productService.insertProduct(createProduct);
		
		return "shop";
	}
	*/
	
	@GetMapping("/updateProduct")
	// 상품 업데이트 (관리자)
	public String updateProduct(@RequestBody ProductListDto dto, Model model) throws Exception{
		Product product = Product.builder().build();
		
		product.setProductPrice(dto.getProductPrice());
		product.setProductImage(dto.getProductImage());
		product.setProductName(dto.getProductName());
		
		productService.updateProduct(product);
		
		model.addAttribute("product", product);
		
		return "shop";
	}
	
	
	// 펫카테고리별로 구분 --> 상품 리스트 출력
		@GetMapping("/productList")
		public String productList(Model model, HttpSession session) {
			List<ProductListDto> productListDto = new ArrayList<>();
			List<Product> productList = new ArrayList<>();
			
			Long userNo = (Long) session.getAttribute("userNo");
			MyPet myPet = MyPet.builder().build();
			
			productList = productService.findAllByOrderByProductNoDesc();
			
			if(userNo != null) {
				myPet = myPetService.findLeaderMyPet(userNo);
				if (myPet == null) {
					myPet = MyPet.builder().mypetKind("강아지").build();
				} else {
					productList = productService.findAllByOrderByProductByPetCategoryNoDesc(myPet.getMypetKind());
				}
			} else {
				myPet = MyPet.builder().mypetKind("강아지").build();
			}
			
			for (Product product : productList) {
				productListDto.add(ProductListDto.toDto(product));
				System.out.println(product.getProductStarAvg());
			}
			
			model.addAttribute("productList", productListDto);
			model.addAttribute("myPet", myPet);
			// System.out.println(productList.get(0).getProductPetCategory());
			return "shop";
		}
		
		/*
		@GetMapping("/productList")
		public String productList(@PageableDefault(page = 0, size = 9, sort = "productNo", direction = Sort.Direction.DESC) Pageable page, Model model, HttpSession session) {
			int pageNo = page.getPageNumber();
			int size = page.getPageSize();
			
			Pageable pageable = PageRequest.of(pageNo, size, Sort.by(Sort.Order.desc("productNo")));
			
			Page<Product> productList = productService.productFindAllPage(pageable);
			
			Long userNo = (Long) session.getAttribute("userNo");
			MyPet myPet = MyPet.builder().build();
			
			productList = productService.productFindAllPage(pageable);
			
			if(userNo != null) {
				myPet = myPetService.findLeaderMyPet(userNo);
				if (myPet == null) {
					myPet = MyPet.builder().mypetKind("강아지").build();
				} else {
					productList = productService.findAllByProductPetCategory(myPet.getMypetKind(), pageable);
				}
			} else {
				myPet = MyPet.builder().mypetKind("강아지").build();
			}
			
			 List<ProductListDto> productListDto = new ArrayList<>();
			
			for (Product product : productList) {
				productListDto.add(ProductListDto.toDto(product));
			}
			
			model.addAttribute("products", productList.getContent());
			model.addAttribute("productList", productList);
			model.addAttribute("myPet", myPet);
			// System.out.println(productList.get(0).getProductPetCategory());
			return "shop";
		}
		*/
		
		// 펫카테고리별로 구분 --> 상품 리스트 출력
	/*
		@GetMapping("/productList")
		public String productList(@PageableDefault(page = 1, size = 5) Pageable p, Model model, HttpSession session) throws Exception {
			List<ProductListDto> productListDto = new ArrayList<>();
			List<Product> productList = new ArrayList<>();
			
			Long userNo = (Long) session.getAttribute("userNo");
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
			
			for (Product product : productList) {
				productListDto.add(ProductListDto.toDto(product));
			}
			
			int page = p.getPageNumber();
			int size = p.getPageSize();
			int blockSize = 5;
			Pageable pageable = PageRequest.of(page - 1, size);
			
			Page<Product> productListPage = productService.findProductList(pageable);
			//ProductListPageDto productListPageDto = new ProductListPageDto(productListPage, blockSize);
			
			//model.addAttribute("productList", productListDto);
			model.addAttribute("productList", productListPage);
			model.addAttribute("myPet", myPet);
			// System.out.println(productList.get(0).getProductPetCategory());
			return "shop";
		}
		*/
		// 펫카테고리별로 구분 --> 상품 리스트 출력
		@GetMapping("/productDogList")
		public String productDogList(Model model, HttpSession session, @RequestParam String category) {
			List<ProductDogListDto> productDogListDto = new ArrayList<>();
			List<Product> productList = new ArrayList<>();
			
			Long userNo = (Long) session.getAttribute("userNo");
			MyPet myPet = MyPet.builder().build();
			productList = productService.findAllByOrderByProductByPetCategoryNoDesc("강아지");
			
			if(category.equals("All")) {
				productList = productService.findAllByOrderByProductByPetCategoryNoDesc("강아지");
			}
			if(category.equals("1")) {
				productList = productService.findAllProductByCategory("사료", "강아지");
			}
			if(category.equals("2")) {
				productList = productService.findAllProductByCategory("간식", "강아지");
			}
			if(category.equals("3")) {
				productList = productService.findAllProductByCategory("캔", "강아지");
			}
			if(category.equals("4")) {
				productList = productService.findAllProductByCategory("위생", "강아지");
			}
			if(category.equals("5")) {
				productList = productService.findAllProductByCategory("미용", "강아지");
			}
			
			myPet.setMypetKind("강아지");
			
			for (Product product : productList) {
				productDogListDto.add(ProductDogListDto.toDto(product));
			}
			
			model.addAttribute("productList", productDogListDto);
			model.addAttribute("myPet", myPet);
			// System.out.println(productList.get(0).getProductPetCategory());
			return "shop";
		}
		
		// 펫카테고리별로 구분 --> 상품 리스트 출력
		@GetMapping("/productCatList")
		public String productCatList(Model model, HttpSession session,@RequestParam String category) {
			List<ProductCatListDto> productCatListDto = new ArrayList<>();
			List<Product> productList = new ArrayList<>();
			
			Long userNo = (Long) session.getAttribute("userNo");
			MyPet myPet = MyPet.builder().build();
			
			productList = productService.findAllByOrderByProductByPetCategoryNoDesc("고양이");
			
			if(category.equals("All")) {
				productList = productService.findAllByOrderByProductByPetCategoryNoDesc("고양이");
			}
			if(category.equals("1")) {
				productList = productService.findAllProductByCategory("사료", "고양이");
			}
			if(category.equals("2")) {
				productList = productService.findAllProductByCategory("간식", "고양이");
			}
			if(category.equals("3")) {
				productList = productService.findAllProductByCategory("캔", "고양이");
			}
			if(category.equals("4")) {
				productList = productService.findAllProductByCategory("모래", "고양이");
			}
			if(category.equals("5")) {
				productList = productService.findAllProductByCategory("미용", "고양이");
			}
			
			myPet.setMypetKind("고양이");
			
			for (Product product : productList) {
				productCatListDto.add(ProductCatListDto.toDto(product));
			}
			
			model.addAttribute("productList", productCatListDto);
			model.addAttribute("myPet", myPet);
			// System.out.println(productList.get(0).getProductPetCategory());
			return "shop";
		}
	
	@GetMapping("/productPriceDesc")
	// 상품가격 비싼 것부터 --> user의 myPetKind 사용
	public String ProductPriceDesc(Model model, HttpSession session, @RequestParam String path) {
		List<ProductPriceDescDto> productPriceDescDto = new ArrayList<>();
		// 상품가격 비싼 것부터
		
		List<Product> productList = productService.findAllByOrderByProductPriceDesc();
		Long userNo = (Long) session.getAttribute("userNo");
		MyPet myPet = MyPet.builder().mypetKind("강아지").build();
		
		String kindPath = "";
		String categoryPath = "";
		
		if(path.equals("/productList")) {
			if(myPetService.findLeaderMyPet(userNo)!=null) {
				myPet.setMypetKind(myPetService.findLeaderMyPet(userNo).getMypetKind());	
			}else {
				myPet.setMypetKind("강아지");
			}
			productList = productService.findAllByOrderByProductByPetCategoryPriceDesc(myPet.getMypetKind());
		} else {
			kindPath = path.substring(1,path.lastIndexOf("?"));
			categoryPath = path.substring(path.lastIndexOf("=")+1);
		}
		
		if(kindPath.equals("productDogList")) {
			myPet.setMypetKind("강아지");
			productList = productService.findAllProductByPetCategory("강아지");
			if(categoryPath.equals("All")) {
				productList = productService.findAllByOrderByProductByPetCategoryPriceDesc("강아지");
			}
			if(categoryPath.equals("1")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryPriceDesc("강아지", "사료");
			}
			if(categoryPath.equals("2")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryPriceDesc("강아지", "간식");
			}
			if(categoryPath.equals("3")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryPriceDesc("강아지", "캔");
			}
			if(categoryPath.equals("4")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryPriceDesc("강아지", "위생");
			}
			if(categoryPath.equals("5")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryPriceDesc("강아지", "미용");
			}
		}
		
		if(kindPath.equals("productCatList")) {
			myPet.setMypetKind("고양이");
			productList = productService.findAllProductByPetCategory("고양이");
			if(categoryPath.equals("All")) {
				productList = productService.findAllByOrderByProductByPetCategoryPriceDesc("고양이");
			}
			if(categoryPath.equals("1")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryPriceDesc("고양이", "사료");
			}
			if(categoryPath.equals("2")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryPriceDesc("고양이", "간식");
			}
			if(categoryPath.equals("3")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryPriceDesc("고양이", "캔");
			}
			if(categoryPath.equals("4")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryPriceDesc("고양이", "모래");
			}
			if(categoryPath.equals("5")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryPriceDesc("고양이", "미용");
			}
		}
		
		for (Product product : productList) {
			productPriceDescDto.add(ProductPriceDescDto.toDto(product));
		}

		model.addAttribute("productList", productPriceDescDto);
		model.addAttribute("myPet", myPet);

		return "shop";
	}
	
	@GetMapping("/productPriceAsc")
	public String productPriceAsc(Model model, HttpSession session, @RequestParam String path) {
		List<ProductPriceAscDto> productPriceAscDto = new ArrayList<>();
		// 상품가격 싼 것부터
		List<Product> productList = productService.findAllByOrderByProductPriceAsc();
		Long userNo = (Long) session.getAttribute("userNo");
		MyPet myPet = MyPet.builder().build();

		String kindPath = "";
		String categoryPath = "";
		
		if(path.equals("/productList")) {
			if(myPetService.findLeaderMyPet(userNo)!=null) {
				myPet.setMypetKind(myPetService.findLeaderMyPet(userNo).getMypetKind());	
			}else {
				myPet.setMypetKind("강아지");
			}
			productList = productService.findAllByOrderByProductByPetCategoryPriceAsc(myPet.getMypetKind());
		} else {
			kindPath = path.substring(1,path.lastIndexOf("?"));
			categoryPath = path.substring(path.lastIndexOf("=")+1);
		}
		
		if(kindPath.equals("productDogList")) {
			myPet.setMypetKind("강아지");
			productList = productService.findAllProductByPetCategory("강아지");
			if(categoryPath.equals("All")) {
				productList = productService.findAllByOrderByProductByPetCategoryPriceAsc("강아지");
			}
			if(categoryPath.equals("1")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryPriceAsc("강아지", "사료");
			}
			if(categoryPath.equals("2")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryPriceAsc("강아지", "간식");
			}
			if(categoryPath.equals("3")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryPriceAsc("강아지", "캔");
			}
			if(categoryPath.equals("4")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryPriceAsc("강아지", "위생");
			}
			if(categoryPath.equals("5")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryPriceAsc("강아지", "미용");
			}
		}
		
		if(kindPath.equals("productCatList")) {
			myPet.setMypetKind("고양이");
			productList = productService.findAllProductByPetCategory("고양이");
			if(categoryPath.equals("All")) {
				productList = productService.findAllByOrderByProductByPetCategoryPriceAsc("고양이");
			}
			if(categoryPath.equals("1")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryPriceAsc("고양이", "사료");
			}
			if(categoryPath.equals("2")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryPriceAsc("고양이", "간식");
			}
			if(categoryPath.equals("3")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryPriceAsc("고양이", "캔");
			}
			if(categoryPath.equals("4")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryPriceAsc("고양이", "모래");
			}
			if(categoryPath.equals("5")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryPriceAsc("고양이", "미용");
			}
		}
		
		for (Product product : productList) {
			productPriceAscDto.add(ProductPriceAscDto.toDto(product));
		}

		model.addAttribute("productList", productPriceAscDto);
		model.addAttribute("myPet", myPet);

		return "shop";
	}
	 
	@GetMapping("/productDateDesc")
	public String productDateDesc(Model model, HttpSession session, @RequestParam String path) {
		List<ProductProductNoDescDto> productProductNoDescDto = new ArrayList<>();
		// 상품가격 등록일순
		List<Product> productList = productService.findAllByOrderByProductNoDesc();
		Long userNo = (Long) session.getAttribute("userNo");
		MyPet myPet = MyPet.builder().build();

		String kindPath = "";
		String categoryPath = "";
		
		if(path.equals("/productList")) {
			if(myPetService.findLeaderMyPet(userNo)!=null) {
				myPet.setMypetKind(myPetService.findLeaderMyPet(userNo).getMypetKind());	
			}else {
				myPet.setMypetKind("강아지");
			}
			productList = productService.findAllByOrderByProductByPetCategoryNoDesc(myPet.getMypetKind());
		} else {
			kindPath = path.substring(1,path.lastIndexOf("?"));
			categoryPath = path.substring(path.lastIndexOf("=")+1);
		}
		
		if(kindPath.equals("productDogList")) {
			myPet.setMypetKind("강아지");
			productList = productService.findAllProductByPetCategory("강아지");
			if(categoryPath.equals("All")) {
				productList = productService.findAllByOrderByProductByPetCategoryNoDesc("강아지");
			}
			if(categoryPath.equals("1")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryNoDesc("강아지", "사료");
			}
			if(categoryPath.equals("2")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryNoDesc("강아지", "간식");
			}
			if(categoryPath.equals("3")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryNoDesc("강아지", "캔");
			}
			if(categoryPath.equals("4")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryNoDesc("강아지", "위생");
			}
			if(categoryPath.equals("5")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryNoDesc("강아지", "미용");
			}
		}
		
		if(kindPath.equals("productCatList")) {
			myPet.setMypetKind("고양이");
			productList = productService.findAllProductByPetCategory("고양이");
			if(categoryPath.equals("All")) {
				productList = productService.findAllByOrderByProductByPetCategoryNoDesc("고양이");
			}
			if(categoryPath.equals("1")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryNoDesc("고양이", "사료");
			}
			if(categoryPath.equals("2")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryNoDesc("고양이", "간식");
			}
			if(categoryPath.equals("3")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryNoDesc("고양이", "캔");
			}
			if(categoryPath.equals("4")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryNoDesc("고양이", "모래");
			}
			if(categoryPath.equals("5")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryNoDesc("고양이", "미용");
			}
		}
		
		for (Product product : productList) {
			productProductNoDescDto.add(ProductProductNoDescDto.toDto(product));
		}

		model.addAttribute("productList", productProductNoDescDto);
		model.addAttribute("myPet", myPet);

		return "shop";
	}
	
	@GetMapping("/productStarDesc")
	public String productStarDesc(Model model, HttpSession session, @RequestParam String path) {
		List<ProductProductNoDescDto> productProductNoDescDto = new ArrayList<>();
		// 상품가격 등록일순
		List<Product> productList = productService.findAllByOrderByProductNoDesc();
		Long userNo = (Long) session.getAttribute("userNo");
		MyPet myPet = MyPet.builder().build();
		
		String kindPath = "";
		String categoryPath = "";
		
		if(path.equals("/productList")) {
			if(myPetService.findLeaderMyPet(userNo)!=null) {
				myPet.setMypetKind(myPetService.findLeaderMyPet(userNo).getMypetKind());	
			}else {
				myPet.setMypetKind("강아지");
			}
			productList = productService.findAllByOrderByProductByPetCategoryStarAvgDesc(myPet.getMypetKind());
		} else {
			kindPath = path.substring(1,path.lastIndexOf("?"));
			categoryPath = path.substring(path.lastIndexOf("=")+1);
		}
		
		if(kindPath.equals("productDogList")) {
			myPet.setMypetKind("강아지");
			productList = productService.findAllProductByPetCategory("강아지");
			if(categoryPath.equals("All")) {
				productList = productService.findAllByOrderByProductByPetCategoryStarAvgDesc("강아지");
			}
			if(categoryPath.equals("1")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryStarAvgDesc("강아지", "사료");
			}
			if(categoryPath.equals("2")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryStarAvgDesc("강아지", "간식");
			}
			if(categoryPath.equals("3")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryStarAvgDesc("강아지", "캔");
			}
			if(categoryPath.equals("4")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryStarAvgDesc("강아지", "위생");
			}
			if(categoryPath.equals("5")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryStarAvgDesc("강아지", "미용");
			}
		}
		
		if(kindPath.equals("productCatList")) {
			myPet.setMypetKind("고양이");
			productList = productService.findAllProductByPetCategory("고양이");
			if(categoryPath.equals("All")) {
				productList = productService.findAllByOrderByProductByPetCategoryStarAvgDesc("고양이");
			}
			if(categoryPath.equals("1")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryStarAvgDesc("고양이", "사료");
			}
			if(categoryPath.equals("2")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryStarAvgDesc("고양이", "간식");
			}
			if(categoryPath.equals("3")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryStarAvgDesc("고양이", "캔");
			}
			if(categoryPath.equals("4")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryStarAvgDesc("고양이", "모래");
			}
			if(categoryPath.equals("5")) {
				productList = productService.findAllByOrderByProductByPetCategoryByProductCategoryStarAvgDesc("고양이", "미용");
			}
		}
		
		for (Product product : productList) {
			productProductNoDescDto.add(ProductProductNoDescDto.toDto(product));
		}
		
		model.addAttribute("productList", productProductNoDescDto);
		model.addAttribute("myPet", myPet);
		
		return "shop";
	}
	
	
	
	@GetMapping(value = "/productDetail", params = "productNo")
	public String productDetail(@RequestParam Long productNo,Model model,HttpServletRequest request) {
		System.out.println(">>>>>>>>>"+request.getRequestURI());
		Product product = productService.findByProductNo(productNo);
		String findProductName = productService.findByProductNo(productNo).getProductName();
		boolean nextExist = productService.existsById(productNo+1);
		System.out.println("-------------->"+nextExist);
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
		//model.addAttribute("nextExist",nextExist);
		model.addAttribute("products", products);
		model.addAttribute("productName", productNameDto);
		
		List<ReviewBoard> reviewList = reviewBoardService.findByProductNo(product.getProductNo());
		model.addAttribute("reviewList", reviewList);
		
		return "product-details";
	}
	
	@GetMapping("/findAllProductByCategory")
	public String findAllProductByCategory(Model model, HttpSession session, @RequestParam Integer a) {
		Product product = Product.builder().build();
		List<ProductListDto> productListDto = new ArrayList<>();
		List<Product> productList = productService.findAllProductByCategory(product.getProductCategory(), product.getProductPetCategory());
		
		for (Product products : productList) {
			productListDto.add(ProductListDto.toDto(products));
		}
		
		model.addAttribute("productList", productListDto);
		
		return "shop";
	}
	
	// 리뷰 정렬
	@GetMapping(value = "/productDetails", params = "productNo")
	public String productDetail(@RequestParam Long productNo,Model model,String sortOrder) {
		Product product = productService.findByProductNo(productNo);
		String findProductName = productService.findByProductNo(productNo).getProductName();
		int firstSpaceIndex = findProductName.indexOf(" ");
		
		System.out.println(">>>>>>>>>>>>>>>>>"+sortOrder);
		
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
		List<ReviewBoard> reviewList = new ArrayList<ReviewBoard>();
		if(sortOrder.equals("rating")) {
			reviewList = reviewBoardService.findByProductProductNoOrderByBoardStarDesc(productNo);
		}
		if(sortOrder.equals("latest")) {
			reviewList = reviewBoardService.findByProductProductNoOrderByBoardDateDesc(productNo);
		}
		
		model.addAttribute("reviewList", reviewList);
		
		return "product-details";
	}
	
	
}
