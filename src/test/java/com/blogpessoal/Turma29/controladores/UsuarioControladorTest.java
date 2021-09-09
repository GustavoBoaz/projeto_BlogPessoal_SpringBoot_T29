package com.blogpessoal.Turma29.controladores;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.blogpessoal.Turma29.modelos.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsuarioControladorTest {

	private @Autowired TestRestTemplate testrestTemplate;

	private Usuario usuarioParaCriar;
	private Usuario usuarioParaAlterar;

	@BeforeAll
	void start() {
		usuarioParaCriar = new Usuario(0L, "Tainan Boaz", "tainan@email.com", "134652");
		usuarioParaAlterar = new Usuario(1L, "Charles Boaz ALTERADO", "charles@email.com", "134652");

	}

	@Disabled
	@Test
	void salvaUsuarioNovoNoBancoRetornaStatus201() {

		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(usuarioParaCriar);

		ResponseEntity<Usuario> resposta = testrestTemplate.exchange("/api/v1/usuario/salvar", HttpMethod.POST,
				requisicao, Usuario.class);

		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
	}

	@Disabled
	@Test
	void buscarTodesRetornaStatus200() {

		ResponseEntity<String> resposta = testrestTemplate.withBasicAuth("tainan@email.com", "134652")
				.exchange("/api/v1/usuario/todes", HttpMethod.GET, null, String.class);

		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}

	@Disabled
	@Test
	void atualizarUsuarioNoBancoRetornaStatus201() {

		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(usuarioParaAlterar);

		ResponseEntity<Usuario> resposta = testrestTemplate.withBasicAuth("tainan@email.com", "134652")
				.exchange("/api/v1/usuario/atualizar", HttpMethod.PUT, requisicao, Usuario.class);

		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());

	}

}
