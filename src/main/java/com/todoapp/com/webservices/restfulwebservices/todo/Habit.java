package com.todoapp.com.webservices.restfulwebservices.todo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Habit {
	
	@Id
	@GeneratedValue
	private Long id;
	private String username;
	private String habitname;
	private String description;
	private String habitTrigger;
	private String reward;
	private Long priority;

	public Habit(Long id, String username, String habitname, String description, String trigger, String reward,
			Long priority) {
		super();
		this.id = id;
		this.username = username;
		this.habitname = habitname;
		this.description = description;
		this.habitTrigger = trigger;
		this.reward = reward;
		this.priority = priority;
	}



	protected Habit() {		
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHabitname() {
		return habitname;
	}

	public void setHabitname(String habitname) {
		this.habitname = habitname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHabbitTrigger() {
		return habitTrigger;
	}

	public void setHabitTrigger(String trigger) {
		this.habitTrigger = trigger;
	}

	public String getReward() {
		return reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

	public Long getPriority() {
		// Returing priority
		return priority;
	}

	public void setPriority(Long priority) {
		this.priority = priority;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Habit other = (Habit) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
