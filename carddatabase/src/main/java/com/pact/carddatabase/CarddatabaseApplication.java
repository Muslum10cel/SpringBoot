package com.pact.carddatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarddatabaseApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(CarddatabaseApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(CarddatabaseApplication.class, args);
		LOGGER.info("Hello Spring Boot...");
	}
}
