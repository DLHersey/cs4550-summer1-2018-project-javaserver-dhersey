package com.example.projectjavaserverdlhersey.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.projectjavaserverdlhersey.models.User;
import com.example.projectjavaserverdlhersey.repositories.UserRepository;

@RestController
public class UserService {
	@Autowired
	UserRepository repository;
	
	//CREATE
	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		return repository.save(user);
	}
	
	//READ
	@GetMapping("/api/user")
	public Iterable<User> findAllUsers() {
		return repository.findAll();
	}
	
	//UPDATE
	@PostMapping("/api/user/{uid}")
	public User updateUser(@PathVariable("uid") int uid, @RequestBody User nUser) {
		Optional<User> data = repository.findById(uid);
		if(data.isPresent()) {
			User user = data.get();
			user.setUsername(nUser.getUsername());
			user.setPassword(nUser.getPassword());
			user.setRole(nUser.getRole());
			user.setEmailAddr(nUser.getEmailAddr());
			user.setFavorites(nUser.getFavorites());
			return user;
		}
		return null;
	}
	
	//DELETE
	@PutMapping("/api/user/{uid}")
	public void deleteUser(@PathVariable("uid") int uid) {
		repository.deleteById(uid);
	}
}
