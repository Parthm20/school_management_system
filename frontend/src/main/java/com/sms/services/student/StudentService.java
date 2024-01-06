package com.sms.services.student;

import java.util.List;

import com.sms.dto.SingleStudentDto;
import com.sms.dto.StudentDto;
import com.sms.dto.StudentLeaveDto;

public interface StudentService {

	SingleStudentDto getStudentById(Long studentId);

	StudentLeaveDto applyLeave(StudentLeaveDto studentLeaveDto);

	List<StudentLeaveDto> getAllAppliedLeavesByStudentId(Long studentId);

	StudentDto updateStudent(Long studentId, StudentDto studentDto);

}
