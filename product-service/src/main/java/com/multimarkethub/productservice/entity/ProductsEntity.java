package com.multimarkethub.productservice.entity;

import java.sql.Timestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.multimarkethub.productservice.utils.JsonTimestampSerializer;

@Entity
@Table(name = "products")
public class ProductsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="products_product_id_seq")
	@SequenceGenerator(name ="products_product_id_seq", sequenceName="products_product_id_seq", allocationSize=1)
	@Column(name="product_id")
	private Integer productId;

	@Column(name="product_name")
	private String productName;

	@Column(name="product_description")
	private String productDescription;

	@Column(name="product_price")
	private Double productPrice;

	@Column(name="product_stock_quantity")
	private Integer productStockQuantity;

	@Column(name="category_id")
	private Integer categoryId;

	@Column(name="unit_id")
	private Integer unitId;

	@Column(name="store_id")
	private Integer storeId;

	@Column(name="product_image_url")
	private String productImageUrl;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="product_created_at")
	private Timestamp productCreatedAt;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="product_updated_at")
	private Timestamp productUpdatedAt;
	

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

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
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

	public Timestamp getProductCreatedAt() {
		return productCreatedAt;
	}

	public void setProductCreatedAt(Timestamp productCreatedAt) {
		this.productCreatedAt = productCreatedAt;
	}

	public Timestamp getProductUpdatedAt() {
		return productUpdatedAt;
	}

	public void setProductUpdatedAt(Timestamp productUpdatedAt) {
		this.productUpdatedAt = productUpdatedAt;
	}

	public ProductsEntity(String productName, String productDescription, Double productPrice,
			Integer productStockQuantity, Integer categoryId, Integer unitId, Integer storeId, String productImageUrl,
			Timestamp productCreatedAt, Timestamp productUpdatedAt) {
		super();
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.productStockQuantity = productStockQuantity;
		this.categoryId = categoryId;
		this.unitId = unitId;
		this.storeId = storeId;
		this.productImageUrl = productImageUrl;
		this.productCreatedAt = productCreatedAt;
		this.productUpdatedAt = productUpdatedAt;
	}
	

	
	public ProductsEntity() {
		
	}
}