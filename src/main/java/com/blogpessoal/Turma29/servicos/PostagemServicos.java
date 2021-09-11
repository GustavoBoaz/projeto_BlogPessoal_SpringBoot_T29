package com.blogpessoal.Turma29.servicos;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogpessoal.Turma29.modelos.Postagem;
import com.blogpessoal.Turma29.repositorios.PostagemRepositorio;

@Service
public class PostagemServicos {

	
	private @Autowired PostagemRepositorio repositorio;
	
	
	/*
	 * Método utilizado para alterar uma postagem que retorna um Optional com Postagem
	 * caso corretoou um Optional.empty() caso id da Postagem não exista.
	 * 
	 * @param postagemParaAlterar do tipo Postagem
	 * 
	 * @return Optional com Postagem alterada
	 * 
	 * @since 1.0
	 * 
	 * @author Turma 29
	 * 
	 */


		public Optional<Postagem> atualizarPostagem(Postagem postagemParaAlterar){
			
			return repositorio.findById(postagemParaAlterar.getIdPostagem()).map(postagemExistente->{
				postagemExistente.setTitulo(postagemParaAlterar.getTitulo());
				postagemExistente.setDescricao(postagemParaAlterar.getDescricao());
				return Optional.ofNullable(repositorio.save(postagemExistente));
			}).orElseGet(() ->{
				
				return Optional.empty();
			});
		}



}
