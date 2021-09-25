package com.blogpessoal.Turma29.controladores;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.blogpessoal.Turma29.modelos.Postagem;
import com.blogpessoal.Turma29.repositorios.PostagemRepositorio;
import com.blogpessoal.Turma29.servicos.PostagemServicos;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/postagem")
@Api(tags = "Controlador de Postagem", description = "Utilitario de Postagens")
@CrossOrigin("*")
public class PostagemControlador {

	private @Autowired PostagemRepositorio repositorio;
	private @Autowired PostagemServicos servicos;

	@ApiOperation(value = "Busca lista de postagens no sistema")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna lista de postagens"),
			@ApiResponse(code = 204, message = "Retorno sem postagens")
	})
	@GetMapping("/todas")
	public ResponseEntity<List<Postagem>> pegarTodas() {
		List<Postagem> objetoLista = repositorio.findAll();

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@ApiOperation(value = "Salva nova postagem no sistema")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Retorna postagem cadastrada"),
			@ApiResponse(code = 400, message = "Erro na requisição: Id Tena ou Id Usuario invalido")
	})
	@PostMapping("/salvar")
	public ResponseEntity<Postagem> salvar(@Valid @RequestBody Postagem novaPostagem) {
		return ResponseEntity.status(201).body(repositorio.save(novaPostagem));
	}

	@ApiOperation(value = "Busca postagem por Id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna postagem existente"),
			@ApiResponse(code = 204, message = "Retorno inexistente")
	})
	@GetMapping("/{id_postagem}")
	public ResponseEntity<Postagem> buscarPorId(@PathVariable(value = "id_postagem") Long idPostagem) {
		Optional<Postagem> objetoPostagem = repositorio.findById(idPostagem);

		if (objetoPostagem.isPresent()) {
			return ResponseEntity.status(200).body(objetoPostagem.get());
		} else {
			return ResponseEntity.status(204).build();
		}
	}

	@ApiOperation(value = "Busca postagems por Titulo")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna postagems existente ou inexistente"),
			@ApiResponse(code = 204, message = "Retorno inexistente")
	})
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> buscarPorTituloI(@PathVariable(value = "titulo") String titulo) {
		List<Postagem> objetoLista = repositorio.findAllByTituloContainingIgnoreCase(titulo);

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@ApiOperation(value = "Busca postagems por Titulo")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna postagems existente ou inexistente"),
			@ApiResponse(code = 204, message = "Retorno inexistente")
	})
	@GetMapping("/pesquisa")
	public ResponseEntity<List<Postagem>> buscarPorTituloII(@RequestParam(defaultValue = "") String titulo) {
		List<Postagem> objetoLista = repositorio.findAllByTituloContainingIgnoreCase(titulo);

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@ApiOperation(value = "Atualizar postagem existente")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Retorna postagem atualizada"),
			@ApiResponse(code = 400, message = "Id de postagem invalida")
	})
	@PutMapping("/atualizar")
	public ResponseEntity<Object> atualizar(@Valid @RequestBody Postagem postagemParaAtualizar) {
		Optional<?> objetoAlterado = servicos.atualizarPostagem(postagemParaAtualizar);

		if (objetoAlterado.isPresent()) {
			return ResponseEntity.status(201).body(objetoAlterado.get());
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id de postagem invalido!", null);
		}
	}

	@ApiOperation(value = "Deletar postagem existente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Caso deletada!"),
			@ApiResponse(code = 400, message = "Id de postagem invalida")
	})
	@DeleteMapping("/deletar/{id_postagem}")
	public ResponseEntity<Object> deletarPostagemPorId(@PathVariable(value = "id_postagem") Long idPostagem) {
		Optional<Postagem> objetoOptional = repositorio.findById(idPostagem);
		if (objetoOptional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id de postagem invalido!", null);
		} else {
			repositorio.deleteById(idPostagem);
			return ResponseEntity.status(200).build();
		}
	}

}
