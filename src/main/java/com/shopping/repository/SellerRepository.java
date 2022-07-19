package com.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.model.Customer;
import com.shopping.model.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long>{
	List<Seller> findByCustomerId(long customerId);
	List<Seller> findFirstByOrderByPicnum();
	List<Seller> findFirstByOrderByPicnumDesc();
}
