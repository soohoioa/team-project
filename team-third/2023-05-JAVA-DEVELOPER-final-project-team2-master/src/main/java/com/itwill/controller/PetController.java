package com.itwill.controller;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.sym.Name;
import com.itwill.dto.PetDto;
import com.itwill.dto.UserWriteActionDto;
import com.itwill.entity.Adopt;
import com.itwill.entity.Center;
import com.itwill.entity.Pet;
import com.itwill.entity.Userinfo;
import com.itwill.repository.AdoptRepository;
import com.itwill.service.AdoptService;
import com.itwill.service.CenterService;
import com.itwill.service.PetService;
import com.itwill.service.UserInfoService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;
@Controller
public class PetController {
@Autowired
PetService petService;
@Autowired
UserInfoService userInfoService;
@Autowired
AdoptRepository adoptRepository;
@Autowired
AdoptService adoptService;
@Autowired
CenterService centerService;
//@Autowired
//팻 등록
/*
 * @PostMapping("/insert_action") public String insert_action(@RequestBody
 * PetDto petDto) throws Exception {
 * 
 * petService.petSave(petDto.toEntity(petDto));
 * 
 * return "pet-list"; }
 */
	
/*
 * @GetMapping("/petinsertform") public String petinsertform(Model model) throws
 * Exception {
 * 
 * List<Center> centers=centerService.findAllCenters();
 * 
 * model.addAttribute("petCenter",centers); return "pet_insert_form"; }
 */
	

	
	//펫 페이징 리스트
	//center dto가져와야함.
	@GetMapping("/petListPage")
	public String petList(Model model,@PageableDefault(page =0,size = 5,sort = "petNo",direction = Sort.Direction.DESC) Pageable page)throws Exception {
		int pag = page.getPageNumber();
		int size = page.getPageSize();
		
		Pageable pageable = PageRequest.of(pag, size, Sort.by(Sort.Order.desc("petNo")));
		
		List<PetDto> petDtoList = new ArrayList<>();
		
		Page<Pet> petList= petService.petFindAllPage(pageable);
		
		/*
		for (Pet pet : petList) {
			List<Adopt> petAdopt = adoptRepository.findAdoptByPetNo(pet.getPetNo());
			for (Adopt adopt : petAdopt) {
				
			}
			
				if(petAdopt==null) {
					petDtoList.add(PetDto.toDto(pet));
				}else {
					if(!petAdopt.getAdoptStatus().equals("입양완료")) {
						petDtoList.add(PetDto.toDto(pet));
					}
				}
			}
		*/

		model.addAttribute("petList",petList);
		model.addAttribute("pagingstatus",1);
		
		return "pet-list" ;
	}
		
		
	
	
	//펫 리스트
		//center dto가져와야함.
		@GetMapping("/petList")
		public String petList(Model model) {
			List<PetDto> petDtoList = new ArrayList<>();
			List<Pet> petList = petService.petFindAll();
			for (Pet pet : petList) {
				petDtoList.add(PetDto.toDto(pet));
			}
			
			
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>"+petDtoList.get(0).getPetType());
			model.addAttribute("petList",petDtoList);
			return "pet-list" ;
		}
	
	
	
	//펫 삭제 관리자만
	@GetMapping("/deletepet")
	public String delete_action(@RequestParam(name = "petNo") Long petNo,HttpSession session) throws Exception{
		Optional<Pet> petOptional = Optional.of(petService.petFindById(petNo));
		Long userNo=(Long)session.getAttribute("userNo");
		Userinfo userinfo=userInfoService.findUserByNo(userNo);
		if(petOptional.isEmpty()) {
			throw new Exception("존재하지 않는 동물입니다.");
		
			}
		if(userinfo.getUserName().equals("관리자")) {
			
			petService.petRemove(petNo);
		}else {
			throw new Exception("올바르지 않은 경로입니다.");
		}
		
		return "redirect:petListPage";
	}
	//펫 업데이트
	@PostMapping("/update_action")
	public String update_action(@RequestBody PetDto updatepetDto) throws Exception{
		Optional<Pet> petOptional = Optional.of(petService.petFindById(updatepetDto.getPetNo()));
		
		Center center=centerService.findByCenterNo(updatepetDto.getCenterNo());
		if(petOptional.isPresent()) {
			Pet pet1 = petOptional.get();
			pet1.setPetLocal(updatepetDto.getPetLocal());
			pet1.setPetType(updatepetDto.getPetType());
			pet1.setPetGender(updatepetDto.getPetGender());
			pet1.setPetRegisterDate(updatepetDto.getPetRegisterDate());
			pet1.setPetFindPlace(updatepetDto.getPetFindPlace());
			pet1.setPetCharacter(updatepetDto.getPetCharacter());
			pet1.setCenter(center);
			
			
			
			
			petService.petUpdate(pet1);
	}
		return "pet-list";
	
	}
	
	@Operation(summary = "펫타입 리스트")	
	@GetMapping("/pets")
	public String petTypeList(@RequestParam(name = "petType")String petType,Model model){
		
		//int pag = page.getPageNumber();
		//int size = page.getPageSize();
		
		//Pageable pageable= PageRequest.of(pag,size);	
		
		
		
		List<PetDto> petDtoList = new ArrayList<>();
			
			
			
			List<Pet> petList = petService.findAllByOrderBypetType(petType);
			for (Pet pet : petList) {
				petDtoList.add(PetDto.toDto(pet));
			}

		model.addAttribute("petList",petList);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>펫타입"+petDtoList);
		model.addAttribute("pagingstatus",0);
		return "pet-list";
	}
	
	//펫 지역 리스트
	@GetMapping("/petLocal")
	public String petLocalList(@RequestParam(name = "petLocal")String petLocal,Model model,@PageableDefault(page =0,size = 5,sort = "pet_no",direction = Sort.Direction.DESC) Pageable page){
		//int pag = page.getPageNumber();
		//int size = page.getPageSize();
		
		//Pageable pageable= PageRequest.of(pag,size);	
		
		
		List<PetDto> petDtoList = new ArrayList<>();
			List<Pet> petList = petService.findAllByPetLocal(petLocal);
			for (Pet pet : petList) {
				petDtoList.add(PetDto.toDto(pet));
			}

		model.addAttribute("petList",petList);
		model.addAttribute("pagingstatus",0);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>"+petDtoList);
		return "pet-list";
	}

	
	//펫 페이징 리스트
	//center dto가져와야함.
	@GetMapping("/petTotal")
	public String petTotal(@RequestParam(name = "petType")String petType,@RequestParam(name = "petLocal")String petLocal,Model model,@PageableDefault(page =0,size = 5,sort = "pet_no",direction = Sort.Direction.DESC) Pageable page)throws Exception {
		//int pag = page.getPageNumber();
		//int size = page.getPageSize();
		
		//Pageable pageable= PageRequest.of(pag,size);
		
		List<PetDto> petDtoList = new ArrayList<>();
		
		List<Pet> petList= petService.findAllByPetTypeByPetLocal(petType,petLocal);
		
		/*
		for (Pet pet : petList) {
			Adopt petAdopt = adoptRepository.findAdoptByPetNo(pet.getPetNo());
				if(petAdopt==null) {
					petDtoList.add(PetDto.toDto(pet));
				}else {
					if(!petAdopt.getAdoptStatus().equals("입양완료")) {
						petDtoList.add(PetDto.toDto(pet));
					}
				}
			}
		*/
		
		model.addAttribute("petList",petList);
		model.addAttribute("pagingstatus",0);
		return "pet-list" ;
	}
	
	
	

}
