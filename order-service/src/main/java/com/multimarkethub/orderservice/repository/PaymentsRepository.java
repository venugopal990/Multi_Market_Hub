package com.multimarkethub.orderservice.repository;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.multimarkethub.orderservice.entity.PaymentsEntity;

public interface PaymentsRepository extends JpaRepository<PaymentsEntity, Integer>{
	
	
	@Query(value="select current_timestamp",nativeQuery=true)
	Timestamp findCurrentTimeStamp();

}
