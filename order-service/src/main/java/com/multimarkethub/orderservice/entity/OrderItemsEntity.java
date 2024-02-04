package com.multimarkethub.orderservice.entity;

import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.multimarkethub.orderservice.utils.JsonTimestampSerializer;

@Entity
@Table(name = "order_items")
public class OrderItemsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="order_items_order_item_id_seq")
	@SequenceGenerator(name ="order_items_order_item_id_seq", sequenceName="order_items_order_item_id_seq", allocationSize=1)
	@Column(name="order_item_id")
	private Integer orderItemId;

	@Column(name="order_id")
	private Integer orderId;

	@Column(name="product_id")
	private Integer productId;

	@Column(name="quantity")
	private Integer quantity;

	@Column(name="unit_price")
	private Double unitPrice;

	@Column(name="store_id")
	private Integer storeId;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="order_item_created_at")
	private Timestamp orderItemCreatedAt;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="order_item_updated_at")
	private Timestamp orderItemUpdatedAt;
	
	
    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", insertable = false, updatable = false)
	private ProductsEntity productsEntity;
	
	
	@ManyToOne
	@JoinColumn(name = "order_id", insertable = false, updatable = false)
	private OrdersEntity orders;

	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Timestamp getOrderItemCreatedAt() {
		return orderItemCreatedAt;
	}

	public void setOrderItemCreatedAt(Timestamp orderItemCreatedAt) {
		this.orderItemCreatedAt = orderItemCreatedAt;
	}

	public Timestamp getOrderItemUpdatedAt() {
		return orderItemUpdatedAt;
	}

	public void setOrderItemUpdatedAt(Timestamp orderItemUpdatedAt) {
		this.orderItemUpdatedAt = orderItemUpdatedAt;
	}

	public OrdersEntity getOrders() {
		return orders;
	}

	public void setOrders(OrdersEntity orders) {
		this.orders = orders;
	}

	public OrderItemsEntity( Integer orderId , Integer productId, Integer quantity, Double unitPrice,
			Integer storeId, Timestamp orderItemCreatedAt, Timestamp orderItemUpdatedAt) {
		super();
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.storeId = storeId;
		this.orderItemCreatedAt = orderItemCreatedAt;
		this.orderItemUpdatedAt = orderItemUpdatedAt;
	}

	public OrderItemsEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductsEntity getProductsEntity() {
		return productsEntity;
	}

	public void setProductsEntity(ProductsEntity productsEntity) {
		this.productsEntity = productsEntity;
	}

	
}