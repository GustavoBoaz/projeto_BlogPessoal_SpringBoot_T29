package com.blogpessoal.Turma29;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/")
public class Turma29Application {
	
	@GetMapping
	public String helloBlogPessoal0() {
		return "Hellow, Blog Pessoal Turma 29 0";
	}
	
	@GetMapping("/rota1")
	public String helloBlogPessoal1() {
		return "Hellow, Blog Pessoal Turma 29 1";
	}
	
	@GetMapping("/rota2")
	public String helloBlogPessoal2() {
		return "Hellow, Blog Pessoal Turma 29 2";
	}

	public static void main(String[] args) {
		SpringApplication.run(Turma29Application.class, args);
	}

}
