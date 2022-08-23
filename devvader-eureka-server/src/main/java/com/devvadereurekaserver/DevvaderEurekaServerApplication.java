package com.devvadereurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DevvaderEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevvaderEurekaServerApplication.class, args);
	}

}
