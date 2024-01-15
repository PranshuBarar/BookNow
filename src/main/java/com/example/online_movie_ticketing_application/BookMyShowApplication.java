package com.example.online_movie_ticketing_application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookMyShowApplication {
	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/springboot3");
		SpringApplication.run(BookMyShowApplication.class, args);
	}
}
