package com.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.model.Products;
import com.shopping.model.Productsize;
import com.shopping.model.Shipping;

@Repository
public interface ProductsizeRepository extends JpaRepository<Productsize, Long>{
	List<Productsize> findByProducts(Products products);

}
