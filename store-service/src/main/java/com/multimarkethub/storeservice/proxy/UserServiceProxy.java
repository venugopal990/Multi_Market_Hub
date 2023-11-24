package com.multimarkethub.storeservice.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.multimarkethub.storeservice.beans.Admin;


@FeignClient(name="user-service")
public interface UserServiceProxy {
	
	@GetMapping("/admins/{id}")
	public List<Admin> getAdmins(@PathVariable Integer id);
	
	
	@PutMapping("/admins/{adminId}/store/{storeId}")
	public void updateStoreId(@PathVariable Integer adminId, @PathVariable Integer storeId);

}
