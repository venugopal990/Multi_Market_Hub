package com.multimarkethub.userservice.services;

import java.util.List;

import com.multimarkethub.userservice.beans.LoginRequest;
import com.multimarkethub.userservice.exception.NotFoundException;

public interface UserService {

	public List<?> getUsers(Integer userId, Integer storeId) throws NotFoundException;

	public Object createUser(Object user);

	public String deleteUserById(Integer userId);

	public Object updateUser(Object user) throws NotFoundException;

	public void updateStoreId(Integer storeId, Integer userId);

	public Object authenticateUser(LoginRequest loginRequest);

	public void updateVerifiedEmail(List<String> emailsList);
	

}
