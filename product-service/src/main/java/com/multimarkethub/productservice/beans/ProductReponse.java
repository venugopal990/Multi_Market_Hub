package com.multimarkethub.productservice.beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class ProductReponse implements Serializable{
	
	private Integer productId;
	
	private String productName;

	private String productDescription;

	private Double productPrice;

	private Integer productStockQuantity;

	private String categoryName;
	
	private Integer categoryId;

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

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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

	public ProductReponse(Integer productId, String productName, String productDescription, Double productPrice,
			Integer productStockQuantity, String categoryName, String unitName, Integer storeId, String productImageUrl,
			Timestamp createdDate, Timestamp updatedDate,Integer categoryId) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.productStockQuantity = productStockQuantity;
		this.categoryName = categoryName;
		this.categoryId = categoryId;
		this.unitName = unitName;
		this.storeId = storeId;
		this.productImageUrl = productImageUrl;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

}
