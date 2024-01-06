package com.sms.entities;

import java.util.Date;

import com.sms.dto.TeacherDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="teachers")
public class Teacher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String gender;
	private String department;
	private String qualification;
	private Date dob;
	private String address;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	public TeacherDto getTeacherDto() {
		TeacherDto teacherDto = new TeacherDto();
		teacherDto.setId(id);
		teacherDto.setName(name);
		teacherDto.setQualification(qualification);
		teacherDto.setDepartment(department);
		teacherDto.setAddress(address);
		teacherDto.setGender(gender);
		teacherDto.setDob(dob);
		return teacherDto;
		
	}
	
	

}
