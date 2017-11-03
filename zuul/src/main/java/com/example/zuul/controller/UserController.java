package com.example.zuul.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.zuul.dao.UserDAO;
import com.example.zuul.domain.User;



@RestController
public class UserController {
	
	@Autowired UserDAO userDAO;

	
	@PostMapping("auth")
	public User useAuth(@RequestBody User user) {
		return userDAO.findUser(user);
	}

}
