package com.blogpessoal.Turma29.servicos;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogpessoal.Turma29.modelos.Tema;
import com.blogpessoal.Turma29.repositorios.TemaRepositorio;

@Service
public class TemaServicos {
	
	@Autowired
	private TemaRepositorio repository;
	
	public Optional<Tema> alterarTema(Tema temaParaAlterar) {
		return repository.findById(temaParaAlterar.getIdTema()).map(temaExistente -> {
			temaExistente.setTema(temaParaAlterar.getTema());
			return Optional.ofNullable(repository.save(temaExistente));
			
		}).orElseGet(() -> {
			
			return Optional.empty();
		});
	}
}