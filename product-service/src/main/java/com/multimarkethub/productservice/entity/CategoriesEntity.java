package com.multimarkethub.productservice.entity;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.multimarkethub.orderservice.entity.OrderItemsEntity;
import com.multimarkethub.productservice.utils.JsonTimestampSerializer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class CategoriesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="categories_category_id_seq")
	@SequenceGenerator(name ="categories_category_id_seq", sequenceName="categories_category_id_seq", allocationSize=1)
	@Column(name="category_id")
	private Integer categoryId;

	@Column(name="category_name")
	private String categoryName;

	@Column(name="store_id")
	private Integer storeId;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="category_created_at")
	private Timestamp categoryCreatedAt;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="category_updated_at")
	private Timestamp categoryUpdatedAt;
	
	@OneToMany(mappedBy="categories", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<ProductsEntity> productsEntities;
	
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Timestamp getCategoryCreatedAt() {
		return categoryCreatedAt;
	}

	public void setCategoryCreatedAt(Timestamp categoryCreatedAt) {
		this.categoryCreatedAt = categoryCreatedAt;
	}

	public Timestamp getCategoryUpdatedAt() {
		return categoryUpdatedAt;
	}

	public void setCategoryUpdatedAt(Timestamp categoryUpdatedAt) {
		this.categoryUpdatedAt = categoryUpdatedAt;
	}

	public List<ProductsEntity> getProductsEntities() {
		return productsEntities;
	}

	public void setProductsEntities(List<ProductsEntity> productsEntities) {
		this.productsEntities = productsEntities;
	}

	public CategoriesEntity(String categoryName, Integer storeId, Timestamp categoryCreatedAt,
			Timestamp categoryUpdatedAt) {
		super();
		this.categoryName = categoryName;
		this.storeId = storeId;
		this.categoryCreatedAt = categoryCreatedAt;
		this.categoryUpdatedAt = categoryUpdatedAt;
	}
	
	public CategoriesEntity() {
		
	}
	
}