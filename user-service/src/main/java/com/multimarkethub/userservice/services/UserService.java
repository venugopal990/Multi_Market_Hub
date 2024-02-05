package com.multimarkethub.userservice.services;

import java.util.List;

import com.multimarkethub.userservice.beans.Admin;
import com.multimarkethub.userservice.beans.Customer;
import com.multimarkethub.userservice.exception.NotFoundException;

public interface UserService {

	public List<?> getUsers(Integer userId) throws NotFoundException;

	public Object createUser(Object user);

	public String deleteUserById(Integer userId);

	public Object updateUser(Object user) throws NotFoundException;

	public void updateStoreId(Integer storeId, Integer userId);

	public Object authenticateUser(String email, String password);

	public void updateVerifiedEmail(List<String> emailsList);
	

}
