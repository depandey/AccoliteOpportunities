package com.accolite.opportunities.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.opportunities.entities.User;
import com.accolite.opportunities.repositories.UserRepository;

@RestController
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	 
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
    	 logger.info("Get User details");
        return (List<User>) userRepository.findAll();
    }

    @PostMapping("/users")
    User addUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}