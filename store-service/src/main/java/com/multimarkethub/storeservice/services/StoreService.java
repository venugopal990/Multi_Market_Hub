package com.multimarkethub.storeservice.services;

import java.util.List;

import com.multimarkethub.storeservice.beans.Store;
import com.multimarkethub.storeservice.exception.NotFoundException;

public interface StoreService {
	
	
	public List<Store> getStores(Integer storeId) throws NotFoundException;

	public Store createStore(Store store);

	public String deleteById(Integer storeId);

	public Store updateStore(Store admin) throws NotFoundException;

}
