package com.todoapp.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Habit implements Comparable<Habit>{
	
	@Id
	@GeneratedValue
	private Long id;
	private String username;
	private String habitname;
	private String description;
	private String reminder;
	private String reward;
	private Long priority;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="habit_id")
    private Set<CalendarEntry> accounts;

	public Habit(Long id, String username, String habitname, String description, String reminder, String reward,
			Long priority) {
		super();
		this.id = id;
		this.username = username;
		this.habitname = habitname;
		this.description = description;
		this.reminder = reminder;
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

	public String getReminder() {
		return reminder;
	}

	public void setReminder(String reminder) {
		this.reminder = reminder;
	}

	public String getReward() {
		return reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

	public Long getPriority() {
		return priority;
	}

	public void setPriority(Long priority) {
		this.priority = priority;
	}

	@Override
	  public int compareTo(Habit h) {
	    if (priority ==  null || h.getPriority() == null) {
	      return 0;
	    }
	    return priority.compareTo(h.getPriority());
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
