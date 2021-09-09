package com.blogpessoal.Turma29.repositorios;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.blogpessoal.Turma29.modelos.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsuarioRepositorioTest {

	private @Autowired UsuarioRepositorio repositorio;
	
	@BeforeEach
	void start() {
		
		Usuario objeto1 = new Usuario(0L,"Charles Boaz", "charles@email.com", "134652");
		if(!repositorio.findByEmail(objeto1.getEmail()).isPresent())
			repositorio.save(objeto1);
		
		Usuario objeto2 = new Usuario(0L,"Evelyn Boaz", "evelyn@email.com", "134652");
		if(!repositorio.findByEmail(objeto2.getEmail()).isPresent())
			repositorio.save(objeto2);
		
	}
	
	@Test
	void findByEmailExistenteRetornaTrue() {
		Usuario objetoCharles = repositorio.findByEmail("charles@email.com").get();
		
		assertTrue(objetoCharles.getEmail().equals("charles@email.com"));
	}
	
	@Test
	void findAllByNomeContainingIgnoreCaseRetornaRetornaDoisUsuarios() {
		List<Usuario> objetoLista = repositorio.findAllByNomeContainingIgnoreCase("Boaz");
		
		assertEquals(2, objetoLista.size());
	}
	
	@AfterAll
	void end() {
		System.out.println("Teste Finalizado!");
	}

}
