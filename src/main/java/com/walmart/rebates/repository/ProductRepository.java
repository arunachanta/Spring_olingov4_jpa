package com.walmart.rebates.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.walmart.rebates.dao.Product;

import java.util.List;

/**
 * Created by Walla on 15/07/2016.
 */
@Repository
public interface ProductRepository extends CrudRepository<Product,Integer> {
    List<Product> findByName(String name);
}
