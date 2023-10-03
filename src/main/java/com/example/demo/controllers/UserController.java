package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import com.example.demo.services.exceptions.UserExistsException;
import com.example.demo.services.exceptions.UserNotFoundException;

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
		try {
			return userService.createUser(user);
		} catch (UserExistsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	
	//get Users method
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable("id") long id){
		try {
			return userService.getUserById(id);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}
	
	//update User
	@PutMapping("/users/{id}")
	public User updateUserByID(@PathVariable("id") long id, @RequestBody User user) {
		try {
			return userService.updateUserById(id, user);
		}  catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}	
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
