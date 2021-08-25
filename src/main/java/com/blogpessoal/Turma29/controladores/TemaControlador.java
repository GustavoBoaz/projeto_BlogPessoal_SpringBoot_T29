package com.blogpessoal.Turma29.controladores;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/api/v1/tema")
public class TemaControlador {

	private @Autowired TemaRepositorio repositorio;

	@GetMapping("/todos")
	public ResponseEntity<List<Tema>> pegarTodos() {
		List<Tema> objetoLista = repositorio.findAll();

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@PostMapping("/salvar")
	public ResponseEntity<Tema> salvar(@Valid @RequestBody Tema novoTema) {
		return ResponseEntity.status(201).body(repositorio.save(novoTema));
	}

	@GetMapping("/{id_tema}")
	public ResponseEntity<Tema> buscarPorId(@PathVariable(value = "id_tema") Long idTema) {
		Optional<Tema> objetoTema = repositorio.findById(idTema);

		if (objetoTema.isPresent()) {
			return ResponseEntity.status(200).body(objetoTema.get());
		} else {
			return ResponseEntity.status(204).build();
		}
	}

	@GetMapping("/{tema}")
	public ResponseEntity<List<Tema>> buscarPorTemaI(@PathVariable(value = "tema") String tema) {
		List<Tema> objetoLista = repositorio.findAllByTemaContainingIgnoreCase(tema);

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@GetMapping("/pesquisa")
	public ResponseEntity<List<Tema>> buscarPorTemaII(@RequestParam(defaultValue = "") String tema) {
		List<Tema> objetoLista = repositorio.findAllByTemaContainingIgnoreCase(tema);

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@PutMapping("/atualizar")
	public ResponseEntity<Tema> atualizar(@Valid @RequestBody Tema temaParaAtualizar) {
		return ResponseEntity.status(201).body(repositorio.save(temaParaAtualizar));
	}

	@DeleteMapping("/deletar/{id_tema}")
	public void deletarTemaPorId(@PathVariable(value = "id_tema") Long idTema) {
		repositorio.deleteById(idTema);
	}

}
