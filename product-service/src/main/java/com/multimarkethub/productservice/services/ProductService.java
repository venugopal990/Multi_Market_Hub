package com.multimarkethub.productservice.services;

import java.util.List;

import com.multimarkethub.productservice.beans.ProductReponse;
import com.multimarkethub.productservice.beans.ProductRequest;

import jakarta.validation.Valid;

public interface ProductService {

	public void createProduct(ProductRequest productRequest);

	public List<ProductReponse> getProducts(Integer productId);

	public List<ProductReponse> getByProductsCategoryIdAndStoreId(Integer storeId, Integer categoryId);

	public String deleteProductById(Integer productId);

	public ProductReponse updateProduct(Integer productId, @Valid ProductRequest productRequest);

}
