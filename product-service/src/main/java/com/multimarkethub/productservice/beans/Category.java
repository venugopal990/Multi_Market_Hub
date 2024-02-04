package com.multimarkethub.productservice.beans;

public class Category {
	
	private Integer categoryId;

	private String categoryName;
	
	private Integer productCount;
	

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


	public Integer getProductCount() {
		return productCount;
	}

	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}

	public Category(Integer categoryId, String categoryName, Integer productCount) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.productCount = productCount;
	}

	
	
	

}
