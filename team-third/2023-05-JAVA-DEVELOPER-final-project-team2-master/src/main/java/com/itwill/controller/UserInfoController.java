package com.itwill.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.dao.OrderItemDao;
import com.itwill.dto.AdminUserListDto;
import com.itwill.dto.PetDto;
import com.itwill.dto.UserWriteActionDto;
import com.itwill.entity.MyPet;
import com.itwill.entity.OrderItem;
import com.itwill.entity.Orders;
import com.itwill.entity.Pet;
import com.itwill.entity.Userinfo;
import com.itwill.entity.Wish;
import com.itwill.service.CartService;
import com.itwill.service.MyPetService;
import com.itwill.service.OrderItemService;
import com.itwill.service.OrderService;
import com.itwill.service.PetService;
import com.itwill.service.UserInfoService;
import com.itwill.service.WishService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserInfoController {

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
	private PetService petService;
	
	
	// 관리자가 유저리스트 볼때
	@GetMapping(value = "/userList")
	public String list(Model model) throws Exception {
		List<Userinfo> userList = userInfoService.findUserList();
		model.addAttribute("userList", userList);
		return "userList";
	}
	
	
	
	//마이페이지 이동
	@GetMapping(value="/userinfo")
	public String view(Model model, HttpSession session) throws Exception{
		Long userNo = (Long)session.getAttribute("userNo");
		if(userNo==null) {
			throw new Exception("로그인을 해주세요");
		}
		Userinfo userinfo = userInfoService.findUserByNo(userNo);
		model.addAttribute("userinfo", userinfo);
		
		return "my-account-userinfo";
		
	}
	
	@PostMapping("/userUpdate")
	public String update(Model model, HttpSession session, UserWriteActionDto dto ) throws Exception {
		Long userNo = (Long) session.getAttribute("userNo");
		Userinfo userinfo = userInfoService.findUserByNo(userNo);
		userinfo.setUserName(dto.getUserName());
		userinfo.setUserPassword(dto.getUserPassword());
		userinfo.setUserPhoneNumber(dto.getUserPhoneNumber());
		userinfo.setUserAddress(dto.getUserAddress());
		userInfoService.update(userinfo);
		session.setAttribute("userName", dto.getUserName());
		model.addAttribute("userinfo", userinfo);
		return "my-account-userinfo";
	}
	
	/*
	@GetMapping(value = "userUpdate")
	public String update(Model model , HttpSession session , @RequestParam String userName , @RequestParam String userPassword,
			@RequestParam String userPhoneNumber,@RequestParam String userAddress) throws Exception{
		Long userNo = (Long)session.getAttribute("userNo");
		Userinfo userinfo = userInfoService.findUserByNo(userNo);
		userinfo.setUserName(userName);
		userinfo.setUserPassword(userPassword);
		userinfo.setUserPhoneNumber(userPhoneNumber);
		userinfo.setUserAddress(userAddress);
		userInfoService.update(userinfo);
		session.setAttribute("userName", userName);
		model.addAttribute("userinfo", userinfo);
		
		return "my-account-userinfo";
	}
	*/
	@GetMapping("userDelete")
	public String delete(HttpSession session) throws Exception {
		Long userNo = (Long)session.getAttribute("userNo");
		
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
		session.invalidate();
		
		return "redirect:index";
	}
	
	@GetMapping(value="/logout")
	public String logout(HttpSession session, Model model) {
		session.invalidate();
		/*
		 * List<PetDto> petDtoList = new ArrayList<>();
		List<Pet> petList = petService.petFindAll();
		for (Pet pet : petList) {
			petDtoList.add(PetDto.toDto(pet));
			
			model.addAttribute("petList", petDtoList);
		}
			*/
		return "redirect:index";
	}
	
	//아이디 찾기,비밀번호 찾기 폼으로 이동
	@GetMapping("/finduserinfo")
	public String finduserinfo() {
		return "finduserinfo";
	}
	
	//아이디 찾은 리스트 폼 이동
	@GetMapping("/findId")
	public String findId() {
		return "findId";
	}
	
	//
	@GetMapping("/loginPopUp")
	public String loginPopUp() {
		return "loginPopUp";
	}
	
}
