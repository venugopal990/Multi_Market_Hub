package com.multimarkethub.orderservice.beans;

import java.sql.Timestamp;

public class ProductReponse {
	
	private Integer productId;
	
	private String productName;

	private String productDescription;

	private Double productPrice;
	
	private Double productCartPrice;
	
	private Integer productStockQuantity;
	
	private Integer productCartQuantity;

	private String categoryName;

	private String unitName;

	private Integer storeId;

	private String productImageUrl;
	
	private Timestamp createdDate;
	
	private Timestamp updatedDate;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getProductStockQuantity() {
		return productStockQuantity;
	}

	public void setProductStockQuantity(Integer productStockQuantity) {
		this.productStockQuantity = productStockQuantity;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getUnit() {
		return unitName;
	}

	public void setUnit(String unitName) {
		this.unitName = unitName;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getProductImageUrl() {
		return productImageUrl;
	}

	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Double getProductCartPrice() {
		return productCartPrice;
	}

	public void setProductCartPrice(Double productCartPrice) {
		this.productCartPrice = productCartPrice;
	}

	public Integer getProductCartQuantity() {
		return productCartQuantity;
	}

	public void setProductCartQuantity(Integer productCartQuantity) {
		this.productCartQuantity = productCartQuantity;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public ProductReponse(Integer productId, String productName, String productDescription, Double productPrice,
			Integer productStockQuantity, String categoryName, String unitName, Integer storeId, String productImageUrl,
			Timestamp createdDate, Timestamp updatedDate) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.productStockQuantity = productStockQuantity;
		this.categoryName = categoryName;
		this.unitName = unitName;
		this.storeId = storeId;
		this.productImageUrl = productImageUrl;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

}
