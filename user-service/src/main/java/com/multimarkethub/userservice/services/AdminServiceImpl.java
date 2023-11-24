package com.multimarkethub.userservice.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multimarkethub.userservice.beans.Admin;
import com.multimarkethub.userservice.entity.AdminEntity;
import com.multimarkethub.userservice.exception.NotFoundException;
import com.multimarkethub.userservice.repository.AdminsRepository;

@Service
public class AdminServiceImpl implements AdminService {
	
	private final AdminsRepository adminsRepository;
    private final PasswordService passwordService;

    @Autowired
    public AdminServiceImpl(AdminsRepository adminsRepository, PasswordService passwordService) {
        this.adminsRepository = adminsRepository;
        this.passwordService = passwordService;
    }

	@Override
	public Admin createAdmin(Admin admin) { 
		if(adminsRepository.countAdminByEmail(admin.getEmail())== 0) {
			AdminEntity  adminEntity = covertAdminToAdminEntity(admin);
			Timestamp timeStamp = adminsRepository.findCurrentTimeStamp();
			adminEntity.setAdminCreatedAt(timeStamp);
			adminEntity.setAdminUpdatedAt(timeStamp);
			adminEntity.setAdminEmailVerified(false);
			adminEntity.setAdminPassword(passwordService.gethashedPassword(admin.getPassword()));
			return covertAdminEntityToAdmin(adminsRepository.save(adminEntity));
		}else {
			throw new IllegalArgumentException("The email address " + admin.getEmail() + " is already associated with an existing admin.");
		}
	}
	
	@Override
	public List<Admin> getAdmins(Integer id) throws NotFoundException  {
		List<AdminEntity> adminEntityList = new ArrayList<>() ;
		if(id == null) {
			adminEntityList = adminsRepository.findAll();
			if(adminEntityList.isEmpty()) {
				throw new NotFoundException("No admins found. The requested operation cannot be completed.");
			}
		}else {
			Optional<AdminEntity> adminEntityOptional = adminsRepository.findById(id);
			if(adminEntityOptional.isEmpty()) {
				throw new NotFoundException("Admin with ID " + id + " not found.");
			}else {
				adminEntityList.add(adminEntityOptional.get());
			}
		}
		
		return covertAdminEntityListToAdminList(adminEntityList);
	}

	@Override
	public String deleteAdminById(Integer id) {
		if(adminsRepository.existsById(id)) {
			adminsRepository.deleteById(id);
		}else {
			throw new NotFoundException("Admin with ID " + id + " not found.");
		}
		return "Admin with ID " + id + " has been successfully deleted.";
	}

	@Override
	public Admin updateAdmin(Admin admin) throws NotFoundException {
		if(adminsRepository.existsById(admin.getId())) {
			adminsRepository.updateAdminDetails(admin.getId(), admin.getFirstName(), admin.getLastName(), admin.getAddress(), admin.getPhoneNumber(), 
					adminsRepository.findCurrentTimeStamp());
			return covertAdminEntityToAdmin(adminsRepository.findById(admin.getId()).get());
		}else {
			throw new NotFoundException("Admin with ID " + admin.getId() + " not found.");
		}
	}
	
	@Override
	public void updateStoreId(Integer storeId, Integer adminId) {
		try {
			System.out.println("Store id:"+storeId+":::AdminId::"+adminId);
			adminsRepository.updateStoreId(storeId,adminsRepository.findCurrentTimeStamp(), adminId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public  List<AdminEntity> covertAdminListToAdminEntityList(List<Admin> adminList) {
		List<AdminEntity> adminEntityList =  new ArrayList<AdminEntity>();
		for(Admin admin : adminList) {
			adminEntityList.add(covertAdminToAdminEntity(admin));
		}
		return adminEntityList;
	}
	
	public  AdminEntity covertAdminToAdminEntity(Admin admin) {
		  return new AdminEntity(admin.getId(), admin.getFirstName(), 
				admin.getLastName(), admin.getEmail(), admin.getPassword(),  admin.getAddress(), admin.getPhoneNumber(),
				admin.getStoreId());
	}

	
	
	public List<Admin> covertAdminEntityListToAdminList(List<AdminEntity> adminEntityList) {
		ArrayList<Admin> adminList =  new ArrayList<Admin>();
		for(AdminEntity adminEntity : adminEntityList) {
			adminList.add(covertAdminEntityToAdmin(adminEntity));
		}
		return adminList;
	}

	public Admin covertAdminEntityToAdmin(AdminEntity adminEntity) {
			return new Admin(adminEntity.getAdminId(), adminEntity.getAdminFirstName(), 
				adminEntity.getAdminLastName(), adminEntity.getAdminEmail(), 
				adminEntity.getAdminAddress(), adminEntity.getAdminPhoneNumber(),
				adminEntity.getStoreId(), adminEntity.getAdminEmailVerified(), adminEntity.getAdminCreatedAt(),
				adminEntity.getAdminUpdatedAt());
	}

	@Override
	public Boolean authenticateAdmin(String email, String password) {
		
		Optional<AdminEntity> adminEntityOptional =  adminsRepository.findAdminByEmail(email);
		if(!adminEntityOptional.isEmpty()) {
			AdminEntity adminEntity= adminEntityOptional.get();
			return passwordService.authenticateUser(password, adminEntity.getAdminPassword());
		}else {
			throw new NotFoundException("No admin found with the email address: " + email);
		}
	}

}
