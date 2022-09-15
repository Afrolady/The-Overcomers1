package com.TheOvercomers.PacificLife;

import com.TheOvercomers.PacificLife.modelos.Empresa;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.SourceType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
/*import org.springframework.web.bind.annotation.RestController;*/



@SpringBootApplication (exclude ={SecurityAutoConfiguration.class})
public class PacificLifeApplication {

	@GetMapping("/hello")
	public String hello(){
		return "Hello World!";
	}

	@GetMapping("/test")
	public String test() {
		Empresa emp = new Empresa("Pacific Life", "Carrera 6 # 85 -68", "3213213211", "800221221-1");
		emp.setNombre("Pacific Life LTDA");
		System.out.println("Aqui ya se creo la empresa y se renombro");
		return emp.getNombre();
	}

	public static void main(String[] args) {
		SpringApplication.run(PacificLifeApplication.class, args);
	}

}
