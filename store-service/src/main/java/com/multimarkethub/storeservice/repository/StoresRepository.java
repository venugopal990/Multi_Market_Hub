package com.multimarkethub.storeservice.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.multimarkethub.storeservice.entity.StoreEntity;

public interface StoresRepository extends JpaRepository<StoreEntity, Integer>{
	
	
	
	@Query(value="select current_timestamp",nativeQuery=true)
	Timestamp findCurrentTimeStamp();
	
	
	
	List<StoreEntity> findByStoreDomain(String storeDomain);

}
