package com.multimarkethub.productservice.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
	
	String uploadImage(MultipartFile file);

}
