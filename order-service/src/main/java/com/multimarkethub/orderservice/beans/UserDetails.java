package com.multimarkethub.orderservice.beans;

import java.sql.Timestamp;
import java.util.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDetails {
	
	private Integer id;
	@NotBlank @NotNull @Size(min=3)
	private String firstName;
	@NotBlank @NotNull @Size(min=1)
	private String lastName;
	@NotBlank @NotNull @Email(message = "Email is not valid")
	private String email;
	@NotBlank @NotNull
	private String password;

	private String address;
	@NotBlank @NotNull
	private String phoneNumber;
	private Integer storeId;
	private boolean isEmailIsVerified;
	private Timestamp createdDate;
	private Timestamp updatedDate;
	
	
	public UserDetails(Integer id, String firstName, String lastName, String email, String address,
			String phoneNumber, Integer storeId, boolean isEmailIsVerified, Timestamp createdDate, Timestamp updatedDate) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.storeId = storeId;
		this.isEmailIsVerified = isEmailIsVerified;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public boolean isEmailIsVerified() {
		return isEmailIsVerified;
	}
	public void setEmailIsVerified(boolean isEmailIsVerified) {
		this.isEmailIsVerified = isEmailIsVerified;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}
	@Override
	public String toString() {
		return "UserDetails [Id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", address=" + address + ", phoneNumber=" + phoneNumber + ", storeId="
				+ storeId + ", isEmailIsVerified=" + isEmailIsVerified + ", createdDate=" + createdDate
				+ ", updatedDate=" + updatedDate + "]";
	}
	
	
	
	

}
