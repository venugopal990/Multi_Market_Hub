package com.multimarkethub.orderservice.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.multimarkethub.orderservice.entity.CartitemsEntity;

import jakarta.transaction.Transactional;

public interface CartitemsRepository extends JpaRepository<CartitemsEntity, Integer>{
	
	
	@Query(value="select current_timestamp",nativeQuery=true)
	Timestamp findCurrentTimeStamp();
	
	
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE CartitemsEntity p SET p.quantity = :quantity, p.unitPrice = :unitPrice, p.cartItemUpdatedAt = CURRENT_TIMESTAMP " +  // Include the update of productUpdatedAt
	       "WHERE p.cartItemId = :cartItemId")
	void updateCartItems(@Param("quantity") Integer quantity,
	                     @Param("unitPrice") Double unitPrice,
	                     @Param("cartItemId") Integer cartItemId);


	List<CartitemsEntity> findByCartId(Integer cartId);
	CartitemsEntity findByProductIdAndCartId(Integer productId, Integer cartId);
	
	
	@Modifying(clearAutomatically = true)
	@Transactional
    @Query("DELETE FROM CartitemsEntity p WHERE p.productId = :productId AND p.cartId = :cartId")
    void deleteByCartIdAndProductId(Integer cartId, Integer productId);

}
