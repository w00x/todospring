package com.blas.todo.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blas.todo.entity.Todo;

@Repository("todoRepository")
public interface TodoRepository extends JpaRepository<Todo, Serializable> {
	
	public abstract Todo findByDescription(String description);
	
}
