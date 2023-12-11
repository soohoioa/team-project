package com.itwill.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.itwill.entity.Center;

import jakarta.transaction.Transactional;
@SpringBootTest
class CenterServiceImplTest {
	
	@Autowired
	CenterService centerService;
	
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void insertCenter() {
		
	
		Center center = Center.builder()
				.centerNo(null)
				.centerName("그냥보호소")
				.centerPhoneNumber("010-1111-1111")
				.centerOpenCloseTime("09:00~17:00")
				.centerLocal("경상도")
				.build();
		centerService.createCenter(center);
		
	}
	
	@Test
	@Disabled
	@Transactional
	void  selectCenter() {
		Center selectCenter = centerService.findByCenterNo(6L);
		System.out.println(selectCenter);
	}

	@Test
	@Disabled
	@Transactional
	@Rollback(value = false)
	void deleteCenter() throws Exception{
		centerService.deleteCenter(6L);
	}
	@Test
	@Disabled
	@Transactional
	@Rollback(value = false)
	void updateCenter() {
		Center updateCenter = centerService.findByCenterNo(1L);
		updateCenter.setCenterName("뭐로하지보호소");
		System.out.println(updateCenter);
	}
	@Test
	@Transactional
	@Rollback(value = false)
	//@Disabled
	void findByName() {
		List<Center> findCenters = centerService.findByName("뭐로");
		System.out.println(findCenters);
	}
}
