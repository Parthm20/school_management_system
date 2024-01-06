package com.sms.services.home;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sms.dto.TeacherDto;
import com.sms.entities.Teacher;
import com.sms.repositories.TeacherRepository;

@Service
public class HomeService {

	private final TeacherRepository teacherRepository;
	
	
	public HomeService(TeacherRepository teacherRepository) {
		super();
		this.teacherRepository = teacherRepository;
	}


	public List<TeacherDto> getAllTeachers(){
		return teacherRepository.findAll().stream().map(Teacher::getTeacherDto).collect(Collectors.toList());
	}
}
