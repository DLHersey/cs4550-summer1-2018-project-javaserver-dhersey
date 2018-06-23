package com.example.projectjavaserverdlhersey.models;

import javax.persistence.*;

import com.example.projectjavaserverdlhersey.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Favorite {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String recipeUri;
	@ManyToOne
  	@JsonIgnore
  	private User user;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRecipe() {
		return recipeUri;
	}
	public void setRecipe(String recipeUri) {
		this.recipeUri = recipeUri;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
