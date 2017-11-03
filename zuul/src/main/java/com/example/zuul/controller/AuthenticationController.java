package com.example.zuul.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.zuul.domain.User;
import com.example.zuul.model.UserModel;
import com.example.zuul.service.UserService;



@RestController
public class AuthenticationController {
	
	private String tokenHeader="Authorization";
	
	//Crea un servicio que tenga autenticación de usuarios
	@Autowired UserService userService;

	
	@PostMapping("auth")
	public ResponseEntity<?> useAuth(@RequestBody UserModel userModel) {
		User user=userService.findUser(userModel);
		if(user!=null)return null;
		return null;
	}

}
