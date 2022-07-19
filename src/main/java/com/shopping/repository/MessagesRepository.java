package com.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.model.Messages;

	
	@Repository
	public interface MessagesRepository extends JpaRepository<Messages, Long>{
	}
