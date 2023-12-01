package com.multimarkethub.productservice.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.multimarkethub.productservice.entity.CategoriesEntity;

public interface CategoryRepository extends JpaRepository<CategoriesEntity, Integer>{
	
	@Query(value="select current_timestamp",nativeQuery=true)
	Timestamp findCurrentTimeStamp();
	
	
	@Query(value = "SELECT * FROM categories WHERE store_id = :storeId AND category_name = :categoryName", nativeQuery = true)
    List<CategoriesEntity> findByStoreIdAndCategoryName(@Param("storeId") Integer storeId, @Param("categoryName") String categoryName);
	
	
	List<CategoriesEntity> findByStoreId(Integer storeId);

}
