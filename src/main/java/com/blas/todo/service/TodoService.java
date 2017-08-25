package com.blas.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blas.todo.entity.Todo;
import com.blas.todo.repository.TodoRepository;
import com.blas.todo.service.interfaces.ITodoService;

@Service
public class TodoService implements ITodoService {
	
	@Autowired
	@Qualifier("todoRepository")
	private TodoRepository todoRepository;

	@Override
	public List<Todo> allTodosDesc() {
		return todoRepository.findAll(new Sort(Sort.Direction.DESC, "id"));
	}

	@Override
	public void saveTodo(Todo todo) {
		todoRepository.save(todo);		
	}

	@Override
	public Todo findById(Long id) {
		return todoRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		Todo todo = todoRepository.findById(id);
		if(todo != null) {
			todoRepository.delete(todo);
		}
	}

}
