package com.sms.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sms.dto.TeacherDto;
import com.sms.services.home.HomeService;
@RestController
public class HomeController {
	
	private final HomeService homeService;
	
	public HomeController(HomeService homeService) {
		super();
		this.homeService = homeService;
	}


	@GetMapping("/teachers")
	public ResponseEntity<List<TeacherDto>> getAllTeachers(){
	List<TeacherDto> allTeachers =	homeService.getAllTeachers();
	return ResponseEntity.ok(allTeachers);
	}

}
