package com.itwill.controller;

import java.nio.charset.Charset;
import java.util.ArrayList;
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

import com.itwill.dto.PetDto;
import com.itwill.dto.ProductInsertDto;
import com.itwill.entity.Center;
import com.itwill.entity.Pet;
import com.itwill.service.CenterService;
import com.itwill.service.PetService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/pet")
public class PetRestController {

	@Autowired
	private PetService petService;
	@Autowired
	private CenterService centerService;
	
	/*
	 * @Operation(summary = "펫 리스트")
	 * 
	 * @GetMapping() public ResponseEntity<List<PetDto>> petList(){ List<PetDto>
	 * petDtoList = new ArrayList<>(); List<Pet> petList = petService.petFindAll();
	 * for (Pet pet : petList) { petDtoList.add(PetDto.toDto(pet)); }
	 * 
	 * 
	 * return ResponseEntity.status(HttpStatus.OK).body(petDtoList); }
	 */
	
	/*
	 * @Operation(summary = "펫 등록")
	 * 
	 * @PostMapping("/insert_action") public ResponseEntity<PetDto>
	 * petSave(@RequestBody PetDto petdto){
	 * petService.petSave(petdto.toEntity(petdto)); HttpHeaders httpHeaders = new
	 * HttpHeaders(); httpHeaders.setContentType(new MediaType("application",
	 * "json", Charset.forName("UTF-8")));
	 * 
	 * return new ResponseEntity<PetDto>(petdto, httpHeaders, HttpStatus.CREATED); }
	 */


@Operation(summary = "펫 삭제")	
@DeleteMapping("/{petNo}")
public ResponseEntity<Map> petDelete(@PathVariable(name = "petNo") Long petNo) throws Exception{
	Optional<Pet> petOptional = Optional.of(petService.petFindById(petNo));
	if(petOptional.isEmpty()) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	
		}
		petService.petRemove(petNo);
		return ResponseEntity.status(HttpStatus.OK).body(new HashMap<>());
	}

@Operation(summary = "펫 업데이트")
@PutMapping("/{petNo}")
public ResponseEntity<PetDto> petUpdate(@PathVariable(name = "petNo") Long petNo,@RequestBody PetDto petdto) throws Exception{
	Optional<Pet> petOptional = Optional.of(petService.petFindById(petNo));
	Center center = centerService.findByCenterNo(petdto.getCenterNo());
	if(petOptional.isPresent()) {
		Pet pet1 = petOptional.get();
		pet1.setPetLocal(petdto.getPetLocal());
		pet1.setPetType(petdto.getPetType());
		pet1.setPetGender(petdto.getPetGender());
		pet1.setPetRegisterDate(petdto.getPetRegisterDate());
		pet1.setPetFindPlace(petdto.getPetFindPlace());
		pet1.setPetCharacter(petdto.getPetCharacter());
		pet1.setCenter(center);
		
		
		petService.petUpdate(pet1);
		return ResponseEntity.status(HttpStatus.OK).body(PetDto.toDto(pet1));
		
	}else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
@Operation(summary = "펫 리스트 최신등록순")	
@GetMapping()
public ResponseEntity<List<PetDto>> petDescList(){
		List<PetDto> petDtoList = new ArrayList<>();
		List<Pet> petList = petService.findAllByOrderBypetNoDesc();
		for (Pet pet : petList) {
			petDtoList.add(PetDto.toDto(pet));
		}

		return ResponseEntity.status(HttpStatus.OK).body(petDtoList);
	
	
}
/*
 * @Operation(summary = "펫타입 리스트")
 * 
 * @GetMapping("/{petType}") public ResponseEntity<List<PetDto>>
 * petTypeList(@RequestParam(name = "petType")String petType){ List<PetDto>
 * petDtoList = new ArrayList<>(); List<Pet> petList =
 * petService.findAllByOrderBypetType(petType); for (Pet pet : petList) {
 * petDtoList.add(PetDto.toDto(pet)); }
 * 
 * return ResponseEntity.status(HttpStatus.OK).body(petDtoList);
 * 
 * 
 * }
 */

}
