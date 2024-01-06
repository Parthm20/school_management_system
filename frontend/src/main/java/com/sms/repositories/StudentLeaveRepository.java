package com.sms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sms.dto.StudentLeaveDto;
import com.sms.entities.StudentLeave;
@Repository
public interface StudentLeaveRepository extends JpaRepository<StudentLeave, Long> {

	List<StudentLeave> findAllByUserId(Long studentId);

}
