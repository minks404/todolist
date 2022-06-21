package com.minks.spring.springboot.todolist.service;

import org.springframework.stereotype.Component;

@Component
public class LoginService {

	public boolean validateUser(String name, String password) {
		if(name.equals("minks")&&password.equals("1234"))
			return true;
		else return false;
	}
}
