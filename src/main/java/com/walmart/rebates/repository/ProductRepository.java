package com.walmart.rebates.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.walmart.rebates.dao.Product;

import java.util.List;


@Repository
public interface ProductRepository extends CrudRepository<Product,Integer> {
    List<Product> findByName(String name);
}
