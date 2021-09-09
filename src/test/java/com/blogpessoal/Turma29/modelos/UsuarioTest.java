package com.blogpessoal.Turma29.modelos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UsuarioTest {

	public Usuario usuario;
	
	@Autowired
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();
	
	@BeforeEach
	public void start() {
		usuario = new Usuario("Luna Boaz", "luna@email.com", "134652");
		
	}
	
	@Test
	void validaUsuarioCorretoRetornaTrue() {
		Set<ConstraintViolation<Usuario>> objetoViolacao = validator.validate(usuario);
		assertTrue(objetoViolacao.isEmpty());
	}
	
	@Test
	void validaNomeDeUsuarioIncorretoRetornaFalse() {
		Usuario usuarioComFalha = new Usuario(null, "bia@email.com", "134652");
		Set<ConstraintViolation<Usuario>> objetoViolacao = validator.validate(usuarioComFalha);
		assertFalse(objetoViolacao.isEmpty());
	}
}
