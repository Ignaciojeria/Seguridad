package com.example.servicioB.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.servicioB.config.API;
import com.example.servicioB.service.HogarService;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;



@Controller
public class HogarController {
	
	@Autowired 
	HogarService _hogarService;
	
	private static HogarService hogarService;
	
	@PostConstruct
	private void initService() {
		hogarService=this._hogarService;
	}
	
	private static final HogarController hogarController= new HogarController();
	
	private HogarController() {}
	
	public static HogarController getInstance() {
		return hogarController;
	}
	
	
	public void findAll(Router router){
		router.get(API.HOGAR).produces("application/json")
		.handler(this::findAllctx);
	}
	
	 void findAllctx(RoutingContext routingContext){
		HttpServerResponse response = routingContext.response();
		response.putHeader("content-type", "application/json; charset=UTF-8");
		response.end(Json.encodePrettily(hogarService.findAll()));
	}

}
