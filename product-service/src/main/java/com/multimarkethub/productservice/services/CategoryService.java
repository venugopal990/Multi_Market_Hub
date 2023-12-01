package com.multimarkethub.productservice.services;

import java.util.List;

import com.multimarkethub.productservice.beans.Category;

public interface CategoryService {

	public Integer createCategory(Integer storeId, String categoryName);

	public Integer getCategoryByName(Integer storeId, String categoryName);

	public Category getCategoryId(Integer categoryId);

	public List<Category> getCategories(Integer storeId);

}
