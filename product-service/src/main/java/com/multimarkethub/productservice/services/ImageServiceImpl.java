package com.multimarkethub.productservice.services;

import java.io.IOException;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;


@Service
public class ImageServiceImpl  implements ImageService{
	
	
	private final S3Client s3Client;

    public ImageServiceImpl(S3Client s3Client) {
        this.s3Client = s3Client;
    }


    @Value("${aws.bucketName}")
    private String bucketName;
    
    
    @Override
    public String uploadImage(MultipartFile file) {
        try {
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            String key = "images/" + fileName;
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
            
            return s3Client.utilities().getUrl(GetUrlRequest.builder().bucket(bucketName).key(key).build()).toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to upload image";
        }
    }

}
