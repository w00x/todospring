package com.blas.todo.service.interfaces;

import java.util.List;

import com.blas.todo.entity.Todo;

public interface ITodoService {
	public abstract List<Todo> allTodosDesc();
	public abstract void saveTodo(Todo todo);
	public abstract Todo findById(Long id);
}
