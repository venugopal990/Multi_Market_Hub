package com.multimarkethub.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.multimarkethub.productservice.beans.Category;
import com.multimarkethub.productservice.services.CategoryService;

@RestController
public class CategoryController {
	
	

	private final CategoryService categoryService;
	
	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	
	@GetMapping("/stores/{storeId}/categories")
	public List<Category> getCategories(@PathVariable Integer storeId) {
		return categoryService.getCategories(storeId);
	}
	
	
	
	
	

}
