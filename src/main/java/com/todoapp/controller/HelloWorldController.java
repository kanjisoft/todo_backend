package com.todoapp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// Controller
//@CrossOrigin(origins="http://localhost:4200")
//@CrossOrigin(origins="http://localhost")
@CrossOrigin
@RestController
public class HelloWorldController {
	
	// GET
	// URI - /hello-world
	// Method returns - "Hello World"

	//@RequestMapping(method=RequestMethod.GET, path="/hello-world")
	@GetMapping(path="/hello-world")
	public String helloWorld() {
		return "Hello World";
	}

	//hello-world-bean
	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		//throw new RuntimeException("Some Error has happened!");
		return new HelloWorldBean("Hello World changed");
		
	}

	//hello-world-bean
	@GetMapping(path="/hello-world-bean2")
	public HelloWorldBean helloWorldBean2() {
		//throw new RuntimeException("Some Error has happened!");
		return new HelloWorldBean("Hello World changed2");
	}
	
	
	//hello-world/path-variable/in28minutes
	@GetMapping(path="/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello Word, %s", name));		
	}
}
