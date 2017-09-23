package com.blas.todo.controller.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.blas.todo.entity.Todo;
import com.blas.todo.service.TodoService;

@Controller
@RequestMapping("/api/v1/todo")
public class TodoRestController {
	
	@Autowired
	@Qualifier("todoService")
	private TodoService todoService;

	@GetMapping("/{id}")
	@ResponseBody
	public Todo find(@PathVariable Long id) {
		return todoService.findById(id);
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public void update(@RequestBody Todo todo) {
		todoService.saveTodo(todo);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long id) {
		todoService.deleteById(id);
	}
}
