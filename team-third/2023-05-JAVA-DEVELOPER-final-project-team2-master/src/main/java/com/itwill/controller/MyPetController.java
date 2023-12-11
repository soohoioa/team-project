package com.itwill.controller;

import com.itwill.dto.MyPetListDto;
import com.itwill.dto.MypetDto;
import com.itwill.entity.MyPet;
import com.itwill.repository.MyPetRepository;
import com.itwill.service.MyPetService;
import com.itwill.service.UserInfoService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
//@RequestMapping("/userinfo")
public class MyPetController {

	@Autowired
	private MyPetService myPetService;
	@Autowired
	private MyPetRepository myPetRepository;
	@Autowired
	private UserInfoService userInfoService;

	@GetMapping("/mypet")
    public String viewMyPet(HttpSession session, Model model) throws Exception {
        if (session.getAttribute("userNo") == null) {
            throw new Exception("로그인 하세요.");
        }
        Long userNo=(Long)session.getAttribute("userNo");
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = "";
		 
        List<MyPet> myPetList = myPetService.findMyPetListByuserNo(userNo); // 애완 목록을 가져옴
        List<MyPetListDto> myPetListDtos = new ArrayList<MyPetListDto>();
        for (int i = 0; i < myPetList.size(); i++) {
        	formattedDate = myPetList.get(i).getMypetBirthday().format(formatter);
        	MyPetListDto tempMypet = MyPetListDto.builder().build();
        	tempMypet.setMypetBirthday(formattedDate);
        	
        	MyPet myPet =  myPetService.findLeaderMyPet(userNo);
        	String Name  = myPet.getMypetName();
        	
        	
        	tempMypet.setMypetKind(myPetList.get(i).getMypetKind());
        	tempMypet.setMypetName(myPetList.get(i).getMypetName());
        	// 보여지는 No
        	tempMypet.setMypetSequence(i+1);
        	// DB 내 PK
        	tempMypet.setMypetNo(myPetList.get(i).getMypetNo());
        	
        	String leader="";
        	
        	if(tempMypet.getMypetName()==Name) {
        		leader="O";
        	}else {
        		leader="";
        	}
        	tempMypet.setMypetLeader(leader);
        	myPetListDtos.add(tempMypet);
		}
        //model.addAttribute("Name", Name);
        model.addAttribute("myPetList",myPetListDtos);
        
        return "my-account-mypet";
      
    }


  @GetMapping("/registerMyPet") 
  public String registerMyPet(HttpSession session, Model model) {
	  return "registerMyPet"; 
  }
  
  @GetMapping("/updateMyPet") 
  public String updateMyPet(@RequestParam Long mypetNo,HttpSession session, Model model) {
	  model.addAttribute("mypetNo",mypetNo);
	  Long userNo = (Long)session.getAttribute("userNo");
	  MyPet updatepet = myPetRepository.findById(mypetNo).get();
	  MyPet leader = myPetService.findLeaderMyPet(userNo);
	  
	  if(updatepet!=leader) {
		  model.addAttribute("leader",true);
	  }else {
		  model.addAttribute("leader",false);
	  }
	  
	  model.addAttribute("updatepet",updatepet);
	  
	  return "updateMyPet"; 
  }
  
}
/*
 * @GetMapping("/registerMyPet") public String registerMyPet(HttpSession
 * session,@RequestParam("mypetBirthday") Date date, Model model) { // Date 객체를
 * LocalDateTime으로 변환 LocalDateTime localDateTime = date.toInstant()
 * .atZone(ZoneId.systemDefault()) .toLocalDateTime();
 * 
 * // 시간 부분을 자정(00:00:00)으로 설정 localDateTime =
 * localDateTime.withHour(0).withMinute(0).withSecond(0);
 * model.addAttribute("localDateTime", localDateTime); // 결과를 모델에 추가 (예:
 * "resultDateTime"라는 이름으로) return "my-account-mypet"; // 결과를 표시할 뷰 페이지의 이름을 반환
 * } }
 * 
 * @PostMapping("/myPet/{mypetNo}") public String
 * updateMyPet(@PathVariable(value = "mypetNo") Long myPetNo, @RequestBody
 * MypetDto mypetDto, HttpSession session) {
 * 
 * Long userNo=(Long)session.getAttribute("userNo"); //MyPet updatePet = MyPet
 * 
 * return "redirect:/userinfo/mypet"; }
 */
