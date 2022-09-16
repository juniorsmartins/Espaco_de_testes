package com.devvadereurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer // Spring Cloud Netflix - Ativa a aplicação como servidor Eureka
public class DevvaderEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevvaderEurekaServerApplication.class, args);
	}
}
