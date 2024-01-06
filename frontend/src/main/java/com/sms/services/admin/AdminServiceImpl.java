	package com.sms.services.admin;

import java.lang.StackWalker.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sms.dto.FeeDto;
import com.sms.dto.SingleStudentDto;
import com.sms.dto.SingleTeacherDto;
import com.sms.dto.StudentDto;
import com.sms.dto.StudentLeaveDto;
import com.sms.dto.TeacherDto;
import com.sms.entities.Fee;
import com.sms.entities.StudentLeave;
import com.sms.entities.Teacher;
import com.sms.entities.User;
import com.sms.enums.StudentLeaveStatus;
import com.sms.enums.UserRole;
import com.sms.repositories.FeeRepository;
import com.sms.repositories.StudentLeaveRepository;
import com.sms.repositories.TeacherRepository;
import com.sms.repositories.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class AdminServiceImpl implements AdminService {
	
	private final UserRepository userRepository;
	
	private final FeeRepository feeRepository;
	
	private final StudentLeaveRepository studentLeaveRepository;
	
	private final TeacherRepository teacherRepository;
	
	public AdminServiceImpl(UserRepository userRepository, FeeRepository feeRepository,
			StudentLeaveRepository studentLeaveRepository, TeacherRepository teacherRepository) {
		super();
		this.userRepository = userRepository;
		this.feeRepository = feeRepository;
		this.studentLeaveRepository = studentLeaveRepository;
		this.teacherRepository = teacherRepository;
	}
	
	@PostConstruct
	public void createAdminAccount() {
		User adminAccount = userRepository.findByRole(UserRole.ADMIN);
		if(adminAccount == null) {
			User admin = new User();
			admin.setEmail("admin@test.com");
			admin.setName("admin");
			admin.setRole(UserRole.ADMIN);
			admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(admin);
			
		}
		
	}
	@Override
	public StudentDto postStudent(StudentDto studentDto) {
		Optional<User> optionalUser = userRepository.findFirstByEmail(studentDto.getEmail());
		if(optionalUser.isEmpty()) {
			User user =new User();
			BeanUtils.copyProperties(studentDto, user);
			user.setPassword(new BCryptPasswordEncoder().encode(studentDto.getPassword()));
			user.setRole(UserRole.STUDENT);
			User createdUser = userRepository.save(user);
			StudentDto createdStudentDto =new StudentDto();
			createdStudentDto.setId(createdUser.getId());
			createdStudentDto.setEmail(createdUser.getEmail());
			return createdStudentDto;
					
		}
		return null;
	}
	@Override
	public List<StudentDto> getAllStudents() {
       return userRepository.findAllByRole(UserRole.STUDENT).stream().map(User::getStudentDto).collect(Collectors.toList());
	}
	@Override
	public void deleteStudent(Long studentId) {
		userRepository.deleteById(studentId);
		
	}
	@Override
	public SingleStudentDto getStudentById(Long studentId) {
	 Optional<User> optionalUser =	userRepository.findById(studentId);
	 if(optionalUser.isPresent()) {
		 SingleStudentDto singleStudentDto = new SingleStudentDto();
		 	singleStudentDto.setStudentDto(optionalUser.get().getStudentDto());
		 	return singleStudentDto;
	 }
	 return null;
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
	@Override
	public FeeDto payFee(Long studentId, FeeDto feeDto) {
		Optional<User> optionalStudent = userRepository.findById(studentId);
		if(optionalStudent.isPresent()) {
			Fee fee =new Fee();
			fee.setAmount(feeDto.getAmount());
			fee.setMonth(feeDto.getMonth());	
			fee.setDescription(feeDto.getDescription());
			fee.setCreatedDate(new Date());
			fee.setGivenBy(feeDto.getGivenBy());
			fee.setUser(optionalStudent.get());
			Fee paidFee = feeRepository.save(fee);
			FeeDto paidfeeDto= new FeeDto();
			paidFee.setId(paidFee.getId());
			return paidfeeDto;
		}
		return null;
	}
	@Override
	public List<StudentLeaveDto> getAllAppliedLeaves() {
		return studentLeaveRepository.findAll().stream().map(StudentLeave::getStudentLeaveDto).collect(Collectors.toList());
	}
	@Override
	public StudentLeaveDto changeLeaveStatus(Long leaveId, String status) {
		Optional<StudentLeave> optionalStudentLeave = studentLeaveRepository.findById(leaveId);
		if(optionalStudentLeave.isPresent()) {
			StudentLeave studentLeave = optionalStudentLeave.get();
			if(Objects.equals(status,"Approve")) {
				studentLeave.setStudentLeaveStatus(StudentLeaveStatus.Approved);
			}else {
				studentLeave.setStudentLeaveStatus(StudentLeaveStatus.Disapproved);
			}
			StudentLeave updatedStudentLeave = studentLeaveRepository.save(studentLeave);
			StudentLeaveDto updatedStudentLeaveDto = new StudentLeaveDto();
			updatedStudentLeaveDto.setId(updatedStudentLeave.getId());
			return updatedStudentLeaveDto;
		}
		
		return null;
	}
	@Override
	public TeacherDto postTeacher(TeacherDto teacherDto) {
		Teacher teacher = new Teacher();
		BeanUtils.copyProperties(teacherDto, teacher);
		Teacher createdTeacher =  teacherRepository.save(teacher);
		TeacherDto createdTeacherDto = new TeacherDto();
		createdTeacherDto.setId(createdTeacher.getId());
		
		return createdTeacherDto;
	}

	@Override
	public List<TeacherDto> getAllTeachers() {
		return teacherRepository.findAll().stream().map(Teacher::getTeacherDto).collect(Collectors.toList());
		
	}

	@Override
	public void deleteTeacher(Long teacherId) {
		teacherRepository.deleteById(teacherId);
		
	}

	@Override
	public SingleTeacherDto getTeacherById(Long teacherId) {
		Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherId);
		if(optionalTeacher.isPresent()) {
			SingleTeacherDto singleTeacherDto = new SingleTeacherDto();
			singleTeacherDto.setTeacherDto(optionalTeacher.get().getTeacherDto());
			return singleTeacherDto;
		}
		return null;
	}

	@Override
	public TeacherDto updateTeacher(Long teacherId, TeacherDto teacherDto) {
		Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherId);
		if(optionalTeacher.isPresent()) {
			Teacher updateTeacher = optionalTeacher.get();
			updateTeacher.setName(teacherDto.getName());
			updateTeacher.setDepartment(teacherDto.getDepartment());
			updateTeacher.setAddress(teacherDto.getAddress());
			updateTeacher.setDob(teacherDto.getDob());
			updateTeacher.setQualification(teacherDto.getQualification());
			updateTeacher.setGender(teacherDto.getGender());
		 	Teacher updatedTeacher = teacherRepository.save(updateTeacher);
		 	TeacherDto updatedTeacherDto = new TeacherDto();
		 	updatedTeacherDto.setId(updatedTeacher.getId());
		 	return updatedTeacherDto;
		}
		return null;
	}

}
