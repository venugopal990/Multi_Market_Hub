package com.multimarkethub.orderservice.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.multimarkethub.orderservice.utils.JsonTimestampSerializer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class OrdersEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="orders_order_id_seq")
	@SequenceGenerator(name ="orders_order_id_seq", sequenceName="orders_order_id_seq", allocationSize=1)
	@Column(name="order_id")
	private Integer orderId;

	@Column(name="customer_id")
	private Integer customerId;

	@Column(name="order_date")
	private Date orderDate;

	@Column(name="total_amount")
	private Double totalAmount;

	@Column(name="store_id")
	private Integer storeId;
	
	@Column(name="order_status_id")
	private Integer orderStatusId;
	
	@Column(name="delivery_status_id")
	private Integer deliveryStatusId;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="order_created_at")
	private Timestamp orderCreatedAt;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="order_updated_at")
	private Timestamp orderUpdatedAt;
	
	@OneToMany(mappedBy="orders", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<OrderItemsEntity> ordersItems;

    
    @OneToOne(mappedBy = "ordersEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PaymentsEntity paymentsEntity;
    
    @OneToOne
    @JoinColumn(name = "order_status_id", referencedColumnName = "status_id", insertable = false, updatable = false)
	private OrderStatusesEntity orderStatusesEntity;
    
    @OneToOne
    @JoinColumn(name = "delivery_status_id", referencedColumnName = "status_id", insertable = false, updatable = false)
	private DeliveryStatusesEntity deliveryStatusesEntity;
	

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getOrderStatusId() {
		return orderStatusId;
	}

	public void setOrderStatusId(Integer orderStatusId) {
		this.orderStatusId = orderStatusId;
	}

	public Timestamp getOrderCreatedAt() {
		return orderCreatedAt;
	}

	public void setOrderCreatedAt(Timestamp orderCreatedAt) {
		this.orderCreatedAt = orderCreatedAt;
	}

	public Timestamp getOrderUpdatedAt() {
		return orderUpdatedAt;
	}

	public void setOrderUpdatedAt(Timestamp orderUpdatedAt) {
		this.orderUpdatedAt = orderUpdatedAt;
	}

	public List<OrderItemsEntity> getOrdersItems() {
		return ordersItems;
	}

	public void setOrdersItems(List<OrderItemsEntity> ordersItems) {
		this.ordersItems = ordersItems;
	}

	public Integer getDeliveryStatusId() {
		return deliveryStatusId;
	}

	public void setDeliveryStatusId(Integer deliveryStatusId) {
		this.deliveryStatusId = deliveryStatusId;
	}

	public PaymentsEntity getPaymentsEntity() {
		return paymentsEntity;
	}

	public void setPaymentsEntity(PaymentsEntity paymentsEntity) {
		this.paymentsEntity = paymentsEntity;
	}

	public OrderStatusesEntity getOrderStatusesEntity() {
		return orderStatusesEntity;
	}

	public void setOrderStatusesEntity(OrderStatusesEntity orderStatusesEntity) {
		this.orderStatusesEntity = orderStatusesEntity;
	}

	public DeliveryStatusesEntity getDeliveryStatusesEntity() {
		return deliveryStatusesEntity;
	}

	public void setDeliveryStatusesEntity(DeliveryStatusesEntity deliveryStatusesEntity) {
		this.deliveryStatusesEntity = deliveryStatusesEntity;
	}

	public OrdersEntity(Integer customerId, Date orderDate, Double totalAmount, Integer storeId,
			Integer orderStatusId, Integer deliveryStatusId, Timestamp orderCreatedAt, Timestamp orderUpdatedAt) {
		super();
		this.customerId = customerId;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.storeId = storeId;
		this.orderStatusId = orderStatusId;
		this.deliveryStatusId = deliveryStatusId;
		this.orderCreatedAt = orderCreatedAt;
		this.orderUpdatedAt = orderUpdatedAt;
	}

	public OrdersEntity() {
		super();
	}
}