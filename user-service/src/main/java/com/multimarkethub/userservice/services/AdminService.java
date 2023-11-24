package com.multimarkethub.userservice.services;

import java.util.List;

import com.multimarkethub.userservice.beans.Admin;
import com.multimarkethub.userservice.exception.NotFoundException;

public interface AdminService {

	public List<Admin> getAdmins(Integer adminId) throws NotFoundException;

	public Admin createAdmin(Admin admin);

	public String deleteAdminById(Integer adminId);

	public Admin updateAdmin(Admin admin) throws NotFoundException;

	public void updateStoreId(Integer storeId, Integer adminId);

	public Boolean authenticateAdmin(String email, String password);
	

}
