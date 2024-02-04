package com.multimarkethub.orderservice.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.multimarkethub.orderservice.entity.OrdersEntity;

public interface OrdersRepository extends JpaRepository<OrdersEntity, Integer>{
	
	@Query(value="select current_timestamp",nativeQuery=true)
	Timestamp findCurrentTimeStamp();
	
	
	List<OrdersEntity> findByStoreIdAndCustomerId(Integer storeId, Integer customerId);

}
