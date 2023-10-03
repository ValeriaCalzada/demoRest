package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.User;
import com.example.demo.services.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	
	//get allUsers method
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	//create user
	@PostMapping("/create")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	
	//get Users method
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable("id") long id){
		return userService.getUserById(id);
	}
	
	//update User
	@PutMapping("/users/{id}")
	public User updateUserByID(@PathVariable("id") long id, @RequestBody User user) {
		return userService.updateUserById(id, user);	
	}
	
	//delete user by id
	@DeleteMapping("/users/{id}")
	public void deleterUserById(@PathVariable("id")long id) {
		userService.deleteById(id);
	}
	
	//get user by username
		@GetMapping("/users/username/{username}")
		public User getUserByUsername(@PathVariable("username") String username){
			return userService.getUserByUsername(username);
		}
	
}
