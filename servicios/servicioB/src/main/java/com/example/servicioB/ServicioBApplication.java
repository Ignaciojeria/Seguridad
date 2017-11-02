package com.example.servicioB;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.servicioB.server.HttpServerLauncher;

import io.vertx.core.Vertx;



@SpringBootApplication
public class ServicioBApplication {
	@Autowired HttpServerLauncher launcher;

	public static void main(String[] args) {
		SpringApplication.run(ServicioBApplication.class, args);
	}
	
	  @PostConstruct
	  public void deployVerticle() {
	    Vertx.vertx().deployVerticle(launcher);
	  }
}
