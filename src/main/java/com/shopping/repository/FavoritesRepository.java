package com.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.model.Favorites;


@Repository
	public interface FavoritesRepository extends JpaRepository<Favorites, Long>{
	}
