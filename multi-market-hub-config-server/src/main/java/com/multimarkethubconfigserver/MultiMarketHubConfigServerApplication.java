package com.multimarkethubconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class MultiMarketHubConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiMarketHubConfigServerApplication.class, args);
	}

}
