package com.todoapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.todoapp.entity.Todo;
 
@Service
public class TodoHardcodedService {

	   Logger logger = LoggerFactory.getLogger(TodoHardcodedService.class);
	   
	private static List<Todo> todos = new ArrayList();
	private static long idCounter = 0; 
	
	static {
		todos.add(new Todo(++idCounter, "mark", "Learn Angularx", new Date(), false, 1L));
		todos.add(new Todo(++idCounter, "mark", "Spring Boot", new Date(), false, 2L));
		todos.add(new Todo(++idCounter, "mark", "Learn Kanji Sisters", new Date(), false, 3L));
	}
	
	public List<Todo> findAll(){
		return todos;
	}

	public List<Todo> findAllCurrent() {
		List<Todo> todos = findAll();
		List<Todo> todosCurrent = new ArrayList();
		
		for (Todo todo:todos) {
			if( !todo.isDone()) {
				todosCurrent.add(todo);
			}
		}
		return todosCurrent; 
	}

	
	public Todo save(Todo todo) {
		logger.info("in save");
		if (todo.getId() == -1 || todo.getId() == 0) {
			todo.setId(++idCounter);
			todos.add(todo);
		} else {
			this.deleteById(todo.getId());
			todos.add(todo);
		}
		return todo; 
	}
	
	public Todo deleteById(long id) {
		Todo todo = findById(id);
		
		if (todo == null) return null; 

		if(todos.remove(todo)) {
			return todo; 
		}
		
		return null; 
	}

	public Todo findById(long id) {

		for (Todo todo:todos) {
			if( todo.getId() == id) {
				return todo;
			}
		}
		return null; 
	}

}
