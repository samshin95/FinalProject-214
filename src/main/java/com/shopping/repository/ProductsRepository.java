package com.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.model.Customer;
import com.shopping.model.Products;
import com.shopping.model.Seller;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long>{
	List<Products> findByProductName(String productName);
	List<Products> findBySeller(Seller seller);
	List<Products> findFirstByOrderByPicnumDesc();
	List<Products> findByOrderByCreateTime();
	List<Products> findByOrderByCreateTimeDesc();
	List<Products> findByOrderByProductprice();
	List<Products> findByOrderByProductpriceDesc();
}
