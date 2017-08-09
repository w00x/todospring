package com.blas.todo.service.interfaces;

import java.util.List;

import com.blas.todo.entity.Todo;

public interface ITodoService {
	public abstract List<Todo> allTodos();
	public abstract void saveTodo(Todo todo);
}
