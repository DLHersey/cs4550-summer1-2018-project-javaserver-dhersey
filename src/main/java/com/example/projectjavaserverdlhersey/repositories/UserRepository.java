package com.example.projectjavaserverdlhersey.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.projectjavaserverdlhersey.models.User;

public interface UserRepository 
	extends CrudRepository<User, Integer>{

}
