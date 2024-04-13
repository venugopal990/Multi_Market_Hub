package com.multimarkethub.storeservice.entity;

import java.sql.Timestamp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.multimarkethub.storeservice.utils.JsonTimestampSerializer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "stores")
public class StoreEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="stores_store_id_seq")
	@SequenceGenerator(name ="stores_store_id_seq", sequenceName="stores_store_id_seq", allocationSize=1)
	@Column(name="store_id")
	private Integer storeId;

	@Column(name="store_name")
	private String storeName;

	@Column(name="store_domain")
	private String storeDomain;
	
	@Column(name="store_image_url")
	private String storeImageUrl;

	@Column(name="store_address")
	private String storeAddress;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="store_created_at")
	private Timestamp storeCreatedAt;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="store_updated_at")
	private Timestamp storeUpdatedAt;

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreDomain() {
		return storeDomain;
	}

	public void setStoreDomain(String storeDomain) {
		this.storeDomain = storeDomain;
	}

	public String getStoreImageUrl() {
		return storeImageUrl;
	}

	public void setStoreImageUrl(String storeImageUrl) {
		this.storeImageUrl = storeImageUrl;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public Timestamp getStoreCreatedAt() {
		return storeCreatedAt;
	}

	public void setStoreCreatedAt(Timestamp storeCreatedAt) {
		this.storeCreatedAt = storeCreatedAt;
	}

	public Timestamp getStoreUpdatedAt() {
		return storeUpdatedAt;
	}

	public void setStoreUpdatedAt(Timestamp storeUpdatedAt) {
		this.storeUpdatedAt = storeUpdatedAt;
	}

	public StoreEntity(String storeName, String storeAddress, String storeImageUrl) {
		super();
		this.storeName = storeName;
		this.storeAddress = storeAddress;
		this.storeImageUrl = storeImageUrl;
	}

	public StoreEntity() {
		super();
	}

	@Override
	public String toString() {
		return "StoreEntity [storeId=" + storeId + ", storeName=" + storeName + ", storeDomain=" + storeDomain
				+ ", storeAddress=" + storeAddress + ", storeCreatedAt=" + storeCreatedAt + ", storeUpdatedAt="
				+ storeUpdatedAt + "]";
	}
}