package com.todoapp.com.webservices.restfulwebservices.todo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitJpaRepository extends JpaRepository<Habit, Long>{
	
	List<Habit> findByUsername(String username);
}
