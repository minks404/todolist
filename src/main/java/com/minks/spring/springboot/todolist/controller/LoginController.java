package com.minks.spring.springboot.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.minks.spring.springboot.todolist.service.LoginService;

@Controller
@SessionAttributes("name")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String showloginPage(ModelMap model) {
		// model.put("name", name);
		// System.out.println("Name: " + name);
		return "login";
	}

	@RequestMapping(value="login", method = RequestMethod.POST)
	public String showWelcomePage( @RequestParam String name ,@RequestParam String password, ModelMap model) {
		
		if( loginService.validateUser(name, password)) {
			model.put("name", name);
			return "welcome";
		}
		else {
			model.put("invalidCredentials", "Invalid Credentials!");
			return "login";
		}
	}
}
