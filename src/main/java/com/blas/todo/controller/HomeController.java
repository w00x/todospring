package com.blas.todo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blas.todo.entity.Todo;
import com.blas.todo.form.TodoForm;
import com.blas.todo.service.TodoService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	@Qualifier("todoService")
	private TodoService todoService;
	
	@GetMapping("/")
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView("home/home");
		List<Todo> todos = todoService.allTodos();
		mav.addObject("todos", todos);
		mav.addObject("todoForm", new TodoForm());
		return mav;
	}
	
	@PostMapping("/todo/save")
	public String todoSave(@Valid TodoForm todoForm, BindingResult bindingResult, Model model, RedirectAttributes attr) {
		if (!bindingResult.hasErrors()) {
			Todo todo = new Todo();
			todo.setDescription(todoForm.getDescription());
			todoService.saveTodo(todo);
		}
		else {
			List<Todo> todos = todoService.allTodos();
			model.addAttribute("todos", todos);
			return "home/home";
		}
		
		return "redirect:/";
	}
}
