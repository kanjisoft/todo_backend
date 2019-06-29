package com.todoapp.jwt.resource;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class  JwtTokenRequest implements Serializable {
  
  private static final long serialVersionUID = -5616176897013108345L;

	private static final Logger log = 
			LoggerFactory.getLogger(JwtTokenRequest.class);
  	private String username;
    private String password;

    public JwtTokenRequest() {
        super();
    }

    public JwtTokenRequest(String username, String password) {
    		log.debug("in JWT Token Request Constructor");
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

