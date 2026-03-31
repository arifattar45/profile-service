package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;


@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/all-users")
	public ResponseEntity<?> getAllUser() {
	    
	    List<User> all = userService.getAllUser();
	    
	    if (all != null && !all.isEmpty()) {
	        return new ResponseEntity<>(all, HttpStatus.OK);
	    }
	    
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/dashboard")
	public String adminDashboard() {
	    return "Welcome ADMIN! You have full access.";
	}
}
