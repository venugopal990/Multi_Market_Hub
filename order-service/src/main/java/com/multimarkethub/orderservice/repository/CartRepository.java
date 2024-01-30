package com.multimarkethub.orderservice.repository;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.multimarkethub.orderservice.entity.CartsEntity;

import jakarta.transaction.Transactional;

public interface CartRepository extends JpaRepository<CartsEntity, Integer>{
	
	
	@Query(value="select current_timestamp",nativeQuery=true)
	Timestamp findCurrentTimeStamp();

	CartsEntity findByCustomerIdAndStoreId(Integer customerId, Integer storeId);
	
	
	@Modifying(clearAutomatically = true)
	@Transactional
    @Query("DELETE FROM CartsEntity p WHERE p.storeId = :storeId AND p.customerId = :customerId")
    void deleteByStoreIdAndCustomerId(Integer storeId, Integer customerId);
	

}
