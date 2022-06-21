package com.minks.spring.springboot.todolist.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.minks.spring.springboot.todolist.model.Todo;
import com.minks.spring.springboot.todolist.service.LoginService;
import com.minks.spring.springboot.todolist.service.TodoRepository;
import com.minks.spring.springboot.todolist.service.TodoService;

@Controller
@SessionAttributes("name")
public class TodoController {

	//@Autowired
	//private TodoService todoService;
	
	@Autowired
	TodoRepository repository;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(value = "list-todos", method = RequestMethod.GET)
	public String showTodos(ModelMap model) {
		String name = (String) model.get("name");
		model.put("todos", repository.findByUser(name));
		//model.put("todos", todoService.retrieveTodos(name));
		System.out.println("Name: " + name);
		return "list-todos";
	}

	@RequestMapping(value = "add-todo", method = RequestMethod.GET)
	public String showAddTodo(ModelMap model) {
		model.addAttribute("todo", new Todo(0, (String) model.get("name"), "Default Description", new Date(), false));
		return "todo";
	}

	@RequestMapping(value = "add-todo", method = RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors()) {
			return "todo";
		}
		todo.setUser((String) model.get("name"));
		repository.save(todo);
		
		//todoService.addTodo((String) model.get("name"), todo.getDesc(), todo.getTargetDate(), false);
		return "redirect:/list-todos";
	}

	@RequestMapping(value = "delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id) {
		repository.deleteById(id);
		//todoService.deleteTodo(id);
		return "redirect:/list-todos";
	}

	@RequestMapping(value = "update-todo", method = RequestMethod.GET)
	public String showupdateTodoPage(@RequestParam int id, ModelMap model) {
		Todo todo = repository.findById(id).get();
		//Todo todo = todoService.retrieveTodo(id);
		model.put("todo", todo);
		return "todo";
	}

	@RequestMapping(value = "update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors()) {
			return "todo";
		}
		todo.setUser((String) model.get("name"));

		repository.save(todo);
		//todoService.updateTodo(todo);
		return "redirect:/list-todos";
	}
}
