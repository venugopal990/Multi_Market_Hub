package com.multimarkethub.orderservice.beans;

import java.sql.Timestamp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class Store {
	
	private Integer id;
	
	@NotBlank @NotNull @Size(min=3)
	private String name;
	
	@NotBlank @NotNull @Size(min=3)
	private String address;
	
	@NotBlank @NotNull
	private String storeImageUrl;
	
	private String domainResource;
	
	private Timestamp created_at;
	
	private Timestamp updated_date;
	
	private Integer adminId;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStoreImageUrl() {
		return storeImageUrl;
	}
	public void setStoreImageUrl(String storeImageUrl) {
		this.storeImageUrl = storeImageUrl;
	}
	public String getDomainResource() {
		return domainResource;
	}
	public void setDomainResource(String domainResource) {
		this.domainResource = domainResource;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	public Timestamp getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(Timestamp updated_date) {
		this.updated_date = updated_date;
	}
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	

	public Store(Integer id, @NotBlank @NotNull @Size(min = 3) String name,
			@NotBlank @NotNull @Size(min = 3) String address, String domainResource,@NotBlank @NotNull String storeImageUrl, Timestamp created_at, Timestamp updated_date) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.domainResource = domainResource;
		this.storeImageUrl = storeImageUrl;
		this.created_at = created_at;
		this.updated_date = updated_date;
	}
	@Override
	public String toString() {
		return "Store [id=" + id + ", name=" + name + ", address=" + address + ", domainResource=" + domainResource
				+ ", created_at=" + created_at + ", updated_date=" + updated_date + ", adminId=" + adminId + "]";
	}
	
	
	
	

}
