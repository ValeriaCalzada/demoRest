package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Order;
import com.example.demo.entities.User;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.exceptions.OrderNotFoundException;
import com.example.demo.services.exceptions.UserNotFoundException;

@RestController
@RequestMapping(value="/users")
public class OrderController {
	
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
			return userOptional.get().getOrders();
		}
		
	//create order
		@PostMapping("/{userid}/orders")
		public Order createOrder(@PathVariable Long userid, @RequestBody Order order) throws UserNotFoundException {
			Optional<User> userOptional = userRepository.findById(userid);
			if (!userOptional.isPresent())
				throw new UserNotFoundException("User not found!");
			User user = userOptional.get();
			order.setUser(user);
			return orderRepository.save(order);
		}
		
		@GetMapping("/{userId}/{orderId}")
		public Order getOrderByOrderId(@PathVariable long userId,@PathVariable long orderId) throws OrderNotFoundException, UserNotFoundException {
			Optional<User> userOptional = userRepository.findById(userId);
			if (!userOptional.isPresent())
				throw new UserNotFoundException("User not found!");			
			Optional<Order> orderOptional = orderRepository.findById(orderId);
			if (!orderOptional.isPresent())
				throw new OrderNotFoundException("Order: "+ orderId+ " not found in repository");
			return orderOptional.get();
		}
		

}
