package com.itwill.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.itwill.dto.PetDto;
import com.itwill.entity.Center;
import com.itwill.entity.Pet;
import com.itwill.entity.ReportBoard;
import com.itwill.entity.Userinfo;
import com.itwill.service.CenterService;
import com.itwill.service.PetService;
import com.itwill.service.ReportBoardService;
import com.itwill.service.UserInfoService;

import jakarta.servlet.http.HttpSession;



@Controller
public class Controller1 {
	
	@Autowired
	private PetService petService;
	@Autowired
	private ReportBoardService reportBoardService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private CenterService centerService;
	
	/*
	@GetMapping(value = "/")
	public String main() {
		return "index";
	}
<<<<<<< HEAD
	 */

	
	@GetMapping(value = "/myAccount")
	public String myAccount() {
		return "my-account";
	}
	
	@GetMapping(value = "/reportview")
	public String reportview() {
		return "reportBoardView";
	}
	
	@GetMapping(value = "/")
	public String indexPetList(Model model) {
		
		List<PetDto> petDtoList = new ArrayList<>();
		List<Pet> petList = petService.petFindAll();
		
		for (Pet pet : petList) {
			PetDto temp = PetDto.toDto(pet);
			Center findcenter = centerService.findByCenterNo(temp.getCenterNo());
			temp.setCenter(findcenter);
			petDtoList.add(temp);
		}
		model.addAttribute("petList", petDtoList);
		List<ReportBoard> reportBoards = reportBoardService.findAll();
		model.addAttribute("reportBoardList", reportBoards);

		return "index";
	}
	
	
	@GetMapping(value = "/index")
	public String indexPetList2(Model model) {
		
		List<PetDto> petDtoList = new ArrayList<>();
		List<Pet> petList = petService.petFindAll();
		
		for (Pet pet : petList) {
			PetDto temp = PetDto.toDto(pet);
			Center findcenter = centerService.findByCenterNo(temp.getCenterNo());
			temp.setCenter(findcenter);
			petDtoList.add(temp);
		}
		model.addAttribute("petList", petDtoList);
		List<ReportBoard> reportBoards = reportBoardService.findAll();
		model.addAttribute("reportBoardList", reportBoards);
		
		return "index";
	}

	
	
}
