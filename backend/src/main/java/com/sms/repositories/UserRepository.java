package com.sms.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sms.dto.StudentDto;
import com.sms.entities.User;
import com.sms.enums.UserRole;


@Repository
public interface UserRepository extends  JpaRepository<User, Long>{

	User findByRole(UserRole userRole);
	
	Optional<User> findFirstByEmail(String email);

	List<User> findAllByRole(UserRole userRole);
	

}
