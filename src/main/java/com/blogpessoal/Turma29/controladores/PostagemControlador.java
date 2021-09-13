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

import com.blogpessoal.Turma29.modelos.Postagem;
import com.blogpessoal.Turma29.repositorios.PostagemRepositorio;
import com.blogpessoal.Turma29.servicos.PostagemServicos;

@RestController
@RequestMapping("/api/v1/postagem")
public class PostagemControlador {

	private @Autowired PostagemRepositorio repositorio;
	private @Autowired PostagemServicos servicos;

	@GetMapping("/todas")
	public ResponseEntity<List<Postagem>> pegarTodos() {
		List<Postagem> objetoLista = repositorio.findAll();

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@PostMapping("/salvar")
	public ResponseEntity<Postagem> salvar(@Valid @RequestBody Postagem novaPostagem) {
		return ResponseEntity.status(201).body(repositorio.save(novaPostagem));
	}

	@GetMapping("/{id_postagem}")
	public ResponseEntity<Postagem> buscarPorId(@PathVariable(value = "id_postagem") Long idPostagem) {
		Optional<Postagem> objetoPostagem = repositorio.findById(idPostagem);

		if (objetoPostagem.isPresent()) {
			return ResponseEntity.status(200).body(objetoPostagem.get());
		} else {
			return ResponseEntity.status(204).build();
		}
	}

	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> buscarPorTituloI(@PathVariable(value = "titulo") String titulo) {
		List<Postagem> objetoLista = repositorio.findAllByTituloContainingIgnoreCase(titulo);

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@GetMapping("/pesquisa")
	public ResponseEntity<List<Postagem>> buscarPorTituloII(@RequestParam(defaultValue = "") String titulo) {
		List<Postagem> objetoLista = repositorio.findAllByTituloContainingIgnoreCase(titulo);

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@PutMapping("/atualizar")
	public ResponseEntity<Object> atualizar(@Valid @RequestBody Postagem postagemParaAtualizar) {
		Optional<?> objetoAlterado = servicos.atualizarPostagem(postagemParaAtualizar);

		if (objetoAlterado.isPresent()) {
			return ResponseEntity.status(201).body(objetoAlterado.get());
		} else {
			return ResponseEntity.status(204).build();
		}
	}

	@DeleteMapping("/deletar/{id_postagem}")
	public ResponseEntity<Object> deletarPostagemPorId(@PathVariable(value = "id_postagem") Long idPostagem) {
		Optional<Postagem> objetoOptional = repositorio.findById(idPostagem);
		if (objetoOptional.isEmpty()) {
			return ResponseEntity.status(400).build();
		} else {
			repositorio.deleteById(idPostagem);
			return ResponseEntity.status(200).build();
		}
	}

}
