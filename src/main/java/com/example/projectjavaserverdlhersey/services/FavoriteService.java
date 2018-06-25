package com.example.projectjavaserverdlhersey.services;

import java.util.List;
import java.util.Optional;

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
import com.example.projectjavaserverdlhersey.models.Favorite;
import com.example.projectjavaserverdlhersey.repositories.FavoriteRepository;
import com.example.projectjavaserverdlhersey.repositories.UserRepository;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class FavoriteService {
	@Autowired
	FavoriteRepository repository;
	@Autowired
	UserRepository uRepository;
	
	
	//CREATE
	@PostMapping("/api/favorite")
	public Favorite createFavorite(@RequestBody Favorite favorite) {
		return repository.save(favorite);
	}
	
	//READ
	@GetMapping("/api/favorite")
	public Iterable<Favorite> findAllFavorites() {
		return repository.findAll();
	}
	@GetMapping("/api/user/{uid}/favorite")
	public List<Favorite> findAllFavoritesForUser(@PathVariable("uid") int uid) {
		Optional<User> data = uRepository.findById(uid);
		if(data.isPresent()) {
			User user = data.get();
			return user.getFavorites();
		}
		return null;
	}
	
	//UPDATE
	//Should not be needed.
	@PutMapping("/api/favorite/{fid}") 
	public Favorite updateFavorite(@PathVariable("fid") int fid, @RequestBody Favorite nFavorite) {
		Optional<Favorite> data = repository.findById(fid);
		if(data.isPresent()) {
			Favorite favorite = data.get();
			favorite.setRecipe(nFavorite.getRecipe());
			favorite.setUser(nFavorite.getUser());
			repository.save(favorite);
			return favorite;
		}
		return null;
	}
	
	//DELETE
	@DeleteMapping("/api/favorite/{fid}")
	public void deleteFavorite(@PathVariable int fid) {
		repository.deleteById(fid);
	}
}
