package com.itwill.controller;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

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

import com.itwill.dto.CenterDto;
import com.itwill.entity.Center;
import com.itwill.service.CenterService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/center")
public class CenterRestController {

	@Autowired
	private CenterService centerService;

	@Operation(summary = "센터추가")
	@PostMapping
	public ResponseEntity<CenterDto> createCenter(@RequestBody CenterDto dto) {
		// 센터 생성
		centerService.createCenter(CenterDto.toEntity(dto));

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		return new ResponseEntity<CenterDto>(dto, httpHeaders, HttpStatus.CREATED);
	}

	@Operation(summary = "센터리스트")
	@GetMapping("/centers")
	public ResponseEntity<List<CenterDto>> centerList() {
		List<Center> centers = centerService.findAllCenters();
		List<CenterDto> centerDtoList = new ArrayList<CenterDto>();
		// 센터리스트 전체출력
		for (Center center : centers) {
			centerDtoList.add(CenterDto.toDto(center));
		}

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		return new ResponseEntity<List<CenterDto>>(centerDtoList, httpHeaders, HttpStatus.OK);

	}

	/*
	 * @Operation(summary = "센터삭제")
	 * 
	 * @DeleteMapping("/{centerNo}") public void CenterDelete(@PathVariable(name =
	 * "centerNo") Long centerNo) { centerService.deleteCenter(centerNo); }
	 */
	@Operation(summary = "센터이름검색")
	@GetMapping("/centers/search")
	public ResponseEntity<List<CenterDto>> searchCentersByName(@RequestParam(name = "name") String name) {
		List<Center> centers = centerService.findAllCenters();
		List<CenterDto> centerDtoList = new ArrayList<CenterDto>();

		for (Center center : centers) {
			String centerName = center.getCenterName();
			if (centerName != null && centerName.equals(name)) { // <NullPointerException시 추가
				centerDtoList.add(CenterDto.toDto(center));
			}
		}

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		if (!centerDtoList.isEmpty()) {
			return new ResponseEntity<>(centerDtoList, httpHeaders, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(httpHeaders, HttpStatus.NOT_FOUND);
		}
	}

	@Operation(summary = "센터번호검색")
	@GetMapping("/centers/search/no")
	public ResponseEntity<List<CenterDto>> searchCentersByNo(@RequestParam(name = "no") Long no) {
		List<Center> centers = centerService.findAllCenters();
		List<CenterDto> centerDtoList = new ArrayList<CenterDto>();

		for (Center center : centers) {
			Long centerNo = center.getCenterNo();
			if (centerNo != null && centerNo.equals(no)) { // <NullPointerException시 추가
				centerDtoList.add(CenterDto.toDto(center));
			}
		}

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		if (!centerDtoList.isEmpty()) {
			return new ResponseEntity<>(centerDtoList, httpHeaders, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(httpHeaders, HttpStatus.NOT_FOUND);
		}
	}

	@Operation(summary = "센터 업데이트")
	@PutMapping("/{centerNo}")
	public ResponseEntity<CenterDto> updateCenter(@PathVariable(name = "centerNo") Long centerNo, @RequestBody CenterDto dto) {
		Center existingCenter = centerService.findByCenterNo(centerNo);

		if (existingCenter != null) {
			// if 문으로 클라이언트가 전달한 필드만 업데이트
			if (dto.getCenterName() != null) {
				existingCenter.setCenterName(dto.getCenterName());
			}
			if (dto.getCenterPhoneNumber() != null) {
				existingCenter.setCenterPhoneNumber(dto.getCenterPhoneNumber());
			}
			if (dto.getCenterLocal() != null) {
				existingCenter.setCenterLocal(dto.getCenterLocal());
			}
			if (dto.getCenterOpenCloseTime() != null) {
				existingCenter.setCenterOpenCloseTime(dto.getCenterOpenCloseTime());
			}

			centerService.updateCenter(existingCenter); // 업데이트된 센터 정보 저장
			return new ResponseEntity<>(CenterDto.toDto(existingCenter), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
