package com.multimarkethub.productservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import jakarta.annotation.PostConstruct;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
//@PropertySource("file:D:\\Multi_market_Hub_Files\\config\\aws.properties")
@PropertySource("file:/app/config/aws.properties")
public class AwsConfig {

	
    @Value("${aws.region}")
    private String awsRegion; 

    @Value("${aws.accessKeyId}")
    private String awsAccessKeyId;

    @Value("${aws.secretKey}")
    private String awsSecretKey;
    
	@Bean
    public S3Client s3Client() {
        AwsCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(AwsBasicCredentials.create(awsAccessKeyId, awsSecretKey));

        return S3Client.builder()
        		.region(Region.of(awsRegion))
                .credentialsProvider(credentialsProvider)
                .build();
    }
	
	
	


}
