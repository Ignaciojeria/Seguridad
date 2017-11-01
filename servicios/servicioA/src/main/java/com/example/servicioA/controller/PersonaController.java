package com.example.servicioA.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.servicioA.config.API;
import com.example.servicioA.dao.PersonaDAO;
import com.example.servicioA.service.PersonaService;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

@Controller
public class PersonaController {
	
	private static final PersonaController personaController=new PersonaController();
	
	 @Autowired
	 PersonaService _personaService;
	
	 private static PersonaService personaService;
	 
	 private PersonaController() {}
	 
	 public static PersonaController getInstance() {
		 return personaController;
	 }
	
	  @PostConstruct     
	  private void initStaticDao () {
		  personaService = this._personaService;
	  }

	
	public void findAll(Router router){
		router.get(API.PERSONA).produces("application/json")
		.handler(this::findAllctx);
	}
	
	 void findAllctx(RoutingContext routingContext){
		HttpServerResponse response = routingContext.response();
		response.putHeader("content-type", "application/json; charset=UTF-8");
		response.end(Json.encodePrettily(personaService.findAll()));
	}

}
