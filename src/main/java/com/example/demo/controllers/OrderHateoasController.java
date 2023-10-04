package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Order;
import com.example.demo.entities.User;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.exceptions.UserNotFoundException;

@RestController
@RequestMapping(value = "/hateoas/users")
public class OrderHateoasController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	//get all orders
			@GetMapping("/{userid}/orders")
			public List<Order> getAllOrders(@PathVariable long userid) throws UserNotFoundException{
				
				Optional<User> userOptional = userRepository.findById(userid);
				if (!userOptional.isPresent())
					throw new UserNotFoundException("User not found!");
				List<Order> allOrders = userOptional.get().getOrders();
				return userOptional.get().getOrders();
			}
	
}
