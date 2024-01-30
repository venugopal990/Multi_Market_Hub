package com.multimarkethub.userservice.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.multimarkethub.userservice.beans.Customer;
import com.multimarkethub.userservice.entity.AdminEntity;
import com.multimarkethub.userservice.entity.CustomerEntity;
import com.multimarkethub.userservice.exception.NotFoundException;
import com.multimarkethub.userservice.repository.CustomersRepository;


@Service
public class CustomerServiceImpl implements UserService {
	
	private final CustomersRepository customersRepository;
    private final PasswordService passwordService;

    @Autowired
    public CustomerServiceImpl(CustomersRepository customersRepository, PasswordService passwordService) {
        this.customersRepository = customersRepository;
        this.passwordService = passwordService;
    }

	@Override
	public List<Customer> getUsers(Integer id) throws NotFoundException {
		List<CustomerEntity> customerEntityList = new ArrayList<>() ;
		if(id == null) {
			customerEntityList = customersRepository.findAll();
			if(customerEntityList.isEmpty()) {
				throw new NotFoundException("No Customer found. The requested operation cannot be completed.");
			}
		}else {
			Optional<CustomerEntity> customerEntityOptional = customersRepository.findById(id);
			if(customerEntityOptional.isEmpty()) {
				throw new NotFoundException("Customer with ID " + id + " not found.");
			}else {
				customerEntityList.add(customerEntityOptional.get());
			}
		}
		return covertCustomerEntityListToCustomerList(customerEntityList);
	}

	private List<Customer> covertCustomerEntityListToCustomerList(List<CustomerEntity> customerEntityList) {
		ArrayList<Customer> customersList =  new ArrayList<>();
		for(CustomerEntity customerEntity : customerEntityList) {
			customersList.add(covertCustomersEntityToCustomer(customerEntity));
		}
		return customersList;
	}

	private Customer covertCustomersEntityToCustomer(CustomerEntity customerEntity) {
		return new Customer(customerEntity.getCustomerId(), customerEntity.getCustomerFirstName(), customerEntity.getCustomerLastName(), customerEntity.getCustomerEmail(), customerEntity.getCustomerAddress(),
				customerEntity.getCustomerPhoneNumber(), customerEntity.getStoreId(), customerEntity.getCustomerEmailVerified(), customerEntity.getCustomerCreatedAt(), customerEntity.getCustomerUpdatedAt());
	}
	
	private CustomerEntity covertCustomerToCustomerEntity(Customer customer) {
		
		return new CustomerEntity(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getPassword(), customer.getAddress(), customer.getPhoneNumber(), customer.getStoreId());
		
	}

	@Override
	public Object createUser(Object customer) {
		if(customersRepository.countCustomerByEmail(((Customer)customer).getEmail())== 0) {
			CustomerEntity  customerEntity = covertCustomerToCustomerEntity((Customer)customer);
			Timestamp timeStamp = customersRepository.findCurrentTimeStamp();
			customerEntity.setCustomerCreatedAt(timeStamp);
			customerEntity.setCustomerUpdatedAt(timeStamp);
			customerEntity.setCustomerEmailVerified(false);
			customerEntity.setCustomerPassword(passwordService.gethashedPassword(((Customer)customer).getPassword()));
			return covertCustomersEntityToCustomer(customersRepository.save(customerEntity));
		}else {
			throw new IllegalArgumentException("The email address " + ((Customer)customer).getEmail() + " is already associated with an existing admin.");
		}
	}

	

	@Override
	public Object updateUser(Object customer) throws NotFoundException {
		if(customersRepository.existsById(((Customer)customer).getId())) {
			customersRepository.updateCustomerDetails(((Customer)customer).getId(), ((Customer)customer).getFirstName(), ((Customer)customer).getLastName(), ((Customer)customer).getAddress(), ((Customer)customer).getPhoneNumber(), 
					customersRepository.findCurrentTimeStamp(), ((Customer)customer).getStoreId());
			return covertCustomersEntityToCustomer(customersRepository.findById(((Customer)customer).getId()).get());
		}else {
			throw new NotFoundException("Customer with ID " + ((Customer)customer).getId() + " not found.");
		}
	}
	
	
	@Override
	public String deleteUserById(Integer userId) {
		if(customersRepository.existsById(userId)) {
			customersRepository.deleteById(userId);
		}else {
			throw new NotFoundException("Customer with ID " + userId + " not found.");
		}
		return "Customer with ID " + userId + " has been successfully deleted.";
	}

	

	@Override
	public Object authenticateUser(String email, String password) {
		Optional<CustomerEntity> customerEntityOptional =  customersRepository.findCustomerByEmail(email);
		if(!customerEntityOptional.isEmpty()) {
			CustomerEntity customerEntity= customerEntityOptional.get();
			if(passwordService.authenticateUser(password, customerEntity.getCustomerPassword())) {
				return covertCustomersEntityToCustomer(customerEntity);
			}else {
				return null;
			}
		}else {
			throw new NotFoundException("No customer found with the email address: " + email);
		}
	}
	
	@Override
	public void updateStoreId(Integer storeId, Integer userId) {
		// TODO Auto-generated method stub
		
	}

}
