package com.todoapp.controller;

import java.net.URI;
import java.util.List;

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
import com.todoapp.service.TodoHardcodedService;

//@CrossOrigin(origins="http://localhost:4201")
@CrossOrigin
@RestController
public class TodoController {
	
	@Autowired
	private TodoHardcodedService todoService;
	
	// /users/mark/todos
	@GetMapping("/users/{username}/todoss")
	public List<Todo> getAllTodos(@PathVariable String username){
		return this.todoService.findAll();
	}

//	@GetMapping("/users/{username}/todoscurrent")
//	public List<Todo> getAllTodosCurrent(@PathVariable String username){
//		return this.todoService.findAllCurrent(); 
//	}
	
	@GetMapping("/users/{username}/todos/{id}")
	public Todo getTodo(@PathVariable String username,  @PathVariable long id){
		return this.todoService.findById(id);
	}

	@PutMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String username, 
			@PathVariable long id,
			@RequestBody Todo todo) 
	{
		Todo todoUpdated = todoService.save(todo);
		return new ResponseEntity<Todo>(todoUpdated, HttpStatus.OK) ; 
	}

	@PostMapping("/users/{username}/todos")
	public ResponseEntity<Void> updateTodo(@PathVariable String username, 
			@RequestBody Todo todo) 
	{
		Todo createdTodo = todoService.save(todo);
		
		// location
		// Get current resource URL
		// /users/{username}/todo{id}
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}").buildAndExpand(createdTodo.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id) {
		Todo todo = this.todoService.deleteById(id);
		if (todo!=null) {
			return ResponseEntity.noContent().build(); 
		}
		return ResponseEntity.notFound().build(); 
	}
}
