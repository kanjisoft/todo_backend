package com.todoapp.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Todo implements Comparable<Todo>{
	
	@Id
	@GeneratedValue
	private Long id;
	private String username;
	private String description;
	private Date targetDate;
	private boolean isDone;
	private Long priority;
	private boolean workRelated;



	public Todo(Long id, String username, String description, Date targetDate, 
			boolean isDone, Long priority, boolean workRelated) {
		super();
		this.id = id;
		this.username = username;
		this.description = description;
		this.targetDate = targetDate;
		this.isDone = isDone;
		this.priority = priority;
		this.workRelated = workRelated;
	}


	protected Todo() {
		
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	
	public Long getPriority() {
		return priority;
	}


	public void setPriority(Long priority) {
		this.priority = priority;
	}

	public boolean isWorkRelated() {
		return workRelated;
	}


	public void setWorkRelated(boolean workRelated) {
		this.workRelated = workRelated;
	}
	
	@Override
	  public int compareTo(Todo t) {
	    if (priority ==  null || t.getPriority() == null) {
	      return 0;
	    }
	    return priority.compareTo(t.getPriority());
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
		Todo other = (Todo) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}
