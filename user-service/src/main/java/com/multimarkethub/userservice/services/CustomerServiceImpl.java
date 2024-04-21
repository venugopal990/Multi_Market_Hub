package com.multimarkethub.userservice.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.multimarkethub.userservice.beans.Customer;
import com.multimarkethub.userservice.beans.LoginRequest;
import com.multimarkethub.userservice.entity.AdminEntity;
import com.multimarkethub.userservice.entity.CustomerEntity;
import com.multimarkethub.userservice.exception.NotFoundException;
import com.multimarkethub.userservice.proxy.OrderServiceProxy;
import com.multimarkethub.userservice.repository.CustomersRepository;


@Service
public class CustomerServiceImpl implements UserService {
	
	private final CustomersRepository customersRepository;
    private final PasswordService passwordService;
    private final OrderServiceProxy orderServiceProxy;


    @Autowired
    public CustomerServiceImpl(CustomersRepository customersRepository, PasswordService passwordService, OrderServiceProxy orderServiceProxy) {
        this.customersRepository = customersRepository;
        this.passwordService = passwordService;
        this.orderServiceProxy = orderServiceProxy;
    }

	@Override
	public List<Customer> getUsers(Integer id,Integer storeId) throws NotFoundException {
		List<CustomerEntity> customerEntityList = new ArrayList<>();
		orderServiceProxy.verifiedEmails();
		if(id == null && storeId == null) {
			customerEntityList = customersRepository.findAll();
			if(customerEntityList.isEmpty()) {
				throw new NotFoundException("No Customer found. The requested operation cannot be completed.");
			}
		}else if(id == null) {
			customerEntityList = customersRepository.findByStoreId(storeId);
			if(customerEntityList.isEmpty()) {
				throw new NotFoundException("No Customer found. The requested operation cannot be completed.");
			}
		}else {
			CustomerEntity customerEntity = customersRepository.findByCustomerIdAndStoreId(id, storeId);
			if(customerEntity == null) {
				throw new NotFoundException("Customer with ID " + id + " not found.");
			}else {
				customerEntityList.add(customerEntity);
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
		
		return new CustomerEntity(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getPassword(), customer.getAddress(), customer.getPhoneNumber(), customer.getStoreId(),
				customer.isEmailIsVerified());
		
	}

	@Override
	public Object createUser(Object customer) {
		if(customersRepository.countCustomerByEmail(((Customer)customer).getEmail(),((Customer)customer).getStoreId())== 0) {
			CustomerEntity  customerEntity = covertCustomerToCustomerEntity((Customer)customer);
			Timestamp timeStamp = customersRepository.findCurrentTimeStamp();
			customerEntity.setCustomerCreatedAt(timeStamp);
			customerEntity.setCustomerUpdatedAt(timeStamp);
			customerEntity.setCustomerPassword(passwordService.gethashedPassword(((Customer)customer).getPassword()));
			orderServiceProxy.sendVerificationEmail(((Customer)customer).getEmail());
			return covertCustomersEntityToCustomer(customersRepository.save(customerEntity));
		}else {
			throw new IllegalArgumentException("The email address " + ((Customer)customer).getEmail() + " is already associated with an existing customer.");
		}
	}

	

	@Override
	public Object updateUser(Object customer) throws NotFoundException {
		if(customersRepository.existsById(((Customer)customer).getId())) {
			if(((Customer)customer).getStoreId() == null) {
				throw new NotFoundException("Store ID not found.");
			}
			customersRepository.updateCustomerDetails(((Customer)customer).getId(), ((Customer)customer).getFirstName(), ((Customer)customer).getLastName(), ((Customer)customer).getAddress(), ((Customer)customer).getPhoneNumber(), 
					customersRepository.findCurrentTimeStamp(), ((Customer)customer).getStoreId()  , ((Customer)customer).isEmailIsVerified());
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
	public Object authenticateUser(LoginRequest loginRequest) {
		Optional<CustomerEntity> customerEntityOptional =  customersRepository.findCustomerByEmailAndStoreId(loginRequest.getEmail(),loginRequest.getStoreId());
		if(!customerEntityOptional.isEmpty()) {
			CustomerEntity customerEntity= customerEntityOptional.get();
			if(passwordService.authenticateUser(loginRequest.getPassword(), customerEntity.getCustomerPassword())) {
				return covertCustomersEntityToCustomer(customerEntity);
			}else {
				return null;
			}
		}else {
			throw new NotFoundException("No customer found with the email address: " + loginRequest.getEmail());
		}
	}
	
	@Override
	public void updateStoreId(Integer storeId, Integer userId) {
   // TODO document why this method is empty
 }

	@Override
	public void updateVerifiedEmail(List<String> emailsList) {
		customersRepository.updateVerifiedEmail(emailsList,customersRepository.findCurrentTimeStamp());
	}

}
