package com.walmart.rebates.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.rebates.dao.Product;
import com.walmart.rebates.repository.ProductRepository;

@RestController
@SpringBootApplication
@ComponentScan("com.walmart.rebates")
@EntityScan("com.walmart.rebates.entities")
public class RebatesApplication {
	
	

	public static void main(String[] args) {
		SpringApplication.run(RebatesApplication.class, args);
	}
	
	@GetMapping(value="/")
	public String callService() {
		return "Hay You started";
	}

}
