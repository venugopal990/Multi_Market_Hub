package com.multimarkethub.orderservice.repository;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.multimarkethub.orderservice.entity.PaymentsEntity;

import jakarta.transaction.Transactional;

public interface PaymentsRepository extends JpaRepository<PaymentsEntity, Integer>{
	
	
	@Query(value="select current_timestamp",nativeQuery=true)
	Timestamp findCurrentTimeStamp();
	
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE PaymentsEntity p SET p.paymentStatusId = :paymentStatusId WHERE p.orderId = :orderId and p.storeId = :storeId" )
	void updateProductStock(@Param("orderId") Integer orderId,@Param("paymentStatusId") Integer paymentStatusId,@Param("storeId") Integer storeId);

}
