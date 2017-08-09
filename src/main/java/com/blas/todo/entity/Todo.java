package com.blas.todo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="todo")
public class Todo {
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="done")
	private boolean done;
	
	@Column(name="description")
	private String description;
	
	public Todo() {
		
	}

	public Todo(Long id, boolean done, String description) {
		super();
		this.id = id;
		this.done = done;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
