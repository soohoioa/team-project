package com.itwill.controller;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.itwill.dto.AdoptDto;
import com.itwill.dto.PetDto;
import com.itwill.entity.Adopt;
import com.itwill.entity.Pet;
import com.itwill.entity.Userinfo;
import com.itwill.service.AdoptService;
import com.itwill.service.PetService;
import com.itwill.service.UserInfoService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/adopt")
public class AdoptRestController {

	
	
	@Autowired
	private AdoptService adoptService;
	@Autowired
	private PetService petService;
	@Autowired
	private UserInfoService userInfoService;
	
	@Operation(summary = "입양신청")
	@PostMapping("/create-adopt")
	public ResponseEntity<AdoptDto> insertAdopt(@RequestBody AdoptDto dto, HttpSession session) throws Exception {
		Long userNo = (Long)session.getAttribute("userNo");
		
		if(userNo==null) {
			throw new Exception("로그인 해주세요");
		}
		
		dto.setUserNo(userNo);
		Adopt adopt = AdoptDto.toEntity(dto);
		adoptService.insertAdopt(adopt);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		return new ResponseEntity<AdoptDto>(dto, httpHeaders, HttpStatus.CREATED);
}

	@Operation(summary = "no로 입양신청 보기")
	@GetMapping("/{no}")
	public ResponseEntity<AdoptDto> findByAdoptNo(@PathVariable(value = "no") Long no) throws Exception {
		Adopt findAdopt = adoptService.findByAdoptNo(no);
		if (findAdopt == null) {
			throw new Exception("입양신청을 찾을 수 없습니다.");
		}
		AdoptDto adoptDto = AdoptDto.fromEntity(findAdopt);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		return new ResponseEntity<AdoptDto>(adoptDto, httpHeaders, HttpStatus.OK);

	}

	@Operation(summary = "userNo로 입양신청 찾기")
	@GetMapping("/{userNo}")
	public ResponseEntity<List<AdoptDto>> findAdoptsByUserNo(@PathVariable(name = "userNo") Long userNo) {
		List<Adopt> findAdopt = adoptService.findAdoptsByUserNo(userNo);
		if (findAdopt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
		}
		List<AdoptDto> adoptDtoList = new ArrayList<AdoptDto>();

		for (Adopt adopt : findAdopt) {
			AdoptDto adoptDto = AdoptDto.fromEntity(adopt);
			adoptDtoList.add(adoptDto);
		}

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		return new ResponseEntity<List<AdoptDto>>(adoptDtoList,httpHeaders, HttpStatus.OK);
	}

	@Operation(summary = "no로 삭제")
	@DeleteMapping("/{adoptNo}")
	public ResponseEntity<Map> deleteAdopt(@PathVariable(value  = "adoptNo") Long adoptNo) throws Exception {
		 try {
		        adoptService.deleteAdopt(adoptNo);
		        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("adoptNo", adoptNo));
		    } catch (Exception e) {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		    }
	}

	@Operation(summary = "no로 입양 수정하기")
	@PutMapping("/update-adopt")
	public ResponseEntity<AdoptDto> updateAdopt(@RequestBody AdoptDto dto, HttpServletRequest request, HttpSession session) throws Exception {
		//HttpSession session=request.getSession();
		Long userNo=(Long)session.getAttribute("userNo");
		
		
		/* if (userNo == null) { throw new Exception("로그인이 필요합니다."); } */
		 
		dto.setUserNo(userNo);
		 
		Adopt findAdopt=adoptService.findByAdoptNo(dto.getAdoptNo());
		Userinfo findUser = userInfoService.findUserByNo(userNo);
		findAdopt.setUserinfo(findUser);
		if(findAdopt!=null) {
			if(dto.getAdoptDate()!=null) {
				findAdopt.setAdoptDate(dto.getAdoptDate());
			}
			
			/*
			 * if(dto.getAdoptStatus()!=null) {
			 * findAdopt.setAdoptStatus(dto.getAdoptStatus()); }
			 */
			
			if(dto.getAdoptTime()!=null) {
				findAdopt.setAdoptTime(dto.getAdoptTime());
			}
			
			
			
			Adopt updateAdopt = adoptService.updateAdopt(findAdopt);
			
			
			AdoptDto updatedDto = AdoptDto.fromEntity(updateAdopt);
			//System.out.println(">>>>>>>>>>>>>>>>>>"+userNo);
			
			
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
			
			return new ResponseEntity<AdoptDto>(updatedDto, httpHeaders, HttpStatus.OK);
			
		}else {
			return new ResponseEntity<AdoptDto>(HttpStatus.NOT_FOUND);
		}
		
		
	}

	
	
	
	
	
	@Operation(summary = "전체 리스트 보기")
	@GetMapping("/all")
	public ResponseEntity<List<AdoptDto>> findAllAdopts() {
		List<Adopt> adoptList = adoptService.findAdoptList();
		List<AdoptDto> adoptDtoList = new ArrayList<>();

		for (Adopt adopt : adoptList) {
			AdoptDto adoptDto = AdoptDto.fromEntity(adopt);
			adoptDtoList.add(adoptDto);
		}

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		return new ResponseEntity<>(adoptDtoList, httpHeaders, HttpStatus.OK);
	}


	

}

