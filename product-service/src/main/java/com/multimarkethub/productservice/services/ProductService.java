package com.multimarkethub.productservice.services;

import java.util.List;

import com.multimarkethub.productservice.beans.ProductReponse;
import com.multimarkethub.productservice.beans.ProductRequest;

import jakarta.validation.Valid;

public interface ProductService {

	public void createProduct(Integer storeId, ProductRequest productRequest);

	public List<ProductReponse> getProducts(Integer storeId, Integer productId);

	public List<ProductReponse> getByProductsCategoryIdAndStoreId(Integer storeId, Integer categoryId);

	public String deleteProductById(Integer storeId, Integer productId);

	public ProductReponse updateProduct(Integer storeId, Integer productId, @Valid ProductRequest productRequest);

	public List<ProductReponse> searchProducts(Integer storeId,String productName);

	public void updateProductStock(Integer storeId, Integer productId, Integer stockQuantity);

	public void createDummyProduct(Integer storeId, @Valid List<ProductRequest> productRequestList);

}
