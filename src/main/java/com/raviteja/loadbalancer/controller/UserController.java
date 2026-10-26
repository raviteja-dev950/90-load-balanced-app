package com.raviteja.loadbalancer.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.raviteja.loadbalancer.entity.User;
import com.raviteja.loadbalancer.repository.UserRepository;
import com.raviteja.loadbalancer.service.InstanceService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired private UserRepository userRepository;
	@Autowired private InstanceService instanceService;

	@GetMapping
	public List<User> getAllUsers() {
		System.out.println("GET /users - Served by " + instanceService.getInstanceId());
		return userRepository.findAll();
	}
	@PostMapping
	public User createUser(@RequestBody User user) {
		System.out.println("POST /users - Served by " + instanceService.getInstanceId() + " - " + user.getName());
		return userRepository.save(user);
	}
	@GetMapping("/{id}")
	public User getUserById(@PathVariable Long id) {
		return userRepository.findById(id).orElse(null);
	}
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable Long id) {
		userRepository.deleteById(id);
		return "User deleted from " + instanceService.getInstanceId() + " ID: " + id;
	}
}