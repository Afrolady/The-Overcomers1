package com.TheOvercomers.PacificLife;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@SpringBootApplication (exclude ={SecurityAutoConfiguration.class})
public class PacificLifeApplication {

	@GetMapping("/hello")
	public String hello(){
		return "Hello World!";
	}


	public static void main(String[] args) {
		SpringApplication.run(PacificLifeApplication.class, args);
	}

}
