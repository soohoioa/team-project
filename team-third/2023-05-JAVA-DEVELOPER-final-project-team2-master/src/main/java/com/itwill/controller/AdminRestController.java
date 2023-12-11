package com.itwill.controller;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.dto.CenterDto;
import com.itwill.dto.ProductListDto;
import com.itwill.dto.VolunteerDto;
import com.itwill.entity.Center;
import com.itwill.entity.Pet;
import com.itwill.entity.Product;
import com.itwill.entity.Volunteer;
import com.itwill.service.AdoptService;
import com.itwill.service.CenterService;
import com.itwill.service.PetService;
import com.itwill.service.ProductService;
import com.itwill.service.VisitService;
import com.itwill.service.VolunteerService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;

@RestController
public class AdminRestController {

	@Autowired
	private CenterService centerService;
	@Autowired
	private ProductService productService;
	@Autowired
	private AdoptService adoptService;
	@Autowired
	private VolunteerService volunteerService;
	@Autowired
	private PetService petService;
	@Autowired
	private VisitService visitService;

	/******************************* Adopt ************************************/

	@Operation(summary = "no로 삭제")
	@DeleteMapping("/{adoptNo}")
	public ResponseEntity<Map> deleteAdopt(@PathVariable(value = "adoptNo") Long adoptNo) throws Exception {
		try {
			adoptService.deleteAdopt(adoptNo);
			return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("adoptNo", adoptNo));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@Operation(summary = "봉사삭제")
	@DeleteMapping("/{volunteerNo}")
	public ResponseEntity<Map> VolunteerDelete(@PathVariable(name = "volunteerNo") Long volunteerNo) throws Exception {
		volunteerService.deleteVolunteer(volunteerNo);
		return ResponseEntity.status(HttpStatus.OK).body(new HashMap<>());
	} // DELETE

	@Operation(summary = "봉사 부분 업데이트")
	@PutMapping("/{volunteerNo}")
	public ResponseEntity<VolunteerDto> updateVolunteer(@PathVariable(name = "volunteerNo") Long volunteerNo,
			@RequestBody VolunteerDto dto) throws Exception {
		Volunteer existingVolunteer = volunteerService.findByVolunteerNo(volunteerNo);

		if (existingVolunteer == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			if (dto.getVolunteerTime() != null) {
				existingVolunteer.setVolunteerTime(dto.getVolunteerTime());
			}
			if (dto.getVolunteerDate() != null) {
				existingVolunteer.setVolunteerDate(dto.getVolunteerDate());
			}
			if (dto.getVolunteerStatus() != null) {
				existingVolunteer.setVolunteerStatus(dto.getVolunteerStatus());
			}
			if (dto.getCenterNo() != null) {
				Center center = centerService.findByCenterNo(dto.getCenterNo());
				existingVolunteer.setCenter(center);
			}

			volunteerService.updateVolunteer(existingVolunteer);
			VolunteerDto updatedVolunteerDto = VolunteerDto.toDto(existingVolunteer);
			return new ResponseEntity<>(updatedVolunteerDto, HttpStatus.OK);
		}
	} // UPDATE

	/******************************* Pet ************************************/

	@Operation(summary = "펫 삭제")
	@DeleteMapping("/{petNo}")
	public ResponseEntity<Map> petDelete(@PathVariable(name = "petNo") Long petNo) throws Exception {
		Optional<Pet> petOptional = Optional.of(petService.petFindById(petNo));
		if (petOptional.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
		petService.petRemove(petNo);
		return ResponseEntity.status(HttpStatus.OK).body(new HashMap<>());
	}

	/******************************* Product ************************************/

	@Operation(summary = "상품 삭제 (관리자)")
	@DeleteMapping("/{no}")
	// delete
	public ResponseEntity deleteProduct(@PathVariable(name = "no") Long no, HttpSession session) throws Exception {

		HttpHeaders httpHeaders = new HttpHeaders();

		productService.deleteProduct(no);
		return new ResponseEntity(httpHeaders, HttpStatus.OK);
	}

	@Operation(summary = "상품 검색")
	@PostMapping("/admin/products/search")
	public ResponseEntity<List<ProductListDto>> search(@RequestBody ProductListDto productdto) {
		List<ProductListDto> productListDto = new ArrayList<ProductListDto>();
		List<Product> findList = productService.findByContains(productdto.getProductName());

		for (Product product : findList) {
			ProductListDto productDto = ProductListDto.toDto(product);
			productListDto.add(productDto);
		}

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		return new ResponseEntity<List<ProductListDto>>(productListDto, httpHeaders, HttpStatus.OK);
	}

	/******************************* center ************************************/
//	@PostMapping("/createCenter") 실패..
//    public ResponseEntity<CenterDto> createCenter(@RequestBody CenterDto centerDto) {
//        try {
//            // 센터 서비스를 통해 센터를 생성하고 결과를 반환
//            Center createdCenter = centerService.createCenter(centerDto.toEntity(centerDto));
//            return new ResponseEntity<>(CenterDto.toDto(createdCenter), HttpStatus.CREATED);
//        } catch (Exception e) {
//            e.printStackTrace(); // 또는 로깅 프레임워크를 사용하여 로그에 기록
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
	@Operation(summary = "센터 및 관련 펫 삭제")
	@DeleteMapping("/pet/center/{centerNo}")
	public ResponseEntity<Map> deleteCenter(@PathVariable(name = "centerNo") Long centerNo) {
		try {
			petService.deleteByCenterNo(centerNo);
			centerService.deleteCenter(centerNo);
			return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("centerNo", centerNo));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	@Operation(summary = "센터번호를 가지고 있는 펫이 없는경우 센터삭제")
	@DeleteMapping("/center/{centerNo}")
	public ResponseEntity<Map> deleteCenter2(@PathVariable(name = "centerNo") Long centerNo) {
	    try {
	        // 센터 삭제 로직 구현
	        centerService.deleteCenter(centerNo);
	        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("centerNo", centerNo));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}

}
