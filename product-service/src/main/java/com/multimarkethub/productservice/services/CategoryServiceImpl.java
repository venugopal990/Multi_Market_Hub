package com.multimarkethub.productservice.services;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multimarkethub.productservice.beans.Category;
import com.multimarkethub.productservice.entity.CategoriesEntity;
import com.multimarkethub.productservice.exception.NotFoundException;
import com.multimarkethub.productservice.repository.CategoryRepository;
import com.multimarkethub.productservice.utils.Utils;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	
	private final CategoryRepository categoryRepository;
	
	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Integer createCategory(Integer storeId, String categoryName) {
		Timestamp timeStamp = categoryRepository.findCurrentTimeStamp();
		CategoriesEntity categoriesEntity = new CategoriesEntity(Utils.capitalizeEachWord(categoryName), storeId, timeStamp, timeStamp);
		return categoryRepository.save(categoriesEntity).getCategoryId();
	}

	@Override
	public Integer getCategoryByName(Integer storeId, String categoryName) {
		List<CategoriesEntity> categoriesEntityList = categoryRepository.findByStoreIdAndCategoryName(storeId, Utils.capitalizeEachWord(categoryName));
		if(categoriesEntityList.isEmpty()) {
			//throw new NotFoundException("No category found with the name "+categoryName);
			return 0;
		}else {
			return categoriesEntityList.get(0).getCategoryId();
		}
	}
	


	@Override
	public Category getCategoryId(Integer categoryId) {
		Optional<CategoriesEntity> categoriesEntityOptional = categoryRepository.findById(categoryId);
		if(categoriesEntityOptional.isEmpty()) {
			throw new NotFoundException("No category found with the Id: "+categoryId); 
		}else{
			return new Category(categoryId, categoriesEntityOptional.get().getCategoryName());
		}
	}

	@Override
	public List<Category> getCategories(Integer storeId) {
		List<CategoriesEntity> categoriesEntityList = categoryRepository.findByStoreId(storeId);
		if(categoriesEntityList.isEmpty()) {
			throw new NotFoundException("No categories found with the storeId : "+storeId);
		}else {
			return mapToCategories(categoriesEntityList);
		}
		
	}
	
	private List<Category> mapToCategories(List<CategoriesEntity> categoriesEntityList){
		List<Category> categoriesList = new ArrayList<Category>();
		
		for(CategoriesEntity categoriesEntity:categoriesEntityList) {
			categoriesList.add(new Category(categoriesEntity.getCategoryId(), categoriesEntity.getCategoryName()));
		}
		
		return categoriesList;
		
	}

}
