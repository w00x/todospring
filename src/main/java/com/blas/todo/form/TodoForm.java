package com.blas.todo.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TodoForm {
	private boolean done;
	
	@NotNull
	@Size(min=2, max=10)
	private String description;
	
	private String error;
	
	public TodoForm() {
		
	}

	public TodoForm(Long id, boolean done, String description, String error) {
		super();
		this.done = done;
		this.description = description;
		this.error = error;
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

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
