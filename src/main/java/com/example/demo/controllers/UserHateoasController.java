package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entities.User;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import com.example.demo.services.exceptions.UserNotFoundException;

import jakarta.validation.constraints.Min;

@RestController
@RequestMapping(value = "/hateoas/users")
@Validated
public class UserHateoasController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;
	
	@Autowired
	OrderHateoasController orderHateoasController;
	
	@Autowired
	OrderRepository orderRepository;

	// get allUsers method
	@GetMapping
	public CollectionModel<User> getAllUsers() throws UserNotFoundException {
			List<User> users = userService.getAllUsers();
			for(User user: users) {
				Long userId = user.getId();
			Link selfLink = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
							.getAllUsers()).withSelfRel();
			user.add(selfLink);
			//Relationship with link for all orders
			Link relLink = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(OrderHateoasController.class)
							.getAllOrders(userId)).withRel("allOrders");
			user.add(relLink);
	
			}
			
			Link selfLinkAllUsers = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(this.getClass()))
							.withSelfRel();
			CollectionModel<User> finalUsers = CollectionModel.of(users, selfLinkAllUsers);
			return finalUsers;
	}

	// get Users method
	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
			Optional<User> userOp = userService.getUserById(id);
			User user = userOp.get();
			Long userId = user.getId();
			Link selfLink = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
							.getUserById(userId)).withSelfRel();
			user.add(selfLink);
			return user;
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}
