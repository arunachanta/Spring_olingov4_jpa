package com.walmart.rebates.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.walmart.rebates.dao.Product;
import com.walmart.rebates.services.ProductSrv;

public class ProductControllers {
	
	@Autowired 
	ProductSrv service;
	
    @GetMapping
    public Iterable<Product> getProducts() {
        return service.getProducts();
    }

}
