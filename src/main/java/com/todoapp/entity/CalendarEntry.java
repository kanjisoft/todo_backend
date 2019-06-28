package com.todoapp.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CalendarEntry {
	
	@Id
	@GeneratedValue
	private Long id;

	private String userName;
    @ManyToOne
    @JoinColumn(name = "habit_id")
	private Habit habit;
    
	private Timestamp dateCompleted;

	public CalendarEntry() {		
	}

	public CalendarEntry(Long id, String userName, Habit habit, Timestamp dateCompleted) {
		super();
		this.id = id;
		this.userName = userName;
		this.habit = habit;
		this.dateCompleted = dateCompleted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Habit getHabit() {
		return habit;
	}

	public void setHabit(Habit habit) {
		this.habit = habit;
	}

	public Date getDateCompleted() {
		return dateCompleted;
	}

	public void setDateCompleted(Timestamp dateCompleted) {
		this.dateCompleted = dateCompleted;
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
		CalendarEntry other = (CalendarEntry) obj;
		if (id != other.id)
			return false;
		return true;
	}

}