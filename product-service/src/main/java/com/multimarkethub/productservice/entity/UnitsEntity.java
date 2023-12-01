package com.multimarkethub.productservice.entity;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.multimarkethub.productservice.utils.JsonTimestampSerializer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "units")
public class UnitsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="units_unit_id_seq")
	@SequenceGenerator(name ="units_unit_id_seq", sequenceName="units_unit_id_seq", allocationSize=1)
	@Column(name="unit_id")
	private Integer unitId;

	@Column(name="unit_name")
	private String unitName;

	@Column(name="unit_abbreviation")
	private String unitAbbreviation;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="unit_created_at")
	private Timestamp unitCreatedAt;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="unit_updated_at")
	private Timestamp unitUpdatedAt;
	

	public Integer getUnitId() {
		return unitId;
	}


	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}


	public String getUnitName() {
		return unitName;
	}


	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}


	public String getUnitAbbreviation() {
		return unitAbbreviation;
	}


	public void setUnitAbbreviation(String unitAbbreviation) {
		this.unitAbbreviation = unitAbbreviation;
	}


	public Timestamp getUnitCreatedAt() {
		return unitCreatedAt;
	}


	public void setUnitCreatedAt(Timestamp unitCreatedAt) {
		this.unitCreatedAt = unitCreatedAt;
	}


	public Timestamp getUnitUpdatedAt() {
		return unitUpdatedAt;
	}


	public void setUnitUpdatedAt(Timestamp unitUpdatedAt) {
		this.unitUpdatedAt = unitUpdatedAt;
	}

	
	
}