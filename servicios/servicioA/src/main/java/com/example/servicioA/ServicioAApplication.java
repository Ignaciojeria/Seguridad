package com.example.servicioA;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.servicioA.server.HttpServerLauncher;

import io.vertx.core.Vertx;

@SpringBootApplication
public class ServicioAApplication {
	@Autowired HttpServerLauncher launcher;

	public static void main(String[] args) {
	ConfigurableApplicationContext context=SpringApplication.run(ServicioAApplication.class, args);

	}
	  @PostConstruct
	  public void deployVerticle() {
	    Vertx.vertx().deployVerticle(launcher);
	  }
}
