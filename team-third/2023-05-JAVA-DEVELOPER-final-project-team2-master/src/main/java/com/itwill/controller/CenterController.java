package com.itwill.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.dto.CenterDto;
import com.itwill.entity.Center;
import com.itwill.service.CenterService;

@Controller
@RequestMapping()
public class CenterController {

	@Autowired
	CenterService centerService;

	
	//센터리스트 전체출력
	@GetMapping("/centerList")
	public String centerList(Model model) {
	    List<Center> centerList = centerService.findAllCenters();
	    model.addAttribute("centerList", centerList);
	    return "center-list";
	}


    //센터이름검색
    @GetMapping("searchCenterName")
    public String searchCenterName(@RequestParam("centerName") String centerName,Model model ) {
    	List<Center> searchName = centerService.findByName(centerName);
    	 model.addAttribute("searchName", searchName);
		return "center-list";
    	
    }
    
}
