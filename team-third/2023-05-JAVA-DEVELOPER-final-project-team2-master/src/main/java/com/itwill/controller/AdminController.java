package com.itwill.controller;

import java.io.File;
import java.sql.Date;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.itwill.dto.AdminUserListDto;
import com.itwill.dto.CenterDto;
import com.itwill.dto.CenterListDto;
import com.itwill.dto.OrdersDto;
import com.itwill.dto.PetDto;
import com.itwill.dto.ProductInsertDto;
import com.itwill.dto.ProductListDto;
import com.itwill.dto.VolunteerDto;
import com.itwill.entity.Adopt;
import com.itwill.entity.Center;
import com.itwill.entity.MyPet;
import com.itwill.entity.Orders;
import com.itwill.entity.Pet;
import com.itwill.entity.Product;
import com.itwill.entity.Userinfo;
import com.itwill.entity.Visit;
import com.itwill.entity.Volunteer;
import com.itwill.entity.Wish;
import com.itwill.repository.AdoptRepository;
import com.itwill.repository.VisitRepository;
import com.itwill.repository.VolunteerRepository;
import com.itwill.service.AdoptService;
import com.itwill.service.CartService;
import com.itwill.service.CenterService;
import com.itwill.service.MyPetService;
import com.itwill.service.OrderService;
import com.itwill.service.PetService;
import com.itwill.service.ProductService;
import com.itwill.service.UserInfoService;
import com.itwill.service.VisitService;
import com.itwill.service.VolunteerService;
import com.itwill.service.WishService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {
		@Autowired
		private UserInfoService userInfoService;
		@Autowired
		private CartService cartService;
		@Autowired
		private OrderService orderService;
		@Autowired
		private WishService wishService;
		@Autowired
		private MyPetService myPetService;
		@Autowired
		private ProductService productService;
		@Autowired
		private AdoptService adoptService;
		@Autowired
		private AdoptRepository adoptRepository;
		@Autowired
		private VolunteerService volunteerService;
		@Autowired
		private VolunteerRepository volunteerRepository;
		@Autowired
		private PetService petService;
		@Autowired
		private VisitService visitService;
		@Autowired
		private VisitRepository visitRepository;
		@Autowired
		private CenterService centerService;
		/******************************* Userinfo ************************************/
		
		
		@GetMapping(value = "/adminUserList")
		// 관리자 --> 회원정보 리스트
		// 번호, 아이디, 이름, 포인트, 성별, 주소, 연락처
		public String adminUserList(Model model,@PageableDefault(page =0,size = 10,sort = "boardNo",direction = Sort.Direction.ASC) Pageable page) throws Exception {
			int pag = page.getPageNumber();
			int size = page.getPageSize();
			
			Pageable pageable= PageRequest.of(pag,size,Sort.by(Sort.Order.asc("userNo")));
			Page<Userinfo> userList = userInfoService.findAllPage(pageable);
			
			model.addAttribute("adminUserList", userList);
			
			return "admin-userinfo";
		}
		
		// 관리자 --> 마이페이지 이동
		@GetMapping(value="/adminUserinfo")
		public String view(Model model, HttpSession session) throws Exception{
			Long userNo = (Long)session.getAttribute("userNo");
			if(userNo==null) {
				throw new Exception("로그인을 해주세요");
			}
			Userinfo userinfo = userInfoService.findUserByNo(userNo);
			model.addAttribute("userinfo", userinfo);
			
			return "admin-userinfo";
			
		}
		
		@GetMapping(value = "adminUpdateUser")
		public String update(Model model,@RequestParam Long userNo) throws Exception{
			
			
			Userinfo userinfo = userInfoService.findUserByNo(userNo);
			
			model.addAttribute("userinfo", userinfo);
			
			return "adminUpdateUserInfo";
		}
		
		
		
		@GetMapping("/adminUserDelete")
		public String delete(@RequestParam Long userNo,Model model) throws Exception {
			cartService.deleteByUserId(userNo);
			
			List<Orders> orderList = orderService.findOrderById(userNo);
			
			for (Orders orders : orderList) {
				orderService.removeOrderByOrderNo(orders.getOrderNo());
			}
			
			List<Wish> wishs = wishService.findAllWishByUserNo(userNo);
			for (Wish wish : wishs) {
				wishService.deleteWish(wish.getWishNo());
			}

			List<MyPet> myPets = myPetService.findMyPetListByuserNo(userNo);
			for (MyPet myPet : myPets) {
				myPetService.Delete(myPet.getMypetNo());
			}
			userInfoService.remove(userNo);
			
			List<AdminUserListDto> adminUserList = new ArrayList<>();
			List<Userinfo> userList = new ArrayList<>();
			
			userList = userInfoService.findUserList();
			
			for (Userinfo userinfo : userList) {
				adminUserList.add(AdminUserListDto.toDto(userinfo));
			}
			
			model.addAttribute("adminUserList", adminUserList);
			
			return "admin-userinfo";
		}
		/*
		 
		  ~~~~~~~~~~~~~~~~~~~~ 팀장 이거 수정해달라 ~~~~~~~~~~~~~~~~~~~~~~
		
		// 관리자 --> 회원 탈퇴
		@GetMapping("userDelete")
		public String delete(HttpSession session) throws Exception {
			Long userNo = (Long)session.getAttribute("userNo");
			
			userInfoService.remove(userNo);
			cartService.deleteByUserId(userNo);
			
			List<Orders> orderList = orderService.findOrderById(userNo);
			
			for (Orders orders : orderList) {
				orderService.removeOrderByOrderNo(orders.getOrderNo());
			}
			
			List<Wish> wishs = wishService.findAllWishByUserNo(userNo);
			for (Wish wish : wishs) {
				wishService.deleteWish(wish.getWishNo());
			}

			List<MyPet> myPets = myPetService.findMyPetListByuserNo(userNo);
			for (MyPet myPet : myPets) {
				myPetService.Delete(myPet.getMypetNo());
			}
			
			session.invalidate();
			
			return "index";
		}
		*/
		
		
		/******************************* Adopt ************************************/
		
		
		@GetMapping("/adminAdoptList")
		// 관리자 --> 입양신청 리스트
		// 
		public String adoptList(Model model, HttpSession session) throws Exception {
			Long userNo = (Long) session.getAttribute("userNo");
			Userinfo userinfo=userInfoService.findUserByNo(userNo);
			List<Adopt> adoptList = adoptService.findAdoptList();
			
			model.addAttribute("adoptList", adoptList);
			return "admin-adopt";
		}
		
		
		@GetMapping("/updateAdopt/{adoptNo}")
		public String updateAdopt(@PathVariable Long adoptNo, Model model, HttpSession session) throws Exception {
		    Long userNo = (Long) session.getAttribute("userNo");
		    Userinfo userinfo = userInfoService.findUserByNo(userNo);
		    
		    Adopt findAdopt = adoptService.findByAdoptNo(adoptNo);

		    
		    // Visit 업데이트 로직
		    findAdopt.setAdoptStatus("입양완료"); 
		    adoptService.updateAdopt(findAdopt);
		   adoptService.deleteAdopt(findAdopt.getAdoptNo());
		  Pet pet= petService.petFindById(findAdopt.getPet().getPetNo());
		  petService.petRemove(pet.getPetNo());
		    // 변경된 상태를 DB에 반영
		    //adoptRepository.save(findAdopt);
		   
		    
		    return "redirect:/adminAdoptList";
		}
		
		
		
		/******************************* Volunteer ************************************/
		
		/*
		// 관리자 --> 상품목록 리스트
		@GetMapping("/adminProductList")
		public String adminProductList(@PageableDefault(page = 0, size = 10, sort = "productNo", direction = Sort.Direction.DESC) Pageable page, Model model, HttpSession session) {
			int pageNo = page.getPageNumber();
			int size = page.getPageSize();
			
			Pageable pageable = PageRequest.of(pageNo, size, Sort.by(Sort.Order.asc("productNo")));
			
			Page<Product> productList = productService.productFindAllPage(pageable);
			
			model.addAttribute("products", productList.getContent());
			model.addAttribute("productList", productList);
			
			return "admin-product";
		}
		*/
		
		// 관리자 -> 봉사 리스트 조회
		@GetMapping("/adminVolunteerList")
		public String findVolunteerList(@PageableDefault(page = 0, size = 10, sort = "volunteerNo", direction = Sort.Direction.DESC) Pageable page,Model model, HttpSession session) throws Exception{
			int pageNo = page.getPageNumber();
			int size = page.getPageSize();
			
			Pageable pageable = PageRequest.of(pageNo, size, Sort.by(Sort.Order.desc("volunteerNo")));
			
			Page<Volunteer> volunteerList = volunteerService.volunteerFindAllPage(pageable);
				
			model.addAttribute("volunteer", volunteerList.getContent());
			model.addAttribute("volunteerList", volunteerList);
			
			return "admin-volunteer";	
		}
		
		/*
		// 관리자 -> 봉사 리스트 조회
		@GetMapping("/adminVolunteerList")
		public String findVolunteerList(Model model, HttpSession session) throws Exception{
			Long userNo = (Long) session.getAttribute("userNo");
			Userinfo userinfo = userInfoService.findUserByNo(userNo);
			
			List<Volunteer> volunteerList;
			volunteerList = volunteerService.findAllVolunteers();
			// volunteerDate를 기준으로 내림차순으로 정렬
		    volunteerList.sort((v1, v2) -> v2.getVolunteerDate().compareTo(v1.getVolunteerDate()));
		
		    model.addAttribute("volunteerList", volunteerList);
		    return "admin-volunteer";
		}
		*/

		
		//@Transactional
		@GetMapping("/updateVolunteer/{volunteerNo}")
		public String updateVolunteer(@PathVariable Long volunteerNo, Model model, HttpSession session) throws Exception {
			try {
		        String userName = (String) session.getAttribute("userName");

		        if ("관리자".equals(userName)) {
		            Volunteer findVolunteer = volunteerService.findByVolunteerNo(volunteerNo);
		            findVolunteer.setVolunteerStatus("봉사완료"); 
		            volunteerService.updateVolunteer(findVolunteer);
		            Long userNo = findVolunteer.getUserinfo().getUserNo();
		            Integer userPoint = (Integer) session.getAttribute("userPoint");

		            Userinfo user = userInfoService.findUserByNo(userNo);
		            if (user != null) {
		                userPoint = (user.getUserPoint() != null) ? user.getUserPoint() + 3000 : 3000;
		                user.setUserPoint(userPoint);
		                userInfoService.update(user);
		                session.setAttribute("userPoint", userPoint);
		            }
		            volunteerRepository.save(findVolunteer);
		        } else {
		            return "redirect:/error"; // 적절한 에러 페이지로 변경하세요.
		        }

		        return "redirect:/adminVolunteerList";
		    } catch (Exception e) {
		        e.printStackTrace();
		        return "redirect:/error";
		    }
		}
		
		
		
		
		
		
		/******************************* Product ************************************/
		
		
		
		// 관리자 --> 상품목록 리스트
		@GetMapping("/adminProductList")
		public String adminProductList(Model model, HttpSession session) {
			List<ProductListDto> productListDto = new ArrayList<>();
			List<Product> productList = new ArrayList<>();
			
			Long userNo = (Long) session.getAttribute("userNo");
			
			productList = productService.findAllByOrderByProductNoAsc();
			
			for (Product product : productList) {
				productListDto.add(ProductListDto.toDto(product));
			}
			
			model.addAttribute("productList", productListDto);
			
			return "admin-product";
		}
		
		
		/*
		// 관리자 --> 상품목록 리스트
		@GetMapping("/adminProductList")
		public String adminProductList(@PageableDefault(page = 0, size = 10, sort = "productNo", direction = Sort.Direction.DESC) Pageable page, Model model, HttpSession session) {
			int pageNo = page.getPageNumber();
			int size = page.getPageSize();
			
			Pageable pageable = PageRequest.of(pageNo, size, Sort.by(Sort.Order.asc("productNo")));
			
			Page<Product> productList = productService.productFindAllPage(pageable);
			
			model.addAttribute("products", productList.getContent());
			model.addAttribute("productList", productList);
			
			return "admin-product";
		}
		*/
		
		// 관리자 --> 상품 추가
		@PostMapping("/adminInsertProduct")
		public String insertProduct(@RequestParam("imageFile1") MultipartFile file1, @RequestParam("imageFile2") MultipartFile file2, @RequestParam("productName") String productName, 
				@RequestParam("productPrice") Integer productPrice, @RequestParam("productCategory") String productCategory, @RequestParam("productPetCategory") String productPetCategory, Model model,
				@PageableDefault(page = 0, size = 10, sort = "productNo", direction = Sort.Direction.ASC) Pageable page) throws Exception {

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
							.productStarAvg(0D)
							.productQty(0)
							.build();
		
		productService.insertProduct(createProduct);
		
		int pag = page.getPageNumber();
		int size = page.getPageSize();
		
		Pageable pageable = PageRequest.of(pag, size, Sort.by(Sort.Order.desc("productNo")));
		
		Page<Product> productList = productService.productFindAllPage(pageable);
		
		model.addAttribute("products", productList.getContent());
		model.addAttribute("productList", productList);
		
		return "admin-product";
}
		
		@GetMapping("/productUpdateForm")
		public String productUpdateForm(@RequestParam Long productNo, Model model) {
			
			Product product = productService.findByProductNo(productNo);
			
			model.addAttribute("product", product);
			
			return "product_update_form"; 
		}
		
		
		// 관리자 --> 상품정보 수정
		// 수정버튼 누를떄임
		@PostMapping("/adminUpdateProduct")
		public String upateProduct(@RequestParam("imageFile") MultipartFile file, @RequestParam("productName") String productName, @RequestParam("productPrice") Integer productPrice, @RequestParam("productNo") Long productNo, Model model,
				@PageableDefault(page = 0, size = 10, sort = "productNo", direction = Sort.Direction.ASC) Pageable page) throws Exception {
			
			List<Product> findProduct = productService.findByProductImage(file.getOriginalFilename());
	
			String savedFileName = "";
			
			if (findProduct.size() > 0) {
				savedFileName = file.getOriginalFilename();
			} else {
				String uploadPath = System.getProperty("user.dir") + "/src/main/resources/static/image/product/";
				String originalFileName = file.getOriginalFilename();
				UUID uuid = UUID.randomUUID();
				savedFileName = uuid.toString() + "_" + originalFileName;
				
				File newFile = new File(uploadPath + savedFileName);
				
				file.transferTo(newFile);
			}
			
			Product update = Product.builder().build();
			
			update.setProductNo(productNo);
			update.setProductName(productName);
			update.setProductPrice(productPrice);
			update.setProductImage(savedFileName);
			
			productService.updateProduct(update);
			
			int pag = page.getPageNumber();
			int size = page.getPageSize();
			
			Pageable pageable = PageRequest.of(pag, size);
			
			Page<Product> productList = productService.productFindAllPage(pageable);
			
			model.addAttribute("products", productList.getContent());
			model.addAttribute("productList", productList);
			
			return "admin-product";
		}
		
		
		
		
		
		/******************************* Orders ************************************/
		
		
		//관리자전용
		@GetMapping("/adminOrdersList")
		public String adminOrderList(Model model,HttpSession session,@PageableDefault(page =0,size = 10,sort = "ORDER_NO",direction = Sort.Direction.DESC) Pageable page) throws Exception {
			int pag = page.getPageNumber();
			int size = page.getPageSize();
			
			Pageable pageable = PageRequest.of(pag, size, Sort.by(Sort.Order.desc("orderNo")));
			
			Page<Orders> orderList = orderService.findOrders(pageable);
			System.out.println(">>>>>>"+orderList);
			List<OrdersDto> ordersDto = new ArrayList<OrdersDto>();
				for (Orders orders : orderList) {
					Userinfo userinfo = orders.getUserinfo();
					OrdersDto dto = OrdersDto.toDto(orders);
					dto.setUserinfo(userinfo);
					ordersDto.add(dto);
				}
				model.addAttribute("ordersList",orderList);
				return "admin-orders";
		}
		
		
		@GetMapping("/dateByAdmin")
		public String dateByOrder(@RequestParam("startDate")Date startDate,@RequestParam("endDate") Date endDate, HttpSession session,Model model){
			List<OrdersDto> ordersListDto = new ArrayList<OrdersDto>();
			System.out.println(">>>>>>>>파라미터 왔을까");
			Long userNo=(Long)session.getAttribute("userNo");
			List<Orders> ordersList = orderService.findAllByOrdersByOrderDate(startDate, endDate);
			
			
			System.out.println(">>>>>>>>어드민 데이트"+ordersList);
			for (Orders orders : ordersList) {
				OrdersDto ordersDto = OrdersDto.toDto(orders);
				ordersListDto.add(ordersDto);
			}
			
			System.out.println(">>>>>>>dto객체"+ordersListDto);
			
			model.addAttribute("ordersList",ordersList);
			model.addAttribute("pageStatus",0);
			
			return "admin-orders";
			
		}
		
		
		
		/******************************* Pet ************************************/
		
		@GetMapping("/petinsertform")
		public String petinsertform(Model model) throws Exception {
			
			List<Center> centers=centerService.findAllCenters();
			
			model.addAttribute("petCenter",centers);
			return "pet_insert_form";
		}
		
		// 관리자 --> 펫 리스트
		@GetMapping("/adminPetList")
		public String petList(Model model) {
			List<PetDto> petDtoList = new ArrayList<>();
			List<Pet> petList = petService.petFindAll();
			for (Pet pet : petList) {
				petDtoList.add(PetDto.toDto(pet));
			}
			
			//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>"+petDtoList.get(0).getPetType());
			model.addAttribute("petList",petDtoList);
			return "pet-list" ;
		}
		
		@GetMapping("/petUpdateForm")
		public String petUpdateForm(@RequestParam Long petNo, Model model) {
	List<Center> centers=centerService.findAllCenters();
			Pet updatePet = petService.petFindById(petNo);
			PetDto petDto=PetDto.toDto(updatePet);
			
			model.addAttribute("pet", petDto);
			model.addAttribute("petCenter", centers);
			
			return "pet_update_form"; 
		}
		
		@PostMapping("/insert_action")
		public String insert_action(@RequestParam("imageFile") MultipartFile file,
				@RequestParam("petType") String petType, @RequestParam("petGender") String petGender,
				@RequestParam("petLocal") String petLocal,
				@RequestParam("centerNo") String centerNo,@RequestParam("petFindPlace") String petFindPlace,@RequestParam("petCharacter") String petCharacter, Model model,@PageableDefault(page = 0, size = 5, sort = "petNo", direction = Sort.Direction.ASC) Pageable page) throws Exception {

			String uploadPath = System.getProperty("user.dir") + "/src/main/resources/static/image/pet/";
			String originalFileName = file.getOriginalFilename();
			UUID uuid = UUID.randomUUID();
			String savedFileName = uuid.toString() + "_" + originalFileName;

			File newFile = new File(uploadPath + savedFileName);

			file.transferTo(newFile);
				
			Center center=centerService.findByCenterNo(Long.parseLong(centerNo));
			
			Pet insertPet = Pet.builder().petType(petType).petGender(petGender)
					.petImage(savedFileName).petLocal(petLocal)
					.center(center).petFindPlace(petCharacter).petCharacter(petCharacter). build();

			petService.petSave(insertPet);
			
			int pag = page.getPageNumber();
			int size = page.getPageSize();
			
			Pageable pageable = PageRequest.of(pag, size, Sort.by(Sort.Order.desc("petNo")));
			Page<Pet> petList= petService.petFindAllPage(pageable);
			

			model.addAttribute("petList",petList);
			return "redirect:petListPage" ;

		}
		@PostMapping("/pet_update_action")
		public String pet_update_action(@RequestParam("imageFile") MultipartFile file,PetDto petDto, Model model,@PageableDefault(page = 0, size = 5, sort = "petNo", direction = Sort.Direction.ASC) Pageable page) throws Exception {

			String uploadPath = System.getProperty("user.dir") + "/src/main/resources/static/image/pet/";
			String originalFileName = file.getOriginalFilename();
			UUID uuid = UUID.randomUUID();
			String savedFileName = uuid.toString() + "_" + originalFileName;

			File newFile = new File(uploadPath + savedFileName);

			file.transferTo(newFile);
				
			
			System.out.println(">>>>>>>>>>펫타입"+petDto.getPetType());
			System.out.println(">>>>>>>>>>펫젠더"+petDto.getPetGender());
			System.out.println(">>>>>>>>>>펫넘버"+petDto.getPetNo());
			System.out.println(">>>>>>>>>>플레이스"+petDto.getPetFindPlace());
			System.out.println(">>>>>>>>>>펫설먕"+petDto.getPetCharacter());
			System.out.println(">>>>>>>>>>펫넘버"+petDto.getCenterNo());
			
			Center center=centerService.findByCenterNo(petDto.getCenterNo());
			
			Pet pet=petService.petFindById(petDto.getPetNo());
			System.out.println(">>>펫정보"+pet);
			pet.setPetType(petDto.getPetType());
			pet.setPetGender(petDto.getPetGender());
			pet.setPetLocal(petDto.getPetLocal());
			pet.setCenter(center);
			pet.setPetFindPlace(petDto.getPetFindPlace());
			pet.setPetCharacter(petDto.getPetCharacter());

			petService.petUpdate(pet);
			
			int pag = page.getPageNumber();
			int size = page.getPageSize();
			
			Pageable pageable = PageRequest.of(pag, size, Sort.by(Sort.Order.desc("petNo")));
			Page<Pet> petList= petService.petFindAllPage(pageable);
			

			model.addAttribute("petList",petList);
			return "redirect:petListPage" ;

		}

		
		
		
	
		
		
		
		/******************************* visit ************************************/
		// 관리자 --> 견학 리스트
		@GetMapping("/adminVisitList")
		public String findOrders(@PageableDefault(page = 0, size = 10, sort = "visitNo", direction = Sort.Direction.DESC) Pageable page,Model model, HttpSession  session) throws Exception {
			int pageNo = page.getPageNumber();
			int size = page.getPageSize();
			
			Pageable pageable = PageRequest.of(pageNo, size, Sort.by(Sort.Order.desc("visitNo")));
					
			Page<Visit> visitList = visitService.visitFindAllPage(pageable);
					
			model.addAttribute("visit", visitList.getContent());
			model.addAttribute("visitList", visitList);			
			
			return "admin-visit";
			}
		
		/*
		// 관리자 --> 견학 리스트
		@GetMapping("/adminVisitList")
		public String findOrders(Model model, HttpSession  session) throws Exception {
			Long userNo = (Long) session.getAttribute("userNo");
			Userinfo userinfo = userInfoService.findUserByNo(userNo);
			
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>"+userinfo);
			List<Visit> visitList;
			visitList = visitService.selectAllVisits();
			
		    model.addAttribute("visitList", visitList);
		    return "admin-visit";
		}
		*/
		
		@GetMapping("/updateVisit/{visitNo}")
		public String updateVisit(@PathVariable Long visitNo, Model model, HttpSession session) throws Exception {
		    Long userNo = (Long) session.getAttribute("userNo");
		    Userinfo userinfo = userInfoService.findUserByNo(userNo);
		    Visit findVisit = visitService.findByVisitNo(visitNo);

		    // 로그를 이용한 디버깅
		    System.out.println("Before update: " + findVisit.getVisitStatus());

		    // Visit 업데이트 로직
		    findVisit.setVisitStatus("견학완료"); 
		    visitService.updateVisit(findVisit);

		    // 로그를 이용한 디버깅
		    System.out.println("After update: " + findVisit.getVisitStatus());

		    // 변경된 상태를 DB에 반영
		    visitRepository.save(findVisit);

		    return "redirect:/adminVisitList";
		}

		/******************************* center ************************************/
		
		@GetMapping("/centerListAll")
		public String centerList(Model model) {
		    List<Center> centerList = centerService.findAllCenters();
		    model.addAttribute("centerList", centerList);
		    return "admin-center";
		}
	
	@GetMapping("centerInsertForm")
	public String updateCenterForm() {
		
		return "center_insert_form";
	}
		
	// 관리자 --> 센터생성
		// 업뎃폼에서 버튼 눌렀을때~
	@PostMapping("/centerInsert")
	public String insertCenter(@RequestParam("imageFile") MultipartFile file,
			@RequestParam("centerName") String centerName, @RequestParam("centerPhoneNumber") String centerPhoneNumber,
			@RequestParam("centerLocal") String centerLocal,
			@RequestParam("centerOpenCloseTime") String centerOpenCloseTime, Model model) throws Exception {

		String uploadPath = System.getProperty("user.dir") + "/src/main/resources/static/image/center/";
		String originalFileName = file.getOriginalFilename();
		UUID uuid = UUID.randomUUID();
		String savedFileName = uuid.toString() + "_" + originalFileName;

		File newFile = new File(uploadPath + savedFileName);

		file.transferTo(newFile);

		Center createCenter = Center.builder().centerName(centerName).centerLocal(centerLocal)
				.centerImage(savedFileName).centerOpenCloseTime(centerOpenCloseTime)
				.centerPhoneNumber(centerPhoneNumber).build();

		centerService.createCenter(createCenter);

		List<CenterListDto> centerListDto = new ArrayList<>();
		List<Center> centerList = new ArrayList<>();

		centerList = centerService.findAllCenters();

		for (Center center : centerList) {
			centerListDto.add(CenterListDto.toDto(center));
		}

		model.addAttribute("centerList", centerListDto);

		return "admin-center";
	}

}
