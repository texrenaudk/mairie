package com.example.projet_mairie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ProjetMairieApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetMairieApplication.class, args);
	}

	@RestController
	public class HomeController {

		@GetMapping("/")
		public String home() {
			return "Bonjour et bienvenue sur mon application Spring Boot !";
		}
	}

}
