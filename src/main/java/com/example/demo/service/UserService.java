package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.JwtUtil;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public void registerUser(String email, String password) {

    	if (userRepo.findByEmail(email) != null) {
    	    throw new RuntimeException("User already exists");
    	}
    	
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        
        user.setRole("USER");

        userRepo.save(user);
    }
    
    public String login(String email, String password) {

        User user = userRepo.findByEmail(email);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {

            return jwtUtil.generateToken(user.getEmail(), user.getRole());
        }

        throw new RuntimeException("Invalid credentials");
    }
    
    public List<User> getAllUser(){
    	
    	return userRepo.findAll();
    }
    
    public User updateUser(String email, User updatedUser) {

        User user = userRepo.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        user.setEmail(updatedUser.getEmail());

        if (updatedUser.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        return userRepo.save(user);
    }
    
    public User getUser(String email) {
        return userRepo.findByEmail(email);
    }
}