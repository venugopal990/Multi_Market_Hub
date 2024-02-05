package com.multimarkethub.userservice.entity;

import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.multimarkethub.userservice.utils.JsonTimestampSerializer;


@Entity
@Table(name = "customers")
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="customers_customer_id_seq")
	@SequenceGenerator(name ="customers_customer_id_seq", sequenceName="customers_customer_id_seq", allocationSize=1)
	@Column(name="customer_id")
	private Integer customerId;

	@Column(name="customer_first_name")
	private String customerFirstName;

	@Column(name="customer_last_name")
	private String customerLastName;

	@Column(name="customer_email")
	private String customerEmail;

	@Column(name="customer_password")
	private String customerPassword;

	@Column(name="customer_address")
	private String customerAddress;

	@Column(name="customer_phone_number")
	private String customerPhoneNumber;

	@Column(name="store_id")
	private Integer storeId;

	@Column(name="customer_email_verified")
	private Boolean customerEmailVerified;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="customer_created_at")
	private Timestamp customerCreatedAt;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="customer_updated_at")
	private Timestamp customerUpdatedAt;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerFirstName() {
		return customerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Boolean getCustomerEmailVerified() {
		return customerEmailVerified;
	}

	public void setCustomerEmailVerified(Boolean customerEmailVerified) {
		this.customerEmailVerified = customerEmailVerified;
	}

	public Timestamp getCustomerCreatedAt() {
		return customerCreatedAt;
	}

	public void setCustomerCreatedAt(Timestamp customerCreatedAt) {
		this.customerCreatedAt = customerCreatedAt;
	}

	public Timestamp getCustomerUpdatedAt() {
		return customerUpdatedAt;
	}

	public void setCustomerUpdatedAt(Timestamp customerUpdatedAt) {
		this.customerUpdatedAt = customerUpdatedAt;
	}

	public CustomerEntity(Integer customerId, String customerFirstName, String customerLastName, String customerEmail,
			String customerPassword, String customerAddress, String customerPhoneNumber, Integer storeId, Boolean customerEmailVerified) {
		super();
		this.customerId = customerId;
		this.customerFirstName = customerFirstName;
		this.customerLastName = customerLastName;
		this.customerEmail = customerEmail;
		this.customerPassword = customerPassword;
		this.customerAddress = customerAddress;
		this.customerPhoneNumber = customerPhoneNumber;
		this.storeId = storeId;
		this.customerEmailVerified = customerEmailVerified;
	}

	public CustomerEntity() {
		
	}
}