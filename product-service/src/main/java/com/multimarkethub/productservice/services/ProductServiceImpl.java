package com.multimarkethub.productservice.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multimarkethub.productservice.beans.ProductReponse;
import com.multimarkethub.productservice.beans.ProductRequest;
import com.multimarkethub.productservice.entity.ProductsEntity;
import com.multimarkethub.productservice.exception.NotFoundException;
import com.multimarkethub.productservice.proxy.StoreServiceProxy;
import com.multimarkethub.productservice.repository.ProductsRepository;

import jakarta.validation.Valid;


@Service
public class ProductServiceImpl implements ProductService{
	
	
	private final StoreServiceProxy storeServiceProxy;
	private final CategoryService categoryService;
	private final ProductsRepository productsRepository;
	private final UnitService unitService;
	
	@Autowired
	public ProductServiceImpl(StoreServiceProxy storeServiceProxy,CategoryService categoryService,ProductsRepository productsRepository,UnitService unitService) {
		this.storeServiceProxy = storeServiceProxy;
		this.categoryService = categoryService;
		this.productsRepository =  productsRepository;
		this.unitService = unitService;
	}

	@Override
	public void createProduct(Integer storeId , ProductRequest productRequest) {
		
		
		if(Boolean.TRUE.equals(storeServiceProxy.getStoreById(storeId))) {
			Integer categoryId = categoryService.getCategoryByName(storeId, productRequest.getCategoryName());
			if(categoryId==0) {
				categoryId = categoryService.createCategory(storeId, productRequest.getCategoryName());
			}
			
			Timestamp timeStamp = productsRepository.findCurrentTimeStamp();
			
			ProductsEntity productsEntity = new ProductsEntity(productRequest.getProductName(), productRequest.getProductDescription(), productRequest.getProductPrice(), 
					productRequest.getProductStockQuantity(), categoryId, productRequest.getUnitId(), storeId,productRequest.getProductImageUrl(), timeStamp, timeStamp);
			productsRepository.save(productsEntity);
		}
		
	}

	@Override
	public List<ProductReponse> getProducts(Integer storeId, Integer productId) {
		List<ProductsEntity> productsEntityList = new ArrayList<>() ;
		if(productId == null) {
			productsEntityList = productsRepository.findAllByStoreId(storeId);
			if(productsEntityList.isEmpty()) {
				throw new NotFoundException("No products found. The requested operation cannot be completed.");
			}
		}else {
			ProductsEntity productsEntity = productsRepository.findByProductIdAndStoreId(productId, storeId);
			if(productsEntity == null) {
				throw new NotFoundException("Product with ID " + productId + " not found.");
			}else {
				productsEntityList.add(productsEntity);
			}
		}
		return mapToProductReponse(productsEntityList);
	}
	
	private List<ProductReponse> mapToProductReponse(List<ProductsEntity> productsEntityList) {
		List<ProductReponse> productReponsesList = new ArrayList<>();
		for(ProductsEntity productsEntity : productsEntityList) {
			productReponsesList.add(new ProductReponse(productsEntity.getProductId(), productsEntity.getProductName(), productsEntity.getProductDescription(), 
					productsEntity.getProductPrice(), productsEntity.getProductStockQuantity(), categoryService.getCategoryId(productsEntity.getCategoryId()).getCategoryName(),
					unitService.getUnits(productsEntity.getUnitId()).get(0).getUnitAbbreviation(), 
					productsEntity.getStoreId(), productsEntity.getProductImageUrl(), productsEntity.getProductCreatedAt(), productsEntity.getProductUpdatedAt()));
		}
		return productReponsesList;
	}

	@Override
	public List<ProductReponse> getByProductsCategoryIdAndStoreId(Integer storeId, Integer categoryId) {
		List<ProductsEntity> productsEntityList = productsRepository.findByStoreIdAndCategoryId(storeId, categoryId);
		if(productsEntityList.isEmpty()) {
			throw new NotFoundException("No products found. The requested operation cannot be completed.");
		}else {
			return mapToProductReponse(productsEntityList);
		}
	}

	@Override
	public String deleteProductById(Integer storeId, Integer productId) {
		if(productsRepository.findByProductIdAndStoreId(productId, storeId) != null) {
			productsRepository.deleteByProductIdAndStoreId(productId, storeId);
		}else {
			throw new NotFoundException("product with ID " + productId + " not found.");
		}
		return "Product with ID " + productId + " has been successfully deleted.";
	}

	@Override
	public ProductReponse updateProduct(Integer storeId, Integer productId, @Valid ProductRequest productRequest) {
		if(Boolean.TRUE.equals(storeServiceProxy.getStoreById(storeId))) {
			Integer categoryId = categoryService.getCategoryByName(storeId, productRequest.getCategoryName());
			if(categoryId==0) {
				categoryId = categoryService.createCategory(storeId, productRequest.getCategoryName());
			}
						
			productsRepository.updateProduct(productId, productRequest.getProductName(), productRequest.getProductDescription(), productRequest.getProductPrice(), 
					productRequest.getProductStockQuantity(), categoryId, productRequest.getUnitId(),productRequest.getProductImageUrl());
		}else {
			throw new NotFoundException("store with ID " + storeId + " not found.");
		}
		return getProducts(storeId,productId).get(0);
	}

}
