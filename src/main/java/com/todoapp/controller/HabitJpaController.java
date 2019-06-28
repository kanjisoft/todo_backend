package com.todoapp.controller;

import java.net.URI;
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

import com.todoapp.entity.Habit;
import com.todoapp.repository.CalendarEntryJpaRepository;
import com.todoapp.repository.HabitJpaRepository;

@CrossOrigin
@RestController
public class HabitJpaController {
	
	private static final Logger log = 
			LoggerFactory.getLogger(HabitJpaController.class);

	
	@Autowired
	private HabitJpaRepository habitJpaRepository;
	
	@Autowired
	private CalendarEntryJpaRepository calendarEntryJpaRepository;
	
	// /users/mark/todos
	@GetMapping("/jpa/users/{username}/habits")
	public List<Habit> getAllHabits(@PathVariable String username){
		List<Habit> habits = this.habitJpaRepository.findByUsername(username);
		Collections.sort(habits);
		return habits; 
	}

	@GetMapping("/jpa/users/{username}/habits/{id}")
	public Habit getHabit(@PathVariable String username,  @PathVariable long id){
		return this.habitJpaRepository.findById(id).get();
	}

	@PutMapping("/jpa/users/{username}/habits/{id}")
	public ResponseEntity<Habit> updateHabit(@PathVariable String username, 
			@PathVariable long id,
			@RequestBody Habit habit) 
	{
		Habit habitUpdated = this.habitJpaRepository.save(habit);
		return new ResponseEntity<Habit>(habitUpdated, HttpStatus.OK) ; 
	}

	@PostMapping("/jpa/users/{username}/habits")
	public ResponseEntity<Void> createHabit(@PathVariable String username, 
			@RequestBody Habit habit) 
	{
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
	}
}
