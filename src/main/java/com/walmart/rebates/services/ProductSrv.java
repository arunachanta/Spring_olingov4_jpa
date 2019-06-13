package com.walmart.rebates.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.rebates.dao.Product;
import com.walmart.rebates.repository.ProductRepository;
import com.walmart.rebates.restcontrollers.ProductDto;

@SpringBootApplication
@RestController
public class ProductSrv {
	@Autowired
	ProductRepository productRepository;
	
	
	@GetMapping(value="/productData")
	public Iterable<Product> CallProductData() {
		return productRepository.findAll();
	}

	@GetMapping ("checkParam")
	public List<Product> CallProductDataByName(@RequestParam String name) {
		return productRepository.findByName(name);
	}

	public Iterable<Product> getProducts() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	public void add(ProductDto dto) {
		// TODO Auto-generated method stub
		
	}
	

}
