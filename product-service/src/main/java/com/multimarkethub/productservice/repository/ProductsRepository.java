package com.multimarkethub.productservice.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.multimarkethub.productservice.entity.ProductsEntity;

import jakarta.transaction.Transactional;

public interface ProductsRepository extends JpaRepository<ProductsEntity, Integer>{

	@Query(value="select current_timestamp",nativeQuery=true)
	Timestamp findCurrentTimeStamp();
	
	
	@Query("SELECT p FROM ProductsEntity  p WHERE LOWER(p.productName) like LOWER(CONCAT('%', :productName, '%'))  AND p.storeId = :storeId")
    List<ProductsEntity> findByProductNameAndStoreIdILike(String productName, Integer storeId);


	List<ProductsEntity> findByStoreIdAndCategoryId(Integer storeId, Integer categoryId);
	
	ProductsEntity findByProductIdAndStoreId(Integer productId, Integer storeId);
	
	List<ProductsEntity> findAllByStoreId(Integer storeId);
	
	@Modifying(clearAutomatically = true)
	@Transactional
    @Query("DELETE FROM ProductsEntity p WHERE p.productId = :productId AND p.storeId = :storeId")
    void deleteByProductIdAndStoreId(Integer productId, Integer storeId);


	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE ProductsEntity p SET p.productName = :productName, p.productDescription = :productDescription, " +
	       "p.productPrice = :productPrice, p.productStockQuantity = :productStockQuantity, " +
	       "p.categoryId = :categoryId, p.unitId = :unitId, p.productImageUrl = :productImageUrl, " +
	       "p.productUpdatedAt = CURRENT_TIMESTAMP " +  // Include the update of productUpdatedAt
	       "WHERE p.productId = :productId and p.storeId = :storeId")
	void updateProduct(@Param("productId") Integer productId,
	                   @Param("productName") String productName,
	                   @Param("productDescription") String productDescription,
	                   @Param("productPrice") Double productPrice,
	                   @Param("productStockQuantity") Integer productStockQuantity,
	                   @Param("categoryId") Integer categoryId,
	                   @Param("unitId") Integer unitId,
	                   @Param("productImageUrl") String productImageUrl,
	                   @Param("storeId") Integer storeId);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE ProductsEntity p SET p.productStockQuantity = :productStockQuantity,p.productUpdatedAt = CURRENT_TIMESTAMP " +  // Include the update of productUpdatedAt
	       "WHERE p.productId = :productId and p.storeId = :storeId")
	void updateProductStock(@Param("productId") Integer productId,@Param("productStockQuantity") Integer productStockQuantity,@Param("storeId") Integer storeId);


}
