package com.example.projectjavaserverdlhersey.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.projectjavaserverdlhersey.models.Favorite;

public interface FavoriteRepository 
	extends CrudRepository<Favorite, Integer>{

}
