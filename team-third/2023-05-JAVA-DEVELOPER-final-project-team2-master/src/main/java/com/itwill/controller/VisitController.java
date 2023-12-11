package com.itwill.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.dto.OrdersDto;
import com.itwill.dto.VisitDto;
import com.itwill.entity.Center;
import com.itwill.entity.Orders;
import com.itwill.entity.Userinfo;
import com.itwill.entity.Visit;
import com.itwill.entity.Volunteer;
import com.itwill.service.CenterService;
import com.itwill.service.UserInfoService;
import com.itwill.service.VisitService;

import jakarta.servlet.http.HttpSession;

@Controller
public class VisitController {

	@Autowired
	private VisitService visitService;
	@Autowired
	private CenterService centerService;
	@Autowired
	private UserInfoService userInfoService;

	@GetMapping(value = "/visit", params = "centerNo") //
	public String insert_action(Model model, @RequestParam Long centerNo) throws Exception {
		Center center = centerService.findByCenterNo(centerNo);
		model.addAttribute("center", center);
		return "visit";
	}

	@PostMapping("/create-visit")
	public String createvisit(@RequestParam("visitDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date visitDate,
			@RequestParam("visitTime") int selectedHour, @RequestParam Long centerNo, HttpSession session, Model model)
			throws Exception {
		Long userNo = (Long) session.getAttribute("userNo");

		if (userNo != null) {
			Visit visit = new Visit();
			visit.setVisitDate(visitDate);
			visit.setVisitStatus("견학신청");
			visit.setVisitTime(selectedHour);

			Center center = centerService.findByCenterNo(centerNo);
			Userinfo userinfo = userInfoService.findUserByNo(userNo);
			visit.setUserinfo(userinfo);
			visit.setCenter(center);

			model.addAttribute("userinfo", userinfo);

			model.addAttribute("message", "신청이 완료되었습니다.");
		} else {

			model.addAttribute("error", "로그인이 필요합니다.");
		}
		return "visitByUserNo"; // my-account 페이지로 이동

	}
	/*
	 * // 관리자 견학리스트 전체출력
	 * 
	 * @GetMapping("/visitList") public String centerList(Model model) { List<Visit>
	 * visits = visitService.selectAllVisits(); model.addAttribute("visits",
	 * visits); return "my-account"; }
	 */

	// 관리자,유저 견학리스트 전체출력
	@GetMapping("/visitByUserNo")
	public String findByUserNoVisitList(Model model, HttpSession session) throws Exception {
		Long userNo = (Long) session.getAttribute("userNo");
		Userinfo user = userInfoService.findUserByNo(userNo);
		List<Visit> visitList;

		if (user.getUserNo().equals(57L)) {
			// userNo가 57인 경우 전체 견학 리스트를 가져옴
			visitList = visitService.selectAllVisits();
		} else {
			// 그 외의 경우 로그인한 회원의 견학 리스트를 가져옴
			visitList = visitService.getVisitsByUserNo(user.getUserNo());
		}

		// 람다 표현식 visitList에서 visit1, visit2 두 개의 요소를 나타내며 compareTo로 두 개의 번호를 비교해서 정렬
		visitList.sort((visit1, visit2) -> visit2.getVisitDate().compareTo(visit1.getVisitDate()));
		model.addAttribute("visitList", visitList);
		return "my-account-visit";
	}

	// 관리자
	@GetMapping("/visitList")
	public String findOrders(Model model) throws Exception {
	    List<Visit> visitList = visitService.selectAllVisits();
	    
	    List<VisitDto> visitDto = new ArrayList<>();
	    for (Visit visit : visitList) {
	        Userinfo userinfo = visit.getUserinfo();
	        VisitDto dto = VisitDto.toDto(visit);
	        dto.setUserinfo(userinfo);
	        visitDto.add(dto);
	    }

	    model.addAttribute("visitList", visitDto);
	    return "my-account-visit";
	}


	@PutMapping("/update-visit")
	public String updateVolunteer(@ModelAttribute Visit visit, @RequestParam(value = "visitNo") Long visitNo,
			HttpSession session, Model model) throws Exception {
		Long userNo = (Long) session.getAttribute("userNo");
		if (userNo != null) {
			Visit findVisit = visitService.findByVisitNo(visitNo);

			findVisit.setVisitDate(visit.getVisitDate());
			findVisit.setVisitTime(visit.getVisitTime());

			visitService.updateVisit(findVisit);
		}
		return "my-account-visit";
	}

	@GetMapping("/visitUpdate")
	public String getVisitPage(@RequestParam Long visitNo, @RequestParam Long centerNo, Model model) throws Exception {

		Visit visit = visitService.findByVisitNo(visitNo);
		Center center = centerService.findByCenterNo(centerNo);

		model.addAttribute("visit", visit);
		model.addAttribute("visitNo", visitNo);
		model.addAttribute("center", center);

		return "visitUpdate";
	}
}
