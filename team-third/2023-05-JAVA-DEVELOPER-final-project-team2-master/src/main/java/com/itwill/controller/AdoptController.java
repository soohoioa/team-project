package com.itwill.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwill.dto.AdoptDto;
import com.itwill.entity.Adopt;
import com.itwill.entity.Center;
import com.itwill.entity.Pet;
import com.itwill.entity.Userinfo;
import com.itwill.service.AdoptService;
import com.itwill.service.CenterService;
import com.itwill.service.PetService;
import com.itwill.service.UserInfoService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdoptController {

	@Autowired
	private AdoptService adoptService;
	@Autowired
	private PetService petService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private CenterService centerService;
	// 입양신청
	@GetMapping(value = "/adopt")
	public String apply(Model model, @RequestParam(name = "petNo") Long petNo ) {
		Pet pet = petService.petFindById(petNo);
		Center center=pet.getCenter();
		model.addAttribute("pet", pet);
		//Center center=centerService.findByCenterNo(centerNo);
		model.addAttribute("center", center);
		return "adopt";
	}


	@PostMapping("/create-adopt")
	public String createAdopt(@RequestParam("adoptDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date adoptDate,
			@RequestParam("adoptTime") int selectedHour, @RequestParam Long petNo, Model model, HttpSession session) throws Exception {
		Long userNo = (Long)session.getAttribute("userNo");
		
	
		if(userNo!=null) {
		
			Adopt adopt = new Adopt();
			adopt.setAdoptDate(adoptDate);
			adopt.setAdoptTime(selectedHour);

			adopt.setAdoptStatus("입양신청접수");
			
	
			Pet pet = petService.petFindById(petNo);
			Userinfo userinfo=userInfoService.findUserByNo(userNo);
			adopt.setUserinfo(userinfo);
			adopt.setPet(pet);
			adoptService.insertAdopt(adopt);
			model.addAttribute("userinfo", userinfo);
		}else {
			throw new Exception("로그인을 해주세요.");
		}
		return "pet-list";
	}
	
	
	
	
	
	// 입양 리스트 조회(관리자)
	@GetMapping("/adoptList")
	public String adoptList(Model model) {
		
		List<Adopt> adoptList = adoptService.findAdoptList();
		
		model.addAttribute("adoptList", adoptList);
		return "my-account-adopt";
	}

	// userNo에 따른 입양리스트 조회
	@GetMapping("/adoptByUserNo")
	public String findByUserNoAdoptList(Model model,HttpSession session) throws Exception{
		Long userNo=(Long)session.getAttribute("userNo");
		Userinfo user=userInfoService.findUserByNo(userNo);
		
		List<Adopt> adoptList = adoptService.findAdoptsByUserNo(user.getUserNo());
		
		adoptList.sort((v1,v2)->v2.getAdoptDate().compareTo(v1.getAdoptDate()));
		
		model.addAttribute("adoptList", adoptList);
		 model.addAttribute("userNo", userNo);
		System.out.println(">>>>>>>>"+adoptList);
		return "my-account-adopt";
	}

	@GetMapping("/adoptUpdate")
    public String getAdoptPage(@RequestParam Long adoptNo, @RequestParam Long petNo, Model model) {
        // adoptNo를 사용하여 입양 정보를 불러와 모델에 추가
        Adopt adopt = adoptService.findByAdoptNo(adoptNo);
        Pet pet=petService.petFindById(petNo);
        Center center=pet.getCenter();
        
        model.addAttribute("adopt", adopt);
        model.addAttribute("adoptNo", adoptNo);
        model.addAttribute("pet", pet);
        model.addAttribute("center", center);
        
      //  model.addAttribute("userNo", userNo);
        return "adoptUpdate"; // adopt.html 페이지로 이동
    }

	@PutMapping("/update-adopt")
	public String updateAdopt(@ModelAttribute Adopt adopt, @RequestParam(value = "adoptNo") Long adoptNo,
			HttpSession session, Model model) throws Exception {
		// 수정된 정보를 처리하고 수정 완료 페이지로 이동 또는 다시 "my-account.html"로 이동
		Long userNo = (Long) session.getAttribute("userNo");
		if (userNo != null) {
			Adopt findAdopt = adoptService.findByAdoptNo(adoptNo);

			findAdopt.setAdoptDate(adopt.getAdoptDate());
			findAdopt.setAdoptTime(adopt.getAdoptTime());
			adoptService.updateAdopt(findAdopt);
			//model.addAttribute("userNo",userNo);
		}
		return "my-account-adopt"; // 수정 완료 페이지 또는 이동할 페이지 설정

	}
	
	
	
	
	
	
	
	
	
	
	
	// adoptNo 입양 조회
	@GetMapping("/adopt/{adoptNo}")
	public String findByAdoptNoAdopt(Model model, @PathVariable(name = "adoptNo") Long adoptNo) {
		Adopt adopt = adoptService.findByAdoptNo(adoptNo);
		model.addAttribute("adopt", adopt);
		return "my-account";
	}

}
