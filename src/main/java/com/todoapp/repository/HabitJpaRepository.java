package com.todoapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todoapp.entity.Habit;

@Repository
public interface HabitJpaRepository extends JpaRepository<Habit, Long>{
	
	List<Habit> findByUsername(String username);
}
