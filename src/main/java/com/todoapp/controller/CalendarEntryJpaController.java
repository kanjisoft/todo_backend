package com.todoapp.controller;

import java.net.URI;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.todoapp.entity.CalendarEntry;
import com.todoapp.entity.Habit;
import com.todoapp.repository.CalendarEntryJpaRepository;
import com.todoapp.repository.HabitJpaRepository;

@CrossOrigin
@RestController
public class CalendarEntryJpaController {
	
	private static final Logger log = 
			LoggerFactory.getLogger(CalendarEntryJpaController.class);

	@Autowired
	private CalendarEntryJpaRepository calendarEntryJpaRepository;
	
	@Autowired
	private HabitJpaRepository habitJpaRepository;
	
	@GetMapping("/jpa/users/{username}/calendar") 
	public List<CalendarEntry> getAllHabitsByUserName(@PathVariable String username){
		
		List<CalendarEntry> calendarEntries = new ArrayList<CalendarEntry>();
		calendarEntries = this.calendarEntryJpaRepository.findByUserName(username);
		return calendarEntries; 
	}

	@GetMapping("/jpa/users/{username}/calendar/{habitId}") 
	public List<CalendarEntry> getAllEntriesByUsernameAndHabitId(
			@PathVariable String username,
			@PathVariable Long habitId
			)
	{
		
		List<CalendarEntry> calendarEntries = new ArrayList<CalendarEntry>();
		calendarEntries = this.calendarEntryJpaRepository.findByUserNameAndHabitId(username, habitId);
		return calendarEntries; 
	}

	
	@PostMapping("/jpa/users/{username}/calendar/{habitId}")
	public ResponseEntity<Void> createCalendarEntry(
			@PathVariable String username,
			@PathVariable Long habitId,
			@RequestBody CalendarEntryShort calendarEntryShort) 
		
	{
        Date date = new Date();  
        Timestamp dateCompleted=new Timestamp(date.getTime()); 
		
		CalendarEntry calendarEntry = new CalendarEntry(); 
		calendarEntry.setUserName(username);
		Optional<Habit> habitOpt = this.habitJpaRepository.findById(habitId);
		Habit habit = habitOpt.get(); 
		calendarEntry.setHabit(habit);
		calendarEntry.setDateCompleted(dateCompleted);
		
		CalendarEntry createdCalendarEntry = this.calendarEntryJpaRepository.save(calendarEntry);
			 	
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}").buildAndExpand(createdCalendarEntry.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}
}
