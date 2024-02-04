package com.multimarkethub.orderservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;

@Entity
@Table(name = "paymentmethods")
public class PaymentMethodsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="paymentmethods_method_id_seq")
	@SequenceGenerator(name ="paymentmethods_method_id_seq", sequenceName="paymentmethods_method_id_seq", allocationSize=1)
	@Column(name="method_id")
	private Integer methodId;

	@Column(name="method_name")
	private String methodName;

	public Integer getMethodId() {
		return methodId;
	}

	public void setMethodId(Integer methodId) {
		this.methodId = methodId;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
}