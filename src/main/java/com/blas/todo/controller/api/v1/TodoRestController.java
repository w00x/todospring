package com.blas.todo.controller.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public Todo findOne(@PathVariable("id") Long id) {
		return todoService.findById(id);
	}
}
