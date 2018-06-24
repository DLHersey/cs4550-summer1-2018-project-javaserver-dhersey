package com.example.projectjavaserverdlhersey.services;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.projectjavaserverdlhersey.models.User;
import com.example.projectjavaserverdlhersey.repositories.UserRepository;


@CrossOrigin(origins = "*", maxAge = 3600)
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
	@DeleteMapping("/api/user/{uid}")
	public void deleteUser(@PathVariable("uid") int uid) {
		repository.deleteById(uid);
	}
	
	//LOGIN
	@PostMapping("/api/login")
	public User login(@RequestBody User credentials, HttpSession session) {
		Optional<User> data = repository.findUserByCredentials(credentials.getUsername(), credentials.getPassword());
		if(data.isPresent()) {
			session.setAttribute("currentUser", data.get());
			return data.get();
		}
		return null;
	}
	

	//LOGOUT
	@PostMapping("/api/logout")
	public void logout(@RequestBody HttpSession session, HttpServletResponse response) {
			session.invalidate();
	}
}
