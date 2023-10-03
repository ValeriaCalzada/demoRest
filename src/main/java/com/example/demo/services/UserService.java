package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.exceptions.UserExistsException;
import com.example.demo.services.exceptions.UserNotFoundException;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepositoy;
	
	//get all users
	public List<User> getAllUsers(){
		return userRepositoy.findAll();	
	}
	
	//create users - saves it to the repo
	public User createUser(User user)throws UserExistsException{
		//if user exist using username
		User existingUser = userRepositoy.findByUsername(user.getUsername());
		//if not exists
		if(existingUser != null) {
			throw new UserExistsException("Username already in repository. Pick a different one.");
		}
		return userRepositoy.save(user);	
	}
	
	//GET USER BY ID
	public Optional<User> getUserById(long id) throws UserNotFoundException{
		Optional<User> user = userRepositoy.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("User not found in repository");
		}
		return user;
	}
	
	//UPDATE USER BY ID
	public User updateUserById(long id, User user) throws UserNotFoundException{
		Optional<User> Opitonaluser = userRepositoy.findById(id);
		if(!Opitonaluser.isPresent()) {
			throw new UserNotFoundException("User not found in repository. Please provide the correct id");
		}
		user.setId(id);
		return user;
	}
	
	//delete user by id
	public void deleteById(long id) {
		Optional<User> optionalUser = userRepositoy.findById(id);
		if(!optionalUser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found in repository");
		}
			userRepositoy.deleteById(id);
	}
	
	
	//get user by username
	public User getUserByUsername(String username) {
		User user = userRepositoy.findByUsername(username);
		return user;
	}

}
