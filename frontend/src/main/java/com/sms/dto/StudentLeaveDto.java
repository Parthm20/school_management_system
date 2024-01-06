package com.sms.dto;

import java.util.Date;

import com.sms.enums.StudentLeaveStatus;

public class StudentLeaveDto {
	private Long id;
	private String subject;
	private String body;
	private Date date;
	private StudentLeaveStatus studentLeaveStatus;
	
	private Long userId;
	

	public StudentLeaveDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentLeaveDto(Long id, String subject, String body, Date date, StudentLeaveStatus studentLeaveStatus,
			Long userId) {
		this.id = id;
		this.subject = subject;
		this.body = body;
		this.date = date;
		this.studentLeaveStatus = studentLeaveStatus;
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public StudentLeaveStatus getStudentLeaveStatus() {
		return studentLeaveStatus;
	}

	public void setStudentLeaveStatus(StudentLeaveStatus studentLeaveStatus) {
		this.studentLeaveStatus = studentLeaveStatus;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	


}
