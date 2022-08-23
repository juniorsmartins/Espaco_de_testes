package com.devvaderclientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DevvaderClientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevvaderClientesApplication.class, args);
	}
}
