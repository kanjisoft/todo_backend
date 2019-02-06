package com.todoapp.com.webservices.restfulwebservices.todo;

import java.net.URI;
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


@CrossOrigin(origins="http://localhost:4200")
@RestController
public class HabitJpaResource {
	
	private static final Logger log = 
			LoggerFactory.getLogger(HabitJpaResource.class);

	
	@Autowired
	private TodoHardcodedService todoService;

	@Autowired
	private HabitJpaRepository habitJpaRepository;
	
	// /users/mark/todos
	@GetMapping("/jpa/users/{username}/habits")
	public List<Habit> getAllTodos(@PathVariable String username){
		return this.habitJpaRepository.findByUsername(username);
		//return this.todoService.findAll(); 
	}

	@GetMapping("/jpa/users/{username}/habits/{id}")
	public Habit getHabit(@PathVariable String username,  @PathVariable long id){
		return this.habitJpaRepository.findById(id).get(); 
		//return this.todoService.findById(id);
	}

	@PutMapping("/jpa/users/{username}/habits/{id}")
	public ResponseEntity<Habit> updateHabit(@PathVariable String username, 
			@PathVariable long id,
			@RequestBody Habit habit) 
	{
		//Todo todoUpdated = todoService.save(todo);
		Habit habitUpdated = this.habitJpaRepository.save(habit);
		return new ResponseEntity<Habit>(habitUpdated, HttpStatus.OK) ; 
	}

	@PostMapping("/jpa/users/{username}/habits")
	public ResponseEntity<Void> createHabit(@PathVariable String username, 
			@RequestBody Habit habit) 
	{
		//Todo todoUpdated = todoService.save(todo);
		habit.setUsername(username);
		log.info("username: " + username);
		Habit createdHabit = this.habitJpaRepository.save(habit);
		
		// location
		// Get current resource URL
		// /users/{username}/todo{id}
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}").buildAndExpand(createdHabit.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/jpa/users/{username}/habits/{id}")
	public ResponseEntity<Void> deleteHabit(@PathVariable String username, @PathVariable long id) 
	{
		
		this.habitJpaRepository.deleteById(id);
		return ResponseEntity.noContent().build(); 
		// return ResponseEntity.notFound().build(); 
	}
}
