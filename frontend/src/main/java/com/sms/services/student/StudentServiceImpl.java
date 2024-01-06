package com.sms.services.student;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sms.dto.SingleStudentDto;
import com.sms.dto.StudentDto;
import com.sms.dto.StudentLeaveDto;
import com.sms.entities.StudentLeave;
import com.sms.entities.User;
import com.sms.enums.StudentLeaveStatus;
import com.sms.repositories.StudentLeaveRepository;
import com.sms.repositories.UserRepository;

@Service
public class StudentServiceImpl implements StudentService {

	private final StudentLeaveRepository studentLeaveRepository;
	
	private final UserRepository userRepository;

	public StudentServiceImpl(StudentLeaveRepository studentLeaveRepository, UserRepository userRepository) {
		super();
		this.studentLeaveRepository = studentLeaveRepository;
		this.userRepository = userRepository;
	}

	@Override
	public SingleStudentDto getStudentById(Long studentId) {
	    Optional<User> optionalUser = userRepository.findById(studentId);
	    if (optionalUser.isPresent()) {
	        SingleStudentDto singleStudentDto = new SingleStudentDto();
	        singleStudentDto.setStudentDto(optionalUser.get().getStudentDto());
	        return singleStudentDto;
	    }
	    return null; 
	}

	@Override
	public StudentLeaveDto applyLeave(StudentLeaveDto studentLeaveDto) {
		Optional<User> optionalUser = userRepository.findById(studentLeaveDto.getUserId());
		if(optionalUser.isPresent()) {
			StudentLeave studentLeave = new StudentLeave();
			studentLeave.setSubject(studentLeaveDto.getSubject());
			studentLeave.setBody(studentLeaveDto.getBody());
			studentLeave.setDate(new Date());
			studentLeave.setStudentLeaveStatus(StudentLeaveStatus.Pending);
			studentLeave.setUser(optionalUser.get());
			StudentLeave SubmittedUserLeave = studentLeaveRepository.save(studentLeave);
			StudentLeaveDto  studentLeaveDto1 = new StudentLeaveDto();
			studentLeaveDto1.setId(SubmittedUserLeave.getId());
			return studentLeaveDto1;
			
			
		}
		return null;
	}

	@Override
	public List<StudentLeaveDto> getAllAppliedLeavesByStudentId(Long studentId) {
		return studentLeaveRepository.findAllByUserId(studentId).stream().map(StudentLeave::getStudentLeaveDto).collect(Collectors.toList());
		
	}
	
	@Override
	public StudentDto updateStudent(Long studentId, StudentDto studentDto) {
		Optional<User> optionalUser = userRepository.findById(studentId);
		if(optionalUser.isPresent()) {
			User user  = optionalUser.get();
			user.setName(studentDto.getName());
			user.setGender(studentDto.getGender());
			user.setAddress(studentDto.getAddress());
			user.setDob(studentDto.getDob());
			user.setEmail(studentDto.getEmail());
			user.setFatherName(studentDto.getFatherName());
			user.setMotherName(studentDto.getMotherName());
			user.setStudentClass(studentDto.getStudentClass());
			User updatedStudent= userRepository.save(user);
			StudentDto updatedStudentDto = new StudentDto();
			updatedStudent.setId(updatedStudent.getId());
			return updatedStudentDto;
		}
		return null;
	}
	

}
