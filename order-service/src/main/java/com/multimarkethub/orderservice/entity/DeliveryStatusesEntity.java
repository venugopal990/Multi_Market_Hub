package com.multimarkethub.orderservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;

@Entity
@Table(name = "deliverystatuses")
public class DeliveryStatusesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="deliverystatuses_status_id_seq")
	@SequenceGenerator(name ="deliverystatuses_status_id_seq", sequenceName="deliverystatuses_status_id_seq", allocationSize=1)
	@Column(name="status_id")
	private Integer statusId;

	@Column(name="status_name")
	private String statusName;

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	
	
}