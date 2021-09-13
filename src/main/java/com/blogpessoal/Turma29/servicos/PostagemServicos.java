package com.blogpessoal.Turma29.servicos;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogpessoal.Turma29.modelos.Postagem;
import com.blogpessoal.Turma29.modelos.Tema;
import com.blogpessoal.Turma29.repositorios.PostagemRepositorio;
import com.blogpessoal.Turma29.repositorios.TemaRepositorio;
import com.blogpessoal.Turma29.repositorios.UsuarioRepositorio;

@Service
public class PostagemServicos {

	private @Autowired PostagemRepositorio repositorioP;
	private @Autowired UsuarioRepositorio repositorioU;
	private @Autowired TemaRepositorio repositorioT;

	/**
	 * Método utilizado para alterar uma postagem que retorna um Optional com
	 * Postagem caso corretoou um Optional.empty() caso id da Postagem não exista.
	 * 
	 * @param postagemParaAlterar do tipo Postagem
	 * @return Optional com Postagem alterada
	 * @since 1.5
	 * @author Turma 29
	 */
	public Optional<Postagem> atualizarPostagem(Postagem postagemParaAlterar) {
		return repositorioP.findById(postagemParaAlterar.getIdPostagem()).map(postagemExistente -> {
			postagemExistente.setTitulo(postagemParaAlterar.getTitulo());
			postagemExistente.setDescricao(postagemParaAlterar.getDescricao());
			return Optional.ofNullable(repositorioP.save(postagemExistente));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}

	/**
	 * Método usado no cadastro de uma nova postagem dentro do banco, validando se o
	 * usuario criador é existente. O id do usuario criador e o id do tema devem ser
	 * repassados dentro do objeto postagem para que a devida criação seja efetuada.
	 * Caso o id do usuario não for passado ou não existir no banco, há retorno de
	 * um Optional.empty()
	 * 
	 * @param novaPostagem do tipo Postagem
	 * @return Optional com Postagem
	 * @since 1.5
	 * @author Turma 29
	 */
	public Optional<?> cadastrarPostagem(Postagem novaPostagem) {
		Optional<Tema> objetoExistente = repositorioT.findById(novaPostagem.getTemaRelacionado().getIdTema());
		return repositorioU.findById(novaPostagem.getCriador().getIdUsuario()).map(usuarioExistente -> {
			if (objetoExistente.isPresent()) {
				novaPostagem.setCriador(usuarioExistente);
				novaPostagem.setTemaRelacionado(objetoExistente.get());
				return Optional.ofNullable(repositorioP.save(novaPostagem));
			} else {
				return Optional.empty();
			}
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}

}
