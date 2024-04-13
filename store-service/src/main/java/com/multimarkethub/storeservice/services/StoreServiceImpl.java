package com.multimarkethub.storeservice.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multimarkethub.storeservice.beans.Admin;
import com.multimarkethub.storeservice.beans.Store;
import com.multimarkethub.storeservice.entity.StoreEntity;
import com.multimarkethub.storeservice.exception.NotFoundException;
import com.multimarkethub.storeservice.proxy.UserServiceProxy;
import com.multimarkethub.storeservice.repository.StoresRepository;


@Service
public class StoreServiceImpl implements StoreService{
	
    private static final Random random = new Random();
	
	private final StoresRepository storesRepository;
	
	
	private final UserServiceProxy userServiceProxy;
	
	@Autowired
	public StoreServiceImpl(StoresRepository storesRepository , UserServiceProxy userServiceProxy) {
		this.storesRepository = storesRepository;
		this.userServiceProxy = userServiceProxy;
	}
	
	
	
		
	@Override
	public Store createStore(Store store)  {
		List<Admin> adminList = userServiceProxy.getAdmins(store.getAdminId());
		if(!adminList.isEmpty()) {
			if(adminList.get(0).getStoreId() == null) {
				StoreEntity storeEntity = covertStoreToStoreEntity(store);
				Timestamp timeStamp = storesRepository.findCurrentTimeStamp();
				storeEntity.setStoreDomain(generateRandomNumericDomain(store.getName()));
				storeEntity.setStoreCreatedAt(timeStamp);
				storeEntity.setStoreUpdatedAt(timeStamp);
				Store resultStore = covertStoreEntityToStore(storesRepository.save(storeEntity));
				userServiceProxy.updateStoreId(store.getAdminId(), resultStore.getId());
				return resultStore;
			}else {
				throw new IllegalArgumentException("Unable to assign admin with ID " + store.getAdminId() + " to the store. This admin is already assigned to another store.");
			}
		}else {
			throw new NotFoundException("Admin with ID " + store.getAdminId() + " not found.");
		}

	}
	

	@Override
	public ArrayList<Store> getStores(Integer storeId) throws NotFoundException {
		List<StoreEntity> storeEntityList = new ArrayList<>() ;
		if(storeId == null) {
			storeEntityList = storesRepository.findAll();
			if(storeEntityList.isEmpty()) {
				throw new NotFoundException("No details found for the specified store.");
			}
		}else {
			Optional<StoreEntity> storeEntityOptional = storesRepository.findById(storeId);
			if(storeEntityOptional.isEmpty()) {
				throw new NotFoundException("Store with ID " + storeId + " not found.");
			}else {
				storeEntityList.add(storeEntityOptional.get());
			}
		}
		
		return covertStoreEntityListToStoreList(storeEntityList);
	}

	

	

	@Override
	public String deleteById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Store updateStore(Store admin) throws NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public StoreEntity covertStoreToStoreEntity(Store store) {
		  return new StoreEntity(store.getName(),store.getAddress(),store.getStoreImageUrl());
	}
	

	public Store covertStoreEntityToStore(StoreEntity storeEntity) {
		return new Store(storeEntity.getStoreId(),storeEntity.getStoreName(),storeEntity.getStoreAddress(),storeEntity.getStoreDomain(),storeEntity.getStoreImageUrl(),storeEntity.getStoreCreatedAt(),storeEntity.getStoreUpdatedAt());
	}
	
	
	private ArrayList<Store> covertStoreEntityListToStoreList(List<StoreEntity> storeEntityList) {
		ArrayList<Store> storeList =  new ArrayList<Store>();
		for(StoreEntity storeEntity : storeEntityList) {
			storeList.add(covertStoreEntityToStore(storeEntity));
		}
		return storeList;
	}
	
	
	private String generateRandomNumericDomain(String storeName) {
        String sanitizedStoreName = storeName.trim().toLowerCase().replace(" ", "_");
        String randomNumericSuffix = generateRandomNumericSuffix(6);
        return (sanitizedStoreName + randomNumericSuffix);
    }

    private String generateRandomNumericSuffix(int length) {
        String numericCharacters = "0123456789";
        
        StringBuilder randomNumericSuffix = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(numericCharacters.length());
            randomNumericSuffix.append(numericCharacters.charAt(index));
        }

        return randomNumericSuffix.toString();
    }




	@Override
	public Store getStoresDetailsByResourceName(String resourceName) {
		List<StoreEntity> storeEntityList ;
		if(resourceName != null) {
			storeEntityList = storesRepository.findByStoreDomain(resourceName);
			if(storeEntityList.isEmpty()) {
				throw new NotFoundException("No details found for the specified store name: "+resourceName);
			}else {
				return covertStoreEntityListToStoreList(storeEntityList).get(0);
			}
		}
		return null;
	}

	

}
