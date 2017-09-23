package com.blas.todo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blas.todo.entity.Todo;

@Repository("todoRepository")
public interface TodoRepository extends JpaRepository<Todo, Serializable> {
	Todo findById(Long id);
}
