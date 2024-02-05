package com.multimarkethub.userservice.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.multimarkethub.userservice.entity.AdminEntity;
import com.multimarkethub.userservice.entity.CustomerEntity;

import jakarta.transaction.Transactional;

public interface CustomersRepository  extends JpaRepository<CustomerEntity, Integer>{
	
	
	@Query(value = "SELECT * FROM Customers WHERE customer_email = :email", nativeQuery = true)
	Optional<CustomerEntity> findCustomerByEmail(
			@Param("email") String email);
	
	
	@Query(value = "SELECT COUNT(*) FROM Customers WHERE customer_email = :email", nativeQuery = true)
	Long countCustomerByEmail(
			@Param("email") String email);
		
	
	@Query(value="select current_timestamp",nativeQuery=true)
	Timestamp findCurrentTimeStamp();
	
	
	 @Modifying(clearAutomatically = true)
	 @Transactional
	 @Query("update CustomerEntity a set a.customerFirstName=:customerFirstName, a.customerLastName=:customerLastName, a.customerAddress=:customerAddress, a.customerPhoneNumber=:customerPhoneNumber, "
	 		+ "a.customerUpdatedAt=:customerUpdatedAt, a.customerEmailVerified=:customerEmailVerified where a.customerId=:customerId and a.storeId=:storeId")
	 void updateCustomerDetails(@Param("customerId") Integer customerId, 
	                         @Param("customerFirstName") String customerFirstName, 
	                         @Param("customerLastName") String customerLastName, 
	                         @Param("customerAddress") String customerAddress, 
	                         @Param("customerPhoneNumber") String customerPhoneNumber,
	                         @Param("customerUpdatedAt") Timestamp customerUpdatedAt, 
	 						 @Param("storeId") Integer storeId, @Param("customerEmailVerified") Boolean customerEmailVerified);


	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("update CustomerEntity a set a.customerUpdatedAt=:customerUpdatedAt, a.customerEmailVerified=true WHERE customerEmail in :emails")
	void updateVerifiedEmail(@Param("emails") List<String> emails,@Param("customerUpdatedAt") Timestamp customerUpdatedAt);

}
