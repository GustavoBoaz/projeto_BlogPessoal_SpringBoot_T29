package com.blogpessoal.Turma29.modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 * Classe utilizada como Entidade no Banco de dados para Postagem, a mesma possui
 * atributos que seram colunas no banco com titulo : titulo, descricao
 * 
 * @author Turma 29
 * @since 1.0
 */
@Entity
public class Postagem {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idPostagem;
	private @NotBlank String titulo;
	private @NotBlank String descricao;

	public Long getIdPostagem() {
		return idPostagem;
	}

	public void setIdPostagem(Long idPostagem) {
		this.idPostagem = idPostagem;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
