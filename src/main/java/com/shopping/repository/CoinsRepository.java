package com.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.model.Coins;

	
	@Repository
	public interface CoinsRepository extends JpaRepository<Coins, Long>{
	}
