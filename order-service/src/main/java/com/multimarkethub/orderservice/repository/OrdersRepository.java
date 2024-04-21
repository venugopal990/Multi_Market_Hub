package com.multimarkethub.orderservice.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.multimarkethub.orderservice.entity.OrdersEntity;

import jakarta.transaction.Transactional;

public interface OrdersRepository extends JpaRepository<OrdersEntity, Integer>{
	
	@Query(value="select current_timestamp",nativeQuery=true)
	Timestamp findCurrentTimeStamp();
	
	List<OrdersEntity> findByStoreId(Integer storeId);
	
	List<OrdersEntity> findByStoreIdAndCustomerId(Integer storeId, Integer customerId);
	
	List<OrdersEntity> findByStoreIdAndCustomerIdAndOrderId(Integer storeId, Integer customerId, Integer orderId);
	
	
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE OrdersEntity p SET p.deliveryStatusId = :deliveryStatusId,p.orderUpdatedAt = CURRENT_TIMESTAMP WHERE p.orderId = :orderId and p.storeId = :storeId and p.customerId = :customerId" )
	void updateDeliveryStatus(@Param("orderId") Integer orderId,@Param("deliveryStatusId") Integer deliveryStatusId,@Param("storeId") Integer storeId,@Param("customerId") Integer customerId);

}
