package com.multimarkethub.userservice.repository;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.multimarkethub.userservice.entity.AdminEntity;

import jakarta.transaction.Transactional;




public interface AdminsRepository extends JpaRepository<AdminEntity, Integer>{
	
	/*@PersistenceContext
	private EntityManager entityManager;
	
	public AdminEntity insert(AdminEntity adminEntity) {
		return entityManager.merge(adminEntity);
	}
	
	public AdminEntity findById(long id) {
		return entityManager.find(AdminEntity.class, id);
	}
	
	public void deleteById(long id) {
		AdminEntity adminEntity= entityManager.find(AdminEntity.class, id);
		 entityManager.remove(adminEntity);
	}*/
	
	
	//Optional<AdminEntity> findByEmail(String adminEmail);
	
	@Query(value = "SELECT * FROM Admins WHERE admin_email = :email", nativeQuery = true)
	Optional<AdminEntity> findAdminByEmail(
			@Param("email") String email);
	
	
	@Query(value = "SELECT COUNT(*) FROM Admins WHERE admin_email = :email", nativeQuery = true)
	Long countAdminByEmail(
			@Param("email") String email);
		
	
	@Query(value="select current_timestamp",nativeQuery=true)
	Timestamp findCurrentTimeStamp();
	
	 @Modifying(clearAutomatically = true)
	 @Transactional
	 @Query("update AdminEntity a set a.adminFirstName=:adminFirstName, a.adminLastName=:adminLastName, a.adminAddress=:adminAddress, a.adminPhoneNumber=:adminPhoneNumber, a.adminUpdatedAt=:adminUpdatedAt where a.adminId=:adminId")
	 void updateAdminDetails(@Param("adminId") Integer adminId, 
	                         @Param("adminFirstName") String adminFirstName, 
	                         @Param("adminLastName") String adminLastName, 
	                         @Param("adminAddress") String adminAddress, 
	                         @Param("adminPhoneNumber") String adminPhoneNumber,
	                         @Param("adminUpdatedAt") Timestamp adminUpdatedAt);
	 
	 @Modifying(clearAutomatically = true)
	 @Transactional
	 @Query("update AdminEntity a set a.storeId=:storeId, a.adminUpdatedAt=:adminUpdatedAt where a.adminId=:adminId")
	 void updateStoreId(
			 @Param("storeId") Integer storeId,
			 @Param("adminUpdatedAt") Timestamp adminUpdatedAt,
			 @Param("adminId") Integer adminId);
}
