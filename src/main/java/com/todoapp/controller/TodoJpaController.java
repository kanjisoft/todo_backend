package com.todoapp.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.todoapp.entity.Todo;
import com.todoapp.repository.TodoJpaRepository;
import com.todoapp.service.TodoHardcodedService;


//@CrossOrigin(origins="http://localhost:4200")
@CrossOrigin
@RestController
public class TodoJpaController {
	
	private static final Logger log = 
			LoggerFactory.getLogger(TodoJpaController.class);

	
	@Autowired
	private TodoHardcodedService todoService;

	@Autowired
	private TodoJpaRepository todoJpaRepository;
	
	// /users/mark/todos
	@GetMapping("/jpa/users/{username}/todos/all")
	public List<Todo> getAllTodos(@PathVariable String username){
		List<Todo> todos = this.todoJpaRepository.findByUsername(username);
		Collections.sort(todos);
		return todos;
	}

	@GetMapping("/jpa/users/{username}/todos/current")
	public List<Todo> getAllTodosCurrent(@PathVariable String username){
		List<Todo> todos = this.todoJpaRepository.findByUsername(username);
		List<Todo> currentTodos = new ArrayList();
		Collections.sort(todos);
		for (Todo todo:todos) {
			if (!todo.isDone()) {
				currentTodos.add(todo);
			}
		}
		return currentTodos; 
	}

	@GetMapping("/jpa/users/{username}/todos/{id}")
	public Todo getTodo(@PathVariable String username,  @PathVariable long id){
		return this.todoJpaRepository.findById(id).get(); 
	}

	@GetMapping("/jpa/users/{username}/todos/{id}/toggle")
	public Todo toggleTodo(@PathVariable String username,  @PathVariable long id){
		Todo todo = this.todoJpaRepository.findById(id).get();
		todo.setDone(!todo.isDone());
		Todo todoUpdated = this.todoJpaRepository.save(todo);
		return todoUpdated; 
	}
	
	@PutMapping("/jpa/users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String username, 
			@PathVariable long id,
			@RequestBody Todo todo) 
	{
		Todo todoUpdated = this.todoJpaRepository.save(todo);
		return new ResponseEntity<Todo>(todoUpdated, HttpStatus.OK) ; 
	}

	@PostMapping("/jpa/users/{username}/todos")
	public ResponseEntity<Void> createTodo(@PathVariable String username, 
			@RequestBody Todo todo) 
	{
		//Todo todoUpdated = todoService.save(todo);
		todo.setUsername(username);
		log.info("username: " + username);
		Todo createdTodo = this.todoJpaRepository.save(todo);
		
		// location
		// Get current resource URL
		// /users/{username}/todo{id}
	 	
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}").buildAndExpand(createdTodo.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/jpa/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id) {		
		this.todoJpaRepository.deleteById(id);
		return ResponseEntity.noContent().build(); 
	}
}
