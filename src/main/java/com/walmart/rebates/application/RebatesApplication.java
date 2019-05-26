package com.walmart.rebates.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
@Configuration
@ComponentScan("com.walmart.rebates")
public class RebatesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RebatesApplication.class, args);
	}
	
	@GetMapping(value="/")
	public String callService() {
		return "Hay You started";
	}

}
