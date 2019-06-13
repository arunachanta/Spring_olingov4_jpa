package com.walmart.rebates.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.walmart.rebates.dao.ItemDetail;
import com.walmart.rebates.dao.MerchHier;
import com.walmart.rebates.dao.Product;

import java.util.List;
@Repository
public interface MercHierRepository extends CrudRepository<MerchHier,Integer> {
	

} 