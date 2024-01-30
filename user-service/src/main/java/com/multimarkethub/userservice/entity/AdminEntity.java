package com.multimarkethub.userservice.entity;


import java.sql.Timestamp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.multimarkethub.userservice.utils.JsonTimestampSerializer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "admins")
public class AdminEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="admins_admin_id_seq")
	@SequenceGenerator(name ="admins_admin_id_seq", sequenceName="admins_admin_id_seq", allocationSize=1)
	@Column(name="admin_id")
	private Integer adminId;

	@Column(name="admin_first_name")
	private String adminFirstName;

	@Column(name="admin_last_name")
	private String adminLastName;

	@Column(name="admin_email")
	private String adminEmail;

	@Column(name="admin_password")
	private String adminPassword;

	@Column(name="admin_address")
	private String adminAddress;

	@Column(name="admin_phone_number")
	private String adminPhoneNumber;

	@Column(name="store_id")
	private Integer storeId;

	@Column(name="admin_email_verified")
	private Boolean adminEmailVerified;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="admin_created_at")
	private Timestamp adminCreatedAt;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="admin_updated_at")
	private Timestamp adminUpdatedAt;

	public Integer getAdminId() {
		return adminId;
	}


	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}


	public String getAdminFirstName() {
		return adminFirstName;
	}


	public void setAdminFirstName(String adminFirstName) {
		this.adminFirstName = adminFirstName;
	}


	public String getAdminLastName() {
		return adminLastName;
	}


	public void setAdminLastName(String adminLastName) {
		this.adminLastName = adminLastName;
	}


	public String getAdminEmail() {
		return adminEmail;
	}


	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}


	public String getAdminPassword() {
		return adminPassword;
	}


	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}


	public String getAdminAddress() {
		return adminAddress;
	}


	public void setAdminAddress(String adminAddress) {
		this.adminAddress = adminAddress;
	}


	public String getAdminPhoneNumber() {
		return adminPhoneNumber;
	}


	public void setAdminPhoneNumber(String adminPhoneNumber) {
		this.adminPhoneNumber = adminPhoneNumber;
	}


	public Integer getStoreId() {
		return storeId;
	}


	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}


	public Boolean getAdminEmailVerified() {
		return adminEmailVerified;
	}


	public void setAdminEmailVerified(Boolean adminEmailVerified) {
		this.adminEmailVerified = adminEmailVerified;
	}


	public Timestamp getAdminCreatedAt() {
		return adminCreatedAt;
	}


	public void setAdminCreatedAt(Timestamp adminCreatedAt) {
		this.adminCreatedAt = adminCreatedAt;
	}


	public Timestamp getAdminUpdatedAt() {
		return adminUpdatedAt;
	}


	public void setAdminUpdatedAt(Timestamp adminUpdatedAt) {
		this.adminUpdatedAt = adminUpdatedAt;
	}
	
	public AdminEntity(Integer adminId, String adminFirstName, String adminLastName, String adminEmail,
			String adminPassword, String adminAddress, String adminPhoneNumber, Integer storeId) {
		super();
		this.adminId = adminId;
		this.adminFirstName = adminFirstName;
		this.adminLastName = adminLastName;
		this.adminEmail = adminEmail;
		this.adminPassword = adminPassword;
		this.adminAddress = adminAddress;
		this.adminPhoneNumber = adminPhoneNumber;
		this.storeId = storeId;
	}


	public AdminEntity() {
		
	}
}