package com.devvadergateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DevvaderGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevvaderGatewayApplication.class, args);
	}
}
