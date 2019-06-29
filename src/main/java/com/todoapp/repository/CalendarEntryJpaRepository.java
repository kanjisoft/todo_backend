package com.todoapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todoapp.entity.CalendarEntry;

@Repository
public interface CalendarEntryJpaRepository extends JpaRepository<CalendarEntry, Long>{
	
	List<CalendarEntry> findByUserName(String username);
	List<CalendarEntry> findByUserNameAndHabitId(String username, Long habitId);
}
