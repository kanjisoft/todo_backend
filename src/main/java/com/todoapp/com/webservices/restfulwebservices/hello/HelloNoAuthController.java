package com.todoapp.com.webservices.restfulwebservices.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
public class HelloNoAuthController {
 
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello Java Code Geeks!";
    }
}