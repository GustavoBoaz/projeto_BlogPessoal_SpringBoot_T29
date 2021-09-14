package com.blogpessoal.Turma29.controladores;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.blogpessoal.Turma29.modelos.Tema;
import com.blogpessoal.Turma29.repositorios.TemaRepositorio;
import com.blogpessoal.Turma29.servicos.TemaServicos;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/tema")
@Api(tags = "Controlador de Tema", description = "Utilitario de Temas")
@CrossOrigin("*")
public class TemaControlador {

	private @Autowired TemaRepositorio repositorio;
	private @Autowired TemaServicos servicos;

	@ApiOperation(value = "Busca lista de temas no sistema")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna lista de temas"),
			@ApiResponse(code = 204, message = "Retorno sem tema")
	})
	@GetMapping("/todos")
	public ResponseEntity<List<Tema>> pegarTodos() {
		List<Tema> objetoLista = repositorio.findAll();

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@ApiOperation(value = "Salva novo tema no sistema")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Retorna tema cadastrado")
	})
	@PostMapping("/salvar")
	public ResponseEntity<Tema> salvar(@Valid @RequestBody Tema novoTema) {
		return ResponseEntity.status(201).body(repositorio.save(novoTema));
	}

	@ApiOperation(value = "Busca tema por Id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna tema existente"),
			@ApiResponse(code = 204, message = "Retorno inexistente")
	})
	@GetMapping("/{id_tema}")
	public ResponseEntity<Tema> buscarPorId(@PathVariable(value = "id_tema") Long idTema) {
		Optional<Tema> objetoTema = repositorio.findById(idTema);

		if (objetoTema.isPresent()) {
			return ResponseEntity.status(200).body(objetoTema.get());
		} else {
			return ResponseEntity.status(204).build();
		}
	}

	@ApiOperation(value = "Busca tema por nome")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna tema existente ou inexistente"),
			@ApiResponse(code = 204, message = "Retorno inexistente")
	})
	@GetMapping("/tema/{tema}")
	public ResponseEntity<List<Tema>> buscarPorTemaI(@PathVariable(value = "tema") String tema) {
		List<Tema> objetoLista = repositorio.findAllByTemaContainingIgnoreCase(tema);

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@ApiOperation(value = "Busca tema por nome")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna tema existente ou inexistente"),
			@ApiResponse(code = 204, message = "Retorno inexistente")
	})
	@GetMapping("/pesquisa")
	public ResponseEntity<List<Tema>> buscarPorTemaII(@RequestParam(defaultValue = "") String tema) {
		List<Tema> objetoLista = repositorio.findAllByTemaContainingIgnoreCase(tema);

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@ApiOperation(value = "Atualizar tema existente")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Retorna tema atualizado"),
			@ApiResponse(code = 400, message = "Id de tema invalido")
	})
	@PutMapping("/atualizar")
	public ResponseEntity<Tema> atualizar(@Valid @RequestBody Tema temaParaAtualizar) {
		Optional<Tema> objetoAlterado = servicos.atualizarTema(temaParaAtualizar);

		if (objetoAlterado.isPresent()) {
			return ResponseEntity.status(201).body(objetoAlterado.get());
		} else {
			return ResponseEntity.status(400).build();
		}
	}

	@ApiOperation(value = "Deletar tema existente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Caso deletado!"),
			@ApiResponse(code = 400, message = "Id de usuario invalido")
	})
	@DeleteMapping("/deletar/{id_tema}")
	public ResponseEntity<Object> deletarTemaPorId(@PathVariable(value = "id_tema") Long idTema) {
		Optional<Tema> objetoOptional = repositorio.findById(idTema);
		if (objetoOptional.isEmpty()) {
			return ResponseEntity.status(400).build();
		} else {
			repositorio.deleteById(idTema);
			return ResponseEntity.status(200).build();
		}
	}

}
