package com.devvadercursos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // Spring Cloud Netflix - informa à aplicação da necessidade de se registrar num server Eureka
public class DevvaderCursosApplication {

	private static Logger logger = LoggerFactory.getLogger(DevvaderCursosApplication.class);

	public static void main(String[] args) {
		logger.info("Aplicação iniciada!");
		SpringApplication.run(DevvaderCursosApplication.class, args);
		logger.info("Microserviço para cadastrar cursos em Portfólio.");
	}

}
