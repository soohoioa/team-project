package com.itwill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.repository.ReportBoardRepository;
import com.itwill.service.ReportBoardService;

@EnableScheduling
@SpringBootApplication
public class TeamprojectAnimalcareApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(TeamprojectAnimalcareApplication.class, args);
		
		
	}

}
