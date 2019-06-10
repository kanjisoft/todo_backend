package com.todoapp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todoapp.basic.auth.AuthenticationBean;

// Controller
// @CrossOrigin(origins="http://localhost:4200")
@CrossOrigin
@RestController
public class BasicAuthenticationController  {
	
	//hello-world-bean
	@GetMapping(path="/basic-auth")
	public AuthenticationBean helloWorldBean() {
		//throw new RuntimeException("Some Error has happened!");
		return new AuthenticationBean("You are authenticated");
		
	}
	
}
