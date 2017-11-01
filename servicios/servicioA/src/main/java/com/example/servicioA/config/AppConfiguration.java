package com.example.servicioA.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import io.vertx.core.Vertx;

@Configuration
public class AppConfiguration {

	  @Autowired
	  Environment environment;
	
	 public int httpPort() {
		    return environment.getProperty("http.port", Integer.class, 8080);
	  }
	 
	  @Bean
	  public Vertx getVertx() {
		return Vertx.vertx();
	  }
}
