package com.multimarkethub.productservice.beans;


public class Unit {
	
	
	private Integer unitId;

	private String unitName;

	private String unitAbbreviation;

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

	public Unit(Integer unitId, String unitName, String unitAbbreviation) {
		super();
		this.unitId = unitId;
		this.unitName = unitName;
		this.unitAbbreviation = unitAbbreviation;
	}
	
	
	

}
