package com.shopping.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.model.Coupon;

	
	@Repository
	public interface CouponRepository extends JpaRepository<Coupon, Long>{
	}
