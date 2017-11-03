package com.example.zuul.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.zuul.dao.UserDAO;
import com.example.zuul.domain.User;
import com.example.zuul.model.UserModel;

public class UserService {
	
	@Autowired UserDAO userDAO;
	
	public User findUser(UserModel userModel) {
      	return userDAO.findUser(new User(userModel.getUserName(),userModel.getPassword()));
	}

}
