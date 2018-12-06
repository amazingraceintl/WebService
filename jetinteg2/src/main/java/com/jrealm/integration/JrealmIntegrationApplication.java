package com.jrealm.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class JrealmIntegrationApplication {

	public static void main(String[] args) {
//		SpringApplication.run(JrealmIntegrationApplication.class, args);
		SpringApplication app = new SpringApplication(JrealmIntegrationApplication.class);
		app.setDefaultProperties(Collections
				.singletonMap("server.port", "8083"));
		app.run(args);
	}
}
