package com.stackroute.moviecruiserserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class MovieCruiserAuthenticationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieCruiserAuthenticationServiceApplication.class, args);
	}

}
