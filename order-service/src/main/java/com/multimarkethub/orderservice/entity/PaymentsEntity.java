package com.multimarkethub.orderservice.entity;


import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.multimarkethub.orderservice.utils.JsonTimestampSerializer;

@Entity
@Table(name = "payments")
public class PaymentsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="payments_payment_id_seq")
	@SequenceGenerator(name ="payments_payment_id_seq", sequenceName="payments_payment_id_seq", allocationSize=1)
	@Column(name="payment_id")
	private Integer paymentId;

	@Column(name="order_id")
	private Integer orderId;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="payment_date")
	private Timestamp paymentDate;

	@Column(name="payment_amount")
	private Double paymentAmount;

	@Column(name="payment_status_id")
	private Integer paymentStatusId;

	@Column(name="payment_method_id")
	private Integer paymentMethodId;

	@Column(name="store_id")
	private Integer storeId;
	
    @OneToOne
    @JoinColumn(name = "payment_status_id", referencedColumnName = "status_id", insertable = false, updatable = false)
	private PaymentStatusEntity paymentStatusEntity;
    
    @OneToOne
    @JoinColumn(name = "payment_method_id", referencedColumnName = "method_id", insertable = false, updatable = false)
	private PaymentMethodsEntity paymentMethodsEntity;
    
    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id",insertable = false, updatable = false)
    private OrdersEntity ordersEntity;

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Timestamp getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public Integer getPaymentStatusId() {
		return paymentStatusId;
	}

	public void setPaymentStatusId(Integer paymentStatusId) {
		this.paymentStatusId = paymentStatusId;
	}

	public Integer getPaymentMethodId() {
		return paymentMethodId;
	}

	public void setPaymentMethodId(Integer paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	public PaymentStatusEntity getPaymentStatusEntity() {
		return paymentStatusEntity;
	}

	public void setPaymentStatusEntity(PaymentStatusEntity paymentStatusEntity) {
		this.paymentStatusEntity = paymentStatusEntity;
	}

	public PaymentMethodsEntity getPaymentMethodsEntity() {
		return paymentMethodsEntity;
	}

	public void setPaymentMethodsEntity(PaymentMethodsEntity paymentMethodsEntity) {
		this.paymentMethodsEntity = paymentMethodsEntity;
	}

	public PaymentsEntity(Integer orderId, Timestamp paymentDate, Double paymentAmount,
			Integer paymentStatusId, Integer paymentMethodId, Integer storeId) {
		super();
		this.orderId = orderId;
		this.paymentDate = paymentDate;
		this.paymentAmount = paymentAmount;
		this.paymentStatusId = paymentStatusId;
		this.paymentMethodId = paymentMethodId;
		this.storeId = storeId;
	}

	public PaymentsEntity() {
		super();
	}
}