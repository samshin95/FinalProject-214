package com.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.model.Game;

	
	@Repository
	public interface GameRepository extends JpaRepository<Game, Long>{
	}
