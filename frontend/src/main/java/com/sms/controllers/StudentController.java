package com.sms.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sms.dto.SingleStudentDto;
import com.sms.dto.StudentDto;
import com.sms.dto.StudentLeaveDto;
import com.sms.services.student.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {
	
	private final StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	
	@GetMapping("/{studentId}")
	public ResponseEntity<SingleStudentDto> getStudentById(@PathVariable Long studentId){
	SingleStudentDto singleStudentDto =	studentService.getStudentById(studentId);
	if(singleStudentDto == null)
		return ResponseEntity.notFound().build();
	return ResponseEntity.ok(singleStudentDto);
	}	
	
	@PostMapping("/leave")
	public ResponseEntity<?> applyLeave (@RequestBody StudentLeaveDto studentLeaveDto){
		StudentLeaveDto submittedLeaveDto= studentService.applyLeave(studentLeaveDto);
		if(submittedLeaveDto == null) 
			return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.CREATED).body(submittedLeaveDto);
		
	}
	@GetMapping("/leave/{studentId}")
	public ResponseEntity<List<StudentLeaveDto>> getAllAppliedLeavesByStudentId(@PathVariable Long studentId){
	List<StudentLeaveDto> studentLeaveDto =	studentService.getAllAppliedLeavesByStudentId(studentId);
	if(studentLeaveDto == null)
		return ResponseEntity.notFound().build();
	return ResponseEntity.ok(studentLeaveDto);
	}

	@PutMapping("/{studentId}")
	public ResponseEntity<?> updateStudent(@PathVariable Long studentId ,@RequestBody StudentDto studentDto){
	StudentDto createdStudentDto =	studentService.updateStudent(studentId,studentDto);
	if(createdStudentDto == null)
		return 	new ResponseEntity<>("Something went wrong.",HttpStatus.BAD_REQUEST);
	return ResponseEntity.status(HttpStatus.CREATED).body(createdStudentDto);
	}
}
