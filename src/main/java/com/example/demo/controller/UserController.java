package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;
	
	@PostMapping("/register")
	public String registerUser(@RequestBody User user) {

	    service.registerUser(user.getEmail(), user.getPassword());

	    return "User registered successfully";
	}

	@PostMapping("/login")
	public String loginUser(@RequestBody User user) {

	    return service.login(user.getEmail(), user.getPassword());
	}
	
	@GetMapping("/profile")
	public ResponseEntity<?> getProfile(Authentication authentication) {

	    String email = authentication.getName(); // from JWT

	    User user = service.getUser(email);

	    return ResponseEntity.ok(user);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateProfile(
	        Authentication authentication,
	        @RequestBody User user) {

	    String email = authentication.getName();

	    User updated = service.updateUser(email, user);

	    return ResponseEntity.ok(updated);
	}
	
	@GetMapping("/test")
	public String test() {
	    return "JWT is working!";
	}
	
}
