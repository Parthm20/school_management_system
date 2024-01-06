package com.sms.services.admin;

import java.util.List;

import com.sms.dto.FeeDto;
import com.sms.dto.SingleStudentDto;
import com.sms.dto.SingleTeacherDto;
import com.sms.dto.StudentDto;
import com.sms.dto.StudentLeaveDto;
import com.sms.dto.TeacherDto;

public interface AdminService {

	StudentDto postStudent(StudentDto studentDto);

	List<StudentDto> getAllStudents();

	void deleteStudent(Long studentId);

	SingleStudentDto getStudentById(Long studentId);

	StudentDto updateStudent(Long studentId, StudentDto studentDto);

	FeeDto payFee(Long studentId, FeeDto feeDto);

	List<StudentLeaveDto> getAllAppliedLeaves();

	StudentLeaveDto changeLeaveStatus(Long leaveId, String status);

	TeacherDto postTeacher(TeacherDto teacherDto);

	List<TeacherDto> getAllTeachers();

	void deleteTeacher(Long teacherId);

	SingleTeacherDto getTeacherById(Long teacherId);

	TeacherDto updateTeacher(Long teacherId, TeacherDto teacherDto);

	
}	
