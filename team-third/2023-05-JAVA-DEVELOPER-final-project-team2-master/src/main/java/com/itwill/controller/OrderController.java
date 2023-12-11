package com.itwill.controller;

import java.nio.charset.Charset;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.dto.CartDto;
import com.itwill.dto.CartOrderViewDto;
import com.itwill.dto.CouponDto;
import com.itwill.dto.OrderItemDto;
import com.itwill.dto.OrderViewDto;
import com.itwill.dto.OrdersDto;
import com.itwill.dto.UserOrderViewDto;
import com.itwill.entity.Cart;
import com.itwill.entity.Coupon;
import com.itwill.entity.OrderItem;
import com.itwill.entity.Orders;
import com.itwill.entity.Orderstatus;
import com.itwill.entity.Product;
import com.itwill.entity.Userinfo;
import com.itwill.repository.OrderStatusRepository;
import com.itwill.service.CartService;
import com.itwill.service.CouponService;
import com.itwill.service.OrderItemService;
import com.itwill.service.OrderService;
import com.itwill.service.ProductService;
import com.itwill.service.ReviewBoardService;
import com.itwill.service.UserInfoService;

import groovyjarjarantlr4.v4.parse.ANTLRParser.parserRule_return;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private CartService cartService;
	@Autowired
	OrderStatusRepository orderStatusRepository;
	@Autowired
	OrderItemService itemService;
	@Autowired
	UserInfoService userInfoService;
	@Autowired
	CouponService couponService;
	@Autowired
	private ReviewBoardService reviewBoardService;
	@Autowired
	private ProductService productService;
	
	@Operation(summary = "주문 등록")
	@GetMapping("/orderInsert")
	public String insert_Order(String orderAddress,String orderPrice,String userPoint,String couponId, HttpSession session)throws Exception {
		if (session.getAttribute("userNo") == null) {
			  
			  throw new
		 Exception("로그인 하세요."); 
		  
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>주문등록");
		
		Long userNo = (Long) session.getAttribute("userNo");
		Userinfo userinfo = userInfoService.findUserByNo(userNo);
		Integer point=Integer.parseInt(userPoint);
		Orderstatus orderstatus= orderStatusRepository.findById(1L).get();
		Long osNo = orderstatus.getOsNo();
		List<Cart> carts = cartService.findAllCartByUserId(userNo);
		
		System.out.println(">>>>>>>>>>>>이게cart>>>"+carts);
		Userinfo findUserinfo = userInfoService.findUserByNo(userNo);
		Orders orders = Orders.builder().userinfo(findUserinfo).orderAddress(orderAddress).orderPrice(Integer.parseInt(orderPrice)).build();
		if(carts.size()>1) {
			orders.setOrderDesc(carts.get(0).getProduct().getProductName()+"외"+(carts.size()-1)+"개 상품");
		}else {
			orders.setOrderDesc(carts.get(0).getProduct().getProductName());
		}
		Orders newOrder = orderService.insertOrder(orders);
		
		//Orders insertOrder = orderService.insertOrder(OrdersDto.toEntity(orderDto));
		List<OrderItemDto> orderItemDtos = new ArrayList<OrderItemDto>();
		
		for (Cart cart : carts) {
			OrderItemDto tempOrderItemDto=OrderItemDto.builder().build();
			tempOrderItemDto.setOiQty(cart.getCartQty());
			tempOrderItemDto.setOrderNo(newOrder.getOrderNo());
			tempOrderItemDto.setOrderstatus(orderstatus);
			tempOrderItemDto.setProduct(cart.getProduct());
			
			itemService.insertOrderItem(OrderItemDto.toEntity(tempOrderItemDto));
			orderItemDtos.add(tempOrderItemDto);
			
		}
		OrdersDto orderdto = OrdersDto.toDto(newOrder);
		orderdto.setOrderItemDtos(orderItemDtos);
		orderdto.setUserNo(userNo);
		userinfo.setUserPoint(userinfo.getUserPoint()-point);
		/*
		//insertOrder.setOrderDesc(carts.get(0).getProduct().getProductName()+"외"+(carts.size()-1)+"개 상품");
		//insertOrder.setOrderAddress(orderDto.getOrderAddress());
		
		orderService.insertOrder(OrdersDto.toEntity(orderDto));
		 */
		couponService.Delete(Long.parseLong(couponId)); //쿠폰삭제기능
		cartService.deleteByUserId(userNo);
		
		return "index";
	}

	/*
	 * //관리자전용
	 * 
	 * @GetMapping("/ordersList") public String findOrders(Model model,HttpSession
	 * session) throws Exception { List<Orders> orderList =
	 * orderService.findOrders(); List<OrdersDto> ordersDto = new
	 * ArrayList<OrdersDto>(); for (Orders orders : orderList) { Userinfo userinfo =
	 * orders.getUserinfo(); OrdersDto dto = OrdersDto.toDto(orders);
	 * dto.setUserinfo(userinfo); ordersDto.add(dto); }
	 * model.addAttribute("ordersList",ordersDto); return "my-account-orders";
	 * 
	 * }
	 */
	//회원 주문목록
	@GetMapping("/orders")
	public String findOrderByUser( HttpSession session,@PageableDefault(page =0,size = 10,sort = "ORDER_NO",direction = Sort.Direction.DESC) Pageable page,Model model) throws Exception {
		if (session.getAttribute("userNo") == null) {
			throw new Exception("로그인 하세요.");
		}
		Long userNo=(Long)session.getAttribute("userNo");
		
		int pag = page.getPageNumber();
		int size = page.getPageSize();
		
		Pageable pageable = PageRequest.of(pag, size, Sort.by(Sort.Order.desc("ORDER_NO")));
		Page<Orders> orderList = orderService.findOrderById(userNo,pageable);
		List<OrdersDto> ordersDtoList = new ArrayList<OrdersDto>();
		for (Orders orders : orderList) {
			Userinfo userinfo = orders.getUserinfo();
			OrdersDto dto = OrdersDto.toDto(orders);
			dto.setUserinfo(userinfo);
			/*
			if(reviewBoardService.findByUserNoAndProductNo(userNo,)!=null) {
				dto.setReviewStatus(1);
			}else {
				dto.setReviewStatus(0);
			}
			*/
			ordersDtoList.add(dto);
		}
		model.addAttribute("ordersList",orderList);
		return "my-account-orders";
	}
	
	
	//orderform
	@PostMapping("orderView")
	public String orderView(@RequestParam("selectedItems") List<Long> selectedItems ,HttpSession session,Model model) throws Exception{
		
		Long userNo = (Long) session.getAttribute("userNo");
		if (userNo == null) {
			throw new Exception("로그인 하세요.");
		}
		
		List<Cart> selectedCartList = new ArrayList<>();
	    for (Long cartNo : selectedItems) {
	        Cart cart = cartService.findByCartNo(cartNo);
	        if (cart != null) {
	            selectedCartList.add(cart);
	        }
	    }
	    List<CartDto> cartDtos= new ArrayList<>();
	    for (Cart cart : selectedCartList) {
	    	CartDto temp = CartDto.toDto(cart);
	    	Product tempProduct = productService.findByProductNo(temp.getProductNo());
	    	temp.setProduct(tempProduct);
			cartDtos.add(temp);
		}
	    
	    Integer totalPrice=0;
	    
	    for (Cart cart : selectedCartList) {
	    	totalPrice=totalPrice+cart.getProduct().getProductPrice()*cart.getCartQty();
		}
		
		Userinfo userinfo=userInfoService.findUserByNo(userNo);
		List<Coupon> coupons = userinfo.getCoupons();
		List<CouponDto> couponDtos=new ArrayList<>();  
		for (Coupon coupon : coupons) {
			couponDtos.add(CouponDto.toDto(coupon));
		}
		//Integer totalPrice=cartService.cartTotalPrice(userNo);
		
		System.out.println("?????"+couponDtos);
		
		//List<Cart> carts = cartService.findAllCartByUserId(userNo);
		System.out.println(">>>>>>>>>>>>>>>>"+selectedCartList);
		System.out.println(">>>>>>>>>>>>>>>>"+selectedCartList.get(0).getUserinfo());
		System.out.println(">>>>>>>>>>>>>>>>"+selectedCartList.get(0).getProduct());
		/*
		List<CartOrderViewDto> cartDtos = new ArrayList<>();
		for (Cart cart : carts) {
			cartDtos.add(CartOrderViewDto.toDto(cart));
		} 
		for (CartOrderViewDto cartDto : cartDtos) {
			System.out.println("<<<<<<<<<<<"+cartDto.getProductNameDto().getProductName());
		}
		*/  
	    //user정보가지고있는 dto 필요 Userinfo
	    
	    //model.addAttribute("user",UserOrderViewDto.toDto(userinfo));//userinfo는 서비스로 찾는지 의문
	    model.addAttribute("cartList",cartDtos);
	    model.addAttribute("user",userinfo );
	    model.addAttribute("coupons",couponDtos);
	    model.addAttribute("cartTotalPrice",totalPrice);
		  
		 
		return "checkout2";
	}
		//orderitemList 조회
		@GetMapping("orderItemiew")
		public String orderItemiew(@RequestParam(name = "orderNo")Long orderNo,HttpSession session,Model model) throws Exception{
			if (session.getAttribute("userNo") == null) {
				throw new Exception("로그인 하세요.");
			}
			Orders orders=orderService.findOrderByNo(orderNo);
		List<OrderItem> orderItems=orders.getOrderItems();
		List<OrderItemDto> orderitemDtos= new ArrayList<>();
		for (OrderItem orderItem : orderItems) {
			orderitemDtos.add(OrderItemDto.toDto(orderItem));
		}
		model.addAttribute("orderItemList",orderitemDtos);
		
			return "orderItemView";
		}
		
		
		@GetMapping("/dateByOrder")
		public String dateByOrder(@RequestParam("startDate")Date startDate,@RequestParam("endDate") Date endDate, HttpSession session,Model model){
			List<OrdersDto> ordersListDto = new ArrayList<OrdersDto>();
			System.out.println(">>>>>>>>파라미터 왔을까");
			Long userNo=(Long)session.getAttribute("userNo");
			List<Orders> ordersList = orderService.findAllByOrdersByOrderDateByUserNo(startDate, endDate, userNo);
			
			for (Orders orders : ordersList) {
				OrdersDto ordersDto = OrdersDto.toDto(orders);
				ordersListDto.add(ordersDto);
			}
			
			model.addAttribute("ordersList",ordersListDto);
			model.addAttribute("pageStatus",0);
			return "my-account-orders";
			
		}
		
		
		
		
	
}
