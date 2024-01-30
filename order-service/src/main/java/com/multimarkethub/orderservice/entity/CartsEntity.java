package com.multimarkethub.orderservice.entity;

import java.sql.Timestamp;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.multimarkethub.orderservice.utils.JsonTimestampSerializer;

@Entity
@Table(name = "carts")
public class CartsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="carts_cart_id_seq")
	@SequenceGenerator(name ="carts_cart_id_seq", sequenceName="carts_cart_id_seq", allocationSize=1)
	@Column(name="cart_id")
	private Integer cartId;

	@Column(name="customer_id")
	private Integer customerId;

	@Column(name="store_id")
	private Integer storeId;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="cart_created_at")
	private Timestamp cartCreatedAt;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="cart_updated_at")
	private Timestamp cartUpdatedAt;

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Timestamp getCartCreatedAt() {
		return cartCreatedAt;
	}

	public void setCartCreatedAt(Timestamp cartCreatedAt) {
		this.cartCreatedAt = cartCreatedAt;
	}

	public Timestamp getCartUpdatedAt() {
		return cartUpdatedAt;
	}

	public void setCartUpdatedAt(Timestamp cartUpdatedAt) {
		this.cartUpdatedAt = cartUpdatedAt;
	}

	public CartsEntity(Integer customerId, Integer storeId, Timestamp cartCreatedAt,Timestamp cartUpdatedAt) {
		super();
		this.customerId = customerId;
		this.storeId = storeId;
		this.cartCreatedAt = cartCreatedAt;
		this.cartUpdatedAt = cartUpdatedAt;
	}

	public CartsEntity() {
	}
}