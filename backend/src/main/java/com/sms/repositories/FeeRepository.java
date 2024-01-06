package com.sms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sms.entities.Fee;

@Repository
public interface FeeRepository extends JpaRepository<Fee, Long>{

}
