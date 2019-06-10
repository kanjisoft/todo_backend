package com.todoapp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todoapp.basic.auth.AuthenticationBean;

@CrossOrigin
@RestController
public class BasicAuthenticationController  {
	
	//hello-world-bean
	@GetMapping(path="/basic-auth")
	public AuthenticationBean helloWorldBean() {
		return new AuthenticationBean("You are authenticated");
		
	}
	
}
