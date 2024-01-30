package com.multimarkethub.userservice.beans;

import java.sql.Timestamp;

public class Admin extends UserDetails{

	public Admin(Integer id, String firstName, String lastName, String email, String address,
			String phoneNumber, Integer storeId, boolean isEmailIsVerified, Timestamp createdDate, Timestamp updatedDate) {
		super(id, firstName, lastName, email, address, phoneNumber, storeId, isEmailIsVerified, createdDate,
				updatedDate);
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
}
