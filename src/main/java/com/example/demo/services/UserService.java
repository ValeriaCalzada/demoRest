package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepositoy;
	
	//get all users
	public List<User> getAllUsers(){
		return userRepositoy.findAll();	
	}
	
	//create users - saves it to the repo
	public User createUser(User user){
		return userRepositoy.save(user);	
	}
	
	//GET USER BY ID
	public Optional<User> getUserById(long id) {
		Optional<User> user = userRepositoy.findById(id);
		return user;
	}
	
	//UPDATE USER BY ID
	public User updateUserById(long id, User user) {
		user.setId(id);
		return user;
	}
	
	//delete user by id
	public void deleteById(long id) {
		if (userRepositoy.findById(id).isPresent()) {
			userRepositoy.deleteById(id);
		}
	}
	
	
	//get user by username
	public User getUserByUsername(String username) {
		User user = userRepositoy.findByUsername(username);
		return user;
	}

}
